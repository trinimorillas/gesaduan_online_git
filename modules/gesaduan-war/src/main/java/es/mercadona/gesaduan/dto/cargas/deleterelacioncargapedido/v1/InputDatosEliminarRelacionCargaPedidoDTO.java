package es.mercadona.gesaduan.dto.cargas.deleterelacioncargapedido.v1;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputDatosEliminarRelacionCargaPedidoDTO extends AbstractDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;	
	
	InputEliminarRelacionCargaPedidoDTO datos;
	InputMetadatosEliminarRelacionCargaPedidoDTO metadatos;
	
	public InputEliminarRelacionCargaPedidoDTO getDatos() {
		return datos;
	}
	
	public void setDatos(InputEliminarRelacionCargaPedidoDTO datos) {
		this.datos = datos;
	}

	public InputMetadatosEliminarRelacionCargaPedidoDTO getMetadatos() {
		return metadatos;
	}

	public void setMetadatos(InputMetadatosEliminarRelacionCargaPedidoDTO metadatos) {
		this.metadatos = metadatos;
	}
	
}