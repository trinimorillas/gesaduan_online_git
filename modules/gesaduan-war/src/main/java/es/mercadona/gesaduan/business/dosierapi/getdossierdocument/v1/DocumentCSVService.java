package es.mercadona.gesaduan.business.dosierapi.getdossierdocument.v1;

import es.mercadona.gesaduan.dto.dosierapi.getdossierdocument.v1.InputDossierDocumentDTO;
import es.mercadona.gesaduan.dto.dosierapi.getdossierdocument.v1.OutputDossierDocHeadDTO;

public interface DocumentCSVService {

	public OutputDossierDocHeadDTO preparaDocumentoCSV(InputDossierDocumentDTO input);
	
}
