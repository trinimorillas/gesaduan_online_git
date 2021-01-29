package es.mercadona.gesaduan.dao.equipotransporte.putcontenedor.v1.impl;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.fwk.data.DaoBaseImpl;
import es.mercadona.gesaduan.dao.equipotransporte.putcontenedor.v1.PutContenedorDAO;
import es.mercadona.gesaduan.dto.equipotransporte.getequipotransportedetalle.v1.restfull.ContenedorDTO;
import es.mercadona.gesaduan.jpa.equipotransporte.v1.EquipoTransporteJPA;

public class PutContenedorDAOImpl extends DaoBaseImpl<Long, EquipoTransporteJPA> implements PutContenedorDAO {

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
	public void actualizarContenedor(ContenedorDTO contenedor, Long codigoEquipo, String codigoUsuario) {
		try {
			String update = "";
			if (contenedor.getMcaFacturado().equals("S")) {
				update = "UPDATE O_CONTENEDOR_EXPEDIDO CE " + 
						 "SET " + 
					     "  CE.COD_N_EQUIPO = ?codigoEquipo, " +
					     "  CE.FEC_D_MODIFICACION = SYSDATE, " +
					     "  CE.COD_V_USR_MODIFICACION = ?codigoUsuario " +
						 "WHERE " + 
					     "  CE.COD_V_ALMACEN = ?codigoCentroOrigen AND " + 
						 "  CE.NUM_CONTENEDOR = ?numContenedor AND " + 
					     "  CE.COD_V_CARGA = ?codigoCarga AND " + 
					     "	TO_CHAR(CE.FEC_DT_EXPEDICION,'YYYYMMDDHH24MI')  = ?fechaExpedicion ";  				
				
			} else {
				update = "UPDATE O_CONTENEDOR_EXPEDIDO CE "	+ 
						 "SET " + 
					     "  CE.COD_N_EQUIPO = NULL, " +
					     "  CE.FEC_D_MODIFICACION = SYSDATE, " +
					     "  CE.COD_V_USR_MODIFICACION = ?codigoUsuario " +
						 "WHERE " + 
					     "  CE.COD_V_ALMACEN = ?codigoCentroOrigen AND " + 
						 "  CE.NUM_CONTENEDOR = ?numContenedor AND " + 
					     "  CE.COD_V_CARGA = ?codigoCarga AND " + 
					     "	TO_CHAR(CE.FEC_DT_EXPEDICION,'YYYYMMDDHH24MI')  = ?fechaExpedicion ";
			}
			
			final Query query = getEntityManager().createNativeQuery(update);			
			query.setParameter("codigoEquipo", codigoEquipo);
			query.setParameter("codigoCarga", contenedor.getCodigoCarga());
			query.setParameter("codigoCentroOrigen", contenedor.getCodigoCentroOrigen());
			query.setParameter("numContenedor", contenedor.getNumContenedor());
			query.setParameter("fechaExpedicion", contenedor.getFechaExpedicion());			
			query.setParameter("codigoUsuario", codigoUsuario);
			query.executeUpdate();		
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","PutEquipoTransporteDAOImpl(GESADUAN)","actualizarContenedor",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}		
	}

}
