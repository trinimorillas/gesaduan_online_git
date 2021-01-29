package es.mercadona.gesaduan.dto.tarics.deletetaricrea.v1;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputPutDatosDTO extends AbstractDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Boolean finVigencia;

	public Boolean getFinVigencia() {
		return finVigencia;
	}

	public void setFinVigencia(Boolean finVigencia) {
		this.finVigencia = finVigencia;
	}
	
	

}
