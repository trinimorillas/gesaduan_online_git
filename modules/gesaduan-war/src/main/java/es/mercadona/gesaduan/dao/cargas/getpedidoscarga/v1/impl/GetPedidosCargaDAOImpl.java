package es.mercadona.gesaduan.dao.cargas.getpedidoscarga.v1.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.Query;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.gesaduan.dao.BaseDAO;
import es.mercadona.gesaduan.dao.cargas.getpedidoscarga.v1.GetPedidosCargaDAO;
import es.mercadona.gesaduan.dto.cargas.getpedidoscarga.v1.InputDatosPedidosCargaDTO;
import es.mercadona.gesaduan.dto.cargas.getpedidoscarga.v1.restfull.DatosPedidosCargaDTO;
import es.mercadona.gesaduan.dto.cargas.getpedidoscarga.v1.restfull.OutputPedidosCargaDTO;
import es.mercadona.gesaduan.jpa.cargas.v1.CargasJPA;

public class GetPedidosCargaDAOImpl extends BaseDAO<CargasJPA> implements GetPedidosCargaDAO {

	@Inject
	private org.slf4j.Logger logger;	
	
	@Override
	public void setEntityClass() {
		this.entityClass = CargasJPA.class;		
	}
	
	@Override
	public OutputPedidosCargaDTO listarPedidosCarga(InputDatosPedidosCargaDTO datos) {
		
		List<DatosPedidosCargaDTO> listaPedidosCarga = null;
		DatosPedidosCargaDTO pedidosCarga = null;
		OutputPedidosCargaDTO result = new OutputPedidosCargaDTO();
		
		try {		

			String codigoCarga = null;
			if (datos.getDatos().getCodigoCarga() != null)
				codigoCarga = datos.getDatos().getCodigoCarga();
			
			String codigoCentroOrigen = null;
			if (datos.getDatos().getCodigoCentroOrigen() != null)
				codigoCentroOrigen = datos.getDatos().getCodigoCentroOrigen();
			
			String mcaPedidoValidado = null;
			if (datos.getDatos().getMcaPedidoValidado() != null)
				mcaPedidoValidado = datos.getDatos().getMcaPedidoValidado();
			
			String orden = datos.getDatos().getOrden();
			
			final StringBuilder sql = new StringBuilder();
			final StringBuilder sqlCount = new StringBuilder();
			
			String count = "SELECT COUNT(*) FROM (";
			String select = "SELECT ";
			String campos = "CP.COD_V_PEDIDO, " +
					"CP.MCA_PEDIDO_VALIDADO, " + 
					"CP.COD_V_DIVISION_PEDIDO, " + 
					"TO_DATE(CP.FEC_DT_CREACION, 'DD/MM/YYYY') ";
			String from = "FROM S_CARGA_PEDIDO CP ";
			String where = "WHERE CP.COD_V_CARGA = ?codigoCarga AND CP.COD_V_ALMACEN_ORIGEN = ?codigoCentroOrigen ";
			
			if (mcaPedidoValidado != null) where += "AND CP.MCA_PEDIDO_VALIDADO = ?mcaPedidoValidado ";
			String order = "";
				
			String countFin = ")";
			
			if (orden.equals("-codigoPedido"))
				order += "ORDER BY CP.COD_V_PEDIDO DESC";
			else if (orden.equals("+codigoPedido"))
				order += "ORDER BY CP.COD_V_PEDIDO ASC";
			else if (orden.equals("-mcaPedidoValidado "))
				order += "ORDER BY CP.MCA_PEDIDO_VALIDADO DESC";
			else if (orden.equals("+mcaPedidoValidado "))
				order += "ORDER BY CP.MCA_PEDIDO_VALIDADO ASC";
			else if (orden.equals("-fechaCreacion"))
				order += "ORDER BY CP.FEC_DT_CREACION DESC";
			else if (orden.equals("+fechaCreacion"))
				order += "ORDER BY CP.FEC_DT_CREACION ASC";
			
			sql.append(select).append(campos).append(from).append(where).append(order);
			sqlCount.append(count).append(select).append(campos).append(from).append(where).append(countFin);
	
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			final Query queryCount = getEntityManager().createNativeQuery(sqlCount.toString());
			
			if (codigoCarga != null) {
				query.setParameter("codigoCarga", codigoCarga);
				queryCount.setParameter("codigoCarga", codigoCarga);
			}
			
			if (codigoCentroOrigen != null) {
				query.setParameter("codigoCentroOrigen", codigoCentroOrigen);
				queryCount.setParameter("codigoCentroOrigen", codigoCentroOrigen);
			}
			
			if (mcaPedidoValidado != null) {
				query.setParameter("mcaPedidoValidado", mcaPedidoValidado);
				queryCount.setParameter("mcaPedidoValidado", mcaPedidoValidado);
			}
			
			@SuppressWarnings("unchecked")
			List<Object[]> listado = query.getResultList();
	
			if (listado != null && !listado.isEmpty()) {
				listaPedidosCarga = new ArrayList<>();
				for (Object[] tmp : listado) {
					pedidosCarga = new DatosPedidosCargaDTO();				
					pedidosCarga.setCodigoPedido(String.valueOf(tmp[0]));
					pedidosCarga.setMcaPedidoValidado(String.valueOf(tmp[1]));				
					pedidosCarga.setDivisionPedido(String.valueOf(tmp[2]));
					pedidosCarga.setFechaCreacion(String.valueOf(tmp[3]));
					listaPedidosCarga.add(pedidosCarga);
				}
			}

			Integer totalResults = Integer.parseInt(String.valueOf(queryCount.getSingleResult()));
			Map<String, String> mapaMetaData = new HashMap<String, String>();
			mapaMetaData.put("totalItemsCount", totalResults.toString());
			result.setDatos(listaPedidosCarga);
			result.setMetadatos(mapaMetaData);
			
		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","GetPedidosCargaDAOImpl(GESADUAN)","listarPedidosCarga",e.getClass().getSimpleName(),e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}
		
		return result;
	}

}
