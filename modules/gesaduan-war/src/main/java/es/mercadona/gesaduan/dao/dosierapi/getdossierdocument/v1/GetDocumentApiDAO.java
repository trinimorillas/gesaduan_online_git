package es.mercadona.gesaduan.dao.dosierapi.getdossierdocument.v1;

import es.mercadona.gesaduan.dto.dosierapi.getdossierdocument.v1.InputDossierDocumentDTO;
import es.mercadona.gesaduan.dto.dosierapi.getdossierdocument.v1.OutputDossierDocHeadDTO;

public interface GetDocumentApiDAO {

	public OutputDossierDocHeadDTO getDatosDocumento(InputDossierDocumentDTO input);
	public boolean isDosierInvalidado(InputDossierDocumentDTO input);	
	public OutputDossierDocHeadDTO getDocumentoInvalidado(InputDossierDocumentDTO input);	
	
}
