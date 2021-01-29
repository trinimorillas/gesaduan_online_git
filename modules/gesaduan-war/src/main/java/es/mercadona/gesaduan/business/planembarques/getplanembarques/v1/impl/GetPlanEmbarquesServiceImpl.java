package es.mercadona.gesaduan.business.planembarques.getplanembarques.v1.impl;

import javax.inject.Inject;

import es.mercadona.fwk.core.web.BoPage;
import es.mercadona.gesaduan.business.planembarques.getplanembarques.v1.GetPlanEmbarquesService;
import es.mercadona.gesaduan.dao.planembarques.getplanembarques.v1.GetPlanEmbarquesDAO;
import es.mercadona.gesaduan.dto.planembarques.getplanembarques.v1.InputPlanEmbarquesDTO;
import es.mercadona.gesaduan.dto.planembarques.getplanembarques.v1.restfull.OutputPlanEmbarquesDTO;

public class GetPlanEmbarquesServiceImpl implements GetPlanEmbarquesService {

	@Inject
	private GetPlanEmbarquesDAO getPlanEmbarquesDao;
	
	@Override
	public OutputPlanEmbarquesDTO listarPlanEmbarques(InputPlanEmbarquesDTO input, BoPage pagination) {
		
		OutputPlanEmbarquesDTO result = getPlanEmbarquesDao.listarPlanEmbarques(input, pagination);

		return result;
		
	}

}
