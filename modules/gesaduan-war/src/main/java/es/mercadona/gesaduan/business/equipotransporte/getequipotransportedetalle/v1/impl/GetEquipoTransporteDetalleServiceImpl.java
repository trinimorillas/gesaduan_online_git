package es.mercadona.gesaduan.business.equipotransporte.getequipotransportedetalle.v1.impl;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.equipotransporte.getequipotransportedetalle.v1.GetEquipoTransporteDetalleService;
import es.mercadona.gesaduan.dao.equipotransporte.getequipotransportedetalle.v1.GetEquipoTransporteDetalleDAO;
import es.mercadona.gesaduan.dto.equipotransporte.getequipotransportedetalle.v1.InputEquipoTransporteDetalleDTO;
import es.mercadona.gesaduan.dto.equipotransporte.getequipotransportedetalle.v1.restfull.OutputEquipoTransporteDetalleDTO;

public class GetEquipoTransporteDetalleServiceImpl implements GetEquipoTransporteDetalleService {

	@Inject
	private GetEquipoTransporteDetalleDAO getEquipoTransporteDetalleDao;
	
	@Override
	public OutputEquipoTransporteDetalleDTO getEquipoTransporteDetalle(InputEquipoTransporteDetalleDTO input) {
		
		return getEquipoTransporteDetalleDao.consultarEquipoTransporte(input);
		
	}

}
