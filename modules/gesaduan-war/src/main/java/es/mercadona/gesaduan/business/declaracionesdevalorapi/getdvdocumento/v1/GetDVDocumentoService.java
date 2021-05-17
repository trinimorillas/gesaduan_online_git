package es.mercadona.gesaduan.business.declaracionesdevalorapi.getdvdocumento.v1;

import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getdvdocumento.v1.InputValueDeclarationDocumentDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getdvdocumento.v1.OutputDeclaracionesDeValorDocCabDTO;

public interface GetDVDocumentoService {

	public OutputDeclaracionesDeValorDocCabDTO preparaDocumento(InputValueDeclarationDocumentDTO input);
	
}
