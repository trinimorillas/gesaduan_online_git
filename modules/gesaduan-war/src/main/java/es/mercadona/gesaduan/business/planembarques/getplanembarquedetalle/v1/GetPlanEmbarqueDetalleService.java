package es.mercadona.gesaduan.business.planembarques.getplanembarquedetalle.v1;

import es.mercadona.gesaduan.dto.planembarques.getplanembarquedetalle.v1.InputDatosDetalleDTO;
import es.mercadona.gesaduan.dto.planembarques.getplanembarquedetalle.v1.restfull.OutputPlanEmbarqueDetalleDTO;

public interface GetPlanEmbarqueDetalleService {
	
	public OutputPlanEmbarqueDetalleDTO getPlanEmbarqueDetalle(InputDatosDetalleDTO input);
	
}
