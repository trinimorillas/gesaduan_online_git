package es.mercadona.gesaduan.dto.proveedores.putproveedores.v1.restfull;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputPutProveedores extends AbstractDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private PutProveedoresMetadatos metadatos;
	
	private PutProveedoresDatos datos;

	public PutProveedoresMetadatos getMetadatos() {
		return metadatos;
	}

	public void setMetadatos(PutProveedoresMetadatos metadatos) {
		this.metadatos = metadatos;
	}

	public PutProveedoresDatos getDatos() {
		return datos;
	}

	public void setDatos(PutProveedoresDatos datos) {
		this.datos = datos;
	}
	
	

}
