package es.mercadona.gesaduan.dto.declaracionesdevalorapi.putreturns.v1.restfull;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class OutputDataDTO extends AbstractDTO implements Serializable {

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

}
