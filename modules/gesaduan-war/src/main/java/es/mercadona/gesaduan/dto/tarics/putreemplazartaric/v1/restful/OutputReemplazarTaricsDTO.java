package es.mercadona.gesaduan.dto.tarics.putreemplazartaric.v1.restful;

import java.util.Map;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class OutputReemplazarTaricsDTO extends AbstractDTO{

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

}
