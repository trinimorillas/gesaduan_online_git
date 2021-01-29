package es.mercadona.gesaduan.dto.reas.getreas.v1;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class InputReasDTO extends AbstractDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String locale;
	private String codigoUsuario;
	private String codigoDesde;
	private String codigoHasta;
	private String codigoTaric;
	private Boolean existeRelacion;
	private String orden;
	
	
	
	public Boolean getExisteRelacion() {
		return existeRelacion;
	}
	public void setExisteRelacion(Boolean existeRelacion) {
		this.existeRelacion = existeRelacion;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public String getCodigoUsuario() {
		return codigoUsuario;
	}
	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}
	public String getCodigoDesde() {
		return codigoDesde;
	}
	public void setCodigoDesde(String codigoDesde) {
		this.codigoDesde = codigoDesde;
	}
	public String getCodigoHasta() {
		return codigoHasta;
	}
	public void setCodigoHasta(String codigoHasta) {
		this.codigoHasta = codigoHasta;
	}
	public String getCodigoTaric() {
		return codigoTaric;
	}
	public void setCodigoTaric(String codigoTaric) {
		this.codigoTaric = codigoTaric;
	}
	public String getOrden() {
		return orden;
	}
	public void setOrden(String orden) {
		this.orden = orden;
	}
	

}
