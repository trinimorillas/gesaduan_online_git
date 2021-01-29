package es.mercadona.gesaduan.dto.alertas.getalertas.v1;

import java.io.Serializable;
import java.util.Date;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputAlertasDTO extends AbstractDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String locale;
	private Date fechaDesde;
	private Date fechaHasta;
	private Long numeroProveedor;
	private String nombreProveedor;
	private String tipoAlerta;
	private String estado;
	private Integer codigoDV;
	private Integer anyoDV;
	private Integer versionDV;				
	private String orden;
	
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public Date getFechaDesde() {
		return fechaDesde;
	}
	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	public Date getFechaHasta() {
		return fechaHasta;
	}
	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	public Long getNumeroProveedor() {
		return numeroProveedor;
	}
	public void setNumeroProveedor(Long numeroProveedor) {
		this.numeroProveedor = numeroProveedor;
	}
	public String getNombreProveedor() {
		return nombreProveedor;
	}
	public void setNombreProveedor(String nombreProveedor) {
		this.nombreProveedor = nombreProveedor;
	}
	public String getTipoAlerta() {
		return tipoAlerta;
	}
	public void setTipoAlerta(String tipoAlerta) {
		this.tipoAlerta = tipoAlerta;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}	
	public Integer getCodigoDV() {
		return codigoDV;
	}
	public void setCodigoDV(Integer codigoDV) {
		this.codigoDV = codigoDV; 
	}
	public Integer getAnyoDV() {
		return anyoDV;
	}
	public void setAnyoDV(Integer anyoDV) {
		this.anyoDV = anyoDV;
	}
	public Integer getVersionDV() {
		return versionDV;
	}
	public void setVersionDV(Integer versionDV) {
		this.versionDV = versionDV;
	}
	public String getOrden() {
		return orden;
	}
	public void setOrden(String orden) {
		this.orden = orden;
	}
		
		

}
