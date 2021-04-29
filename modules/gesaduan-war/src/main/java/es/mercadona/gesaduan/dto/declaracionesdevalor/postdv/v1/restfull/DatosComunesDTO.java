package es.mercadona.gesaduan.dto.declaracionesdevalor.postdv.v1.restfull;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class DatosComunesDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
	private boolean esUltimaVigente;
	private boolean esCorrecta;
	private boolean estaNotificada;
	private boolean estaDescargada;
	private boolean esCargaManual;
	private String fechaDescarga;
	private boolean estaDescargadaExportador;
	private String fechaDescargaExportador;
	private boolean estaDescargadaImportador;
	private String fechaDescargaImportador;
	private boolean tienePdf;
	private boolean tieneCsv;

	/**
	 * @return the esUltimaVigente
	 */
	public boolean isEsUltimaVigente() {
		return esUltimaVigente;
	}

	/**
	 * @param esUltimaVigente the esUltimaVigente to set
	 */
	public void setEsUltimaVigente(boolean esUltimaVigente) {
		this.esUltimaVigente = esUltimaVigente;
	}

	/**
	 * @return the esCorrecta
	 */
	public boolean isEsCorrecta() {
		return esCorrecta;
	}

	/**
	 * @param esCorrecta the esCorrecta to set
	 */
	public void setEsCorrecta(boolean esCorrecta) {
		this.esCorrecta = esCorrecta;
	}

	/**
	 * @return the estaNotificada
	 */
	public boolean isEstaNotificada() {
		return estaNotificada;
	}

	/**
	 * @param estaNotificada the estaNotificada to set
	 */
	public void setEstaNotificada(boolean estaNotificada) {
		this.estaNotificada = estaNotificada;
	}

	/**
	 * @return the estaDescargada
	 */
	public boolean isEstaDescargada() {
		return estaDescargada;
	}

	/**
	 * @param estaDescargada the estaDescargada to set
	 */
	public void setEstaDescargada(boolean estaDescargada) {
		this.estaDescargada = estaDescargada;
	}

	/**
	 * @return the esCargaManual
	 */
	public boolean isEsCargaManual() {
		return esCargaManual;
	}

	/**
	 * @param esCargaManual the esCargaManual to set
	 */
	public void setEsCargaManual(boolean esCargaManual) {
		this.esCargaManual = esCargaManual;
	}

	/**
	 * @return the fechaDescarga
	 */
	public String getFechaDescarga() {
		return fechaDescarga;
	}

	/**
	 * @param fechaDescarga the fechaDescarga to set
	 */
	public void setFechaDescarga(String fechaDescarga) {
		this.fechaDescarga = fechaDescarga;
	}

	/**
	 * @return the estaDescargadaExportador
	 */
	public boolean isEstaDescargadaExportador() {
		return estaDescargadaExportador;
	}

	/**
	 * @param estaDescargadaExportador the estaDescargadaExportador to set
	 */
	public void setEstaDescargadaExportador(boolean estaDescargadaExportador) {
		this.estaDescargadaExportador = estaDescargadaExportador;
	}

	/**
	 * @return the fechaDescargaExportador
	 */
	public String getFechaDescargaExportador() {
		return fechaDescargaExportador;
	}

	/**
	 * @param fechaDescargaExportador the fechaDescargaExportador to set
	 */
	public void setFechaDescargaExportador(String fechaDescargaExportador) {
		this.fechaDescargaExportador = fechaDescargaExportador;
	}

	/**
	 * @return the estaDescargadaImportador
	 */
	public boolean isEstaDescargadaImportador() {
		return estaDescargadaImportador;
	}

	/**
	 * @param estaDescargadaImportador the estaDescargadaImportador to set
	 */
	public void setEstaDescargadaImportador(boolean estaDescargadaImportador) {
		this.estaDescargadaImportador = estaDescargadaImportador;
	}

	/**
	 * @return the fechaDescargaImportador
	 */
	public String getFechaDescargaImportador() {
		return fechaDescargaImportador;
	}

	/**
	 * @param fechaDescargaImportador the fechaDescargaImportador to set
	 */
	public void setFechaDescargaImportador(String fechaDescargaImportador) {
		this.fechaDescargaImportador = fechaDescargaImportador;
	}

	/**
	 * @return the tienePdf
	 */
	public boolean isTienePdf() {
		return tienePdf;
	}

	/**
	 * @param tienePdf the tienePdf to set
	 */
	public void setTienePdf(boolean tienePdf) {
		this.tienePdf = tienePdf;
	}

	/**
	 * @return the tieneCsv
	 */
	public boolean isTieneCsv() {
		return tieneCsv;
	}

	/**
	 * @param tieneCsv the tieneCsv to set
	 */
	public void setTieneCsv(boolean tieneCsv) {
		this.tieneCsv = tieneCsv;
	}

}