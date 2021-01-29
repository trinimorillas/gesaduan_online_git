package es.mercadona.gesaduan.business.cargas.getpedidoscarga.v1;

import es.mercadona.gesaduan.dto.cargas.getpedidoscarga.v1.InputDatosPedidosCargaDTO;
import es.mercadona.gesaduan.dto.cargas.getpedidoscarga.v1.restfull.OutputPedidosCargaDTO;

public interface GetPedidosCargaService {
	public OutputPedidosCargaDTO listarPedidosCarga(InputDatosPedidosCargaDTO input);
}
