package es.mercadona.gesaduan.business.cargas.putrelacioncargapedido.v1.impl;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.cargas.putrelacioncargapedido.v1.PutRelacionCargaPedidoService;
import es.mercadona.gesaduan.dao.cargas.putrelacioncargapedido.v1.PutRelacionCargaPedidoDAO;
import es.mercadona.gesaduan.dto.cargas.putrelacioncargapedido.v1.InputDatosCrearRelacionCargaPedidoDTO;
import es.mercadona.gesaduan.dto.cargas.putrelacioncargapedido.v1.PedidoDTO;
import es.mercadona.gesaduan.exception.EnumGesaduanException;
import es.mercadona.gesaduan.exception.GesaduanException;
import es.mercadona.gesaduan.jpa.cargas.v1.CargaPedidoJPA;
import es.mercadona.gesaduan.jpa.cargas.v1.CargaPedidoPkJPA;

public class PutRelacionCargaPedidoServiceImpl implements PutRelacionCargaPedidoService {

	@Inject
	private PutRelacionCargaPedidoDAO putRelacionCargaPedidoDao;
	
	@Override
	public void crearRelacionCargaPedido(InputDatosCrearRelacionCargaPedidoDTO datos) throws GesaduanException {
		if (putRelacionCargaPedidoDao.validarEstadoCarga(datos) == 0) {
			for (PedidoDTO pedido : datos.getDatos().getPedido()) {
				if (putRelacionCargaPedidoDao.validarExistePedido(datos,pedido) == 0) {
					CargaPedidoPkJPA pkCargaPedido = new CargaPedidoPkJPA();
					CargaPedidoJPA cargaPedido = new CargaPedidoJPA();
					pkCargaPedido.setCodigoCarga(datos.getDatos().getCodigoCarga());
					pkCargaPedido.setCodigoCentroOrigen(datos.getDatos().getCodigoAlmacenOrigen());
					pkCargaPedido.setCodigoPedido(pedido.getCodigoPedido());
					pkCargaPedido.setCodigoDivisionPedido(pedido.getCodigoDivision());					
					cargaPedido.setId(pkCargaPedido);
					cargaPedido.setUsuarioCreacion(datos.getMetadatos().getCodigoUsuario());
					putRelacionCargaPedidoDao.crearRelacionCargaPedido(cargaPedido);
				} else {
					throw new GesaduanException(EnumGesaduanException.PEDIDO_YA_EXISTE);
				}
			}
		} else throw new GesaduanException(EnumGesaduanException.ESTADO_CARGA_NO_PENDIENTE);
	}

}
