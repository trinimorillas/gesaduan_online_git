package es.mercadona.gesaduan.dto.tarics.putreemplazartaric.v1.restful;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class ReemplazarTaricsDatosDTO extends AbstractDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    	
    private String codigoTaricOrigen;
    private String codigoTaricDestino;
    
	
	public String getCodigoTaricOrigen() {
		return codigoTaricOrigen;
	}
	public void setCodigoTaricOrigen(String codigoTaricOrigen) {
		this.codigoTaricOrigen = codigoTaricOrigen;
	}
	public String getCodigoTaricDestino() {
		return codigoTaricDestino;
	}
	public void setCodigoTaricDestino(String codigoTaricDestino) {
		this.codigoTaricDestino = codigoTaricDestino;
	}
    
    

}
