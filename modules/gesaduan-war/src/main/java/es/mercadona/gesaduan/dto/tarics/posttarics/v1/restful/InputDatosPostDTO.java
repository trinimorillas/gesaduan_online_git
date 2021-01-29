package es.mercadona.gesaduan.dto.tarics.posttarics.v1.restful;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputDatosPostDTO extends AbstractDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	InputMetadatosPostDTO metadatos;
	InputTaricsPostDTO datos;
	
	
	public InputMetadatosPostDTO getMetadatos() {
		return metadatos;
	}
	public void setMetadatos(InputMetadatosPostDTO metadatos) {
		this.metadatos = metadatos;
	}
	public InputTaricsPostDTO getDatos() {
		return datos;
	}
	public void setDatos(InputTaricsPostDTO datos) {
		this.datos = datos;
	}
	
	

}
