package es.mercadona.gesaduan.dto.cargas.putcargas.v1;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputDatosPutCargaDTO extends AbstractDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;	
	
	InputPutCargaDTO datos;
	InputMetadatosPutCargaDTO metadatos;
	
	public InputPutCargaDTO getDatos() {
		return datos;
	}
	
	public void setDatos(InputPutCargaDTO datos) {
		this.datos = datos;
	}

	public InputMetadatosPutCargaDTO getMetadatos() {
		return metadatos;
	}

	public void setMetadatos(InputMetadatosPutCargaDTO metadatos) {
		this.metadatos = metadatos;
	}
	
}