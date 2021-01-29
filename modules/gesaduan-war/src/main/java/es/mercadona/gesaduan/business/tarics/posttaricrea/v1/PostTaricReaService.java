package es.mercadona.gesaduan.business.tarics.posttaricrea.v1;

import es.mercadona.gesaduan.dto.tarics.posttaricrea.v1.InputTaricReaDTO;
import es.mercadona.gesaduan.dto.tarics.posttaricrea.v1.restfull.OutputTaricReaDTO;

public interface PostTaricReaService {
	
	public OutputTaricReaDTO crearRelacion(InputTaricReaDTO input);

}
