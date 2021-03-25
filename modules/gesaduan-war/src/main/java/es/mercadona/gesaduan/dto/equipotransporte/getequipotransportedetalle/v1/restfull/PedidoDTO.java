package es.mercadona.gesaduan.dto.equipotransporte.getequipotransportedetalle.v1.restfull;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class PedidoDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;

	private String codigoPedido;
	private String codigoDivision;

	public String getCodigoPedido() {
		return codigoPedido;
	}

	public void setCodigoPedido(String codigoPedido) {
		this.codigoPedido = codigoPedido;
	}

	/**
	 * @return the codigoDivision
	 */
	public String getCodigoDivision() {
		return codigoDivision;
	}

	/**
	 * @param codigoDivision the codigoDivision to set
	 */
	public void setCodigoDivision(String codigoDivision) {
		this.codigoDivision = codigoDivision;
	}

}
