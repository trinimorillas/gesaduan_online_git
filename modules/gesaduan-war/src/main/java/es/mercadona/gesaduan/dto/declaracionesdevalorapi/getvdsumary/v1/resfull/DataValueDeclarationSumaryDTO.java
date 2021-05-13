package es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvdsumary.v1.resfull;

import java.io.Serializable;
import java.util.List;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class DataValueDeclarationSumaryDTO extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private boolean isValueDeclarationOk;
	private boolean isAutomaticLoading ;
	private Integer valueDeclarationNumber;
	private Integer valueDeclarationYear;	
	private Integer valueDeclarationVersion;
	private Integer dossierNumber;	
	private Integer dossierYear;
	private String valueDeclarationStatus;	
	private Double totalAmount;
	private String totalAmountCurrency;
	private String dispatchDate;
	private String valueDeclarationGenerationDate;
	private String valueDeclarationDownloadDate;
	private String exportDownloadDate;
	private String importDownloadDate;
	private List<DataValueDeclarationSumaryOrderDTO> internalOrderList;	
	private DataValueDeclarationSumarySourceDTO source;
	private String dispatchCode;
	
	/**
	 * @return the isValueDeclarationOk
	 */
	public boolean isIsValueDeclarationOk() {
		return isValueDeclarationOk;
	}
	/**
	 * @param isValueDeclarationOk the isValueDeclarationOk to set
	 */
	public void setValueDeclarationOk(boolean isValueDeclarationOk) {
		this.isValueDeclarationOk = isValueDeclarationOk;
	}
	/**
	 * @return the isAutomaticLoading
	 */
	public boolean isIsAutomaticLoading() {
		return isAutomaticLoading;
	}
	/**
	 * @param isAutomaticLoading the isAutomaticLoading to set
	 */
	public void setAutomaticLoading(boolean isAutomaticLoading) {
		this.isAutomaticLoading = isAutomaticLoading;
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
	 * @return the totalAmount
	 */
	public Double getTotalAmount() {
		return totalAmount;
	}
	/**
	 * @param totalAmount the totalAmount to set
	 */
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	/**
	 * @return the totalAmountCurrency
	 */
	public String getTotalAmountCurrency() {
		return totalAmountCurrency;
	}
	/**
	 * @param totalAmountCurrency the totalAmountCurrency to set
	 */
	public void setTotalAmountCurrency(String totalAmountCurrency) {
		this.totalAmountCurrency = totalAmountCurrency;
	}
	/**
	 * @return the dispatchDate
	 */
	public String getDispatchDate() {
		return dispatchDate;
	}
	/**
	 * @param dispatchDate the dispatchDate to set
	 */
	public void setDispatchDate(String dispatchDate) {
		this.dispatchDate = dispatchDate;
	}
	/**
	 * @return the valueDeclarationGenerationDate
	 */
	public String getValueDeclarationGenerationDate() {
		return valueDeclarationGenerationDate;
	}
	/**
	 * @param valueDeclarationGenerationDate the valueDeclarationGenerationDate to set
	 */
	public void setValueDeclarationGenerationDate(String valueDeclarationGenerationDate) {
		this.valueDeclarationGenerationDate = valueDeclarationGenerationDate;
	}
	/**
	 * @return the valueDeclarationDownloadDate
	 */
	public String getValueDeclarationDownloadDate() {
		return valueDeclarationDownloadDate;
	}
	/**
	 * @param valueDeclarationDownloadDate the valueDeclarationDownloadDate to set
	 */
	public void setValueDeclarationDownloadDate(String valueDeclarationDownloadDate) {
		this.valueDeclarationDownloadDate = valueDeclarationDownloadDate;
	}
	/**
	 * @return the exportDownloadDate
	 */
	public String getExportDownloadDate() {
		return exportDownloadDate;
	}
	/**
	 * @param exportDownloadDate the exportDownloadDate to set
	 */
	public void setExportDownloadDate(String exportDownloadDate) {
		this.exportDownloadDate = exportDownloadDate;
	}
	/**
	 * @return the importDownloadDate
	 */
	public String getImportDownloadDate() {
		return importDownloadDate;
	}
	/**
	 * @param importDownloadDate the importDownloadDate to set
	 */
	public void setImportDownloadDate(String importDownloadDate) {
		this.importDownloadDate = importDownloadDate;
	}
	/**
	 * @return the internalOrderList
	 */
	public List<DataValueDeclarationSumaryOrderDTO> getInternalOrderList() {
		return internalOrderList;
	}
	/**
	 * @param internalOrderList the internalOrderList to set
	 */
	public void setInternalOrderList(List<DataValueDeclarationSumaryOrderDTO> internalOrderList) {
		this.internalOrderList = internalOrderList;
	}
	/**
	 * @return the source
	 */
	public DataValueDeclarationSumarySourceDTO getSource() {
		return source;
	}
	/**
	 * @param source the source to set
	 */
	public void setSource(DataValueDeclarationSumarySourceDTO source) {
		this.source = source;
	}
	/**
	 * @return the dispatchCode
	 */
	public String getDispatchCode() {
		return dispatchCode;
	}
	/**
	 * @param dispatchCode the dispatchCode to set
	 */
	public void setDispatchCode(String dispatchCode) {
		this.dispatchCode = dispatchCode;
	}

	

}
