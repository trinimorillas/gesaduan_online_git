package es.mercadona.gesaduan.business.declaracionesdevalor.getdvsumario.v1;

import es.mercadona.fwk.core.web.BoPage;
import es.mercadona.gesaduan.dto.declaracionesdevalor.getdvsumario.v1.InputDeclaracionesDeValorDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.getdvsumario.v1.restfull.OutputDeclaracionesDeValorDTO;


public interface GetDVSumarioService {

	public OutputDeclaracionesDeValorDTO getDeclaracionesDeValorList(InputDeclaracionesDeValorDTO bodata,
			BoPage paginacion);


}
