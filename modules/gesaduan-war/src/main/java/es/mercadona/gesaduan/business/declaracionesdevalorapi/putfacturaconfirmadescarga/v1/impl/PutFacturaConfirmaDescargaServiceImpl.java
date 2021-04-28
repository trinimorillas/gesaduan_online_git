package es.mercadona.gesaduan.business.declaracionesdevalorapi.putfacturaconfirmadescarga.v1.impl;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.declaracionesdevalorapi.putfacturaconfirmadescarga.v1.PutFacturaConfirmaDescargaService;
import es.mercadona.gesaduan.dao.declaracionesdevalorapi.putfacturaconfirmadescarga.v1.PutFacturaConfirmaDescargaDAO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.putfacturaconfirmadescarga.v1.InputPutFacturaConfirmaDescargaDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.putfacturaconfirmadescarga.v1.restfull.OutputPutFacturaConfirmaDescargaDTO;

public class PutFacturaConfirmaDescargaServiceImpl implements PutFacturaConfirmaDescargaService {
	
	@Inject
	private PutFacturaConfirmaDescargaDAO putFacturaConfirmaDescargaDao;

	@Override
	public OutputPutFacturaConfirmaDescargaDTO updateEstadoDescarga(InputPutFacturaConfirmaDescargaDTO input) {
		return putFacturaConfirmaDescargaDao.actualizarEstadoDescarga(input);
	}
}
