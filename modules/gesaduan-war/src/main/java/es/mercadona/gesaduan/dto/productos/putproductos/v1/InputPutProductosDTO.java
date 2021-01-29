package es.mercadona.gesaduan.dto.productos.putproductos.v1;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputPutProductosDTO extends AbstractDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private InputMetadatosDTO metadatos;
	private InputDatosDTO datos;
	
	public InputMetadatosDTO getMetadatos() {
		return metadatos;
	}
	public void setMetadatos(InputMetadatosDTO metadatos) {
		this.metadatos = metadatos;
	}
	public InputDatosDTO getDatos() {
		return datos;
	}
	public void setDatos(InputDatosDTO datos) {
		this.datos = datos;
	}
	
	

}
