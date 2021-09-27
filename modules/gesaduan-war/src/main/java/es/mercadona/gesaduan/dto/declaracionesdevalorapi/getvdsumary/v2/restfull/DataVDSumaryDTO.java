package es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvdsumary.v2.restfull;

import java.io.Serializable;
import java.util.List;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class DataVDSumaryDTO extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private boolean isValueDeclarationOk;
	private boolean isAutomaticLoading ;
	private DataVDeclarationIdsDTO valueDeclarationIds;
	private DataVDossierIdsDTO dossiersIds;	
	private String valueDeclarationStatus;
	private String valueDeclarationType;
	private Double totalAmount;
	private String totalAmountCurrency;
	private DataVDDatesDTO dates;
	private List<DataVDSumaryOrderDTO> internalOrderList;	
	private DataVDSumarySourceDTO source;
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
	 * @return the valueDeclarationIds
	 */
	public DataVDeclarationIdsDTO getValueDeclarationIds() {
		return valueDeclarationIds;
	}
	/**
	 * @param valueDeclarationIds the valueDeclarationIds to set
	 */
	public void setValueDeclarationIds(DataVDeclarationIdsDTO valueDeclarationIds) {
		this.valueDeclarationIds = valueDeclarationIds;
	}
	/**
	 * @return the dossiersIds
	 */
	public DataVDossierIdsDTO getDossiersIds() {
		return dossiersIds;
	}
	/**
	 * @param dossiersIds the dossiersIds to set
	 */
	public void setDossiersIds(DataVDossierIdsDTO dossiersIds) {
		this.dossiersIds = dossiersIds;
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
	 * @return the dates
	 */
	public DataVDDatesDTO getDates() {
		return dates;
	}
	/**
	 * @param dates the dates to set
	 */
	public void setDates(DataVDDatesDTO dates) {
		this.dates = dates;
	}
	/**
	 * @return the internalOrderList
	 */
	public List<DataVDSumaryOrderDTO> getInternalOrderList() {
		return internalOrderList;
	}
	/**
	 * @param internalOrderList the internalOrderList to set
	 */
	public void setInternalOrderList(List<DataVDSumaryOrderDTO> internalOrderList) {
		this.internalOrderList = internalOrderList;
	}
	/**
	 * @return the source
	 */
	public DataVDSumarySourceDTO getSource() {
		return source;
	}
	/**
	 * @param source the source to set
	 */
	public void setSource(DataVDSumarySourceDTO source) {
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
