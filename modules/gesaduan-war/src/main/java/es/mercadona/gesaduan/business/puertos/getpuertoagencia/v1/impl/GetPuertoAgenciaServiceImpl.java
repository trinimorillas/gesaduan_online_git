package es.mercadona.gesaduan.business.puertos.getpuertoagencia.v1.impl;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.puertos.getpuertoagencia.v1.GetPuertoAgenciaService;
import es.mercadona.gesaduan.dao.puertos.getpuertoagencia.v1.GetPuertoAgenciaDAO;
import es.mercadona.gesaduan.dto.puertos.getpuertoagencia.v1.InputPuertoAgenciaDTO;
import es.mercadona.gesaduan.dto.puertos.getpuertoagencia.v1.restfull.OutputPuertoAgenciaDTO;

public class GetPuertoAgenciaServiceImpl implements GetPuertoAgenciaService {

	@Inject
	private GetPuertoAgenciaDAO getPuertoAgenciaDao;
	
	public OutputPuertoAgenciaDTO listarPuertoAgencia(InputPuertoAgenciaDTO datos) {
		OutputPuertoAgenciaDTO result = null;
		
		if (datos.getCodigoAgencia() != null) result = getPuertoAgenciaDao.listarPuertos(datos);
		else result = getPuertoAgenciaDao.listarAgencias(datos);
		
		return result;
	}

}
