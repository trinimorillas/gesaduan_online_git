package es.mercadona.gesaduan.business.equipotransporte.putcontenedor.v1.impl;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.equipotransporte.putcontenedor.v1.PutContenedorService;
import es.mercadona.gesaduan.dao.equipotransporte.putcontenedor.v1.PutContenedorDAO;
import es.mercadona.gesaduan.dto.equipotransporte.getequipotransportedetalle.v1.restfull.ContenedorDTO;
import es.mercadona.gesaduan.dto.equipotransporte.putcontenedor.v1.InputDatosContenedorDTO;
import es.mercadona.gesaduan.exception.GesaduanException;

public class PutContenedorServiceImpl implements PutContenedorService {

	@Inject
	private PutContenedorDAO actualizarContenedorDao;
	
	@Override
	public void actualizarContenedor(InputDatosContenedorDTO datos) throws GesaduanException {
		for (ContenedorDTO contenedor : datos.getDatos().getContenedor()) {
			actualizarContenedorDao.actualizarContenedor(contenedor, datos.getDatos().getCodigoEquipo(), datos.getMetadatos().getCodigoUsuario());
		}
	}
}
