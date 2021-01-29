package es.mercadona.gesaduan.dto.proveedores.putrelacionesproveedores.v1.restfull;

import java.io.Serializable;
import java.util.Map;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class OutputRelacionesProveedorDTO extends AbstractDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Map<?, ?> metadatos;
	
	private DatosRelacionesProveedorDTO datos;

	public Map<?, ?> getMetadatos() {
		return metadatos;
	}

	public void setMetadatos(Map<?, ?> metadatos) {
		this.metadatos = metadatos;
	}

	public DatosRelacionesProveedorDTO getDatos() {
		return datos;
	}

	public void setDatos(DatosRelacionesProveedorDTO datos) {
		this.datos = datos;
	}
	
	

}
