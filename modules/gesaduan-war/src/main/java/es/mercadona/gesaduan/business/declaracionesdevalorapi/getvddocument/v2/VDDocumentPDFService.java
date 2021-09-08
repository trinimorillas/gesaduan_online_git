package es.mercadona.gesaduan.business.declaracionesdevalorapi.getvddocument.v2;

import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvddocument.v2.InputValueDeclarationDocumentDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvddocument.v2.OutputDeclaracionesDeValorDocCabDTO;

public interface VDDocumentPDFService {
	public OutputDeclaracionesDeValorDocCabDTO preparaDocumentoPDF(InputValueDeclarationDocumentDTO input);
}
