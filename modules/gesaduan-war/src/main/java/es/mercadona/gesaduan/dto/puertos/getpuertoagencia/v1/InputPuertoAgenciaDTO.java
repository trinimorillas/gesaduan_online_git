package es.mercadona.gesaduan.dto.puertos.getpuertoagencia.v1;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputPuertoAgenciaDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
	
	private Long codigoPuerto;
	private String codigoAgencia;
	private String orden;
	
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
	 * @return the orden
	 */
	public String getOrden() {
		return orden;
	}

	/**
	 * @param orden the orden to set
	 */
	public void setOrden(String orden) {
		this.orden = orden;
	}

}
