package es.mercadona.gesaduan.business.tarics.gettaricsdetalle.v1.impl;

import javax.inject.Inject;


import es.mercadona.gesaduan.business.tarics.gettaricsdetalle.v1.GetTaricsDetalleService;
import es.mercadona.gesaduan.dao.tarics.gettaricsdetalle.v1.GetTaricsDetalleDAO;
import es.mercadona.gesaduan.dto.tarics.gettaricsdetalle.v1.InputTaricsDetalleDTO;
import es.mercadona.gesaduan.dto.tarics.gettaricsdetalle.v1.restfull.OutputTaricsDetalleDTO;

public class GetTaricsDetalleServiceImpl implements GetTaricsDetalleService{
	
	@Inject
	private GetTaricsDetalleDAO getTaricsDetalleDao;

	@Override
	public OutputTaricsDetalleDTO getTaricsDetalle(InputTaricsDetalleDTO input) {
		
		OutputTaricsDetalleDTO result = this.getTaricsDetalleDao.getTaricsDetalle(input);
		
		
		return result;
	}

	@Override
	public boolean checkExistTaric(InputTaricsDetalleDTO input) {
		
		return this.getTaricsDetalleDao.checkExistTaric(input);
	}

}
