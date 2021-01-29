package es.mercadona.gesaduan.dao.puertos.getpuertos.v1.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.Query;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.gesaduan.dao.BaseDAO;
import es.mercadona.gesaduan.dao.puertos.getpuertos.v1.GetPuertosDAO;
import es.mercadona.gesaduan.dto.puertos.getpuertos.v1.DatosPuertosDTO;
import es.mercadona.gesaduan.dto.puertos.getpuertos.v1.OutputPuertosDTO;
import es.mercadona.gesaduan.jpa.puertos.v1.PuertosJPA;

public class GetPuertosDAOImpl extends BaseDAO<PuertosJPA> implements GetPuertosDAO {

	@Inject
	private org.slf4j.Logger logger;	
	
	@Override
	public void setEntityClass() {
		this.entityClass = PuertosJPA.class;		
	}
	
	@Override
	public OutputPuertosDTO listarPuertos() {
		
		OutputPuertosDTO result = new OutputPuertosDTO();
		List<DatosPuertosDTO> listaPuertos = new ArrayList<>();
		
		try {		
		
			final StringBuilder sql = new StringBuilder();
			final StringBuilder sqlCount = new StringBuilder();
			
			String count = "SELECT COUNT(*) FROM (";
			String select = "SELECT ";		
			String campos = "P.COD_N_PUERTO, P.TXT_NOMBRE_PUERTO ";
			String from = "FROM D_PUERTO P";	
			String countFin = ")";
			
			sql.append(select).append(campos).append(from);
			sqlCount.append(count).append(select).append(campos).append(from).append(countFin);
	
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			final Query queryCount = getEntityManager().createNativeQuery(sqlCount.toString());
			
			@SuppressWarnings("unchecked")
			List<Object[]> listado = query.getResultList();
			
			if (listado != null && !listado.isEmpty()) {
				
				DatosPuertosDTO puerto = null;
	
				for (Object[] tmp : listado) {
					puerto = new DatosPuertosDTO();
					puerto.setCodigoPuerto(Integer.parseInt(String.valueOf(tmp[0])));
					puerto.setNombrePuerto(String.valueOf(tmp[1]));
					listaPuertos.add(puerto);
				}		
			}
		

			Integer totalResults = Integer.parseInt(String.valueOf(queryCount.getSingleResult()));

			Map<String, String> mapaMetaData = new HashMap<String, String>();
			mapaMetaData.put("totalItemsCount", totalResults.toString());

			result.setMetadatos(mapaMetaData);
			result.setDatos(listaPuertos);
			
		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","GetPuertosDAOImpl(GESADUAN)","listarPuertos",e.getClass().getSimpleName(),e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}
		
		return result;
	}

}
