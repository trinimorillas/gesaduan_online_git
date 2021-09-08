package es.mercadona.gesaduan.dao.declaracionesdevalorapi.getvddocument.v2;

import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvddocument.v2.InputValueDeclarationDocumentDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvddocument.v2.OutputDeclaracionesDeValorDocCabDTO;

public interface GetVDDocApiDAO {
	public OutputDeclaracionesDeValorDocCabDTO getDatosDocumento(InputValueDeclarationDocumentDTO input);
	public boolean esCorrecta(InputValueDeclarationDocumentDTO input);
}
