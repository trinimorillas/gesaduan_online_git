package es.mercadona.gesaduan.dto.equipotransporte.getcontenedores.v1.restfull;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class PedidoDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;

	private String codigoPedido;

	public String getCodigoPedido() {
		return codigoPedido;
	}

	public void setCodigoPedido(String codigoPedido) {
		this.codigoPedido = codigoPedido;
	}

}
