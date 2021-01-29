package es.mercadona.gesaduan.dto.productos.putproductos.v1.restfull;

import java.io.Serializable;
import java.util.Map;

import es.mercadona.gesaduan.dto.common.AbstractDTO;
import es.mercadona.gesaduan.dto.productos.getproductos.v1.restfull.DatosProductosDTO;

public class OutputPutProductosDTO extends AbstractDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Map<?, ?> metadatos;
	
	private DatosProductosDTO datos;

	public Map<?, ?> getMetadatos() {
		return metadatos;
	}

	public void setMetadatos(Map<?, ?> metadatos) {
		this.metadatos = metadatos;
	}

	public DatosProductosDTO getDatos() {
		return datos;
	}

	public void setDatos(DatosProductosDTO datos) {
		this.datos = datos;
	}
	
	

}
