package es.mercadona.gesaduan.dto.cargas.validapedido.v1;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class ValidarPedidoDTO  extends AbstractDTO implements Serializable {

	
	private static final long serialVersionUID = 1L;	
	private String codigoCarga;
	private String codigoAlmacenOrigen;
	
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
	
	
}
