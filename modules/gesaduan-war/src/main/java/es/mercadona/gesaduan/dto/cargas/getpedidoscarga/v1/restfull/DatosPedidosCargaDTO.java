package es.mercadona.gesaduan.dto.cargas.getpedidoscarga.v1.restfull;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class DatosPedidosCargaDTO extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String codigoPedido;
	private String divisionPedido;
	private String mcaPedidoValidado;
	private String fechaCreacion;
	
	public String getCodigoPedido() {
		return codigoPedido;
	}
	
	public void setCodigoPedido(String codigoPedido) {
		this.codigoPedido = codigoPedido;
	}
	
	public String getDivisionPedido() {
		return divisionPedido;
	}
	
	public void setDivisionPedido(String divisionPedido) {
		this.divisionPedido = divisionPedido;
	}
	
	public String getMcaPedidoValidado() {
		return mcaPedidoValidado;
	}
	
	public void setMcaPedidoValidado(String mcaPedidoValidado) {
		this.mcaPedidoValidado = mcaPedidoValidado;
	}
	
	public String getFechaCreacion() {
		return fechaCreacion;
	}
	
	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
}
