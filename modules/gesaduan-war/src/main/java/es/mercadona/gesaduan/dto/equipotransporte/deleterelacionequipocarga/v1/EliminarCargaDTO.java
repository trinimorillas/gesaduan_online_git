package es.mercadona.gesaduan.dto.equipotransporte.deleterelacionequipocarga.v1;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class EliminarCargaDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
    	
    private String codigoCarga;
    private String codigoCentroOrigen;
	
	public String getCodigoCentroOrigen() {
		return codigoCentroOrigen;
	}

	public void setCodigoCentroOrigen(String codigoCentroOrigen) {
		this.codigoCentroOrigen = codigoCentroOrigen;
	}

	public String getCodigoCarga() {
		return codigoCarga;
	}
	
	public void setCodigoCarga(String codigoCarga) {
		this.codigoCarga = codigoCarga;
	}
	
}
