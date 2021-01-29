package es.mercadona.gesaduan.dao.reas.postreas.v1;

import es.mercadona.gesaduan.dto.reas.common.v1.restfull.OutputPostReasDTO;
import es.mercadona.gesaduan.jpa.reas.v1.ReasPostJPA;

public interface PostReasDAO {
	
	public OutputPostReasDTO crearReas(ReasPostJPA input);

}
