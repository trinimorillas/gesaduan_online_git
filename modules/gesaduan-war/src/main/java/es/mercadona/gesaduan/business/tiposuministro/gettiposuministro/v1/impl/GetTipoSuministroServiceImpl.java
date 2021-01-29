package es.mercadona.gesaduan.business.tiposuministro.gettiposuministro.v1.impl;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.tiposuministro.gettiposuministro.v1.GetTipoSuministroService;
import es.mercadona.gesaduan.dao.tiposuministro.gettiposuministro.v1.GetTipoSuministroDAO;
import es.mercadona.gesaduan.dto.tiposuministro.gettiposuministro.v1.OutputTipoSuministroDTO;

public class GetTipoSuministroServiceImpl implements GetTipoSuministroService {

	@Inject
	private GetTipoSuministroDAO getTipoSuministroDao;
	
	public OutputTipoSuministroDTO listarTipoSuministro() {
		
		OutputTipoSuministroDTO result = getTipoSuministroDao.listarTipoSuministro();

		return result;
		
	}

}