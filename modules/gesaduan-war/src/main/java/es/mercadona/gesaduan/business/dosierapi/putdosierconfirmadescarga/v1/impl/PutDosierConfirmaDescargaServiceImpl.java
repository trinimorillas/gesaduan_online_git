package es.mercadona.gesaduan.business.dosierapi.putdosierconfirmadescarga.v1.impl;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.declaracionesdevalorapi.putfacturaconfirmadescarga.v1.PutFacturaConfirmaDescargaService;
import es.mercadona.gesaduan.business.dosierapi.putdosierconfirmadescarga.v1.PutDosierConfirmaDescargaService;
import es.mercadona.gesaduan.dao.declaracionesdevalorapi.putfacturaconfirmadescarga.v1.PutFacturaConfirmaDescargaDAO;
import es.mercadona.gesaduan.dao.dosierapi.putdosierconfirmadescarga.v1.PutDosierConfirmaDescargaDAO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.putfacturaconfirmadescarga.v1.InputPutFacturaConfirmaDescargaDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.putfacturaconfirmadescarga.v1.restfull.OutputPutFacturaConfirmaDescargaDTO;
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
