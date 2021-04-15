package es.mercadona.gesaduan.dto.dosier.getdosierdetalle.v1.resfull;

import java.util.List;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class FacturaDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
	private Long codigoFactura;
	private Integer anyoFactura;
	private String fechaEmbarque;
	private String codigoOrigen;
	private String nombreOrigen;
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
	 * @return the fechaEmbarque
	 */
	public String getFechaEmbarque() {
		return fechaEmbarque;
	}

	/**
	 * @param fechaEmbarque the fechaEmbarque to set
	 */
	public void setFechaEmbarque(String fechaEmbarque) {
		this.fechaEmbarque = fechaEmbarque;
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
