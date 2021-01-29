package es.mercadona.gesaduan.dao.tarics.gettaricsdetalle.v1.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import es.mercadona.fwk.data.DaoBaseImpl;
import es.mercadona.gesaduan.dao.tarics.gettaricsdetalle.v1.GetTaricsDetalleDAO;
import es.mercadona.gesaduan.dto.tarics.gettaricsdetalle.v1.InputTaricsDetalleDTO;
import es.mercadona.gesaduan.dto.tarics.gettaricsdetalle.v1.restfull.DatosTaricsDTO;
import es.mercadona.gesaduan.dto.tarics.gettaricsdetalle.v1.restfull.OutputTaricsDetalleDTO;
import es.mercadona.gesaduan.dto.tarics.gettaricsdetalle.v1.restfull.ProductosDTO;
import es.mercadona.gesaduan.dto.tarics.gettaricsdetalle.v1.restfull.ReasDTO;

import es.mercadona.gesaduan.jpa.tarics.v1.TaricsJPA;

@SuppressWarnings("unchecked")
public class GetTaricsDetalleDAOImpl extends DaoBaseImpl<Long, TaricsJPA> implements GetTaricsDetalleDAO{

	@Override
	public void setEntityClass() {
		this.entityClass = TaricsJPA.class;
		
	}
	
	@Override
	public OutputTaricsDetalleDTO getTaricsDetalle(InputTaricsDetalleDTO input) {
		
		OutputTaricsDetalleDTO result = new OutputTaricsDetalleDTO();
		Long codigoTaric = Long.parseLong(input.getCodigoTaric());
		
		DatosTaricsDTO resultTarics = new DatosTaricsDTO();
		resultTarics = getTarics(codigoTaric);
		resultTarics.setProductos(getProductosList(codigoTaric));
		resultTarics.setReas(getProductoPorRea(codigoTaric));
		
		HashMap<String,String> metadatos = new HashMap<>();
		
		result.setDatos(resultTarics);
		result.setMetadatos(metadatos);
		
		
		
		return result;
	}
	

	private DatosTaricsDTO getTarics(Long codigoTaric) {
		
		
		DatosTaricsDTO taricsSalida = new DatosTaricsDTO();
		
		final StringBuilder sqlTarics = new StringBuilder();
		
		/* INICIO QUERY PARA RECUPERAR TARICS Y NUMERO DE REAS Y PRODUCTOS */
		
		sqlTarics.append(" SELECT CT.COD_N_TARIC,  ");
		sqlTarics.append(" CT.TXT_CAPITULO, ");
		sqlTarics.append(" CT.TXT_PARTIDA, ");
		sqlTarics.append(" CT.TXT_SUBPARTIDA, ");
		sqlTarics.append(" CT.TXT_SUBDIVISION, ");
		sqlTarics.append(" CT.TXT_DESCRIPCION_APERTURA, ");
		sqlTarics.append(" COUNT(DISTINCT TP.COD_N_PRODUCTO) AS PRODUCTOS, ");
		sqlTarics.append(" COUNT(DISTINCT CR.COD_V_REA) AS REAS ");
		sqlTarics.append(" FROM D_CODIGO_TARIC CT ");
		sqlTarics.append(" LEFT JOIN S_TARIC_PRODUCTO TP ON TP.COD_N_TARIC = CT.COD_N_TARIC AND TP.FEC_D_INICIO <= SYSDATE AND (TP.FEC_D_FIN IS NULL OR TP.FEC_D_FIN >= SYSDATE) ");
		sqlTarics.append(" LEFT JOIN S_TARIC_REA CR ON CR.COD_N_TARIC = CT.COD_N_TARIC AND CR.FEC_D_INICIO <= SYSDATE AND (CR.FEC_D_FIN IS NULL OR CR.FEC_D_FIN >= SYSDATE) ");
		sqlTarics.append(" WHERE CT.COD_N_TARIC = ?codigoTaric ");
		sqlTarics.append(" GROUP BY CT.COD_N_TARIC, ");
		sqlTarics.append(" CT.TXT_CAPITULO, ");
		sqlTarics.append(" CT.TXT_PARTIDA, ");
		sqlTarics.append(" CT.TXT_SUBPARTIDA, ");
		sqlTarics.append(" CT.TXT_SUBDIVISION, ");
		sqlTarics.append(" CT.TXT_DESCRIPCION_APERTURA ");
		
				
		/* FIN QUERY PARA RECUPERAR TARICS Y NUMERO DE REAS Y PRODUCTOS */
		
		final Query queryTarics = getEntityManager().createNativeQuery(sqlTarics.toString());
		
		queryTarics.setParameter("codigoTaric", codigoTaric);
		
		List<Object[]> listado = queryTarics.getResultList();
		
		if (listado != null && !listado.isEmpty()) {
			for (Object[] tmp : listado) {
				taricsSalida.setCodigo(String.valueOf(tmp[0]));
				taricsSalida.setCapitulo(String.valueOf(tmp[1]));
				taricsSalida.setPartidaSA(String.valueOf(tmp[2]));
				taricsSalida.setSubPartidaSA(String.valueOf(tmp[4]));
				taricsSalida.setSubPartidaNC(String.valueOf(tmp[3]));
				taricsSalida.setAperturaTARIC(String.valueOf(tmp[5]));
				taricsSalida.setNumeroProductos(Long.parseLong(String.valueOf(tmp[6])));
				taricsSalida.setNumeroREAs(Integer.parseInt(String.valueOf(tmp[7])));
			}
			
		}

		return taricsSalida;
	}
	
	
	private List<ProductosDTO> getProductosList(Long codigoTaric){
		
		final StringBuilder sqlProductos = new StringBuilder();
		
		sqlProductos.append(" SELECT PR.COD_N_PRODUCTO, ");
		sqlProductos.append(" PR.TXT_DENOMINA_ETIQUETA, ");
		sqlProductos.append(" PR.NUM_FORMATO_VENTA, ");
		sqlProductos.append(" PR.TXT_MARCA, ");
		sqlProductos.append(" PR.TXT_DENOMINA_ALTERNATIVA, ");
		sqlProductos.append(" PR.TXT_FORMATO_ALTERNATIVO, ");
		sqlProductos.append(" TP.COD_N_TARIC, ");
		sqlProductos.append(" RP.COD_V_REA, ");
		sqlProductos.append(" CPR.COD_V_SECCION ");
		sqlProductos.append(" FROM D_CODIGO_TARIC CT  ");
		sqlProductos.append(" INNER JOIN S_TARIC_PRODUCTO TP ON TP.COD_N_TARIC = CT.COD_N_TARIC AND TP.FEC_D_INICIO <= SYSDATE AND (TP.FEC_D_FIN IS NULL OR TP.FEC_D_FIN >= SYSDATE) ");
		sqlProductos.append(" INNER JOIN D_PRODUCTO_R PR ON TP.COD_N_PRODUCTO = PR.COD_N_PRODUCTO ");
		sqlProductos.append(" LEFT JOIN S_COMPONENTES_PRODUCTO_R CPR ON CPR.COD_N_PRODUCTO = PR.COD_N_PRODUCTO ");
		sqlProductos.append(" LEFT JOIN S_REA_PRODUCTO RP ON RP.COD_N_PRODUCTO = PR.COD_N_PRODUCTO AND RP.FEC_D_INICIO <= SYSDATE AND (RP.FEC_D_FIN IS NULL OR RP.FEC_D_FIN >= SYSDATE) ");
		sqlProductos.append(" WHERE CT.COD_N_TARIC = ?codigoTaric ");
		
		final Query queryProductos = getEntityManager().createNativeQuery(sqlProductos.toString());
		
		queryProductos.setParameter("codigoTaric", codigoTaric);
		
		List<Object[]> listado = queryProductos.getResultList();
		
		List<ProductosDTO> resultList = new ArrayList<>();
		
		if (listado != null && !listado.isEmpty()) {
			for (Object[] tmp : listado) {
					ProductosDTO productos = new ProductosDTO();
					
					productos.setCodigo(String.valueOf(tmp[0]));
					productos.setDenominacion(String.valueOf(tmp[1]));
					productos.setFormatoVenta(String.valueOf(tmp[2]));
					productos.setMarca(String.valueOf(tmp[3]));
					productos.setDenominacionAlt(String.valueOf(tmp[4]));
					productos.setFormatoVentaAlt(String.valueOf(tmp[5]));
					productos.setCodigoTaric(String.valueOf(tmp[6]));
					productos.setCodigoRea(String.valueOf(tmp[7]));
					if (tmp[8] != null) {
						if(tmp[8].equals("15")) {
							productos.setEsListoParaComer(true);
						}else {
							productos.setEsListoParaComer(false);
						}
					} else {
						productos.setEsListoParaComer(false);
					}
					
					resultList.add(productos);
				}
			}
		
		return resultList;
	}
	

	private List<ReasDTO> getProductoPorRea(Long codigoTaric){
		
		final StringBuilder sqlReas = new StringBuilder();
			
		String patternOutputDate = "yyy-MM-dd";
		SimpleDateFormat formatDate = new SimpleDateFormat(patternOutputDate);
		
		sqlReas.append(" SELECT "
				+ "CR.COD_V_REA, "
				+ "TR.COD_N_TARIC,  "
				+ "COUNT(RP.COD_N_PRODUCTO) AS PRODUCTOS, "
				+ "TR.FEC_D_INICIO "
				+ "FROM D_CODIGO_REA CR ");
		sqlReas.append(" INNER JOIN S_TARIC_REA TR ON TR.COD_V_REA = CR.COD_V_REA AND TR.FEC_D_INICIO <= SYSDATE AND (TR.FEC_D_FIN IS NULL OR TR.FEC_D_FIN >= SYSDATE) ");
		sqlReas.append(" LEFT JOIN S_REA_PRODUCTO RP ON RP.COD_V_REA = CR.COD_V_REA AND RP.FEC_D_INICIO <= SYSDATE AND (RP.FEC_D_FIN IS NULL OR RP.FEC_D_FIN >= SYSDATE) ");
		sqlReas.append(" WHERE TR.COD_N_TARIC = ?codigoTaric ");
		sqlReas.append(" GROUP BY CR.COD_V_REA, TR.COD_N_TARIC, TR.FEC_D_INICIO ");
		
		final Query queryReas = getEntityManager().createNativeQuery(sqlReas.toString());
		
		queryReas.setParameter("codigoTaric", codigoTaric);
		
		List<Object[]> listado = queryReas.getResultList();
		
		List<ReasDTO> resultList = new ArrayList<>();
		
		if (listado != null && !listado.isEmpty()) {
			for (Object[] tmp : listado) {
					ReasDTO reas = new ReasDTO();
					
					String codigoRea = String.valueOf(tmp[0]);
					Long codigoTaricRea = Long.parseLong(String.valueOf(tmp[2]));
					List<ProductosDTO> addProd =  getProductosRea(codigoRea, codigoTaric);
					
					reas.setCodigo(codigoRea);
					reas.setCodigoTaric(String.valueOf(tmp[1]));
					reas.setNumeroProductos(codigoTaricRea);
					reas.setProductos(addProd);
					
					if(tmp[3] != null) {
						Date fechaOutput = (Date) tmp[3];
						String dateToString = formatDate.format(fechaOutput);
						reas.setFechaInicio(dateToString);
					}else {
						reas.setFechaInicio(String.valueOf(tmp[3]));
					}
					
					resultList.add(reas);
				}
			}
		
		return resultList;
	}
	
	private List<ProductosDTO> getProductosRea(String codigoRea, Long codigoTaric){
		
		final StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT PR.COD_N_PRODUCTO, ");
		sql.append(" PR.TXT_DENOMINA_ETIQUETA, ");
		sql.append(" PR.NUM_FORMATO_VENTA, ");
		sql.append(" PR.TXT_MARCA, ");
		sql.append(" PR.TXT_DENOMINA_ALTERNATIVA, ");
		sql.append(" PR.TXT_FORMATO_ALTERNATIVO, ");
		sql.append(" TR.COD_N_TARIC, ");
		sql.append(" RP.COD_V_REA, ");
		sql.append(" CPR.COD_V_SECCION ");
		sql.append(" FROM D_CODIGO_REA CR ");
		sql.append(" INNER JOIN S_REA_PRODUCTO RP ON RP.COD_V_REA = CR.COD_V_REA AND RP.FEC_D_INICIO <= SYSDATE AND (RP.FEC_D_FIN IS NULL OR RP.FEC_D_FIN >= SYSDATE)");
		sql.append(" INNER JOIN D_PRODUCTO_R PR ON RP.COD_N_PRODUCTO = PR.COD_N_PRODUCTO ");
		sql.append(" LEFT JOIN S_TARIC_REA TR ON TR.COD_V_REA = CR.COD_V_REA AND TR.FEC_D_INICIO <= SYSDATE AND (TR.FEC_D_FIN IS NULL OR TR.FEC_D_FIN >= SYSDATE) AND TR.COD_N_TARIC = ?codigoTaric");
		sql.append(" LEFT JOIN S_COMPONENTES_PRODUCTO_R CPR ON CPR.COD_N_PRODUCTO = PR.COD_N_PRODUCTO ");
		sql.append(" WHERE CR.COD_V_REA = ?codigoRea ");
		
		final Query query = getEntityManager().createNativeQuery(sql.toString());
		
		query.setParameter("codigoRea", codigoRea);
		query.setParameter("codigoTaric", codigoTaric);
		
		List<Object[]> listado = query.getResultList();
		
		List<ProductosDTO> resultList = new ArrayList<>();
		
		if (listado != null && !listado.isEmpty()) {
			for (Object[] tmp : listado) {
					ProductosDTO productos = new ProductosDTO();
					
					productos.setCodigo(String.valueOf(tmp[0]));
					productos.setDenominacion(String.valueOf(tmp[1]));
					productos.setFormatoVenta(String.valueOf(tmp[2]));
					productos.setMarca(String.valueOf(tmp[3]));
					productos.setDenominacionAlt(String.valueOf(tmp[4]));
					productos.setFormatoVentaAlt(String.valueOf(tmp[5]));
					productos.setCodigoTaric(String.valueOf(tmp[6]));
					productos.setCodigoRea(String.valueOf(tmp[7]));
					if(tmp[8].equals("15")) {
						productos.setEsListoParaComer(true);
					}else {
						productos.setEsListoParaComer(false);
					}
					
					resultList.add(productos);
				}
			}
		
		return resultList;
	}

	
	@Override
	public boolean checkExistTaric(InputTaricsDetalleDTO input) {

		TaricsJPA taric = findById(Long.parseLong(input.getCodigoTaric()));  
		if(taric != null) {
			return true;
		}
		else {
			return false;
		}
		
	}

	@Override
	protected EntityManager getEntityManager() {
		return this.entityM;
	}
	
	@PersistenceContext
	private EntityManager entityM;

}
