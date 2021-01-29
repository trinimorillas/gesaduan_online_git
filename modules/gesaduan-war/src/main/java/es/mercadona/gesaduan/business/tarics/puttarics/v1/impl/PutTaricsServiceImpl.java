package es.mercadona.gesaduan.business.tarics.puttarics.v1.impl;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.alertas.crearalertas.v1.CrearAlertasService;
import es.mercadona.gesaduan.business.tarics.gettarics.v1.GetTaricsSumarioService;
import es.mercadona.gesaduan.business.tarics.puttarics.v1.PutTaricsService;
import es.mercadona.gesaduan.dao.tarics.puttarics.v1.PutTaricsDAO;
import es.mercadona.gesaduan.dto.tarics.common.v1.restfull.OutputTaricsDTO;
import es.mercadona.gesaduan.dto.tarics.puttarics.v1.restful.InputDatosPutDTO;
import es.mercadona.gesaduan.jpa.tarics.v1.TaricsJPA;

public class PutTaricsServiceImpl implements PutTaricsService{

	@Inject
	private PutTaricsDAO putTaricsDao;
	
	@Inject
	private GetTaricsSumarioService getTaricsSumarioService;
	
	@Inject
	private CrearAlertasService crearAlertasService;
	
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
		
		boolean crearAlerta = getTaricsSumarioService.checkExistTaricAlerta(codigoTaric);
		if(crearAlerta) {
			Integer codigoAlerta = 7;
			String codigoUsuario = input.getMetadatos().getCodigoUsuario();
			crearAlertasService.crearAlerta(codigoAlerta, String.valueOf(codigoTaric), codigoUsuario.toUpperCase());
		}

		return result;
	}

}
