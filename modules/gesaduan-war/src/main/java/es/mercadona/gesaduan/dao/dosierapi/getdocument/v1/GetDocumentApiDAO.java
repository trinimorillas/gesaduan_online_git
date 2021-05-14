package es.mercadona.gesaduan.dao.dosierapi.getdocument.v1;

import es.mercadona.gesaduan.dto.dosierapi.getdocument.v1.InputDossierDocumentDTO;
import es.mercadona.gesaduan.dto.dosierapi.getdocument.v1.OutputDossierDocHeadDTO;

public interface GetDocumentApiDAO {

	public OutputDossierDocHeadDTO getDatosDocumento(InputDossierDocumentDTO input);
	public boolean isDosierInvalidado(InputDossierDocumentDTO input);	
	public OutputDossierDocHeadDTO getDocumentoInvalidado(InputDossierDocumentDTO input);	
	
}
