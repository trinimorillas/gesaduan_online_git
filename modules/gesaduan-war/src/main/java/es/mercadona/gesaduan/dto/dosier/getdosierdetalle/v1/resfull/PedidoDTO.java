package es.mercadona.gesaduan.dto.dosier.getdosierdetalle.v1.resfull;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class PedidoDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;

	private String codigoPedido;
	private String fechaExpedicionPedido;
	private String origen;
	private String descripcionOrigen;

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

	/**
	 * @return the fechaExpedicionPedido
	 */
	public String getFechaExpedicionPedido() {
		return fechaExpedicionPedido;
	}

	/**
	 * @param fechaExpedicionPedido the fechaExpedicionPedido to set
	 */
	public void setFechaExpedicionPedido(String fechaExpedicionPedido) {
		this.fechaExpedicionPedido = fechaExpedicionPedido;
	}

	/**
	 * @return the origen
	 */
	public String getOrigen() {
		return origen;
	}

	/**
	 * @param origen the origen to set
	 */
	public void setOrigen(String origen) {
		this.origen = origen;
	}

	/**
	 * @return the descripcionOrigen
	 */
	public String getDescripcionOrigen() {
		return descripcionOrigen;
	}

	/**
	 * @param descripcionOrigen the descripcionOrigen to set
	 */
	public void setDescripcionOrigen(String descripcionOrigen) {
		this.descripcionOrigen = descripcionOrigen;
	}

}
