package es.mercadona.gesaduan.business.dosierapi.v1;

import es.mercadona.gesaduan.dto.dosierapi.getdocumento.v1.InputDosierDocumentoDTO;
import es.mercadona.gesaduan.dto.dosierapi.getdocumento.v1.OutputDosierDocCabDTO;

public interface DocumentoCSVService {

	public OutputDosierDocCabDTO preparaDocumentoCSV(InputDosierDocumentoDTO input);
	
}
