package es.mercadona.gesaduan.dto.planembarques.getplanembarquedetalle.v1.restfull;

import java.util.List;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class EquipoDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
    	
    private Long codigoEquipo;
	private String matricula;
	private String codigoTransportista;
	private String nombreTransportista;
	private Integer codigoTemperatura;
	private String valorTemperatura;
	private Double numeroCapacidad;
	private Double numeroOcupacion;
	private String fechaCarga;
	private Integer codigoEstado;
	private String nombreEstado;
	private String codigoUsuarioCreacion;
	private List<SuministroDTO> suministro;
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

	public String getCodigoTransportista() {
		return codigoTransportista;
	}

	public void setCodigoTransportista(String codigoTransportista) {
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

	public Double getNumeroCapacidad() {
		return numeroCapacidad;
	}

	public void setNumeroCapacidad(Double numeroCapacidad) {
		this.numeroCapacidad = numeroCapacidad;
	}

	public Double getNumeroOcupacion() {
		return numeroOcupacion;
	}

	public void setNumeroOcupacion(Double numeroOcupacion) {
		this.numeroOcupacion = numeroOcupacion;
	}

	public String getFechaCarga() {
		return fechaCarga;
	}

	public void setFechaCarga(String fechaCarga) {
		this.fechaCarga = fechaCarga;
	}

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

	public String getCodigoUsuarioCreacion() {
		return codigoUsuarioCreacion;
	}

	public void setCodigoUsuarioCreacion(String codigoUsuarioCreacion) {
		this.codigoUsuarioCreacion = codigoUsuarioCreacion;
	}

	public List<SuministroDTO> getSuministro() {
		return suministro;
	}

	public void setSuministro(List<SuministroDTO> suministro) {
		this.suministro = suministro;
	}

	public List<CargaDTO> getCarga() {
		return carga;
	}

	public void setCarga(List<CargaDTO> carga) {
		this.carga = carga;
	}
	
	
}
