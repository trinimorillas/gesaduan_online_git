package es.mercadona.gesaduan.jpa.declaracionesdevalorapi.putreturns.v1;

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
@Table(name = "O_DECLARACION_VALOR_LIN")
@IdClass(DeclarationLinePK.class)
public class DeclarationLineJPA implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "COD_N_MERCA")
	private Integer itemId;

	@Id
	@Column(name = "TXT_NOMBRE_BULTO_DV")
	private String namePackage;

	@Id
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumns({ @JoinColumn(name = "COD_N_DECLARACION_VALOR", referencedColumnName = "COD_N_DECLARACION_VALOR"),
			@JoinColumn(name = "NUM_ANYO", referencedColumnName = "NUM_ANYO"),
			@JoinColumn(name = "COD_N_VERSION", referencedColumnName = "COD_N_VERSION") })
	private ValueDeclarationJPA valueDeclaration;

	@Column(name = "COD_N_TARIC")
	private Long itemTaric;

	@Column(name = "COD_V_REA")
	private String itemSsrId;

	@Column(name = "NUM_BULTOS")
	private Integer packageQuantity;

	@Column(name = "NUM_PESO_NETO")
	private Double netWeight;

	@Column(name = "NUM_PESO_BRUTO")
	private Double grossWeight;

	@Column(name = "NUM_VOLUMEN")
	private Double volume;

	@Column(name = "NUM_CANTIDAD")
	private Double quantity;

	@Column(name = "NUM_PRECIO_UNIDAD")
	private Double unitPrice;

	@Column(name = "NUM_IMPORTE_TOTAL")
	private Double totalAmount;

	@Column(name = "NUM_GRADO_ALCOHOL")
	private Double alcoholPercentage;

	@Column(name = "NUM_GRADO_PLATO")
	private Double plateGrade;

	@Column(name = "COD_V_PAIS")
	private String country;

	@Column(name = "MCA_PRODUCTO_LPC")
	private String isReadyToEat;

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
	public Integer getItemId() {
		return itemId;
	}

	/**
	 * @param itemId the itemId to set
	 */
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	/**
	 * @return the namePackage
	 */
	public String getNamePackage() {
		return namePackage;
	}

	/**
	 * @param namePackage the namePackage to set
	 */
	public void setNamePackage(String namePackage) {
		this.namePackage = namePackage;
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
	 * @return the itemSsrId
	 */
	public String getItemSsrId() {
		return itemSsrId;
	}

	/**
	 * @param itemSsrId the itemSsrId to set
	 */
	public void setItemSsrId(String itemSsrId) {
		this.itemSsrId = itemSsrId;
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
	 * @return the volume
	 */
	public Double getVolume() {
		return volume;
	}

	/**
	 * @param volume the volume to set
	 */
	public void setVolume(Double volume) {
		this.volume = volume;
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
	 * @return the alcoholPercentage
	 */
	public Double getAlcoholPercentage() {
		return alcoholPercentage;
	}

	/**
	 * @param alcoholPercentage the alcoholPercentage to set
	 */
	public void setAlcoholPercentage(Double alcoholPercentage) {
		this.alcoholPercentage = alcoholPercentage;
	}

	/**
	 * @return the plateGrade
	 */
	public Double getPlateGrade() {
		return plateGrade;
	}

	/**
	 * @param plateGrade the plateGrade to set
	 */
	public void setPlateGrade(Double plateGrade) {
		this.plateGrade = plateGrade;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the isReadyToEat
	 */
	public String getIsReadyToEat() {
		return isReadyToEat;
	}

	/**
	 * @param isReadyToEat the isReadyToEat to set
	 */
	public void setIsReadyToEat(String isReadyToEat) {
		this.isReadyToEat = isReadyToEat;
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
		result = prime * result + ((alcoholPercentage == null) ? 0 : alcoholPercentage.hashCode());
		result = prime * result + ((app == null) ? 0 : app.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((grossWeight == null) ? 0 : grossWeight.hashCode());
		result = prime * result + ((isReadyToEat == null) ? 0 : isReadyToEat.hashCode());
		result = prime * result + ((itemId == null) ? 0 : itemId.hashCode());
		result = prime * result + ((itemSsrId == null) ? 0 : itemSsrId.hashCode());
		result = prime * result + ((itemTaric == null) ? 0 : itemTaric.hashCode());
		result = prime * result + ((mcaError == null) ? 0 : mcaError.hashCode());
		result = prime * result + ((namePackage == null) ? 0 : namePackage.hashCode());
		result = prime * result + ((netWeight == null) ? 0 : netWeight.hashCode());
		result = prime * result + ((packageQuantity == null) ? 0 : packageQuantity.hashCode());
		result = prime * result + ((plateGrade == null) ? 0 : plateGrade.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		result = prime * result + ((totalAmount == null) ? 0 : totalAmount.hashCode());
		result = prime * result + ((unitPrice == null) ? 0 : unitPrice.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((valueDeclaration == null) ? 0 : valueDeclaration.hashCode());
		result = prime * result + ((volume == null) ? 0 : volume.hashCode());
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
		DeclarationLineJPA other = (DeclarationLineJPA) obj;
		if (alcoholPercentage == null) {
			if (other.alcoholPercentage != null)
				return false;
		} else if (!alcoholPercentage.equals(other.alcoholPercentage))
			return false;
		if (app == null) {
			if (other.app != null)
				return false;
		} else if (!app.equals(other.app))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
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
		if (isReadyToEat == null) {
			if (other.isReadyToEat != null)
				return false;
		} else if (!isReadyToEat.equals(other.isReadyToEat))
			return false;
		if (itemId == null) {
			if (other.itemId != null)
				return false;
		} else if (!itemId.equals(other.itemId))
			return false;
		if (itemSsrId == null) {
			if (other.itemSsrId != null)
				return false;
		} else if (!itemSsrId.equals(other.itemSsrId))
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
		if (namePackage == null) {
			if (other.namePackage != null)
				return false;
		} else if (!namePackage.equals(other.namePackage))
			return false;
		if (netWeight == null) {
			if (other.netWeight != null)
				return false;
		} else if (!netWeight.equals(other.netWeight))
			return false;
		if (packageQuantity == null) {
			if (other.packageQuantity != null)
				return false;
		} else if (!packageQuantity.equals(other.packageQuantity))
			return false;
		if (plateGrade == null) {
			if (other.plateGrade != null)
				return false;
		} else if (!plateGrade.equals(other.plateGrade))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		if (totalAmount == null) {
			if (other.totalAmount != null)
				return false;
		} else if (!totalAmount.equals(other.totalAmount))
			return false;
		if (unitPrice == null) {
			if (other.unitPrice != null)
				return false;
		} else if (!unitPrice.equals(other.unitPrice))
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
		if (volume == null) {
			if (other.volume != null)
				return false;
		} else if (!volume.equals(other.volume))
			return false;
		return true;
	}

}
