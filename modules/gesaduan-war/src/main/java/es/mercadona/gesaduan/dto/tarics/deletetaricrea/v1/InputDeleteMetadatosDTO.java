package es.mercadona.gesaduan.dto.tarics.deletetaricrea.v1;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputDeleteMetadatosDTO extends AbstractDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoUsuario;

	public String getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}
	
	
	
}
