package es.mercadona.gesaduan.business.reas.getreasdetalle.v1.impl;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.reas.getreasdetalle.v1.GetReasDetalleService;
import es.mercadona.gesaduan.dao.reas.getreasdetalle.v1.GetReasDetalleDAO;
import es.mercadona.gesaduan.dto.reas.getreasdetalle.v1.InputReasDetalleDTO;
import es.mercadona.gesaduan.dto.reas.getreasdetalle.v1.restfull.OutputReasDetalleDTO;

public class GetReasDetalleServiceImpl implements GetReasDetalleService{

	@Inject
	private GetReasDetalleDAO getReasDetalleDao;
	
	@Override
	public OutputReasDetalleDTO getReasDetalle(InputReasDetalleDTO input) {
		
		OutputReasDetalleDTO result = this.getReasDetalleDao.getReasDetalle(input);
		
		return result;
	}

	@Override
	public boolean checkExistRea(InputReasDetalleDTO input) {

		return getReasDetalleDao.checkExistRea(input);
		
	}

}
