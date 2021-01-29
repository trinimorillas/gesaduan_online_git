package es.mercadona.gesaduan.restfull.reas.v100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import es.mercadona.fwk.core.web.BoPage;
import es.mercadona.fwk.restful.service.annotate.RESTful;
import es.mercadona.gesaduan.business.reas.deletereas.v1.DeleteReasService;
import es.mercadona.gesaduan.business.reas.getreas.v1.GetReasSumarioService;
import es.mercadona.gesaduan.business.reas.getreasdetalle.v1.GetReasDetalleService;
import es.mercadona.gesaduan.business.reas.postreas.v1.PostReasService;
import es.mercadona.gesaduan.dto.common.error.ErrorDTO;
import es.mercadona.gesaduan.dto.common.error.OutputResponseErrorDTO;
import es.mercadona.gesaduan.dto.reas.common.v1.restfull.OutputPostReasDTO;
import es.mercadona.gesaduan.dto.reas.deletereas.v1.InputDeleteReasDTO;
import es.mercadona.gesaduan.dto.reas.getreas.v1.InputReasDTO;
import es.mercadona.gesaduan.dto.reas.getreas.v1.restfull.OutputReasDTO;
import es.mercadona.gesaduan.dto.reas.getreasdetalle.v1.InputReasDetalleDTO;
import es.mercadona.gesaduan.dto.reas.getreasdetalle.v1.restfull.OutputReasDetalleDTO;
import es.mercadona.gesaduan.dto.reas.postreas.v1.InputDatosPostReasDTO;

@RESTful
@Path("logistica/gestion-aduanas/v1.0")
@RequestScoped
public class ReasRestful {
	
	@Inject
	private GetReasSumarioService getReasSumarioService;
	
	@Inject
	private GetReasDetalleService getReasDetalleService;
	
	@Inject
	private PostReasService postReasService;
	
	@Inject
	private DeleteReasService deleteReasService;
	
	@GET
	@Path("codigos-rea")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getReas(
			@DefaultValue("es-ES") @QueryParam("locale") String locale,
			@QueryParam("codigoMin") String codigoMin,
			@QueryParam("codigoMax") String codigoMax,
			@QueryParam("codigoTaric") String codigoTaric,
			@DefaultValue("true") @QueryParam("existeRelacion") Boolean existeRelacion,
			@DefaultValue("+codigo") @QueryParam("orden") String orden,
			@DefaultValue("1") @QueryParam("paginaInicio") Long paginaInicio,
			@DefaultValue("10") @QueryParam("paginaTamanyo") Long paginaTamanyo
			) {
		
		OutputReasDTO response = null;
		
		try {
						
			InputReasDTO input = new InputReasDTO();
			
			input.setLocale(locale);
			
			if(codigoMin != null) {
				input.setCodigoDesde(codigoMin);
			}
			
			if(codigoMax != null) {
				input.setCodigoHasta(codigoMax);
			}
			
			if(codigoTaric != null) {
				input.setCodigoTaric(codigoTaric);
			}
			if(existeRelacion != null) {
				input.setExisteRelacion(existeRelacion);
			}
			
			input.setOrden(orden);
			
			BoPage pagination = new BoPage();
			pagination.setPage(paginaInicio);
			pagination.setLimit(paginaTamanyo);
			
			response = getReasSumarioService.getReasSumario(input, pagination);
			
			
		}catch(Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(getError(e)).build();
		}
		
		return Response.ok(response, MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("codigos-rea/{codigoRea}")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getReasDetalle(
			@DefaultValue("es-ES") @QueryParam("locale") String locale,
			@NotNull @PathParam("codigoRea") String codigoRea
			) {
		OutputReasDetalleDTO response = null;
		
		try {
			
			InputReasDetalleDTO input = new InputReasDetalleDTO();
			
			input.setLocale(locale);
			input.setCodigoRea(codigoRea);
			
			if(getReasDetalleService.checkExistRea(input)) {
				response = getReasDetalleService.getReasDetalle(input);
			}else {
				return Response.status(Status.NOT_FOUND).type("text/plain").entity("Conflicto. El recurso no existe en BBDD.").build();
			}
			
		}catch(Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(getError(e)).build();
		}
		
		return Response.ok(response, MediaType.APPLICATION_JSON).build();
	}
	
	@POST
	@Path("codigos-rea")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response postReas(
			InputDatosPostReasDTO input
			) {
		OutputPostReasDTO response = null;
		
		try {
			
			InputReasDetalleDTO rea = new InputReasDetalleDTO();
			rea.setCodigoRea(input.getDatos().getCodigo());
			rea.setLocale(input.getMetadatos().getLocale());
			rea.setCodigoUsuario(input.getMetadatos().getCodigoUsuario());
			
			if(getReasDetalleService.checkExistRea(rea)) {
				return Response.status(Status.CONFLICT).type("text/plain").entity("Conflicto. El recurso ya existe en BBDD.").build();
			}
			else {
				response = postReasService.crearReas(input);
			}
						
		}catch(Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(getError(e)).build();
		}
		
		return Response.status(Status.CREATED).entity(response).build();
	}
	
	@DELETE
	@Path("codigos-rea/{codigoRea}")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteReas(
			@NotNull @PathParam("codigoRea") String codigoRea
			) {
		
		try {
			InputDeleteReasDTO input = new InputDeleteReasDTO();
			
			input.setCodigoRea(codigoRea);
			
			deleteReasService.deleteReas(input);
			
			
		}catch(Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(getError(e)).build();
		}
		
		return Response.status(Status.NO_CONTENT).type("text/plain").entity("Ejecución correcta. El recurso es eliminado con éxito.").build();
		
	}
	
	
	
	private OutputResponseErrorDTO getError(Exception ex) {

		OutputResponseErrorDTO error = new OutputResponseErrorDTO();
		ErrorDTO errorDesc = new ErrorDTO();

		Map<String, String> mapaDetalleError = new HashMap<String, String>();
		mapaDetalleError.put("ERROR DETAIL: ", ex.getMessage().toString());

		List<Map> listaDetalles = new ArrayList<Map>();
		listaDetalles.add(mapaDetalleError);

		errorDesc.setCodigo("500");
		errorDesc.setDescripcion("Se ha producido un error en el servicio ".concat(this.getClass().getSimpleName()));
		errorDesc.setDetalles(listaDetalles);

		error.setError(errorDesc);

		return error;

	}

}
