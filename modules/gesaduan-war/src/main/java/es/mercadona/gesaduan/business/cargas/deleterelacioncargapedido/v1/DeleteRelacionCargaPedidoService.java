package es.mercadona.gesaduan.business.cargas.deleterelacioncargapedido.v1;

import es.mercadona.gesaduan.dto.cargas.deleterelacioncargapedido.v1.InputDatosEliminarRelacionCargaPedidoDTO;
import es.mercadona.gesaduan.exception.GesaduanException;

public interface DeleteRelacionCargaPedidoService {
	public void eliminarRelacionCargaPedido(InputDatosEliminarRelacionCargaPedidoDTO input) throws GesaduanException;
}
