package es.mercadona.gesaduan.dto.proveedores.getrelacionesproveedores.v1.restfull;

import java.io.Serializable;
import java.util.Map;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class OutputRelacionesProveedoresDTO extends AbstractDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Map<?, ?> metadatos;
	
	private DatosRelacionesProveedoresDTO datos;

	public Map<?, ?> getMetadatos() {
		return metadatos;
	}

	public void setMetadatos(Map<?, ?> metadatos) {
		this.metadatos = metadatos;
	}

	public DatosRelacionesProveedoresDTO getDatos() {
		return datos;
	}

	public void setDatos(DatosRelacionesProveedoresDTO datos) {
		this.datos = datos;
	}
	
	

}
