package es.mercadona.gesaduan.dao.planembarques.getplanembarques.v1;

import es.mercadona.fwk.core.web.BoPage;
import es.mercadona.gesaduan.dto.planembarques.getplanembarques.v1.InputDatosGetPlanEmbarqueDTO;
import es.mercadona.gesaduan.dto.planembarques.getplanembarques.v1.restfull.OutputPlanEmbarquesDTO;

public interface GetPlanEmbarquesDAO {
	
	public OutputPlanEmbarquesDTO listarPlanEmbarques(InputDatosGetPlanEmbarqueDTO input, BoPage pagination);
	
}
