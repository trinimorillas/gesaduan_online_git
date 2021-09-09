package es.mercadona.gesaduan.jpa.declaracionesdevalorapi.putreturns.v2;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "O_DECLARACION_VALOR_CAB")
@IdClass(ValueDeclarationPK.class)
@Cacheable(false)
public class ValueDeclarationJPA implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "COD_N_DECLARACION_VALOR")
	@SequenceGenerator(name = "ADM_GESADUAN", sequenceName = "DECLARACION_VALOR_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADM_GESADUAN")
	private Long valueDeclarationNumber;

	@Id
	@Column(name = "NUM_ANYO")
	private Integer valueDeclarationYear;

	@Id
	@Column(name = "COD_N_VERSION")
	private Integer valueDeclarationVersion;

	@Column(name = "NUM_DEVOLUCION")
	private Long returnNumber;

	@Column(name = "NUM_ANYO_DEVOLUCION")
	private Integer returnYear;

	@Column(name = "FEC_DT_EXPEDICION")
	@Temporal(TemporalType.TIMESTAMP)
	private Date expeditionDate;

	@Column(name = "COD_N_BLOQUE_LOGISTICO")
	private Integer targetId;

	@Column(name = "COD_V_TIENDA")
	private String sourceId;

	@Column(name = "COD_N_PROVINCIA_CARGA")
	private Integer sourceRegionId;

	@Column(name = "TXT_CONDICIONES_ENTREGA")
	private String deliveryConditions;

	@Column(name = "MCA_FACTURA_DV")
	private String mcaFactura;

	@Column(name = "MCA_ULTIMA_VIGENTE")
	private String mcaLastCurrent;

	@Column(name = "MCA_CARGA_AUTOMATICA")
	private String mcaAutomaticLoading;

	@Column(name = "MCA_DV_CORRECTA")
	private String mcaRightDV;

	@Column(name = "MCA_ENVIO")
	private String mcaShipping;

	@Column(name = "MCA_DESCARGA")
	private String mcaDownload;

	@Column(name = "FEC_D_ENVIO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date returnDate;

	@Column(name = "FEC_DT_CREACION")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

	@Column(name = "FEC_D_CREACION")
	@Temporal(TemporalType.TIMESTAMP)
	private Date recordCreationDate;

	@Column(name = "COD_V_USUARIO_CREACION")
	private String userId;

	@Column(name = "COD_V_APLICACION")
	private String app;

	@Column(name = "COD_V_TIPO_FACTURA")
	private String invoiceType;

	@OneToMany(mappedBy = "valueDeclaration", cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	private List<DeclarationLineJPA> productLines;

	@OneToMany(mappedBy = "valueDeclaration", cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	private List<EnvaseDeclaracionJPA> itemLines;

	/**
	 * @return the valueDeclarationNumber
	 */
	public Long getValueDeclarationNumber() {
		return valueDeclarationNumber;
	}

	/**
	 * @param valueDeclarationNumber the valueDeclarationNumber to set
	 */
	public void setValueDeclarationNumber(Long valueDeclarationNumber) {
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
	 * @return the returnNumber
	 */
	public Long getReturnNumber() {
		return returnNumber;
	}

	/**
	 * @param returnNumber the returnNumber to set
	 */
	public void setReturnNumber(Long returnNumber) {
		this.returnNumber = returnNumber;
	}

	/**
	 * @return the returnYear
	 */
	public Integer getReturnYear() {
		return returnYear;
	}

	/**
	 * @param returnYear the returnYear to set
	 */
	public void setReturnYear(Integer returnYear) {
		this.returnYear = returnYear;
	}

	/**
	 * @return the expeditionDate
	 */
	public Date getExpeditionDate() {
		return expeditionDate;
	}

	/**
	 * @param expeditionDate the expeditionDate to set
	 */
	public void setExpeditionDate(Date expeditionDate) {
		this.expeditionDate = expeditionDate;
	}

	/**
	 * @return the targetId
	 */
	public Integer getTargetId() {
		return targetId;
	}

	/**
	 * @param targetId the targetId to set
	 */
	public void setTargetId(Integer targetId) {
		this.targetId = targetId;
	}

	/**
	 * @return the sourceId
	 */
	public String getSourceId() {
		return sourceId;
	}

	/**
	 * @param sourceId the sourceId to set
	 */
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	/**
	 * @return the sourceRegionId
	 */
	public Integer getSourceRegionId() {
		return sourceRegionId;
	}

	/**
	 * @param sourceRegionId the sourceRegionId to set
	 */
	public void setSourceRegionId(Integer sourceRegionId) {
		this.sourceRegionId = sourceRegionId;
	}

	/**
	 * @return the deliveryConditions
	 */
	public String getDeliveryConditions() {
		return deliveryConditions;
	}

	/**
	 * @param deliveryConditions the deliveryConditions to set
	 */
	public void setDeliveryConditions(String deliveryConditions) {
		this.deliveryConditions = deliveryConditions;
	}

	/**
	 * @return the mcaFactura
	 */
	public String getMcaFactura() {
		return mcaFactura;
	}

	/**
	 * @param mcaFactura the mcaFactura to set
	 */
	public void setMcaFactura(String mcaFactura) {
		this.mcaFactura = mcaFactura;
	}

	/**
	 * @return the mcaLastCurrent
	 */
	public String getMcaLastCurrent() {
		return mcaLastCurrent;
	}

	/**
	 * @param mcaLastCurrent the mcaLastCurrent to set
	 */
	public void setMcaLastCurrent(String mcaLastCurrent) {
		this.mcaLastCurrent = mcaLastCurrent;
	}

	/**
	 * @return the mcaAutomaticLoading
	 */
	public String getMcaAutomaticLoading() {
		return mcaAutomaticLoading;
	}

	/**
	 * @param mcaAutomaticLoading the mcaAutomaticLoading to set
	 */
	public void setMcaAutomaticLoading(String mcaAutomaticLoading) {
		this.mcaAutomaticLoading = mcaAutomaticLoading;
	}

	/**
	 * @return the mcaRightDV
	 */
	public String getMcaRightDV() {
		return mcaRightDV;
	}

	/**
	 * @param mcaRightDV the mcaRightDV to set
	 */
	public void setMcaRightDV(String mcaRightDV) {
		this.mcaRightDV = mcaRightDV;
	}

	/**
	 * @return the mcaShipping
	 */
	public String getMcaShipping() {
		return mcaShipping;
	}

	/**
	 * @param mcaShipping the mcaShipping to set
	 */
	public void setMcaShipping(String mcaShipping) {
		this.mcaShipping = mcaShipping;
	}

	/**
	 * @return the mcaDownload
	 */
	public String getMcaDownload() {
		return mcaDownload;
	}

	/**
	 * @param mcaDownload the mcaDownload to set
	 */
	public void setMcaDownload(String mcaDownload) {
		this.mcaDownload = mcaDownload;
	}

	/**
	 * @return the returnDate
	 */
	public Date getReturnDate() {
		return returnDate;
	}

	/**
	 * @param returnDate the returnDate to set
	 */
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
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
	 * @return the recordCreationDate
	 */
	public Date getRecordCreationDate() {
		return recordCreationDate;
	}

	/**
	 * @param recordCreationDate the recordCreationDate to set
	 */
	public void setRecordCreationDate(Date recordCreationDate) {
		this.recordCreationDate = recordCreationDate;
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
	 * @return the invoiceType
	 */
	public String getInvoiceType() {
		return invoiceType;
	}

	/**
	 * @param invoiceType the invoiceType to set
	 */
	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	/**
	 * @return the productLines
	 */
	public List<DeclarationLineJPA> getProductLines() {
		return productLines;
	}

	/**
	 * @param productLines the productLines to set
	 */
	public void setProductLines(List<DeclarationLineJPA> productLines) {
		this.productLines = productLines;
	}

	/**
	 * @return the itemLines
	 */
	public List<EnvaseDeclaracionJPA> getItemLines() {
		return itemLines;
	}

	/**
	 * @param itemLines the itemLines to set
	 */
	public void setItemLines(List<EnvaseDeclaracionJPA> itemLines) {
		this.itemLines = itemLines;
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
		result = prime * result + ((deliveryConditions == null) ? 0 : deliveryConditions.hashCode());
		result = prime * result + ((expeditionDate == null) ? 0 : expeditionDate.hashCode());
		result = prime * result + ((invoiceType == null) ? 0 : invoiceType.hashCode());
		result = prime * result + ((itemLines == null) ? 0 : itemLines.hashCode());
		result = prime * result + ((mcaAutomaticLoading == null) ? 0 : mcaAutomaticLoading.hashCode());
		result = prime * result + ((mcaDownload == null) ? 0 : mcaDownload.hashCode());
		result = prime * result + ((mcaFactura == null) ? 0 : mcaFactura.hashCode());
		result = prime * result + ((mcaLastCurrent == null) ? 0 : mcaLastCurrent.hashCode());
		result = prime * result + ((mcaRightDV == null) ? 0 : mcaRightDV.hashCode());
		result = prime * result + ((mcaShipping == null) ? 0 : mcaShipping.hashCode());
		result = prime * result + ((productLines == null) ? 0 : productLines.hashCode());
		result = prime * result + ((recordCreationDate == null) ? 0 : recordCreationDate.hashCode());
		result = prime * result + ((returnDate == null) ? 0 : returnDate.hashCode());
		result = prime * result + ((returnNumber == null) ? 0 : returnNumber.hashCode());
		result = prime * result + ((returnYear == null) ? 0 : returnYear.hashCode());
		result = prime * result + ((sourceId == null) ? 0 : sourceId.hashCode());
		result = prime * result + ((sourceRegionId == null) ? 0 : sourceRegionId.hashCode());
		result = prime * result + ((targetId == null) ? 0 : targetId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((valueDeclarationNumber == null) ? 0 : valueDeclarationNumber.hashCode());
		result = prime * result + ((valueDeclarationVersion == null) ? 0 : valueDeclarationVersion.hashCode());
		result = prime * result + ((valueDeclarationYear == null) ? 0 : valueDeclarationYear.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ValueDeclarationJPA other = (ValueDeclarationJPA) obj;
		if (app == null) {
			if (other.app != null) {
				return false;
			}
		} else if (!app.equals(other.app)) {
			return false;
		}
		if (creationDate == null) {
			if (other.creationDate != null) {
				return false;
			}
		} else if (!creationDate.equals(other.creationDate)) {
			return false;
		}
		if (deliveryConditions == null) {
			if (other.deliveryConditions != null) {
				return false;
			}
		} else if (!deliveryConditions.equals(other.deliveryConditions)) {
			return false;
		}
		if (expeditionDate == null) {
			if (other.expeditionDate != null) {
				return false;
			}
		} else if (!expeditionDate.equals(other.expeditionDate)) {
			return false;
		}
		if (invoiceType == null) {
			if (other.invoiceType != null) {
				return false;
			}
		} else if (!invoiceType.equals(other.invoiceType)) {
			return false;
		}
		if (itemLines == null) {
			if (other.itemLines != null) {
				return false;
			}
		} else if (!itemLines.equals(other.itemLines)) {
			return false;
		}
		if (mcaAutomaticLoading == null) {
			if (other.mcaAutomaticLoading != null) {
				return false;
			}
		} else if (!mcaAutomaticLoading.equals(other.mcaAutomaticLoading)) {
			return false;
		}
		if (mcaDownload == null) {
			if (other.mcaDownload != null) {
				return false;
			}
		} else if (!mcaDownload.equals(other.mcaDownload)) {
			return false;
		}
		if (mcaFactura == null) {
			if (other.mcaFactura != null) {
				return false;
			}
		} else if (!mcaFactura.equals(other.mcaFactura)) {
			return false;
		}
		if (mcaLastCurrent == null) {
			if (other.mcaLastCurrent != null) {
				return false;
			}
		} else if (!mcaLastCurrent.equals(other.mcaLastCurrent)) {
			return false;
		}
		if (mcaRightDV == null) {
			if (other.mcaRightDV != null) {
				return false;
			}
		} else if (!mcaRightDV.equals(other.mcaRightDV)) {
			return false;
		}
		if (mcaShipping == null) {
			if (other.mcaShipping != null) {
				return false;
			}
		} else if (!mcaShipping.equals(other.mcaShipping)) {
			return false;
		}
		if (productLines == null) {
			if (other.productLines != null) {
				return false;
			}
		} else if (!productLines.equals(other.productLines)) {
			return false;
		}
		if (recordCreationDate == null) {
			if (other.recordCreationDate != null) {
				return false;
			}
		} else if (!recordCreationDate.equals(other.recordCreationDate)) {
			return false;
		}
		if (returnDate == null) {
			if (other.returnDate != null) {
				return false;
			}
		} else if (!returnDate.equals(other.returnDate)) {
			return false;
		}
		if (returnNumber == null) {
			if (other.returnNumber != null) {
				return false;
			}
		} else if (!returnNumber.equals(other.returnNumber)) {
			return false;
		}
		if (returnYear == null) {
			if (other.returnYear != null) {
				return false;
			}
		} else if (!returnYear.equals(other.returnYear)) {
			return false;
		}
		if (sourceId == null) {
			if (other.sourceId != null) {
				return false;
			}
		} else if (!sourceId.equals(other.sourceId)) {
			return false;
		}
		if (sourceRegionId == null) {
			if (other.sourceRegionId != null) {
				return false;
			}
		} else if (!sourceRegionId.equals(other.sourceRegionId)) {
			return false;
		}
		if (targetId == null) {
			if (other.targetId != null) {
				return false;
			}
		} else if (!targetId.equals(other.targetId)) {
			return false;
		}
		if (userId == null) {
			if (other.userId != null) {
				return false;
			}
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (valueDeclarationNumber == null) {
			if (other.valueDeclarationNumber != null) {
				return false;
			}
		} else if (!valueDeclarationNumber.equals(other.valueDeclarationNumber)) {
			return false;
		}
		if (valueDeclarationVersion == null) {
			if (other.valueDeclarationVersion != null) {
				return false;
			}
		} else if (!valueDeclarationVersion.equals(other.valueDeclarationVersion)) {
			return false;
		}
		if (valueDeclarationYear == null) {
			if (other.valueDeclarationYear != null) {
				return false;
			}
		} else if (!valueDeclarationYear.equals(other.valueDeclarationYear)) {
			return false;
		}
		return true;
	}

}
