package es.mercadona.gesaduan.business.reas.getreasdetalle.v1;

import es.mercadona.gesaduan.dto.reas.getreasdetalle.v1.InputReasDetalleDTO;
import es.mercadona.gesaduan.dto.reas.getreasdetalle.v1.restfull.OutputReasDetalleDTO;

public interface GetReasDetalleService {
	
	public OutputReasDetalleDTO getReasDetalle(InputReasDetalleDTO input);
	
	public boolean checkExistRea(InputReasDetalleDTO input);

}
