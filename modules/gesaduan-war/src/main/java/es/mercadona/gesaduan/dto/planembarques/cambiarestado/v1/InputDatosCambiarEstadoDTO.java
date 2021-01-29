package es.mercadona.gesaduan.dto.planembarques.cambiarestado.v1;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputDatosCambiarEstadoDTO extends AbstractDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;	
	
	InputMetadatosCambiarEstadoDTO metadatos;
	InputCambiarEstadoDTO datos;
	
	public InputMetadatosCambiarEstadoDTO getMetadatos() {
		return metadatos;
	}
	public void setMetadatos(InputMetadatosCambiarEstadoDTO metadatos) {
		this.metadatos = metadatos;
	}
	public InputCambiarEstadoDTO getDatos() {
		return datos;
	}
	public void setDatos(InputCambiarEstadoDTO datos) {
		this.datos = datos;
	}	
	
}
