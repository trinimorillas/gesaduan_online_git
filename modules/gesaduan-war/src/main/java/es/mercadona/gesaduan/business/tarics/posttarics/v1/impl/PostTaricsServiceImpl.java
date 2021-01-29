package es.mercadona.gesaduan.business.tarics.posttarics.v1.impl;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.alertas.crearalertas.v1.CrearAlertasService;
import es.mercadona.gesaduan.business.tarics.gettarics.v1.GetTaricsSumarioService;
import es.mercadona.gesaduan.business.tarics.posttarics.v1.PostTaricsService;
import es.mercadona.gesaduan.dao.tarics.posttarics.v1.PostTaricsDAO;
import es.mercadona.gesaduan.dto.tarics.common.v1.restfull.OutputTaricsDTO;
import es.mercadona.gesaduan.dto.tarics.posttarics.v1.restful.InputDatosPostDTO;
import es.mercadona.gesaduan.jpa.tarics.v1.TaricsJPA;

public class PostTaricsServiceImpl implements PostTaricsService{
	
	@Inject 
	private PostTaricsDAO postTaricsDao;
	
	@Inject
	private GetTaricsSumarioService getTaricsSumarioService;
	
	@Inject
	private CrearAlertasService crearAlertasService;

	@Override
	public OutputTaricsDTO createTarics(InputDatosPostDTO input) {
		
		Long codigoTaric = Long.parseLong(input.getDatos().getCodigo());
		
		TaricsJPA datosToInsert = new TaricsJPA();
		datosToInsert.setCodigoTaric(codigoTaric); 
		datosToInsert.setCapitulo(input.getDatos().getCapitulo()); 
		datosToInsert.setPartida(input.getDatos().getPartidaSA()); 
		datosToInsert.setSubpartida(input.getDatos().getSubPartidaNC()); 
		datosToInsert.setSubdivision(input.getDatos().getSubPartidaSA()); 
		datosToInsert.setDescApertura(input.getDatos().getAperturaTARIC()); 
		datosToInsert.setCodigoAplicacion("GESADUAN");
		datosToInsert.setUsuarioCreacion(input.getMetadatos().getCodigoUsuario().toUpperCase());
		datosToInsert.setUsuarioModificacion(input.getMetadatos().getCodigoUsuario().toUpperCase());
		
		OutputTaricsDTO result = postTaricsDao.crearTarics(datosToInsert);
		
		boolean crearAlerta = getTaricsSumarioService.checkExistTaricAlerta(codigoTaric);
		if(crearAlerta) {
			Integer codigoAlerta = 7;
			String codigoUsuario = input.getMetadatos().getCodigoUsuario().toUpperCase();
			crearAlertasService.crearAlerta(codigoAlerta, String.valueOf(codigoTaric), codigoUsuario);
		}
		
		return result;
	}

}
