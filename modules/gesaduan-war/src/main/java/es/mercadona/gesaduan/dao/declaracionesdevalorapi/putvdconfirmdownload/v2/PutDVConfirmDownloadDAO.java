package es.mercadona.gesaduan.dao.declaracionesdevalorapi.putvdconfirmdownload.v2;

import es.mercadona.gesaduan.dto.declaracionesdevalorapi.putvdconfirmdownload.v2.InputDataDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.putvdconfirmdownload.v2.restfull.OutputPutVDConfirmDownloadDTO;

public interface PutDVConfirmDownloadDAO {		
		public OutputPutVDConfirmDownloadDTO actualizarEstadoDescarga(InputDataDTO input);
}
