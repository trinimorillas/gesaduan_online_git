package es.mercadona.gesaduan.business.declaracionesdevalorapi.getvddocument.v1;

import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvddocument.v1.InputValueDeclarationDocumentDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvddocument.v1.OutputDeclaracionesDeValorDocCabDTO;

public interface VDDocumentPDFService {

	public OutputDeclaracionesDeValorDocCabDTO preparaDocumentoPDF(InputValueDeclarationDocumentDTO input);
	
}
