package es.mercadona.gesaduan.dto.cargas.validapedido.v1;

import java.io.Serializable;
import java.util.List;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputValidarPedidoDTO extends AbstractDTO implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private List<ValidarPedidoDTO> carga;
	
	public List<ValidarPedidoDTO> getCarga() {
		return carga;
	}
	
	public void setCarga(List<ValidarPedidoDTO> carga) {
		this.carga = carga;
	}
	
}
