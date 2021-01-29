package es.mercadona.gesaduan.restfull.estadoequipo.v100;

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
import es.mercadona.gesaduan.business.estadoequipo.getestadoequipo.v1.GetEstadoEquipoService;
import es.mercadona.gesaduan.dto.common.error.ErrorDTO;
import es.mercadona.gesaduan.dto.common.error.OutputResponseErrorDTO;
import es.mercadona.gesaduan.dto.estadoequipo.getestadoequipo.v1.OutputEstadoEquipoDTO;
import es.mercadona.gesaduan.exception.EnumGesaduanException;
import es.mercadona.gesaduan.util.ResponseUtil;

@RESTful
@Path("logistica/gestion-aduanas/v1.0")
@RequestScoped
public class EstadoEquipoRestful {
	
	@Inject
	private org.slf4j.Logger logger;	
	
	@Inject
	private GetEstadoEquipoService getEstadoEquipoService;
	
	@GET
	@Path("estado-equipo/sumario")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEstados() {		
		OutputEstadoEquipoDTO response;
		
		try {
			response = getEstadoEquipoService.listarEstados();
		} catch(Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","EstadoEquipoRestful(GESADUAN)","getEstados",e.getClass().getSimpleName(),e.getMessage());	
			return Response.status(Status.BAD_REQUEST).entity(ResponseUtil.getError(e, EnumGesaduanException.ERROR_GENERICO.getCodigo(), e.getMessage())).build();
		}
		
		return Response.ok(response, MediaType.APPLICATION_JSON).build();
	}
	


}
