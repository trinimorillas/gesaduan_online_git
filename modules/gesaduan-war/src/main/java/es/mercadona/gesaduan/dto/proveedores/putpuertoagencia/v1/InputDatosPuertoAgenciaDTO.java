package es.mercadona.gesaduan.dto.proveedores.putpuertoagencia.v1;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputDatosPuertoAgenciaDTO extends AbstractDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private InputMetadatosPuertoAgenciaDTO metadatos;
	private InputPuertoAgenciaDTO datos;

	public InputMetadatosPuertoAgenciaDTO getMetadatos() {
		return metadatos;
	}

	public void setMetadatos(InputMetadatosPuertoAgenciaDTO metadatos) {
		this.metadatos = metadatos;
	}

	public InputPuertoAgenciaDTO getDatos() {
		return datos;
	}

	public void setDatos(InputPuertoAgenciaDTO datos) {
		this.datos = datos;
	}

}
