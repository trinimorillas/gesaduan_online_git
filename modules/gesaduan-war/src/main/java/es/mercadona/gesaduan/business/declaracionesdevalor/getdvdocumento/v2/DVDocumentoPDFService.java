package es.mercadona.gesaduan.business.declaracionesdevalor.getdvdocumento.v2;

import es.mercadona.gesaduan.dto.declaracionesdevalor.getdvdocumento.v2.InputDeclaracionesDeValorDocumentoDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.getdvdocumento.v2.OutputDeclaracionesDeValorDocCabDTO;

public interface DVDocumentoPDFService {

	public OutputDeclaracionesDeValorDocCabDTO preparaDocumentoPDF(InputDeclaracionesDeValorDocumentoDTO input);
	
}
