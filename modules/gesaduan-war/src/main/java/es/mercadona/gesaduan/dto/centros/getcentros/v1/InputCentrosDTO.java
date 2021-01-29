package es.mercadona.gesaduan.dto.centros.getcentros.v1;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputCentrosDTO extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;	
	private String codigoCentro;
	
	public String getCodigoCentro() {
		return codigoCentro;
	}
	
	public void setCodigoCentro(String codigoCentro) {
		this.codigoCentro = codigoCentro;
	}

}