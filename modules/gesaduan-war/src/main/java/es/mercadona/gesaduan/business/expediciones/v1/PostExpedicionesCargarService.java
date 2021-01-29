package es.mercadona.gesaduan.business.expediciones.v1;

import es.mercadona.fwk.core.io.Resource;
import es.mercadona.gesaduan.jpa.declaracionesdevalor.DeclaracionesDeValorPK;

public interface PostExpedicionesCargarService {
	
	public DeclaracionesDeValorPK cargarExpedicion(Resource expedicion, String usuario, String locale) throws Exception;

}
