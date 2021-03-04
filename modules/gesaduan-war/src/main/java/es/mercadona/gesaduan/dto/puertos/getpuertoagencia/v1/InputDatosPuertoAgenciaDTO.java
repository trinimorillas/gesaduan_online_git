package es.mercadona.gesaduan.dto.puertos.getpuertoagencia.v1;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputDatosPuertoAgenciaDTO extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;	
	private Long codigoPuerto;
	private Long codigoAgencia;
	
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
	public Long getCodigoAgencia() {
		return codigoAgencia;
	}
	
	/**
	 * @param codigoAgencia the codigoAgencia to set
	 */
	public void setCodigoAgencia(Long codigoAgencia) {
		this.codigoAgencia = codigoAgencia;
	}	

}
