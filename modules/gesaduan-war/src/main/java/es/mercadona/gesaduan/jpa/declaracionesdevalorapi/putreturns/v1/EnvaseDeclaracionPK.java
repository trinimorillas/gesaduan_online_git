package es.mercadona.gesaduan.jpa.declaracionesdevalorapi.putreturns.v1;

import java.io.Serializable;

public class EnvaseDeclaracionPK implements Serializable {

	private static final long serialVersionUID = 1L;
	private ValueDeclarationPK valueDeclaration;
	private String itemId;

	/**
	 * @return the valueDeclaration
	 */
	public ValueDeclarationPK getValueDeclaration() {
		return valueDeclaration;
	}

	/**
	 * @param valueDeclaration the valueDeclaration to set
	 */
	public void setValueDeclaration(ValueDeclarationPK valueDeclaration) {
		this.valueDeclaration = valueDeclaration;
	}

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itemId == null) ? 0 : itemId.hashCode());
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
		EnvaseDeclaracionPK other = (EnvaseDeclaracionPK) obj;
		if (itemId == null) {
			if (other.itemId != null)
				return false;
		} else if (!itemId.equals(other.itemId))
			return false;
		if (valueDeclaration == null) {
			if (other.valueDeclaration != null)
				return false;
		} else if (!valueDeclaration.equals(other.valueDeclaration))
			return false;
		return true;
	}

}