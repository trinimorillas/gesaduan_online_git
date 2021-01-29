package es.mercadona.gesaduan.dao.centros.getcentros.v1;

import es.mercadona.fwk.core.web.BoPage;
import es.mercadona.gesaduan.dto.centros.getcentros.v1.InputCentrosDTO;
import es.mercadona.gesaduan.dto.centros.getcentros.v1.restfull.OutputCentrosDTO;

public interface GetCentrosDAO {
	
	public OutputCentrosDTO listarCentros(InputCentrosDTO input, BoPage pagination);
	
}
