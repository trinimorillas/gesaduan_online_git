package es.mercadona.gesaduan.business.tarics.deletetaricrea.v1;

import es.mercadona.gesaduan.dto.tarics.deletetaricrea.v1.InputDeleteTaricReaDTO;
import es.mercadona.gesaduan.dto.tarics.deletetaricrea.v1.restfull.OutputDeleteTaricReaDTO;

public interface DeleteTaricReaService {

	public OutputDeleteTaricReaDTO eliminarRelacion(InputDeleteTaricReaDTO input);
	
}
