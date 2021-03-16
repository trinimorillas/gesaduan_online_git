package es.mercadona.gesaduan.dao.puertos.getpuertoagencia.v1;

import es.mercadona.gesaduan.dto.puertos.getpuertoagencia.v1.InputPuertoAgenciaDTO;
import es.mercadona.gesaduan.dto.puertos.getpuertoagencia.v1.restfull.OutputPuertoAgenciaDTO;

public interface GetPuertoAgenciaDAO {
	
	public OutputPuertoAgenciaDTO listarPuertos(InputPuertoAgenciaDTO datos);
	public OutputPuertoAgenciaDTO listarAgencias(InputPuertoAgenciaDTO datos);
	
}
