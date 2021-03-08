package es.mercadona.gesaduan.dao.dosier.putdosier.v1.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.fwk.data.DaoBaseImpl;
import es.mercadona.gesaduan.dao.dosier.putdosier.v1.PutDosierDAO;
import es.mercadona.gesaduan.dto.dosier.getdosierdetalle.v1.resfull.EquipoDTO;
import es.mercadona.gesaduan.dto.dosier.putdosier.v1.restfull.OutputDosierPutDTO;
import es.mercadona.gesaduan.jpa.dosier.DosierContenedorJPA;
import es.mercadona.gesaduan.jpa.dosier.DosierEquipoJPA;
import es.mercadona.gesaduan.jpa.dosier.DosierJPA;
import es.mercadona.gesaduan.jpa.dosier.DosierPkJPA;

public class PutDosierDAOImpl extends DaoBaseImpl<Long, DosierJPA> implements PutDosierDAO {

	@Inject
	private org.slf4j.Logger logger;		
	
	@PersistenceContext
	private EntityManager entityM;
	
	@Override
	protected EntityManager getEntityManager() {
		return this.entityM;
	}

	@Override
	public void setEntityClass() {
		this.entityClass = DosierJPA.class;		
	}
	
	@Transactional
	@Override
	public void crearDosier(DosierJPA dosierJPA) {
		
		
		try {
		
			
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","PutDosierDAOImpl(GESADUAN)","putDosierDao",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}			

	}
	
	@Transactional
	@Override
	public void crearRelacionDosierEquipo(DosierEquipoJPA dosierEquipoJPA) {
		

		
		try {
		
			
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","PutDosierDAOImpl(GESADUAN)","putDosierDao",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}			

	}	
	
	@Transactional
	@Override
	public void crearRelacionDosierContenedor(DosierContenedorJPA dosierContenedorJPA) {
		

		
		try {
		
			
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","PutDosierDAOImpl(GESADUAN)","putDosierDao",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}			

	}		
	
	@Override
	public DosierPkJPA getNewDosierPk() {
		
		try {
			
			DosierPkJPA dosierPk = null;			
			
			String select = "SELECT TO_NUMBER(TXT_VALOR)+1, TO_CHAR(SYSDATE,'YYYY') FROM C_VARIABLE WHERE COD_V_VARIABLE = 'NUM_DOSIER'";

			final Query query = getEntityManager().createNativeQuery(select);
			@SuppressWarnings("unchecked")
			List<Object[]> listado = query.getResultList();
			
			if (listado != null && !listado.isEmpty()) {
				for (Object[] tmp : listado) {			
									
					dosierPk = new DosierPkJPA();
					dosierPk.setNumDosier(Long.parseLong(String.valueOf(tmp[0])));
					dosierPk.setAnyoDosier(Integer.parseInt(String.valueOf(tmp[1])));
					
					break;
				}			
			}
			
			return dosierPk;	
			
		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","PostExpedicionesDAOImpl(GESADUAN)","comprobarVersion",e.getClass().getSimpleName(),e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}
	}	

}
