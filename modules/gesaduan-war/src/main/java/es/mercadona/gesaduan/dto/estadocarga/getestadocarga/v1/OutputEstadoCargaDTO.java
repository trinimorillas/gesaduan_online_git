package es.mercadona.gesaduan.dto.estadocarga.getestadocarga.v1;

import java.util.List;
import java.util.Map;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class OutputEstadoCargaDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
		
	private Map<?, ?> metadatos;
	private List<DatosEstadoCargaDTO> datos;
	
	public Map<?, ?> getMetadatos() {
		return metadatos;
	}
	
	public void setMetadatos(Map<?, ?> metadatos) {
		this.metadatos = metadatos;
	}

	public List<DatosEstadoCargaDTO> getDatos() {
		return datos;
	}

	public void setDatos(List<DatosEstadoCargaDTO> datos) {
		this.datos = datos;
	}

}