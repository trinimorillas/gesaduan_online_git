package es.mercadona.gesaduan.business.tarics.putreemplazartarics.v1.impl;

import java.util.HashMap;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.tarics.putreemplazartarics.v1.PutReemplazarTaricsService;
import es.mercadona.gesaduan.dao.tarics.putreemplazartarics.PutReemplazarTaricsDAO;
import es.mercadona.gesaduan.dto.tarics.putreemplazartaric.v1.restful.InputReemplazarTaricsDTO;
import es.mercadona.gesaduan.dto.tarics.putreemplazartaric.v1.restful.OutputReemplazarTaricsDTO;

public class PutReemplazarTaricsServiceImpl implements PutReemplazarTaricsService{
	
	
	@Inject
	private PutReemplazarTaricsDAO putReemplazarTaricsDao;
	

	@Override
	public OutputReemplazarTaricsDTO reemplazarTarics(InputReemplazarTaricsDTO input) {

			OutputReemplazarTaricsDTO response = new OutputReemplazarTaricsDTO();
		
			putReemplazarTaricsDao.reemplazarTarics(input);
			
			HashMap<String, String> metadatos = new HashMap<String, String>();
			response.setMetadatos(metadatos);
			
			return response;
	}

}
