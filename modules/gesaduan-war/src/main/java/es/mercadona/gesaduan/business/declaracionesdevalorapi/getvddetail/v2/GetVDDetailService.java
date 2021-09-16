package es.mercadona.gesaduan.business.declaracionesdevalorapi.getvddetail.v2;

import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvddetail.v2.InputVDDetailDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvddetail.v2.restfull.OutputVDDetailDTO;

public interface GetVDDetailService {	
	public OutputVDDetailDTO getValueDeclarationDetail(InputVDDetailDTO input);
}
