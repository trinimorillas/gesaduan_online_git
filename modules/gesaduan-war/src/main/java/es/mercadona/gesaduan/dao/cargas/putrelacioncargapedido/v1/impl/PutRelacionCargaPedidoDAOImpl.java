package es.mercadona.gesaduan.dao.cargas.putrelacioncargapedido.v1.impl;

import java.util.Date;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.fwk.data.DaoBaseImpl;
import es.mercadona.gesaduan.dao.cargas.putrelacioncargapedido.v1.PutRelacionCargaPedidoDAO;
import es.mercadona.gesaduan.dto.cargas.putrelacioncargapedido.v1.InputDatosCrearRelacionCargaPedidoDTO;
import es.mercadona.gesaduan.dto.cargas.putrelacioncargapedido.v1.PedidoDTO;
import es.mercadona.gesaduan.jpa.cargas.v1.CargaPedidoJPA;

public class PutRelacionCargaPedidoDAOImpl extends DaoBaseImpl<Long, CargaPedidoJPA> implements PutRelacionCargaPedidoDAO {

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
	
	@Transactional
	@Override
	public void crearRelacionCargaPedido(CargaPedidoJPA crearRelacionCargaPedido) {
		
		try {
		
			Date fechaHoy = new Date();	
			crearRelacionCargaPedido.setFechaCreacion(fechaHoy);
			crearRelacionCargaPedido.setCodigoAplicacion("GESADUAN");
			crearRelacionCargaPedido.setMcaPedidoValidado("S");
			entityM.persist(crearRelacionCargaPedido);
			entityM.flush();
		
		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","PutRelacionCargaPedidoDAOImpl(GESADUAN)","crearRelacionCargaPedido",e.getClass().getSimpleName(),e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}
		
	}
	
	@Override
	public Integer validarEstadoCarga(InputDatosCrearRelacionCargaPedidoDTO datos) {
		
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
			this.logger.error("({}-{}) ERROR - {} {}","PutRelacionCargaPedidoDAOImpl(GESADUAN)","validarEstadoCarga",e.getClass().getSimpleName(),e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}
		
	}
	
	@Override
	public Integer validarExistePedido(InputDatosCrearRelacionCargaPedidoDTO datos,PedidoDTO pedido) {
		
		try {
		
			final StringBuilder sql = new StringBuilder();
			
			String select = "SELECT COUNT(*) ";
			String from   = "FROM S_CARGA_PEDIDO CP ";
			String where  = "WHERE CP.COD_V_CARGA = ?codigoCarga " + 
						"AND CP.COD_V_ALMACEN_ORIGEN = ?codigoAlmacenOrigen " + 
						"AND CP.COD_V_PEDIDO = ?codigoPedido " + 
						"AND CP.COD_V_DIVISION_PEDIDO  = ?codigoDivision";		
					
			sql.append(select).append(from).append(where);		
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			query.setParameter("codigoCarga", datos.getDatos().getCodigoCarga());
			query.setParameter("codigoAlmacenOrigen", datos.getDatos().getCodigoAlmacenOrigen());
			query.setParameter("codigoPedido", pedido.getCodigoPedido());		
			query.setParameter("codigoDivision", pedido.getCodigoDivision());		
			Integer restultadoQuery = Integer.parseInt(query.getSingleResult().toString());
			
			return restultadoQuery;	
		
		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","PutRelacionCargaPedidoDAOImpl(GESADUAN)","validarExistePedido",e.getClass().getSimpleName(),e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}		
	}	

}