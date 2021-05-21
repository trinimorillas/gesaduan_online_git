package es.mercadona.gesaduan.business.declaracionesdevalorapi.putvdconfirmdownload.v1;

import es.mercadona.gesaduan.dto.declaracionesdevalorapi.putvdconfirmdownload.v1.InputDataDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.putvdconfirmdownload.v1.restfull.OutputPutVDConfirmDownloadDTO;

public interface PutVDConfirmDownloadService {
	public OutputPutVDConfirmDownloadDTO updateEstadoDescarga(InputDataDTO input);
}