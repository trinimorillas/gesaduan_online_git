package es.mercadona.gesaduan.dto.planembarques.getplanembarques.v1;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;
import es.mercadona.gesaduan.dto.planembarques.getplanembarques.v1.InputPlanEmbarquesDTO;

public class InputDatosGetPlanEmbarqueDTO extends AbstractDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;	
	
	InputPlanEmbarquesDTO datos;	
	
	public InputPlanEmbarquesDTO getDatos() {
		return datos;
	}
	
	public void setDatos(InputPlanEmbarquesDTO datos) {
		this.datos = datos;
	}
	
}
