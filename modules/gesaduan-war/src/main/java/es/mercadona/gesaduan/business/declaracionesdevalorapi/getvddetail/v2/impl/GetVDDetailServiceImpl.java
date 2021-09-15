package es.mercadona.gesaduan.business.declaracionesdevalorapi.getvddetail.v2.impl;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.declaracionesdevalorapi.getvddetail.v2.GetVDDetailService;
import es.mercadona.gesaduan.dao.declaracionesdevalorapi.getvddetail.v2.GetVDDetailDAO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvddetail.v2.InputVDDetailDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvddetail.v2.restful.OutputVDDetailDTO;



public class GetVDDetailServiceImpl implements GetVDDetailService {

	@Inject
	private GetVDDetailDAO getValueDeclarationDetailDAO;

	@Override
	public OutputVDDetailDTO getValueDeclarationDetail(InputVDDetailDTO input) {
		return getValueDeclarationDetailDAO.getValueDeclarationDetail(input);		
	}
}
