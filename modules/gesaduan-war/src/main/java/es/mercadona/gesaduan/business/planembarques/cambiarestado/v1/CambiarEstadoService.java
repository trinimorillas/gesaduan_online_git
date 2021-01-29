package es.mercadona.gesaduan.business.planembarques.cambiarestado.v1;

import es.mercadona.gesaduan.dto.planembarques.cambiarestado.v1.InputDatosCambiarEstadoDTO;
import es.mercadona.gesaduan.dto.planembarques.cambiarestado.v1.restfull.OutputCambiarEstadoDTO;
import es.mercadona.gesaduan.exception.GesaduanException;

public interface CambiarEstadoService {
	
	public OutputCambiarEstadoDTO cambiarEstado(InputDatosCambiarEstadoDTO input) throws GesaduanException;
	
}