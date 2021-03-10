package es.mercadona.gesaduan.dao.dosier.putdosier.v1.impl;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.fwk.data.DaoBaseImpl;
import es.mercadona.gesaduan.dao.dosier.putdosier.v1.PutDosierDAO;
import es.mercadona.gesaduan.jpa.dosier.DosierContenedorJPA;
import es.mercadona.gesaduan.jpa.dosier.DosierEquipoJPA;
import es.mercadona.gesaduan.jpa.dosier.DosierJPA;
import es.mercadona.gesaduan.jpa.dosier.DosierPkJPA;

public class PutDosierDAOImpl extends DaoBaseImpl<Long, DosierJPA> implements PutDosierDAO {

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
		this.entityClass = DosierJPA.class;		
	}
	
	@Transactional
	@Override
	public void crearDosier(DosierJPA dosierJPA) {
		
		
		try {
		
			String gesaduan = "GESADUAN";
			Date fechaHoy = new Date();
			dosierJPA.setCodigoEstado(1);
			dosierJPA.setFechaCreacion(fechaHoy);
			dosierJPA.setCodigoAplicacion(gesaduan);
			entityM.persist(dosierJPA);
			entityM.flush();
						
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","PutDosierDAOImpl(GESADUAN)","crearDosier",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}			

	}
	
	@Transactional
	@Override
	public void crearRelacionDosierEquipo(DosierEquipoJPA dosierEquipoJPA) {
		
		try {

			String gesaduan = "GESADUAN";
			Date fechaHoy = new Date();
			dosierEquipoJPA.setFechaCreacion(fechaHoy);
			dosierEquipoJPA.setCodigoAplicacion(gesaduan);
			entityM.persist(dosierEquipoJPA);
			entityM.flush();			
			
			
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","PutDosierDAOImpl(GESADUAN)","crearRelacionDosierEquipo",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}			

	}	
	
	@Transactional
	@Override
	public void actualizaEstadoDocumentacionEquipo(DosierEquipoJPA dosierEquipoJPA) {
		
		try {

			StringBuilder update = new StringBuilder();
			
			update.append("UPDATE D_EQUIPO_TRANSPORTE ET ");
			update.append("SET ET.COD_N_ESTADO_DOCUMENTACION = 3, ");
			update.append("ET.FEC_DT_MODIFICACION = SYSDATE, ");
			update.append("ET.COD_V_USUARIO_MODIFICACION = ?codigoUsuario ");
			update.append("WHERE ET.COD_N_EQUIPO = ?codigoEquipo ");
			update.append("AND NOT EXISTS (SELECT 1 FROM O_CONTENEDOR_EXPEDIDO WHERE COD_N_EQUIPO = ET.COD_N_EQUIPO AND NUM_DOSIER IS NULL)");
			
			final Query query = getEntityManager().createNativeQuery(update.toString());
			query.setParameter("codigoUsuario", dosierEquipoJPA.getUsuarioCreacion());
			query.setParameter("codigoEquipo", dosierEquipoJPA.getCodigoEquipo());
			query.executeUpdate();		
			
			StringBuilder update2 = new StringBuilder();
			
			update2.append("UPDATE D_EQUIPO_TRANSPORTE ET ");
			update2.append("SET ET.COD_N_ESTADO_DOCUMENTACION = 2, ");
			update2.append("ET.FEC_DT_MODIFICACION = SYSDATE, ");
			update2.append("ET.COD_V_USUARIO_MODIFICACION = ?codigoUsuario ");
			update2.append("WHERE ET.COD_N_EQUIPO = ?codigoEquipo ");
			update2.append("AND EXISTS (SELECT 1 FROM O_CONTENEDOR_EXPEDIDO WHERE COD_N_EQUIPO = ET.COD_N_EQUIPO AND NUM_DOSIER IS NULL)");
			
			final Query query2 = getEntityManager().createNativeQuery(update2.toString());
			query2.setParameter("codigoUsuario", dosierEquipoJPA.getUsuarioCreacion());
			query2.setParameter("codigoEquipo", dosierEquipoJPA.getCodigoEquipo());
			query2.executeUpdate();					
			
			
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","PutDosierDAOImpl(GESADUAN)","actualizaEstadoDocumentacionEquipo",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}			

	}	
	
	@Transactional
	@Override
	public void crearRelacionDosierContenedor(DosierContenedorJPA dosierContenedorJPA) {
		
		try {
			
			StringBuilder update = new StringBuilder();
		
			update.append("UPDATE O_CONTENEDOR_EXPEDIDO CE ");
			update.append("SET CE.NUM_DOSIER = ?numDosier, ");
			update.append("CE.NUM_ANYO = ?anyoDosier, ");				
			update.append("CE.FEC_D_MODIFICACION = SYSDATE, ");
			update.append("CE.COD_V_USR_MODIFICACION = ?codigoUsuario ");
			update.append("WHERE CE.COD_V_ALMACEN = ?codigoAlmacen ");
			update.append("AND CE.NUM_CONTENEDOR = ?numContenedor ");
			update.append("AND CE.FEC_DT_EXPEDICION = TO_DATE(?fechaExpedicion,'DD/MM/YYYY HH24:MI:SS') ");
			update.append("AND CE.COD_N_EQUIPO = ?codigoEquipo ");			
					
			final Query query = getEntityManager().createNativeQuery(update.toString());
			query.setParameter("numDosier", dosierContenedorJPA.getNumDosier());
			query.setParameter("anyoDosier", dosierContenedorJPA.getAnyoDosier());
			query.setParameter("codigoUsuario", dosierContenedorJPA.getUsuarioCreacion());
			query.setParameter("codigoAlmacen", dosierContenedorJPA.getCodigoAlmacen());			
			query.setParameter("numContenedor", dosierContenedorJPA.getNumContenedor());
			query.setParameter("fechaExpedicion", dosierContenedorJPA.getFechaExpedicion());
			query.setParameter("codigoEquipo", dosierContenedorJPA.getCodigoEquipo());			
			query.executeUpdate();		
			
			
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","PutDosierDAOImpl(GESADUAN)","crearRelacionDosierContenedor",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}			

	}	
	
	@Transactional
	@Override
	public void crearRelacionDosierContenedorDeEquipo(DosierContenedorJPA dosierContenedorJPA) {
		
		try {
			
			StringBuilder update = new StringBuilder();
		
			update.append("UPDATE O_CONTENEDOR_EXPEDIDO CE ");
			update.append("SET CE.NUM_DOSIER = ?numDosier, ");
			update.append("CE.NUM_ANYO = ?anyoDosier, ");				
			update.append("CE.FEC_D_MODIFICACION = SYSDATE, ");
			update.append("CE.COD_V_USR_MODIFICACION = ?codigoUsuario ");
			update.append("WHERE CE.COD_N_EQUIPO = ?codigoEquipo ");
					
			final Query query = getEntityManager().createNativeQuery(update.toString());
			query.setParameter("numDosier", dosierContenedorJPA.getNumDosier());
			query.setParameter("anyoDosier", dosierContenedorJPA.getAnyoDosier());
			query.setParameter("codigoUsuario", dosierContenedorJPA.getUsuarioCreacion());
			query.setParameter("codigoEquipo", dosierContenedorJPA.getCodigoEquipo());			
			query.executeUpdate();		
			
			
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","PutDosierDAOImpl(GESADUAN)","crearRelacionDosierContenedorDeEquipo",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}			

	}		
	
	@Transactional	
	@Override
	public DosierPkJPA getNewDosierPk() {
		
		try {
			
			DosierPkJPA dosierPk = null;			
			
			String select = "SELECT TO_NUMBER(TXT_VALOR)+1, TO_CHAR(SYSDATE,'YYYY') FROM C_VARIABLE WHERE COD_V_VARIABLE = 'NUM_DOSIER'";

			final Query query = getEntityManager().createNativeQuery(select);
			@SuppressWarnings("unchecked")
			List<Object[]> listado = query.getResultList();
			
			if (listado != null && !listado.isEmpty()) {
				for (Object[] tmp : listado) {			
									
					dosierPk = new DosierPkJPA();
					dosierPk.setNumDosier(Long.parseLong(String.valueOf(tmp[0])));
					dosierPk.setAnyoDosier(Integer.parseInt(String.valueOf(tmp[1])));
					
					break;
				}			
			}
			
			return dosierPk;	
			
		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","PostExpedicionesDAOImpl(GESADUAN)","getNewDosierPk",e.getClass().getSimpleName(),e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}
	}	
	
	@Transactional	
	@Override
	public void updateNumDosier(Long lastNumDosier) {
		
		try {
			
			StringBuilder update = new StringBuilder();
		
			update.append("UPDATE C_VARIABLE SET TXT_VALOR = ?lastNumDosier WHERE COD_V_VARIABLE = 'NUM_DOSIER' ");
					
			final Query query = getEntityManager().createNativeQuery(update.toString());
			query.setParameter("lastNumDosier", lastNumDosier);
			query.executeUpdate();		
			
			
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","PutDosierDAOImpl(GESADUAN)","updateNumDosier",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}	
	}		

}
