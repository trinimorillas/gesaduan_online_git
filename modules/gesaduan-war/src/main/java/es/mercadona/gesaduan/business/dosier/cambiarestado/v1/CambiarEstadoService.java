package es.mercadona.gesaduan.business.dosier.cambiarestado.v1;

import es.mercadona.gesaduan.dto.dosier.cambiarestado.v1.InputDatosCambiarEstadoDTO;
import es.mercadona.gesaduan.dto.dosier.cambiarestado.v1.resfull.OutputCambiarEstadoDTO;
import es.mercadona.gesaduan.exception.GesaduanException;

public interface CambiarEstadoService {
	
	public OutputCambiarEstadoDTO cambiarEstado(InputDatosCambiarEstadoDTO input) throws GesaduanException;
	
}