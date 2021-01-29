package es.mercadona.gesaduan.dao.centros.getcentros.v1.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.Query;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.fwk.core.web.BoPage;
import es.mercadona.gesaduan.dao.BaseDAO;
import es.mercadona.gesaduan.dao.centros.getcentros.v1.GetCentrosDAO;
import es.mercadona.gesaduan.dto.centros.getcentros.v1.InputCentrosDTO;
import es.mercadona.gesaduan.dto.centros.getcentros.v1.restfull.DatosCentrosDTO;
import es.mercadona.gesaduan.dto.centros.getcentros.v1.restfull.OutputCentrosDTO;
import es.mercadona.gesaduan.jpa.centros.v1.CentrosJPA;

public class GetCentrosDAOImpl extends BaseDAO<CentrosJPA> implements GetCentrosDAO {

	@Inject
	private org.slf4j.Logger logger;	
	
	@Override
	public void setEntityClass() {
		this.entityClass = CentrosJPA.class;		
	}
	
	@Override
	public OutputCentrosDTO listarCentros(InputCentrosDTO input, BoPage pagination) {
		
		OutputCentrosDTO result = new OutputCentrosDTO();
		List<DatosCentrosDTO> listaCentros = new ArrayList<>();
		
		try {		
		
			String codigoCentro = null;
			if (input.getCodigoCentro() != null)
				codigoCentro = input.getCodigoCentro();
			
			Integer paginaInicio = null;
			if (pagination.getPage() != null)
				paginaInicio = pagination.getPage().intValue();
			
			Integer paginaTamanyo = null;
			if (pagination.getLimit() != null)
				paginaTamanyo = pagination.getLimit().intValue();
			
			final StringBuilder sql = new StringBuilder();
			final StringBuilder sqlCount = new StringBuilder();
			
			String count = "SELECT COUNT(*) FROM (";
			String select = "SELECT ";		
			String campos = "C.COD_V_CENTRO, C.TXT_NOMBRE_LARGO ";
			String from = "FROM D_CENTRO_R C ";
			String where = "WHERE 1 = 1";
			if (codigoCentro != null) where += " AND C.COD_V_CENTRO LIKE (?codigoCentro ||'%')";		
			
			String countFin = ")";
			
			sql.append(select).append(campos).append(from).append(where);
			sqlCount.append(count).append(select).append(campos).append(from).append(where).append(countFin);
	
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			final Query queryCount = getEntityManager().createNativeQuery(sqlCount.toString());
			
			if(codigoCentro != null) {
				query.setParameter("codigoCentro", codigoCentro);
				queryCount.setParameter("codigoCentro", codigoCentro);
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
				
				DatosCentrosDTO centro = null;
	
				for (Object[] tmp : listado) {
					centro = new DatosCentrosDTO();
					centro.setCodigoCentro(String.valueOf(tmp[0]));
					centro.setNombreLargo(String.valueOf(tmp[1]));
					listaCentros.add(centro);
				}
			}

			Integer totalResults = Integer.parseInt(String.valueOf(queryCount.getSingleResult()));

			Map<String, String> mapaMetaData = new HashMap<String, String>();
			mapaMetaData.put("totalItemsCount", totalResults.toString());

			result.setMetadatos(mapaMetaData);
			result.setDatos(listaCentros);
			
		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","GetCentrosDAOImpl(GESADUAN)","listarCentros",e.getClass().getSimpleName(),e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}
		
		return result;
	}

}
