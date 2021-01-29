package es.mercadona.gesaduan.dto.tarics.gettarics.v1.restful;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class InputTaricsDTO extends AbstractDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String locale;
	private String codigoUsuario;
	private String codigoTaricDesde;
	private String codigoTaricHasta;
	private String descripcion;
	private String orden;
	
	
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
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getOrden() {
		return orden;
	}
	public void setOrden(String orden) {
		this.orden = orden;
	}
	public String getCodigoTaricDesde() {
		return codigoTaricDesde;
	}
	public void setCodigoTaricDesde(String codigoTaricDesde) {
		this.codigoTaricDesde = codigoTaricDesde;
	}
	public String getCodigoTaricHasta() {
		return codigoTaricHasta;
	}
	public void setCodigoTaricHasta(String codigoTaricHasta) {
		this.codigoTaricHasta = codigoTaricHasta;
	}

	
	
	

}
