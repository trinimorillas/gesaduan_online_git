package es.mercadona.gesaduan.business.cargas.deletecargas.v1;

import es.mercadona.gesaduan.dto.cargas.deletecargas.v1.InputDatosEliminarCargaDTO;
import es.mercadona.gesaduan.exception.GesaduanException;

public interface DeleteCargaService {
	public void eliminarCarga(InputDatosEliminarCargaDTO input) throws GesaduanException;
}
