package es.mercadona.gesaduan.dto.planembarques.putplanembarque.v1.restfull;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class DatosPlanEmbarquePutDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
	
	private Long codigoEmbarque;
	
	public Long getCodigoEmbarque() {
		return codigoEmbarque;
	}
	
	public void setCodigoEmbarque(Long codigoEmbarque) {
		this.codigoEmbarque = codigoEmbarque;
	}

}