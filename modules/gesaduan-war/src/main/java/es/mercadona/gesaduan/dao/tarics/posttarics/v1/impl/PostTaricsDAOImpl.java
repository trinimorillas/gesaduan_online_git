package es.mercadona.gesaduan.dao.tarics.posttarics.v1.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import es.mercadona.fwk.data.DaoBaseImpl;
import es.mercadona.gesaduan.dao.tarics.posttarics.v1.PostTaricsDAO;
import es.mercadona.gesaduan.dto.tarics.common.v1.restfull.DatosTaricsDTO;
import es.mercadona.gesaduan.dto.tarics.common.v1.restfull.OutputTaricsDTO;
import es.mercadona.gesaduan.jpa.tarics.v1.TaricsJPA;


public class PostTaricsDAOImpl extends DaoBaseImpl<Long, TaricsJPA> implements PostTaricsDAO{

	@Override
	public void setEntityClass() {
		this.entityClass = TaricsJPA.class;
		
	}
	
	@PersistenceContext
	private EntityManager entityM;
	
	@Override
	protected EntityManager getEntityManager() {

		return this.entityM;
	}
	
	
	@Transactional
	@Override
	public OutputTaricsDTO crearTarics(TaricsJPA input) {
		
		entityM.persist(input);
		entityM.flush();
		
		DatosTaricsDTO result = new DatosTaricsDTO();
		List <DatosTaricsDTO> resultList =  new ArrayList<>();
		
		result.setCodigo(Long.toString(input.getCodigoTaric()));
		result.setCapitulo(input.getCapitulo());
		result.setPartidaSA(input.getPartida());
		result.setSubPartidaSA(input.getSubpartida());
		result.setSubPartidaNC(input.getSubdivision());
		result.setAperturaTARIC(input.getDescApertura());
		
		resultList.add(result);
		
		OutputTaricsDTO resultSalida = new OutputTaricsDTO();
		
		HashMap<String, String> metadatos = new HashMap<>();
		
		resultSalida.setDatos(resultList);
		resultSalida.setMetadatos(metadatos);
		
		return resultSalida;
	}
	




}
