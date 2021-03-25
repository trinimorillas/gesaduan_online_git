package es.mercadona.gesaduan.business.tarics.gettarics.v1;

import es.mercadona.fwk.core.web.BoPage;
import es.mercadona.gesaduan.dto.tarics.common.v1.restfull.OutputTaricsDTO;
import es.mercadona.gesaduan.dto.tarics.gettarics.v1.restful.InputTaricsDTO;

public interface GetTaricsSumarioService {	
	public OutputTaricsDTO getSumarioTarics(InputTaricsDTO input, BoPage pagination);	
	public boolean checkExisteRelacionTaricProd(Long codigoTaric);
}
