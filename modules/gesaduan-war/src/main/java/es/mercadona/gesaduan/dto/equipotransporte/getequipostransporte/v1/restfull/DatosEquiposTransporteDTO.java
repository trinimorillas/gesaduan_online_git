package es.mercadona.gesaduan.dto.equipotransporte.getequipostransporte.v1.restfull;

import java.io.Serializable;
import java.util.List;

import es.mercadona.gesaduan.dto.common.AbstractDTO;
import es.mercadona.gesaduan.dto.equipotransporte.getequipostransporte.v1.TipoCargaDTO;
import es.mercadona.gesaduan.dto.equipotransporte.getequipotransportedetalle.v1.restfull.CargaDTO;
import es.mercadona.gesaduan.dto.planembarques.getplanembarquedetalle.v1.restfull.SuministroDTO;

public class DatosEquiposTransporteDTO extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long codigoEquipo;
	private String matricula;
	private Long codigoTransportista;
	private String nombreTransportista;
	private Integer codigoTemperatura;
	private String valorTemperatura;
	private Double capacidad;
	private Double ocupacion;
	private String codigoEstado;
	private String nombreEstado;
	private String fechaCarga;
	private String observaciones;
	private String codigoUsuarioCreacion;
	private Long codigoEmbarque;
	private Integer codigoPuertoDesembarque;
	private String nombrePuertoDesembarque;
	private String fechaEmbarque;
	private List<SuministroDTO> suministro;
	private List<TipoCargaDTO> tipoCarga;
	private List<CargaDTO> carga;		
	
	public Long getCodigoEquipo() {
		return codigoEquipo;
	}
	
	public void setCodigoEquipo(Long codigoEquipo) {
		this.codigoEquipo = codigoEquipo;
	}
	
	public String getMatricula() {
		return matricula;
	}
	
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	public Long getCodigoTransportista() {
		return codigoTransportista;
	}
	
	public void setCodigoTransportista(Long codigoTransportista) {
		this.codigoTransportista = codigoTransportista;
	}
	
	public String getNombreTransportista() {
		return nombreTransportista;
	}
	
	public void setNombreTransportista(String nombreTransportista) {
		this.nombreTransportista = nombreTransportista;
	}
	
	public Integer getCodigoTemperatura() {
		return codigoTemperatura;
	}
	
	public void setCodigoTemperatura(Integer codigoTemperatura) {
		this.codigoTemperatura = codigoTemperatura;
	}
	
	public String getValorTemperatura() {
		return valorTemperatura;
	}
	
	public void setValorTemperatura(String valorTemperatura) {
		this.valorTemperatura = valorTemperatura;
	}
	
	public Double getCapacidad() {
		return capacidad;
	}
	
	public void setCapacidad(Double capacidad) {
		this.capacidad = capacidad;
	}
	
	public Double getOcupacion() {
		return ocupacion;
	}
	
	public void setOcupacion(Double ocupacion) {
		this.ocupacion = ocupacion;
	}
	
	public String getCodigoEstado() {
		return codigoEstado;
	}

	public void setCodigoEstado(String codigoEstado) {
		this.codigoEstado = codigoEstado;
	}

	public String getNombreEstado() {
		return nombreEstado;
	}

	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
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
	
	public String getCodigoUsuarioCreacion() {
		return codigoUsuarioCreacion;
	}
	
	public void setCodigoUsuarioCreacion(String codigoUsuarioCreacion) {
		this.codigoUsuarioCreacion = codigoUsuarioCreacion;
	}

	public Long getCodigoEmbarque() {
		return codigoEmbarque;
	}

	public void setCodigoEmbarque(Long codigoEmbarque) {
		this.codigoEmbarque = codigoEmbarque;
	}

	public Integer getCodigoPuertoDesembarque() {
		return codigoPuertoDesembarque;
	}

	public void setCodigoPuertoDesembarque(Integer codigoPuertoDesembarque) {
		this.codigoPuertoDesembarque = codigoPuertoDesembarque;
	}

	public String getNombrePuertoDesembarque() {
		return nombrePuertoDesembarque;
	}

	public void setNombrePuertoDesembarque(String nombrePuertoDesembarque) {
		this.nombrePuertoDesembarque = nombrePuertoDesembarque;
	}

	public String getFechaEmbarque() {
		return fechaEmbarque;
	}

	public void setFechaEmbarque(String fechaEmbarque) {
		this.fechaEmbarque = fechaEmbarque;
	}

	public List<SuministroDTO> getSuministro() {
		return suministro;
	}
	
	public void setSuministro(List<SuministroDTO> suministro) {
		this.suministro = suministro;
	}

	public List<TipoCargaDTO> getTipoCarga() {
		return tipoCarga;
	}

	public void setTipoCarga(List<TipoCargaDTO> tipoCarga) {
		this.tipoCarga = tipoCarga;
	}

	public List<CargaDTO> getCarga() {
		return carga;
	}

	public void setCarga(List<CargaDTO> carga) {
		this.carga = carga;
	}
	
	

}
