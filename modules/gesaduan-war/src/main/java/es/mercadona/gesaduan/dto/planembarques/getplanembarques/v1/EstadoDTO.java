package es.mercadona.gesaduan.dto.planembarques.getplanembarques.v1;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class EstadoDTO extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;	
	private Integer codigoEstado;
	
	public Integer getCodigoEstado() {
		return codigoEstado;
	}
	
	public void setCodigoEstado(Integer codigoEstado) {
		this.codigoEstado = codigoEstado;
	}

}
