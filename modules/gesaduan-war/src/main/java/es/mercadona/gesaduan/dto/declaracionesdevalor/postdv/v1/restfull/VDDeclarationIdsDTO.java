package es.mercadona.gesaduan.dto.declaracionesdevalor.postdv.v1.restfull;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class VDDeclarationIdsDTO extends AbstractDTO {
	
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
