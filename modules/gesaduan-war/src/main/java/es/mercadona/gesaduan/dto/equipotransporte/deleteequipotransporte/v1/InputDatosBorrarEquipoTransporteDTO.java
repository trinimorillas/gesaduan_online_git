package es.mercadona.gesaduan.dto.equipotransporte.deleteequipotransporte.v1;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputDatosBorrarEquipoTransporteDTO extends AbstractDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;	
	
	InputMetadatosBorrarEquipoTransporteDTO metadatos;
	InputEquipoTransporteDeleteDTO datos;
	
	public InputMetadatosBorrarEquipoTransporteDTO getMetadatos() {
		return metadatos;
	}
	
	public void setMetadatos(InputMetadatosBorrarEquipoTransporteDTO metadatos) {
		this.metadatos = metadatos;
	}
	
	public InputEquipoTransporteDeleteDTO getDatos() {
		return datos;
	}
	
	public void setDatos(InputEquipoTransporteDeleteDTO datos) {
		this.datos = datos;
	}	
	
}
