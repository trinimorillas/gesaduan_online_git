package es.mercadona.gesaduan.dao.tiposuministro.gettiposuministro.v1.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.Query;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.gesaduan.dao.BaseDAO;
import es.mercadona.gesaduan.dao.tiposuministro.gettiposuministro.v1.GetTipoSuministroDAO;
import es.mercadona.gesaduan.dto.tiposuministro.gettiposuministro.v1.DatosTipoSuministroDTO;
import es.mercadona.gesaduan.dto.tiposuministro.gettiposuministro.v1.OutputTipoSuministroDTO;
import es.mercadona.gesaduan.jpa.tiposuministro.v1.TipoSuministroJPA;

public class GetTipoSuministroDAOImpl extends BaseDAO<TipoSuministroJPA> implements GetTipoSuministroDAO {

	@Inject
	private org.slf4j.Logger logger;		
	
	@Override
	public void setEntityClass() {
		this.entityClass = TipoSuministroJPA.class;		
	}
	
	@Override
	public OutputTipoSuministroDTO listarTipoSuministro() {
		
		OutputTipoSuministroDTO result = new OutputTipoSuministroDTO();
		List<DatosTipoSuministroDTO> listaTipoSuministro = new ArrayList<>();
		
		try {		
		
			final StringBuilder sql = new StringBuilder();
			final StringBuilder sqlCount = new StringBuilder();
			
			String count = "SELECT COUNT(*) FROM (";
			String select = "SELECT ";		
			String campos = "TS.COD_N_TIPO_SUMINISTRO, TS.TXT_NOMBRE_TIPO_SUMINISTRO ";
			String from = "FROM D_TIPO_SUMINISTRO TS ";	
			String countFin = ")";
			
			sql.append(select).append(campos).append(from);
			sqlCount.append(count).append(select).append(campos).append(from).append(countFin);
	
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			final Query queryCount = getEntityManager().createNativeQuery(sqlCount.toString());
			
			@SuppressWarnings("unchecked")
			List<Object[]> listado = query.getResultList();
			
			if (listado != null && !listado.isEmpty()) {
				
				DatosTipoSuministroDTO tipoSuministro = null;
	
				for (Object[] tmp : listado) {
					tipoSuministro = new DatosTipoSuministroDTO();
					tipoSuministro.setCodigoTipoSuministro(Integer.parseInt(String.valueOf(tmp[0])));
					tipoSuministro.setNombreTipoSuministro(String.valueOf(tmp[1]));
					listaTipoSuministro.add(tipoSuministro);
				}			
			}
		
			Integer totalResults = Integer.parseInt(String.valueOf(queryCount.getSingleResult()));

			Map<String, String> mapaMetaData = new HashMap<String, String>();
			mapaMetaData.put("totalItemsCount", totalResults.toString());

			result.setMetadatos(mapaMetaData);
			result.setDatos(listaTipoSuministro);
			
		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","GetTipoSuministroDAOImpl(GESADUAN)","listarTipoSuministro",e.getClass().getSimpleName(),e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}
		
		return result;
	}

}
