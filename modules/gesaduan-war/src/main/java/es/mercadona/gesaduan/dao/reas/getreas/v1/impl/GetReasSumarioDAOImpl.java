package es.mercadona.gesaduan.dao.reas.getreas.v1.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.Query;

import es.mercadona.fwk.auth.SecurityService;
import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.fwk.core.exceptions.ExceptionUtils;
import es.mercadona.fwk.core.web.BoPage;
import es.mercadona.gesaduan.dao.BaseDAO;
import es.mercadona.gesaduan.dao.reas.getreas.v1.GetReasSumarioDAO;
import es.mercadona.gesaduan.dto.reas.getreas.v1.InputReasDTO;
import es.mercadona.gesaduan.dto.reas.getreas.v1.restfull.DatosReasDTO;
import es.mercadona.gesaduan.dto.reas.getreas.v1.restfull.OutputReasDTO;
import es.mercadona.gesaduan.jpa.reas.v1.ReasPostJPA;

public class GetReasSumarioDAOImpl extends BaseDAO<ReasPostJPA> implements GetReasSumarioDAO{

	@Inject
	private org.slf4j.Logger logger;
	  
	@Inject
	private SecurityService securityService;
	
	@Override
	public void setEntityClass() {
		this.entityClass = ReasPostJPA.class;
	}
	
	
	@SuppressWarnings({"unchecked"})
	@Override
	public OutputReasDTO getReasSumario(InputReasDTO input, BoPage pagination) {
		
		OutputReasDTO result = null;
		boolean existeRelacion = input.getExisteRelacion();
		
		try {
			
			String orden = input.getOrden();
						
			final StringBuilder sql = new StringBuilder();
			final StringBuilder sqlCount = new StringBuilder();	
			
			if(existeRelacion) {
				sql.append(" SELECT DISTINCT CR.COD_V_REA, TR.COD_N_TARIC, COUNT(DISTINCT RP.COD_N_PRODUCTO) AS PRODUCTOS ");
				sql.append(" FROM D_CODIGO_REA CR ");
				sql.append(" LEFT JOIN S_TARIC_REA TR ON TR.COD_V_REA = CR.COD_V_REA AND TR.FEC_D_INICIO <= SYSDATE AND (TR.FEC_D_FIN >= SYSDATE OR TR.FEC_D_FIN IS NULL) ");
				sql.append(" LEFT JOIN S_REA_PRODUCTO RP ON CR.COD_V_REA = RP.COD_V_REA AND RP.FEC_D_INICIO <= SYSDATE AND (RP.FEC_D_FIN >= SYSDATE OR RP.FEC_D_FIN IS NULL) ");
				sql.append(" WHERE 1 = 1 ");
				
			}else {
				sql.append(" SELECT CR.COD_V_REA, null as taric, 0 as productos ");
				sql.append(" FROM D_CODIGO_REA CR ");
				sql.append(" WHERE EXISTS ");
				sql.append(" (SELECT * ");
				sql.append(" FROM D_CODIGO_TARIC CT ");
				sql.append(" WHERE CT.COD_N_TARIC = ?codigoTaric ");
				sql.append(" AND SUBSTR(CR.COD_V_REA, 0, 8) = SUBSTR(lpad(CT.COD_N_TARIC, 10, 0), 0, 8)) ");
				sql.append(" AND NOT EXISTS ");
				sql.append(" (SELECT * ");
				sql.append(" FROM S_TARIC_REA TR ");
				sql.append(" WHERE TR.COD_V_REA = CR.COD_V_REA ");
				sql.append(" AND TR.COD_N_TARIC = ?codigoTaric ");
				sql.append(" AND TR.FEC_D_INICIO <= SYSDATE ");
				sql.append(" AND (TR.FEC_D_FIN IS NULL ");
				sql.append(" OR TR.FEC_D_FIN >= SYSDATE)) ");
			}
				
			String codigoDesde = null;
			if(input.getCodigoDesde() != null) {
				codigoDesde = input.getCodigoDesde();
				sql.append(" AND CR.COD_V_REA >= ?codigoDesde ");				
			}
			
			String codigoHasta = null;
			if(input.getCodigoHasta() != null) {
				codigoHasta = input.getCodigoHasta();
				sql.append(" AND CR.COD_V_REA <= ?codigoHasta ");				
			}
			
			Long codigoTaric = input.getCodigoTaric() != null ? Long.parseLong(input.getCodigoTaric()) : null;
			
			if(existeRelacion) {
				sql.append(" GROUP BY CR.COD_V_REA, TR.COD_N_TARIC ");
			}else {
				sql.append(" GROUP BY CR.COD_V_REA ");
			}
			
			
			
			if(orden.equals("+codigo")) {
				sql.append(" ORDER BY CR.COD_V_REA ASC ");
			}
			
			if(orden.equals("-codigo")) {
				sql.append(" ORDER BY CR.COD_V_REA DESC ");
			}
			
			if(orden.equals("+codigoTaric")) {
				sql.append(" ORDER BY TR.COD_N_TARIC ASC ");
			}
			
			if(orden.equals("-codigoTaric")) {
				sql.append(" ORDER BY TR.COD_N_TARIC DESC ");
			}
			
			if(orden.equals("+numeroProductos")) {
				sql.append(" ORDER BY PRODUCTOS ASC ");
			}
			
			if(orden.equals("-numeroProductos")) {
				sql.append(" ORDER BY PRODUCTOS DESC ");
			}
			
			
			sqlCount.append(" SELECT COUNT(*) FROM( ").append(sql).append(" )");
			
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			final Query queryCount = getEntityManager().createNativeQuery(sqlCount.toString());
			
				
			Integer paginaTamanyo = pagination.getLimit().intValue();
			Integer paginaInicio = (pagination.getPage().intValue() * paginaTamanyo) - paginaTamanyo;
			
			query.setFirstResult(paginaInicio);
			query.setMaxResults(paginaTamanyo);
			
			if(codigoDesde != null) {
				query.setParameter("codigoDesde", codigoDesde);
				queryCount.setParameter("codigoDesde", codigoDesde);
			}
			
			if(codigoHasta != null) {
				query.setParameter("codigoHasta", codigoHasta);
				queryCount.setParameter("codigoHasta", codigoHasta);
			}
			
			if(codigoTaric != null) {
				query.setParameter("codigoTaric", codigoTaric);
				queryCount.setParameter("codigoTaric", codigoTaric);
			}
			
			
			Integer totalResults = Integer.parseInt(String.valueOf(queryCount.getSingleResult()));
						
			HashMap<String,String> metadatos = new HashMap<>();
			metadatos.put("totalItemsCount", totalResults.toString());
			
			result = new OutputReasDTO(); 
			List<DatosReasDTO> resultList = new ArrayList<>(); 
			
			
			List<Object[]> listado = query.getResultList();
			
			if(listado != null && !listado.isEmpty()) {			
				for (Object[] tmp : listado) {
						DatosReasDTO datosSalida = new DatosReasDTO();
						
						datosSalida.setCodigo(String.valueOf(tmp[0]));
						datosSalida.setCodigoTaric(String.valueOf(tmp[1]));
						datosSalida.setNumeroProductos(Long.parseLong(String.valueOf(tmp[2])));
						
						resultList.add(datosSalida);
					}
				}
			
			
			result.setDatos(resultList);
			result.setMetadatos(metadatos);
			
		} catch (NumberFormatException nfe) {
			establecerSalidaError(nfe, "getReasSumario", "error.NumberFormatException");
			throw new ApplicationException(nfe.getMessage());
		} catch (Exception e) {
			establecerSalidaError(e, "getReasSumario", "error.Exception");
			throw new ApplicationException(e.getMessage());
		}
		
		return result;
	}
	
	@Override
	public boolean checkReaVariosTaric(String codigoRea) {
		
		Date fechaActual = new Date();
		
		Long cantidad = (Long) getEntityManager().createQuery("SELECT count(tr) from TaricsReaJPA tr WHERE tr.id.codigoRea = :codigoRea "
				+ "AND (tr.fechaFin IS NULL OR tr.fechaFin >= :fechaActual)")
				.setParameter("codigoRea", codigoRea).setParameter("fechaActual", fechaActual)
				.getSingleResult();
		
		if(cantidad == 1) {
			return true;
		}else {
			return false;
		}
	}

	  private void establecerSalidaError(Exception exception, String metodo, String codError) {
	
		    String login = this.securityService.getPrincipal().getLogin();
		    
		    this.logger.error("Error ejecutando la clase: GetReasSumarioDAOImpl",
		        new Object[] { metodo, login, ExceptionUtils.getStackTrace(exception) });
	  }




}
