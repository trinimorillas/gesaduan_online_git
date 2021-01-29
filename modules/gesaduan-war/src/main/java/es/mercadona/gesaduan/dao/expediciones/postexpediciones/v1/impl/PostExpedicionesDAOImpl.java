package es.mercadona.gesaduan.dao.expediciones.postexpediciones.v1.impl;

import java.util.Date;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.fwk.data.DaoBaseImpl;
import es.mercadona.gesaduan.dao.expediciones.postexpediciones.v1.PostExpedicionesDAO;
import es.mercadona.gesaduan.dto.expediciones.postCargarExpedicion.v1.ExpedicionResponsePKsDTO;
import es.mercadona.gesaduan.jpa.expediciones.v1.postexpediciones.ExpedicionesCabeceraJPA;
import es.mercadona.gesaduan.jpa.expediciones.v1.postexpediciones.ExpedicionesCabeceraPK;

@Stateless
public class PostExpedicionesDAOImpl extends DaoBaseImpl<ExpedicionesCabeceraPK, ExpedicionesCabeceraJPA>
implements PostExpedicionesDAO{

	@PersistenceContext
	private EntityManager entityM;
	
	@Inject
	private org.slf4j.Logger logger;
	
	@Override
	public ExpedicionResponsePKsDTO postCabecera(ExpedicionesCabeceraJPA input) {
		
		ExpedicionesCabeceraPK pk = new ExpedicionesCabeceraPK();
		Boolean crearDV = false;
				
		pk.setCodVExpedicion(input.getCodVExpedicion());
		pk.setFechaAlbaran(input.getFechaAlbaran());
		
		try {
			
			ExpedicionesCabeceraJPA expeAux = findById(pk);
			
			if(expeAux == null) {
				entityM.persist(input);
				entityM.flush();
				
				pk.setCodVExpedicion(input.getCodVExpedicion());
				pk.setFechaAlbaran(input.getFechaAlbaran());
				
				crearDV = true;
				
			}else {
				
				expeAux.setEditUser(input.getCreationUser());
				expeAux.setEditDate(new Date());
				expeAux.setCondicionesEntrega(input.getCondicionesEntrega());
				
				
				entityM.persist(expeAux);
				entityM.flush();
				
				pk.setCodVExpedicion(expeAux.getCodVExpedicion());
				pk.setFechaAlbaran(expeAux.getFechaAlbaran());				
			}
			
		
		} catch (Exception e) {
			
			throw new ApplicationException(e.getMessage());
		}
		
		ExpedicionResponsePKsDTO repsonse = new ExpedicionResponsePKsDTO(pk, crearDV);
		
		return repsonse;
		
	}
	
	@Override
	public String comprobarVersion() {
		try {		
			String select = "SELECT TXT_VALOR FROM C_VARIABLE WHERE COD_V_VARIABLE = 'VERSION_EXCEL'";

			final Query query = getEntityManager().createNativeQuery(select);
			String restultadoQuery = query.getSingleResult().toString();
			
			return restultadoQuery;		
		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","PostExpedicionesDAOImpl(GESADUAN)","comprobarVersion",e.getClass().getSimpleName(),e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}
	}

	@Override
	protected EntityManager getEntityManager() {
		return this.entityM;
	}

	@Override
	public void setEntityClass() {
		entityClass = ExpedicionesCabeceraJPA.class;
		
	}

}
