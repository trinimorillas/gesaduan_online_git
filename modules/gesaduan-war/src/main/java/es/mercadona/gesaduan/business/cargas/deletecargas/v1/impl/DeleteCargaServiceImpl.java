package es.mercadona.gesaduan.business.cargas.deletecargas.v1.impl;

import java.util.List;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.cargas.deletecargas.v1.DeleteCargaService;
import es.mercadona.gesaduan.dao.cargas.deletecargas.v1.DeleteCargaDAO;
import es.mercadona.gesaduan.dto.cargas.deletecargas.v1.EliminarCargaDTO;
import es.mercadona.gesaduan.dto.cargas.deletecargas.v1.InputDatosEliminarCargaDTO;
import es.mercadona.gesaduan.exception.EnumGesaduanException;
import es.mercadona.gesaduan.exception.GesaduanException;

public class DeleteCargaServiceImpl implements DeleteCargaService {

	@Inject
	private DeleteCargaDAO eliminarCargaDao;
	
	@Override
	public void eliminarCarga(InputDatosEliminarCargaDTO datos) throws GesaduanException {
		List<EliminarCargaDTO> cargas = datos.getDatos().getCarga();
		int numCargasNoPendientes = 0;
		for (EliminarCargaDTO carga : cargas) {
			if (eliminarCargaDao.validarEstadoCarga(carga) != 0) numCargasNoPendientes++;
		}
		if (numCargasNoPendientes > 0) throw new GesaduanException(EnumGesaduanException.ESTADO_CARGA_NO_PENDIENTE);
		for (EliminarCargaDTO carga : cargas) {
			eliminarCargaDao.eliminarPedidosCarga(carga);			
			eliminarCargaDao.eliminarCarga(carga);
			eliminarCargaDao.cambiarEstado(datos, carga);
		}
	}

}
