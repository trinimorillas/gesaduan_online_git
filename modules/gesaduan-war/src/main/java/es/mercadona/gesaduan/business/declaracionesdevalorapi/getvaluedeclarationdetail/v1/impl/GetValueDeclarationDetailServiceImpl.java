package es.mercadona.gesaduan.business.declaracionesdevalorapi.getvaluedeclarationdetail.v1.impl;

import javax.inject.Inject;

import es.mercadona.fwk.core.web.BoPage;
import es.mercadona.gesaduan.business.declaracionesdevalor.getdvdetalle.v1.GetDVDetalleService;
import es.mercadona.gesaduan.business.declaracionesdevalorapi.getvaluedeclarationdetail.v1.GetValueDeclarationDetailService;
import es.mercadona.gesaduan.dao.declaracionesdevalor.getdvdetalle.v1.GetDVDetalleDAO;
import es.mercadona.gesaduan.dao.declaracionesdevalorapi.getdeclarationvaluedetail.v1.GetValueDeclarationDetailDAO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.getdvdetalle.v1.InputDeclaracionesDeValorDetalleDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.getdvdetalle.v1.restfull.OutputDeclaracionesDeValorDetalleDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvaluedeclarationdetail.v1.InputValueDeclarationDetailDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvaluedeclarationdetail.v1.restfull.OutputValueDeclarationDetailDTO;

public class GetValueDeclarationDetailServiceImpl implements GetValueDeclarationDetailService {

	@Inject
	private GetValueDeclarationDetailDAO getValueDeclarationDetailDAO;

	@Override
	public OutputValueDeclarationDetailDTO getValueDeclarationDetail(InputValueDeclarationDetailDTO input, BoPage paginacion) {
		OutputValueDeclarationDetailDTO result = getValueDeclarationDetailDAO.getValueDeclarationDetail(input, paginacion);
		
		if (result.getData() != null) {
			result.getData().setValueDeclarationHistorical(getValueDeclarationDetailDAO.obtenerHistoricoDV(declarationCode, declarationYear));			
			return result;
		} else {			
			return null;
		}		
	}
}