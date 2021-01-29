package es.mercadona.gesaduan.business.alertas.crearalertas.v1.impl;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.alertas.crearalertas.v1.CrearAlertasService;
import es.mercadona.gesaduan.business.alertas.getalertas.v1.GetAlertasService;
import es.mercadona.gesaduan.dao.alertas.crearalertas.v1.CrearAlertasDAO;

public class CrearAlertasServiceImpl implements CrearAlertasService{
	
	@Inject
	private CrearAlertasDAO crearAlertasDao;
	
	@Inject
	private GetAlertasService getAlertasService;

	@Override
	public void crearAlerta(Integer codigoAlerta, String codigoElemento, String codigoUsuario) {
		
		boolean existeAlerta = getAlertasService.checkNotificacionExiste(codigoElemento);
		
		if(!existeAlerta) {
			crearAlertasDao.crearAlertas(codigoAlerta, codigoElemento, codigoUsuario);
		}
	}

}
