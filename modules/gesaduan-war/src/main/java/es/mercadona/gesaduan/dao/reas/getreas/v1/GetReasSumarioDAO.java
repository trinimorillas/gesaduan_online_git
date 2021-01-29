package es.mercadona.gesaduan.dao.reas.getreas.v1;

import es.mercadona.fwk.core.web.BoPage;
import es.mercadona.gesaduan.dto.reas.getreas.v1.InputReasDTO;
import es.mercadona.gesaduan.dto.reas.getreas.v1.restfull.OutputReasDTO;

public interface GetReasSumarioDAO {
	
	public OutputReasDTO getReasSumario(InputReasDTO input, BoPage pagination);
	
	public boolean checkReaVariosTaric(String codigoRea);

}
