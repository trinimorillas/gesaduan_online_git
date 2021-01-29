package es.mercadona.gesaduan.dto.declaracionesdevalor.putdvinddescarga.v1.restfull;

import java.util.List;
import java.util.Map;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class OutputDeclaracionesDeValorEstadoDescargaDTO  extends AbstractDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	private DeclaracionesDeValorEstadoDescargaDTO datos;
	
	private Map<?, ?> metadatos;

	public Map<?, ?> getMetadatos() {
		return metadatos;
	}

	public void setMetadatos(Map<?, ?> metadatos) {
		this.metadatos = metadatos;
	}

	
	public DeclaracionesDeValorEstadoDescargaDTO getDatos() {
		return datos;
	}
	public void setDatos(DeclaracionesDeValorEstadoDescargaDTO datos) {
		this.datos = datos;
	}
		

}
