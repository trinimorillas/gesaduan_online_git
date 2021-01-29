package es.mercadona.gesaduan.dto.cargas.getpedidoscarga.v1;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputDatosPedidosCargaDTO extends AbstractDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;	
	
	InputPedidosCargaDTO datos;
	
	public InputPedidosCargaDTO getDatos() {
		return datos;
	}
	
	public void setDatos(InputPedidosCargaDTO datos) {
		this.datos = datos;
	}
	
}