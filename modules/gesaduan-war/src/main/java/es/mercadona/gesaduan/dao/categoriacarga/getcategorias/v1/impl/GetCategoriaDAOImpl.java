package es.mercadona.gesaduan.dao.categoriacarga.getcategorias.v1.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.Query;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.gesaduan.dao.BaseDAO;
import es.mercadona.gesaduan.dao.categoriacarga.getcategorias.v1.GetCategoriaDAO;
import es.mercadona.gesaduan.dto.categoriacarga.getcategorias.v1.DatosCategoriaDTO;
import es.mercadona.gesaduan.dto.categoriacarga.getcategorias.v1.OutputCategoriaDTO;
import es.mercadona.gesaduan.jpa.categoriacarga.v1.CategoriaCargaJPA;

public class GetCategoriaDAOImpl extends BaseDAO<CategoriaCargaJPA> implements GetCategoriaDAO {

	@Inject
	private org.slf4j.Logger logger;	
	
	@Override
	public void setEntityClass() {
		this.entityClass = CategoriaCargaJPA.class;		
	}
	
	@Override
	public OutputCategoriaDTO listarCategoria() {
		
		OutputCategoriaDTO result = new OutputCategoriaDTO();
		List<DatosCategoriaDTO> listaCategorias = new ArrayList<>();
		
		final StringBuilder sql = new StringBuilder();
		final StringBuilder sqlCount = new StringBuilder();
		
		try {
		
			String count = "SELECT COUNT(*) FROM (";
			String select = "SELECT ";		
			String campos = "C.COD_N_CATEGORIA, C.TXT_NOMBRE_CATEGORIA ";
			String from = "FROM D_CATEGORIA_CARGA C";	
			String countFin = ")";
		
			sql.append(select).append(campos).append(from);
			sqlCount.append(count).append(select).append(campos).append(from).append(countFin);

			final Query query = getEntityManager().createNativeQuery(sql.toString());
			final Query queryCount = getEntityManager().createNativeQuery(sqlCount.toString());
		
			@SuppressWarnings("unchecked")
			List<Object[]> listado = query.getResultList();
		
			if (listado != null && !listado.isEmpty()) {
			
				DatosCategoriaDTO categoria = null;

				for (Object[] tmp : listado) {
					categoria = new DatosCategoriaDTO();
					categoria.setCodigoCategoriaCarga(Integer.parseInt(String.valueOf(tmp[0])));
					categoria.setNombreCategoriaCarga(String.valueOf(tmp[1]));
					listaCategorias.add(categoria);
				}		
			}
		
	
			Integer totalResults = Integer.parseInt(String.valueOf(queryCount.getSingleResult()));

			Map<String, String> mapaMetaData = new HashMap<String, String>();
			mapaMetaData.put("totalItemsCount", totalResults.toString());

			result.setMetadatos(mapaMetaData);
			result.setDatos(listaCategorias);
			
		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","GetCategoriaDAOImpl(GESADUAN)","listarCategoria",e.getClass().getSimpleName(),e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}
		
		return result;
	}

}
