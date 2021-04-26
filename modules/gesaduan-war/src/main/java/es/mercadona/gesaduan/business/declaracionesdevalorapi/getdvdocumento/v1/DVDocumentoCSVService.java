package es.mercadona.gesaduan.business.declaracionesdevalorapi.getdvdocumento.v1;

import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getdvdocumento.v1.InputDeclaracionesDeValorDocumentoDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getdvdocumento.v1.OutputDeclaracionesDeValorDocCabDTO;

public interface DVDocumentoCSVService {

	public OutputDeclaracionesDeValorDocCabDTO preparaDocumentoCSV(InputDeclaracionesDeValorDocumentoDTO input);
	
}
