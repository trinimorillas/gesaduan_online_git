package es.mercadona.gesaduan.dto.expediciones.postCargarExpedicion.v1;

import es.mercadona.gesaduan.jpa.expediciones.v1.postexpediciones.ExpedicionesCabeceraPK;

public final class ExpedicionResponsePKsDTO {

	private final ExpedicionesCabeceraPK pkExp;
    private final Boolean crearDV;
    
	public ExpedicionesCabeceraPK getPkExp() {
		return pkExp;
	}
	public Boolean getCrearDV() {
		return crearDV;
	}
	
	public ExpedicionResponsePKsDTO(ExpedicionesCabeceraPK pkExp, Boolean crearDV) {
		super();
		this.pkExp = pkExp;
		this.crearDV = crearDV;
	}
	
	@Override
	public String toString() {
		return "ExpedicionResponsePKsDTO [pkExp=" + pkExp + ", crearDV=" + crearDV + "]";
	}
	    
	
}
