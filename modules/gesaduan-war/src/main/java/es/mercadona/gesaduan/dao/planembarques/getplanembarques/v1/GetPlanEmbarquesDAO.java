package es.mercadona.gesaduan.dao.planembarques.getplanembarques.v1;

import es.mercadona.fwk.core.web.BoPage;
import es.mercadona.gesaduan.dto.planembarques.getplanembarques.v1.InputPlanEmbarquesDTO;
import es.mercadona.gesaduan.dto.planembarques.getplanembarques.v1.restfull.OutputPlanEmbarquesDTO;

public interface GetPlanEmbarquesDAO {
	
	public OutputPlanEmbarquesDTO listarPlanEmbarques(InputPlanEmbarquesDTO input, BoPage pagination);
	
}
