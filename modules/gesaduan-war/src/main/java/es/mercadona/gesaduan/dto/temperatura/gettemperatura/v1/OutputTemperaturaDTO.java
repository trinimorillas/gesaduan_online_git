package es.mercadona.gesaduan.dto.temperatura.gettemperatura.v1;

import java.util.List;
import java.util.Map;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class OutputTemperaturaDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
		
	private Map<?, ?> metadatos;
	private List<DatosTemperaturaDTO> datos;
	
	public Map<?, ?> getMetadatos() {
		return metadatos;
	}
	
	public void setMetadatos(Map<?, ?> metadatos) {
		this.metadatos = metadatos;
	}

	public List<DatosTemperaturaDTO> getDatos() {
		return datos;
	}

	public void setDatos(List<DatosTemperaturaDTO> datos) {
		this.datos = datos;
	}

}