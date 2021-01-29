package es.mercadona.gesaduan.dto.equipotransporte.getequipotransportedetalle.v1.restfull;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class EquipoInputDTO extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;	
	private Integer codigoEquipo;
	
	public Integer getCodigoEquipo() {
		return codigoEquipo;
	}
	public void setCodigoEquipo(Integer codigoEquipo) {
		this.codigoEquipo = codigoEquipo;
	}
	
	
}
