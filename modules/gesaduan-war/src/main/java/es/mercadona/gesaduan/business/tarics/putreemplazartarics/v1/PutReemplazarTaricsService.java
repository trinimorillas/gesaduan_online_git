package es.mercadona.gesaduan.business.tarics.putreemplazartarics.v1;

import es.mercadona.gesaduan.dto.tarics.putreemplazartaric.v1.restful.InputReemplazarTaricsDTO;
import es.mercadona.gesaduan.dto.tarics.putreemplazartaric.v1.restful.OutputReemplazarTaricsDTO;


public interface PutReemplazarTaricsService {
	public OutputReemplazarTaricsDTO reemplazarTarics(InputReemplazarTaricsDTO input);
}
