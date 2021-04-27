package es.mercadona.gesaduan.dto.dosierapi.putdosierconfirmadescarga.v1;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class MetadatosDTO extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String codigoUsuario;
	private String locale;

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
