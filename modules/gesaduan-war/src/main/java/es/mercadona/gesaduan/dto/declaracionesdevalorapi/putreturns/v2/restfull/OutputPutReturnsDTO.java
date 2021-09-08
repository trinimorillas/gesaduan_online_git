package es.mercadona.gesaduan.dto.declaracionesdevalorapi.putreturns.v2.restfull;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class OutputPutReturnsDTO extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private OutputDataDTO datos;

	/**
	 * @return the datos
	 */
	public OutputDataDTO getDatos() {
		return datos;
	}

	/**
	 * @param datos the datos to set
	 */
	public void setDatos(OutputDataDTO datos) {
		this.datos = datos;
	}

}