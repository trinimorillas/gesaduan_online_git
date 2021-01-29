package es.mercadona.gesaduan.business.equipotransporte.cambiarestado.v1;

import es.mercadona.gesaduan.dto.equipotransporte.cambiarestado.v1.InputDatosCambiarEstadoDTO;
import es.mercadona.gesaduan.dto.equipotransporte.cambiarestado.v1.restfull.OutputCambiarEstadoDTO;

public interface PutCambiarEstadoService {
	public OutputCambiarEstadoDTO cambiarEstado(InputDatosCambiarEstadoDTO input);
}