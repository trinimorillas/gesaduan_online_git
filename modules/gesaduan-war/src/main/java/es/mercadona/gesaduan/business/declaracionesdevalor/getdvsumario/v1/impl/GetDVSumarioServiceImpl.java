package es.mercadona.gesaduan.business.declaracionesdevalor.getdvsumario.v1.impl;

import javax.inject.Inject;

import es.mercadona.fwk.core.web.BoPage;
import es.mercadona.gesaduan.business.declaracionesdevalor.getdvsumario.v1.GetDVSumarioService;
import es.mercadona.gesaduan.dao.declaracionesdevalor.getdvsumario.v1.GetDVSumarioDAO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.getdvsumario.v1.InputDeclaracionesDeValorDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.getdvsumario.v1.restfull.OutputDeclaracionesDeValorDTO;


public class GetDVSumarioServiceImpl implements GetDVSumarioService {

	@Inject
	private GetDVSumarioDAO getDVSumarioDAO;

	@Override
	public OutputDeclaracionesDeValorDTO getDeclaracionesDeValorList(InputDeclaracionesDeValorDTO bodata,
			BoPage paginacion) {

		OutputDeclaracionesDeValorDTO result = getDVSumarioDAO.obtenerDeclaraciones(bodata, paginacion);

		return result;

	}

}
