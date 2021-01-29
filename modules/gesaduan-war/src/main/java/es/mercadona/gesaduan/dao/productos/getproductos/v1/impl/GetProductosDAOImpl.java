package es.mercadona.gesaduan.dao.productos.getproductos.v1.impl;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.fwk.core.web.BoPage;
import es.mercadona.gesaduan.dao.BaseDAO;
import es.mercadona.gesaduan.dao.productos.getproductos.v1.GetProductosDAO;
import es.mercadona.gesaduan.dto.productos.getproductos.v1.InputGetProductoDTO;
import es.mercadona.gesaduan.dto.productos.getproductos.v1.restfull.OutputProductosDTO;
import es.mercadona.gesaduan.dto.productos.getproductos.v1.restfull.DatosProductosDTO;
import es.mercadona.gesaduan.jpa.productos.v1.ProductosJPA;

public class GetProductosDAOImpl extends BaseDAO<ProductosJPA> implements GetProductosDAO {

	@Override
	public void setEntityClass() {
		this.entityClass = ProductosJPA.class;		
	}

	@Override
	public OutputProductosDTO getProductos(InputGetProductoDTO input, BoPage pagination) {
		
		List<DatosProductosDTO> resultList = new ArrayList<>();
		OutputProductosDTO result = new OutputProductosDTO();

		String denominacion = null;
		if (input.getDenominacion() != null)
			denominacion = input.getDenominacion();

		String marca = null;
		if (input.getMarca() != null)
			marca = input.getMarca();
		
		Long codigoTaric = null;
		if (input.getCodigoTaric() != null)
			codigoTaric = Long.parseLong(input.getCodigoTaric());
		
		String codigoRea = null;
		if (input.getCodigoRea() != null)
			codigoRea = input.getCodigoRea();
		
		String subvencion = null;
		if (input.getSubvencion() != null)
			subvencion = input.getSubvencion();
		
		Integer formatoVenta = null;
		if (input.getFormatoVenta() != null)
			formatoVenta = Integer.parseInt(input.getFormatoVenta());
		
		Boolean listoParaComer = null;
		if (input.getListoParaComer() != null)
			listoParaComer = input.getListoParaComer();
		
		Integer codigo = null;
		if (input.getCodigo() != null)
			codigo = Integer.parseInt(input.getCodigo());
		
		String estado = null;
		if (input.getEstado() != null)
			estado = input.getEstado();
		
		Integer paginaInicio = null;
		if (pagination.getPage() != null)
			paginaInicio = pagination.getPage().intValue();
		
		Integer paginaTamanyo = null;
		if (pagination.getLimit() != null)
			paginaTamanyo = pagination.getLimit().intValue();
		
		String orden = null;
		if (input.getOrden() != null)
			orden = input.getOrden();
		
		final StringBuilder sql = new StringBuilder();
		final StringBuilder sqlCount = new StringBuilder();
		
		String count = " SELECT COUNT(*) FROM (";
		String select = "SELECT DISTINCT ";
		String campos = "PRO.COD_N_PRODUCTO, PRO.TXT_DENOMINA_ETIQUETA, PRO.NUM_FORMATO_VENTA, "
				+ "PRO.TXT_MARCA, PRO.TXT_DENOMINA_ALTERNATIVA, PRO.TXT_FORMATO_ALTERNATIVO, "
				+ "STP.COD_N_TARIC, SRP.COD_V_REA, DECODE(SCP.COD_V_SECCION, 15, 'S', 'N') AS LISTO_PARA_COMER ";
		String from = "FROM D_PRODUCTO_R PRO "
				+ "LEFT JOIN S_COMPONENTES_PRODUCTO_R SCP ON SCP.COD_N_PRODUCTO = PRO.COD_N_PRODUCTO "
				+ "LEFT JOIN S_TARIC_PRODUCTO STP ON STP.COD_N_PRODUCTO = PRO.COD_N_PRODUCTO AND STP.FEC_D_INICIO <= SYSDATE AND (STP.FEC_D_FIN IS NULL OR STP.FEC_D_FIN >= SYSDATE) "
				+ "LEFT JOIN S_REA_PRODUCTO SRP ON SRP.COD_N_PRODUCTO = PRO.COD_N_PRODUCTO AND SRP.FEC_D_INICIO <= SYSDATE AND (SRP.FEC_D_FIN IS NULL OR SRP.FEC_D_FIN >= SYSDATE) ";
		String countFin = " )";
		
		if(input.getActivoAduana() != null) {
			if(input.getActivoAduana().equals("SI")) {
				from += "INNER JOIN S_EAN_PRODUCTO_PROVEEDOR_R EPPR ON EPPR.COD_N_PRODUCTO = PRO.COD_N_PRODUCTO " + 
						"INNER JOIN D_PROVEEDOR_R PROV ON PROV.COD_N_LEGACY_PROVEEDOR = EPPR.COD_N_LEGACY_PROVEEDOR AND PROV.MCA_ACTIVO_CSM = 'S' ";
			}else if(input.getActivoAduana().equals("NO")) {
				from += "INNER JOIN S_EAN_PRODUCTO_PROVEEDOR_R EPPR ON EPPR.COD_N_PRODUCTO = PRO.COD_N_PRODUCTO " + 
						"INNER JOIN D_PROVEEDOR_R PROV ON PROV.COD_N_LEGACY_PROVEEDOR = EPPR.COD_N_LEGACY_PROVEEDOR AND PROV.MCA_ACTIVO_CSM = 'N' ";
			}
		}
		
		String where = " WHERE 1 = 1 ";
		String order = "";

		if (denominacion != null)
			where += " AND UPPER(PRO.TXT_DENOMINA_ETIQUETA) LIKE ('%'|| UPPER(?denominacion) ||'%') ";
		
		if (marca != null)
			where += " AND UPPER(PRO.TXT_MARCA) LIKE ('%'|| UPPER(?marca) ||'%') ";
		
		if (codigoTaric != null)
			where += " AND STP.COD_N_TARIC = ?codigoTaric ";
		
		if (codigoRea != null)
			where += " AND UPPER(SRP.COD_V_REA) = UPPER(?codigoRea) ";
		
		if(subvencion != null) {
			if (subvencion.equals("S")) {
				where += " AND SRP.COD_V_REA IS NOT NULL ";
			} else if (subvencion.equals("N")) {
				where += " AND SRP.COD_V_REA IS NULL ";
			}
		}
		
		if (formatoVenta != null)
			where += " AND PRO.NUM_FORMATO_VENTA = ?formatoVenta ";
		
		if (listoParaComer != null) {
			if (listoParaComer)
				where += " AND SCP.COD_V_SECCION = 15 ";
			else if (!listoParaComer)
				where += " AND (SCP.COD_V_SECCION <> 15 OR SCP.COD_V_SECCION IS NULL) ";
		}	
		
		if (codigo != null)
			where += " AND PRO.COD_N_PRODUCTO = ?codigo ";
		
		if (estado != null) {
			if(estado.equals("A")) {
				where += " AND PRO.TXT_ESTADO_COMERCIAL = 'A' ";
			} else if(estado.equals("O")) {
				where += " AND PRO.TXT_ESTADO_COMERCIAL <> 'A' ";
			}
		}
		
		if (orden.equals("+codigo"))
			order += " ORDER BY PRO.COD_N_PRODUCTO ASC ";
		else if (orden.equals("-codigo"))
			order += " ORDER BY PRO.COD_N_PRODUCTO DESC ";
		else if (orden.equals("+denominacion"))
			order += " ORDER BY PRO.TXT_DENOMINA_ETIQUETA ASC ";
		else if (orden.equals("-denominacion"))
			order += " ORDER BY PRO.TXT_DENOMINA_ETIQUETA DESC ";
		else if (orden.equals("+marca"))
			order += " ORDER BY PRO.TXT_MARCA ASC ";
		else if (orden.equals("-marca"))
			order += " ORDER BY PRO.TXT_MARCA DESC ";
		else if (orden.equals("+taric"))
			order += " ORDER BY STP.COD_N_TARIC ASC ";
		else if (orden.equals("-taric"))
			order += " ORDER BY STP.COD_N_TARIC DESC ";
		else if (orden.equals("+rea"))
			order += " ORDER BY SRP.COD_V_REA ASC ";
		else if (orden.equals("-rea"))
			order += " ORDER BY SRP.COD_V_REA DESC ";
		
		sql.append(select).append(campos).append(from).append(where).append(order);
		sqlCount.append(count).append(select).append(campos).append(from).append(where).append(countFin);

		final Query query = getEntityManager().createNativeQuery(sql.toString());
		final Query queryCount = getEntityManager().createNativeQuery(sqlCount.toString());
		
		if (denominacion != null) {
			query.setParameter("denominacion", denominacion);
			queryCount.setParameter("denominacion", denominacion);
		}
		
		if (marca != null) {
			query.setParameter("marca", marca);
			queryCount.setParameter("marca", marca);
		}
		
		if (codigoTaric != null) {
			query.setParameter("codigoTaric", codigoTaric);
			queryCount.setParameter("codigoTaric", codigoTaric);
		}
		
		if (codigoRea != null) {
			query.setParameter("codigoRea", codigoRea);
			queryCount.setParameter("codigoRea", codigoRea);
		}
		
		if (subvencion != null) {
			query.setParameter("subvencion", subvencion);
			queryCount.setParameter("subvencion", subvencion);
		}
		
		if (formatoVenta != null) {
			query.setParameter("formatoVenta", formatoVenta);
			queryCount.setParameter("formatoVenta", formatoVenta);
		}
		
		if (listoParaComer != null) {
			query.setParameter("listoParaComer", listoParaComer);
			queryCount.setParameter("listoParaComer", listoParaComer);
		}
		
		if (codigo != null) {
			query.setParameter("codigo", codigo);
			queryCount.setParameter("codigo", codigo);
		}
		
		if (estado != null) {
			query.setParameter("estado", estado);
			queryCount.setParameter("estado", estado);
		}
		
		if (paginaInicio != null) {
			if (paginaTamanyo != null) {
				paginaInicio = (paginaInicio * paginaTamanyo) - paginaTamanyo;
			} else {
				paginaInicio = (paginaInicio * 10) - 10;
			}
		}

		query.setFirstResult(paginaInicio);
		query.setMaxResults(paginaTamanyo);
		
		@SuppressWarnings("unchecked")
		List<Object[]> listado = query.getResultList();

		if (listado != null && !listado.isEmpty()) {

			for (Object[] tmp : listado) {
				DatosProductosDTO datos = new DatosProductosDTO();

				datos.setCodigo(String.valueOf(tmp[0]));
				datos.setDenominacion(String.valueOf(tmp[1]));
				datos.setFormatoVenta(String.valueOf(tmp[2]));
				datos.setMarca(String.valueOf(tmp[3]));
				datos.setDenominacionAlt(String.valueOf(tmp[4]));
				datos.setFormatoVentaAlt(String.valueOf(tmp[5]));
				datos.setCodigoTaric(String.valueOf(tmp[6]));
				datos.setCodigoRea(String.valueOf(tmp[7]));
				if(tmp[8].equals("S")) {
					datos.setEsListoParaComer(true);
				}else {
					datos.setEsListoParaComer(false);
				}

				resultList.add(datos);
			}
		}
		
		try {
			Integer totalResults = Integer.parseInt(String.valueOf(queryCount.getSingleResult()));

			Map<String, String> mapaMetaData = new HashMap<String, String>();
			mapaMetaData.put("totalItemsCount", totalResults.toString());
	

			result.setMetadatos(mapaMetaData);
			result.setDatos(resultList);

		} catch (NumberFormatException e) {
			throw new ApplicationException(e.getMessage());
		}
		
		return result;
	}
	
}
