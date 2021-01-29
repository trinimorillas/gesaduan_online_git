package es.mercadona.gesaduan.dto.equipotransporte.cambiarestado.v1;

import java.util.List;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputCambiarEstadoDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
	
  	private Integer codigoEstado;
	private String forzarCambio;
	private List<EquipoDTO> equipo;
	
	public Integer getCodigoEstado() {
		return codigoEstado;
	}

	public void setCodigoEstado(Integer codigoEstado) {
		this.codigoEstado = codigoEstado;
	}

	public String getForzarCambio() {
		return forzarCambio;
	}
	
	public void setForzarCambio(String forzarCambio) {
		this.forzarCambio = forzarCambio;
	}
	
	public List<EquipoDTO> getEquipo() {
		return equipo;
	}
	
	public void setEquipo(List<EquipoDTO> equipo) {
		this.equipo = equipo;
	}

}
