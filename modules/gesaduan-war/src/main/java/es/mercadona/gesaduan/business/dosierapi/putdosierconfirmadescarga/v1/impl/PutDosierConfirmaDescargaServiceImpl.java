package es.mercadona.gesaduan.business.dosierapi.putdosierconfirmadescarga.v1.impl;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.dosierapi.putdosierconfirmadescarga.v1.PutDosierConfirmaDescargaService;
import es.mercadona.gesaduan.dao.dosierapi.putdosierconfirmadescarga.v1.PutDosierConfirmaDescargaDAO;
import es.mercadona.gesaduan.dto.dosierapi.putdosierconfirmadescarga.v1.InputPutDosierConfirmaDescargaDTO;
import es.mercadona.gesaduan.dto.dosierapi.putdosierconfirmadescarga.v1.restfull.OutputPutDosierConfirmaDescargaDTO;

public class PutDosierConfirmaDescargaServiceImpl implements PutDosierConfirmaDescargaService {
	
	@Inject
	private PutDosierConfirmaDescargaDAO putDosierConfirmaDescargaDao;

	@Override
	public OutputPutDosierConfirmaDescargaDTO updateEstadoDescarga(InputPutDosierConfirmaDescargaDTO input) {
		return putDosierConfirmaDescargaDao.actualizarEstadoDescarga(input);
	}
}
