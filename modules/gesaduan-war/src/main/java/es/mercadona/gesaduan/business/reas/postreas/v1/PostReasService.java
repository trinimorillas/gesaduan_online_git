package es.mercadona.gesaduan.business.reas.postreas.v1;

import es.mercadona.gesaduan.dto.reas.common.v1.restfull.OutputPostReasDTO;
import es.mercadona.gesaduan.dto.reas.postreas.v1.InputDatosPostReasDTO;

public interface PostReasService {
	
	public OutputPostReasDTO crearReas(InputDatosPostReasDTO input);

}
