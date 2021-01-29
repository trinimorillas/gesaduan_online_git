package es.mercadona.gesaduan.dto.tarics.putreemplazartaric.v1.restful;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputReemplazarTaricsDTO extends AbstractDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ReemplazarTaricsMetadatosDTO metadatos;
	private ReemplazarTaricsDatosDTO datos;
	
	public ReemplazarTaricsMetadatosDTO getMetadatos() {
		return metadatos;
	}
	public void setMetadatos(ReemplazarTaricsMetadatosDTO metadatos) {
		this.metadatos = metadatos;
	}
	public ReemplazarTaricsDatosDTO getDatos() {
		return datos;
	}
	public void setDatos(ReemplazarTaricsDatosDTO datos) {
		this.datos = datos;
	}
	
	
	
	

}
