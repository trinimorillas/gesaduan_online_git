package es.mercadona.gesaduan.dto.planembarques.deleteplanembarque.v1;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputPlanEmbarqueDeleteDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
	
	private Long codigoEmbarque;
	
	public Long getCodigoEmbarque() {
		return codigoEmbarque;
	}
	
	public void setCodigoEmbarque(Long codigoEmbarque) {
		this.codigoEmbarque = codigoEmbarque;
	}

}
