package es.mercadona.gesaduan.business.dosier.getdosierdetalle.v1.impl;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.dosier.getdosierdetalle.v1.GetDosierDetalleService;
import es.mercadona.gesaduan.dao.dosier.getdosierdetalle.v1.GetDosierDetalleDAO;
import es.mercadona.gesaduan.dto.dosier.getdosierdetalle.v1.InputDatosDetalleDTO;
import es.mercadona.gesaduan.dto.dosier.getdosierdetalle.v1.resfull.OutputDosierDetalleDTO;

public class GetDosierDetalleServiceImpl implements GetDosierDetalleService {

	@Inject
	private GetDosierDetalleDAO getDosierDetalleDao;
	
	@Override
	public OutputDosierDetalleDTO getDosierDetalle(InputDatosDetalleDTO input) {
		
		return getDosierDetalleDao.consultarDosier(input);
		
	}

}
	