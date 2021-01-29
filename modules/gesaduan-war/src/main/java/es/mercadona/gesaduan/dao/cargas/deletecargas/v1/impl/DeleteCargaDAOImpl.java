package es.mercadona.gesaduan.dao.cargas.deletecargas.v1.impl;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.fwk.data.DaoBaseImpl;
import es.mercadona.gesaduan.dao.cargas.deletecargas.v1.DeleteCargaDAO;
import es.mercadona.gesaduan.dto.cargas.deletecargas.v1.EliminarCargaDTO;
import es.mercadona.gesaduan.dto.cargas.deletecargas.v1.InputDatosEliminarCargaDTO;
import es.mercadona.gesaduan.jpa.cargas.v1.CargasJPA;

public class DeleteCargaDAOImpl extends DaoBaseImpl<Long, CargasJPA> implements DeleteCargaDAO {

	@Inject
	private org.slf4j.Logger logger;	
	
	@Override
	public void setEntityClass() {
		this.entityClass = CargasJPA.class;		
	}
	
	@Override
	protected EntityManager getEntityManager() {
		return this.entityM;
	}
	
	@PersistenceContext
	private EntityManager entityM;
	
	@Transactional
	public Integer validarEstadoCarga(EliminarCargaDTO carga) {
		
		try {
		
			final StringBuilder sql = new StringBuilder();
			
			String select = "SELECT COUNT(*) ";
			String from   = "FROM D_CARGA C ";
			String where  = "WHERE C.COD_V_CARGA = ?codigoCarga " + 
						"AND C.COD_V_ALMACEN_ORIGEN = ?codigoAlmacenOrigen " + 
						"AND ((C.COD_N_ESTADO <> 1 AND C.COD_N_ESTADO <> 2)  OR C.NUM_DIVISIONES > 0)";
					
			sql.append(select).append(from).append(where);		
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			query.setParameter("codigoCarga", carga.getCodigoCarga());
			query.setParameter("codigoAlmacenOrigen", carga.getCodigoAlmacenOrigen());
			Integer restultadoQuery = Integer.parseInt(query.getSingleResult().toString());
			
			return restultadoQuery;
		
		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","DeleteCargaDAOImpl(GESADUAN)","validarEstadoCarga",e.getClass().getSimpleName(),e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}		
	}
	
	@Transactional
	@Override
	public void eliminarPedidosCarga(EliminarCargaDTO carga) {
		
		try {
		
			String delete = "DELETE FROM S_CARGA_PEDIDO CP " +
					"WHERE EXISTS( " + 
					"SELECT " + 
					" C.COD_V_CARGA, " + 
					" C.COD_V_ALMACEN_ORIGEN " + 
					"FROM D_CARGA C " +
					"WHERE C.COD_V_CARGA = ?codigoCarga " +
					"AND C.COD_V_ALMACEN_ORIGEN = ?codigoAlmacenOrigen " +
					"AND C.COD_V_CARGA = CP.COD_V_CARGA " +
					"AND C.COD_V_ALMACEN_ORIGEN = CP.COD_V_ALMACEN_ORIGEN " +				
					"AND C.COD_N_ESTADO in (1,2) " +
					"AND C.NUM_DIVISIONES = 0 " +
					"AND C.COD_V_APLICACION_ORIGEN = 'GESADUAN')";
			
							
			final Query query = getEntityManager().createNativeQuery(delete);
			query.setParameter("codigoCarga", carga.getCodigoCarga());
			query.setParameter("codigoAlmacenOrigen", carga.getCodigoAlmacenOrigen());
			query.executeUpdate();	
		
		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","DeleteCargaDAOImpl(GESADUAN)","eliminarPedidosCarga",e.getClass().getSimpleName(),e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}		
	}	
	
	@Transactional
	@Override
	public void eliminarCarga(EliminarCargaDTO carga) {
		
		try {
		
			String delete = "DELETE FROM D_CARGA C " +
					"WHERE C.COD_V_CARGA = ?codigoCarga " +
					"AND C.COD_V_ALMACEN_ORIGEN = ?codigoAlmacenOrigen " +
					"AND C.COD_N_ESTADO in (1,2) " +
					"AND C.NUM_DIVISIONES = 0 " +
					"AND C.COD_V_APLICACION_ORIGEN = 'GESADUAN'";
					
			final Query query = getEntityManager().createNativeQuery(delete);
			query.setParameter("codigoCarga", carga.getCodigoCarga());
			query.setParameter("codigoAlmacenOrigen", carga.getCodigoAlmacenOrigen());
			query.executeUpdate();
		
		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","DeleteCargaDAOImpl(GESADUAN)","eliminarCarga",e.getClass().getSimpleName(),e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}		
		
	}
	
	@Transactional
	@Override
	public void cambiarEstado(InputDatosEliminarCargaDTO datos, EliminarCargaDTO carga) {
		
		try {
		
			String delete = "UPDATE D_CARGA C " +
					"SET C.COD_N_ESTADO = 5, " +
					"C.MCA_PEDIDOS_SIN_VALIDAR = 'S', " +				
					"C.FEC_DT_MODIFICACION = SYSDATE, " +
					"C.COD_V_USUARIO_MODIFICACION = ?codigoUsuario " +
					"WHERE C.COD_V_CARGA = ?codigoCarga " +
					"AND C.COD_V_ALMACEN_ORIGEN = ?codigoAlmacenOrigen " +
					"AND C.COD_N_ESTADO in (1,2) " +
					"AND C.NUM_DIVISIONES = 0 " +
					"AND C.COD_V_APLICACION_ORIGEN <> 'GESADUAN'";
					
			final Query query = getEntityManager().createNativeQuery(delete);
			query.setParameter("codigoCarga", carga.getCodigoCarga());
			query.setParameter("codigoAlmacenOrigen", carga.getCodigoAlmacenOrigen());
			query.setParameter("codigoUsuario", datos.getMetadatos().getCodigoUsuario());
			query.executeUpdate();
		
		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","DeleteCargaDAOImpl(GESADUAN)","cambiarEstado",e.getClass().getSimpleName(),e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}		
		
	}

}