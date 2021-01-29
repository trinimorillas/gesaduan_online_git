package es.mercadona.gesaduan.dao.cargas.deleterelacioncargapedido.v1.impl;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.fwk.data.DaoBaseImpl;
import es.mercadona.gesaduan.dao.cargas.deleterelacioncargapedido.v1.DeleteRelacionCargaPedidoDAO;
import es.mercadona.gesaduan.dto.cargas.deleterelacioncargapedido.v1.InputDatosEliminarRelacionCargaPedidoDTO;
import es.mercadona.gesaduan.jpa.cargas.v1.CargaPedidoJPA;
import es.mercadona.gesaduan.jpa.equipotransporte.v1.EquipoCargaJPA;

public class DeleteRelacionCargaPedidoDAOImpl extends DaoBaseImpl<Long, CargaPedidoJPA> implements DeleteRelacionCargaPedidoDAO {
	
	@Inject
	private org.slf4j.Logger logger;	
	

	@Override
	public void setEntityClass() {
		this.entityClass = CargaPedidoJPA.class;		
	}
	
	@Override
	protected EntityManager getEntityManager() {
		return this.entityM;
	}
	
	@PersistenceContext
	private EntityManager entityM;
	
	@Override
	public Integer validarEstadoCarga(InputDatosEliminarRelacionCargaPedidoDTO datos) {
		
		try {
		
			final StringBuilder sql = new StringBuilder();		
			
			String select = "SELECT COUNT(*) ";
			String from   = "FROM D_CARGA C ";
			String where  = "WHERE C.COD_V_CARGA = ?codigoCarga " +
							"AND C.COD_V_ALMACEN_ORIGEN = ?codigoAlmacenOrigen " +
							"AND C.COD_N_ESTADO NOT IN (1, 2)";
					
			sql.append(select).append(from).append(where);		
			final Query query = getEntityManager().createNativeQuery(sql.toString());		
			query.setParameter("codigoCarga", datos.getDatos().getCodigoCarga());
			query.setParameter("codigoAlmacenOrigen", datos.getDatos().getCodigoAlmacenOrigen());
			Integer restultadoQuery = Integer.parseInt(query.getSingleResult().toString());
			
			return restultadoQuery;
		
		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","DeleteRelacionCargaPedidoDAOImpl(GESADUAN)","validarEstadoCarga",e.getClass().getSimpleName(),e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}		
		
	}
	
	@Transactional
	public void eliminarRelacionCargaPedido(CargaPedidoJPA cargaPedido) {
		
		try {
		
			CargaPedidoJPA eliminarCargaPedido = entityM.find(CargaPedidoJPA.class, cargaPedido.getId());
			entityM.remove(eliminarCargaPedido);
			entityM.flush();
		
		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","DeleteRelacionCargaPedidoDAOImpl(GESADUAN)","eliminarRelacionCargaPedido",e.getClass().getSimpleName(),e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}
		
	}
	
	@Transactional
	public void modificarPedidosSinValidar(InputDatosEliminarRelacionCargaPedidoDTO datos) {
		
		try {
		
			String update = "UPDATE D_CARGA C " +
					"SET C.MCA_PEDIDOS_SIN_VALIDAR = 'N' " +
					"WHERE C.COD_V_CARGA = ?codigoCarga " +
					"AND C.COD_V_ALMACEN_ORIGEN = ?codigoAlmacenOrigen " +
					"AND NOT EXISTS (" +
					"SELECT 1 " +
					"FROM S_CARGA_PEDIDO CP " +
					"WHERE CP.COD_V_CARGA = C.COD_V_CARGA " +
					"AND CP.COD_V_ALMACEN_ORIGEN = C.COD_V_ALMACEN_ORIGEN " +
					"AND CP.MCA_PEDIDO_VALIDADO = 'N')";
				
			final Query query = getEntityManager().createNativeQuery(update);		
			query.setParameter("codigoCarga", datos.getDatos().getCodigoCarga());
			query.setParameter("codigoAlmacenOrigen", datos.getDatos().getCodigoAlmacenOrigen());
			query.executeUpdate();
		
		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","DeleteRelacionCargaPedidoDAOImpl(GESADUAN)","modificarPedidosSinValidar",e.getClass().getSimpleName(),e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}		
		
	}

}