package es.mercadona.gesaduan.dto.planembarques.getplanembarquedetalle.v1;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputDatosDetalleDTO extends AbstractDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;	
	
	InputPlanEmbarqueDetalleDTO datos;
	
	public InputPlanEmbarqueDetalleDTO getDatos() {
		return datos;
	}
	
	public void setDatos(InputPlanEmbarqueDetalleDTO datos) {
		this.datos = datos;
	}	
	
}
