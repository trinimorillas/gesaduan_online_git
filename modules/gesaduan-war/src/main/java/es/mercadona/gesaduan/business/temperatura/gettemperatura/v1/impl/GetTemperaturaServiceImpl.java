package es.mercadona.gesaduan.business.temperatura.gettemperatura.v1.impl;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.temperatura.gettemperatura.v1.GetTemperaturaService;
import es.mercadona.gesaduan.dao.temperatura.gettemperatura.v1.GetTemperaturaDAO;
import es.mercadona.gesaduan.dto.temperatura.gettemperatura.v1.OutputTemperaturaDTO;

public class GetTemperaturaServiceImpl implements GetTemperaturaService {

	@Inject
	private GetTemperaturaDAO getTemperaturaDao;
	
	public OutputTemperaturaDTO listarTemperatura() {
		
		OutputTemperaturaDTO result = getTemperaturaDao.listarTemperatura();

		return result;
		
	}

}