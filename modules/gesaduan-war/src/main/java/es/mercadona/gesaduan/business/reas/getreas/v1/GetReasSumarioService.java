package es.mercadona.gesaduan.business.reas.getreas.v1;

import es.mercadona.fwk.core.web.BoPage;
import es.mercadona.gesaduan.dto.reas.getreas.v1.InputReasDTO;
import es.mercadona.gesaduan.dto.reas.getreas.v1.restfull.OutputReasDTO;

public interface GetReasSumarioService {
	
	public OutputReasDTO getReasSumario(InputReasDTO input, BoPage pagination);

}
