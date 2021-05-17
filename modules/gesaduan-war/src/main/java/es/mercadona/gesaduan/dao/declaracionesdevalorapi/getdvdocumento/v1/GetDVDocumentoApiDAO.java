package es.mercadona.gesaduan.dao.declaracionesdevalorapi.getdvdocumento.v1;

import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getdvdocumento.v1.InputValueDeclarationDocumentDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getdvdocumento.v1.OutputDeclaracionesDeValorDocCabDTO;

public interface GetDVDocumentoApiDAO {

	public OutputDeclaracionesDeValorDocCabDTO getDatosDocumento(InputValueDeclarationDocumentDTO input);
	
}
