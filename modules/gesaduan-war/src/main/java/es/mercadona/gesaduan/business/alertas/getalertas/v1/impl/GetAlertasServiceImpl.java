package es.mercadona.gesaduan.business.alertas.getalertas.v1.impl;

import javax.inject.Inject;

import es.mercadona.fwk.core.web.BoPage;
import es.mercadona.gesaduan.business.alertas.getalertas.v1.GetAlertasService;
import es.mercadona.gesaduan.dao.alertas.getalertas.v1.GetAlertasDAO;
import es.mercadona.gesaduan.dto.alertas.getalertas.v1.InputAlertasDTO;
import es.mercadona.gesaduan.dto.alertas.getalertas.v1.restfull.OutputAlertasDTO;

public class GetAlertasServiceImpl implements GetAlertasService{

	@Inject
	private GetAlertasDAO getAlertasDao;
	
	
	@Override
	public OutputAlertasDTO getAlertas(InputAlertasDTO input, BoPage paginacion) {
		return getAlertasDao.getAlertas(input, paginacion);
	}


	@Override
	public boolean checkNotificacionExiste(String codigoElemento) {
		return getAlertasDao.checkNotificacionExiste(codigoElemento);
	}
	
	

}
