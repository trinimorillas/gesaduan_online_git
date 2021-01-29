package es.mercadona.gesaduan.dto.cargas.getpedidoscarga.v1;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputPedidosCargaDTO extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String codigoCarga;
	private String codigoCentroOrigen;
	private String mcaPedidoValidado;
	private String orden;
	
	public String getCodigoCarga() {
		return codigoCarga;
	}
	
	public void setCodigoCarga(String codigoCarga) {
		this.codigoCarga = codigoCarga;
	}
	
	public String getCodigoCentroOrigen() {
		return codigoCentroOrigen;
	}
	
	public void setCodigoCentroOrigen(String codigoCentroOrigen) {
		this.codigoCentroOrigen = codigoCentroOrigen;
	}
	
	public String getMcaPedidoValidado() {
		return mcaPedidoValidado;
	}

	public void setMcaPedidoValidado(String mcaPedidoValidado) {
		this.mcaPedidoValidado = mcaPedidoValidado;
	}

	public String getOrden() {
		return orden;
	}
	
	public void setOrden(String orden) {
		this.orden = orden;
	}	

}
