package es.mercadona.gesaduan.dto.puertos.getpuertoagencia.v1.restfull;

import java.util.List;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class PuertoDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;

	private Long codigoPuerto;
	private String nombrePuerto;
	private String codigoAgenciaPreferente;
	private String nombreAgenciaPreferente;
	private List<AgenciaDTO> agencia;

	/**
	 * @return the codigoPuerto
	 */
	public Long getCodigoPuerto() {
		return codigoPuerto;
	}

	/**
	 * @param codigoPuerto the codigoPuerto to set
	 */
	public void setCodigoPuerto(Long codigoPuerto) {
		this.codigoPuerto = codigoPuerto;
	}

	/**
	 * @return the nombrePuerto
	 */
	public String getNombrePuerto() {
		return nombrePuerto;
	}

	/**
	 * @param nombrePuerto the nombrePuerto to set
	 */
	public void setNombrePuerto(String nombrePuerto) {
		this.nombrePuerto = nombrePuerto;
	}

	/**
	 * @return the codigoAgenciaPreferente
	 */
	public String getCodigoAgenciaPreferente() {
		return codigoAgenciaPreferente;
	}

	/**
	 * @param codigoAgenciaPreferente the codigoAgenciaPreferente to set
	 */
	public void setCodigoAgenciaPreferente(String codigoAgenciaPreferente) {
		this.codigoAgenciaPreferente = codigoAgenciaPreferente;
	}

	/**
	 * @return the nombreAgenciaPreferente
	 */
	public String getNombreAgenciaPreferente() {
		return nombreAgenciaPreferente;
	}

	/**
	 * @param nombreAgenciaPreferente the nombreAgenciaPreferente to set
	 */
	public void setNombreAgenciaPreferente(String nombreAgenciaPreferente) {
		this.nombreAgenciaPreferente = nombreAgenciaPreferente;
	}

	/**
	 * @return the agencias
	 */
	public List<AgenciaDTO> getAgencia() {
		return agencia;
	}

	/**
	 * @param agencias the agencias to set
	 */
	public void setAgencia(List<AgenciaDTO> agencia) {
		this.agencia = agencia;
	}

}
