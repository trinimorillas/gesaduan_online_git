package es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvdsumary.v2;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class InputVDSumaryDTO extends AbstractDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String locale;	
	private String userId	;	
	private String agencyId;
	private Integer valueDeclarationNumber;
	private Integer valueDeclarationYear;
	private Integer dossierNumber;
	private Integer dossierYear;
	private String valueDeclarationType;
	private String internalOrderId;
	private String supplierId;
	private String sourceName;
	private String warehouseId;
	private String targetName;
	private String valueDeclarationStatus;
	private String downloadStatus;
	private String dateFilterType;
	private String startDate;
	private String endDate;
	private Integer firstPage ;
	private Integer sizePage ;
	private String order;
	
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
	 * @return the valueDeclarationNumber
	 */
	public Integer getValueDeclarationNumber() {
		return valueDeclarationNumber;
	}
	/**
	 * @param valueDeclarationNumber the valueDeclarationNumber to set
	 */
	public void setValueDeclarationNumber(Integer valueDeclarationNumber) {
		this.valueDeclarationNumber = valueDeclarationNumber;
	}
	/**
	 * @return the valueDeclarationYear
	 */
	public Integer getValueDeclarationYear() {
		return valueDeclarationYear;
	}
	/**
	 * @param valueDeclarationYear the valueDeclarationYear to set
	 */
	public void setValueDeclarationYear(Integer valueDeclarationYear) {
		this.valueDeclarationYear = valueDeclarationYear;
	}
	/**
	 * @return the dossierNumber
	 */
	public Integer getDossierNumber() {
		return dossierNumber;
	}
	/**
	 * @param dossierNumber the dossierNumber to set
	 */
	public void setDossierNumber(Integer dossierNumber) {
		this.dossierNumber = dossierNumber;
	}
	/**
	 * @return the dossierYear
	 */
	public Integer getDossierYear() {
		return dossierYear;
	}
	/**
	 * @param dossierYear the dossierYear to set
	 */
	public void setDossierYear(Integer dossierYear) {
		this.dossierYear = dossierYear;
	}
	/**
	 * @return the internalOrderId
	 */
	public String getInternalOrderId() {
		return internalOrderId;
	}
	/**
	 * @param internalOrderId the internalOrderId to set
	 */
	public void setInternalOrderId(String internalOrderId) {
		this.internalOrderId = internalOrderId;
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
	 * @return the sourceName
	 */
	public String getSourceName() {
		return sourceName;
	}
	/**
	 * @param sourceName the sourceName to set
	 */
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
	/**
	 * @return the warehouseId
	 */
	public String getWarehouseId() {
		return warehouseId;
	}
	/**
	 * @param warehouseId the warehouseId to set
	 */
	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}
	/**
	 * @return the targetName
	 */
	public String getTargetName() {
		return targetName;
	}
	/**
	 * @param targetName the targetName to set
	 */
	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}
	/**
	 * @return the valueDeclarationStatus
	 */
	public String getValueDeclarationStatus() {
		return valueDeclarationStatus;
	}
	/**
	 * @param valueDeclarationStatus the valueDeclarationStatus to set
	 */
	public void setValueDeclarationStatus(String valueDeclarationStatus) {
		this.valueDeclarationStatus = valueDeclarationStatus;
	}
	/**
	 * @return the downloadStatus
	 */
	public String getDownloadStatus() {
		return downloadStatus;
	}
	/**
	 * @param downloadStatus the downloadStatus to set
	 */
	public void setDownloadStatus(String downloadStatus) {
		this.downloadStatus = downloadStatus;
	}
	/**
	 * @return the dateFilterType
	 */
	public String getDateFilterType() {
		return dateFilterType;
	}
	/**
	 * @param dateFilterType the dateFilterType to set
	 */
	public void setDateFilterType(String dateFilterType) {
		this.dateFilterType = dateFilterType;
	}
	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return the firstPage
	 */
	public Integer getFirstPage() {
		return firstPage;
	}
	/**
	 * @param firstPage the firstPage to set
	 */
	public void setFirstPage(Integer firstPage) {
		this.firstPage = firstPage;
	}
	/**
	 * @return the sizePage
	 */
	public Integer getSizePage() {
		return sizePage;
	}
	/**
	 * @param sizePage the sizePage to set
	 */
	public void setSizePage(Integer sizePage) {
		this.sizePage = sizePage;
	}
	/**
	 * @return the order
	 */
	public String getOrder() {
		return order;
	}
	/**
	 * @param order the order to set
	 */
	public void setOrder(String order) {
		this.order = order;
	}
	/**
	 * @return the valueDeclarationType
	 */
	/**
	 * @return the valueDeclarationType
	 */
	public String getValueDeclarationType() {
		return valueDeclarationType;
	}
	/**
	 * @param valueDeclarationType the valueDeclarationType to set
	 */
	public void setValueDeclarationType(String valueDeclarationType) {
		this.valueDeclarationType = valueDeclarationType;
	}
}
