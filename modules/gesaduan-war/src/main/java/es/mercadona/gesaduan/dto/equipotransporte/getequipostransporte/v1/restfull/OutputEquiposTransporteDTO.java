package es.mercadona.gesaduan.dto.equipotransporte.getequipostransporte.v1.restfull;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class OutputEquiposTransporteDTO extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<DatosEquiposTransporteDTO> datos;
	
	private Map<?, ?> metadatos;

	public Map<?, ?> getMetadatos() {
		return metadatos;
	}

	public void setMetadatos(Map<?, ?> metadatos) {
		this.metadatos = metadatos;
	}

	public List<DatosEquiposTransporteDTO> getDatos() {
		return datos;
	}

	public void setDatos(List<DatosEquiposTransporteDTO> datos) {
		this.datos = datos;
	}

}
