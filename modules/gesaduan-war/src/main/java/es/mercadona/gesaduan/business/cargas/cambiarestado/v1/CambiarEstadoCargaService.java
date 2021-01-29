package es.mercadona.gesaduan.business.cargas.cambiarestado.v1;

import es.mercadona.gesaduan.dto.cargas.cambiarestado.v1.InputDatosCambiarEstadoCargaDTO;
import es.mercadona.gesaduan.exception.GesaduanException;

public interface CambiarEstadoCargaService {
	public void cambiarEstadoCarga(InputDatosCambiarEstadoCargaDTO input) throws GesaduanException;
}
