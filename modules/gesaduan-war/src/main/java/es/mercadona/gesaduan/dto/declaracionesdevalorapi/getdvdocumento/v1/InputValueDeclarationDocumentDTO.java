package es.mercadona.gesaduan.dto.declaracionesdevalorapi.getdvdocumento.v1;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class InputValueDeclarationDocumentDTO extends AbstractDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String valueDeclarationNumber;
	private String valueDeclarationYear;
	private String valueDeclarationVersion;
	private String locale;
	private String documentType;
	private String userId;
	/**
	 * @return the valueDeclarationNumber
	 */
	public String getValueDeclarationNumber() {
		return valueDeclarationNumber;
	}
	/**
	 * @param valueDeclarationNumber the valueDeclarationNumber to set
	 */
	public void setValueDeclarationNumber(String valueDeclarationNumber) {
		this.valueDeclarationNumber = valueDeclarationNumber;
	}
	/**
	 * @return the valueDeclarationYear
	 */
	public String getValueDeclarationYear() {
		return valueDeclarationYear;
	}
	/**
	 * @param valueDeclarationYear the valueDeclarationYear to set
	 */
	public void setValueDeclarationYear(String valueDeclarationYear) {
		this.valueDeclarationYear = valueDeclarationYear;
	}
	/**
	 * @return the valueDeclarationVersion
	 */
	public String getValueDeclarationVersion() {
		return valueDeclarationVersion;
	}
	/**
	 * @param valueDeclarationVersion the valueDeclarationVersion to set
	 */
	public void setValueDeclarationVersion(String valueDeclarationVersion) {
		this.valueDeclarationVersion = valueDeclarationVersion;
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
	/**
	 * @return the documentType
	 */
	public String getDocumentType() {
		return documentType;
	}
	/**
	 * @param documentType the documentType to set
	 */
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
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

	
	
}
