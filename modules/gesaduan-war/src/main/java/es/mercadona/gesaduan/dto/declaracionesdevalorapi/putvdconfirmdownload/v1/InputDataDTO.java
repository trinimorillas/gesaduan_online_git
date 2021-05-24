package es.mercadona.gesaduan.dto.declaracionesdevalorapi.putvdconfirmdownload.v1;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputDataDTO extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String valueDeclarationNumber;
	private String valueDeclarationYear;
	private String valueDeclarationVersion;	
	private String locale;
	private String supplierId;
	private String userId;	
	private Boolean isDownloaded;
	
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
	 * @return the supplierId
	 */
	public String getSupplierId() {
		return supplierId;
	}
	/**
	 * @param supplierId the supplierId to set
	 */
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
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
	/**
	 * @return the isDownloaded
	 */
	public Boolean getIsDownloaded() {
		return isDownloaded;
	}
	/**
	 * @param isDownloaded the isDownloaded to set
	 */
	public void setIsDownloaded(Boolean isDownloaded) {
		this.isDownloaded = isDownloaded;
	}


}
