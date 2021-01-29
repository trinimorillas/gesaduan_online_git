package es.mercadona.gesaduan.business.alertas.putalertas.v1;

import es.mercadona.gesaduan.dto.alertas.putalertas.v1.InputPutAlertasDTO;
import es.mercadona.gesaduan.dto.alertas.putalertas.v1.restfull.OutputPutAlertasDTO;

public interface PutAlertasService {
	
	public OutputPutAlertasDTO editarAlertas(InputPutAlertasDTO input);
	
}
