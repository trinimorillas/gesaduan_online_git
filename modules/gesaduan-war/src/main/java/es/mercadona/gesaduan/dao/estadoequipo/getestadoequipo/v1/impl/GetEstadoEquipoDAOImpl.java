package es.mercadona.gesaduan.dao.estadoequipo.getestadoequipo.v1.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.Query;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.gesaduan.dao.BaseDAO;
import es.mercadona.gesaduan.dao.estadoequipo.getestadoequipo.v1.GetEstadoEquipoDAO;
import es.mercadona.gesaduan.dto.estadoequipo.getestadoequipo.v1.DatosEstadoEquipoDTO;
import es.mercadona.gesaduan.dto.estadoequipo.getestadoequipo.v1.OutputEstadoEquipoDTO;
import es.mercadona.gesaduan.jpa.estadoequipo.v1.EstadoEquipoJPA;

public class GetEstadoEquipoDAOImpl extends BaseDAO<EstadoEquipoJPA> implements GetEstadoEquipoDAO {

	@Inject
	private org.slf4j.Logger logger;	
	
	@Override
	public void setEntityClass() {
		this.entityClass = EstadoEquipoJPA.class;		
	}
	
	@Override
	public OutputEstadoEquipoDTO listarEstados() {
		
		OutputEstadoEquipoDTO result = new OutputEstadoEquipoDTO();
		List<DatosEstadoEquipoDTO> listaEstados = new ArrayList<>();
		
		try {		
		
			final StringBuilder sql = new StringBuilder();
			final StringBuilder sqlCount = new StringBuilder();
			
			String count = "SELECT COUNT(*) FROM (";
			String select = "SELECT ";		
			String campos = "E.COD_N_ESTADO, E.TXT_NOMBRE_ESTADO ";
			String from = "FROM D_ESTADO_EQUIPO E";	
			String countFin = ")";
			
			sql.append(select).append(campos).append(from);
			sqlCount.append(count).append(select).append(campos).append(from).append(countFin);
	
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			final Query queryCount = getEntityManager().createNativeQuery(sqlCount.toString());
			
			@SuppressWarnings("unchecked")
			List<Object[]> listado = query.getResultList();
			
			if (listado != null && !listado.isEmpty()) {
				
				DatosEstadoEquipoDTO estado = null;
	
				for (Object[] tmp : listado) {
					estado = new DatosEstadoEquipoDTO();
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
			this.logger.error("({}-{}) ERROR - {} {}","GetEstadoEquipoDAOImpl(GESADUAN)","listarEstados",e.getClass().getSimpleName(),e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}
		
		return result;
	}

}
