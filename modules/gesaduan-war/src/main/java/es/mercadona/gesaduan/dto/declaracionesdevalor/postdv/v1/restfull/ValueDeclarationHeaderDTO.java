package es.mercadona.gesaduan.dto.declaracionesdevalor.postdv.v1.restfull;

import java.util.List;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class ValueDeclarationHeaderDTO extends AbstractDTO {
	private static final long serialVersionUID = 1L;
	private String valueDeclarationNumber;
	private String valueDeclarationYear;
	private String valueDeclarationVersion;
	private String dossierNumber;
	private String dossierYear;
	private String valueDeclarationDate;
	private Double totalAmount;
	private String totalAmountCurrency;
	private String dispatchId;
	private String incoterm;
	private String deliveryNoteDate;
	private String dispatchDate;
	private List<InternalOrderListDTO> internalOrderList;
	private SourceDTO source;
	private TargetDTO target;

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
	 * @return the valueDeclarationDate
	 */
	public String getValueDeclarationDate() {
		return valueDeclarationDate;
	}

	/**
	 * @param valueDeclarationDate the valueDeclarationDate to set
	 */
	public void setValueDeclarationDate(String valueDeclarationDate) {
		this.valueDeclarationDate = valueDeclarationDate;
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
	 * @return the dispatchId
	 */
	public String getDispatchId() {
		return dispatchId;
	}

	/**
	 * @param dispatchId the dispatchId to set
	 */
	public void setDispatchId(String dispatchId) {
		this.dispatchId = dispatchId;
	}

	/**
	 * @return the incoterm
	 */
	public String getIncoterm() {
		return incoterm;
	}

	/**
	 * @param incoterm the incoterm to set
	 */
	public void setIncoterm(String incoterm) {
		this.incoterm = incoterm;
	}

	/**
	 * @return the deliveryNoteDate
	 */
	public String getDeliveryNoteDate() {
		return deliveryNoteDate;
	}

	/**
	 * @param deliveryNoteDate the deliveryNoteDate to set
	 */
	public void setDeliveryNoteDate(String deliveryNoteDate) {
		this.deliveryNoteDate = deliveryNoteDate;
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
	 * @return the internalOrderList
	 */
	public List<InternalOrderListDTO> getInternalOrderList() {
		return internalOrderList;
	}

	/**
	 * @param internalOrderList the internalOrderList to set
	 */
	public void setInternalOrderList(List<InternalOrderListDTO> internalOrderList) {
		this.internalOrderList = internalOrderList;
	}

	/**
	 * @return the source
	 */
	public SourceDTO getSource() {
		return source;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(SourceDTO source) {
		this.source = source;
	}

	/**
	 * @return the target
	 */
	public TargetDTO getTarget() {
		return target;
	}

	/**
	 * @param target the target to set
	 */
	public void setTarget(TargetDTO target) {
		this.target = target;
	}

}
