package es.mercadona.gesaduan.business.tarics.posttaricrea.v1.impl;

import java.util.HashMap;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.tarics.posttaricrea.v1.PostTaricReaService;
import es.mercadona.gesaduan.dao.tarics.posttaricrea.v1.PostTaricReaDAO;
import es.mercadona.gesaduan.dto.tarics.posttaricrea.v1.InputTaricReaDTO;
import es.mercadona.gesaduan.dto.tarics.posttaricrea.v1.restfull.DatosTaricReaDTO;
import es.mercadona.gesaduan.dto.tarics.posttaricrea.v1.restfull.OutputTaricReaDTO;

public class PostTaricReaServiceImpl implements PostTaricReaService{

	@Inject
	private PostTaricReaDAO postTaricReaDao;
	
	@Override
	public OutputTaricReaDTO crearRelacion(InputTaricReaDTO input) {
		
		OutputTaricReaDTO response = new OutputTaricReaDTO();
		DatosTaricReaDTO datos = new DatosTaricReaDTO();
		datos.setCodigoRea(input.getCodigoRea());
		datos.setCodigoTaric(input.getCodigoTaric());
		response.setDatos(datos);
		HashMap<String, String> metadatos = new HashMap<String, String>();
		response.setMetadatos(metadatos);
		
		postTaricReaDao.crearRelacion(input);
				
		return response;
	}

}
