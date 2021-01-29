package es.mercadona.gesaduan.dto.reas.deletereas.v1;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class InputDeleteReasDTO extends AbstractDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String locale;
	private String codigoUsuario;
	private String codigoRea;
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
	public String getCodigoRea() {
		return codigoRea;
	}
	public void setCodigoRea(String codigoRea) {
		this.codigoRea = codigoRea;
	}
	
	
	
	

}
