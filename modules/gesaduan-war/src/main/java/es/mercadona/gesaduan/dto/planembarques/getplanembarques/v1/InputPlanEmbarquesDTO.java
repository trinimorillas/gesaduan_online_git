package es.mercadona.gesaduan.dto.planembarques.getplanembarques.v1;

import java.util.List;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputPlanEmbarquesDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
	
	private String fechaEmbarque;
	private Integer codigoBloqueOrigen;
	private Integer codigoPuertoEmbarque;
	private Integer codigoPuertoDesembarque;
	private Integer codigoEstadoDocumentacion;
	private Integer paginaInicio;
	private Integer paginaTamanyo;
	private String orden;
	private List<EstadoDTO> estado;
	
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

	public List<EstadoDTO> getEstado() {
		return estado;
	}

	public void setEstado(List<EstadoDTO> estado) {
		this.estado = estado;
	}

	public Integer getCodigoEstadoDocumentacion() {
		return codigoEstadoDocumentacion;
	}

	public void setCodigoEstadoDocumentacion(Integer codigoEstadoDocumentacion) {
		this.codigoEstadoDocumentacion = codigoEstadoDocumentacion;
	}

	public Integer getPaginaInicio() {
		return paginaInicio;
	}

	public void setPaginaInicio(Integer paginaInicio) {
		this.paginaInicio = paginaInicio;
	}

	public Integer getPaginaTamanyo() {
		return paginaTamanyo;
	}

	public void setPaginaTamanyo(Integer paginaTamanyo) {
		this.paginaTamanyo = paginaTamanyo;
	}

	public String getOrden() {
		return orden;
	}

	public void setOrden(String orden) {
		this.orden = orden;
	}

}
