package es.mercadona.gesaduan.dto.cargas.deleterelacioncargapedido.v1;

import java.io.Serializable;
import java.util.List;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputEliminarRelacionCargaPedidoDTO extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String codigoCarga;
	private String codigoAlmacenOrigen;
	private List<PedidoEliminarRelacionCargaPedidoDTO> pedido;
	
	public String getCodigoCarga() {
		return codigoCarga;
	}
	
	public void setCodigoCarga(String codigoCarga) {
		this.codigoCarga = codigoCarga;
	}
	
	public String getCodigoAlmacenOrigen() {
		return codigoAlmacenOrigen;
	}
	
	public void setCodigoAlmacenOrigen(String codigoAlmacenOrigen) {
		this.codigoAlmacenOrigen = codigoAlmacenOrigen;
	}
	
	public List<PedidoEliminarRelacionCargaPedidoDTO> getPedido() {
		return pedido;
	}
	
	public void setPedido(List<PedidoEliminarRelacionCargaPedidoDTO> pedido) {
		this.pedido = pedido;
	}	

}
