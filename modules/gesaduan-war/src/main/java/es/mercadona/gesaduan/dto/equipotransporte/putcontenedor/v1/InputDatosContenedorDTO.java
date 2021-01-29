package es.mercadona.gesaduan.dto.equipotransporte.putcontenedor.v1;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputDatosContenedorDTO extends AbstractDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;	
	
	InputMetadatosContenedorDTO metadatos;
	InputPutContenedorDTO datos;
	
	public InputMetadatosContenedorDTO getMetadatos() {
		return metadatos;
	}
	
	public void setMetadatos(InputMetadatosContenedorDTO metadatos) {
		this.metadatos = metadatos;
	}
	
	public InputPutContenedorDTO getDatos() {
		return datos;
	}
	
	public void setDatos(InputPutContenedorDTO datos) {
		this.datos = datos;
	}
	
}
