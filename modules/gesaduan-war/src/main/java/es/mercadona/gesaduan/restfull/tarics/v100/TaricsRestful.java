package es.mercadona.gesaduan.restfull.tarics.v100;

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
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import es.mercadona.fwk.core.web.BoPage;
import es.mercadona.fwk.restful.service.annotate.RESTful;
import es.mercadona.gesaduan.business.tarics.deletetaricrea.v1.DeleteTaricReaService;
import es.mercadona.gesaduan.business.tarics.deletetarics.v1.DeleteTaricsService;
import es.mercadona.gesaduan.business.tarics.gettarics.v1.GetTaricsSumarioService;
import es.mercadona.gesaduan.business.tarics.gettaricsdetalle.v1.GetTaricsDetalleService;
import es.mercadona.gesaduan.business.tarics.posttaricrea.v1.PostTaricReaService;
import es.mercadona.gesaduan.business.tarics.posttarics.v1.PostTaricsService;
import es.mercadona.gesaduan.business.tarics.putreemplazartarics.v1.PutReemplazarTaricsService;
import es.mercadona.gesaduan.business.tarics.puttarics.v1.PutTaricsService;
import es.mercadona.gesaduan.dto.common.error.ErrorDTO;
import es.mercadona.gesaduan.dto.common.error.OutputResponseErrorDTO;
import es.mercadona.gesaduan.dto.tarics.common.v1.restfull.OutputTaricsDTO;
import es.mercadona.gesaduan.dto.tarics.deletetaricrea.v1.InputDeleteTaricReaDTO;
import es.mercadona.gesaduan.dto.tarics.deletetaricrea.v1.restfull.OutputDeleteTaricReaDTO;
import es.mercadona.gesaduan.dto.tarics.deletetarics.v1.InputTaricDeleteDTO;
import es.mercadona.gesaduan.dto.tarics.gettarics.v1.restful.InputTaricsDTO;
import es.mercadona.gesaduan.dto.tarics.gettaricsdetalle.v1.InputTaricsDetalleDTO;
import es.mercadona.gesaduan.dto.tarics.gettaricsdetalle.v1.restfull.OutputTaricsDetalleDTO;
import es.mercadona.gesaduan.dto.tarics.posttaricrea.v1.InputTaricReaDTO;
import es.mercadona.gesaduan.dto.tarics.posttaricrea.v1.restfull.OutputTaricReaDTO;
import es.mercadona.gesaduan.dto.tarics.posttarics.v1.restful.InputDatosPostDTO;
import es.mercadona.gesaduan.dto.tarics.putreemplazartaric.v1.restful.InputReemplazarTaricsDTO;
import es.mercadona.gesaduan.dto.tarics.putreemplazartaric.v1.restful.OutputReemplazarTaricsDTO;
import es.mercadona.gesaduan.dto.tarics.puttarics.v1.restful.InputDatosPutDTO;

@RESTful
@Path("logistica/gestion-aduanas/v1.0")
@RequestScoped
public class TaricsRestful {
		
	@Inject
	private GetTaricsSumarioService getTaricsSumarioService;
	
	@Inject
	private DeleteTaricsService deleteTaricService;
	
	@Inject
	private PostTaricsService postTaricsService;
	
	@Inject
	private GetTaricsDetalleService getTaricsDetalleService;
	
	@Inject
	private PutTaricsService putTaricsService;
	
	@Inject
	private PutReemplazarTaricsService putReemplazarTaricsService;
	
	@Inject
	private PostTaricReaService postTaricReaService;
	
	@Inject
	private DeleteTaricReaService deleteTaricReaService;
	
	/*
	 * TaricsRestful
	 * 
	 * */
	
	
	@GET
	@Path("codigos-taric")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTarics(
			@NotNull @DefaultValue("es-ES") @QueryParam("locale") String locale,
			@QueryParam("codigoMin") String codigoMin,
			@QueryParam("codigoMax") String codigoMax,
			@QueryParam("descripcion") String descripcion,
			@DefaultValue("+codigo") @QueryParam("orden") String orden,
			@DefaultValue("1") @QueryParam("paginaInicio") Long paginaInicio,
			@DefaultValue("10") @QueryParam("paginaTamanyo") Long paginaTamanyo
			) {
		
		/* DATOS DE SALIDA */
		OutputTaricsDTO response = null;
		
		try {
			
			InputTaricsDTO input = new InputTaricsDTO();
			
			input.setLocale(locale);
			input.setOrden(orden);
			
			if(codigoMin != null){			
				input.setCodigoTaricDesde(codigoMin);
			}
			
			if(codigoMax != null) {
				input.setCodigoTaricHasta(codigoMax);
			}
			
			if(descripcion != null) {
				input.setDescripcion(descripcion);
			}
			
			BoPage pagination = new BoPage();
			pagination.setPage(paginaInicio);
			pagination.setLimit(paginaTamanyo);
			
			
			response = getTaricsSumarioService.getSumarioTarics(input, pagination);
		}catch(Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(getError(e)).build();
		}
		
		return Response.ok(response, MediaType.APPLICATION_JSON).build();
	}
	
	
	@POST
	@Path("codigos-taric")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response postTarics(
			InputDatosPostDTO input
			) {
		OutputTaricsDTO response;
		try {
			
			InputTaricsDetalleDTO taric = new InputTaricsDetalleDTO();
			taric.setCodigoTaric(input.getDatos().getCodigo());
			taric.setCodigoUsuario(input.getMetadatos().getCodigoUsuario());
			taric.setLocale(input.getMetadatos().getLocale());
			
			if(getTaricsDetalleService.checkExistTaric(taric)) {
				return Response.status(Status.CONFLICT).type("text/plain").entity("Conflicto. El recurso ya existe en BBDD.").build();
			}else {
				response = postTaricsService.createTarics(input);
			}
		
		}catch(Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(getError(e)).build();
		}
		
		return Response.status(Status.CREATED).entity(response).build();
	}
	
	@PUT
	@Path("codigos-taric/{codigoTaric}")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response putTarics(
			@NotNull @PathParam("codigoTaric") String codigoTaric,
			InputDatosPutDTO input
			) {
		
		OutputTaricsDTO response;
		input.getDatos().setCodigo(codigoTaric);
		
		try {
			response = putTaricsService.updateTarics(input);
		}catch(Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(getError(e)).build();
		}
		
		return Response.status(Status.CREATED).entity(response).build();
	}
	
	@DELETE
	@Path("codigos-taric/{codigoTaric}")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteTarics(
			@NotNull @PathParam("codigoTaric") String codigoTaric
			) {
		
		try {
			InputTaricDeleteDTO inputDeleteTaric = new InputTaricDeleteDTO();
			
			inputDeleteTaric.setCodigo(codigoTaric);
			
			if(getTaricsSumarioService.checkExisteRelacionTaricProd(Long.parseLong(codigoTaric))) {
				String mensaje = "No se pueden eliminar tarics que tienen productos asociados";
				return Response.status(Status.BAD_REQUEST).entity(getFunctionalError(mensaje)).build();
			}else {
				deleteTaricService.deleteTarics(inputDeleteTaric);
			}
		}catch(Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(getError(e)).build();
		}
		return Response.status(Status.NO_CONTENT).type("text/plain").entity("Ejecución correcta. El recurso es eliminado con éxito.").build();
	}
	
	@GET
	@Path("codigos-taric/{codigoTaric}")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTaricsDetalle(
			@NotNull @PathParam("codigoTaric") String codigoTaric,
			@DefaultValue("es-ES") @QueryParam("locale") String locale
			) {
		
		OutputTaricsDetalleDTO taricsPorCodigo;
		
		try {
			InputTaricsDetalleDTO input = new InputTaricsDetalleDTO();
			
			input.setCodigoTaric(codigoTaric);
			input.setLocale(locale);
			
			taricsPorCodigo = getTaricsDetalleService.getTaricsDetalle(input);
			
		}catch(Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(getError(e)).build();
		}

		return Response.ok(taricsPorCodigo, MediaType.APPLICATION_JSON).build();
		
	}
	

	 
	@PUT
	@Path("codigos-taric/reemplazar")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response putTaricsReemplazar(
			InputReemplazarTaricsDTO input
			) {
		
		OutputReemplazarTaricsDTO response = new OutputReemplazarTaricsDTO();
		
		try {
			response = putReemplazarTaricsService.reemplazarTarics(input);
		}catch(Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(getError(e)).build();
		}
		
		return Response.status(Status.OK).entity(response).build();
	}
	
	@POST
	@Path("/codigos-taric/{codigoTaric}/codigos-rea/{codigoRea}-{fechaInicio}")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response postTaricsRea(
			@NotNull @PathParam("codigoTaric") String codigoTaric,
			@NotNull @PathParam("codigoRea") String codigoRea,
			@NotNull @PathParam("codigoRea") String fechaInicio,
			InputTaricReaDTO input
			) {
		OutputTaricReaDTO response = null;
		try {

			input.setCodigoRea(codigoRea);
			input.setCodigoTaric(codigoTaric);
			input.setFechaInicio(fechaInicio);
			
			response = postTaricReaService.crearRelacion(input);
		
		}catch(Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(getError(e)).build();
		}
		
		return Response.status(Status.CREATED).entity(response).build();
	}
	
	@PUT
	@Path("/codigos-taric/{codigoTaric}/codigos-rea/{codigoRea}-{fechaInicio}")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteTaricsRea(
			@NotNull @PathParam("codigoTaric") String codigoTaric,
			@NotNull @PathParam("codigoRea") String codigoRea,
			@NotNull @PathParam("fechaInicio") String fechaInicio,
			InputDeleteTaricReaDTO input
			) {
		OutputDeleteTaricReaDTO response = new OutputDeleteTaricReaDTO();
		try {

			input.setCodigoRea(codigoRea);
			input.setCodigoTaric(codigoTaric);
			input.setFechaInicio(fechaInicio);
			
			Boolean finVigencia = input.getDatos().getFinVigencia();
			if(finVigencia) {
				response = deleteTaricReaService.eliminarRelacion(input);
			}else {
				HashMap<String, String> metadatos = new HashMap<String, String>();
				response.setMetadatos(metadatos);
			}						
		
		}catch(Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(getError(e)).build();
		}
		
		return Response.status(Status.CREATED).entity(response).build();
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
	
	private OutputResponseErrorDTO getFunctionalError(String msg) {

		OutputResponseErrorDTO error = new OutputResponseErrorDTO();
		ErrorDTO errorDesc = new ErrorDTO();

		Map<String, String> mapaDetalleError = new HashMap<String, String>();
		mapaDetalleError.put("ERROR DETAIL: ", msg);

		List<Map> listaDetalles = new ArrayList<Map>();
		listaDetalles.add(mapaDetalleError);

		errorDesc.setCodigo("400");
		errorDesc.setDescripcion("Se ha producido un error funcional en el servicio ".concat(this.getClass().getSimpleName()));
		errorDesc.setDetalles(listaDetalles);
		
		HashMap<String, String> metadatos = new HashMap<String, String>();
		error.setMetadatos(metadatos);

		error.setError(errorDesc);

		return error;

	}

}
