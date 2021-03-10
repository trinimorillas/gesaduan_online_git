package es.mercadona.gesaduan.dao.alertas.putalertas.v1.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import es.mercadona.fwk.auth.SecurityService;
import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.fwk.core.exceptions.ExceptionUtils;
import es.mercadona.gesaduan.dao.alertas.putalertas.v1.PutAlertasDAO;
import es.mercadona.gesaduan.dto.alertas.putalertas.v1.InputPutAlertasDTO;
import es.mercadona.gesaduan.jpa.alertas.v1.NotificacionAlertaExpedicionJPA;
import es.mercadona.gesaduan.jpa.alertas.v1.NotificacionAlertaExpedicionPkJPA;
import es.mercadona.gesaduan.jpa.alertas.v1.NotificacionAlertaJPA;
import es.mercadona.gesaduan.jpa.alertas.v1.NotificacionAlertaPkJPA;

public class PutAlertasDAOImpl implements PutAlertasDAO{

	
	@Inject
	private org.slf4j.Logger logger;
	
	@Inject
	private SecurityService securityService;
	
	@PersistenceContext
	private EntityManager entityM;
	
	@Transactional
	@Override
	public void editarAlertasExpedicion(InputPutAlertasDTO input) {
		
		try {
			NotificacionAlertaExpedicionPkJPA id = new NotificacionAlertaExpedicionPkJPA();
			id.setCodigoAlerta(Long.parseLong(input.getDatos().getAlerta()));
			id.setCodigoElemento(input.getDatos().getElemento());
			id.setCodigoExpedicion(input.getDatos().getExpedicion());
			
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			Date fechaAlbaran = new Date();
			try {
				fechaAlbaran = format.parse(input.getDatos().getFechaAlbaran());
			} catch (ParseException e) {
				this.logger.error("({}-{}) ERROR - {} {}","PutAlertasDAOImpl(GESADUAN)","editarAlertasExpedicion",e.getClass().getSimpleName(),e.getMessage());
			}
			
			id.setFechaAlbaran(fechaAlbaran);
			
			NotificacionAlertaExpedicionJPA alertaExped = entityM.find(NotificacionAlertaExpedicionJPA.class, id);
			
			Date fechaHoy = new Date(); 
			if(input.getDatos().getEstaResuelta()) {
				alertaExped.setMarcaResuelto("S");
			}else {
				alertaExped.setMarcaResuelto("N");
			}
			alertaExped.setUsuarioModificacion(input.getMetadatos().getCodigoUsuario().toUpperCase());
			alertaExped.setFechaModificacion(fechaHoy);
		}catch(Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","PutAlertasDAOImpl(GESADUAN)","editarAlertasExpedicion",e.getClass().getSimpleName(),e.getMessage());
			establecerSalidaError(e, "editarAlertasExpedicion", "error.ioexception");
			throw new ApplicationException(e.getMessage());
		}
		

	}
	
	@Transactional
	@Override
	public void editarAlertas(InputPutAlertasDTO input) {
		
		try {
			NotificacionAlertaPkJPA id = new NotificacionAlertaPkJPA();
			id.setCodigoAlerta(Integer.parseInt(input.getDatos().getAlerta()));
			id.setCodigoElemento(input.getDatos().getElemento());
			
			NotificacionAlertaJPA alerta = entityM.find(NotificacionAlertaJPA.class, id);
			
			Date fechaHoy = new Date();
			if(input.getDatos().getEstaResuelta()) {
				alerta.setMarcaResuelto("S");
			}else {
				alerta.setMarcaResuelto("N");
			}
			alerta.setUsuarioModificacion(input.getMetadatos().getCodigoUsuario().toUpperCase());
			alerta.setFechaModificacion(fechaHoy);
		}catch(Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","PutAlertasDAOImpl(GESADUAN)","editarAlertas",e.getClass().getSimpleName(),e.getMessage());
			establecerSalidaError(e, "editarAlertas", "error.ioexception");
			throw new ApplicationException(e.getMessage());
		}
		

	}
	
	  private void establecerSalidaError(Exception exception, String metodo, String codError) {

		    String login = this.securityService.getPrincipal().getLogin();
		    
		    this.logger.error("Error ejecutando la clase: PutAlertasDAOImpl",
		        new Object[] { metodo, login, ExceptionUtils.getStackTrace(exception) });
	  }

}
