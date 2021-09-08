package es.mercadona.gesaduan.business.declaracionesdevalorapi.putvdconfirmdownload.v2;

import es.mercadona.gesaduan.dto.declaracionesdevalorapi.putvdconfirmdownload.v2.InputDataDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.putvdconfirmdownload.v2.restfull.OutputPutVDConfirmDownloadDTO;

public interface PutDVConfirmDownloadService {
	public OutputPutVDConfirmDownloadDTO updateEstadoDescarga(InputDataDTO input);
}