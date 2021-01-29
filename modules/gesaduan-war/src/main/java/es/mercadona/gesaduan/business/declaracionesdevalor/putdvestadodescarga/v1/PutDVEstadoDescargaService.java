package es.mercadona.gesaduan.business.declaracionesdevalor.putdvestadodescarga.v1;

import es.mercadona.gesaduan.dto.declaracionesdevalor.putdvinddescarga.v1.DeclaracionesDeValorEstadoDescargaServiceDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.putdvinddescarga.v1.restfull.OutputDeclaracionesDeValorEstadoDescargaDTO;

public interface PutDVEstadoDescargaService {

	public OutputDeclaracionesDeValorEstadoDescargaDTO updateEstadoDescarga(DeclaracionesDeValorEstadoDescargaServiceDTO input);
	

	
}
