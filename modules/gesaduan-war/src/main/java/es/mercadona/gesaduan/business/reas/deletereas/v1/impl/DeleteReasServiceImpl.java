package es.mercadona.gesaduan.business.reas.deletereas.v1.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.reas.deletereas.v1.DeleteReasService;
import es.mercadona.gesaduan.dao.reas.deletereas.v1.DeleteReasDAO;
import es.mercadona.gesaduan.dto.reas.common.v1.restfull.CodigoReaDTO;
import es.mercadona.gesaduan.dto.reas.common.v1.restfull.OutputPostReasDTO;
import es.mercadona.gesaduan.dto.reas.deletereas.v1.InputDeleteReasDTO;

public class DeleteReasServiceImpl implements DeleteReasService{

	@Inject
	private DeleteReasDAO deleteReasDao;
	
	@Override
	public OutputPostReasDTO deleteReas(InputDeleteReasDTO input) {
		
		OutputPostReasDTO datosSalida = new OutputPostReasDTO();
		CodigoReaDTO datos =  new CodigoReaDTO();
		
		deleteReasDao.deleteRea(input);
				
		datosSalida.setDatos(datos);
		
		HashMap<String, String> metadatosVacio = new HashMap<String,String>();
		datosSalida.setMetadatos(metadatosVacio);
		
		return datosSalida;
	}

}
