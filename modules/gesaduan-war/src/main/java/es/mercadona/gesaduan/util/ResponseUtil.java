package es.mercadona.gesaduan.util;

import es.mercadona.gesaduan.dto.common.error.ErrorDTO;
import es.mercadona.gesaduan.dto.common.error.OutputResponseErrorDTO;

public final class ResponseUtil {
	
	
	public static OutputResponseErrorDTO getError(Exception ex, String codigo, String descripcion) {
		OutputResponseErrorDTO error = new OutputResponseErrorDTO();
		ErrorDTO errorDesc = new ErrorDTO();

		errorDesc.setCodigo(codigo);
		errorDesc.setDescripcion(descripcion);

		error.setError(errorDesc);

		return error;
	}

}
