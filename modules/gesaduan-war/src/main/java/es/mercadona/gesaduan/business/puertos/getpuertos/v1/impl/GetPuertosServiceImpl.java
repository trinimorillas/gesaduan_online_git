package es.mercadona.gesaduan.business.puertos.getpuertos.v1.impl;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.puertos.getpuertos.v1.GetPuertosService;
import es.mercadona.gesaduan.dao.puertos.getpuertos.v1.GetPuertosDAO;
import es.mercadona.gesaduan.dto.puertos.getpuertos.v1.OutputPuertosDTO;

public class GetPuertosServiceImpl implements GetPuertosService {

	@Inject
	private GetPuertosDAO getPuertosDao;
	
	public OutputPuertosDTO listarPuertos() {
		
		OutputPuertosDTO result = getPuertosDao.listarPuertos();

		return result;
		
	}

}
