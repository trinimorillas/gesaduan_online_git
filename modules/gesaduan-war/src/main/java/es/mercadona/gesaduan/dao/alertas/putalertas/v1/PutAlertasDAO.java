package es.mercadona.gesaduan.dao.alertas.putalertas.v1;

import es.mercadona.gesaduan.dto.alertas.putalertas.v1.InputPutAlertasDTO;

public interface PutAlertasDAO {

	public void editarAlertasExpedicion(InputPutAlertasDTO input);
	
	public void editarAlertas(InputPutAlertasDTO input);
	
}
