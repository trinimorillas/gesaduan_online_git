package es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvddetail.v2.restfull;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class VDHistoricalDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;

	private String eventId;
	private String eventCreateDate;
	private Integer valueDeclarationVersion;
	private Boolean isCurrent;
	private Boolean isValueDeclarationOk;

	/**
	 * @return the eventId
	 */
	public String getEventId() {
		return eventId;
	}

	/**
	 * @param eventId the eventId to set
	 */
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	/**
	 * @return the eventCreateDate
	 */
	public String getEventCreateDate() {
		return eventCreateDate;
	}

	/**
	 * @param eventCreateDate the eventCreateDate to set
	 */
	public void setEventCreateDate(String eventCreateDate) {
		this.eventCreateDate = eventCreateDate;
	}

	/**
	 * @return the valueDeclarationVersion
	 */
	public Integer getValueDeclarationVersion() {
		return valueDeclarationVersion;
	}

	/**
	 * @param valueDeclarationVersion the valueDeclarationVersion to set
	 */
	public void setValueDeclarationVersion(Integer valueDeclarationVersion) {
		this.valueDeclarationVersion = valueDeclarationVersion;
	}

	/**
	 * @return the isCurrent
	 */
	public Boolean getIsCurrent() {
		return isCurrent;
	}

	/**
	 * @param isCurrent the isCurrent to set
	 */
	public void setIsCurrent(Boolean isCurrent) {
		this.isCurrent = isCurrent;
	}

	/**
	 * @return the isValueDeclarationOk
	 */
	public Boolean getIsValueDeclarationOk() {
		return isValueDeclarationOk;
	}

	/**
	 * @param isValueDeclarationOk the isValueDeclarationOk to set
	 */
	public void setIsValueDeclarationOk(Boolean isValueDeclarationOk) {
		this.isValueDeclarationOk = isValueDeclarationOk;
	}

}
