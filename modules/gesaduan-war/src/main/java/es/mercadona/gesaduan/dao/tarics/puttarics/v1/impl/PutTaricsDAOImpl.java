package es.mercadona.gesaduan.dao.tarics.puttarics.v1.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import es.mercadona.fwk.data.DaoBaseImpl;
import es.mercadona.gesaduan.dao.tarics.puttarics.v1.PutTaricsDAO;
import es.mercadona.gesaduan.dto.tarics.common.v1.restfull.DatosTaricsDTO;
import es.mercadona.gesaduan.dto.tarics.common.v1.restfull.OutputTaricsDTO;
import es.mercadona.gesaduan.jpa.tarics.v1.TaricsJPA;

public class PutTaricsDAOImpl extends DaoBaseImpl<Long, TaricsJPA> implements PutTaricsDAO{

	@PersistenceContext
	private EntityManager entityM;
	
	@Override
	protected EntityManager getEntityManager() {

		return this.entityM;
	}

	@Override
	public void setEntityClass() {
		this.entityClass = TaricsJPA.class;
		
	}
	
	@Transactional
	@Override
	public OutputTaricsDTO updateTarics(TaricsJPA input) {
		
		OutputTaricsDTO resultSalida = new OutputTaricsDTO();
		List <DatosTaricsDTO> resultList =  new ArrayList<>();
		HashMap<String, String> metadatos = new HashMap<>();
		
		Long id = input.getCodigoTaric();
		
		TaricsJPA actualizar = entityM.find(TaricsJPA.class, id);
		
		actualizar.setCapitulo(input.getCapitulo());
		actualizar.setCapitulo(input.getCapitulo());
		actualizar.setPartida(input.getPartida());
		actualizar.setSubpartida(input.getSubpartida());
		actualizar.setSubdivision(input.getSubdivision());
		actualizar.setDescApertura(input.getDescApertura());
		actualizar.setUsuarioModificacion(input.getUsuarioModificacion().toUpperCase());
		
		DatosTaricsDTO result = new DatosTaricsDTO();
		
		result.setCodigo(Long.toString(input.getCodigoTaric()));
		result.setCapitulo(input.getCapitulo());
		result.setPartidaSA(input.getPartida());
		result.setSubPartidaSA(input.getSubdivision());
		result.setSubPartidaNC(input.getSubpartida());
		result.setAperturaTARIC(input.getDescApertura());
		
		resultList.add(result);
		resultSalida.setDatos(resultList);
		resultSalida.setMetadatos(metadatos);
		
		return resultSalida;
	}



}
