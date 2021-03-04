package es.mercadona.gesaduan.dao.puertos.getpuertoagencia.v1.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.Query;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.gesaduan.dao.BaseDAO;
import es.mercadona.gesaduan.dao.puertos.getpuertoagencia.v1.GetPuertoAgenciaDAO;
import es.mercadona.gesaduan.dao.puertos.getpuertos.v1.GetPuertosDAO;
import es.mercadona.gesaduan.dto.puertos.getpuertoagencia.v1.InputDatosPuertoAgenciaDTO;
import es.mercadona.gesaduan.dto.puertos.getpuertoagencia.v1.restfull.DatosPuertoAgenciaDTO;
import es.mercadona.gesaduan.dto.puertos.getpuertoagencia.v1.restfull.OutputPuertoAgenciaDTO;
import es.mercadona.gesaduan.dto.puertos.getpuertos.v1.DatosPuertosDTO;
import es.mercadona.gesaduan.dto.puertos.getpuertos.v1.OutputPuertosDTO;
import es.mercadona.gesaduan.jpa.puertos.v1.PuertosJPA;

public class GetPuertoAgenciaDAOImpl extends BaseDAO<PuertosJPA> implements GetPuertoAgenciaDAO {

	@Inject
	private org.slf4j.Logger logger;	
	
	@Override
	public void setEntityClass() {
		this.entityClass = PuertosJPA.class;		
	}
	
	@Override
	public OutputPuertoAgenciaDTO listarPuertoAgencia(InputDatosPuertoAgenciaDTO input) {
		
		OutputPuertoAgenciaDTO result = new OutputPuertoAgenciaDTO();
		List<DatosPuertoAgenciaDTO> listaPuertoAgencia = new ArrayList<>();
		
		try {		
		
			final StringBuilder sql = new StringBuilder();
			final StringBuilder sqlCount = new StringBuilder();
			
			String count = "SELECT COUNT(*) FROM (";
			String select = "SELECT ";		
			String campos = "PA.COD_N_PUERTO, PU.TXT_NOMBRE_PUERTO, PA.COD_V_AGENCIA_ADUANA, PR.TXT_RAZON_SOCIAL ";
			String from = "FROM S_PUERTO_AGENCIA PA " +
						  "INNER JOIN D_PUERTO PU ON (PU.COD_N_PUERTO = PA.COD_N_PUERTO) " +
					      "INNER JOIN D_PROVEEDOR_R PR ON (PR.COD_N_PROVEEDOR=PA.COD_V_AGENCIA_ADUANA) ";	
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
