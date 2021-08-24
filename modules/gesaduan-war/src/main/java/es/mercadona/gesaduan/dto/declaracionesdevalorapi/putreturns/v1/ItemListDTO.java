package es.mercadona.gesaduan.dto.declaracionesdevalorapi.putreturns.v1;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class ItemListDTO extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String itemId;
	private String itemTypeId;
	private String itemName;
	private Integer packageQuantity;
	private Double quantity;
	private String quantityUnit;
	private Double netWeight;
	private String netWeightUnit;
	private Double grossWeight;
	private String grossWeightUnit;
	private Double unitPrice;
	private String unitPriceCurrency;
	private Double totalAmount;
	private String totalAmountCurrency;

	/**
	 * @return the itemId
	 */
	public String getItemId() {
		return itemId;
	}

	/**
	 * @param itemId the itemId to set
	 */
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	/**
	 * @return the itemTypeId
	 */
	public String getItemTypeId() {
		return itemTypeId;
	}

	/**
	 * @param itemTypeId the itemTypeId to set
	 */
	public void setItemTypeId(String itemTypeId) {
		this.itemTypeId = itemTypeId;
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
	 * @return the packageQuantity
	 */
	public Integer getPackageQuantity() {
		return packageQuantity;
	}

	/**
	 * @param packageQuantity the packageQuantity to set
	 */
	public void setPackageQuantity(Integer packageQuantity) {
		this.packageQuantity = packageQuantity;
	}

	/**
	 * @return the quantity
	 */
	public Double getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Double quantity) {
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
	 * @return the netWeight
	 */
	public Double getNetWeight() {
		return netWeight;
	}

	/**
	 * @param netWeight the netWeight to set
	 */
	public void setNetWeight(Double netWeight) {
		this.netWeight = netWeight;
	}

	/**
	 * @return the netWeightUnit
	 */
	public String getNetWeightUnit() {
		return netWeightUnit;
	}

	/**
	 * @param netWeightUnit the netWeightUnit to set
	 */
	public void setNetWeightUnit(String netWeightUnit) {
		this.netWeightUnit = netWeightUnit;
	}

	/**
	 * @return the grossWeight
	 */
	public Double getGrossWeight() {
		return grossWeight;
	}

	/**
	 * @param grossWeight the grossWeight to set
	 */
	public void setGrossWeight(Double grossWeight) {
		this.grossWeight = grossWeight;
	}

	/**
	 * @return the grossWeightUnit
	 */
	public String getGrossWeightUnit() {
		return grossWeightUnit;
	}

	/**
	 * @param grossWeightUnit the grossWeightUnit to set
	 */
	public void setGrossWeightUnit(String grossWeightUnit) {
		this.grossWeightUnit = grossWeightUnit;
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

}
