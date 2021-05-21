package es.mercadona.gesaduan.dao.declaracionesdevalorapi.getvddocument.v1;

import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvddocument.v1.InputValueDeclarationDocumentDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvddocument.v1.OutputDeclaracionesDeValorDocCabDTO;

public interface GetVDDocumentApiDAO {

	public OutputDeclaracionesDeValorDocCabDTO getDatosDocumento(InputValueDeclarationDocumentDTO input);
	
}
