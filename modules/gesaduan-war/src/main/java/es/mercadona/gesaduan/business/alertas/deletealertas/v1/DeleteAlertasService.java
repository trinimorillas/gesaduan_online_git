package es.mercadona.gesaduan.business.alertas.deletealertas.v1;

import es.mercadona.gesaduan.dto.alertas.deletealertas.v1.InputDeleteAlertasDTO;
import es.mercadona.gesaduan.dto.alertas.deletealertas.v1.restfull.OutputDeleteAlertasDTO;

public interface DeleteAlertasService {
	
	public OutputDeleteAlertasDTO eliminarAlertas(InputDeleteAlertasDTO input);
	
}
