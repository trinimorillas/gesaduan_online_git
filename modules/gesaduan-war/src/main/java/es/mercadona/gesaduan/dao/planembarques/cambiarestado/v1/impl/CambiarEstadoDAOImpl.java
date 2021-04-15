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
import es.mercadona.gesaduan.common.Constantes;

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
	
	private static final String NOMBRE_CLASE = "CambiarEstadoDAOImpl(GESADUAN)";
	
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
			this.logger.error(Constantes.FORMATO_ERROR_LOG, NOMBRE_CLASE, "cambiarEstado", ex.getClass().getSimpleName(), ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}			
		
		return resultSalida;
	}
	
	@Override
	public Integer getEstadoActual(Long codigoEmbarque) {		
		final StringBuilder sql = new StringBuilder();
		Integer restultadoQuery;
			
		try {		
			String select = "SELECT COD_N_ESTADO ";
			String from   = "FROM D_PLAN_EMBARQUE ";
			String where  = "WHERE COD_N_EMBARQUE = ?codigoEmbarque";
		
			sql.append(select).append(from).append(where);		
			final Query query = getEntityManager().createNativeQuery(sql.toString());				
			query.setParameter("codigoEmbarque", codigoEmbarque);		
			restultadoQuery = Integer.parseInt(query.getSingleResult().toString());			
		} catch (Exception ex) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, NOMBRE_CLASE, "getEstadoActual", ex.getClass().getSimpleName(), ex.getMessage());	
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
			this.logger.error(Constantes.FORMATO_ERROR_LOG, NOMBRE_CLASE, "getEquipos", ex.getClass().getSimpleName(), ex.getMessage());	
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
			this.logger.error(Constantes.FORMATO_ERROR_LOG, NOMBRE_CLASE, "getEquiposCargas", ex.getClass().getSimpleName(), ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}			
		
		return restultadoQuery;		
	}
	
	@Override
	public Integer getEquiposNoCargados(Long codigoEmbarque) {		
		Integer restultadoQuery;
		
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
			this.logger.error(Constantes.FORMATO_ERROR_LOG, NOMBRE_CLASE, "getEquiposNoCargados", ex.getClass().getSimpleName(), ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}			
		
		return restultadoQuery;		
	}
	
	@Transactional
	@Override
	public void crearContenedoresFicticios(Long codigoEmbarque, String codigoUsuario) {		
		try {		
			final StringBuilder sql = new StringBuilder();
				
			sql.append("INSERT INTO O_CONTENEDOR_EXPEDIDO (COD_V_ALMACEN, NUM_CONTENEDOR, COD_V_CARGA, FEC_DT_EXPEDICION, FEC_D_CREACION, COD_V_APLICACION, COD_V_USUARIO_CREACION) ");
			sql.append("SELECT EC.COD_V_ALMACEN_ORIGEN, CONTENEDOR_SEQ.NEXTVAL, EC.COD_V_CARGA, CA.FEC_D_ENTREGA, SYSDATE, 'GESADUAN', ?codigoUsuario ");
			sql.append("FROM D_EQUIPO_TRANSPORTE E ");
			sql.append("INNER JOIN S_EQUIPO_CARGA EC ON (EC.COD_N_EQUIPO = E.COD_N_EQUIPO) ");
			sql.append("INNER JOIN D_CARGA CA ON (CA.COD_V_CARGA = EC.COD_V_CARGA AND CA.COD_V_ALMACEN_ORIGEN = EC.COD_V_ALMACEN_ORIGEN AND CA.COD_N_TIPO_CARGA IN (3,4)) ");
			sql.append("LEFT JOIN O_CONTENEDOR_EXPEDIDO CE ON (CE.COD_V_ALMACEN = EC.COD_V_ALMACEN_ORIGEN AND CE.COD_V_CARGA = EC.COD_V_CARGA) ");
			sql.append("WHERE E.COD_N_EMBARQUE = ?codigoEmbarque ");
			sql.append("AND CE.COD_N_EQUIPO IS NULL");
			
			final Query query = getEntityManager().createNativeQuery(sql.toString());				
			query.setParameter("codigoEmbarque", codigoEmbarque);
			query.setParameter("codigoUsuario", codigoUsuario);
			query.executeUpdate();		
		} catch(Exception ex) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, NOMBRE_CLASE, "crearContenedoresFicticios", ex.getClass().getSimpleName(), ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}	
	}

}
