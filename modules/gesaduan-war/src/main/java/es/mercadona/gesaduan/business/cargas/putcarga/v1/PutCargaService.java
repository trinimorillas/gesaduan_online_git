package es.mercadona.gesaduan.business.cargas.putcarga.v1;

import es.mercadona.gesaduan.dto.cargas.putcargas.v1.InputDatosPutCargaDTO;
import es.mercadona.gesaduan.dto.cargas.putcargas.v1.restfull.OutputPutCargaDTO;

public interface PutCargaService {
	public OutputPutCargaDTO putCarga(InputDatosPutCargaDTO input);
}
