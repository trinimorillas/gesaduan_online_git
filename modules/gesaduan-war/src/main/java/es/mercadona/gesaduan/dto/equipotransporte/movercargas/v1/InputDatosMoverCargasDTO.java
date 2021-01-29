package es.mercadona.gesaduan.dto.equipotransporte.movercargas.v1;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputDatosMoverCargasDTO extends AbstractDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;	
	
	InputMetadatosMoverCargasDTO metadatos;
	InputMoverCargasDTO datos;
	
	public InputMetadatosMoverCargasDTO getMetadatos() {
		return metadatos;
	}
	
	public void setMetadatos(InputMetadatosMoverCargasDTO metadatos) {
		this.metadatos = metadatos;
	}
	
	public InputMoverCargasDTO getDatos() {
		return datos;
	}
	
	public void setDatos(InputMoverCargasDTO datos) {
		this.datos = datos;
	}	
	
}
