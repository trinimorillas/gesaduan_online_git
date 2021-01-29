package es.mercadona.gesaduan.dto.planembarques.putplanembarque.v1;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputPlanEmbarqueDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
	
  	private Long codigoEmbarque;
	private String fechaEmbarque;
	private Integer codigoBloqueOrigen;
	private Integer codigoPuertoEmbarque;
	private Integer codigoPuertoDesembarque;
	private Long codigoNaviera;
	private Integer codigoEstado;
	
	public Long getCodigoEmbarque() {
		return codigoEmbarque;
	}
	
	public void setCodigoEmbarque(Long codigoEmbarque) {
		this.codigoEmbarque = codigoEmbarque;
	}
	
	public String getFechaEmbarque() {
		return fechaEmbarque;
	}
	
	public void setFechaEmbarque(String fechaEmbarque) {
		this.fechaEmbarque = fechaEmbarque;
	}
	
	public Integer getCodigoBloqueOrigen() {
		return codigoBloqueOrigen;
	}
	
	public void setCodigoBloqueOrigen(Integer codigoBloqueOrigen) {
		this.codigoBloqueOrigen = codigoBloqueOrigen;
	}
	
	public Integer getCodigoPuertoEmbarque() {
		return codigoPuertoEmbarque;
	}
	
	public void setCodigoPuertoEmbarque(Integer codigoPuertoEmbarque) {
		this.codigoPuertoEmbarque = codigoPuertoEmbarque;
	}
	
	public Integer getCodigoPuertoDesembarque() {
		return codigoPuertoDesembarque;
	}
	
	public void setCodigoPuertoDesembarque(Integer codigoPuertoDesembarque) {
		this.codigoPuertoDesembarque = codigoPuertoDesembarque;
	}
	
	public Long getCodigoNaviera() {
		return codigoNaviera;
	}
	
	public void setCodigoNaviera(Long codigoNaviera) {
		this.codigoNaviera = codigoNaviera;
	}

	public Integer getCodigoEstado() {
		return codigoEstado;
	}

	public void setCodigoEstado(Integer codigoEstado) {
		this.codigoEstado = codigoEstado;
	}

}
