package es.mercadona.gesaduan.dto.cargas.getcargas.v1;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputDatosCargasDTO extends AbstractDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;	
	
	InputCargasDTO datos;
	
	public InputCargasDTO getDatos() {
		return datos;
	}
	
	public void setDatos(InputCargasDTO datos) {
		this.datos = datos;
	}
	
}