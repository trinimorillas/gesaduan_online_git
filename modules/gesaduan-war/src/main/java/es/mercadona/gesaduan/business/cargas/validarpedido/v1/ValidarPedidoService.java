package es.mercadona.gesaduan.business.cargas.validarpedido.v1;

import es.mercadona.gesaduan.dto.cargas.validapedido.v1.InputDatosValidarPedidoDTO;
import es.mercadona.gesaduan.exception.GesaduanException;

public interface ValidarPedidoService {
		public void validarPedido(InputDatosValidarPedidoDTO input) throws GesaduanException;
}
