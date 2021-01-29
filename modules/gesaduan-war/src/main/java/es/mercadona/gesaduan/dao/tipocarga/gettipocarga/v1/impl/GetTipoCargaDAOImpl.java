package es.mercadona.gesaduan.dao.tipocarga.gettipocarga.v1.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.Query;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.gesaduan.dao.BaseDAO;
import es.mercadona.gesaduan.dao.tipocarga.gettipocarga.v1.GetTipoCargaDAO;
import es.mercadona.gesaduan.dto.tipocarga.gettipocarga.v1.DatosTipoCargaDTO;
import es.mercadona.gesaduan.dto.tipocarga.gettipocarga.v1.OutputTipoCargaDTO;
import es.mercadona.gesaduan.jpa.tipocarga.v1.TipoCargaJPA;

public class GetTipoCargaDAOImpl extends BaseDAO<TipoCargaJPA> implements GetTipoCargaDAO {

	@Inject
	private org.slf4j.Logger logger;		
	
	@Override
	public void setEntityClass() {
		this.entityClass = TipoCargaJPA.class;		
	}
	
	@Override
	public OutputTipoCargaDTO listarTipoCarga() {
		
		OutputTipoCargaDTO result = new OutputTipoCargaDTO();
		List<DatosTipoCargaDTO> listaTipoCarga = new ArrayList<>();
		
		try {		
		
			final StringBuilder sql = new StringBuilder();
			final StringBuilder sqlCount = new StringBuilder();
			
			String count = "SELECT COUNT(*) FROM (";
			String select = "SELECT ";		
			String campos = "TP.COD_N_TIPO_CARGA, TP.TXT_NOMBRE_TIPO_CARGA ";
			String from = "FROM D_TIPO_CARGA TP ";	
			String countFin = ")";
			
			sql.append(select).append(campos).append(from);
			sqlCount.append(count).append(select).append(campos).append(from).append(countFin);
	
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			final Query queryCount = getEntityManager().createNativeQuery(sqlCount.toString());
			
			@SuppressWarnings("unchecked")
			List<Object[]> listado = query.getResultList();
			
			if (listado != null && !listado.isEmpty()) {
				
				DatosTipoCargaDTO tipoCarga = null;
	
				for (Object[] tmp : listado) {
					tipoCarga = new DatosTipoCargaDTO();
					tipoCarga.setCodigoTipoCarga(Integer.parseInt(String.valueOf(tmp[0])));
					tipoCarga.setNombreTipoCarga(String.valueOf(tmp[1]));
					listaTipoCarga.add(tipoCarga);
				}			
			}

			Integer totalResults = Integer.parseInt(String.valueOf(queryCount.getSingleResult()));

			Map<String, String> mapaMetaData = new HashMap<String, String>();
			mapaMetaData.put("totalItemsCount", totalResults.toString());

			result.setMetadatos(mapaMetaData);
			result.setDatos(listaTipoCarga);
			
		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","GetTipoCargaDAOImpl(GESADUAN)","listarTipoCarga",e.getClass().getSimpleName(),e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}
		
		return result;
	}

}
