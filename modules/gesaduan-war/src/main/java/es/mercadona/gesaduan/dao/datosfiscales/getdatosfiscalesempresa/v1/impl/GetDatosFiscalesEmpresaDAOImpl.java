package es.mercadona.gesaduan.dao.datosfiscales.getdatosfiscalesempresa.v1.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import es.mercadona.fwk.auth.SecurityService;
import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.fwk.core.exceptions.ExceptionUtils;
import es.mercadona.fwk.data.DaoBaseImpl;
import es.mercadona.gesaduan.dao.datosfiscales.getdatosfiscalesempresa.v1.GetDatosFiscalesEmpresaDAO;
import es.mercadona.gesaduan.jpa.datosfiscales.v1.DatosFiscalesEmpresaJPA;

@Stateless
public class GetDatosFiscalesEmpresaDAOImpl extends DaoBaseImpl<String, DatosFiscalesEmpresaJPA> implements GetDatosFiscalesEmpresaDAO{
	
	@Inject
	private org.slf4j.Logger logger;
	
	@PersistenceContext
	private EntityManager entityM;
		  
	@Inject
	private SecurityService securityService;
	
	@Override
	public DatosFiscalesEmpresaJPA getDatosFiscalesPorEmpresa(String codigoEmpresa) {
			
		return findById(codigoEmpresa);
	
	}

	@Override
	protected EntityManager getEntityManager() {
		return this.entityM;
	}

	@Override
	public void setEntityClass() {
		entityClass = DatosFiscalesEmpresaJPA.class;
		
	}

	@Override
	public String getDescPais(String codigoPais) {
		
		String descripcionPais = null;
		
		try {
			final StringBuilder sql = new StringBuilder();
			
			sql.append(" SELECT TXT_NOMBRE FROM D_PAIS_R ");
			sql.append(" WHERE COD_V_PUBLICO = ?codigoPais ");
			
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			query.setParameter("codigoPais", codigoPais);
			
			descripcionPais = String.valueOf(query.getSingleResult()) != null ? String.valueOf(query.getSingleResult()) : "";
		} catch (Exception e) {
			establecerSalidaError(e, "getDescPais", "error.ioexception");
			throw new ApplicationException(e.getMessage());
		}
		return descripcionPais;
	}
	
	  private void establecerSalidaError(Exception exception, String metodo, String codError) {

		    String login = this.securityService.getPrincipal().getLogin();
		    
		    this.logger.error("Error ejecutando la clase: GetDatosFiscalesEmpresaDAOImpl",
		        new Object[] { metodo, login, ExceptionUtils.getStackTrace(exception) });
	  }

}
