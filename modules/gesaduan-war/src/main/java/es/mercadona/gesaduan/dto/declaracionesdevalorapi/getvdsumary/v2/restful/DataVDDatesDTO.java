package es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvdsumary.v2.restful;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class DataVDDatesDTO extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String dispatchDate;
	private String valueDeclarationGenerationDate;
	private String valueDeclarationDownloadDate;
	private String exportDownloadDate;
	private String importDownloadDate;
	/**
	 * @return the dispatchDate
	 */
	public String getDispatchDate() {
		return dispatchDate;
	}
	/**
	 * @param dispatchDate the dispatchDate to set
	 */
	public void setDispatchDate(String dispatchDate) {
		this.dispatchDate = dispatchDate;
	}
	/**
	 * @return the valueDeclarationGenerationDate
	 */
	public String getValueDeclarationGenerationDate() {
		return valueDeclarationGenerationDate;
	}
	/**
	 * @param valueDeclarationGenerationDate the valueDeclarationGenerationDate to set
	 */
	public void setValueDeclarationGenerationDate(String valueDeclarationGenerationDate) {
		this.valueDeclarationGenerationDate = valueDeclarationGenerationDate;
	}
	/**
	 * @return the valueDeclarationDownloadDate
	 */
	public String getValueDeclarationDownloadDate() {
		return valueDeclarationDownloadDate;
	}
	/**
	 * @param valueDeclarationDownloadDate the valueDeclarationDownloadDate to set
	 */
	public void setValueDeclarationDownloadDate(String valueDeclarationDownloadDate) {
		this.valueDeclarationDownloadDate = valueDeclarationDownloadDate;
	}
	/**
	 * @return the exportDownloadDate
	 */
	public String getExportDownloadDate() {
		return exportDownloadDate;
	}
	/**
	 * @param exportDownloadDate the exportDownloadDate to set
	 */
	public void setExportDownloadDate(String exportDownloadDate) {
		this.exportDownloadDate = exportDownloadDate;
	}
	/**
	 * @return the importDownloadDate
	 */
	public String getImportDownloadDate() {
		return importDownloadDate;
	}
	/**
	 * @param importDownloadDate the importDownloadDate to set
	 */
	public void setImportDownloadDate(String importDownloadDate) {
		this.importDownloadDate = importDownloadDate;
	}
	
	
	
}
