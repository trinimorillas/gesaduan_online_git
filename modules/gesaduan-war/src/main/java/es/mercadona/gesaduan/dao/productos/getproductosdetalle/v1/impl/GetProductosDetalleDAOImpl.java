package es.mercadona.gesaduan.dao.productos.getproductosdetalle.v1.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import es.mercadona.gesaduan.dao.BaseDAO;
import es.mercadona.gesaduan.dao.productos.getproductosdetalle.v1.GetProductosDetalleDAO;
import es.mercadona.gesaduan.dto.productos.getproductosdetalle.v1.InputProductosDetalleDTO;
import es.mercadona.gesaduan.dto.productos.getproductosdetalle.v1.restfull.DatosGeneralDTO;
import es.mercadona.gesaduan.dto.productos.getproductosdetalle.v1.restfull.DatosProductosDetalleDTO;
import es.mercadona.gesaduan.dto.productos.getproductosdetalle.v1.restfull.OutputProductosDetalleDTO;
import es.mercadona.gesaduan.dto.productos.getproductosdetalle.v1.restfull.ProveedoresProductoDTO;
import es.mercadona.gesaduan.dto.productos.getproductosdetalle.v1.restfull.ReaProductoDTO;
import es.mercadona.gesaduan.dto.productos.getproductosdetalle.v1.restfull.TaricProductoDTO;
import es.mercadona.gesaduan.jpa.productos.v1.ProductosJPA;

@SuppressWarnings("unchecked")
public class GetProductosDetalleDAOImpl extends BaseDAO<ProductosJPA> implements GetProductosDetalleDAO{

	@Override
	public void setEntityClass() {
		this.entityClass = ProductosJPA.class;		
	}	
	
	@Override
	public OutputProductosDetalleDTO getProductosDetalle(InputProductosDetalleDTO input) {
		
		OutputProductosDetalleDTO resultOutput = new OutputProductosDetalleDTO();
		DatosGeneralDTO datos = new DatosGeneralDTO();
		DatosProductosDetalleDTO producto = new DatosProductosDetalleDTO();
		
		
		Long codigoProducto = Long.parseLong(input.getCodigoProducto());
		String orden = input.getOrden();
		
		producto = getProductos(codigoProducto);
		
		String codigoTaric = producto.getCodigoTaric();
		
		/*
		if(codigoTaric != null && !codigoTaric.isEmpty()) {
			producto.setTaric(getTarics(Long.parseLong(codigoTaric)));
			producto.setReas(getRea(Long.parseLong(codigoTaric)));
		}
		*/		
		
		producto.setProveedores(getProveedores(codigoProducto, orden));
		datos.setProducto(producto);
		resultOutput.setDatos(datos);

		return resultOutput;
	}
	
	
	private DatosProductosDetalleDTO getProductos(Long codigoProducto) {
		
		DatosProductosDetalleDTO productos =  new DatosProductosDetalleDTO();		
		final StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT PR.COD_N_PRODUCTO, ");
		sql.append(" PR.TXT_DENOMINA_ETIQUETA, ");
		sql.append(" PR.NUM_FORMATO_VENTA, ");
		sql.append(" PR.TXT_MARCA, ");
		sql.append(" PR.TXT_DENOMINA_ALTERNATIVA, ");
		sql.append(" PR.TXT_FORMATO_ALTERNATIVO, ");
		sql.append(" TP.COD_N_TARIC, ");
		sql.append(" RP.COD_V_REA, ");
		sql.append(" CPR.COD_V_SECCION, ");
		sql.append(" PR.txt_unidad_medida ");
		sql.append(" FROM D_PRODUCTO_R PR ");
		sql.append(" LEFT JOIN S_TARIC_PRODUCTO TP ON TP.COD_N_PRODUCTO = PR.COD_N_PRODUCTO AND TP.FEC_D_INICIO <= SYSDATE AND (TP.FEC_D_FIN IS NULL OR TP.FEC_D_FIN >= SYSDATE)");
		sql.append(" LEFT JOIN S_REA_PRODUCTO RP ON RP.COD_N_PRODUCTO = PR.COD_N_PRODUCTO AND RP.FEC_D_INICIO <= SYSDATE AND (RP.FEC_D_FIN IS NULL OR RP.FEC_D_FIN >= SYSDATE)");
		sql.append(" LEFT JOIN S_COMPONENTES_PRODUCTO_R CPR ON CPR.COD_N_PRODUCTO = PR.COD_N_PRODUCTO ");
		sql.append(" WHERE 1 = 1 ");
		sql.append(" AND PR.COD_N_PRODUCTO = ?codigoProducto");
		
		final Query query = getEntityManager().createNativeQuery(sql.toString());
		
		query.setParameter("codigoProducto", codigoProducto);
		
		List<Object[]> listado = query.getResultList();
		
		if (listado != null && !listado.isEmpty()) {
			for (Object[] tmp : listado) {
				productos.setCodigo(String.valueOf(tmp[0]));
				productos.setDenominacion(String.valueOf(tmp[1]));
				productos.setFormatoVenta(String.valueOf(tmp[2]));
				productos.setMarca(String.valueOf(tmp[3]));
				productos.setDenominacionAlt(String.valueOf(tmp[4]));
				productos.setFormatoVentaAlt(String.valueOf(tmp[5]));
				if (tmp[6] != null) {
					productos.setCodigoTaric(String.valueOf(tmp[6]));
				} else {
					productos.setCodigoTaric(null);
				}
				
				if (tmp[7] != null) {
					productos.setCodigoRea(String.valueOf(tmp[7]));
				} else {
					productos.setCodigoRea(null);
				}
	
				if (tmp[8] != null && tmp[8].equals("15")) {
					productos.setEsListoParaComer(true);
				} else {
					productos.setEsListoParaComer(false);
				}
				productos.setUnidadMedida(String.valueOf(tmp[9]));
			}
			
		}
		
		return productos;
	}
	

	private TaricProductoDTO getTarics(Long codigoTaric) {
		
		TaricProductoDTO tarics = new TaricProductoDTO();
		
		final StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT CT.COD_N_TARIC,  ");
		sql.append(" CT.TXT_CAPITULO, ");
		sql.append(" CT.TXT_PARTIDA, ");
		sql.append(" CT.TXT_SUBPARTIDA, ");
		sql.append(" CT.TXT_SUBDIVISION, ");
		sql.append(" CT.TXT_DESCRIPCION_APERTURA, ");
		sql.append(" COUNT(DISTINCT TP.COD_N_PRODUCTO) AS PRODUCTOS, ");
		sql.append(" COUNT(DISTINCT CR.COD_V_REA) AS REAS ");
		sql.append(" FROM D_CODIGO_TARIC CT ");
		sql.append(" LEFT JOIN S_TARIC_PRODUCTO TP ON TP.COD_N_TARIC = CT.COD_N_TARIC AND TP.FEC_D_INICIO <= SYSDATE AND (TP.FEC_D_FIN IS NULL OR TP.FEC_D_FIN >= SYSDATE) ");
		sql.append(" LEFT JOIN S_TARIC_REA CR ON CR.COD_N_TARIC = CT.COD_N_TARIC AND CR.FEC_D_INICIO <= SYSDATE AND (CR.FEC_D_FIN IS NULL OR CR.FEC_D_FIN >= SYSDATE) ");
		sql.append(" WHERE CT.COD_N_TARIC = ?codigoTaric ");
		sql.append(" GROUP BY CT.COD_N_TARIC, ");
		sql.append(" CT.TXT_CAPITULO, ");
		sql.append(" CT.TXT_PARTIDA, ");
		sql.append(" CT.TXT_SUBPARTIDA, ");
		sql.append(" CT.TXT_SUBDIVISION, ");
		sql.append(" CT.TXT_DESCRIPCION_APERTURA ");
		
		final Query query = getEntityManager().createNativeQuery(sql.toString());
		
		query.setParameter("codigoTaric", codigoTaric);
		
		List<Object[]> listado = query.getResultList();
		
		if (listado != null && !listado.isEmpty()) {
			for (Object[] tmp : listado) {
				tarics.setCodigo(String.valueOf(tmp[0]));
				tarics.setCapitulo(String.valueOf(tmp[1]));
				tarics.setPartidaSA(String.valueOf(tmp[2]));
				tarics.setSubPartidaSA(String.valueOf(tmp[3]));
				tarics.setSubPartidaNC(String.valueOf(tmp[4]));
				tarics.setAperturaTARIC(String.valueOf(tmp[5]));
				tarics.setNumeroProductos(Long.parseLong(String.valueOf(tmp[6])));
				tarics.setNumeroREAs(Long.parseLong(String.valueOf(tmp[7])));
			}
			
		}
		
		return tarics;
	}

	
	private List<ReaProductoDTO> getRea(Long codigoTaric) {
		
		final StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT STR.COD_V_REA, CT.COD_N_TARIC, COUNT(RP.COD_N_PRODUCTO) FROM D_CODIGO_TARIC CT ");
		sql.append(" LEFT JOIN S_TARIC_REA STR ON STR.COD_N_TARIC = CT.COD_N_TARIC AND STR.FEC_D_INICIO <= SYSDATE AND (STR.FEC_D_FIN IS NULL OR STR.FEC_D_FIN >= SYSDATE)");
		sql.append(" LEFT JOIN S_REA_PRODUCTO RP ON RP.COD_V_REA = STR.COD_V_REA AND RP.FEC_D_INICIO <= SYSDATE AND (RP.FEC_D_FIN IS NULL OR RP.FEC_D_FIN >= SYSDATE) ");
		sql.append(" WHERE CT.COD_N_TARIC = ?codigoTaric ");
		sql.append(" GROUP BY STR.COD_V_REA, CT.COD_N_TARIC ");
		
		final Query query = getEntityManager().createNativeQuery(sql.toString());
		
		query.setParameter("codigoTaric", codigoTaric);
		
		List<Object[]> listado = query.getResultList();
		
		List<ReaProductoDTO> resultList = new ArrayList<>();
		
		if (listado != null && !listado.isEmpty()) {
			for (Object[] tmp : listado) {
				ReaProductoDTO reas = new ReaProductoDTO();
				reas.setCodigo(String.valueOf(tmp[0]));
				reas.setCodigoTaric(String.valueOf(tmp[1]));
				reas.setNumeroProductos(Long.parseLong(String.valueOf(tmp[2])));
				resultList.add(reas);
			}
			
		}
		
		return resultList;
	}
	
	
	private List<ProveedoresProductoDTO> getProveedores(Long codigoProducto, String orden) {
		
		final StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT DISTINCT ");
		sql.append(" PPR.COD_N_LEGACY_PROVEEDOR, ");
		sql.append(" PROV.TXT_RAZON_SOCIAL, ");
		sql.append(" PROV.COD_V_PROVINCIA, ");
		sql.append(" PROV.COD_N_PROVEEDOR, ");
		sql.append(" PROV.FEC_D_ACTIVACION ");
		sql.append(" FROM D_PRODUCTO_R PROD ");
		sql.append(" LEFT JOIN S_EAN_PRODUCTO_PROVEEDOR_R PPR ON PPR.COD_N_PRODUCTO = PROD.COD_N_PRODUCTO ");
		sql.append(" INNER JOIN D_PROVEEDOR_R PROV ON PROV.COD_N_LEGACY_PROVEEDOR = PPR.COD_N_LEGACY_PROVEEDOR ");
		sql.append(" WHERE PROD.COD_N_PRODUCTO = ?codigoProducto ");
		
		if (orden.equals("+codigoPublico"))
			sql.append(" ORDER BY CASE WHEN REPLACE(TRANSLATE(TRIM(PPR.COD_N_LEGACY_PROVEEDOR), '0123456789', '0'), '0', '') IS NULL THEN TO_NUMBER(PPR.COD_N_LEGACY_PROVEEDOR) END, PPR.COD_N_LEGACY_PROVEEDOR ");
		else if (orden.equals("-codigoPublico"))
			sql.append(" ORDER BY CASE WHEN REPLACE(TRANSLATE(TRIM(PPR.COD_N_LEGACY_PROVEEDOR), '0123456789', '0'), '0', '') IS NULL THEN TO_NUMBER(PPR.COD_N_LEGACY_PROVEEDOR) END DESC, PPR.COD_N_LEGACY_PROVEEDOR DESC ");
		else if (orden.equals("+nombre"))
			sql.append(" ORDER BY PROV.TXT_RAZON_SOCIAL ASC ");
		else if (orden.equals("-nombre"))
			sql.append(" ORDER BY PROV.TXT_RAZON_SOCIAL DESC ");
		
		final Query query = getEntityManager().createNativeQuery(sql.toString());
		
		query.setParameter("codigoProducto", codigoProducto);
		
		List<Object[]> listado = query.getResultList();
		
		List<ProveedoresProductoDTO> resultList = new ArrayList<>();
		
		if (listado != null && !listado.isEmpty()) {
			for (Object[] tmp : listado) {
				ProveedoresProductoDTO productos = new ProveedoresProductoDTO();
				
				String patternOutputDateTime = "yyyy-MM-dd'T'HH:mm:ss.SSSz";
				SimpleDateFormat formatDateTime = new SimpleDateFormat(patternOutputDateTime);
				Date fechaOutput = null;
					
					productos.setCodigoPublico(String.valueOf(tmp[0]));
					productos.setNombre(String.valueOf(tmp[1]));
					productos.setProvincia(String.valueOf(tmp[2]));
					productos.setCodigo(String.valueOf(tmp[3]));
					if(tmp[4] != null) {
						fechaOutput = (Date) tmp[4];
						String dateToString = formatDateTime.format(fechaOutput);
						productos.setFechaActivacion(dateToString);
					}else {
						productos.setFechaActivacion(String.valueOf(tmp[4]));
					}
					resultList.add(productos);
				}
			}
		
		return resultList;
	}
}
