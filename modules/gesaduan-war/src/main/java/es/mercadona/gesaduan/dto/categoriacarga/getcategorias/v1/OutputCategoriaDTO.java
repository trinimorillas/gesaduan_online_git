package es.mercadona.gesaduan.dto.categoriacarga.getcategorias.v1;

import java.util.List;
import java.util.Map;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class OutputCategoriaDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
		
	private Map<?, ?> metadatos;
	private List<DatosCategoriaDTO> datos;
	
	public Map<?, ?> getMetadatos() {
		return metadatos;
	}
	
	public void setMetadatos(Map<?, ?> metadatos) {
		this.metadatos = metadatos;
	}

	public List<DatosCategoriaDTO> getDatos() {
		return datos;
	}

	public void setDatos(List<DatosCategoriaDTO> datos) {
		this.datos = datos;
	}

}