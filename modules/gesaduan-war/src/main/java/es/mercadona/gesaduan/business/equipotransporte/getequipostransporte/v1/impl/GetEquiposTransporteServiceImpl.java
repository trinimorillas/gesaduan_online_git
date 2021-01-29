package es.mercadona.gesaduan.business.equipotransporte.getequipostransporte.v1.impl;

import javax.inject.Inject;

import es.mercadona.fwk.core.web.BoPage;
import es.mercadona.gesaduan.business.equipotransporte.getequipostransporte.v1.GetEquiposTransporteService;
import es.mercadona.gesaduan.dao.equipotransporte.getequipostransporte.v1.GetEquiposTransporteDAO;
import es.mercadona.gesaduan.dto.equipotransporte.getequipostransporte.v1.InputDatosEquipoTransporteDTO;
import es.mercadona.gesaduan.dto.equipotransporte.getequipostransporte.v1.restfull.OutputEquiposTransporteDTO;

public class GetEquiposTransporteServiceImpl implements GetEquiposTransporteService {

	@Inject
	private GetEquiposTransporteDAO getEquiposTransporteDao;
	
	@Override
	public OutputEquiposTransporteDTO listarEquiposTransporte(InputDatosEquipoTransporteDTO input, BoPage pagination) {
		
		OutputEquiposTransporteDTO result = getEquiposTransporteDao.listarEquiposTransporte(input, pagination);

		return result;
		
	}

}
