package es.mercadona.gesaduan.dto.tarics.common.v1.restfull;

import java.util.List;
import java.util.Map;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class OutputTaricsDTO extends AbstractDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Map<?, ?> metadatos;

	public Map<?, ?> getMetadatos() {
		return metadatos;
	}

	public void setMetadatos(Map<?, ?> metadatos) {
		this.metadatos = metadatos;
	}
	
	private List<DatosTaricsDTO> datos;
	
		
	public List<DatosTaricsDTO> getDatos() {
		return datos;
	}
	public void setDatos(List<DatosTaricsDTO> datos) {
		this.datos = datos;
	}

}
