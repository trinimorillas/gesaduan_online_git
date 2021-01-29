package es.mercadona.gesaduan.business.reas.deletereas.v1;

import es.mercadona.gesaduan.dto.reas.common.v1.restfull.OutputPostReasDTO;
import es.mercadona.gesaduan.dto.reas.deletereas.v1.InputDeleteReasDTO;

public interface DeleteReasService {
	
	public OutputPostReasDTO deleteReas(InputDeleteReasDTO input);

}
