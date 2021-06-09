package es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvdsumary.v1.resfull;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class DataVDSumaryOrderDTO extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String  internalOrderId;

	/**
	 * @return the internalOrderId
	 */
	public String getInternalOrderId() {
		return internalOrderId;
	}

	/**
	 * @param internalOrderId the internalOrderId to set
	 */
	public void setInternalOrderId(String internalOrderId) {
		this.internalOrderId = internalOrderId;
	}

	
}
