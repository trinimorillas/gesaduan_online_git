package es.mercadona.gesaduan.business.dosier.putdosier.v1.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import es.mercadona.gesaduan.business.dosier.putdosier.v1.PutDosierService;
import es.mercadona.gesaduan.dao.dosier.putdosier.v1.PutDosierDAO;
import es.mercadona.gesaduan.dto.dosier.putdosier.v1.InputDatosPutDTO;
import es.mercadona.gesaduan.dto.dosier.putdosier.v1.InputDosierDTO;
import es.mercadona.gesaduan.dto.dosier.putdosier.v1.InputDosierEquipoContenedorDTO;
import es.mercadona.gesaduan.dto.dosier.putdosier.v1.InputDosierEquipoDTO;
import es.mercadona.gesaduan.dto.dosier.putdosier.v1.restfull.DatosDosierPutDTO;
import es.mercadona.gesaduan.dto.dosier.putdosier.v1.restfull.DosierPutDTO;
import es.mercadona.gesaduan.dto.dosier.putdosier.v1.restfull.OutputDosierPutDTO;
import es.mercadona.gesaduan.jpa.dosier.DosierContenedorJPA;
import es.mercadona.gesaduan.jpa.dosier.DosierEquipoJPA;
import es.mercadona.gesaduan.jpa.dosier.DosierJPA;
import es.mercadona.gesaduan.jpa.dosier.DosierPkJPA;

public class PutDosierServiceImpl implements PutDosierService {

	@Inject
	private PutDosierDAO putDosierDao;
	
	@Transactional
	@Override
	public OutputDosierPutDTO putDosier(InputDatosPutDTO input) {		
		OutputDosierPutDTO result = new OutputDosierPutDTO();
		DatosDosierPutDTO datos = new DatosDosierPutDTO();		
		List<DosierPutDTO> listaDosieres = new ArrayList<>();
		List<InputDosierDTO> dosieres = input.getDatos().getDosier();
		String codigoUsario = input.getMetadatos().getCodigoUsuario();						
		
		for (InputDosierDTO dosier: dosieres) {			
			// Actualiza y obtiene un nuevo numero de dosier
			putDosierDao.updateNumDosier();
			DosierPkJPA dosierPk = putDosierDao.getNewDosierPk();
			
			DosierJPA dosierJPA = new DosierJPA();
			dosierJPA.setId(dosierPk);
			
			dosierJPA.setCodigoEmbarque(dosier.getCodigoEmbarque());
			dosierJPA.setAgenciaExportacion(dosier.getCodigoAgenciaExportacion());
			dosierJPA.setAgenciaImportacion(dosier.getCodigoAgenciaImportacion());
			dosierJPA.setUsuarioCreacion(codigoUsario);
			
			// Crea el dosier
			putDosierDao.crearDosier(dosierJPA);
			
			// Crea la relacion con equipo
			putRelacionEquipoDosier(dosierJPA, dosier.getEquipo());
			
			DosierPutDTO dosierOutPut = new DosierPutDTO();
			
			dosierOutPut.setNumDosier(dosierPk.getNumDosier());
			dosierOutPut.setAnyoDosier(dosierPk.getAnyoDosier());			
			
			listaDosieres.add(dosierOutPut);
			
			// Crea las facturas
			crearFacturas(dosierJPA);
			
			putDosierDao.validarFacturas(dosierPk, input.getMetadatos().getCodigoUsuario());						
		}
		
		if (listaDosieres.isEmpty()) listaDosieres = null;
		datos.setDosier(listaDosieres);
		result.setDatos(datos);		
		
		return result;	
	}
	
	private void putRelacionEquipoDosier(DosierJPA dosierJPA, List<InputDosierEquipoDTO> equipos) {		
		// Se crea un nuevo dosier
		for (InputDosierEquipoDTO equipo: equipos) {			
			DosierEquipoJPA dosierEquipoJPA = new DosierEquipoJPA();
			dosierEquipoJPA.setNumDosier(dosierJPA.getId().getNumDosier());
			dosierEquipoJPA.setAnyoDosier(dosierJPA.getId().getAnyoDosier());
			dosierEquipoJPA.setCodigoEquipo(equipo.getCodigoEquipo());
			dosierEquipoJPA.setMatricula(equipo.getMatricula());
			dosierEquipoJPA.setUsuarioCreacion(dosierJPA.getUsuarioCreacion());
			
			// Crea la relacion con equipo			
			putDosierDao.crearRelacionDosierEquipo(dosierEquipoJPA);
			
			// Crea la relacion con contenedor
			if (equipo.getContenedor().isEmpty()) {				
				putRelacionDosierContenedorDeEquipo(dosierEquipoJPA);
			} else {
				putRelacionDosierContenedor(dosierEquipoJPA, equipo.getContenedor());
			}
			
			// Actualiza estado de la documentacion del equipo 
			putDosierDao.actualizaEstadoDocumentacionEquipo(dosierEquipoJPA);			
		}
	}


	private void putRelacionDosierContenedorDeEquipo(DosierEquipoJPA dosierEquipoJPA) {
		DosierContenedorJPA dosierContenedorJPA = new DosierContenedorJPA();
		
		dosierContenedorJPA.setNumDosier(dosierEquipoJPA.getNumDosier());
		dosierContenedorJPA.setAnyoDosier(dosierEquipoJPA.getAnyoDosier());
		dosierContenedorJPA.setCodigoEquipo(dosierEquipoJPA.getCodigoEquipo());				
		dosierContenedorJPA.setUsuarioCreacion(dosierEquipoJPA.getUsuarioCreacion());
		
		putDosierDao.crearRelacionDosierContenedorDeEquipo(dosierContenedorJPA);
	}	
	
	private void putRelacionDosierContenedor(DosierEquipoJPA dosierEquipoJPA ,List<InputDosierEquipoContenedorDTO> contenedores) {		
		// Se crea un nuevo dosier
		for (InputDosierEquipoContenedorDTO contenedor: contenedores) {
			
			DosierContenedorJPA dosierContenedorJPA = new DosierContenedorJPA();
			
			dosierContenedorJPA.setNumDosier(dosierEquipoJPA.getNumDosier());
			dosierContenedorJPA.setAnyoDosier(dosierEquipoJPA.getAnyoDosier());
			dosierContenedorJPA.setCodigoEquipo(dosierEquipoJPA.getCodigoEquipo());			
			dosierContenedorJPA.setCodigoAlmacen(contenedor.getCodigoAlmacen());
			dosierContenedorJPA.setNumContenedor(contenedor.getNumContenedor());
			dosierContenedorJPA.setFechaExpedicion(contenedor.getFechaExpedicion());			
			dosierContenedorJPA.setUsuarioCreacion(dosierEquipoJPA.getUsuarioCreacion());
			
			putDosierDao.crearRelacionDosierContenedor(dosierContenedorJPA);			
		}
	}
	
	private void crearFacturas(DosierJPA dosierJPA) {
		// Obtener numero de categorias
		int numCategorias = putDosierDao.getNumCategorias(dosierJPA);
		
		// Crear tantas facturas como número de categorías distintas haya
		for (int i = 0; i < numCategorias; i++) {
			putDosierDao.crearFacturas(dosierJPA);
		}
		
		// Insertar pedidos asociados a cada una de las facturas creadas
		putDosierDao.actualizarContenedores(dosierJPA);
		
		// Insertar lineas facturas
		putDosierDao.insertarLineasFacturas(dosierJPA);
		
		// Modificar facturas existentes incluidas en el dosier
		// putDosierDao.updateFacturas(dosierEquipoJPA);
		
		// Modificar los contenedores ficticios de las cargas de Grupaje o Directo, para indicar su factura
		// putDosierDao.updateContenedoresFicticios(dosierEquipoJPA, dosierJPA.getUsuarioCreacion());
		
		// Insertar la relación de las facturas con sus pedidos asociados para cargas de Tienda o Traspaso
		putDosierDao.relFacturaPedidoCargaTT(dosierJPA);
		
		// Insertar la relación de las facturas con sus pedidos asociados para cargas de Directo o Grupaje
		// putDosierDao.relFacturaPedidoCargaDG(dosierEquipoJPA, dosierJPA.getUsuarioCreacion());
	}
	

	

}
