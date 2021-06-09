package es.mercadona.gesaduan.business.declaracionesdevalorapi.getvdsumary.v1.impl;

import javax.inject.Inject;

import es.mercadona.fwk.core.web.BoPage;
import es.mercadona.gesaduan.business.declaracionesdevalor.getdvsumario.v1.GetDVSumarioService;
import es.mercadona.gesaduan.business.declaracionesdevalorapi.getvdsumary.v1.GetVDSumaryService;
import es.mercadona.gesaduan.dao.declaracionesdevalor.getdvsumario.v1.GetDVSumarioDAO;
import es.mercadona.gesaduan.dao.declaracionesdevalorapi.getvdsumary.v1.GetVDSumaryDAO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.getdvsumario.v1.InputDeclaracionesDeValorDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.getdvsumario.v1.restfull.OutputDeclaracionesDeValorDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvdsumary.v1.InputVDSumaryDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvdsumary.v1.resfull.OutputVDSumaryDTO;


public class GetVDSumaryServiceImpl implements GetVDSumaryService {

	@Inject
	private GetVDSumaryDAO getVDSumaryDAO;

	@Override
	public OutputVDSumaryDTO getValueDeclarationList(InputVDSumaryDTO data) {
		return getVDSumaryDAO.getValueDeclarationList(data);
	}

}
