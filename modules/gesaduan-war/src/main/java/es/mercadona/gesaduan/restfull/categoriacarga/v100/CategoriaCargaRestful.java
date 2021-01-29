package es.mercadona.gesaduan.restfull.categoriacarga.v100;

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
import es.mercadona.gesaduan.business.categoriacarga.getcategorias.v1.GetCategoriaService;
import es.mercadona.gesaduan.dto.categoriacarga.getcategorias.v1.OutputCategoriaDTO;
import es.mercadona.gesaduan.dto.common.error.ErrorDTO;
import es.mercadona.gesaduan.dto.common.error.OutputResponseErrorDTO;
import es.mercadona.gesaduan.exception.EnumGesaduanException;
import es.mercadona.gesaduan.util.ResponseUtil;

@RESTful
@Path("logistica/gestion-aduanas/v1.0")
@RequestScoped
public class CategoriaCargaRestful {
	
	@Inject
	private org.slf4j.Logger logger;	
	
	@Inject
	private GetCategoriaService getCategoriaService;
	
	@GET
	@Path("categoria/sumario")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCategoriaCarga() {
		
		OutputCategoriaDTO response;
		
		try {			
			response = getCategoriaService.listarCategoria();	
		} catch(Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","CategoriaCargaRestful(GESADUAN)","getCategoriaCarga",e.getClass().getSimpleName(),e.getMessage());	
			return Response.status(Status.BAD_REQUEST).entity(ResponseUtil.getError(e, EnumGesaduanException.ERROR_GENERICO.getCodigo(), e.getMessage())).build();
		}
		
		return Response.ok(response, MediaType.APPLICATION_JSON).build();
	}

}
