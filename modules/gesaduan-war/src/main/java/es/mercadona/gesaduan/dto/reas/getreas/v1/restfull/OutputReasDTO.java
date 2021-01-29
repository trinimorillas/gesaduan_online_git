package es.mercadona.gesaduan.dto.reas.getreas.v1.restfull;

import java.util.List;
import java.util.Map;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class OutputReasDTO extends AbstractDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Map<?, ?> metadatos;
	private List<DatosReasDTO> datos;
	
	

	public Map<?, ?> getMetadatos() {
		return metadatos;
	}

	public void setMetadatos(Map<?, ?> metadatos) {
		this.metadatos = metadatos;
	}

	
	public List<DatosReasDTO> getDatos() {
		return datos;
	}
	public void setDatos(List<DatosReasDTO> datos) {
		this.datos = datos;
	}
	
	
	

}
