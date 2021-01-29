package es.mercadona.gesaduan.business.estadocarga.getestadocarga.v1.impl;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.estadocarga.getestadocarga.v1.GetEstadoCargaService;
import es.mercadona.gesaduan.dao.estadocarga.getestadocarga.v1.GetEstadoCargaDAO;
import es.mercadona.gesaduan.dto.estadocarga.getestadocarga.v1.OutputEstadoCargaDTO;

public class GetEstadoCargaServiceImpl implements GetEstadoCargaService {

	@Inject
	private GetEstadoCargaDAO getEstadoCargaDao;
	
	public OutputEstadoCargaDTO listarEstados() {
		
		OutputEstadoCargaDTO result = getEstadoCargaDao.listarEstados();

		return result;
		
	}

}
