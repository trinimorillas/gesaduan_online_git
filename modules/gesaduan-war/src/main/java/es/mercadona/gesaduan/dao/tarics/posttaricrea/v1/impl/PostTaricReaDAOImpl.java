package es.mercadona.gesaduan.dao.tarics.posttaricrea.v1.impl;

import java.util.Date;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import es.mercadona.fwk.auth.SecurityService;
import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.fwk.core.exceptions.ExceptionUtils;
import es.mercadona.fwk.data.DaoBaseImpl;
import es.mercadona.gesaduan.dao.tarics.posttaricrea.v1.PostTaricReaDAO;
import es.mercadona.gesaduan.dto.tarics.posttaricrea.v1.InputTaricReaDTO;
import es.mercadona.gesaduan.jpa.tarics.v1.TaricsReaJPA;
import es.mercadona.gesaduan.jpa.tarics.v1.TaricsReaPkJPA;

public class PostTaricReaDAOImpl extends DaoBaseImpl<TaricsReaPkJPA, TaricsReaJPA> implements PostTaricReaDAO{

	@Inject
	private org.slf4j.Logger logger;
	
	@Inject
	private SecurityService securityService;
	
	@Transactional
	@Override
	public void crearRelacion(InputTaricReaDTO input) {
		
		try {
			Date fechaActual = new Date();
			Long codigoTaric = Long.parseLong(input.getCodigoTaric());
			String codigoRea = input.getCodigoRea();
			String codigoUsuario = input.getMetadatos().getCodigoUsuario().toUpperCase();

			TaricsReaPkJPA id = new TaricsReaPkJPA();
			id.setFechaInicio(fechaActual);
			id.setCodigoRea(codigoRea);
			id.setCodigoTaric(codigoTaric);
			
			TaricsReaJPA existeRelacion = entityM.find(TaricsReaJPA.class, id);
			if(existeRelacion != null) {
				
				existeRelacion.setCodigoModificacionTaricRea(codigoUsuario);
				existeRelacion.setFechaFin(null);
				existeRelacion.setFechaModificacionTaricRea(fechaActual);
				
				entityM.flush();
			}else {
				TaricsReaJPA relacion = new TaricsReaJPA();
				relacion.setId(id);
				relacion.setFechaCreacionTaricRea(fechaActual);
				relacion.setCodigoCreacionTaricRea(codigoUsuario.toUpperCase());
				relacion.setCodigoAplicacionTaricRea("GESADUAN");
				
				entityM.persist(relacion);
			}
						
	
		}catch(Exception e) {
			establecerSalidaError(e, "crearRelacion", "error.ioexception");
			throw new ApplicationException(e.getMessage());
		}
	}
	
	  private void establecerSalidaError(Exception exception, String metodo, String codError) {

		    String login = this.securityService.getPrincipal().getLogin();
		    
		    this.logger.error("Error ejecutando la clase: PostTaricReaDAOImpl",
		        new Object[] { metodo, login, ExceptionUtils.getStackTrace(exception) });
	  }
	  
	
	@PersistenceContext
	private EntityManager entityM;

	@Override
	protected EntityManager getEntityManager() {
		return this.entityM;
	}

	@Override
	public void setEntityClass() {
		this.entityClass = TaricsReaJPA.class;
		
	}

}
