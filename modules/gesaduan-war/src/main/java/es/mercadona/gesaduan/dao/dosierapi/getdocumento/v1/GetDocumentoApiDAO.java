package es.mercadona.gesaduan.dao.dosierapi.getdocumento.v1;

import es.mercadona.gesaduan.dto.dosierapi.getdocumento.v1.InputDosierDocumentoDTO;
import es.mercadona.gesaduan.dto.dosierapi.getdocumento.v1.OutputDosierDocCabDTO;

public interface GetDocumentoApiDAO {

	public OutputDosierDocCabDTO getDatosDocumento(InputDosierDocumentoDTO input);
	public boolean isDosierInvalidado(InputDosierDocumentoDTO input);	
	public OutputDosierDocCabDTO getDocumentoInvalidado(InputDosierDocumentoDTO input);	
	
}
