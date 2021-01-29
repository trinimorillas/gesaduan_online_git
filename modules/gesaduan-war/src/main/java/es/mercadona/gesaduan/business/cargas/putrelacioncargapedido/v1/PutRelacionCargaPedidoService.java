package es.mercadona.gesaduan.business.cargas.putrelacioncargapedido.v1;

import es.mercadona.gesaduan.dto.cargas.putrelacioncargapedido.v1.InputDatosCrearRelacionCargaPedidoDTO;
import es.mercadona.gesaduan.exception.GesaduanException;

public interface PutRelacionCargaPedidoService {
	public void crearRelacionCargaPedido(InputDatosCrearRelacionCargaPedidoDTO input) throws GesaduanException;
}
