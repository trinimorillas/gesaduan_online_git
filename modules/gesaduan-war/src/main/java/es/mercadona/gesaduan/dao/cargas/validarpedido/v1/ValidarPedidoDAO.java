package es.mercadona.gesaduan.dao.cargas.validarpedido.v1;

import es.mercadona.gesaduan.dto.cargas.validapedido.v1.InputDatosValidarPedidoDTO;
import es.mercadona.gesaduan.dto.cargas.validapedido.v1.ValidarPedidoDTO;

public interface ValidarPedidoDAO {

	public void marcarPedidosValidados(InputDatosValidarPedidoDTO datos,ValidarPedidoDTO carga);
	public void borrarPedidosMarcadosBorrar(ValidarPedidoDTO carga);
	public boolean existenPedidosCarga(ValidarPedidoDTO carga);
	public void actualizarPedidosValidadosCarga(InputDatosValidarPedidoDTO datos,ValidarPedidoDTO carga);
	
}
