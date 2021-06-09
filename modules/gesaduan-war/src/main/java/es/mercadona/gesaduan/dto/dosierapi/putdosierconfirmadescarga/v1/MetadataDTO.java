package es.mercadona.gesaduan.dto.dosierapi.putdosierconfirmadescarga.v1;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class MetadataDTO extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String userId;
	private String locale;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

}
