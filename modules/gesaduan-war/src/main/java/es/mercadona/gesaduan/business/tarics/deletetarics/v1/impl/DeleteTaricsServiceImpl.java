package es.mercadona.gesaduan.business.tarics.deletetarics.v1.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.tarics.deletetarics.v1.DeleteTaricsService;
import es.mercadona.gesaduan.dao.tarics.deletetarics.v1.DeleteTaricsDAO;
import es.mercadona.gesaduan.dto.tarics.common.v1.restfull.DatosTaricsDTO;
import es.mercadona.gesaduan.dto.tarics.common.v1.restfull.OutputTaricsDTO;
import es.mercadona.gesaduan.dto.tarics.deletetarics.v1.InputTaricDeleteDTO;
import es.mercadona.gesaduan.jpa.tarics.v1.TaricsJPA;

public class DeleteTaricsServiceImpl implements DeleteTaricsService{
	
	@Inject
	private DeleteTaricsDAO deleteTaricDao;

	@Override
	public OutputTaricsDTO deleteTarics(InputTaricDeleteDTO input) {
		
		OutputTaricsDTO datosSalida = new OutputTaricsDTO();
		DatosTaricsDTO datos = new DatosTaricsDTO();
		List<DatosTaricsDTO> datosDos = new ArrayList<>();
		HashMap< String, String> metadatos = new HashMap<>();
		
		TaricsJPA taricDeleted = deleteTaricDao.deleteTarics(input);
		
		
		datos.setCodigo(Long.toString(taricDeleted.getCodigoTaric()));
		datos.setCapitulo(taricDeleted.getCapitulo());
		datos.setPartidaSA(taricDeleted.getPartida());
		datos.setSubPartidaNC(taricDeleted.getSubpartida());
		datos.setSubPartidaSA(taricDeleted.getSubdivision());
		datos.setAperturaTARIC(taricDeleted.getDescApertura());
		
		datosDos.add(datos);
		
		datosSalida.setDatos(datosDos);
		datosSalida.setMetadatos(metadatos);
		
		return datosSalida;
		
	}

}
