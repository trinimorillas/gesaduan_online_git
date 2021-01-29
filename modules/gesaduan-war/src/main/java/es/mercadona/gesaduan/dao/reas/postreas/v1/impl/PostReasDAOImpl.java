package es.mercadona.gesaduan.dao.reas.postreas.v1.impl;


import java.util.HashMap;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import es.mercadona.fwk.data.DaoBaseImpl;
import es.mercadona.gesaduan.dao.reas.postreas.v1.PostReasDAO;
import es.mercadona.gesaduan.dto.reas.common.v1.restfull.CodigoReaDTO;
import es.mercadona.gesaduan.dto.reas.common.v1.restfull.OutputPostReasDTO;
import es.mercadona.gesaduan.jpa.reas.v1.ReasPostJPA;

public class PostReasDAOImpl extends DaoBaseImpl<String , ReasPostJPA> implements PostReasDAO{

	@PersistenceContext
	private EntityManager entityM;
	
	@Override
	protected EntityManager getEntityManager() {
		
		return this.entityM;
	}

	@Override
	public void setEntityClass() {
		this.entityClass =  ReasPostJPA.class;
	}
	
	@Transactional
	@Override
	public OutputPostReasDTO crearReas(ReasPostJPA input) {
		
		entityM.persist(input);
		entityM.flush();
		
		CodigoReaDTO datosSalida = new CodigoReaDTO();
		
		datosSalida.setCodigo(input.getCodigoRea());
		
		OutputPostReasDTO result = new OutputPostReasDTO();	
		HashMap<String, String> metadatosSalida = new HashMap<>();
				
		result.setMetadatos(metadatosSalida);
		result.setDatos(datosSalida);
		
		return result;
	}


}
