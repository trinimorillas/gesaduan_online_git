package es.mercadona.gesaduan.business.declaracionesdevalorapi.getvddetail.v1;

import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvddetail.v1.InputVDDetailDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvddetail.v1.restfull.OutputVDDetailDTO;

public interface GetVDDetailService {	
	public OutputVDDetailDTO getValueDeclarationDetail(InputVDDetailDTO input);
}