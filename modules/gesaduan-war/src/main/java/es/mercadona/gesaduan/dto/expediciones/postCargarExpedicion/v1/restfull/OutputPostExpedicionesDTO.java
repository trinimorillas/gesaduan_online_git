package es.mercadona.gesaduan.dto.expediciones.postCargarExpedicion.v1.restfull;

import java.util.Map;

import es.mercadona.gesaduan.dto.declaracionesdevalor.postdv.v1.restfull.DVInsertPKDTO;

public class OutputPostExpedicionesDTO {
		
	
	private DVInsertPKDTO datos;

	private Map<?, ?> metadatos;

	public Map<?, ?> getMetadatos() {
		return metadatos;
	}

	public void setMetadatos(Map<?, ?> metadatos) {
		this.metadatos = metadatos;
	}


	public DVInsertPKDTO getNumeroDeclaracion() {
		return datos;
	}

	public void setNumeroDeclaracion(DVInsertPKDTO numeroDeclaracion) {
		this.datos = numeroDeclaracion;
	}
	
}
