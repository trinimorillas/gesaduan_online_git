package es.mercadona.gesaduan.business.tarics.deletetaricrea.v1.impl;

import java.util.HashMap;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.tarics.deletetaricrea.v1.DeleteTaricReaService;
import es.mercadona.gesaduan.dao.reas.deletereas.v1.DeleteReasDAO;
import es.mercadona.gesaduan.dao.reas.getreas.v1.GetReasSumarioDAO;
import es.mercadona.gesaduan.dao.tarics.deletetaricrea.v1.DeleteTaricReaDAO;
import es.mercadona.gesaduan.dto.tarics.deletetaricrea.v1.InputDeleteTaricReaDTO;
import es.mercadona.gesaduan.dto.tarics.deletetaricrea.v1.restfull.DatosDeleteTaricReaDTO;
import es.mercadona.gesaduan.dto.tarics.deletetaricrea.v1.restfull.OutputDeleteTaricReaDTO;

public class DeleteTaricReaServiceImpl implements DeleteTaricReaService{

	@Inject
	DeleteTaricReaDAO deleteTaricReaDao;
	
	@Inject
	DeleteReasDAO deleteReasDao;
	
	@Inject
	GetReasSumarioDAO getReasSUmarioDao;
	
	@Override
	public OutputDeleteTaricReaDTO eliminarRelacion(InputDeleteTaricReaDTO input) {

		OutputDeleteTaricReaDTO response = new OutputDeleteTaricReaDTO();
		DatosDeleteTaricReaDTO datos = new DatosDeleteTaricReaDTO();
		
		String codigoRea = input.getCodigoRea();
		String codigoUsuario = input.getMetadatos().getCodigoUsuario().toUpperCase();
		
		datos.setCodigoRea(codigoRea);
		datos.setCodigoTaric(input.getCodigoTaric());
		response.setDatos(datos);
		HashMap<String, String> metadatos = new HashMap<String, String>();
		response.setMetadatos(metadatos);
		
		/* Comprobamos si el rea esta asociado a un solo Taric, se es asi, eliminamos las relaciones del Rea con sus productos */
		if(getReasSUmarioDao.checkReaVariosTaric(codigoRea)) {
			deleteReasDao.desasociarReaProductos(codigoRea, codigoUsuario);
		}
		
		deleteTaricReaDao.eliminarRelacion(input);
		
		return response;
	}

}
