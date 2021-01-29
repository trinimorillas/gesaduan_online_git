package es.mercadona.gesaduan.dto.common.error;


import java.util.Map;

import es.mercadona.gesaduan.dto.common.AbstractDTO;



public class OutputResponseErrorDTO extends AbstractDTO{

	private static final long serialVersionUID = 1L;

	private ErrorDTO error;
	
	private Map<?, ?> metadatos;

	public Map<?, ?> getMetadatos() {
		return metadatos;
	}

	public void setMetadatos(Map<?, ?> metadatos) {
		this.metadatos = metadatos;
	}

	public ErrorDTO getError() {
		return error;
	}

	public void setError(ErrorDTO error) {
		this.error = error;
	}

	

	
}
