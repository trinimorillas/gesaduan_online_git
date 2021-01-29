package es.mercadona.gesaduan.dao.cargas.getpedidoscarga.v1;

import es.mercadona.gesaduan.dto.cargas.getpedidoscarga.v1.InputDatosPedidosCargaDTO;
import es.mercadona.gesaduan.dto.cargas.getpedidoscarga.v1.restfull.OutputPedidosCargaDTO;

public interface GetPedidosCargaDAO {
	
	public OutputPedidosCargaDTO listarPedidosCarga(InputDatosPedidosCargaDTO input);
	
}
