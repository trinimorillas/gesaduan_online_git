package es.mercadona.gesaduan.business.reas.getreas.v1.impl;

import javax.inject.Inject;

import es.mercadona.fwk.core.web.BoPage;
import es.mercadona.gesaduan.business.reas.getreas.v1.GetReasSumarioService;
import es.mercadona.gesaduan.dao.reas.getreas.v1.GetReasSumarioDAO;
import es.mercadona.gesaduan.dto.reas.getreas.v1.InputReasDTO;
import es.mercadona.gesaduan.dto.reas.getreas.v1.restfull.OutputReasDTO;

public class GetReasSumarioServiceImpl implements GetReasSumarioService{
	
	@Inject
	private GetReasSumarioDAO getReasSumarioDao;

	@Override
	public OutputReasDTO getReasSumario(InputReasDTO input, BoPage pagination) {

		OutputReasDTO result = this.getReasSumarioDao.getReasSumario(input, pagination);
		
		return result;
	}

}
