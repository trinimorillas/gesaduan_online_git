package es.mercadona.gesaduan.business.dosierapi.getdocumento.v1;

import es.mercadona.gesaduan.dto.dosierapi.getdocumento.v1.InputDosierDocumentoDTO;
import es.mercadona.gesaduan.dto.dosierapi.getdocumento.v1.OutputDosierDocCabDTO;

public interface GetDocumentoService {

	public OutputDosierDocCabDTO preparaDocumento(InputDosierDocumentoDTO input);
	
}