package es.mercadona.gesaduan.business.centros.getcentros.v1.impl;

import javax.inject.Inject;

import es.mercadona.fwk.core.web.BoPage;
import es.mercadona.gesaduan.business.centros.getcentros.v1.GetCentrosService;
import es.mercadona.gesaduan.dao.centros.getcentros.v1.GetCentrosDAO;
import es.mercadona.gesaduan.dto.centros.getcentros.v1.InputCentrosDTO;
import es.mercadona.gesaduan.dto.centros.getcentros.v1.restfull.OutputCentrosDTO;

public class GetCentrosServiceImpl implements GetCentrosService {

	@Inject
	private GetCentrosDAO getCentrosDao;
	
	public OutputCentrosDTO listarCentros(InputCentrosDTO input, BoPage pagination) {
		
		OutputCentrosDTO result = getCentrosDao.listarCentros(input, pagination);

		return result;
		
	}

}