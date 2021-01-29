package es.mercadona.gesaduan.dto.cargas.putrelacioncargapedido.v1;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputDatosCrearRelacionCargaPedidoDTO extends AbstractDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;	
	
	InputCrearRelacionCargaPedidoDTO datos;
	InputMetadatosCrearRelacionCargaPedidoDTO metadatos;
	
	public InputCrearRelacionCargaPedidoDTO getDatos() {
		return datos;
	}
	
	public void setDatos(InputCrearRelacionCargaPedidoDTO datos) {
		this.datos = datos;
	}

	public InputMetadatosCrearRelacionCargaPedidoDTO getMetadatos() {
		return metadatos;
	}

	public void setMetadatos(InputMetadatosCrearRelacionCargaPedidoDTO metadatos) {
		this.metadatos = metadatos;
	}
	
}