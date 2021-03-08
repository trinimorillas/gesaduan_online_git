package es.mercadona.gesaduan.business.dosier.putdosier.v1.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
		
		for (InputDosierDTO dosier: dosieres) {
			
			DosierPkJPA dosierPk = putDosierDao.getNewDosierPk();
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
			
		}
		
		// actualiza C_VARIABLE con el último numero de dosier
		if (nuevoNumDosier != null) {
			
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
			dosierEquipoJPA.setCodigoEquipo(equipo.getCodigEquipo());
			dosierEquipoJPA.setMatricula(equipo.getMatricula());
			dosierEquipoJPA.setCodigoUsuario(dosierJPA.getUsuarioCreacion());
			
			// Crea la realacion con equipo			
			putDosierDao.crearRelacionDosierEquipo(dosierEquipoJPA);
			
			// Crea la realacion con contenedor
			putRelacionEquipoContenedorDosier(dosierJPA ,equipo.getContenedor());
			
			// actualiza estado de la documentacion del equipo 
			
		}

	}	
	
	private void putRelacionEquipoContenedorDosier(DosierJPA dosierJPA ,List<InputDosierEquipoContenedorDTO> contenedores) {
		
		// Se crea un nuevo dosier
		for (InputDosierEquipoContenedorDTO contenedor: contenedores) {
			
			DosierContenedorJPA dosierContenedorJPA = new DosierContenedorJPA();
			
			dosierContenedorJPA.setNumDosier(dosierJPA.getId().getNumDosier());
			dosierContenedorJPA.setAnyoDosier(dosierJPA.getId().getAnyoDosier());
			dosierContenedorJPA.setCodigoAlmacen(contenedor.getCodigoAlmacen());
			dosierContenedorJPA.setNumContenedor(contenedor.getNumContenedor());
			dosierContenedorJPA.setFechaExpedicion(contenedor.getFechaExpedicion());			
			dosierContenedorJPA.setCodigoUsuario(dosierJPA.getUsuarioCreacion());
			
			putDosierDao.crearRelacionDosierContenedor(dosierContenedorJPA);
			
		}

	}		

}
