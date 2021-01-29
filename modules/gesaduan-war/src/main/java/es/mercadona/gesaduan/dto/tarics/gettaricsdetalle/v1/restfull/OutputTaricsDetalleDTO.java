package es.mercadona.gesaduan.dto.tarics.gettaricsdetalle.v1.restfull;

import java.util.List;
import java.util.Map;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class OutputTaricsDetalleDTO extends AbstractDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Map<?, ?> metadatos;
	private DatosTaricsDTO datos;

	public Map<?, ?> getMetadatos() {
		return metadatos;
	}

	public void setMetadatos(Map<?, ?> metadatos) {
		this.metadatos = metadatos;
	}

	public DatosTaricsDTO getDatos() {
		return datos;
	}

	public void setDatos(DatosTaricsDTO datos) {
		this.datos = datos;
	}

	

	
	

}
