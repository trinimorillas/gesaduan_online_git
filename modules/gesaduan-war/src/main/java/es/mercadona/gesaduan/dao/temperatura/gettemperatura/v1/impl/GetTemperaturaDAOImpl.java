package es.mercadona.gesaduan.dao.temperatura.gettemperatura.v1.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.Query;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.gesaduan.dao.BaseDAO;
import es.mercadona.gesaduan.dao.temperatura.gettemperatura.v1.GetTemperaturaDAO;
import es.mercadona.gesaduan.dto.temperatura.gettemperatura.v1.DatosTemperaturaDTO;
import es.mercadona.gesaduan.dto.temperatura.gettemperatura.v1.OutputTemperaturaDTO;
import es.mercadona.gesaduan.jpa.temperatura.v1.TemperaturaJPA;

public class GetTemperaturaDAOImpl extends BaseDAO<TemperaturaJPA> implements GetTemperaturaDAO {

	@Inject
	private org.slf4j.Logger logger;	
	
	@Override
	public void setEntityClass() {
		this.entityClass = TemperaturaJPA.class;		
	}
	
	@Override
	public OutputTemperaturaDTO listarTemperatura() {
		
		OutputTemperaturaDTO result = new OutputTemperaturaDTO();
		List<DatosTemperaturaDTO> listaTemperatura = new ArrayList<>();
		
		try {		
		
			final StringBuilder sql = new StringBuilder();
			final StringBuilder sqlCount = new StringBuilder();
			
			String count = "SELECT COUNT(*) FROM (";
			String select = "SELECT ";		
			String campos = "T.COD_N_TEMPERATURA, T.TXT_TEMPERATURA ";
			String from = "FROM D_TEMPERATURA T";	
			String countFin = ")";
			
			sql.append(select).append(campos).append(from);
			sqlCount.append(count).append(select).append(campos).append(from).append(countFin);
	
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			final Query queryCount = getEntityManager().createNativeQuery(sqlCount.toString());
			
			@SuppressWarnings("unchecked")
			List<Object[]> listado = query.getResultList();
			
			if (listado != null && !listado.isEmpty()) {
				
				DatosTemperaturaDTO temperatura = null;
	
				for (Object[] tmp : listado) {
					temperatura = new DatosTemperaturaDTO();
					temperatura.setCodigoTemperatura(Integer.parseInt(String.valueOf(tmp[0])));
					temperatura.setValorTemperatura(String.valueOf(tmp[1]));
					listaTemperatura.add(temperatura);
				}	
			}
		

			Integer totalResults = Integer.parseInt(String.valueOf(queryCount.getSingleResult()));

			Map<String, String> mapaMetaData = new HashMap<String, String>();
			mapaMetaData.put("totalItemsCount", totalResults.toString());

			result.setMetadatos(mapaMetaData);
			result.setDatos(listaTemperatura);
			
		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","GetTemperaturaDAOImpl(GESADUAN)","listarTemperatura",e.getClass().getSimpleName(),e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}
		
		return result;
	}

}
