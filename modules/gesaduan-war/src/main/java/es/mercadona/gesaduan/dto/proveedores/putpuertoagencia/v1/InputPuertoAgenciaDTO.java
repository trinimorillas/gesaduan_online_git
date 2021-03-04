package es.mercadona.gesaduan.dto.proveedores.putpuertoagencia.v1;

import java.io.Serializable;
import java.util.List;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputPuertoAgenciaDTO extends AbstractDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codigoAgencia;
	private String operacion;
	private List<PuertoDTO> puertos;

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
	 * @return the operacion
	 */
	public String getOperacion() {
		return operacion;
	}

	/**
	 * @param operacion the operacion to set
	 */
	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	/**
	 * @return the puertos
	 */
	public List<PuertoDTO> getPuertos() {
		return puertos;
	}

	/**
	 * @param puertos the puertos to set
	 */
	public void setPuertos(List<PuertoDTO> puertos) {
		this.puertos = puertos;
	}

}
