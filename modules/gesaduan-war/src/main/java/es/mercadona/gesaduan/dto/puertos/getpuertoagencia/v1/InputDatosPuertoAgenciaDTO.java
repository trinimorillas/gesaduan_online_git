package es.mercadona.gesaduan.dto.puertos.getpuertoagencia.v1;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputDatosPuertoAgenciaDTO extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	InputPuertoAgenciaDTO datos;

	public InputPuertoAgenciaDTO getDatos() {
		return datos;
	}

	public void setDatos(InputPuertoAgenciaDTO datos) {
		this.datos = datos;
	}

}
