package es.mercadona.gesaduan.business.declaracionesdevalorapi.getvaluedeclarationdetail.v1.impl;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.declaracionesdevalorapi.getvaluedeclarationdetail.v1.GetValueDeclarationDetailService;
import es.mercadona.gesaduan.dao.declaracionesdevalorapi.getdeclarationvaluedetail.v1.GetValueDeclarationDetailDAO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvaluedeclarationdetail.v1.InputValueDeclarationDetailDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvaluedeclarationdetail.v1.restfull.OutputValueDeclarationDetailDTO;

public class GetValueDeclarationDetailServiceImpl implements GetValueDeclarationDetailService {

	@Inject
	private GetValueDeclarationDetailDAO getValueDeclarationDetailDAO;

	@Override
	public OutputValueDeclarationDetailDTO getValueDeclarationDetail(InputValueDeclarationDetailDTO input) {
		return getValueDeclarationDetailDAO.getValueDeclarationDetail(input);		
	}
}