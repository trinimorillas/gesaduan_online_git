package es.mercadona.gesaduan.business.planembarques.getplanembarques.v1;

import es.mercadona.fwk.core.web.BoPage;
import es.mercadona.gesaduan.dto.planembarques.getplanembarques.v1.InputPlanEmbarquesDTO;
import es.mercadona.gesaduan.dto.planembarques.getplanembarques.v1.restfull.OutputPlanEmbarquesDTO;

public interface GetPlanEmbarquesService {
	public OutputPlanEmbarquesDTO listarPlanEmbarques(InputPlanEmbarquesDTO input, BoPage pagination);
}
