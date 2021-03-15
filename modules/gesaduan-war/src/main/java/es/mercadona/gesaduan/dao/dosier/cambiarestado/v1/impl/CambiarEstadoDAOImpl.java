package es.mercadona.gesaduan.dao.dosier.cambiarestado.v1.impl;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.fwk.data.DaoBaseImpl;
import es.mercadona.gesaduan.dao.dosier.cambiarestado.v1.CambiarEstadoDAO;
import es.mercadona.gesaduan.dto.dosier.cambiarestado.v1.resfull.DatosCambiarEstadoDTO;
import es.mercadona.gesaduan.dto.dosier.cambiarestado.v1.resfull.OutputCambiarEstadoDTO;
import es.mercadona.gesaduan.jpa.dosier.DosierJPA;

public class CambiarEstadoDAOImpl extends DaoBaseImpl<Long, DosierJPA> implements CambiarEstadoDAO {

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
	public void actualizaContenedores(DosierJPA dosierJPA) {
		
		StringBuilder update = new StringBuilder();
		try {
		
			// Si la carga pasa de no validada a no asignada se inicializan los campos de restantes
			update.append("UPDATE O_CONTENEDOR_EXPEDIDO SET "); 
			update.append("NUM_DOSIER = NULL, ");
			update.append("NUM_ANYO = NULL, ");				
			update.append("FEC_D_MODIFICACION = SYSDATE, ");
			update.append("COD_V_USUARIO_CREACION = ?codigoUsuario ");
			update.append("WHERE "); 
			update.append("NUM_DOSIER = ?numDosier AND "); 
			update.append("NUM_ANYO = ?anyoDosier");
				
			final Query query = getEntityManager().createNativeQuery(update.toString());
			
			query.setParameter("numDosier", dosierJPA.getId().getNumDosier());	
			query.setParameter("anyoDosier", dosierJPA.getId().getAnyoDosier());
			query.setParameter("codigoUsuario", dosierJPA.getUsuarioModificacion());
			
			query.executeUpdate();
		
		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","CambiarEstadoDAOImpl(GESADUAN)","actualizaContenedores",e.getClass().getSimpleName(),e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}		
		
	}
	
	
	@Transactional
	@Override	
	public void actualizaEquipos(DosierJPA dosierJPA) {
		
		StringBuilder update = new StringBuilder();
		StringBuilder update2 = new StringBuilder();		
		try {
		
			// Si se invalida el único dosier del equipo, el estado de documentación del equipo pasará a "Pendiente"
			update.append("UPDATE D_EQUIPO_TRANSPORTE ET SET "); 
			update.append("COD_N_ESTADO_DOCUMENTACION = 1, ");				
			update.append("FEC_DT_MODIFICACION = SYSDATE, ");
			update.append("COD_V_USUARIO_CREACION = ?codigoUsuario ");
			update.append("WHERE EXISTS ("); 
			update.append(" SELECT 1 "); 
			update.append(" FROM S_DOSIER_EQUIPO ");
			update.append(" WHERE COD_N_EQUIPO = ET.COD_N_EQUIPO AND ");
			update.append(" NUM_DOSIER = ?numDosier AND ");				
			update.append(" NUM_ANYO = ?anyoDosier");
			update.append(")");
			update.append("AND NOT EXISTS ("); 
			update.append(" SELECT 1 "); 
			update.append(" FROM O_CONTENEDOR_EXPEDIDO ");
			update.append(" WHERE COD_N_EQUIPO = ET.COD_N_EQUIPO AND ");
			update.append(" NUM_DOSIER IS NOT NULL ");				
			update.append(")");						
					
			final Query query = getEntityManager().createNativeQuery(update.toString());
			
			query.setParameter("numDosier", dosierJPA.getId().getNumDosier());	
			query.setParameter("anyoDosier", dosierJPA.getId().getAnyoDosier());
			query.setParameter("codigoUsuario", dosierJPA.getUsuarioModificacion());
				
			query.executeUpdate();
			
			// Si se invalida el único dosier del equipo, el estado de documentación del equipo pasará a "Pendiente"
			update2.append("UPDATE D_EQUIPO_TRANSPORTE ET SET "); 
			update2.append("COD_N_ESTADO_DOCUMENTACION = 2, ");				
			update2.append("FEC_DT_MODIFICACION = SYSDATE, ");
			update2.append("COD_V_USUARIO_CREACION = ?codigoUsuario ");
			update2.append("WHERE EXISTS ("); 
			update2.append(" SELECT 1 "); 
			update2.append(" FROM S_DOSIER_EQUIPO ");
			update2.append(" WHERE COD_N_EQUIPO = ET.COD_N_EQUIPO AND ");
			update2.append(" NUM_DOSIER = ?numDosier AND ");				
			update2.append(" NUM_ANYO = ?anyoDosier");
			update2.append(")");
			update2.append("AND EXISTS ("); 
			update2.append(" SELECT 1 "); 
			update2.append(" FROM S_DOSIER_EQUIPO ");
			update2.append(" WHERE COD_N_EQUIPO = ET.COD_N_EQUIPO AND ");
			update2.append(" (NUM_DOSIER <> ?numDosier OR NUM_ANYO <> ?anyoDosier) ");				
			update2.append(")");						
					
			final Query query2 = getEntityManager().createNativeQuery(update2.toString());
			
			query2.setParameter("numDosier", dosierJPA.getId().getNumDosier());	
			query2.setParameter("anyoDosier", dosierJPA.getId().getAnyoDosier());
			query2.setParameter("codigoUsuario", dosierJPA.getUsuarioModificacion());
			/*query2.setParameter("numDosier2", dosierJPA.getNumDosier());	
			query2.setParameter("anyoDosier2", dosierJPA.getAnyoDosier());*/			
				
			query2.executeUpdate();			
		
		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","CambiarEstadoDAOImpl(GESADUAN)","actualizaEquipos",e.getClass().getSimpleName(),e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}			
	}
	
	
	@Transactional
	@Override
	public void eliminaRelacionEquipo(DosierJPA dosierJPA) {
		
		StringBuilder delete = new StringBuilder();
		try {
		
			// Si la carga pasa de no validada a no asignada se inicializan los campos de restantes
			delete.append("DELETE S_DOSIER_EQUIPO DE "); 
			delete.append("WHERE "); 
			delete.append("DE.NUM_DOSIER = ?numDosier AND "); 
			delete.append("DE.NUM_ANYO = ?anyoDosier");
				
			final Query query = getEntityManager().createNativeQuery(delete.toString());
			
			query.setParameter("numDosier", dosierJPA.getId().getNumDosier());	
			query.setParameter("anyoDosier", dosierJPA.getId().getAnyoDosier());
			
			query.executeUpdate();
		
		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","CambiarEstadoDAOImpl(GESADUAN)","eliminaRelacionEquipo",e.getClass().getSimpleName(),e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}			
		
	}
	
	@Transactional
	@Override
	public OutputCambiarEstadoDTO cambiarEstado(DosierJPA dosierJPA) {

		StringBuilder update = new StringBuilder();
		OutputCambiarEstadoDTO retorno = new OutputCambiarEstadoDTO();
		try {
		
			// Si la carga pasa de no validada a no asignada se inicializan los campos de restantes
			update.append("UPDATE D_DOSIER SET "); 
			update.append("COD_N_ESTADO  = 3, ");				
			update.append("FEC_DT_MODIFICACION = SYSDATE, ");
			update.append("COD_V_USUARIO_CREACION = ?codigoUsuario ");
			update.append("WHERE "); 
			update.append("NUM_DOSIER = ?numDosier AND "); 
			update.append("NUM_ANYO = ?anyoDosier");
				
			final Query query = getEntityManager().createNativeQuery(update.toString());
			
			query.setParameter("numDosier", dosierJPA.getId().getNumDosier());	
			query.setParameter("anyoDosier", dosierJPA.getId().getAnyoDosier());
			query.setParameter("codigoUsuario", dosierJPA.getUsuarioModificacion());
			
			query.executeUpdate();
			
			DatosCambiarEstadoDTO datos = new DatosCambiarEstadoDTO();
			
			datos.setNumDosier(dosierJPA.getId().getNumDosier());
			datos.setAnyoDosier(dosierJPA.getId().getAnyoDosier());			
			
			retorno.setDatos(datos);
			
			return retorno;
		
		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","CambiarEstadoDAOImpl(GESADUAN)","actualizaContenedores",e.getClass().getSimpleName(),e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}		
		
		
	}
	


}
