package es.mercadona.gesaduan.dto.tarics.deletetaricrea.v1.restfull;

import java.util.Map;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class OutputDeleteTaricReaDTO extends AbstractDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Map<?, ?> metadatos;
	
	private DatosDeleteTaricReaDTO datos;

	public Map<?, ?> getMetadatos() {
		return metadatos;
	}

	public void setMetadatos(Map<?, ?> metadatos) {
		this.metadatos = metadatos;
	}

	public DatosDeleteTaricReaDTO getDatos() {
		return datos;
	}

	public void setDatos(DatosDeleteTaricReaDTO datos) {
		this.datos = datos;
	}
	
	
	

}
