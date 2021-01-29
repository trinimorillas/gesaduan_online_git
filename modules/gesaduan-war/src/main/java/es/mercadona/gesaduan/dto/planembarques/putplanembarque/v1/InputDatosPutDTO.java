package es.mercadona.gesaduan.dto.planembarques.putplanembarque.v1;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputDatosPutDTO extends AbstractDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;	
	
	InputMetadatosPutDTO metadatos;
	InputPlanEmbarqueDTO datos;
	
	public InputMetadatosPutDTO getMetadatos() {
		return metadatos;
	}
	public void setMetadatos(InputMetadatosPutDTO metadatos) {
		this.metadatos = metadatos;
	}
	public InputPlanEmbarqueDTO getDatos() {
		return datos;
	}
	public void setDatos(InputPlanEmbarqueDTO datos) {
		this.datos = datos;
	}	
	
}
