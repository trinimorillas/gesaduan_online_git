package es.mercadona.gesaduan.dto.declaracionesdevalor.getdvsumario.v1.restfull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class OutputDeclaracionesDeValorDTO extends AbstractDTO{
	
	private static final long serialVersionUID = 1L;
	
	private Map<?, ?> metadatos;
	private List<DatosDvDTO> datos;
	
	
	public Map<?, ?> getMetadatos() {
		return metadatos;
	}
	public void setMetadatos(Map<?, ?> metadatos) {
		this.metadatos = metadatos;
	}
	public List<DatosDvDTO> getDatos() {
		return datos;
	}
	public void setDatos(List<DatosDvDTO> datos) {
		this.datos = datos;
	}
	
	
	
	

}
