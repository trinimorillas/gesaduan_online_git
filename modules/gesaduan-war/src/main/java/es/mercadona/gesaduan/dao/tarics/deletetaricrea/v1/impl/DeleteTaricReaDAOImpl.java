package es.mercadona.gesaduan.dao.tarics.deletetaricrea.v1.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import es.mercadona.fwk.auth.SecurityService;
import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.fwk.core.exceptions.ExceptionUtils;
import es.mercadona.fwk.data.DaoBaseImpl;
import es.mercadona.gesaduan.dao.tarics.deletetaricrea.v1.DeleteTaricReaDAO;
import es.mercadona.gesaduan.dto.tarics.deletetaricrea.v1.InputDeleteTaricReaDTO;
import es.mercadona.gesaduan.jpa.tarics.v1.TaricsReaJPA;
import es.mercadona.gesaduan.jpa.tarics.v1.TaricsReaPkJPA;

public class DeleteTaricReaDAOImpl extends DaoBaseImpl<TaricsReaPkJPA, TaricsReaJPA> implements DeleteTaricReaDAO{
	
	@Inject
	private org.slf4j.Logger logger;
	
	@Inject
	private SecurityService securityService;

	@Transactional
	@Override
	public void eliminarRelacion(InputDeleteTaricReaDTO input) {
		try {
			Calendar yesterday = Calendar.getInstance();
			yesterday.add(Calendar.DATE, -1);
			Date fecha = yesterday.getTime();
			Date fechaActual = new Date();
			
			Long codigoTaric = Long.parseLong(input.getCodigoTaric());
			String codigoRea = input.getCodigoRea();
			
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			Date fechaInicio = new Date();
			fechaInicio = format.parse(input.getFechaInicio());
			
			TaricsReaPkJPA id = new TaricsReaPkJPA();
			id.setCodigoRea(codigoRea);
			id.setCodigoTaric(codigoTaric);
			id.setFechaInicio(fechaInicio);
			
			TaricsReaJPA relacion = entityM.find(TaricsReaJPA.class, id);
			relacion.setFechaFin(fecha);
			relacion.setFechaModificacionTaricRea(fechaActual);
			relacion.setCodigoModificacionTaricRea(input.getMetadatos().getCodigoUsuario().toUpperCase());
			
		}catch(Exception e) {
			establecerSalidaError(e, "eliminarRelacion", "error.ioexception");
			throw new ApplicationException(e.getMessage());
		}
		
	}

	@Override
	protected EntityManager getEntityManager() {
		return this.entityM;
	}

	@Override
	public void setEntityClass() {
		this.entityClass = TaricsReaJPA.class;
		
	}
	
	@PersistenceContext
	private EntityManager entityM;
	
	  private void establecerSalidaError(Exception exception, String metodo, String codError) {

		    String login = this.securityService.getPrincipal().getLogin();
		    
		    this.logger.error("Error ejecutando la clase: DeleteTaricReaDAOImpl",
		        new Object[] { metodo, login, ExceptionUtils.getStackTrace(exception) });
	  }

}
