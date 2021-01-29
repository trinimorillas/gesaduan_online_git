package es.mercadona.gesaduan.dao.equipotransporte.putequipotransporte.v1.impl;

import java.util.Date;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.fwk.data.DaoBaseImpl;
import es.mercadona.gesaduan.dao.equipotransporte.putequipotransporte.v1.PutEquipoTransporteDAO;
import es.mercadona.gesaduan.dto.equipotransporte.putequipotransporte.v1.restfull.DatosEquipoTransportePutDTO;
import es.mercadona.gesaduan.dto.equipotransporte.putequipotransporte.v1.restfull.OutputEquipoTransportePutDTO;
import es.mercadona.gesaduan.jpa.equipotransporte.v1.EquipoTransporteJPA;

public class PutEquipoTransporteDAOImpl extends DaoBaseImpl<Long, EquipoTransporteJPA> implements PutEquipoTransporteDAO {

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
		this.entityClass = EquipoTransporteJPA.class;		
	}
	
	@Transactional
	@Override
	public OutputEquipoTransportePutDTO crearEquipoTransporte(EquipoTransporteJPA input) {	
		
		OutputEquipoTransportePutDTO resultSalida;
		
		try {
			
			Date fechaHoy = new Date();
			input.setFechaCreacion(fechaHoy);
			input.setCodigoAplicacion("GESADUAN");
			input.setOcupacion(input.getOcupacion());
			entityM.persist(input);
			entityM.flush();
			
			actualizarNumeroEquipos(input.getCodigoEmbarque());
			
			resultSalida = new OutputEquipoTransportePutDTO();
			DatosEquipoTransportePutDTO datos = new DatosEquipoTransportePutDTO();
			datos.setCodigoEquipo(input.getCodigoEquipo());
			resultSalida.setDatos(datos);
		
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","PutEquipoTransporteDAOImpl(GESADUAN)","crearEquipoTransporte",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}			
		
		return resultSalida;
	}
	
	@Transactional
	@Override
	public OutputEquipoTransportePutDTO modificarEquipoTransporte(EquipoTransporteJPA input) {
		
		OutputEquipoTransportePutDTO resultSalida;
		
		try {
		
			Long codigoEquipo = input.getCodigoEquipo();
			Date fechaHoy = new Date();
			EquipoTransporteJPA put = entityM.find(EquipoTransporteJPA.class, codigoEquipo);
			
			put.setMatricula(input.getMatricula());
			put.setCodigoProveedor(input.getCodigoProveedor());
			put.setTemperatura(input.getTemperatura());
			if (input.getCapacidad() != null) {
				put.setCapacidad(input.getCapacidad());		
			} else {
				put.setCapacidad(0);
			}
			put.setFechaCarga(input.getFechaCarga());
			put.setObservaciones(input.getObservaciones());		
			put.setFechaModificacion(fechaHoy);
			put.setCodigoAplicacion("GESADUAN");
			put.setUsuarioModificacion(input.getUsuarioModificacion().toUpperCase());
			entityM.flush();		
			
			resultSalida = new OutputEquipoTransportePutDTO();
			DatosEquipoTransportePutDTO datos = new DatosEquipoTransportePutDTO();
			datos.setCodigoEquipo(codigoEquipo);
			resultSalida.setDatos(datos);
		
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","PutEquipoTransporteDAOImpl(GESADUAN)","modificarEquipoTransporte",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}			
		
		return resultSalida;
	}
	
	@Override
	public void actualizarNumeroEquipos(Long codigoEmbarque) {
		
		try {
			
			String update = "UPDATE D_PLAN_EMBARQUE PE SET PE.NUM_EQUIPOS = (SELECT NVL(NUM_EQUIPOS, 0)+1 FROM D_PLAN_EMBARQUE WHERE COD_N_EMBARQUE = ?codigoEmbarque) WHERE PE.COD_N_EMBARQUE = ?codigoEmbarque";
			final Query query = getEntityManager().createNativeQuery(update);				
			query.setParameter("codigoEmbarque", codigoEmbarque);		
			query.executeUpdate();
		
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","PutEquipoTransporteDAOImpl(GESADUAN)","actualizarNumeroEquipos",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}		
	}

}
