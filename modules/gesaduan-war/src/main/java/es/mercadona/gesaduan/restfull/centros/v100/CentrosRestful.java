package es.mercadona.gesaduan.restfull.centros.v100;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import es.mercadona.fwk.core.web.BoPage;
import es.mercadona.fwk.restful.service.annotate.RESTful;
import es.mercadona.gesaduan.business.centros.getcentros.v1.GetCentrosService;
import es.mercadona.gesaduan.dto.centros.getcentros.v1.InputCentrosDTO;
import es.mercadona.gesaduan.dto.centros.getcentros.v1.restfull.OutputCentrosDTO;
import es.mercadona.gesaduan.dto.common.error.ErrorDTO;
import es.mercadona.gesaduan.dto.common.error.OutputResponseErrorDTO;
import es.mercadona.gesaduan.exception.EnumGesaduanException;
import es.mercadona.gesaduan.util.ResponseUtil;

@RESTful
@Path("logistica/gestion-aduanas/v1.0")
@RequestScoped
public class CentrosRestful {
	
	@Inject
	private org.slf4j.Logger logger;	
	
	@Inject
	private GetCentrosService getCentrosService;
	
	@GET
	@Path("centro/sumario")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCentros(@DefaultValue("1") @QueryParam("paginaInicio") Integer paginaInicio,
			@DefaultValue("14") @QueryParam("paginaTamanyo") Integer paginaTamanyo,
			@QueryParam("codigoCentro") String codigoCentro) {
		
		OutputCentrosDTO response;
		
		try {
			InputCentrosDTO input = new InputCentrosDTO();
			BoPage pagination = new BoPage();
			pagination.setPage(Long.valueOf(paginaInicio));
			pagination.setLimit(Long.valueOf(paginaTamanyo));
			if (codigoCentro != null) input.setCodigoCentro(codigoCentro);
			response = getCentrosService.listarCentros(input, pagination);
		} catch(Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","CentrosRestful(GESADUAN)","getCentros",e.getClass().getSimpleName(),e.getMessage());	
			return Response.status(Status.BAD_REQUEST).entity(ResponseUtil.getError(e, EnumGesaduanException.ERROR_GENERICO.getCodigo(), e.getMessage())).build();
		}
		
		return Response.ok(response, MediaType.APPLICATION_JSON).build();
	}


}
