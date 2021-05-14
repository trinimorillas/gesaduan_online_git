package es.mercadona.gesaduan.business.dosierapi.getdocument.v1;

import es.mercadona.gesaduan.dto.dosierapi.getdocument.v1.InputDossierDocumentDTO;
import es.mercadona.gesaduan.dto.dosierapi.getdocument.v1.OutputDossierDocHeadDTO;

public interface DocumentCSVService {

	public OutputDossierDocHeadDTO preparaDocumentoCSV(InputDossierDocumentDTO input);
	
}
