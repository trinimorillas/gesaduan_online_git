package es.mercadona.gesaduan.dto.estadoplanembarque.getestadoplanembarque.v1;

import java.util.List;
import java.util.Map;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class OutputEstadoPlanEmbarqueDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
		
	private Map<?, ?> metadatos;
	private List<DatosEstadoPlanEmbarqueDTO> datos;
	
	public Map<?, ?> getMetadatos() {
		return metadatos;
	}
	
	public void setMetadatos(Map<?, ?> metadatos) {
		this.metadatos = metadatos;
	}

	public List<DatosEstadoPlanEmbarqueDTO> getDatos() {
		return datos;
	}

	public void setDatos(List<DatosEstadoPlanEmbarqueDTO> datos) {
		this.datos = datos;
	}

}