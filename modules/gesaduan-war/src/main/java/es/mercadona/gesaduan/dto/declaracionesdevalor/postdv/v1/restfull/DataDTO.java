package es.mercadona.gesaduan.dto.declaracionesdevalor.postdv.v1.restfull;

import java.util.List;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class DataDTO extends AbstractDTO {
	private static final long serialVersionUID = 1L;
	private ValueDeclarationCommonDataDTO valueDeclarationCommonData;
	private ValueDeclarationHeaderDTO valueDeclarationHeader;
	private List<ValueDeclarationHistoricalDTO> valueDeclarationHistorical;
	private List<ValueDeclarationLineDTO> valueDeclarationLineDTO;

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
	public List<ValueDeclarationHistoricalDTO> getValueDeclarationHistorical() {
		return valueDeclarationHistorical;
	}

	/**
	 * @param valueDeclarationHistorical the valueDeclarationHistorical to set
	 */
	public void setValueDeclarationHistorical(List<ValueDeclarationHistoricalDTO> valueDeclarationHistorical) {
		this.valueDeclarationHistorical = valueDeclarationHistorical;
	}

	/**
	 * @return the valueDeclarationLineDTO
	 */
	public List<ValueDeclarationLineDTO> getValueDeclarationLineDTO() {
		return valueDeclarationLineDTO;
	}

	/**
	 * @param valueDeclarationLineDTO the valueDeclarationLineDTO to set
	 */
	public void setValueDeclarationLineDTO(List<ValueDeclarationLineDTO> valueDeclarationLineDTO) {
		this.valueDeclarationLineDTO = valueDeclarationLineDTO;
	}

}
