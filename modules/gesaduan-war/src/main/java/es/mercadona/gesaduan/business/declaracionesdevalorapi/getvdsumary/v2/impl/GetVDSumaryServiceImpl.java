package es.mercadona.gesaduan.business.declaracionesdevalorapi.getvdsumary.v2.impl;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.declaracionesdevalorapi.getvdsumary.v2.GetVDSumaryService;
import es.mercadona.gesaduan.dao.declaracionesdevalorapi.getvdsumary.v2.GetVDSumaryDAO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvdsumary.v2.InputVDSumaryDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvdsumary.v2.restfull.OutputVDSumaryDTO;



public class GetVDSumaryServiceImpl implements GetVDSumaryService {

	@Inject
	private GetVDSumaryDAO getVDSumaryDAO;

	@Override
	public OutputVDSumaryDTO getValueDeclarationList(InputVDSumaryDTO data) {
		return getVDSumaryDAO.getValueDeclarationList(data);
	}

}
