package es.mercadona.gesaduan.dto.dosier.getdosierdetalle.v1.resfull;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class OrigenDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
	private String codigoOrigen;
	private String nombreOrigen;
	private String provinciaOrigen;
	private String tipoOrigen;

	/**
	 * @return the codigoOrigen
	 */
	public String getCodigoOrigen() {
		return codigoOrigen;
	}

	/**
	 * @param codigoOrigen the codigoOrigen to set
	 */
	public void setCodigoOrigen(String codigoOrigen) {
		this.codigoOrigen = codigoOrigen;
	}

	/**
	 * @return the nombreOrigen
	 */
	public String getNombreOrigen() {
		return nombreOrigen;
	}

	/**
	 * @param nombreOrigen the nombreOrigen to set
	 */
	public void setNombreOrigen(String nombreOrigen) {
		this.nombreOrigen = nombreOrigen;
	}

	/**
	 * @return the provinciaOrigen
	 */
	public String getProvinciaOrigen() {
		return provinciaOrigen;
	}

	/**
	 * @param provinciaOrigen the provinciaOrigen to set
	 */
	public void setProvinciaOrigen(String provinciaOrigen) {
		this.provinciaOrigen = provinciaOrigen;
	}

	/**
	 * @return the tipoOrigen
	 */
	public String getTipoOrigen() {
		return tipoOrigen;
	}

	/**
	 * @param tipoOrigen the tipoOrigen to set
	 */
	public void setTipoOrigen(String tipoOrigen) {
		this.tipoOrigen = tipoOrigen;
	}

}