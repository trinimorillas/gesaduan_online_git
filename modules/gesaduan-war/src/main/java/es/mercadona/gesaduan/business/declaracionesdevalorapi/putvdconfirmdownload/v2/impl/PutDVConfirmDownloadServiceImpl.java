package es.mercadona.gesaduan.business.declaracionesdevalorapi.putvdconfirmdownload.v2.impl;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.declaracionesdevalorapi.putvdconfirmdownload.v2.PutDVConfirmDownloadService;
import es.mercadona.gesaduan.dao.declaracionesdevalorapi.putvdconfirmdownload.v2.PutDVConfirmDownloadDAO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.putvdconfirmdownload.v2.InputDataDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.putvdconfirmdownload.v2.restfull.OutputPutVDConfirmDownloadDTO;

public class PutDVConfirmDownloadServiceImpl implements PutDVConfirmDownloadService {
	
	@Inject
	private PutDVConfirmDownloadDAO putFacturaConfirmaDescargaDao;

	@Override
	public OutputPutVDConfirmDownloadDTO updateEstadoDescarga(InputDataDTO input) {
		return putFacturaConfirmaDescargaDao.actualizarEstadoDescarga(input);
	}
}
