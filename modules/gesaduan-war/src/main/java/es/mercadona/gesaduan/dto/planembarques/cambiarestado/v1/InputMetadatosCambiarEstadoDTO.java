package es.mercadona.gesaduan.dto.planembarques.cambiarestado.v1;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputMetadatosCambiarEstadoDTO extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String codigoUsuario;
	
	public String getCodigoUsuario() {
		return codigoUsuario;
	}
	
	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

}
