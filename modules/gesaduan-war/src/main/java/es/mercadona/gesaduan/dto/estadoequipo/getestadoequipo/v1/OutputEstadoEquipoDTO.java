package es.mercadona.gesaduan.dto.estadoequipo.getestadoequipo.v1;

import java.util.List;
import java.util.Map;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class OutputEstadoEquipoDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
		
	private Map<?, ?> metadatos;
	private List<DatosEstadoEquipoDTO> datos;
	
	public Map<?, ?> getMetadatos() {
		return metadatos;
	}
	
	public void setMetadatos(Map<?, ?> metadatos) {
		this.metadatos = metadatos;
	}

	public List<DatosEstadoEquipoDTO> getDatos() {
		return datos;
	}

	public void setDatos(List<DatosEstadoEquipoDTO> datos) {
		this.datos = datos;
	}

}