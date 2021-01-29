package es.mercadona.gesaduan.dao.planembarques.getplanembarquedetalle.v1;

import es.mercadona.gesaduan.dto.planembarques.getplanembarquedetalle.v1.InputDatosDetalleDTO;
import es.mercadona.gesaduan.dto.planembarques.getplanembarquedetalle.v1.restfull.OutputPlanEmbarqueDetalleDTO;

public interface GetPlanEmbarqueDetalleDAO {
	
	public OutputPlanEmbarqueDetalleDTO consultarPlanEmbarque(InputDatosDetalleDTO input);
	
}
