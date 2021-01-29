package es.mercadona.gesaduan.dao.planembarques.putplanembarque.v1.impl;

import java.util.Date;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.fwk.data.DaoBaseImpl;
import es.mercadona.gesaduan.dao.planembarques.putplanembarque.v1.PutPlanEmbarqueDAO;
import es.mercadona.gesaduan.dto.planembarques.putplanembarque.v1.restfull.DatosPlanEmbarquePutDTO;
import es.mercadona.gesaduan.dto.planembarques.putplanembarque.v1.restfull.OutputPlanEmbarquePutDTO;
import es.mercadona.gesaduan.jpa.planembarques.v1.PlanEmbarquesJPA;

public class PutPlanEmbarqueDAOImpl extends DaoBaseImpl<Long, PlanEmbarquesJPA> implements PutPlanEmbarqueDAO {

	@Inject
	private org.slf4j.Logger logger;		
	
	@PersistenceContext
	private EntityManager entityM;
	
	@Override
	protected EntityManager getEntityManager() {
		return this.entityM;
	}

	@Override
	public void setEntityClass() {
		this.entityClass = PlanEmbarquesJPA.class;		
	}
	
	@Transactional
	@Override
	public OutputPlanEmbarquePutDTO crearPlanEmbarque(PlanEmbarquesJPA input) {
		
		OutputPlanEmbarquePutDTO resultSalida = new OutputPlanEmbarquePutDTO();
		DatosPlanEmbarquePutDTO result = new DatosPlanEmbarquePutDTO();
		
		try {
		
			Date fechaHoy = new Date();		
			input.setFechaCreacion(fechaHoy);
			input.setCodigoAplicacion("GESADUAN");
			entityM.persist(input);
			entityM.flush();
			
			
			result.setCodigoEmbarque(input.getCodigoEmbarque());
			resultSalida.setDatos(result);
			
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","PutPlanEmbarqueDAOImpl(GESADUAN)","crearPlanEmbarque",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}			
		
		return resultSalida;
	}
	
	@Transactional
	@Override
	public OutputPlanEmbarquePutDTO modificarPlanEmbarque(PlanEmbarquesJPA input) {
		
		OutputPlanEmbarquePutDTO resultSalida = new OutputPlanEmbarquePutDTO();
		DatosPlanEmbarquePutDTO result = new DatosPlanEmbarquePutDTO();		
		
		try {
		
			Long codigoEmbarque = input.getCodigoEmbarque();
			Date fechaHoy = new Date();
			PlanEmbarquesJPA put = entityM.find(PlanEmbarquesJPA.class, codigoEmbarque);		
			
			if (input.getFechaEmbarque() != null) put.setFechaEmbarque(input.getFechaEmbarque());
			if (input.getBloqueOrigen() != null) put.setBloqueOrigen(input.getBloqueOrigen());
			if (input.getPuertoOrigen() != null) put.setPuertoOrigen(input.getPuertoOrigen());
			if (input.getPuertoDestino() != null) put.setPuertoDestino(input.getPuertoDestino());
			if (input.getNaviera() != null) put.setNaviera(input.getNaviera());
			put.setFechaModificacion(fechaHoy);
			put.setCodigoAplicacion("GESADUAN");
			put.setUsuarioModificacion(input.getUsuarioModificacion().toUpperCase());
			entityM.flush();		
			
			result.setCodigoEmbarque(codigoEmbarque);		
			resultSalida.setDatos(result);
		
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","PutPlanEmbarqueDAOImpl(GESADUAN)","modificarPlanEmbarque",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}			
		
		return resultSalida;
	}

}
