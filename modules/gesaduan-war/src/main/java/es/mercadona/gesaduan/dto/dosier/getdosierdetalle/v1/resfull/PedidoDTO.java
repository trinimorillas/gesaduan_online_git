package es.mercadona.gesaduan.dto.dosier.getdosierdetalle.v1.resfull;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class PedidoDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;

	private String codigoPedido;

	/**
	 * @return the codigoPedido
	 */
	public String getCodigoPedido() {
		return codigoPedido;
	}

	/**
	 * @param codigoPedido the codigoPedido to set
	 */
	public void setCodigoPedido(String codigoPedido) {
		this.codigoPedido = codigoPedido;
	}
}
