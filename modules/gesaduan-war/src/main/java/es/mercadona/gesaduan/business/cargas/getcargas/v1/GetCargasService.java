package es.mercadona.gesaduan.business.cargas.getcargas.v1;

import es.mercadona.fwk.core.web.BoPage;
import es.mercadona.gesaduan.dto.cargas.getcargas.v1.InputDatosCargasDTO;
import es.mercadona.gesaduan.dto.cargas.getcargas.v1.restfull.OutputCargasDTO;

public interface GetCargasService {
	public OutputCargasDTO listarCargas(InputDatosCargasDTO input, BoPage pagination);
}
