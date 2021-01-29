package es.mercadona.gesaduan.dao.tarics.gettarics.v1;

import es.mercadona.fwk.core.web.BoPage;
import es.mercadona.gesaduan.dto.tarics.common.v1.restfull.OutputTaricsDTO;
import es.mercadona.gesaduan.dto.tarics.gettarics.v1.restful.InputTaricsDTO;

public interface GetTaricsSumarioDAO {
	
	public OutputTaricsDTO getTaricsSumario(InputTaricsDTO input, BoPage pagination);
	
	public boolean checkExistTaricAlerta(Long codigoTaric);
	
	public boolean checkExisteRelacionTaricProd(Long codigoTaric);
	

}
