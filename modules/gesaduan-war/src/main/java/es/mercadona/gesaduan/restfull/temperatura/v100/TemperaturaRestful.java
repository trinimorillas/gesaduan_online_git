package es.mercadona.gesaduan.restfull.temperatura.v100;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import es.mercadona.fwk.restful.service.annotate.RESTful;
import es.mercadona.gesaduan.business.temperatura.gettemperatura.v1.GetTemperaturaService;
import es.mercadona.gesaduan.dto.common.error.ErrorDTO;
import es.mercadona.gesaduan.dto.common.error.OutputResponseErrorDTO;
import es.mercadona.gesaduan.dto.temperatura.gettemperatura.v1.OutputTemperaturaDTO;
import es.mercadona.gesaduan.exception.EnumGesaduanException;
import es.mercadona.gesaduan.util.ResponseUtil;

@RESTful
@Path("logistica/gestion-aduanas/v1.0")
@RequestScoped
public class TemperaturaRestful {
	
	@Inject
	private GetTemperaturaService getTemperaturaService;
	
	@Inject
	private org.slf4j.Logger logger;	
	
	@GET
	@Path("temperatura/sumario")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTemperatura() {
		
		OutputTemperaturaDTO response;
		
		try {			
			response = getTemperaturaService.listarTemperatura();	
		} catch(Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","TemperaturaRestful(GESADUAN)","getTemperatura",e.getClass().getSimpleName(),e.getMessage());	
			return Response.status(Status.BAD_REQUEST).entity(ResponseUtil.getError(e, EnumGesaduanException.ERROR_GENERICO.getCodigo(), e.getMessage())).build();
		}
		
		return Response.ok(response, MediaType.APPLICATION_JSON).build();
	}


}
