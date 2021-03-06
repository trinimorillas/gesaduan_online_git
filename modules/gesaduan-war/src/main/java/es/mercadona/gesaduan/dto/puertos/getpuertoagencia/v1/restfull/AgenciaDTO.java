package es.mercadona.gesaduan.dto.puertos.getpuertoagencia.v1.restfull;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class AgenciaDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;

	private String codigoAgencia;
	private String nombreAgencia;

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
	 * @return the nombreAgencia
	 */
	public String getNombreAgencia() {
		return nombreAgencia;
	}

	/**
	 * @param nombreAgencia the nombreAgencia to set
	 */
	public void setNombreAgencia(String nombreAgencia) {
		this.nombreAgencia = nombreAgencia;
	}

}
