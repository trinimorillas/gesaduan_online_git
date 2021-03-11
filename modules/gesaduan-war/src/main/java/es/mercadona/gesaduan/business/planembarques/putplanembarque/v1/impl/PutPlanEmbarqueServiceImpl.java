package es.mercadona.gesaduan.business.planembarques.putplanembarque.v1.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.planembarques.putplanembarque.v1.PutPlanEmbarqueService;
import es.mercadona.gesaduan.dao.planembarques.cambiarestado.v1.CambiarEstadoDAO;
import es.mercadona.gesaduan.dao.planembarques.putplanembarque.v1.PutPlanEmbarqueDAO;
import es.mercadona.gesaduan.dto.planembarques.putplanembarque.v1.InputDatosPutDTO;
import es.mercadona.gesaduan.dto.planembarques.putplanembarque.v1.restfull.OutputPlanEmbarquePutDTO;
import es.mercadona.gesaduan.exception.EnumGesaduanException;
import es.mercadona.gesaduan.exception.GesaduanException;
import es.mercadona.gesaduan.jpa.planembarques.v1.PlanEmbarquesJPA;

public class PutPlanEmbarqueServiceImpl implements PutPlanEmbarqueService {

	@Inject
	private PutPlanEmbarqueDAO putPlanEmbarqueDao;
	
	@Inject
	private CambiarEstadoDAO cambiarEstadoDao;
	
	@Inject
	private org.slf4j.Logger logger;		
	
	@Override
	public OutputPlanEmbarquePutDTO crearPlanEmbarque(InputDatosPutDTO input) {
		
		PlanEmbarquesJPA datos = new PlanEmbarquesJPA();

		Date fechaEmbarque = null;
		if (input.getDatos().getFechaEmbarque() != null) {	
			try {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				fechaEmbarque = dateFormat.parse(input.getDatos().getFechaEmbarque());
			} catch (ParseException e) {
				this.logger.error("({}-{}) ERROR - {} {}","PutPlanEmbarqueServiceImpl(GESADUAN)","crearPlanEmbarque",e.getClass().getSimpleName(),e.getMessage());
			}
		}
		
		datos.setFechaEmbarque(fechaEmbarque);
		datos.setBloqueOrigen(input.getDatos().getCodigoBloqueOrigen()); 
		datos.setPuertoOrigen(input.getDatos().getCodigoPuertoEmbarque());
		datos.setPuertoDestino(input.getDatos().getCodigoPuertoDesembarque());
		datos.setNaviera(input.getDatos().getCodigoNaviera());
		datos.setEstado(input.getDatos().getCodigoEstado());
		datos.setUsuarioCreacion(input.getMetadatos().getCodigoUsuario().toUpperCase());
		
		return putPlanEmbarqueDao.crearPlanEmbarque(datos);		
	}
	
	@Override
	public OutputPlanEmbarquePutDTO modificarPlanEmbarque(InputDatosPutDTO input) throws GesaduanException {
		if (cambiarEstadoDao.getEstadoActual(input.getDatos().getCodigoEmbarque()) != 1)
			throw new GesaduanException(EnumGesaduanException.ESTADO_NO_EN_CURSO);
		
		PlanEmbarquesJPA datos = new PlanEmbarquesJPA();
		
		datos.setCodigoEmbarque(input.getDatos().getCodigoEmbarque());
		
		Date fechaEmbarque = null;
		if (input.getDatos().getFechaEmbarque() != null) {	
			try {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				fechaEmbarque = dateFormat.parse(input.getDatos().getFechaEmbarque());
			} catch (ParseException e) {
				this.logger.error("({}-{}) ERROR - {} {}","PutPlanEmbarqueServiceImpl(GESADUAN)","modificarPlanEmbarque",e.getClass().getSimpleName(),e.getMessage());
			}
		}
		
		if (fechaEmbarque != null) datos.setFechaEmbarque(fechaEmbarque);
		if (input.getDatos().getCodigoBloqueOrigen() != null) datos.setBloqueOrigen(input.getDatos().getCodigoBloqueOrigen()); 
		if (input.getDatos().getCodigoPuertoEmbarque() != null) datos.setPuertoOrigen(input.getDatos().getCodigoPuertoEmbarque());
		if (input.getDatos().getCodigoPuertoDesembarque() != null) datos.setPuertoDestino(input.getDatos().getCodigoPuertoDesembarque());
		if (input.getDatos().getCodigoNaviera() != null) datos.setNaviera(input.getDatos().getCodigoNaviera());
		datos.setUsuarioModificacion(input.getMetadatos().getCodigoUsuario().toUpperCase());
		
		return putPlanEmbarqueDao.modificarPlanEmbarque(datos);
		
	}

}
