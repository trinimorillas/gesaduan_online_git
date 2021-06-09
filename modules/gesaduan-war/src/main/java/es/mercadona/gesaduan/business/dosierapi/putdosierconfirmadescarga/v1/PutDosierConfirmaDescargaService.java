package es.mercadona.gesaduan.business.dosierapi.putdosierconfirmadescarga.v1;

import es.mercadona.gesaduan.dto.dosierapi.putdosierconfirmadescarga.v1.InputDataDTO;
import es.mercadona.gesaduan.dto.dosierapi.putdosierconfirmadescarga.v1.restfull.OutputPutDosierConfirmaDescargaDTO;

public interface PutDosierConfirmaDescargaService {
	public OutputPutDosierConfirmaDescargaDTO updateEstadoDescarga(InputDataDTO input);
}