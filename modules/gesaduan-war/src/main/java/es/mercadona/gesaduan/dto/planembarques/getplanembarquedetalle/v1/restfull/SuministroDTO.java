package es.mercadona.gesaduan.dto.planembarques.getplanembarquedetalle.v1.restfull;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class SuministroDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
    	
    private Long codigoSuministro;
	private String nombreSuministro;
	
	public Long getCodigoSuministro() {
		return codigoSuministro;
	}
	
	public void setCodigoSuministro(Long codigoSuministro) {
		this.codigoSuministro = codigoSuministro;
	}
	
	public String getNombreSuministro() {
		return nombreSuministro;
	}
	
	public void setNombreSuministro(String nombreSuministro) {
		this.nombreSuministro = nombreSuministro;
	}	
	
}
