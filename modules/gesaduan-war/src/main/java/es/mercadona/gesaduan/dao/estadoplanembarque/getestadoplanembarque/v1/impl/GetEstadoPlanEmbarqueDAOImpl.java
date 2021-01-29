package es.mercadona.gesaduan.dao.estadoplanembarque.getestadoplanembarque.v1.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.Query;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.gesaduan.dao.BaseDAO;
import es.mercadona.gesaduan.dao.estadoplanembarque.getestadoplanembarque.v1.GetEstadoPlanEmbarqueDAO;
import es.mercadona.gesaduan.dto.estadoplanembarque.getestadoplanembarque.v1.DatosEstadoPlanEmbarqueDTO;
import es.mercadona.gesaduan.dto.estadoplanembarque.getestadoplanembarque.v1.OutputEstadoPlanEmbarqueDTO;
import es.mercadona.gesaduan.jpa.estadoplanembarque.v1.EstadoPlanEmbarqueJPA;

public class GetEstadoPlanEmbarqueDAOImpl extends BaseDAO<EstadoPlanEmbarqueJPA> implements GetEstadoPlanEmbarqueDAO {

	@Inject
	private org.slf4j.Logger logger;		
	
	@Override
	public void setEntityClass() {
		this.entityClass = EstadoPlanEmbarqueJPA.class;		
	}
	
	@Override
	public OutputEstadoPlanEmbarqueDTO listarEstados() {
		
		OutputEstadoPlanEmbarqueDTO result = new OutputEstadoPlanEmbarqueDTO();
		List<DatosEstadoPlanEmbarqueDTO> listaEstados = new ArrayList<>();
		
		try {		
		
			final StringBuilder sql = new StringBuilder();
			final StringBuilder sqlCount = new StringBuilder();
			
			String count = "SELECT COUNT(*) FROM (";
			String select = "SELECT ";		
			String campos = "E.COD_N_ESTADO, E.TXT_NOMBRE_ESTADO ";
			String from = "FROM D_ESTADO_PLAN_EMBARQUE E";	
			String countFin = ")";
			
			sql.append(select).append(campos).append(from);
			sqlCount.append(count).append(select).append(campos).append(from).append(countFin);
	
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			final Query queryCount = getEntityManager().createNativeQuery(sqlCount.toString());
			
			@SuppressWarnings("unchecked")
			List<Object[]> listado = query.getResultList();
			
			if (listado != null && !listado.isEmpty()) {
				
				DatosEstadoPlanEmbarqueDTO estado = null;
	
				for (Object[] tmp : listado) {
					estado = new DatosEstadoPlanEmbarqueDTO();
					estado.setCodigoEstado(Integer.parseInt(String.valueOf(tmp[0])));
					estado.setNombreEstado(String.valueOf(tmp[1]));
					listaEstados.add(estado);
				}		
			}

			Integer totalResults = Integer.parseInt(String.valueOf(queryCount.getSingleResult()));

			Map<String, String> mapaMetaData = new HashMap<String, String>();
			mapaMetaData.put("totalItemsCount", totalResults.toString());

			result.setMetadatos(mapaMetaData);
			result.setDatos(listaEstados);
			
		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","GetEstadoPlanEmbarqueDAOImpl(GESADUAN)","listarEstados",e.getClass().getSimpleName(),e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}
		
		return result;
	}

}
