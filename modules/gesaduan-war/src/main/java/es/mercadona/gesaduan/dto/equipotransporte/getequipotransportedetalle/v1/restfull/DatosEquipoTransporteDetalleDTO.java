package es.mercadona.gesaduan.dto.equipotransporte.getequipotransportedetalle.v1.restfull;

import java.util.List;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class DatosEquipoTransporteDetalleDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
    
    private Long codigoEquipo;
	private Long codigoEmbarque;
	private String matricula;
	private String codigoTransportista;
	private String nombreTransportista;
	private Integer codigoTemperatura;
	private String valorTemperatura;
	private Double capacidad;
	private Double ocupacion;
	private Integer codigoEstado;
	private String nombreEstado;
	private String diaCarga;
	private String horaCarga;
	private String observaciones;
	private String codigoUsuarioCreacion;
	private String mcaExistenContenedoresFacturados;
	private Integer codigoEstadoDocumentacion;
	private String nombreEstadoDocumentacion;
	private List<CargaDTO> carga;
	
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

	public String getDiaCarga() {
		return diaCarga;
	}
	
	public void setDiaCarga(String diaCarga) {
		this.diaCarga = diaCarga;
	}
	
	public String getHoraCarga() {
		return horaCarga;
	}
	
	public void setHoraCarga(String horaCarga) {
		this.horaCarga = horaCarga;
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

	public Integer getCodigoEstadoDocumentacion() {
		return codigoEstadoDocumentacion;
	}

	public void setCodigoEstadoDocumentacion(Integer codigoEstadoDocumentacion) {
		this.codigoEstadoDocumentacion = codigoEstadoDocumentacion;
	}

	public String getNombreEstadoDocumentacion() {
		return nombreEstadoDocumentacion;
	}

	public void setNombreEstadoDocumentacion(String nombreEstadoDocumentacion) {
		this.nombreEstadoDocumentacion = nombreEstadoDocumentacion;
	}

	public List<CargaDTO> getCarga() {
		return carga;
	}
	
	public void setCarga(List<CargaDTO> carga) {
		this.carga = carga;
	}

	public String getMcaExistenContenedoresFacturados() {
		return mcaExistenContenedoresFacturados;
	}

	public void setMcaExistenContenedoresFacturados(String mcaExistenContenedoresFacturados) {
		this.mcaExistenContenedoresFacturados = mcaExistenContenedoresFacturados;
	}

}
