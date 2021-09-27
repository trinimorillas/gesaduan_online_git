package es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvddetail.v2.restfull;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class VDItemDTO extends AbstractDTO {
	
	private static final long serialVersionUID = 1L;
	private Long itemId;
	private Long typeItemId;
	private Long taricId;
	private Long actualTaricId;
	private String itemName;
	private Long packageQuantity;
	private Long quantity;
	private String quantityUnit;
	private Double lineNetWeight;
	private String lineNetWeightUnit;
	private Double lineGrossWeight;
	private String lineGrossWeightUnit;
	private Double unitPrice;
	private String unitPriceCurrency;
	private Double totalLineAmount;
	private String totalLineAmountCurrency;
	/**
	 * @return the itemId
	 */
	public Long getItemId() {
		return itemId;
	}
	/**
	 * @param itemId the itemId to set
	 */
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	/**
	 * @return the typeItemId
	 */
	public Long getTypeItemId() {
		return typeItemId;
	}
	/**
	 * @param typeItemId the typeItemId to set
	 */
	public void setTypeItemId(Long typeItemId) {
		this.typeItemId = typeItemId;
	}
	/**
	 * @return the taricId
	 */
	public Long getTaricId() {
		return taricId;
	}
	/**
	 * @param taricId the taricId to set
	 */
	public void setTaricId(Long taricId) {
		this.taricId = taricId;
	}
	/**
	 * @return the actualTaricId
	 */
	public Long getActualTaricId() {
		return actualTaricId;
	}
	/**
	 * @param actualTaricId the actualTaricId to set
	 */
	public void setActualTaricId(Long actualTaricId) {
		this.actualTaricId = actualTaricId;
	}
	/**
	 * @return the itemName
	 */
	public String getItemName() {
		return itemName;
	}
	/**
	 * @param itemName the itemName to set
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	/**
	 * @return the quantity
	 */
	public Long getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	/**
	 * @return the quantityUnit
	 */
	public String getQuantityUnit() {
		return quantityUnit;
	}
	/**
	 * @param quantityUnit the quantityUnit to set
	 */
	public void setQuantityUnit(String quantityUnit) {
		this.quantityUnit = quantityUnit;
	}
	/**
	 * @return the lineNetWeight
	 */
	public Double getLineNetWeight() {
		return lineNetWeight;
	}
	/**
	 * @param lineNetWeight the lineNetWeight to set
	 */
	public void setLineNetWeight(Double lineNetWeight) {
		this.lineNetWeight = lineNetWeight;
	}
	/**
	 * @return the lineNetWeightUnit
	 */
	public String getLineNetWeightUnit() {
		return lineNetWeightUnit;
	}
	/**
	 * @param lineNetWeightUnit the lineNetWeightUnit to set
	 */
	public void setLineNetWeightUnit(String lineNetWeightUnit) {
		this.lineNetWeightUnit = lineNetWeightUnit;
	}
	/**
	 * @return the lineGrossWeight
	 */
	public Double getLineGrossWeight() {
		return lineGrossWeight;
	}
	/**
	 * @param lineGrossWeight the lineGrossWeight to set
	 */
	public void setLineGrossWeight(Double lineGrossWeight) {
		this.lineGrossWeight = lineGrossWeight;
	}
	/**
	 * @return the lineGrossWeightUnit
	 */
	public String getLineGrossWeightUnit() {
		return lineGrossWeightUnit;
	}
	/**
	 * @param lineGrossWeightUnit the lineGrossWeightUnit to set
	 */
	public void setLineGrossWeightUnit(String lineGrossWeightUnit) {
		this.lineGrossWeightUnit = lineGrossWeightUnit;
	}
	/**
	 * @return the unitPrice
	 */
	public Double getUnitPrice() {
		return unitPrice;
	}
	/**
	 * @param unitPrice the unitPrice to set
	 */
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	/**
	 * @return the unitPriceCurrency
	 */
	public String getUnitPriceCurrency() {
		return unitPriceCurrency;
	}
	/**
	 * @param unitPriceCurrency the unitPriceCurrency to set
	 */
	public void setUnitPriceCurrency(String unitPriceCurrency) {
		this.unitPriceCurrency = unitPriceCurrency;
	}
	/**
	 * @return the totalLineAmount
	 */
	public Double getTotalLineAmount() {
		return totalLineAmount;
	}
	/**
	 * @param totalLineAmount the totalLineAmount to set
	 */
	public void setTotalLineAmount(Double totalLineAmount) {
		this.totalLineAmount = totalLineAmount;
	}
	/**
	 * @return the totalLineAmountCurrency
	 */
	public String getTotalLineAmountCurrency() {
		return totalLineAmountCurrency;
	}
	/**
	 * @param totalLineAmountCurrency the totalLineAmountCurrency to set
	 */
	public void setTotalLineAmountCurrency(String totalLineAmountCurrency) {
		this.totalLineAmountCurrency = totalLineAmountCurrency;
	}
	public Long getPackageQuantity() {
		return packageQuantity;
	}
	public void setPackageQuantity(Long packageQuantity) {
		this.packageQuantity = packageQuantity;
	}
	
	
}