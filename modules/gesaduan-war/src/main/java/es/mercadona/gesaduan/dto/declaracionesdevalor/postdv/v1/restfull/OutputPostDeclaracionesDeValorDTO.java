package es.mercadona.gesaduan.dto.declaracionesdevalor.postdv.v1.restfull;

import java.util.List;
import java.util.Map;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class OutputPostDeclaracionesDeValorDTO extends AbstractDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
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
