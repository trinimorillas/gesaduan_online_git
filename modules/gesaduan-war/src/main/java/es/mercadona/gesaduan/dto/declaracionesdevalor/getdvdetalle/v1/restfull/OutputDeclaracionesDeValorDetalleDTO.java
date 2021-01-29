package es.mercadona.gesaduan.dto.declaracionesdevalor.getdvdetalle.v1.restfull;

import java.util.Map;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class OutputDeclaracionesDeValorDetalleDTO extends AbstractDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private DatosDTO datos;
	
	private Map<?, ?> metadatos;

	public Map<?, ?> getMetadatos() {
		return metadatos;
	}

	public void setMetadatos(Map<?, ?> metadatos) {
		this.metadatos = metadatos;
	}

	
	public DatosDTO getDatos() {
		return datos;
	}
	public void setDatos(DatosDTO datos) {
		this.datos = datos;
	}
		




}
