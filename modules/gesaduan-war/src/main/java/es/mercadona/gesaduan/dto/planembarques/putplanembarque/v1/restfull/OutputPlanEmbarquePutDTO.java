package es.mercadona.gesaduan.dto.planembarques.putplanembarque.v1.restfull;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class OutputPlanEmbarquePutDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;		
	private DatosPlanEmbarquePutDTO datos;	
		
	public DatosPlanEmbarquePutDTO getDatos() {
		return datos;
	}
	
	public void setDatos(DatosPlanEmbarquePutDTO datos) {
		this.datos = datos;
	}

}
