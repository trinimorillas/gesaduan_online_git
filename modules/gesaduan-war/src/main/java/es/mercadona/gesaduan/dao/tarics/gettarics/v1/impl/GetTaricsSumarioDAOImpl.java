package es.mercadona.gesaduan.dao.tarics.gettarics.v1.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.fwk.core.web.BoPage;
import es.mercadona.gesaduan.dao.BaseDAO;
import es.mercadona.gesaduan.dao.tarics.gettarics.v1.GetTaricsSumarioDAO;
import es.mercadona.gesaduan.dto.tarics.common.v1.restfull.DatosTaricsDTO;
import es.mercadona.gesaduan.dto.tarics.common.v1.restfull.OutputTaricsDTO;
import es.mercadona.gesaduan.dto.tarics.gettarics.v1.restful.InputTaricsDTO;
import es.mercadona.gesaduan.jpa.tarics.v1.TaricsJPA;

@SuppressWarnings("unchecked")
public class GetTaricsSumarioDAOImpl extends BaseDAO<TaricsJPA> implements GetTaricsSumarioDAO {

	@Override
	public void setEntityClass() {

		this.entityClass = TaricsJPA.class;

	}

	
	@Override
	public OutputTaricsDTO getTaricsSumario(InputTaricsDTO input, BoPage pagination) {

		List<DatosTaricsDTO> resultList = new ArrayList<>();
		OutputTaricsDTO result = new OutputTaricsDTO();


		Long taricDesde = null;
		if (input.getCodigoTaricDesde() != null) {
			taricDesde = Long.parseLong(input.getCodigoTaricDesde());
		}

		Long taricHasta = null;
		if (input.getCodigoTaricHasta() != null) {
			taricHasta = Long.parseLong(input.getCodigoTaricHasta());
		}

		String descripcion = null;
		if (input.getDescripcion() != null) {
			descripcion = input.getDescripcion();
		}

		String orden = null;
		orden = input.getOrden();

		final StringBuilder sql = new StringBuilder();
		final StringBuilder sqlCount = new StringBuilder();

		String count = " COUNT(*) ";
		String select = " SELECT  ";
		String campos = " CT.COD_N_TARIC, CT.TXT_CAPITULO, CT.TXT_PARTIDA, CT.TXT_SUBPARTIDA, "
				+ "CT.TXT_SUBDIVISION, CT.TXT_DESCRIPCION_APERTURA, "
				+ "COUNT(DISTINCT TP.COD_N_PRODUCTO) AS PRODUCTOS, COUNT(DISTINCT CR.COD_V_REA) AS REAS";
		String fromCount = " FROM D_CODIGO_TARIC CT ";
		String from = " FROM D_CODIGO_TARIC CT " + "LEFT JOIN S_TARIC_PRODUCTO TP ON TP.COD_N_TARIC = CT.COD_N_TARIC AND TP.FEC_D_INICIO <= SYSDATE AND (TP.FEC_D_FIN IS NULL OR TP.FEC_D_FIN >= SYSDATE) "
				+ "LEFT JOIN S_TARIC_REA CR ON CR.COD_N_TARIC = CT.COD_N_TARIC AND CR.FEC_D_INICIO <= SYSDATE AND (CR.FEC_D_FIN IS NULL OR CR.FEC_D_FIN >= SYSDATE) ";
		String where = " WHERE 1=1 ";
		String order = "";

		if (taricDesde != null) {
			where += " AND CT.COD_N_TARIC >= ?codigoTaricDesde ";
		}

		if (taricHasta != null) {
			where += " AND CT.COD_N_TARIC <= ?codigoTaricHasta ";
		}

		if (descripcion != null) {
			where += "AND (UPPER (CT.TXT_CAPITULO)  LIKE ('%'|| UPPER(?descripcion) ||'%') ";
			where += "OR UPPER (CT.TXT_DESCRIPCION_APERTURA)  LIKE ('%'|| UPPER(?descripcion) ||'%')";
			where += "OR UPPER (CT.TXT_PARTIDA)  LIKE ('%'|| UPPER(?descripcion) ||'%') ";
			where += "OR UPPER (CT.TXT_SUBDIVISION)  LIKE ('%'|| UPPER(?descripcion) ||'%') ";
			where += "OR UPPER (CT.TXT_SUBPARTIDA)  LIKE ('%'|| UPPER(?descripcion) ||'%') )";
		}

		String groupBy = " GROUP BY CT.COD_N_TARIC, CT.TXT_CAPITULO, CT.TXT_PARTIDA, CT.TXT_SUBPARTIDA, CT.TXT_SUBDIVISION, CT.TXT_DESCRIPCION_APERTURA ";

		if (orden.equals("+codigo")) {
			order += " ORDER BY CT.COD_N_TARIC ASC ";
		}

		if (orden.equals("-codigo")) {
			order += " ORDER BY CT.COD_N_TARIC DESC ";
		}

		if (orden.equals("+capitulo")) {
			order += " ORDER BY CT.TXT_CAPITULO ASC ";
		}

		if (orden.equals("-capitulo")) {
			order += " ORDER BY CT.TXT_CAPITULO DESC ";
		}

		sql.append(select).append(campos).append(from).append(where).append(groupBy).append(order);
		sqlCount.append(select).append(count).append(fromCount).append(where);
		
		Integer paginaTamanyo = pagination.getLimit().intValue();
		Integer paginaInicio = (pagination.getPage().intValue() * paginaTamanyo) - paginaTamanyo;

		final Query query = getEntityManager().createNativeQuery(sql.toString());
		final Query queryCount = getEntityManager().createNativeQuery(sqlCount.toString());
		
		query.setFirstResult(paginaInicio);
		query.setMaxResults(paginaTamanyo);

		if (taricDesde != null) {
			query.setParameter("codigoTaricDesde", taricDesde);
			queryCount.setParameter("codigoTaricDesde", taricDesde);
		}

		if (taricHasta != null) {
			query.setParameter("codigoTaricHasta", taricHasta);
			queryCount.setParameter("codigoTaricHasta", taricHasta);
		}

		if (descripcion != null) {
			query.setParameter("descripcion", descripcion);
			queryCount.setParameter("descripcion", descripcion);
		}


		
		List<Object[]> listado = query.getResultList();

		if (listado != null && !listado.isEmpty()) {

			for (Object[] tmp : listado) {
				DatosTaricsDTO datos = new DatosTaricsDTO();

				datos.setCodigo(String.valueOf(tmp[0]));
				datos.setCapitulo(String.valueOf(tmp[1]));
				datos.setPartidaSA(String.valueOf(tmp[2]));
				datos.setSubPartidaSA(String.valueOf(tmp[4]));
				datos.setSubPartidaNC(String.valueOf(tmp[3]));
				datos.setAperturaTARIC(String.valueOf(tmp[5]));
				datos.setNumeroREAs(Integer.parseInt(String.valueOf(tmp[7])));
				datos.setNumeroProductos(Integer.parseInt(String.valueOf(tmp[6])));

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


	@Override
	public boolean checkExisteRelacionTaricProd(Long codigoTaric) {

		if(codigoTaric != null) {
			String resultado = (String) getEntityManager().createNativeQuery("SELECT CASE WHEN COUNT(*) > 0 THEN 'TRUE' ELSE 'FALSE' END EXISTE_RELACION FROM S_TARIC_PRODUCTO "
					+ "WHERE COD_N_TARIC = ?codigoTaric AND (FEC_D_FIN IS NULL OR FEC_D_FIN >= SYSDATE)")
					.setParameter("codigoTaric", codigoTaric).getSingleResult();
			
			if(resultado.equals("TRUE")) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
		
	}

}
