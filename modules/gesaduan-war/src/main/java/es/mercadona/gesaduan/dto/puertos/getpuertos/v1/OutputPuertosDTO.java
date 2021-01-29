package es.mercadona.gesaduan.dto.puertos.getpuertos.v1;

import java.util.List;
import java.util.Map;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class OutputPuertosDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
		
	private Map<?, ?> metadatos;
	private List<DatosPuertosDTO> datos;
	
	public Map<?, ?> getMetadatos() {
		return metadatos;
	}
	
	public void setMetadatos(Map<?, ?> metadatos) {
		this.metadatos = metadatos;
	}

	public List<DatosPuertosDTO> getDatos() {
		return datos;
	}

	public void setDatos(List<DatosPuertosDTO> datos) {
		this.datos = datos;
	}

}