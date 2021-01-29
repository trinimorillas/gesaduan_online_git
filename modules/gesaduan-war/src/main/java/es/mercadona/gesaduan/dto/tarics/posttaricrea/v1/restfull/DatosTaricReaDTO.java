package es.mercadona.gesaduan.dto.tarics.posttaricrea.v1.restfull;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class DatosTaricReaDTO extends AbstractDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoTaric;
	private String codigoRea;
	
	public String getCodigoTaric() {
		return codigoTaric;
	}
	public void setCodigoTaric(String codigoTaric) {
		this.codigoTaric = codigoTaric;
	}
	public String getCodigoRea() {
		return codigoRea;
	}
	public void setCodigoRea(String codigoRea) {
		this.codigoRea = codigoRea;
	}
	
	

}
