package es.mercadona.gesaduan.dto.equipotransporte.putequipotransporte.v1.restfull;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class OutputEquipoTransportePutDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
		
	private DatosEquipoTransportePutDTO datos;

	public DatosEquipoTransportePutDTO getDatos() {
		return datos;
	}

	public void setDatos(DatosEquipoTransportePutDTO datos) {
		this.datos = datos;
	}

}
