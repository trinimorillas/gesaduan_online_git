package es.mercadona.gesaduan.business.alertas.deletealertas.v1.impl;

import java.util.HashMap;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.alertas.deletealertas.v1.DeleteAlertasService;
import es.mercadona.gesaduan.dao.alertas.deletealertas.v1.DeleteAlertasDAO;
import es.mercadona.gesaduan.dto.alertas.deletealertas.v1.InputDeleteAlertasDTO;
import es.mercadona.gesaduan.dto.alertas.deletealertas.v1.restfull.OutputDeleteAlertasDTO;

public class DeleteAlertasServiceImpl implements DeleteAlertasService{
	
	@Inject
	private DeleteAlertasDAO deleteAlertasDao;

	@Override
	public OutputDeleteAlertasDTO eliminarAlertas(InputDeleteAlertasDTO input) {
		
		OutputDeleteAlertasDTO response = new OutputDeleteAlertasDTO();
		
		boolean esExpedicion = (input.getExpedicion() != null);
		if(esExpedicion) {
			deleteAlertasDao.eliminarAlertasExpedicion(input);
		}else {
			deleteAlertasDao.eliminarAlertas(input);
		}
		
		HashMap<String, String> metadatos = new HashMap<>();
		response.setMetadatos(metadatos);
		return response;
	}

}
