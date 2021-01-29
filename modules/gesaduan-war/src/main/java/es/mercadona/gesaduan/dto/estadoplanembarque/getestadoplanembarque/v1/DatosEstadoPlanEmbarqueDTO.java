package es.mercadona.gesaduan.dto.estadoplanembarque.getestadoplanembarque.v1;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class DatosEstadoPlanEmbarqueDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
	
	private Integer codigoEstado;
	private String nombreEstado;
	
	public Integer getCodigoEstado() {
		return codigoEstado;
	}
	
	public void setCodigoEstado(Integer codigoEstado) {
		this.codigoEstado = codigoEstado;
	}
	
	public String getNombreEstado() {
		return nombreEstado;
	}
	
	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}

}