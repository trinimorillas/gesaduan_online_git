package es.mercadona.gesaduan.business.equipotransporte.getcontenedores.v1.impl;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.equipotransporte.getcontenedores.v1.GetContenedoresService;
import es.mercadona.gesaduan.dao.equipotransporte.getcontenedores.v1.GetContenedoresDAO;
import es.mercadona.gesaduan.dto.equipotransporte.getcontenedores.v1.InputDatosGetContenedorDTO;
import es.mercadona.gesaduan.dto.equipotransporte.getcontenedores.v1.restfull.OutputGetContenedoresDTO;

public class GetContenedoresServiceImpl implements GetContenedoresService {

	@Inject
	private GetContenedoresDAO getContenedoresDao;
	
	@Override
	public OutputGetContenedoresDTO listarContenedores(InputDatosGetContenedorDTO datos) {
		
		OutputGetContenedoresDTO result = getContenedoresDao.listarContenedores(datos);

		return result;
		
	}

}
