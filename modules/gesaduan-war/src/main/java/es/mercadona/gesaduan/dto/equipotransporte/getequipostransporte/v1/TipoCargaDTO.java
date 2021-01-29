package es.mercadona.gesaduan.dto.equipotransporte.getequipostransporte.v1;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class TipoCargaDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
	
	private Long codigoTipoCarga;
	private String nombreTipoCarga;
	
	public Long getCodigoTipoCarga() {
		return codigoTipoCarga;
	}
	
	public void setCodigoTipoCarga(Long codigoTipoCarga) {
		this.codigoTipoCarga = codigoTipoCarga;
	}
	
	public String getNombreTipoCarga() {
		return nombreTipoCarga;
	}
	
	public void setNombreTipoCarga(String nombreTipoCarga) {
		this.nombreTipoCarga = nombreTipoCarga;
	}	

}
