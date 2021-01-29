package es.mercadona.gesaduan.dto.productos.getproductos.v1;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputGetProductoDTO extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String locale;
	private String denominacion;
	private String marca;
	private String codigoTaric;
	private String codigoRea;
	private String subvencion;
	private String formatoVenta;
	private Boolean listoParaComer;
	private String activoAduana;
	private String codigo;
	private Integer paginaInicio;
	private Integer paginaTamanyo;
	private String orden;
	private String estado;
	
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getDenominacion() {
		return denominacion;
	}
	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getCodigoTaric() {
		return codigoTaric;
	}
	public void setCodigoTaric(String codigoTaric) {
		this.codigoTaric = codigoTaric;
	}
	public String getCodigoRea() {
		return codigoRea;
	}
	public void setCodigoRea(String codigoRea) {
		this.codigoRea = codigoRea;
	}
	public String getSubvencion() {
		return subvencion;
	}
	public void setSubvencion(String subvencion) {
		this.subvencion = subvencion;
	}
	public String getFormatoVenta() {
		return formatoVenta;
	}
	public void setFormatoVenta(String formatoVenta) {
		this.formatoVenta = formatoVenta;
	}
	public Boolean getListoParaComer() {
		return listoParaComer;
	}
	public void setListoParaComer(Boolean listoParaComer) {
		this.listoParaComer = listoParaComer;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
	public String getActivoAduana() {
		return activoAduana;
	}
	public void setActivoAduana(String activoAduana) {
		this.activoAduana = activoAduana;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}
