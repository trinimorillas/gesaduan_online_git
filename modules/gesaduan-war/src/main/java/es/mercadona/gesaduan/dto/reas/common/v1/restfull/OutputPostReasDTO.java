package es.mercadona.gesaduan.dto.reas.common.v1.restfull;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class OutputPostReasDTO extends AbstractDTO implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private Map <?, ?> metadatos;
	private CodigoReaDTO datos;
	
	public Map<?, ?> getMetadatos() {
		return metadatos;
	}
	public void setMetadatos(Map<?, ?> metadatos) {
		this.metadatos = metadatos;
	}
	public CodigoReaDTO getDatos() {
		return datos;
	}
	public void setDatos(CodigoReaDTO datos) {
		this.datos = datos;
	}
	
	
	
	
	

}
