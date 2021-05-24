package es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvdsumary.v1.resfull;

import java.io.Serializable;
import java.util.List;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class DataVDeclarationIdsDTO extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String valueDeclarationNumber;
	private String valueDeclarationYear;	
	private String valueDeclarationVersion;
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
	
	

}
