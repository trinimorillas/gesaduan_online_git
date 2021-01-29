package es.mercadona.gesaduan.business.tarics.gettarics.v1.impl;

import javax.inject.Inject;

import es.mercadona.fwk.core.web.BoPage;
import es.mercadona.gesaduan.business.tarics.gettarics.v1.GetTaricsSumarioService;
import es.mercadona.gesaduan.dao.tarics.gettarics.v1.GetTaricsSumarioDAO;
import es.mercadona.gesaduan.dto.tarics.common.v1.restfull.OutputTaricsDTO;
import es.mercadona.gesaduan.dto.tarics.gettarics.v1.restful.InputTaricsDTO;

public class GetTaricsSumarioServiceImpl implements GetTaricsSumarioService{
	
	@Inject
	private GetTaricsSumarioDAO getTaricsSumarioDao;
	
	@Override
	public OutputTaricsDTO getSumarioTarics(InputTaricsDTO input, BoPage pagination) {

		OutputTaricsDTO result = getTaricsSumarioDao.getTaricsSumario(input, pagination);
				
		return result;
	}

	@Override
	public boolean checkExistTaricAlerta(Long codigoTaric) {
		
		return getTaricsSumarioDao.checkExistTaricAlerta(codigoTaric);
		
	}

	@Override
	public boolean checkExisteRelacionTaricProd(Long codigoTaric) {
		
		return getTaricsSumarioDao.checkExisteRelacionTaricProd(codigoTaric);
		
	}
	
	

}
