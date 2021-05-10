package es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvaluedeclarationdetail.v1.restfull;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class DataDTO extends AbstractDTO {
	private static final long serialVersionUID = 1L;
	private ValueDeclarationCommonDataDTO valueDeclarationCommonData;
	private ValueDeclarationHeaderDTO valueDeclarationHeader;
	private ValueDeclarationHistoricalDTO valueDeclarationHistorical;
	private ValueDeclarationLineDTO valueDeclarationLineDTO;

	/**
	 * @return the valueDeclarationCommonData
	 */
	public ValueDeclarationCommonDataDTO getValueDeclarationCommonData() {
		return valueDeclarationCommonData;
	}

	/**
	 * @param valueDeclarationCommonData the valueDeclarationCommonData to set
	 */
	public void setValueDeclarationCommonData(ValueDeclarationCommonDataDTO valueDeclarationCommonData) {
		this.valueDeclarationCommonData = valueDeclarationCommonData;
	}

	/**
	 * @return the valueDeclarationHeader
	 */
	public ValueDeclarationHeaderDTO getValueDeclarationHeader() {
		return valueDeclarationHeader;
	}

	/**
	 * @param valueDeclarationHeader the valueDeclarationHeader to set
	 */
	public void setValueDeclarationHeader(ValueDeclarationHeaderDTO valueDeclarationHeader) {
		this.valueDeclarationHeader = valueDeclarationHeader;
	}

	/**
	 * @return the valueDeclarationHistorical
	 */
	public ValueDeclarationHistoricalDTO getValueDeclarationHistorical() {
		return valueDeclarationHistorical;
	}

	/**
	 * @param valueDeclarationHistorical the valueDeclarationHistorical to set
	 */
	public void setValueDeclarationHistorical(ValueDeclarationHistoricalDTO valueDeclarationHistorical) {
		this.valueDeclarationHistorical = valueDeclarationHistorical;
	}

	/**
	 * @return the valueDeclarationLineDTO
	 */
	public ValueDeclarationLineDTO getValueDeclarationLineDTO() {
		return valueDeclarationLineDTO;
	}

	/**
	 * @param valueDeclarationLineDTO the valueDeclarationLineDTO to set
	 */
	public void setValueDeclarationLineDTO(ValueDeclarationLineDTO valueDeclarationLineDTO) {
		this.valueDeclarationLineDTO = valueDeclarationLineDTO;
	}

}
