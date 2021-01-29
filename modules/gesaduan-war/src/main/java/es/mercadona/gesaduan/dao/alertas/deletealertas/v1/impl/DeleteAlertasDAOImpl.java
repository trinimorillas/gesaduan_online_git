package es.mercadona.gesaduan.dao.alertas.deletealertas.v1.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import es.mercadona.fwk.auth.SecurityService;
import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.fwk.core.exceptions.ExceptionUtils;
import es.mercadona.gesaduan.dao.alertas.deletealertas.v1.DeleteAlertasDAO;
import es.mercadona.gesaduan.dto.alertas.deletealertas.v1.InputDeleteAlertasDTO;
import es.mercadona.gesaduan.jpa.alertas.v1.NotificacionAlertaExpedicionJPA;
import es.mercadona.gesaduan.jpa.alertas.v1.NotificacionAlertaExpedicionPkJPA;
import es.mercadona.gesaduan.jpa.alertas.v1.NotificacionAlertaJPA;
import es.mercadona.gesaduan.jpa.alertas.v1.NotificacionAlertaPkJPA;

public class DeleteAlertasDAOImpl implements DeleteAlertasDAO{

	@Inject
	private org.slf4j.Logger logger;
	
	@Inject
	private SecurityService securityService;
	
	@PersistenceContext
	private EntityManager entityM;
	
	@Transactional
	@Override
	public void eliminarAlertasExpedicion(InputDeleteAlertasDTO input) {
		
		Long alerta = Long.parseLong(input.getAlerta());
		String elemento = input.getElemento();
		String expedicion = input.getExpedicion();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Date fechaAlbaran = new Date();
		try {
			fechaAlbaran = format.parse(input.getFechaAlbaran());
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		try {
			
			Query queryContacto = entityM.createNativeQuery("DELETE FROM S_USUARIO_NOTIF_ALERTA_EXPED WHERE COD_N_ALERTA = ?alerta AND COD_V_ELEMENTO = ?elemento AND COD_V_EXPEDICION = ?expedicion AND FEC_D_ALBARAN = ?fechaAlbaran");
			queryContacto.setParameter("alerta", alerta).setParameter("elemento", elemento).setParameter("expedicion", expedicion).setParameter("fechaAlbaran", fechaAlbaran).executeUpdate();
			
			Query queryUsuario = entityM.createNativeQuery("DELETE FROM S_CONTACTO_NOTIF_ALERTA_EXPED WHERE COD_N_ALERTA = ?alerta AND COD_V_ELEMENTO = ?elemento AND COD_V_EXPEDICION = ?expedicion AND FEC_D_ALBARAN = ?fechaAlbaran");
			queryUsuario.setParameter("alerta", alerta).setParameter("elemento", elemento).setParameter("expedicion", expedicion).setParameter("fechaAlbaran", fechaAlbaran).executeUpdate();
			
			NotificacionAlertaExpedicionPkJPA id = new NotificacionAlertaExpedicionPkJPA();
			id.setCodigoAlerta(Long.parseLong(input.getAlerta()));
			id.setCodigoElemento(input.getElemento());
			id.setCodigoExpedicion(input.getExpedicion());
			id.setFechaAlbaran(fechaAlbaran);
			
			NotificacionAlertaExpedicionJPA alertaExpedicion = entityM.find(NotificacionAlertaExpedicionJPA.class, id);
			entityM.remove(alertaExpedicion);
			
		}catch(Exception e) {
			establecerSalidaError(e, "eliminarAlertasExpedicion", "error.ioexception");
			throw new ApplicationException(e.getMessage());
		}
		
	}
	
	@Transactional
	@Override
	public void eliminarAlertas(InputDeleteAlertasDTO input) {
		
		Long alerta = Long.parseLong(input.getAlerta());
		String elemento = input.getElemento();

		try {
			Query query = entityM.createNativeQuery("DELETE FROM S_USUARIO_NOTIFICACION_ALERTA WHERE COD_N_ALERTA = ?alerta AND COD_V_ELEMENTO = ?elemento");
			query.setParameter("alerta", alerta).setParameter("elemento", elemento).executeUpdate();
			
			NotificacionAlertaPkJPA id = new NotificacionAlertaPkJPA();
			id.setCodigoAlerta(Integer.parseInt(input.getAlerta()));
			id.setCodigoElemento(input.getElemento());
			
			NotificacionAlertaJPA alertaEliminar = entityM.find(NotificacionAlertaJPA.class, id);
			entityM.remove(alertaEliminar);
			
		}catch(Exception e) {
			establecerSalidaError(e, "eliminarAlertas", "error.ioexception");
			throw new ApplicationException(e.getMessage());
		}
	}
	
	  private void establecerSalidaError(Exception exception, String metodo, String codError) {

		    String login = this.securityService.getPrincipal().getLogin();
		    
		    this.logger.error("Error ejecutando la clase: DeleteAlertasDAOImpl",
		        new Object[] { metodo, login, ExceptionUtils.getStackTrace(exception) });
	  }



}
