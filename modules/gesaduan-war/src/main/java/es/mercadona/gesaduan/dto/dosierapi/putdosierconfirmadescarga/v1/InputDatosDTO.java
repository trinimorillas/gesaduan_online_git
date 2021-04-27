package es.mercadona.gesaduan.dto.dosierapi.putdosierconfirmadescarga.v1;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputDatosDTO extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String codigoDosier;
	private String codigoAgencia;
	private Boolean estaDescargado;

	/**
	 * @return the codigoDosier
	 */
	public String getCodigoDosier() {
		return codigoDosier;
	}

	/**
	 * @param codigoDosier the codigoDosier to set
	 */
	public void setCodigoDosier(String codigoDosier) {
		this.codigoDosier = codigoDosier;
	}

	/**
	 * @return the codigoAgencia
	 */
	public String getCodigoAgencia() {
		return codigoAgencia;
	}

	/**
	 * @param codigoAgencia the codigoAgencia to set
	 */
	public void setCodigoAgencia(String codigoAgencia) {
		this.codigoAgencia = codigoAgencia;
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