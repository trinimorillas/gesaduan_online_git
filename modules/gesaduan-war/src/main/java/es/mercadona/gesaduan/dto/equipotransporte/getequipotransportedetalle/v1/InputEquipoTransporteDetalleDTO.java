package es.mercadona.gesaduan.dto.equipotransporte.getequipotransportedetalle.v1;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputEquipoTransporteDetalleDTO extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;	
	private Long codigoEquipo;
	private String mcaIncluyeContenedores;
	private String orden;
	
	public Long getCodigoEquipo() {
		return codigoEquipo;
	}

	public void setCodigoEquipo(Long codigoEquipo) {
		this.codigoEquipo = codigoEquipo;
	}

	public String getOrden() {
		return orden;
	}

	public void setOrden(String orden) {
		this.orden = orden;
	}

	public String getMcaIncluyeContenedores() {
		return mcaIncluyeContenedores;
	}

	public void setMcaIncluyeContenedores(String mcaIncluyeContenedores) {
		this.mcaIncluyeContenedores = mcaIncluyeContenedores;
	}

}
