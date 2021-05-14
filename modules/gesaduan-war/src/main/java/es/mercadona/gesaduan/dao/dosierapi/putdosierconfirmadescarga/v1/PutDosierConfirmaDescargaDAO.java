package es.mercadona.gesaduan.dao.dosierapi.putdosierconfirmadescarga.v1;

import es.mercadona.gesaduan.dto.dosierapi.putdosierconfirmadescarga.v1.InputDataDTO;
import es.mercadona.gesaduan.dto.dosierapi.putdosierconfirmadescarga.v1.restfull.OutputPutDosierConfirmaDescargaDTO;

public interface PutDosierConfirmaDescargaDAO {		
		public OutputPutDosierConfirmaDescargaDTO actualizarEstadoDescarga(InputDataDTO input);
}