package es.mercadona.gesaduan.restfull.planembarques.v100;

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
import es.mercadona.gesaduan.business.planembarques.cambiarestado.v1.CambiarEstadoService;
import es.mercadona.gesaduan.business.planembarques.deleteplanembarque.v1.DeletePlanEmbarqueService;
import es.mercadona.gesaduan.business.planembarques.getplanembarquedetalle.v1.GetPlanEmbarqueDetalleService;
import es.mercadona.gesaduan.business.planembarques.getplanembarques.v1.GetPlanEmbarquesService;
import es.mercadona.gesaduan.business.planembarques.putplanembarque.v1.PutPlanEmbarqueService;
import es.mercadona.gesaduan.dto.planembarques.cambiarestado.v1.InputDatosCambiarEstadoDTO;
import es.mercadona.gesaduan.dto.planembarques.cambiarestado.v1.restfull.OutputCambiarEstadoDTO;
import es.mercadona.gesaduan.dto.planembarques.deleteplanembarque.v1.InputDatosDeletePlanEmbarqueDTO;
import es.mercadona.gesaduan.dto.planembarques.deleteplanembarque.v1.InputMetadatosDeletePlanEmbarqueDTO;
import es.mercadona.gesaduan.dto.planembarques.deleteplanembarque.v1.InputPlanEmbarqueDeleteDTO;
import es.mercadona.gesaduan.dto.planembarques.getplanembarquedetalle.v1.InputDatosDetalleDTO;
import es.mercadona.gesaduan.dto.planembarques.getplanembarquedetalle.v1.restfull.OutputPlanEmbarqueDetalleDTO;
import es.mercadona.gesaduan.dto.planembarques.getplanembarques.v1.InputDatosGetPlanEmbarqueDTO;
import es.mercadona.gesaduan.dto.planembarques.getplanembarques.v1.InputPlanEmbarquesDTO;
import es.mercadona.gesaduan.dto.planembarques.getplanembarques.v1.restfull.OutputPlanEmbarquesDTO;
import es.mercadona.gesaduan.dto.planembarques.putplanembarque.v1.InputDatosPutDTO;
import es.mercadona.gesaduan.dto.planembarques.putplanembarque.v1.restfull.OutputPlanEmbarquePutDTO;
import es.mercadona.gesaduan.exception.EnumGesaduanException;
import es.mercadona.gesaduan.exception.GesaduanException;
import es.mercadona.gesaduan.util.ResponseUtil;

@RESTful
@Path("logistica/gestion-aduanas/v1.0")
@RequestScoped
public class PlanEmbarquesRestful {
	
	@Inject
	private PutPlanEmbarqueService putPlanEmbarqueService;
	
	@Inject
	private GetPlanEmbarquesService getPlanEmbarquesService;
	
	@Inject
	private GetPlanEmbarqueDetalleService getPlanEmbarqueDetalleService;
	
	@Inject
	private DeletePlanEmbarqueService deletePlanEmbarqueService;
	
	@Inject
	private CambiarEstadoService cambiarEstadoService;
	
	@Inject
	private org.slf4j.Logger logger;		
	
	@POST
	@Path("plan-embarque/sumario")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPlanEmbarques(
		@QueryParam("fechaEmbarque") String fechaEmbarque,
		@QueryParam("codigoBloqueOrigen") Integer codigoBloqueOrigen,
		@QueryParam("codigoPuertoEmbarque") Integer codigoPuertoEmbarque,
		@QueryParam("codigoPuertoDesembarque") Integer codigoPuertoDesembarque,
		@QueryParam("codigoEstadoDocumentacion") Integer codigoEstadoDocumentacion,
		@DefaultValue("1") @QueryParam("paginaInicio") Integer paginaInicio,
		@DefaultValue("14") @QueryParam("paginaTamanyo") Integer paginaTamanyo,
		@DefaultValue("-fechaEmbarque") @QueryParam("orden") String orden,
		InputDatosGetPlanEmbarqueDTO datos) {
		
		OutputPlanEmbarquesDTO response;
		
		try {
			BoPage pagination = new BoPage();
			pagination.setPage(Long.valueOf(paginaInicio));
			pagination.setLimit(Long.valueOf(paginaTamanyo));
			
			//InputPlanEmbarquesDTO input = new InputPlanEmbarquesDTO();
			
			if (fechaEmbarque != null) datos.getDatos().setFechaEmbarque(fechaEmbarque);			
			if (codigoBloqueOrigen != null) datos.getDatos().setCodigoBloqueOrigen(codigoBloqueOrigen);			
			if (codigoPuertoEmbarque != null) datos.getDatos().setCodigoPuertoEmbarque(codigoPuertoEmbarque);			
			if (codigoPuertoDesembarque != null) datos.getDatos().setCodigoPuertoDesembarque(codigoPuertoDesembarque);			
			if (codigoEstadoDocumentacion != null) datos.getDatos().setCodigoEstadoDocumentacion(codigoEstadoDocumentacion);
			datos.getDatos().setOrden(orden);

			response = getPlanEmbarquesService.listarPlanEmbarques(datos, pagination);		
			
		} catch(Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","PlanEmbarquesRestful(GESADUAN)","getPlanEmbarques",e.getClass().getSimpleName(),e.getMessage());	
			return Response.status(Status.BAD_REQUEST).entity(ResponseUtil.getError(e, EnumGesaduanException.ERROR_GENERICO.getCodigo(), e.getMessage())).build();
		}
		
		return Response.ok(response, MediaType.APPLICATION_JSON).build();
	}
	
	@POST
	@Path("plan-embarque/{codigoEmbarque}")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPlanEmbarqueDetalle (
			@NotNull @PathParam("codigoEmbarque") Long codigoEmbarque,
			@QueryParam("usuarioCreacion") String usuarioCreacion,
			@QueryParam("mcaOcultaLlenos") String mcaOcultaLlenos,
			@QueryParam("mcaIncluyeCargas") String mcaIncluyeCargas,			
			@QueryParam("mcaIncluyePedidos") String mcaIncluyePedidos,			
			@DefaultValue("+matricula") @QueryParam("orden") String orden,
			InputDatosDetalleDTO input) {	
		OutputPlanEmbarqueDetalleDTO response = null;
		
		try {			
			if (usuarioCreacion != null) input.getDatos().setUsuarioCreacion(usuarioCreacion);
			if (mcaOcultaLlenos != null) input.getDatos().setMcaOcultaLlenos(mcaOcultaLlenos);
			if (mcaIncluyeCargas != null) {
				input.getDatos().setMcaIncluyeCargas(mcaIncluyeCargas);
			} else {
				input.getDatos().setMcaIncluyeCargas("N");			
			}
			if (mcaIncluyePedidos != null) {
				input.getDatos().setMcaIncluyePedidos(mcaIncluyePedidos);
			} else {
				input.getDatos().setMcaIncluyePedidos("N");			
			}			
			
			input.getDatos().setCodigoEmbarque(codigoEmbarque);
			input.getDatos().setOrden(orden);
			
			response = getPlanEmbarqueDetalleService.getPlanEmbarqueDetalle(input);	
		} catch(Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","PlanEmbarquesRestful(GESADUAN)","getPlanEmbarqueDetalle",e.getClass().getSimpleName(),e.getMessage());	
			return Response.status(Status.BAD_REQUEST).entity(ResponseUtil.getError(e, EnumGesaduanException.ERROR_GENERICO.getCodigo(), e.getMessage())).build();
		}
		
		return Response.ok(response, MediaType.APPLICATION_JSON).build();
	}
	
	@PUT
	@Path("plan-embarque")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response putPlanEmbarque(@DefaultValue("1") @QueryParam("codigoEstado") Integer codigoEstado, InputDatosPutDTO input) {	
		OutputPlanEmbarquePutDTO response = null;
		
		try {
			if (input.getMetadatos() == null || input.getMetadatos().getCodigoUsuario() == null) throw new GesaduanException(EnumGesaduanException.PARAMETROS_OBLIGATORIOS);
			else if (input.getDatos().getCodigoEmbarque() != null) response = putPlanEmbarqueService.modificarPlanEmbarque(input);
			else if (input.getDatos().getFechaEmbarque() == null ||
					 input.getDatos().getCodigoBloqueOrigen() == null ||
					 input.getDatos().getCodigoPuertoEmbarque() == null ||
					 input.getDatos().getCodigoPuertoDesembarque() == null) {
					 	throw new GesaduanException(EnumGesaduanException.PARAMETROS_OBLIGATORIOS);
			} else {
				input.getDatos().setCodigoEstado(codigoEstado);
				response = putPlanEmbarqueService.crearPlanEmbarque(input);
			}
		} catch(GesaduanException ex) {
			this.logger.error("({}-{}) ERROR - {} {}","PlanEmbarquesRestful(GESADUAN)","putPlanEmbarque",ex.getClass().getSimpleName(),ex.getMessage());	
			return Response.status(Status.BAD_REQUEST).entity(ResponseUtil.getError(ex, ex.getEnumGesaduan().getCodigo(), ex.getEnumGesaduan().getDescripcion())).build();
		} catch(Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","PlanEmbarquesRestful(GESADUAN)","putPlanEmbarque",e.getClass().getSimpleName(),e.getMessage());	
			return Response.status(Status.BAD_REQUEST).entity(ResponseUtil.getError(e, EnumGesaduanException.ERROR_GENERICO.getCodigo(), e.getMessage())).build();
		}
		
		return Response.status(Status.CREATED).entity(response).build();
	}
	
	@DELETE
	@Path("plan-embarque/{codigoEmbarque}")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletePlanEmbarque(@NotNull @PathParam("codigoEmbarque") Long codigoEmbarque) {		
		try {
			//if (datos.getMetadatos() == null || datos.getMetadatos().getCodigoUsuario() == null) throw new GesaduanException(EnumGesaduanException.PARAMETROS_OBLIGATORIOS);
			InputDatosDeletePlanEmbarqueDTO datos = new InputDatosDeletePlanEmbarqueDTO();
			InputPlanEmbarqueDeleteDTO input = new InputPlanEmbarqueDeleteDTO();
			input.setCodigoEmbarque(codigoEmbarque);
			datos.setDatos(input);
			InputMetadatosDeletePlanEmbarqueDTO metadatos = new InputMetadatosDeletePlanEmbarqueDTO();
			datos.setMetadatos(metadatos);
			deletePlanEmbarqueService.deletePlanEmbarque(datos);	
		} catch(GesaduanException ex) {
			this.logger.error("({}-{}) ERROR - {} {}","PlanEmbarquesRestful(GESADUAN)","deletePlanEmbarque",ex.getClass().getSimpleName(),ex.getMessage());	
			return Response.status(Status.BAD_REQUEST).entity(ResponseUtil.getError(ex, ex.getEnumGesaduan().getCodigo(), ex.getEnumGesaduan().getDescripcion())).build();
		} catch(Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","PlanEmbarquesRestful(GESADUAN)","deletePlanEmbarque",e.getClass().getSimpleName(),e.getMessage());	
			return Response.status(Status.BAD_REQUEST).entity(ResponseUtil.getError(e, EnumGesaduanException.ERROR_GENERICO.getCodigo(), e.getMessage())).build();
		}
		
		return Response.status(Status.NO_CONTENT).build();
	}	
	/*
	@POST
	@Path("plan-embarque/{codigoEmbarque}")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletePlanEmbarque(@NotNull @PathParam("codigoEmbarque") Long codigoEmbarque,
			InputDatosDeletePlanEmbarqueDTO datos) {		
		try {
			if (datos.getMetadatos() == null || datos.getMetadatos().getCodigoUsuario() == null) throw new GesaduanException(EnumGesaduanException.PARAMETROS_OBLIGATORIOS);
			InputPlanEmbarqueDeleteDTO input = new InputPlanEmbarqueDeleteDTO();
			input.setCodigoEmbarque(codigoEmbarque);
			datos.setDatos(input);
			deletePlanEmbarqueService.deletePlanEmbarque(datos);	
		} catch(GesaduanException ex) {
			this.logger.error("({}-{}) ERROR - {} {}","PlanEmbarquesRestful(GESADUAN)","deletePlanEmbarque",ex.getClass().getSimpleName(),ex.getMessage());	
			return Response.status(Status.BAD_REQUEST).entity(ResponseUtil.getError(ex, ex.getEnumGesaduan().getCodigo(), ex.getEnumGesaduan().getDescripcion())).build();
		} catch(Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","PlanEmbarquesRestful(GESADUAN)","deletePlanEmbarque",e.getClass().getSimpleName(),e.getMessage());	
			return Response.status(Status.BAD_REQUEST).entity(ResponseUtil.getError(e, EnumGesaduanException.ERROR_GENERICO.getCodigo(), e.getMessage())).build();
		}
		
		return Response.status(Status.NO_CONTENT).build();
	}
	*/
	
	@PUT
	@Path("plan-embarque/{codigoEmbarque}/cambio-estado")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response putCambiarEstado(@PathParam("codigoEmbarque") Long codigoEmbarque, InputDatosCambiarEstadoDTO datos) {	
		OutputCambiarEstadoDTO response = null;
		
		try {
			if (datos.getDatos().getCodigoEstado() == null || datos.getMetadatos().getCodigoUsuario() == null)
				throw new GesaduanException(EnumGesaduanException.PARAMETROS_OBLIGATORIOS);
			datos.getDatos().setCodigoEmbarque(codigoEmbarque);			
			response = cambiarEstadoService.cambiarEstado(datos);
		} catch(GesaduanException ex) {
			this.logger.error("({}-{}) ERROR - {} {}","PlanEmbarquesRestful(GESADUAN)","putCambiarEstado",ex.getClass().getSimpleName(),ex.getMessage());	
			return Response.status(Status.BAD_REQUEST).entity(ResponseUtil.getError(ex, ex.getEnumGesaduan().getCodigo(), ex.getEnumGesaduan().getDescripcion())).build();
		} catch(Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","PlanEmbarquesRestful(GESADUAN)","putCambiarEstado",e.getClass().getSimpleName(),e.getMessage());	
			return Response.status(Status.BAD_REQUEST).entity(ResponseUtil.getError(e, EnumGesaduanException.ERROR_GENERICO.getCodigo(), e.getMessage())).build();
		}
		
		return Response.status(Status.CREATED).entity(response).build();
	}


}