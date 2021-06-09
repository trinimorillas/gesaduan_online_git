package es.mercadona.gesaduan.dto.dosierapi.getdossierdocument.v1;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class InputDossierDocumentDTO extends AbstractDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String dossierNumber;
	private String dossierYear;	
	private String locale;
	private String documentType;
	private String userId;
	/**
	 * @return the dossierNumber
	 */
	public String getDossierNumber() {
		return dossierNumber;
	}
	/**
	 * @param dossierNumber the dossierNumber to set
	 */
	public void setDossierNumber(String dossierNumber) {
		this.dossierNumber = dossierNumber;
	}
	/**
	 * @return the dossierYear
	 */
	public String getDossierYear() {
		return dossierYear;
	}
	/**
	 * @param dossierYear the dossierYear to set
	 */
	public void setDossierYear(String dossierYear) {
		this.dossierYear = dossierYear;
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
