package es.mercadona.gesaduan.dto.tipocarga.gettipocarga.v1;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class DatosTipoCargaDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
	
	private Integer codigoTipoCarga;
	private String nombreTipoCarga;
	
	public Integer getCodigoTipoCarga() {
		return codigoTipoCarga;
	}
	
	public void setCodigoTipoCarga(Integer codigoTipoCarga) {
		this.codigoTipoCarga = codigoTipoCarga;
	}
	
	public String getNombreTipoCarga() {
		return nombreTipoCarga;
	}
	
	public void setNombreTipoCarga(String nombreTipoCarga) {
		this.nombreTipoCarga = nombreTipoCarga;
	}	

}