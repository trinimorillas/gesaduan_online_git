package es.mercadona.gesaduan.business.cargas.cambiarestado.v1.impl;

import java.util.List;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.cargas.cambiarestado.v1.CambiarEstadoCargaService;
import es.mercadona.gesaduan.dao.cargas.cambiarestado.v1.CambiarEstadoCargaDAO;
import es.mercadona.gesaduan.dto.cargas.cambiarestado.v1.CambiarEstadoCargaDTO;
import es.mercadona.gesaduan.dto.cargas.cambiarestado.v1.InputDatosCambiarEstadoCargaDTO;
import es.mercadona.gesaduan.exception.EnumGesaduanException;
import es.mercadona.gesaduan.exception.GesaduanException;

public class CambiarEstadoCargaServiceImpl implements CambiarEstadoCargaService {

	@Inject
	private CambiarEstadoCargaDAO cambiarEstadoCargaDao;
	
	@Override
	public void cambiarEstadoCarga(InputDatosCambiarEstadoCargaDTO datos) throws GesaduanException {
		List<CambiarEstadoCargaDTO> cargas = datos.getDatos().getCarga();
		for (CambiarEstadoCargaDTO carga : cargas) {
			carga.setCodigoEstadoActual(cambiarEstadoCargaDao.getEstadoActual(carga));
			if ((carga.getCodigoEstadoActual() == 1 && (carga.getCodigoEstado() != 2 && carga.getCodigoEstado() != 5)) ||
				(carga.getCodigoEstadoActual() == 2 && carga.getCodigoEstado() != 5) ||
				(carga.getCodigoEstadoActual() == 5 && carga.getCodigoEstado() != 2))
				throw new GesaduanException(EnumGesaduanException.CAMBIAR_ESTADO_CARGA);
			if (carga.getCodigoEstado() == 2 && cambiarEstadoCargaDao.comprobarPedidosAsociados(carga) == 0)
				throw new GesaduanException(EnumGesaduanException.PEDIDOS_NO_ASOCIADOS);
			
			cambiarEstadoCargaDao.cambiarEstado(datos, carga);
			if (carga.getCodigoEstado() == 2) cambiarEstadoCargaDao.validarPedidosAsociados(datos, carga);
		}
	}

}