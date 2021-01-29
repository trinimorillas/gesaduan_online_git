package es.mercadona.gesaduan.business.alertas.getalertas.v1;

import es.mercadona.fwk.core.web.BoPage;
import es.mercadona.gesaduan.dto.alertas.getalertas.v1.InputAlertasDTO;
import es.mercadona.gesaduan.dto.alertas.getalertas.v1.restfull.OutputAlertasDTO;

public interface GetAlertasService {
	
	public OutputAlertasDTO getAlertas(InputAlertasDTO input, BoPage paginacion);
	
	public boolean checkNotificacionExiste(String codigoElemento);
	
}
