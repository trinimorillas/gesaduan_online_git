package es.mercadona.gesaduan.dto.equipotransporte.getcontenedores.v1;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputDatosGetContenedorDTO extends AbstractDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;	
	
	InputMetadatosGetContenedorDTO metadatos;
	InputGetContenedorDTO datos;
	
	public InputMetadatosGetContenedorDTO getMetadatos() {
		return metadatos;
	}
	
	public void setMetadatos(InputMetadatosGetContenedorDTO metadatos) {
		this.metadatos = metadatos;
	}
	
	public InputGetContenedorDTO getDatos() {
		return datos;
	}
	
	public void setDatos(InputGetContenedorDTO datos) {
		this.datos = datos;
	}
	
}
