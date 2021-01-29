package es.mercadona.gesaduan.dto.proveedores.putrelacionesproveedores.v1;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class InputRelacionesProveedorDTO extends AbstractDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private MetadatosDTO metadatos;
	private DatosRelacionesProveedoreDTO datos;
	
	public MetadatosDTO getMetadatos() {
		return metadatos;
	}
	public void setMetadatos(MetadatosDTO metadatos) {
		this.metadatos = metadatos;
	}
	public DatosRelacionesProveedoreDTO getDatos() {
		return datos;
	}
	public void setDatos(DatosRelacionesProveedoreDTO datos) {
		this.datos = datos;
	}
	
	

}
