package es.mercadona.gesaduan.dto.cargas.cambiarestado.v1;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputDatosCambiarEstadoCargaDTO extends AbstractDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;	
	
	InputCambiarEstadoCargaDTO datos;
	InputMetadatosCambiarEstadoCargaDTO metadatos;
	
	public InputCambiarEstadoCargaDTO getDatos() {
		return datos;
	}
	
	public void setDatos(InputCambiarEstadoCargaDTO datos) {
		this.datos = datos;
	}

	public InputMetadatosCambiarEstadoCargaDTO getMetadatos() {
		return metadatos;
	}

	public void setMetadatos(InputMetadatosCambiarEstadoCargaDTO metadatos) {
		this.metadatos = metadatos;
	}
	
}