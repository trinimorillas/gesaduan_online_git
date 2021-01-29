package es.mercadona.gesaduan.dto.proveedores.putproveedores.v1.restfull;

import java.io.Serializable;
import java.util.Map;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class OutputProveedoresContactos extends AbstractDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Map<?, ?> metadatos;
	
	private PutProveedorContacto datos;

	public Map<?, ?> getMetadatos() {
		return metadatos;
	}

	public void setMetadatos(Map<?, ?> metadatos) {
		this.metadatos = metadatos;
	}

	public PutProveedorContacto getDatos() {
		return datos;
	}

	public void setDatos(PutProveedorContacto datos) {
		this.datos = datos;
	}


	
	
	
}
