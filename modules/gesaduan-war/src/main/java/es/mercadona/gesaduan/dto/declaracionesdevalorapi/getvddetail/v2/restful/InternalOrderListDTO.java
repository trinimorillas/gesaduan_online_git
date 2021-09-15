package es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvddetail.v2.restful;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class InternalOrderListDTO extends AbstractDTO {
	private static final long serialVersionUID = 1L;
	private String internalOrderId;

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
