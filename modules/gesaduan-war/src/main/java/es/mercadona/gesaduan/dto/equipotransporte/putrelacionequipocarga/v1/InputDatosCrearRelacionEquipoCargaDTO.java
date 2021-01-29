package es.mercadona.gesaduan.dto.equipotransporte.putrelacionequipocarga.v1;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputDatosCrearRelacionEquipoCargaDTO extends AbstractDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;	
	
	private InputMetadatosDTO metadatos;
	InputDatosEquipoCargaDTO datos;
	
	public InputDatosEquipoCargaDTO getDatos() {
		return datos;
	}
	
	public void setDatos(InputDatosEquipoCargaDTO datos) {
		this.datos = datos;
	}

	public InputMetadatosDTO getMetadatos() {
		return metadatos;
	}

	public void setMetadatos(InputMetadatosDTO metadatos) {
		this.metadatos = metadatos;
	}	
	
}
