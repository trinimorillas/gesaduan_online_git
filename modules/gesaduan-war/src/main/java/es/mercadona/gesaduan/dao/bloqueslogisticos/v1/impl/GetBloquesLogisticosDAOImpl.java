package es.mercadona.gesaduan.dao.bloqueslogisticos.v1.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.Query;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.gesaduan.dao.BaseDAO;
import es.mercadona.gesaduan.dao.bloqueslogisticos.v1.GetBloquesLogisticosDAO;
import es.mercadona.gesaduan.dto.bloqueslogisticos.getbloqueslogisticos.v1.CentroDTO;
import es.mercadona.gesaduan.dto.bloqueslogisticos.getbloqueslogisticos.v1.DatosBloquesLogisticosDTO;
import es.mercadona.gesaduan.dto.bloqueslogisticos.getbloqueslogisticos.v1.OutputBloquesLogisticosDTO;
import es.mercadona.gesaduan.jpa.bloqueslogisticos.v1.BloquesLogisticosJPA;

public class GetBloquesLogisticosDAOImpl extends BaseDAO<BloquesLogisticosJPA> implements GetBloquesLogisticosDAO {

	@Inject
	private org.slf4j.Logger logger;	
	
	@Override
	public void setEntityClass() {
		this.entityClass = BloquesLogisticosJPA.class;		
	}
	
	@Override
	public OutputBloquesLogisticosDTO listarBloquesLogisticos() {
		
		OutputBloquesLogisticosDTO result = new OutputBloquesLogisticosDTO();
		List<DatosBloquesLogisticosDTO> listaBloques = new ArrayList<>();
		
		final StringBuilder sql = new StringBuilder();
		
		try {		
		
			String select = "SELECT ";		
			String campos = "BL.COD_N_BLOQUE_LOGISTICO, BL.TXT_NOMBRE, C.COD_V_CENTRO ";
			String from = "FROM D_BLOQUE_LOGISTICO_R BL " + 
					"INNER JOIN S_BLOQUE_LOGISTICO_CENTRO_R C ON (C.COD_N_BLOQUE_LOGISTICO = BL.COD_N_BLOQUE_LOGISTICO)";	
		
			sql.append(select).append(campos).append(from);

			final Query query = getEntityManager().createNativeQuery(sql.toString());
		
			@SuppressWarnings("unchecked")
			List<Object[]> listado = query.getResultList();
		
			if (listado != null && !listado.isEmpty()) {
			
				Integer campoanterior1 = 0;
			
				List<CentroDTO> listaCentro = null;
				CentroDTO centro = null;
				DatosBloquesLogisticosDTO bloqueLogistico = null;

				for (Object[] tmp : listado) {
				
					if (!campoanterior1.equals(Integer.parseInt(String.valueOf(tmp[0])))) {
						bloqueLogistico = new DatosBloquesLogisticosDTO();
						bloqueLogistico.setCodigoBloqueLogistico(Integer.parseInt(String.valueOf(tmp[0])));
						bloqueLogistico.setNombreBloqueLogistico(String.valueOf(tmp[1]));
						listaBloques.add(bloqueLogistico);
						listaCentro = new ArrayList<>();
						bloqueLogistico.setCentro(listaCentro);
					}
				
					centro = new CentroDTO();
					centro.setCodigoCentro(String.valueOf(tmp[2]));
					listaCentro.add(centro);			
				
					campoanterior1 = Integer.parseInt(String.valueOf(tmp[0]));
				
				}		
			}
		

			Map<String, String> mapaMetaData = new HashMap<String, String>();
			mapaMetaData.put("totalItemsCount", String.valueOf(listaBloques.size()));

			result.setMetadatos(mapaMetaData);
			result.setDatos(listaBloques);
			
		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","GetBloquesLogisticosDAOImpl(GESADUAN)","listarBloquesLogisticos",e.getClass().getSimpleName(),e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}
		
		return result;
	}

}
