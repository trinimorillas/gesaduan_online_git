package es.mercadona.gesaduan.dto.dosierapi.putdosierconfirmadescarga.v1;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputDataDTO extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String dossierNumber;
	private String dossierYear;	
	private String agencyId;
	private Boolean isDownloaded;
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
	 * @return the agencyId
	 */
	public String getAgencyId() {
		return agencyId;
	}
	/**
	 * @param agencyId the agencyId to set
	 */
	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
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