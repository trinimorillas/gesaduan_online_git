package es.mercadona.gesaduan.dto.equipotransporte.getequipostransporte.v1;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputDatosEquipoTransporteDTO extends AbstractDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;	
	
	InputEquiposTransporteDTO datos;
	
	public InputEquiposTransporteDTO getDatos() {
		return datos;
	}
	
	public void setDatos(InputEquiposTransporteDTO datos) {
		this.datos = datos;
	}
	
}