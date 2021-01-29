package es.mercadona.gesaduan.dto.proveedores.getrelacionesproveedores.v1;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class InputRelacionesProveedoresDTO extends AbstractDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long codigoProveedor;
	private String codigoUsuario;
	private String locale;
	private String tipoBusqueda;
	private String orden;
	private String numeroProveedorRelacionado;
	private String nombreProveedorRelacionado;
	private Boolean relacionesVigentes;
	
	public Long getCodigoProveedor() {
		return codigoProveedor;
	}
	public void setCodigoProveedor(Long codigoProveedor) {
		this.codigoProveedor = codigoProveedor;
	}
	public String getCodigoUsuario() {
		return codigoUsuario;
	}
	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public String getTipoBusqueda() {
		return tipoBusqueda;
	}
	public void setTipoBusqueda(String tipoBusqueda) {
		this.tipoBusqueda = tipoBusqueda;
	}
	public String getOrden() {
		return orden;
	}
	public void setOrden(String orden) {
		this.orden = orden;
	}
	public String getNumeroProveedorRelacionado() {
		return numeroProveedorRelacionado;
	}
	public void setNumeroProveedorRelacionado(String numeroProveedorRelacionado) {
		this.numeroProveedorRelacionado = numeroProveedorRelacionado;
	}
	public String getNombreProveedorRelacionado() {
		return nombreProveedorRelacionado;
	}
	public void setNombreProveedorRelacionado(String nombreProveedorRelacionado) {
		this.nombreProveedorRelacionado = nombreProveedorRelacionado;
	}
	public Boolean getRelacionesVigentes() {
		return relacionesVigentes;
	}
	public void setRelacionesVigentes(Boolean relacionesVigentes) {
		this.relacionesVigentes = relacionesVigentes;
	}
	
	
	
	

}
