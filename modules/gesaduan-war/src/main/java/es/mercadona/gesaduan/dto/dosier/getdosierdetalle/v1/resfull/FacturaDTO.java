package es.mercadona.gesaduan.dto.dosier.getdosierdetalle.v1.resfull;

import java.util.List;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class FacturaDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
	private Long codigoFactura;
	private Integer anyoFactura;
	private String fechaExpedicion;
	private String codigoOrigen;
	private String nombreOrigen;
	private List<PedidoDTO> pedido;
	private List<OrigenDTO> origen;

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
	 * @return the fechaExpedicion
	 */
	public String getFechaExpedicion() {
		return fechaExpedicion;
	}

	/**
	 * @param fechaExpedicion the fechaExpedicion to set
	 */
	public void setFechaExpedicion(String fechaExpedicion) {
		this.fechaExpedicion = fechaExpedicion;
	}

	/**
	 * @return the origen
	 */
	public List<OrigenDTO> getOrigen() {
		return origen;
	}

	/**
	 * @param origen the origen to set
	 */
	public void setOrigen(List<OrigenDTO> origen) {
		this.origen = origen;
	}

	/**
	 * @return the codigoOrigen
	 */
	public String getCodigoOrigen() {
		return codigoOrigen;
	}

	/**
	 * @param codigoOrigen the codigoOrigen to set
	 */
	public void setCodigoOrigen(String codigoOrigen) {
		this.codigoOrigen = codigoOrigen;
	}

	/**
	 * @return the nombreOrigen
	 */
	public String getNombreOrigen() {
		return nombreOrigen;
	}

	/**
	 * @param nombreOrigen the nombreOrigen to set
	 */
	public void setNombreOrigen(String nombreOrigen) {
		this.nombreOrigen = nombreOrigen;
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
