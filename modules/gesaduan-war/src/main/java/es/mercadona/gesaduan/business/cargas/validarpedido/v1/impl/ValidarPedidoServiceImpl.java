package es.mercadona.gesaduan.business.cargas.validarpedido.v1.impl;

import java.util.List;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.cargas.validarpedido.v1.ValidarPedidoService;
import es.mercadona.gesaduan.dao.cargas.validarpedido.v1.ValidarPedidoDAO;
import es.mercadona.gesaduan.dto.cargas.validapedido.v1.InputDatosValidarPedidoDTO;
import es.mercadona.gesaduan.dto.cargas.validapedido.v1.ValidarPedidoDTO;
import es.mercadona.gesaduan.exception.EnumGesaduanException;
import es.mercadona.gesaduan.exception.GesaduanException;

public class ValidarPedidoServiceImpl implements ValidarPedidoService {

	@Inject
	private ValidarPedidoDAO validarPedidoDAO;
		
	@Override
	public void validarPedido(InputDatosValidarPedidoDTO datos) throws GesaduanException {
		List<ValidarPedidoDTO> cargas = datos.getDatos().getCarga();
		boolean error = false;
		String errorStr = "";
		for (ValidarPedidoDTO carga : cargas) {
			validarPedidoDAO.marcarPedidosValidados(datos,carga);
			validarPedidoDAO.borrarPedidosMarcadosBorrar(carga);
			if (validarPedidoDAO.existenPedidosCarga(carga)) {
				validarPedidoDAO.actualizarPedidosValidadosCarga(datos,carga);
			} else {
				error = true;	
				errorStr += carga.getCodigoAlmacenOrigen() + " - " + carga.getCodigoCarga() + ", ";
			}
		}
		if (error) {
			EnumGesaduanException errorText = EnumGesaduanException.CARGA_NO_VALIDADA_NO_TIENE_PEDIDOS;
			String descripcion = errorText.getDescripcion();
			String descripcionAlt = descripcion.replace("$CARGAS", errorStr.substring(0,errorStr.length()-2));
			throw new GesaduanException(EnumGesaduanException.CARGA_NO_VALIDADA_NO_TIENE_PEDIDOS,descripcionAlt);
		}
	}
}
	

