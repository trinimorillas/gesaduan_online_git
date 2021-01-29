package es.mercadona.gesaduan.dto.planembarques.getplanembarquedetalle.v1.restfull;

import java.util.Map;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class OutputPlanEmbarqueDetalleDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
	
	private Map<?, ?> metadatos;
	private DatosPlanEmbarqueDetalleDTO datos;

	public Map<?, ?> getMetadatos() {
		return metadatos;
	}

	public void setMetadatos(Map<?, ?> metadatos) {
		this.metadatos = metadatos;
	}

	public DatosPlanEmbarqueDetalleDTO getDatos() {
		return datos;
	}

	public void setDatos(DatosPlanEmbarqueDetalleDTO datos) {
		this.datos = datos;
	}

}
