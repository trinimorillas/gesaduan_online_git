package es.mercadona.gesaduan.business.declaracionesdevalor.putdvestadodescarga.v1.impl;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.declaracionesdevalor.putdvestadodescarga.v1.PutDVEstadoDescargaService;
import es.mercadona.gesaduan.dao.declaracionesdevalor.putdvestadodescarga.v1.PutDVEstadoDescargaDAO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.putdvinddescarga.v1.DeclaracionesDeValorEstadoDescargaServiceDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.putdvinddescarga.v1.restfull.OutputDeclaracionesDeValorEstadoDescargaDTO;

public class PutDVEstadoDescargaServiceImpl implements PutDVEstadoDescargaService {
	
	@Inject
	private PutDVEstadoDescargaDAO DeclaracionesDeValorEstadoDescargaDao;

	@Override
	public OutputDeclaracionesDeValorEstadoDescargaDTO updateEstadoDescarga(DeclaracionesDeValorEstadoDescargaServiceDTO input) {

		OutputDeclaracionesDeValorEstadoDescargaDTO result = this.DeclaracionesDeValorEstadoDescargaDao.actualizarEstadoDescarga(input);
		
		return result;
		
	}

	

}
