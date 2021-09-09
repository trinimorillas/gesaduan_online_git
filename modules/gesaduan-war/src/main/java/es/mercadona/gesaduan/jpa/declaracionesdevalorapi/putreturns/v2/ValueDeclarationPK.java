package es.mercadona.gesaduan.jpa.declaracionesdevalorapi.putreturns.v2;

import java.io.Serializable;

public class ValueDeclarationPK implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long valueDeclarationNumber;	
	private Integer valueDeclarationYear; 
	private Integer valueDeclarationVersion;

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		ValueDeclarationPK other = (ValueDeclarationPK) obj;
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
