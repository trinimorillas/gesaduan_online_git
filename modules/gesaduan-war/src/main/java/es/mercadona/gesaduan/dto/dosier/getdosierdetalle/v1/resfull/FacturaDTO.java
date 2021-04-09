package es.mercadona.gesaduan.dto.dosier.getdosierdetalle.v1.resfull;

import java.util.List;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class FacturaDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
	private Long codigoFactura;
	private Integer anyoFactura;
	private List<PedidoDTO> pedido;

	/**
	 * @return the codigoFactura
	 */
	public Long getCodigoFactura() {
		return codigoFactura;
	}

	/**
	 * @param codigoFactura the codigoFactura to set
	 */
	public void setCodigoFactura(Long codigoFactura) {
		this.codigoFactura = codigoFactura;
	}

	/**
	 * @return the anyoFactura
	 */
	public Integer getAnyoFactura() {
		return anyoFactura;
	}

	/**
	 * @param anyoFactura the anyoFactura to set
	 */
	public void setAnyoFactura(Integer anyoFactura) {
		this.anyoFactura = anyoFactura;
	}

	/**
	 * @return the pedido
	 */
	public List<PedidoDTO> getPedido() {
		return pedido;
	}

	/**
	 * @param pedido the pedido to set
	 */
	public void setPedido(List<PedidoDTO> pedido) {
		this.pedido = pedido;
	}

}
