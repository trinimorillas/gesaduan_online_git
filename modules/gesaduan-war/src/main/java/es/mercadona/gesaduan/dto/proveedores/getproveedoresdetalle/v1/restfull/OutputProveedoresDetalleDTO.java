package es.mercadona.gesaduan.dto.proveedores.getproveedoresdetalle.v1.restfull;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class OutputProveedoresDetalleDTO extends AbstractDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Map<?, ?> metadatos;
	
	private DatosProveedoresDetalleDTO datos;



	public DatosProveedoresDetalleDTO getDatos() {
		return datos;
	}

	public void setDatos(DatosProveedoresDetalleDTO datos) {
		this.datos = datos;
	}

	public Map<?, ?> getMetadatos() {
		return metadatos;
	}

	public void setMetadatos(Map<?, ?> metadatos) {
		this.metadatos = metadatos;
	}
	
	

}
