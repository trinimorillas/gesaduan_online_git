package es.mercadona.gesaduan.business.cargas.getcargas.v1.impl;

import javax.inject.Inject;

import es.mercadona.fwk.core.web.BoPage;
import es.mercadona.gesaduan.business.cargas.getcargas.v1.GetCargasService;
import es.mercadona.gesaduan.dao.cargas.getcargas.v1.GetCargasDAO;
import es.mercadona.gesaduan.dto.cargas.getcargas.v1.InputDatosCargasDTO;
import es.mercadona.gesaduan.dto.cargas.getcargas.v1.restfull.OutputCargasDTO;

public class GetCargasServiceImpl implements GetCargasService {
	@Inject
	private GetCargasDAO getCargasDao;
	
	@Override
	public OutputCargasDTO listarCargas(InputDatosCargasDTO input, BoPage pagination) {		
		return getCargasDao.listarCargas(input, pagination);
	}
}