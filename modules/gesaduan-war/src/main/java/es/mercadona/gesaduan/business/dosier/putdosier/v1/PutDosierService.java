package es.mercadona.gesaduan.business.dosier.putdosier.v1;

import es.mercadona.gesaduan.dto.dosier.putdosier.v1.InputDatosPutDTO;
import es.mercadona.gesaduan.dto.dosier.putdosier.v1.restfull.OutputDosierPutDTO;

public interface PutDosierService {
	public OutputDosierPutDTO putDosier(InputDatosPutDTO input);
}
