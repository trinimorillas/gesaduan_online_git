package es.mercadona.gesaduan.dao.declaracionesdevalorapi.putvdconfirmdownload.v1;

import es.mercadona.gesaduan.dto.declaracionesdevalorapi.putvdconfirmdownload.v1.InputDataDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.putvdconfirmdownload.v1.restfull.OutputPutVDConfirmDownloadDTO;

public interface PutVDConfirmDownloadDAO {		
		public OutputPutVDConfirmDownloadDTO actualizarEstadoDescarga(InputDataDTO input);
}
