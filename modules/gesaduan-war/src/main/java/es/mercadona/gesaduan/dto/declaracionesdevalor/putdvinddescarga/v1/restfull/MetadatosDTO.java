package es.mercadona.gesaduan.dto.declaracionesdevalor.putdvinddescarga.v1.restfull;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class MetadatosDTO extends AbstractDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoUsuario;
	
	private String locale = "es-ES";

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
	
	

}
