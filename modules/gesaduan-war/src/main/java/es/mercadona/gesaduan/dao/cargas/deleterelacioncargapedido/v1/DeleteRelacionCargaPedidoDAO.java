package es.mercadona.gesaduan.dao.cargas.deleterelacioncargapedido.v1;

import es.mercadona.gesaduan.dto.cargas.deleterelacioncargapedido.v1.InputDatosEliminarRelacionCargaPedidoDTO;
import es.mercadona.gesaduan.jpa.cargas.v1.CargaPedidoJPA;

public interface DeleteRelacionCargaPedidoDAO {
	public void eliminarRelacionCargaPedido(CargaPedidoJPA cargaPedido);
	public Integer validarEstadoCarga(InputDatosEliminarRelacionCargaPedidoDTO datos);
	public void modificarPedidosSinValidar(InputDatosEliminarRelacionCargaPedidoDTO datos);
}
