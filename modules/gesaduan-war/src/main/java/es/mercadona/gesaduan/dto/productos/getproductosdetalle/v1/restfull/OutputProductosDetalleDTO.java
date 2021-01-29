package es.mercadona.gesaduan.dto.productos.getproductosdetalle.v1.restfull;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class OutputProductosDetalleDTO extends AbstractDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Map<?, ?> metadatos;
	
	private DatosGeneralDTO datos;

	public Map<?, ?> getMetadatos() {
		return metadatos;
	}

	public void setMetadatos(Map<?, ?> metadatos) {
		this.metadatos = metadatos;
	}

	public DatosGeneralDTO getDatos() {
		return datos;
	}

	public void setDatos(DatosGeneralDTO datos) {
		this.datos = datos;
	}

	
	
	
	
	

}
