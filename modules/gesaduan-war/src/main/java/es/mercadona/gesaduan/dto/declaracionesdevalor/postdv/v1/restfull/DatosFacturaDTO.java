package es.mercadona.gesaduan.dto.declaracionesdevalor.postdv.v1.restfull;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class DatosFacturaDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
	private String numFactura;
	private String anyoFactura;
	private String version;
	private String numDosier;
	private String anyoDosier;
	private String fecha;
	private Integer totalImporte;
	private String codigoExpedicion;
	private String condicionesEntrega;
	private String fechaAlbaran;
	private String fechaEnvio;

	/**
	 * @return the numFactura
	 */
	public String getNumFactura() {
		return numFactura;
	}

	/**
	 * @param numFactura the numFactura to set
	 */
	public void setNumFactura(String numFactura) {
		this.numFactura = numFactura;
	}

	/**
	 * @return the anyoFactura
	 */
	public String getAnyoFactura() {
		return anyoFactura;
	}

	/**
	 * @param anyoFactura the anyoFactura to set
	 */
	public void setAnyoFactura(String anyoFactura) {
		this.anyoFactura = anyoFactura;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return the numDosier
	 */
	public String getNumDosier() {
		return numDosier;
	}

	/**
	 * @param numDosier the numDosier to set
	 */
	public void setNumDosier(String numDosier) {
		this.numDosier = numDosier;
	}

	/**
	 * @return the anyoDosier
	 */
	public String getAnyoDosier() {
		return anyoDosier;
	}

	/**
	 * @param anyoDosier the anyoDosier to set
	 */
	public void setAnyoDosier(String anyoDosier) {
		this.anyoDosier = anyoDosier;
	}

	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the totalImporte
	 */
	public Integer getTotalImporte() {
		return totalImporte;
	}

	/**
	 * @param totalImporte the totalImporte to set
	 */
	public void setTotalImporte(Integer totalImporte) {
		this.totalImporte = totalImporte;
	}

	/**
	 * @return the codigoExpedicion
	 */
	public String getCodigoExpedicion() {
		return codigoExpedicion;
	}

	/**
	 * @param codigoExpedicion the codigoExpedicion to set
	 */
	public void setCodigoExpedicion(String codigoExpedicion) {
		this.codigoExpedicion = codigoExpedicion;
	}

	/**
	 * @return the condicionesEntrega
	 */
	public String getCondicionesEntrega() {
		return condicionesEntrega;
	}

	/**
	 * @param condicionesEntrega the condicionesEntrega to set
	 */
	public void setCondicionesEntrega(String condicionesEntrega) {
		this.condicionesEntrega = condicionesEntrega;
	}

	/**
	 * @return the fechaAlbaran
	 */
	public String getFechaAlbaran() {
		return fechaAlbaran;
	}

	/**
	 * @param fechaAlbaran the fechaAlbaran to set
	 */
	public void setFechaAlbaran(String fechaAlbaran) {
		this.fechaAlbaran = fechaAlbaran;
	}

	/**
	 * @return the fechaEnvio
	 */
	public String getFechaEnvio() {
		return fechaEnvio;
	}

	/**
	 * @param fechaEnvio the fechaEnvio to set
	 */
	public void setFechaEnvio(String fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}

}