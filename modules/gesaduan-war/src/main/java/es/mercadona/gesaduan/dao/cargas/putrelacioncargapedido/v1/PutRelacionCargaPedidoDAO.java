package es.mercadona.gesaduan.dao.cargas.putrelacioncargapedido.v1;

import es.mercadona.gesaduan.dto.cargas.putrelacioncargapedido.v1.InputDatosCrearRelacionCargaPedidoDTO;
import es.mercadona.gesaduan.dto.cargas.putrelacioncargapedido.v1.PedidoDTO;
import es.mercadona.gesaduan.jpa.cargas.v1.CargaPedidoJPA;

public interface PutRelacionCargaPedidoDAO {
	public void crearRelacionCargaPedido(CargaPedidoJPA crearRelacionCargaPedido);
	public Integer validarEstadoCarga(InputDatosCrearRelacionCargaPedidoDTO datos);
	public Integer validarExistePedido(InputDatosCrearRelacionCargaPedidoDTO datos,PedidoDTO pedido);	
}
