package es.mercadona.gesaduan.business.tarics.puttarics.v1;

import es.mercadona.gesaduan.dto.tarics.common.v1.restfull.OutputTaricsDTO;
import es.mercadona.gesaduan.dto.tarics.puttarics.v1.restful.InputDatosPutDTO;

public interface PutTaricsService {

	public OutputTaricsDTO updateTarics(InputDatosPutDTO input);
}
