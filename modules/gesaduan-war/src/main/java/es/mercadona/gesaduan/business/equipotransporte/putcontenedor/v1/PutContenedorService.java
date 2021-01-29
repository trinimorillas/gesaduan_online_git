package es.mercadona.gesaduan.business.equipotransporte.putcontenedor.v1;

import es.mercadona.gesaduan.dto.equipotransporte.putcontenedor.v1.InputDatosContenedorDTO;
import es.mercadona.gesaduan.exception.GesaduanException;

public interface PutContenedorService {
	public void actualizarContenedor(InputDatosContenedorDTO datos) throws GesaduanException;
}