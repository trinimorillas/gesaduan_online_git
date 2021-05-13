package es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvdsumary.v1;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class InputValueDeclarationSumaryDTO extends AbstractDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String localeId;	
	private String userCode	;	
	private String agencyLegacyId;
	private Integer valueDeclarationNumber;
	private Integer valueDeclarationYear;
	private Integer dossierNumber ;	
	private Integer dossierYear;		
	private String internalOrderId;
	private String supplierLegacyId;
	private String sourceName;
	private String warehouseLegacyId;
	private String targetName;
	private String valueDeclarationStateId;
	private String downloadStateId;
	private String dateFilterTypeId;
	private String dateFrom;
	private String dateTo;
	private Integer firstPage ;
	private Integer sizePage ;
	private String order;
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
	/**
	 * @return the agencyLegacyId
	 */
	public String getAgencyLegacyId() {
		return agencyLegacyId;
	}
	/**
	 * @param agencyLegacyId the agencyLegacyId to set
	 */
	public void setAgencyLegacyId(String agencyLegacyId) {
		this.agencyLegacyId = agencyLegacyId;
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
	 * @return the supplierLegacyId
	 */
	public String getSupplierLegacyId() {
		return supplierLegacyId;
	}
	/**
	 * @param supplierLegacyId the supplierLegacyId to set
	 */
	public void setSupplierLegacyId(String supplierLegacyId) {
		this.supplierLegacyId = supplierLegacyId;
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
	 * @return the warehouseLegacyId
	 */
	public String getWarehouseLegacyId() {
		return warehouseLegacyId;
	}
	/**
	 * @param warehouseLegacyId the warehouseLegacyId to set
	 */
	public void setWarehouseLegacyId(String warehouseLegacyId) {
		this.warehouseLegacyId = warehouseLegacyId;
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
	 * @return the valueDeclarationStateId
	 */
	public String getValueDeclarationStateId() {
		return valueDeclarationStateId;
	}
	/**
	 * @param valueDeclarationStateId the valueDeclarationStateId to set
	 */
	public void setValueDeclarationStateId(String valueDeclarationStateId) {
		this.valueDeclarationStateId = valueDeclarationStateId;
	}
	/**
	 * @return the downloadStateId
	 */
	public String getDownloadStateId() {
		return downloadStateId;
	}
	/**
	 * @param downloadStateId the downloadStateId to set
	 */
	public void setDownloadStateId(String downloadStateId) {
		this.downloadStateId = downloadStateId;
	}
	/**
	 * @return the dateFilterTypeId
	 */
	public String getDateFilterTypeId() {
		return dateFilterTypeId;
	}
	/**
	 * @param dateFilterTypeId the dateFilterTypeId to set
	 */
	public void setDateFilterTypeId(String dateFilterTypeId) {
		this.dateFilterTypeId = dateFilterTypeId;
	}
	/**
	 * @return the dateFrom
	 */
	public String getDateFrom() {
		return dateFrom;
	}
	/**
	 * @param dateFrom the dateFrom to set
	 */
	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}
	/**
	 * @return the dateTo
	 */
	public String getDateTo() {
		return dateTo;
	}
	/**
	 * @param dateTo the dateTo to set
	 */
	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
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
	
	

}
