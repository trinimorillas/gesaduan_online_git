package es.mercadona.gesaduan.dao.proveedores.putpuertoagencia.v1.impl;

import java.util.Date;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.fwk.data.DaoBaseImpl;
import es.mercadona.gesaduan.dao.proveedores.putpuertoagencia.v1.PutPuertoAgenciaDAO;
import es.mercadona.gesaduan.dto.proveedores.putpuertoagencia.v1.PuertoDTO;
import es.mercadona.gesaduan.jpa.proveedores.putpuertoagencia.v1.PuertoAgenciaJPA;

public class PutPuertoAgenciaDAOImpl extends DaoBaseImpl<Integer, PuertoAgenciaJPA> implements PutPuertoAgenciaDAO {
	
	@Override
	protected EntityManager getEntityManager() {
		return this.entityM;
	}

	@Override
	public void setEntityClass() {
		entityClass = PuertoAgenciaJPA.class;
	}
	
	@PersistenceContext
	private EntityManager entityM;
	
	@Inject
	private org.slf4j.Logger logger;
	
	@Transactional
	@Override
	public Integer consultarAgenciaPreferente(PuertoDTO puerto) {
		try {
			final StringBuilder sql = new StringBuilder();		
			
			String select = "SELECT COUNT(*) ";
			String from   = "FROM S_PUERTO_AGENCIA ";
			String where  = "WHERE COD_N_PUERTO = ?codigoPuerto AND MCA_AGENCIA_PREFERENTE = 'S'";
					
			sql.append(select).append(from).append(where);		
			final Query query = getEntityManager().createNativeQuery(sql.toString());		
			query.setParameter("codigoPuerto", puerto.getCodigoPuerto());
			return Integer.parseInt(query.getSingleResult().toString());
		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","PutPuertoAgenciaDAOImpl(GESADUAN)","consultarAgenciaPreferente",e.getClass().getSimpleName(),e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}
	}

	@Transactional
	@Override
	public void insertarPuertoAgencia(PuertoAgenciaJPA crearPuertoAgencia) {		
		try {
			Date fechaHoy = new Date();
			crearPuertoAgencia.setFechaCreacion(fechaHoy);
			crearPuertoAgencia.setCodigoAplicacion("GESADUAN");
			entityM.persist(crearPuertoAgencia);
			entityM.flush();			
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","PutPuertoAgenciaDAOImpl(GESADUAN)","consultarAgenciaPreferente",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}
	}
	
	@Transactional
	@Override
	public void modificarAgenciaPreferente(Long codigoPuerto, String codigoAgencia) {		
		try {
			final StringBuilder sql = new StringBuilder();		
			
			String update = "UPDATE S_PUERTO_AGENCIA ";
			String campos = "SET MCA_AGENCIA_PREFERENTE = 'N' ";
			String where  = "WHERE COD_N_PUERTO = ?codigoPuerto AND MCA_AGENCIA_PREFERENTE = 'S'";
					
			sql.append(update).append(campos).append(where);		
			final Query query = getEntityManager().createNativeQuery(sql.toString());		
			query.setParameter("codigoPuerto", codigoPuerto);
			query.executeUpdate();
			
			final StringBuilder sql2 = new StringBuilder();		
			
			String update2 = "UPDATE S_PUERTO_AGENCIA ";
			String campos2 = "SET MCA_AGENCIA_PREFERENTE = 'S' ";
			String where2  = "WHERE COD_N_PUERTO = ?codigoPuerto AND COD_V_AGENCIA_ADUANA = ?codigoAgencia";
					
			sql2.append(update2).append(campos2).append(where2);		
			final Query query2 = getEntityManager().createNativeQuery(sql2.toString());		
			query2.setParameter("codigoPuerto", codigoPuerto);
			query2.setParameter("codigoAgencia", codigoAgencia);
			query2.executeUpdate();
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","PutPuertoAgenciaDAOImpl(GESADUAN)","consultarAgenciaPreferente",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}
	}
	
	@Transactional
	@Override
	public void eliminarPuertoAgencia(PuertoAgenciaJPA puertoAgencia) {		
		try {
			PuertoAgenciaJPA eliminarPuertoAgencia = entityM.find(PuertoAgenciaJPA.class, puertoAgencia.getId());
			entityM.remove(eliminarPuertoAgencia);
			entityM.flush();
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","PutPuertoAgenciaDAOImpl(GESADUAN)","consultarAgenciaPreferente",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}
	}

}
