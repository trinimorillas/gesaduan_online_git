package es.mercadona.gesaduan.dto.tarics.posttaricrea.v1.restfull;

import java.util.Map;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class OutputTaricReaDTO extends AbstractDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Map<?, ?> metadatos;
	
	private DatosTaricReaDTO datos;

	public Map<?, ?> getMetadatos() {
		return metadatos;
	}

	public void setMetadatos(Map<?, ?> metadatos) {
		this.metadatos = metadatos;
	}

	public DatosTaricReaDTO getDatos() {
		return datos;
	}

	public void setDatos(DatosTaricReaDTO datos) {
		this.datos = datos;
	}
	
	
	

}
