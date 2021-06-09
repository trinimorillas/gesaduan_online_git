package es.mercadona.gesaduan.business.declaracionesdevalorapi.putvdconfirmdownload.v1.impl;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.declaracionesdevalorapi.putvdconfirmdownload.v1.PutVDConfirmDownloadService;
import es.mercadona.gesaduan.dao.declaracionesdevalorapi.putvdconfirmdownload.v1.PutVDConfirmDownloadDAO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.putvdconfirmdownload.v1.InputDataDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.putvdconfirmdownload.v1.restfull.OutputPutVDConfirmDownloadDTO;

public class PutVDConfirmDownloadServiceImpl implements PutVDConfirmDownloadService {
	
	@Inject
	private PutVDConfirmDownloadDAO putFacturaConfirmaDescargaDao;

	@Override
	public OutputPutVDConfirmDownloadDTO updateEstadoDescarga(InputDataDTO input) {
		return putFacturaConfirmaDescargaDao.actualizarEstadoDescarga(input);
	}
}
