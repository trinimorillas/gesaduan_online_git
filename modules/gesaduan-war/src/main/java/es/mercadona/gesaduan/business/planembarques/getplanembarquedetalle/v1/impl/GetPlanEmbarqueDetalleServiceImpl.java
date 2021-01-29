package es.mercadona.gesaduan.business.planembarques.getplanembarquedetalle.v1.impl;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.planembarques.getplanembarquedetalle.v1.GetPlanEmbarqueDetalleService;
import es.mercadona.gesaduan.dao.planembarques.getplanembarquedetalle.v1.GetPlanEmbarqueDetalleDAO;
import es.mercadona.gesaduan.dto.planembarques.getplanembarquedetalle.v1.InputDatosDetalleDTO;
import es.mercadona.gesaduan.dto.planembarques.getplanembarquedetalle.v1.restfull.OutputPlanEmbarqueDetalleDTO;

public class GetPlanEmbarqueDetalleServiceImpl implements GetPlanEmbarqueDetalleService {

	@Inject
	private GetPlanEmbarqueDetalleDAO getPlanEmbarqueDetalleDao;
	
	@Override
	public OutputPlanEmbarqueDetalleDTO getPlanEmbarqueDetalle(InputDatosDetalleDTO input) {
		
		OutputPlanEmbarqueDetalleDTO result = getPlanEmbarqueDetalleDao.consultarPlanEmbarque(input);

		return result;
		
	}

}
