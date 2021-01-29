package es.mercadona.gesaduan.dto.reas.getreasdetalle.v1.restfull;

import java.util.List;
import java.util.Map;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class OutputReasDetalleDTO extends AbstractDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Map<?,?> metadatos;
	private List<DatosDetalleDTO> datos;
	
	
	public Map<?, ?> getMetadatos() {
		return metadatos;
	}
	public void setMetadatos(Map<?, ?> metadatos) {
		this.metadatos = metadatos;
	}
	public List<DatosDetalleDTO> getDatos() {
		return datos;
	}
	public void setDatos(List<DatosDetalleDTO> datos) {
		this.datos = datos;
	}
	
	
	
	
	

}
