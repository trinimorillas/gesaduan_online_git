package es.mercadona.gesaduan.dto.equipotransporte.putequipotransporte.v1;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputDatosPutDTO extends AbstractDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;	
	
	InputMetadatosPutDTO metadatos;
	InputEquipoTransporteDTO datos;
	
	public InputMetadatosPutDTO getMetadatos() {
		return metadatos;
	}
	
	public void setMetadatos(InputMetadatosPutDTO metadatos) {
		this.metadatos = metadatos;
	}
	
	public InputEquipoTransporteDTO getDatos() {
		return datos;
	}
	
	public void setDatos(InputEquipoTransporteDTO datos) {
		this.datos = datos;
	}	
	
}
