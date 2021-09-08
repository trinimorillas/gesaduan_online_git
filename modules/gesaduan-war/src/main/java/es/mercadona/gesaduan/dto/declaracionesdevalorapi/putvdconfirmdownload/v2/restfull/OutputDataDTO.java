package es.mercadona.gesaduan.dto.declaracionesdevalorapi.putvdconfirmdownload.v2.restfull;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class OutputDataDTO extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long numFactura;
	private Integer anyoFactura;
	private String version;

	/**
	 * @return the numFactura
	 */
	public Long getNumFactura() {
		return numFactura;
	}

	/**
	 * @param numFactura the numFactura to set
	 */
	public void setNumFactura(Long numFactura) {
		this.numFactura = numFactura;
	}

	/**
	 * @return the anyoFactura
	 */
	public Integer getAnyoFactura() {
		return anyoFactura;
	}

	/**
	 * @param anyoFactura the anyoFactura to set
	 */
	public void setAnyoFactura(Integer anyoFactura) {
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

}
