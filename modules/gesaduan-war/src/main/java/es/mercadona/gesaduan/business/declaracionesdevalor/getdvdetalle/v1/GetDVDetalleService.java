package es.mercadona.gesaduan.business.declaracionesdevalor.getdvdetalle.v1;

import es.mercadona.fwk.core.web.BoPage;
import es.mercadona.gesaduan.dto.declaracionesdevalor.getdvdetalle.v1.InputDeclaracionesDeValorDetalleDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.getdvdetalle.v1.restfull.OutputDeclaracionesDeValorDetalleDTO;

public interface GetDVDetalleService {
	
	public OutputDeclaracionesDeValorDetalleDTO getDeclaracionesDeValorPorCodigoList(InputDeclaracionesDeValorDetalleDTO bodata,
			BoPage paginacion);

}
