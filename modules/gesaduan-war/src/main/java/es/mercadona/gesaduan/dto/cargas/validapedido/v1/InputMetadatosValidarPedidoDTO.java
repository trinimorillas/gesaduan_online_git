package es.mercadona.gesaduan.dto.cargas.validapedido.v1;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.cargas.cambiarestado.v1.InputCambiarEstadoCargaDTO;
import es.mercadona.gesaduan.dto.cargas.cambiarestado.v1.InputMetadatosCambiarEstadoCargaDTO;
import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputMetadatosValidarPedidoDTO extends AbstractDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String codigoUsuario;
	
	public String getCodigoUsuario() {
		return codigoUsuario;
	}
	
	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}
	
}
