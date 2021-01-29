package es.mercadona.gesaduan.business.cargas.deleterelacioncargapedido.v1.impl;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.cargas.deleterelacioncargapedido.v1.DeleteRelacionCargaPedidoService;
import es.mercadona.gesaduan.dao.cargas.deleterelacioncargapedido.v1.DeleteRelacionCargaPedidoDAO;
import es.mercadona.gesaduan.dto.cargas.deleterelacioncargapedido.v1.InputDatosEliminarRelacionCargaPedidoDTO;
import es.mercadona.gesaduan.dto.cargas.deleterelacioncargapedido.v1.PedidoEliminarRelacionCargaPedidoDTO;
import es.mercadona.gesaduan.exception.EnumGesaduanException;
import es.mercadona.gesaduan.exception.GesaduanException;
import es.mercadona.gesaduan.jpa.cargas.v1.CargaPedidoJPA;
import es.mercadona.gesaduan.jpa.cargas.v1.CargaPedidoPkJPA;

public class DeleteRelacionCargaPedidoServiceImpl implements DeleteRelacionCargaPedidoService {

	@Inject
	private DeleteRelacionCargaPedidoDAO deleteRelacionCargaPedidoDao;
	
	@Override
	public void eliminarRelacionCargaPedido(InputDatosEliminarRelacionCargaPedidoDTO datos) throws GesaduanException {
		if (deleteRelacionCargaPedidoDao.validarEstadoCarga(datos) == 0) {
			for (PedidoEliminarRelacionCargaPedidoDTO pedido : datos.getDatos().getPedido()) {
				CargaPedidoPkJPA pkCargaPedido = new CargaPedidoPkJPA();
				CargaPedidoJPA cargaPedido = new CargaPedidoJPA();
				pkCargaPedido.setCodigoCarga(datos.getDatos().getCodigoCarga());
				pkCargaPedido.setCodigoCentroOrigen(datos.getDatos().getCodigoAlmacenOrigen());
				pkCargaPedido.setCodigoPedido(pedido.getCodigoPedido());
				pkCargaPedido.setCodigoDivisionPedido(pedido.getCodigoDivision());				
				cargaPedido.setId(pkCargaPedido);
				deleteRelacionCargaPedidoDao.eliminarRelacionCargaPedido(cargaPedido);
			}
			deleteRelacionCargaPedidoDao.modificarPedidosSinValidar(datos);
		} else throw new GesaduanException(EnumGesaduanException.ESTADO_CARGA_NO_PENDIENTE);
	}

}
