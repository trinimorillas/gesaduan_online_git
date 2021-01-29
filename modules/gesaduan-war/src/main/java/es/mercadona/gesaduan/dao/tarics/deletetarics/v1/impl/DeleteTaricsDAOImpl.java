package es.mercadona.gesaduan.dao.tarics.deletetarics.v1.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import es.mercadona.fwk.data.DaoBaseImpl;
import es.mercadona.gesaduan.dao.tarics.deletetarics.v1.DeleteTaricsDAO;
import es.mercadona.gesaduan.dto.tarics.deletetarics.v1.InputTaricDeleteDTO;
import es.mercadona.gesaduan.jpa.tarics.v1.TaricsJPA;

@Stateless
public class DeleteTaricsDAOImpl extends DaoBaseImpl<Long ,TaricsJPA> implements DeleteTaricsDAO{
	
	@PersistenceContext
	private EntityManager entityM;
	
	@Transactional
	@Override
	public TaricsJPA deleteTarics(InputTaricDeleteDTO input) {

		Long id = Long.parseLong(input.getCodigo());
		TaricsJPA tarics = entityM.find(TaricsJPA.class, id);
		entityM.remove(tarics);
		entityM.flush();
		
		return tarics;
		
	}

	@Override
	public void setEntityClass() {
		entityClass = TaricsJPA.class;
		
	}

	@Override
	protected EntityManager getEntityManager() {
		return this.entityM;
	}

}
