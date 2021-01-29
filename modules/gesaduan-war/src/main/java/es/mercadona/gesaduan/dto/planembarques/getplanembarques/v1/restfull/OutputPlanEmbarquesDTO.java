package es.mercadona.gesaduan.dto.planembarques.getplanembarques.v1.restfull;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class OutputPlanEmbarquesDTO extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<DatosPlanEmbarquesDTO> datos;
	
	private Map<?, ?> metadatos;

	public Map<?, ?> getMetadatos() {
		return metadatos;
	}

	public void setMetadatos(Map<?, ?> metadatos) {
		this.metadatos = metadatos;
	}

	public List<DatosPlanEmbarquesDTO> getDatos() {
		return datos;
	}

	public void setDatos(List<DatosPlanEmbarquesDTO> datos) {
		this.datos = datos;
	}

}
