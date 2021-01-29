package es.mercadona.gesaduan.dao.declaracionesdevalor.getdvsumario.v1;

import es.mercadona.fwk.core.web.BoPage;
import es.mercadona.gesaduan.dto.declaracionesdevalor.getdvsumario.v1.InputDeclaracionesDeValorDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.getdvsumario.v1.restfull.OutputDeclaracionesDeValorDTO;

public interface GetDVSumarioDAO {

	public OutputDeclaracionesDeValorDTO obtenerDeclaraciones(InputDeclaracionesDeValorDTO input, BoPage paginacion);
	
}
