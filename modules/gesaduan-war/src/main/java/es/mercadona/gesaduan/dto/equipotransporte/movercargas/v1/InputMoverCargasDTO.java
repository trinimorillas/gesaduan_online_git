package es.mercadona.gesaduan.dto.equipotransporte.movercargas.v1;

import java.util.List;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputMoverCargasDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
	
  	private Long codigoEquipoOrigen;
	private Long codigoEquipoDestino;
	private List<CargasDTO> cargas;
	
	public Long getCodigoEquipoOrigen() {
		return codigoEquipoOrigen;
	}
	
	public void setCodigoEquipoOrigen(Long codigoEquipoOrigen) {
		this.codigoEquipoOrigen = codigoEquipoOrigen;
	}
	
	public Long getCodigoEquipoDestino() {
		return codigoEquipoDestino;
	}
	
	public void setCodigoEquipoDestino(Long codigoEquipoDestino) {
		this.codigoEquipoDestino = codigoEquipoDestino;
	}
	
	public List<CargasDTO> getCargas() {
		return cargas;
	}
	
	public void setCargas(List<CargasDTO> cargas) {
		this.cargas = cargas;
	}	

}
