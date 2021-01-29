package es.mercadona.gesaduan.dto.cargas.deleterelacioncargapedido.v1;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class PedidoEliminarRelacionCargaPedidoDTO extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;	
	private String codigoPedido;
	private String codigoDivision;	
	
	public String getCodigoPedido() {
		return codigoPedido;
	}
	
	public void setCodigoPedido(String codigoPedido) {
		this.codigoPedido = codigoPedido;
	}

	public String getCodigoDivision() {
		return codigoDivision;
	}

	public void setCodigoDivision(String codigoDivision) {
		this.codigoDivision = codigoDivision;
	}
	
	

}
