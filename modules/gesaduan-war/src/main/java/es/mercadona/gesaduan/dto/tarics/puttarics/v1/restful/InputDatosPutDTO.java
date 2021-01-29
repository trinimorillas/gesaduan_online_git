package es.mercadona.gesaduan.dto.tarics.puttarics.v1.restful;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputDatosPutDTO extends AbstractDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	InputMetadatosPutDTO metadatos;
	InputTaricsPutDTO datos;
	
	public InputMetadatosPutDTO getMetadatos() {
		return metadatos;
	}
	public void setMetadatos(InputMetadatosPutDTO metadatos) {
		this.metadatos = metadatos;
	}
	public InputTaricsPutDTO getDatos() {
		return datos;
	}
	public void setDatos(InputTaricsPutDTO datos) {
		this.datos = datos;
	}
	
	
}
