package es.mercadona.gesaduan.dao.declaracionesdevalor.putdvestadodescarga.v1;

import javax.ejb.Local;

import es.mercadona.gesaduan.dto.declaracionesdevalor.putdvinddescarga.v1.DeclaracionesDeValorEstadoDescargaServiceDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.putdvinddescarga.v1.restfull.OutputDeclaracionesDeValorEstadoDescargaDTO;

@Local
public interface PutDVEstadoDescargaDAO {
		
		public OutputDeclaracionesDeValorEstadoDescargaDTO actualizarEstadoDescarga(DeclaracionesDeValorEstadoDescargaServiceDTO input);

		
}
