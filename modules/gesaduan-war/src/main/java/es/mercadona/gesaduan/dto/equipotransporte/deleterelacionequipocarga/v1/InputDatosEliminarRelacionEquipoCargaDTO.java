package es.mercadona.gesaduan.dto.equipotransporte.deleterelacionequipocarga.v1;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputDatosEliminarRelacionEquipoCargaDTO extends AbstractDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;	
		
	InputEliminarRelacionEquipoCargaDTO datos;
	InputMetadatosBorrarEquipoCargaDTO metadatos;
	
	public InputEliminarRelacionEquipoCargaDTO getDatos() {
		return datos;
	}
	
	public void setDatos(InputEliminarRelacionEquipoCargaDTO datos) {
		this.datos = datos;
	}
	
	public InputMetadatosBorrarEquipoCargaDTO getMetadatos() {
		return metadatos;
	}
	
	public void setMetadatos(InputMetadatosBorrarEquipoCargaDTO metadatos) {
		this.metadatos = metadatos;
	}
	
}
