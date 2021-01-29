package es.mercadona.gesaduan.business.bloqueslogisticos.getbloqueslogisticos.v1.impl;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.bloqueslogisticos.getbloqueslogisticos.v1.GetBloquesLogisticosService;
import es.mercadona.gesaduan.dao.bloqueslogisticos.v1.GetBloquesLogisticosDAO;
import es.mercadona.gesaduan.dto.bloqueslogisticos.getbloqueslogisticos.v1.OutputBloquesLogisticosDTO;

public class GetBloquesLogisticosServiceImpl implements GetBloquesLogisticosService {

	@Inject
	private GetBloquesLogisticosDAO getBloquesLogisticosDao;
	
	public OutputBloquesLogisticosDTO listarBloquesLogisticos() {
		
		OutputBloquesLogisticosDTO result = getBloquesLogisticosDao.listarBloquesLogisticos();

		return result;
		
	}

}
