package es.mercadona.gesaduan.dto.tarics.gettaricsdetalle.v1;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class InputTaricsDetalleDTO extends AbstractDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoTaric;
	private String codigoUsuario;
	private String locale;
	
	
	public String getCodigoTaric() {
		return codigoTaric;
	}
	public void setCodigoTaric(String codigoTaric) {
		this.codigoTaric = codigoTaric;
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
	
	

}
