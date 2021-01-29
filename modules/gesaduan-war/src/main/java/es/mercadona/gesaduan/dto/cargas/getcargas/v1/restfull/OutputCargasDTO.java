package es.mercadona.gesaduan.dto.cargas.getcargas.v1.restfull;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class OutputCargasDTO extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<DatosCargasDTO> datos;
	
	private Map<?, ?> metadatos;

	public Map<?, ?> getMetadatos() {
		return metadatos;
	}

	public void setMetadatos(Map<?, ?> metadatos) {
		this.metadatos = metadatos;
	}

	public List<DatosCargasDTO> getDatos() {
		return datos;
	}

	public void setDatos(List<DatosCargasDTO> datos) {
		this.datos = datos;
	}

}