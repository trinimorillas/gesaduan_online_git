package es.mercadona.gesaduan.business.puertos.getpuertoagencia.v1;

import es.mercadona.gesaduan.dto.puertos.getpuertoagencia.v1.InputPuertoAgenciaDTO;
import es.mercadona.gesaduan.dto.puertos.getpuertoagencia.v1.restfull.OutputPuertoAgenciaDTO;

public interface GetPuertoAgenciaService {
	
	public OutputPuertoAgenciaDTO listarPuertoAgencia(InputPuertoAgenciaDTO datos);
	
}
