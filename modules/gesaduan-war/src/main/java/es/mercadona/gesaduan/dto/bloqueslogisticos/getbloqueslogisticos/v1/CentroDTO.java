package es.mercadona.gesaduan.dto.bloqueslogisticos.getbloqueslogisticos.v1;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class CentroDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
    	
    private String codigoCentro;

	public String getCodigoCentro() {
		return codigoCentro;
	}

	public void setCodigoCentro(String codigoCentro) {
		this.codigoCentro = codigoCentro;
	}
	
}
