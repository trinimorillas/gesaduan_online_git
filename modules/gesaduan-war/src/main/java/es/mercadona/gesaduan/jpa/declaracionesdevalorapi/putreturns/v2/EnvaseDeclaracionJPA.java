package es.mercadona.gesaduan.jpa.declaracionesdevalorapi.putreturns.v2;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "O_DECLARACION_VALOR_ENV")
@IdClass(EnvaseDeclaracionPK.class)
public class EnvaseDeclaracionJPA implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "COD_V_ENVASE")
	private String itemId;

	@Id
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumns({ @JoinColumn(name = "COD_N_DECLARACION_VALOR", referencedColumnName = "COD_N_DECLARACION_VALOR"),
			@JoinColumn(name = "NUM_ANYO", referencedColumnName = "NUM_ANYO"),
			@JoinColumn(name = "COD_N_VERSION", referencedColumnName = "COD_N_VERSION") })
	private ValueDeclarationJPA valueDeclaration;

	@Column(name = "TXT_DENOMINACION")
	private String itemName;

	@Column(name = "TXT_NOMBRE_BULTO_DV")
	private String packageName;

	@Column(name = "NUM_BULTOS")
	private Integer packageQuantity;

	@Column(name = "NUM_CANTIDAD")
	private Double quantity;

	@Column(name = "COD_V_UM_CANTIDAD")
	private String quantityUnit;

	@Column(name = "NUM_PESO_NETO")
	private Double netWeight;

	@Column(name = "COD_V_UM_PESO_NETO")
	private String netWeightUnit;

	@Column(name = "NUM_PESO_BRUTO")
	private Double grossWeight;

	@Column(name = "COD_V_UM_PESO_BRUTO")
	private String grossWeightUnit;

	@Column(name = "NUM_PRECIO_UNIDAD")
	private Double unitPrice;

	@Column(name = "COD_V_UM_PRECIO")
	private String unitPriceCurrency;

	@Column(name = "NUM_IMPORTE")
	private Double totalAmount;

	@Column(name = "COD_V_UM_IMPORTE")
	private String totalAmountCurrency;

	@Column(name = "COD_N_TARIC")
	private Long itemTaric;

	@Column(name = "MCA_ERROR")
	private String mcaError;

	@Column(name = "FEC_D_CREACION")
	@Temporal(TemporalType.DATE)
	private Date creationDate;

	@Column(name = "COD_V_APLICACION")
	private String app;

	@Column(name = "COD_V_USUARIO_CREACION")
	private String userId;

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
	 * @return the valueDeclaration
	 */
	public ValueDeclarationJPA getValueDeclaration() {
		return valueDeclaration;
	}

	/**
	 * @param valueDeclaration the valueDeclaration to set
	 */
	public void setValueDeclaration(ValueDeclarationJPA valueDeclaration) {
		this.valueDeclaration = valueDeclaration;
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
	 * @return the packageName
	 */
	public String getPackageName() {
		return packageName;
	}

	/**
	 * @param packageName the packageName to set
	 */
	public void setPackageName(String packageName) {
		this.packageName = packageName;
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

	/**
	 * @return the itemTaric
	 */
	public Long getItemTaric() {
		return itemTaric;
	}

	/**
	 * @param itemTaric the itemTaric to set
	 */
	public void setItemTaric(Long itemTaric) {
		this.itemTaric = itemTaric;
	}

	/**
	 * @return the mcaError
	 */
	public String getMcaError() {
		return mcaError;
	}

	/**
	 * @param mcaError the mcaError to set
	 */
	public void setMcaError(String mcaError) {
		this.mcaError = mcaError;
	}

	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return the app
	 */
	public String getApp() {
		return app;
	}

	/**
	 * @param app the app to set
	 */
	public void setApp(String app) {
		this.app = app;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((app == null) ? 0 : app.hashCode());
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((grossWeight == null) ? 0 : grossWeight.hashCode());
		result = prime * result + ((grossWeightUnit == null) ? 0 : grossWeightUnit.hashCode());
		result = prime * result + ((itemId == null) ? 0 : itemId.hashCode());
		result = prime * result + ((itemName == null) ? 0 : itemName.hashCode());
		result = prime * result + ((itemTaric == null) ? 0 : itemTaric.hashCode());
		result = prime * result + ((mcaError == null) ? 0 : mcaError.hashCode());
		result = prime * result + ((netWeight == null) ? 0 : netWeight.hashCode());
		result = prime * result + ((netWeightUnit == null) ? 0 : netWeightUnit.hashCode());
		result = prime * result + ((packageName == null) ? 0 : packageName.hashCode());
		result = prime * result + ((packageQuantity == null) ? 0 : packageQuantity.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		result = prime * result + ((quantityUnit == null) ? 0 : quantityUnit.hashCode());
		result = prime * result + ((totalAmount == null) ? 0 : totalAmount.hashCode());
		result = prime * result + ((totalAmountCurrency == null) ? 0 : totalAmountCurrency.hashCode());
		result = prime * result + ((unitPrice == null) ? 0 : unitPrice.hashCode());
		result = prime * result + ((unitPriceCurrency == null) ? 0 : unitPriceCurrency.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((valueDeclaration == null) ? 0 : valueDeclaration.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EnvaseDeclaracionJPA other = (EnvaseDeclaracionJPA) obj;
		if (app == null) {
			if (other.app != null)
				return false;
		} else if (!app.equals(other.app))
			return false;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (grossWeight == null) {
			if (other.grossWeight != null)
				return false;
		} else if (!grossWeight.equals(other.grossWeight))
			return false;
		if (grossWeightUnit == null) {
			if (other.grossWeightUnit != null)
				return false;
		} else if (!grossWeightUnit.equals(other.grossWeightUnit))
			return false;
		if (itemId == null) {
			if (other.itemId != null)
				return false;
		} else if (!itemId.equals(other.itemId))
			return false;
		if (itemName == null) {
			if (other.itemName != null)
				return false;
		} else if (!itemName.equals(other.itemName))
			return false;
		if (itemTaric == null) {
			if (other.itemTaric != null)
				return false;
		} else if (!itemTaric.equals(other.itemTaric))
			return false;
		if (mcaError == null) {
			if (other.mcaError != null)
				return false;
		} else if (!mcaError.equals(other.mcaError))
			return false;
		if (netWeight == null) {
			if (other.netWeight != null)
				return false;
		} else if (!netWeight.equals(other.netWeight))
			return false;
		if (netWeightUnit == null) {
			if (other.netWeightUnit != null)
				return false;
		} else if (!netWeightUnit.equals(other.netWeightUnit))
			return false;
		if (packageName == null) {
			if (other.packageName != null)
				return false;
		} else if (!packageName.equals(other.packageName))
			return false;
		if (packageQuantity == null) {
			if (other.packageQuantity != null)
				return false;
		} else if (!packageQuantity.equals(other.packageQuantity))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		if (quantityUnit == null) {
			if (other.quantityUnit != null)
				return false;
		} else if (!quantityUnit.equals(other.quantityUnit))
			return false;
		if (totalAmount == null) {
			if (other.totalAmount != null)
				return false;
		} else if (!totalAmount.equals(other.totalAmount))
			return false;
		if (totalAmountCurrency == null) {
			if (other.totalAmountCurrency != null)
				return false;
		} else if (!totalAmountCurrency.equals(other.totalAmountCurrency))
			return false;
		if (unitPrice == null) {
			if (other.unitPrice != null)
				return false;
		} else if (!unitPrice.equals(other.unitPrice))
			return false;
		if (unitPriceCurrency == null) {
			if (other.unitPriceCurrency != null)
				return false;
		} else if (!unitPriceCurrency.equals(other.unitPriceCurrency))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (valueDeclaration == null) {
			if (other.valueDeclaration != null)
				return false;
		} else if (!valueDeclaration.equals(other.valueDeclaration))
			return false;
		return true;
	}

}
