package es.mercadona.gesaduan.dto.puertos.getpuertoagencia.v1.restfull;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class OutputPuertoAgenciaDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
	
	private DatosPuertoAgenciaDTO datos;

	public DatosPuertoAgenciaDTO getDatos() {
		return datos;
	}

	public void setDatos(DatosPuertoAgenciaDTO datos) {
		this.datos = datos;
	}

}
