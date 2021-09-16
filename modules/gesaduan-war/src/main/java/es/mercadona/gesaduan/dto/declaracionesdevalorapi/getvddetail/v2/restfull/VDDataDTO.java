package es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvddetail.v2.restfull;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class VDDataDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;

	private VDDeclarationIdsDTO valueDeclarationIds;
	private VDDossierIdsDTO dossierIds;

	private String valueDeclarationGenerationDate;
	private Double totalAmount;
	private String totalAmountCurrency;
	private String dispatchCode;
	private String incotermId;
	private String deliveryNoteDate;
	private String dispatchDate;

	/**
	 * @return the valueDeclarationIds
	 */
	public VDDeclarationIdsDTO getValueDeclarationIds() {
		return valueDeclarationIds;
	}

	/**
	 * @param valueDeclarationIds the valueDeclarationIds to set
	 */
	public void setValueDeclarationIds(VDDeclarationIdsDTO valueDeclarationIds) {
		this.valueDeclarationIds = valueDeclarationIds;
	}

	/**
	 * @return the dossierIds
	 */
	public VDDossierIdsDTO getDossierIds() {
		return dossierIds;
	}

	/**
	 * @param dossierIds the dossierIds to set
	 */
	public void setDossierIds(VDDossierIdsDTO dossierIds) {
		this.dossierIds = dossierIds;
	}

	/**
	 * @return the valueDeclarationGenerationDate
	 */
	public String getValueDeclarationGenerationDate() {
		return valueDeclarationGenerationDate;
	}

	/**
	 * @param valueDeclarationGenerationDate the valueDeclarationGenerationDate to
	 *                                       set
	 */
	public void setValueDeclarationGenerationDate(String valueDeclarationGenerationDate) {
		this.valueDeclarationGenerationDate = valueDeclarationGenerationDate;
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
	 * @return the incotermId
	 */
	public String getIncotermId() {
		return incotermId;
	}

	/**
	 * @param incotermId the incotermId to set
	 */
	public void setIncotermId(String incotermId) {
		this.incotermId = incotermId;
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

}
