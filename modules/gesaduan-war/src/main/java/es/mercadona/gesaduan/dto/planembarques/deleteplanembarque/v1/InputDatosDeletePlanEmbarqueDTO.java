package es.mercadona.gesaduan.dto.planembarques.deleteplanembarque.v1;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputDatosDeletePlanEmbarqueDTO extends AbstractDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;	
	
	InputMetadatosDeletePlanEmbarqueDTO metadatos;
	InputPlanEmbarqueDeleteDTO datos;
	
	public InputMetadatosDeletePlanEmbarqueDTO getMetadatos() {
		return metadatos;
	}
	public void setMetadatos(InputMetadatosDeletePlanEmbarqueDTO metadatos) {
		this.metadatos = metadatos;
	}
	public InputPlanEmbarqueDeleteDTO getDatos() {
		return datos;
	}
	public void setDatos(InputPlanEmbarqueDeleteDTO datos) {
		this.datos = datos;
	}	
	
}
