package es.mercadona.gesaduan.dto.proveedores.putproveedores.v1.restfull;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.proveedores.AbstractDTO;

public class InputPutProveedoresContactos extends AbstractDTO implements Serializable{

private static final long serialVersionUID = 1L;
	
	private PutProveedoresMetadatos metadatos;
	
	private PutProveedorContacto datosContacto;

	public PutProveedoresMetadatos getMetadatos() {
		return metadatos;
	}

	public void setMetadatos(PutProveedoresMetadatos metadatos) {
		this.metadatos = metadatos;
	}

	public PutProveedorContacto getDatosContacto() {
		return datosContacto;
	}

	public void setDatosContacto(PutProveedorContacto datosContacto) {
		this.datosContacto = datosContacto;
	}
	
	
	
	
}
