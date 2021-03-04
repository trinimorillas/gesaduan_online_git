package es.mercadona.gesaduan.dao.puertos.getpuertoagencia.v1;

import es.mercadona.gesaduan.dto.puertos.getpuertoagencia.v1.InputDatosPuertoAgenciaDTO;
import es.mercadona.gesaduan.dto.puertos.getpuertoagencia.v1.restfull.OutputPuertoAgenciaDTO;

public interface GetPuertoAgenciaDAO {
	
	public OutputPuertoAgenciaDTO listarPuertoAgencia(InputDatosPuertoAgenciaDTO datos);
	
}
