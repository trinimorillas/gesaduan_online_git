package es.mercadona.gesaduan.business.dosier.putdosier.v1.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

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
	
	
	@Override
	public OutputDosierPutDTO putDosier(InputDatosPutDTO input) {
		
		OutputDosierPutDTO result = new OutputDosierPutDTO();
		DatosDosierPutDTO datos = new DatosDosierPutDTO();		
		List<DosierPutDTO> listaDosieres = new ArrayList<>();
		List<InputDosierDTO> dosieres = input.getDatos().getDosier();
		String codigoUsario = input.getMetadatos().getCodigoUsuario();	
		Long nuevoNumDosier = null;
		
		DosierPkJPA dosierPk = putDosierDao.getNewDosierPk();		
		
		for (InputDosierDTO dosier: dosieres) {
			
			nuevoNumDosier = dosierPk.getNumDosier();
			DosierJPA dosierJPA = new DosierJPA();
			dosierJPA.setId(dosierPk);
			
			dosierJPA.setCodigoEmbarque(dosier.getCodigoEmbarque());
			dosierJPA.setAgenciaExportacion(dosier.getCodigoAgenciaExportacion());
			dosierJPA.setAgenciaImportacion(dosier.getCodigoAgenciaImportacion());
			dosierJPA.setUsuarioCreacion(codigoUsario);
			
			// Crea el dosier
			putDosierDao.crearDosier(dosierJPA);
			
			// Crea la realacion con equipo
			putRelacionEquipoDosier(dosierJPA ,dosier.getEquipo());
			
			DosierPutDTO dosierOutPut = new DosierPutDTO();
			
			dosierOutPut.setNumDosier(dosierPk.getNumDosier());
			dosierOutPut.setAnyoDosier(dosierPk.getAnyoDosier());			
			
			listaDosieres.add(dosierOutPut);
			
			dosierPk.setNumDosier(dosierPk.getNumDosier() + 1);
			
		}
		
		// actualiza C_VARIABLE con el último numero de dosier
		if (nuevoNumDosier != null) {
			putDosierDao.updateNumDosier(nuevoNumDosier);
		}
		
		if (listaDosieres.isEmpty()) listaDosieres = null;
		datos.setDosier(listaDosieres);
		result.setDatos(datos);
		
		
		return result;	
	}
	
	private void putRelacionEquipoDosier(DosierJPA dosierJPA ,List<InputDosierEquipoDTO> equipos) {
		
		// Se crea un nuevo dosier
		for (InputDosierEquipoDTO equipo: equipos) {
			
			DosierEquipoJPA dosierEquipoJPA = new DosierEquipoJPA();
			
			dosierEquipoJPA.setNumDosier(dosierJPA.getId().getNumDosier());
			dosierEquipoJPA.setAnyoDosier(dosierJPA.getId().getAnyoDosier());
			dosierEquipoJPA.setCodigoEquipo(equipo.getCodigoEquipo());
			dosierEquipoJPA.setMatricula(equipo.getMatricula());
			dosierEquipoJPA.setUsuarioCreacion(dosierJPA.getUsuarioCreacion());
			
			// Crea la realacion con equipo			
			putDosierDao.crearRelacionDosierEquipo(dosierEquipoJPA);
			
			// Crea la realacion con contenedor
			if (equipo.getContenedor().isEmpty()) {				
				putRelacionDosierContenedorDeEquipo(dosierEquipoJPA);
			} else {
				putRelacionDosierContenedor(dosierEquipoJPA ,equipo.getContenedor());
			}
			
			// actualiza estado de la documentacion del equipo 
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

}
