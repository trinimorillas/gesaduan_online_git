package es.mercadona.gesaduan.dto.bloqueslogisticos.getbloqueslogisticos.v1;

import java.util.List;
import java.util.Map;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class OutputBloquesLogisticosDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
		
	private Map<?, ?> metadatos;
	private List<DatosBloquesLogisticosDTO> datos;
	
	public Map<?, ?> getMetadatos() {
		return metadatos;
	}
	
	public void setMetadatos(Map<?, ?> metadatos) {
		this.metadatos = metadatos;
	}

	public List<DatosBloquesLogisticosDTO> getDatos() {
		return datos;
	}

	public void setDatos(List<DatosBloquesLogisticosDTO> datos) {
		this.datos = datos;
	}

}