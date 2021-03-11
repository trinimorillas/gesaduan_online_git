package es.mercadona.gesaduan.dto.dosier.putdosier.v1;

import java.io.Serializable;
import java.util.List;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputDatosPutDTO extends AbstractDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;	
	
	InputMetadatosPutDTO metadatos;
	InputDatosDosierDTO datos;
	
	public InputMetadatosPutDTO getMetadatos() {
		return metadatos;
	}
	public void setMetadatos(InputMetadatosPutDTO metadatos) {
		this.metadatos = metadatos;
	}
	public InputDatosDosierDTO getDatos() {
		return datos;
	}
	public void setDatos(InputDatosDosierDTO datos) {
		this.datos = datos;
	}	
	
}
