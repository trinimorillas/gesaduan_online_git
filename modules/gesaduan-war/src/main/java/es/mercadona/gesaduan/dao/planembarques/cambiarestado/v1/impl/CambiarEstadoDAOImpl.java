package es.mercadona.gesaduan.dao.planembarques.cambiarestado.v1.impl;

import java.util.Date;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.fwk.data.DaoBaseImpl;
import es.mercadona.gesaduan.dao.planembarques.cambiarestado.v1.CambiarEstadoDAO;
import es.mercadona.gesaduan.dto.planembarques.cambiarestado.v1.restfull.DatosCambiarEstadoDTO;
import es.mercadona.gesaduan.dto.planembarques.cambiarestado.v1.restfull.OutputCambiarEstadoDTO;
import es.mercadona.gesaduan.jpa.planembarques.v1.PlanEmbarquesJPA;

public class CambiarEstadoDAOImpl extends DaoBaseImpl<Long, PlanEmbarquesJPA> implements CambiarEstadoDAO {

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
	public OutputCambiarEstadoDTO cambiarEstado(PlanEmbarquesJPA input) {		

		OutputCambiarEstadoDTO resultSalida = new OutputCambiarEstadoDTO();
		DatosCambiarEstadoDTO result = new DatosCambiarEstadoDTO();		
		
		try {
		
			Date fechaHoy = new Date();
			
			PlanEmbarquesJPA put = entityM.find(PlanEmbarquesJPA.class, input.getCodigoEmbarque());
			put.setEstado(input.getEstado());
			put.setFechaModificacion(fechaHoy);
			put.setCodigoAplicacion("GESADUAN");
			put.setUsuarioModificacion(input.getUsuarioModificacion().toUpperCase());
			put.setUsuarioValidacion(input.getUsuarioValidacion());
			entityM.flush();
			
			
			result.setCodigoEmbarque(input.getCodigoEmbarque());
			resultSalida.setDatos(result);
		
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","CambiarEstadoDAOImpl(GESADUAN)","cambiarEstado",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}			
		
		return resultSalida;
	}
	
	@Override
	public Integer getEstadoActual(Long codigoEmbarque){
		
		final StringBuilder sql = new StringBuilder();
		Integer restultadoQuery ;
			
		try {		
		
			String select = "SELECT COD_N_ESTADO ";
			String from   = "FROM D_PLAN_EMBARQUE ";
			String where  = "WHERE COD_N_EMBARQUE = ?codigoEmbarque";
		
			sql.append(select).append(from).append(where);		
			final Query query = getEntityManager().createNativeQuery(sql.toString());				
			query.setParameter("codigoEmbarque", codigoEmbarque);		
			restultadoQuery = Integer.parseInt(query.getSingleResult().toString());
			
		} catch (Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","CambiarEstadoDAOImpl(GESADUAN)","getEstadoActual",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex,ex.getMessage());
		}
					
		return restultadoQuery;
		
	}
	
	@Override
	public Integer getEquipos(Long codigoEmbarque) {	
		
		Integer restultadoQuery;
		
		try {
		
			final StringBuilder sql = new StringBuilder();
				
			String select = "SELECT COUNT(*) ";
			String from   = "FROM D_EQUIPO_TRANSPORTE  ";
			String where  = "WHERE COD_N_EMBARQUE = ?codigoEmbarque";
			
			sql.append(select).append(from).append(where);		
			final Query query = getEntityManager().createNativeQuery(sql.toString());				
			query.setParameter("codigoEmbarque", codigoEmbarque);		
			restultadoQuery = Integer.parseInt(query.getSingleResult().toString());
		
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","CambiarEstadoDAOImpl(GESADUAN)","getEquipos",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}				
		
		return restultadoQuery;		
	}
	
	@Override
	public Integer getEquiposCargas(Long codigoEmbarque) {
		
		Integer restultadoQuery;
		
		try {
		
			final StringBuilder sql = new StringBuilder();
				
			String select = "SELECT COUNT(*) ";
			String from   = "FROM D_PLAN_EMBARQUE PE " + 
							"INNER JOIN D_EQUIPO_TRANSPORTE ET ON PE.COD_N_EMBARQUE = ET.COD_N_EMBARQUE ";
			String where  = "WHERE NOT EXISTS (SELECT 1 FROM S_EQUIPO_CARGA EC WHERE EC.COD_N_EQUIPO = ET.COD_N_EQUIPO) " + 
							"AND PE.COD_N_EMBARQUE = ?codigoEmbarque";
			
			sql.append(select).append(from).append(where);		
			final Query query = getEntityManager().createNativeQuery(sql.toString());				
			query.setParameter("codigoEmbarque", codigoEmbarque);		
			restultadoQuery = Integer.parseInt(query.getSingleResult().toString());
		
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","CambiarEstadoDAOImpl(GESADUAN)","getEquiposCargas",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}			
		
		return restultadoQuery;		
	}
	
	@Override
	public Integer getEquiposNoCargados(Long codigoEmbarque) {
		
		Integer restultadoQuery ;
		
		try {
		
			final StringBuilder sql = new StringBuilder();
				
			String select = "SELECT COUNT(*) ";
			String from   = "FROM D_PLAN_EMBARQUE PE " + 
							"INNER JOIN D_EQUIPO_TRANSPORTE ET ON PE.COD_N_EMBARQUE = ET.COD_N_EMBARQUE ";
			String where  = "WHERE ET.COD_N_ESTADO <> 2 AND PE.COD_N_EMBARQUE = ?codigoEmbarque";
			
			sql.append(select).append(from).append(where);		
			final Query query = getEntityManager().createNativeQuery(sql.toString());				
			query.setParameter("codigoEmbarque", codigoEmbarque);		
			restultadoQuery = Integer.parseInt(query.getSingleResult().toString());
		
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","CambiarEstadoDAOImpl(GESADUAN)","getEquiposNoCargados",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}			
		
		return restultadoQuery;		
	}

}
