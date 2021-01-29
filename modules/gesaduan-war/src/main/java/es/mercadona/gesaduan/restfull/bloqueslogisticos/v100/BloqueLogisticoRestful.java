package es.mercadona.gesaduan.restfull.bloqueslogisticos.v100;

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
import es.mercadona.gesaduan.business.bloqueslogisticos.getbloqueslogisticos.v1.GetBloquesLogisticosService;
import es.mercadona.gesaduan.dto.bloqueslogisticos.getbloqueslogisticos.v1.OutputBloquesLogisticosDTO;
import es.mercadona.gesaduan.dto.common.error.ErrorDTO;
import es.mercadona.gesaduan.dto.common.error.OutputResponseErrorDTO;
import es.mercadona.gesaduan.exception.EnumGesaduanException;
import es.mercadona.gesaduan.util.ResponseUtil;

@RESTful
@Path("logistica/gestion-aduanas/v1.0")
@RequestScoped
public class BloqueLogisticoRestful {
	
	@Inject
	private org.slf4j.Logger logger;	
	
	@Inject
	private GetBloquesLogisticosService getBloquesLogisticosService;
	
	@GET
	@Path("bloque-logistico/sumario")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBloquesLogisticos() {
		
		OutputBloquesLogisticosDTO response;
		
		try {			
			response = getBloquesLogisticosService.listarBloquesLogisticos();			
		} catch(Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","BloqueLogisticoRestful(GESADUAN)","getBloquesLogisticos",e.getClass().getSimpleName(),e.getMessage());	
			return Response.status(Status.BAD_REQUEST).entity(ResponseUtil.getError(e, EnumGesaduanException.ERROR_GENERICO.getCodigo(), e.getMessage())).build();
		}
		
		return Response.ok(response, MediaType.APPLICATION_JSON).build();
	}
	

}
