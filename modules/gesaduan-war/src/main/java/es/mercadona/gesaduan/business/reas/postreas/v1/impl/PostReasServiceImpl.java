package es.mercadona.gesaduan.business.reas.postreas.v1.impl;

import java.util.Date;
import java.util.Formatter;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.reas.postreas.v1.PostReasService;
import es.mercadona.gesaduan.dao.reas.postreas.v1.PostReasDAO;
import es.mercadona.gesaduan.dto.reas.common.v1.restfull.OutputPostReasDTO;
import es.mercadona.gesaduan.dto.reas.postreas.v1.InputDatosPostReasDTO;
import es.mercadona.gesaduan.jpa.reas.v1.ReasPostJPA;

public class PostReasServiceImpl implements PostReasService{

	@Inject
	private PostReasDAO postReasDao;
	
	private Formatter obj;
	
	@Override
	public OutputPostReasDTO crearReas(InputDatosPostReasDTO input) {
		
		ReasPostJPA datosToInsert = new ReasPostJPA();
		
		/* AGREGAMOS CEROS FALTANTES AL TARIC SI TIENE MENOS DE 10 DIGITOS Y RECUPERAMOS SOLO LOS PRIMEROS 8 */
		
		/*
		obj = new Formatter();
		String codigoTaric = String.valueOf(obj.format("%010d", Integer.parseInt(input.getDatos().getCodigoTaric())));
		String codigoTaricCorto = codigoTaric.substring(0, 8);
		String codigoRea = codigoTaricCorto + input.getDatos().getCodigo();
		*/
		
		/* Datos a insertar en la tabla de Reas */
		datosToInsert.setCodigoRea(input.getDatos().getCodigo());
		datosToInsert.setUsuarioCreacion(input.getMetadatos().getCodigoUsuario());
		datosToInsert.setUsuarioModificacion(input.getMetadatos().getCodigoUsuario());
		datosToInsert.setCodigoAplicacion(new String("GESADUAN"));
		datosToInsert.setFechaCreacion(new Date());
		datosToInsert.setFechaModificacion(new Date());
		
		/* Datos a insertar en la relacion de Taric Rea */
		/*
		datosToInsert.setCodigoTaric(Long.parseLong(codigoTaric));
		datosToInsert.setCodigoCreacionTaricRea(input.getMetadatos().getCodigoUsuario());
		datosToInsert.setCodigoModificacionTaricRea(input.getMetadatos().getCodigoUsuario());
		datosToInsert.setCodigoAplicacionTaricRea(new String("GESADUAN"));
		
		
		Date fechaHoy = new Date();
		datosToInsert.setFechaInicio(fechaHoy);
		*/
		
		
		
		OutputPostReasDTO result = postReasDao.crearReas(datosToInsert);
		
		return result;
	}

}
