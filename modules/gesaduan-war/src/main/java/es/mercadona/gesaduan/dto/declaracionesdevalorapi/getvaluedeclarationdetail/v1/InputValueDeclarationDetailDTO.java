package es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvaluedeclarationdetail.v1;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class InputValueDeclarationDetailDTO extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String valueDeclarationCode;
	private String localeId;
	private String userCode;

	/**
	 * @return the valueDeclarationCode
	 */
	public String getValueDeclarationCode() {
		return valueDeclarationCode;
	}

	/**
	 * @param valueDeclarationCode the valueDeclarationCode to set
	 */
	public void setValueDeclarationCode(String valueDeclarationCode) {
		this.valueDeclarationCode = valueDeclarationCode;
	}

	/**
	 * @return the localeId
	 */
	public String getLocaleId() {
		return localeId;
	}

	/**
	 * @param localeId the localeId to set
	 */
	public void setLocaleId(String localeId) {
		this.localeId = localeId;
	}

	/**
	 * @return the userCode
	 */
	public String getUserCode() {
		return userCode;
	}

	/**
	 * @param userCode the userCode to set
	 */
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

}
