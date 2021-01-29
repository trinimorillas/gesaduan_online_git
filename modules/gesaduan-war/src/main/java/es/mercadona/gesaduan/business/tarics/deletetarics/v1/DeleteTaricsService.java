package es.mercadona.gesaduan.business.tarics.deletetarics.v1;

import es.mercadona.gesaduan.dto.tarics.common.v1.restfull.OutputTaricsDTO;
import es.mercadona.gesaduan.dto.tarics.deletetarics.v1.InputTaricDeleteDTO;


public interface DeleteTaricsService {

	public OutputTaricsDTO deleteTarics(InputTaricDeleteDTO input);
}
