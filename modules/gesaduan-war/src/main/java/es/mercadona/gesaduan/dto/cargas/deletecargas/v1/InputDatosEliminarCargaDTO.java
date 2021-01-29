package es.mercadona.gesaduan.dto.cargas.deletecargas.v1;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputDatosEliminarCargaDTO extends AbstractDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;	
	
	InputEliminarCargaDTO datos;
	InputMetadatosEliminarCargaDTO metadatos;
	
	public InputEliminarCargaDTO getDatos() {
		return datos;
	}
	
	public void setDatos(InputEliminarCargaDTO datos) {
		this.datos = datos;
	}

	public InputMetadatosEliminarCargaDTO getMetadatos() {
		return metadatos;
	}

	public void setMetadatos(InputMetadatosEliminarCargaDTO metadatos) {
		this.metadatos = metadatos;
	}
	
}