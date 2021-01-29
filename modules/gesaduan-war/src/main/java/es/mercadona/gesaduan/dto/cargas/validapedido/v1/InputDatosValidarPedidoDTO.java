package es.mercadona.gesaduan.dto.cargas.validapedido.v1;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputDatosValidarPedidoDTO extends AbstractDTO implements Serializable {

	
	private static final long serialVersionUID = 1L;	
	
	InputValidarPedidoDTO datos;
	InputMetadatosValidarPedidoDTO metadatos;
	
	public InputValidarPedidoDTO getDatos() {
		return datos;
	}
	
	public void setDatos(InputValidarPedidoDTO datos) {
		this.datos = datos;
	}

	public InputMetadatosValidarPedidoDTO getMetadatos() {
		return metadatos;
	}

	public void setMetadatos(InputMetadatosValidarPedidoDTO metadatos) {
		this.metadatos = metadatos;
	}
	
}
