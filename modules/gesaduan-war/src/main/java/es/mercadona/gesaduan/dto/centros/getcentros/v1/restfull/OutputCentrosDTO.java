package es.mercadona.gesaduan.dto.centros.getcentros.v1.restfull;

import java.util.List;
import java.util.Map;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class OutputCentrosDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
		
	private Map<?, ?> metadatos;
	private List<DatosCentrosDTO> datos;
	
	public Map<?, ?> getMetadatos() {
		return metadatos;
	}
	
	public void setMetadatos(Map<?, ?> metadatos) {
		this.metadatos = metadatos;
	}

	public List<DatosCentrosDTO> getDatos() {
		return datos;
	}

	public void setDatos(List<DatosCentrosDTO> datos) {
		this.datos = datos;
	}

}