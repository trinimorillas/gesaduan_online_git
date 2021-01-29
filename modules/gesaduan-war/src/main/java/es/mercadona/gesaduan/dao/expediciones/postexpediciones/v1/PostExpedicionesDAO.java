package es.mercadona.gesaduan.dao.expediciones.postexpediciones.v1;

import es.mercadona.gesaduan.dto.expediciones.postCargarExpedicion.v1.ExpedicionResponsePKsDTO;
import es.mercadona.gesaduan.jpa.expediciones.v1.postexpediciones.ExpedicionesCabeceraJPA;


public interface PostExpedicionesDAO {

	public ExpedicionResponsePKsDTO postCabecera(ExpedicionesCabeceraJPA input);
	public String comprobarVersion();

}
