package es.mercadona.gesaduan.dto.declaracionesdevalorapi.putfacturaconfirmadescarga.v1;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputDatosDTO extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String codigoFactura;
	private String codigoProveedor;
	private Boolean estaDescargado;

	/**
	 * @return the codigoFactura
	 */
	public String getCodigoFactura() {
		return codigoFactura;
	}

	/**
	 * @param codigoFactura the codigoFactura to set
	 */
	public void setCodigoFactura(String codigoFactura) {
		this.codigoFactura = codigoFactura;
	}

	/**
	 * @return the codigoProveedor
	 */
	public String getCodigoProveedor() {
		return codigoProveedor;
	}

	/**
	 * @param codigoProveedor the codigoProveedor to set
	 */
	public void setCodigoProveedor(String codigoProveedor) {
		this.codigoProveedor = codigoProveedor;
	}

	/**
	 * @return the estaDescargado
	 */
	public Boolean getEstaDescargado() {
		return estaDescargado;
	}

	/**
	 * @param estaDescargado the estaDescargado to set
	 */
	public void setEstaDescargado(Boolean estaDescargado) {
		this.estaDescargado = estaDescargado;
	}

}
