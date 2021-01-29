package es.mercadona.gesaduan.dto.equipotransporte.getequipotransportedetalle.v1.restfull;

import java.util.Map;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class OutputEquipoTransporteDetalleDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
	
	private Map<?, ?> metadatos;
	private DatosEquipoTransporteDetalleDTO datos;

	public Map<?, ?> getMetadatos() {
		return metadatos;
	}

	public void setMetadatos(Map<?, ?> metadatos) {
		this.metadatos = metadatos;
	}

	public DatosEquipoTransporteDetalleDTO getDatos() {
		return datos;
	}

	public void setDatos(DatosEquipoTransporteDetalleDTO datos) {
		this.datos = datos;
	}

}
