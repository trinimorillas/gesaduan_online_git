package es.mercadona.gesaduan.business.estadoplanembarque.getestadoplanembarque.v1.impl;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.estadoplanembarque.getestadoplanembarque.v1.GetEstadoPlanEmbarqueService;
import es.mercadona.gesaduan.dao.estadoplanembarque.getestadoplanembarque.v1.GetEstadoPlanEmbarqueDAO;
import es.mercadona.gesaduan.dto.estadoplanembarque.getestadoplanembarque.v1.OutputEstadoPlanEmbarqueDTO;

public class GetEstadoPlanEmbarqueServiceImpl implements GetEstadoPlanEmbarqueService {

	@Inject
	private GetEstadoPlanEmbarqueDAO getEstadosDao;
	
	public OutputEstadoPlanEmbarqueDTO listarEstados() {
		
		OutputEstadoPlanEmbarqueDTO result = getEstadosDao.listarEstados();

		return result;
		
	}

}
