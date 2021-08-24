package es.mercadona.gesaduan.dto.declaracionesdevalorapi.putreturns.v1;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class MetadataDTO extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String userId;
	private String locale;

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the locale
	 */
	public String getLocale() {
		return locale;
	}

	/**
	 * @param locale the locale to set
	 */
	public void setLocale(String locale) {
		this.locale = locale;
	}

}
