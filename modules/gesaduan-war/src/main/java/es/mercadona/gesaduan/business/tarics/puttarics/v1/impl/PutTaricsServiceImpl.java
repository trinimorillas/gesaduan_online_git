package es.mercadona.gesaduan.business.tarics.puttarics.v1.impl;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.tarics.puttarics.v1.PutTaricsService;
import es.mercadona.gesaduan.dao.tarics.puttarics.v1.PutTaricsDAO;
import es.mercadona.gesaduan.dto.tarics.common.v1.restfull.OutputTaricsDTO;
import es.mercadona.gesaduan.dto.tarics.puttarics.v1.restful.InputDatosPutDTO;
import es.mercadona.gesaduan.jpa.tarics.v1.TaricsJPA;

public class PutTaricsServiceImpl implements PutTaricsService{

	@Inject
	private PutTaricsDAO putTaricsDao;
	
	@Override
	public OutputTaricsDTO updateTarics(InputDatosPutDTO input) {
		
		Long codigoTaric = Long.parseLong(input.getDatos().getCodigo());
		
		TaricsJPA datosToUpdate = new TaricsJPA();
		datosToUpdate.setCodigoTaric(codigoTaric); 
		datosToUpdate.setCapitulo(input.getDatos().getCapitulo()); 
		datosToUpdate.setPartida(input.getDatos().getPartidaSA()); 
		datosToUpdate.setSubpartida(input.getDatos().getSubPartidaNC()); 
		datosToUpdate.setSubdivision(input.getDatos().getSubPartidaSA()); 
		datosToUpdate.setDescApertura(input.getDatos().getAperturaTARIC()); 
		datosToUpdate.setCodigoAplicacion("GESADUAN");
		datosToUpdate.setUsuarioCreacion(input.getMetadatos().getCodigoUsuario().toUpperCase());
		datosToUpdate.setUsuarioModificacion(input.getMetadatos().getCodigoUsuario().toUpperCase());
		
		OutputTaricsDTO result = putTaricsDao.updateTarics(datosToUpdate);

		return result;
	}

}
