package es.mercadona.gesaduan.dto.declaracionesdevalor.postdv.v1.restfull;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class ValueDeclarationCommonDataDTO extends AbstractDTO {
	private static final long serialVersionUID = 1L;
	private boolean isLastCurrent;
	private boolean isValueDeclarationOk;
	private boolean isNotified;
	private String valueDeclarationDownloadDate;
	private String exportDownloadDate;
	private String importDownloadDate;
	private boolean isManualLoading;

	/**
	 * @return the isLastCurrent
	 */
	public boolean isLastCurrent() {
		return isLastCurrent;
	}

	/**
	 * @param isLastCurrent the isLastCurrent to set
	 */
	public void setLastCurrent(boolean isLastCurrent) {
		this.isLastCurrent = isLastCurrent;
	}

	/**
	 * @return the isValueDeclarationOk
	 */
	public boolean isValueDeclarationOk() {
		return isValueDeclarationOk;
	}

	/**
	 * @param isValueDeclarationOk the isValueDeclarationOk to set
	 */
	public void setValueDeclarationOk(boolean isValueDeclarationOk) {
		this.isValueDeclarationOk = isValueDeclarationOk;
	}

	/**
	 * @return the isNotified
	 */
	public boolean isNotified() {
		return isNotified;
	}

	/**
	 * @param isNotified the isNotified to set
	 */
	public void setNotified(boolean isNotified) {
		this.isNotified = isNotified;
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

	/**
	 * @return the isManualLoading
	 */
	public boolean isManualLoading() {
		return isManualLoading;
	}

	/**
	 * @param isManualLoading the isManualLoading to set
	 */
	public void setManualLoading(boolean isManualLoading) {
		this.isManualLoading = isManualLoading;
	}

}
