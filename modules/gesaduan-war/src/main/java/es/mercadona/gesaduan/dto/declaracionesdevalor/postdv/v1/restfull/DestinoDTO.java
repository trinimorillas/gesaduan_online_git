package es.mercadona.gesaduan.dto.declaracionesdevalor.postdv.v1.restfull;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class DestinoDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
	private String codigoDestino;
	private String nombreDestino;
	private String tipoDestino;

	/**
	 * @return the codigoDestino
	 */
	public String getCodigoDestino() {
		return codigoDestino;
	}

	/**
	 * @param codigoDestino the codigoDestino to set
	 */
	public void setCodigoDestino(String codigoDestino) {
		this.codigoDestino = codigoDestino;
	}

	/**
	 * @return the nombreDestino
	 */
	public String getNombreDestino() {
		return nombreDestino;
	}

	/**
	 * @param nombreDestino the nombreDestino to set
	 */
	public void setNombreDestino(String nombreDestino) {
		this.nombreDestino = nombreDestino;
	}

	/**
	 * @return the tipoDestino
	 */
	public String getTipoDestino() {
		return tipoDestino;
	}

	/**
	 * @param tipoDestino the tipoDestino to set
	 */
	public void setTipoDestino(String tipoDestino) {
		this.tipoDestino = tipoDestino;
	}

}
