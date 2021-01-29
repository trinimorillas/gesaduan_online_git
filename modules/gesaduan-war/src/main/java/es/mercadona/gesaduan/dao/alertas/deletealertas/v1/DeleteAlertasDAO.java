package es.mercadona.gesaduan.dao.alertas.deletealertas.v1;

import es.mercadona.gesaduan.dto.alertas.deletealertas.v1.InputDeleteAlertasDTO;

public interface DeleteAlertasDAO {
	
	public void eliminarAlertasExpedicion(InputDeleteAlertasDTO input);
	
	public void eliminarAlertas(InputDeleteAlertasDTO input);

}
