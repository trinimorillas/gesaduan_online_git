package es.mercadona.gesaduan.dto.equipotransporte.putequipotransporte.v1;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputEquipoTransporteDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
	
  	private Long codigoEquipo;
	private Long codigoEmbarque;
	private String matricula;
	private String codigoTransportista;
	private Integer codigoTemperatura;
	private Integer capacidad;
	private String fechaCarga;
	private String observaciones;
	private Integer codigoEstado;
	private Integer ocupacion;
	
	public Long getCodigoEquipo() {
		return codigoEquipo;
	}
	
	public void setCodigoEquipo(Long codigoEquipo) {
		this.codigoEquipo = codigoEquipo;
	}
	
	public Long getCodigoEmbarque() {
		return codigoEmbarque;
	}
	
	public void setCodigoEmbarque(Long codigoEmbarque) {
		this.codigoEmbarque = codigoEmbarque;
	}
	
	public String getMatricula() {
		return matricula;
	}
	
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	public String getCodigoTransportista() {
		return codigoTransportista;
	}
	
	public void setCodigoTransportista(String codigoTransportista) {
		this.codigoTransportista = codigoTransportista;
	}
	
	public Integer getCodigoTemperatura() {
		return codigoTemperatura;
	}
	
	public void setCodigoTemperatura(Integer codigoTemperatura) {
		this.codigoTemperatura = codigoTemperatura;
	}
	
	public Integer getCapacidad() {
		return capacidad;
	}
	
	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}
	
	public String getFechaCarga() {
		return fechaCarga;
	}
	
	public void setFechaCarga(String fechaCarga) {
		this.fechaCarga = fechaCarga;
	}
	
	public String getObservaciones() {
		return observaciones;
	}
	
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Integer getCodigoEstado() {
		return codigoEstado;
	}

	public void setCodigoEstado(Integer codigoEstado) {
		this.codigoEstado = codigoEstado;
	}

	public Integer getOcupacion() {
		return ocupacion;
	}

	public void setOcupacion(Integer ocupacion) {
		this.ocupacion = ocupacion;
	}

}
