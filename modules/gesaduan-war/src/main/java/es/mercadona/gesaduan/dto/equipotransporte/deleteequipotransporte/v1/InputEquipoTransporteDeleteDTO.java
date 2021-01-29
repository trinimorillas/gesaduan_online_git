package es.mercadona.gesaduan.dto.equipotransporte.deleteequipotransporte.v1;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputEquipoTransporteDeleteDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
	
	private Long codigoEquipo;
	
	public Long getCodigoEquipo() {
		return codigoEquipo;
	}
	
	public void setCodigoEquipo(Long codigoEquipo) {
		this.codigoEquipo = codigoEquipo;
	}

}
