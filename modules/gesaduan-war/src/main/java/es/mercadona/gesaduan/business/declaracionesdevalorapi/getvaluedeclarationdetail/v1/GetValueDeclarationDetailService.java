package es.mercadona.gesaduan.business.declaracionesdevalorapi.getvaluedeclarationdetail.v1;

import es.mercadona.fwk.core.web.BoPage;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvaluedeclarationdetail.v1.InputValueDeclarationDetailDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvaluedeclarationdetail.v1.restfull.OutputValueDeclarationDetailDTO;

public interface GetValueDeclarationDetailService {	
	public OutputValueDeclarationDetailDTO getValueDeclarationDetail(InputValueDeclarationDetailDTO input, BoPage paginacion);
}