package es.mercadona.gesaduan.dto.productos.getproductos.v1.restfull;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class OutputProductosDTO extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Map<?, ?> metadatos;

	public Map<?, ?> getMetadatos() {
		return metadatos;
	}

	public void setMetadatos(Map<?, ?> metadatos) {
		this.metadatos = metadatos;
	}
	
	private List<DatosProductosDTO> datos;

	public List<DatosProductosDTO> getDatos() {
		return datos;
	}

	public void setDatos(List<DatosProductosDTO> datos) {
		this.datos = datos;
	}

}
