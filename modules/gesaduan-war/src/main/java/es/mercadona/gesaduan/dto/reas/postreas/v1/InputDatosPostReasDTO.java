package es.mercadona.gesaduan.dto.reas.postreas.v1;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputDatosPostReasDTO extends AbstractDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	InputMetadatosReasPostDTO metadatos;
	InputPostReasDTO datos;
	
	public InputMetadatosReasPostDTO getMetadatos() {
		return metadatos;
	}
	public void setMetadatos(InputMetadatosReasPostDTO metadatos) {
		this.metadatos = metadatos;
	}
	public InputPostReasDTO getDatos() {
		return datos;
	}
	public void setDatos(InputPostReasDTO datos) {
		this.datos = datos;
	}
	
	
	
	

}
