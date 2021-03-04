package es.mercadona.gesaduan.dto.dosier.getdosierdetalle.v1;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputDatosDetalleDTO extends AbstractDTO implements Serializable{

	private static final long serialVersionUID = 1L;	
	
	InputDosierDetalleDTO datos;
	
	public InputDosierDetalleDTO getDatos() {
		return datos;
	}
	
	public void setDatos(InputDosierDetalleDTO datos) {
		this.datos = datos;
	}	
	
}
