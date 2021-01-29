package es.mercadona.gesaduan.business.alertas.putalertas.v1.impl;

import java.util.HashMap;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.alertas.putalertas.v1.PutAlertasService;
import es.mercadona.gesaduan.dao.alertas.putalertas.v1.PutAlertasDAO;
import es.mercadona.gesaduan.dto.alertas.putalertas.v1.InputPutAlertasDTO;
import es.mercadona.gesaduan.dto.alertas.putalertas.v1.restfull.OutputPutAlertasDTO;

public class PutAlertasServiceImpl implements PutAlertasService{
	
	@Inject
	PutAlertasDAO putAlertasDao;

	@Override
	public OutputPutAlertasDTO editarAlertas(InputPutAlertasDTO input) {
		
		OutputPutAlertasDTO response = new OutputPutAlertasDTO();
		
		boolean esExpedicion = (input.getDatos().getExpedicion() != null) ? true : false;
		
		if(esExpedicion) {
			putAlertasDao.editarAlertasExpedicion(input);
		}else {
			putAlertasDao.editarAlertas(input);
		}
		
		HashMap<String, String> metadatos = new HashMap<String, String>();
		response.setMetadatos(metadatos);
		return response;
		
	}

}
