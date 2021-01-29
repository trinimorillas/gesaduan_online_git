package es.mercadona.gesaduan.dto.planembarques.cambiarestado.v1;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputCambiarEstadoDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
	private Long codigoEmbarque;
	private Integer codigoEstado;

	public Long getCodigoEmbarque() {
		return codigoEmbarque;
	}

	public void setCodigoEmbarque(Long codigoEmbarque) {
		this.codigoEmbarque = codigoEmbarque;
	}

	public Integer getCodigoEstado() {
		return codigoEstado;
	}

	public void setCodigoEstado(Integer codigoEstado) {
		this.codigoEstado = codigoEstado;
	}

}
