package es.mercadona.gesaduan.business.estadoequipo.getestadoequipo.v1.impl;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.estadoequipo.getestadoequipo.v1.GetEstadoEquipoService;
import es.mercadona.gesaduan.dao.estadoequipo.getestadoequipo.v1.GetEstadoEquipoDAO;
import es.mercadona.gesaduan.dto.estadoequipo.getestadoequipo.v1.OutputEstadoEquipoDTO;

public class GetEstadoEquipoServiceImpl implements GetEstadoEquipoService {

	@Inject
	private GetEstadoEquipoDAO getEstadoEquipoDao;
	
	public OutputEstadoEquipoDTO listarEstados() {
		
		OutputEstadoEquipoDTO result = getEstadoEquipoDao.listarEstados();

		return result;
		
	}

}
