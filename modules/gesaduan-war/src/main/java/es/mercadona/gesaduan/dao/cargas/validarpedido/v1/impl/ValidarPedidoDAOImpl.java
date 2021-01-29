package es.mercadona.gesaduan.dao.cargas.validarpedido.v1.impl;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.gesaduan.dao.BaseDAO;
import es.mercadona.gesaduan.dao.cargas.validarpedido.v1.ValidarPedidoDAO;
import es.mercadona.gesaduan.dto.cargas.validapedido.v1.InputDatosValidarPedidoDTO;
import es.mercadona.gesaduan.dto.cargas.validapedido.v1.ValidarPedidoDTO;
import es.mercadona.gesaduan.jpa.cargas.v1.CargasJPA;

public class ValidarPedidoDAOImpl  extends BaseDAO<CargasJPA> implements ValidarPedidoDAO{

	@Inject
	private org.slf4j.Logger logger;	
	
	@Override
	public void setEntityClass() {
		this.entityClass = CargasJPA.class;		
	}
	
	@PersistenceContext
	private EntityManager entityM;
	
	@Transactional	
	@Override
	public void marcarPedidosValidados(InputDatosValidarPedidoDTO datos,ValidarPedidoDTO carga) {
		
		try {
		
			String update = "UPDATE S_CARGA_PEDIDO CP  " +
					"SET CP.MCA_PEDIDO_VALIDADO = 'S', " +
					"CP.FEC_DT_MODIFICACION = SYSDATE, " +
					"CP.COD_V_USUARIO_MODIFICACION = ?codigoUsuario " +
					"WHERE CP.COD_V_CARGA = ?codigoCarga " +
					"AND CP.COD_V_ALMACEN_ORIGEN = ?codigoAlmacenOrigen " +
					"AND CP.MCA_PEDIDO_VALIDADO  = 'N'";		
					
			final Query query = getEntityManager().createNativeQuery(update);		
			query.setParameter("codigoCarga", carga.getCodigoCarga());
			query.setParameter("codigoAlmacenOrigen", carga.getCodigoAlmacenOrigen());
			query.setParameter("codigoUsuario", datos.getMetadatos().getCodigoUsuario());
			query.executeUpdate();		
		
		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","ValidarPedidoDAOImpl(GESADUAN)","marcarPedidosValidados",e.getClass().getSimpleName(),e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}
		
	}
	
	@Transactional	
	@Override
	public void borrarPedidosMarcadosBorrar(ValidarPedidoDTO carga) {
		
		try {
		
			String delete = "DELETE S_CARGA_PEDIDO CP  " +
					"WHERE CP.COD_V_CARGA = ?codigoCarga " +
					"AND CP.COD_V_ALMACEN_ORIGEN = ?codigoAlmacenOrigen " +
					"AND CP.MCA_PEDIDO_VALIDADO  = 'E'";		
					
			final Query query = getEntityManager().createNativeQuery(delete);		
			query.setParameter("codigoCarga", carga.getCodigoCarga());
			query.setParameter("codigoAlmacenOrigen", carga.getCodigoAlmacenOrigen());
			query.executeUpdate();		
		
		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","ValidarPedidoDAOImpl(GESADUAN)","borrarPedidosMarcadosBorrar",e.getClass().getSimpleName(),e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}		
	}
	
	@Transactional	
	@Override
	public boolean existenPedidosCarga(ValidarPedidoDTO carga) {
		
		try {
		
			final StringBuilder sql = new StringBuilder();		
			
			String select = "SELECT COUNT(*) ";
			String from   = "FROM S_CARGA_PEDIDO ";
			String where  = "WHERE COD_V_CARGA = ?codigoCarga " +
							"AND COD_V_ALMACEN_ORIGEN = ?codigoAlmacenOrigen ";
					
			sql.append(select).append(from).append(where);		
			final Query query = getEntityManager().createNativeQuery(sql.toString());		
			query.setParameter("codigoCarga", carga.getCodigoCarga());
			query.setParameter("codigoAlmacenOrigen", carga.getCodigoAlmacenOrigen());
			Integer restultadoQuery = Integer.parseInt(query.getSingleResult().toString());
			
			if (restultadoQuery > 0) {		
				return true;
			} else {
				return false;
			}
		
		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","ValidarPedidoDAOImpl(GESADUAN)","existenPedidosCarga",e.getClass().getSimpleName(),e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}
		
	}
	
	@Transactional	
	@Override
	public void actualizarPedidosValidadosCarga(InputDatosValidarPedidoDTO datos,ValidarPedidoDTO carga) {
		
		try {
		
			String update = "UPDATE D_CARGA CA  " +
					"SET CA.MCA_PEDIDOS_SIN_VALIDAR  = 'N', " +
					"CA.FEC_DT_MODIFICACION = SYSDATE, " +
					"CA.COD_V_USUARIO_MODIFICACION = ?codigoUsuario " +
					"WHERE CA.COD_V_CARGA = ?codigoCarga " +
					"AND CA.COD_V_ALMACEN_ORIGEN = ?codigoAlmacenOrigen ";		
					
			final Query query = getEntityManager().createNativeQuery(update);		
			query.setParameter("codigoCarga", carga.getCodigoCarga());
			query.setParameter("codigoAlmacenOrigen", carga.getCodigoAlmacenOrigen());
			query.setParameter("codigoUsuario", datos.getMetadatos().getCodigoUsuario());
			query.executeUpdate();		
		
		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","ValidarPedidoDAOImpl(GESADUAN)","actualizarPedidosValidadosCarga",e.getClass().getSimpleName(),e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}		
		
	}
	
}
