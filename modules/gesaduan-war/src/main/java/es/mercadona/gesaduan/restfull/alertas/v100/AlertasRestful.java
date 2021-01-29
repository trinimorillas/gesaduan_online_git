package es.mercadona.gesaduan.restfull.alertas.v100;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import es.mercadona.gesaduan.business.alertas.deletealertas.v1.DeleteAlertasService;
import es.mercadona.gesaduan.business.alertas.getalertas.v1.GetAlertasService;
import es.mercadona.gesaduan.business.alertas.putalertas.v1.PutAlertasService;
import es.mercadona.gesaduan.dto.alertas.deletealertas.v1.InputDeleteAlertasDTO;
import es.mercadona.gesaduan.dto.alertas.deletealertas.v1.restfull.OutputDeleteAlertasDTO;
import es.mercadona.gesaduan.dto.alertas.getalertas.v1.InputAlertasDTO;
import es.mercadona.gesaduan.dto.alertas.getalertas.v1.restfull.OutputAlertasDTO;
import es.mercadona.gesaduan.dto.alertas.putalertas.v1.InputPutAlertasDTO;
import es.mercadona.gesaduan.dto.alertas.putalertas.v1.restfull.OutputPutAlertasDTO;
import es.mercadona.gesaduan.dto.common.error.ErrorDTO;
import es.mercadona.gesaduan.dto.common.error.OutputResponseErrorDTO;

@RESTful
@Path("logistica/gestion-aduanas/v1.0")
@RequestScoped
public class AlertasRestful {
	
	@Inject
	private org.slf4j.Logger logger;		
	
	@Inject
	private GetAlertasService getAlertasService;
	
	@Inject
	private PutAlertasService putAlertasService;
	
	@Inject
	private DeleteAlertasService deleteAlertasService;
	
	@GET
	@Path("notificaciones-alertas")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAlertas(
			@NotNull @DefaultValue("es-ES") @QueryParam("locale") String locale,
			@QueryParam("fechaDesde") String fechaDesde,
			@QueryParam("fechaHasta") String fechaHasta,
			@QueryParam("numeroProveedor") Long numeroProveedor,
			@QueryParam("nombreProveedor") String nombreProveedor,
			@QueryParam("tipoDeAlerta") String tipoDeAlerta,
			@QueryParam("estado") String estado,
			@QueryParam("codigoDV") Integer codigoDV,
			@QueryParam("anyoDV") Integer anyoDV,
			@QueryParam("versionDV") Integer versionDV,			
			@DefaultValue("1") @QueryParam("paginaInicio") Long paginaInicio,
			@DefaultValue("10") @QueryParam("paginaTamanyo") Long paginaTamanyo,
			@DefaultValue("-fecha") @QueryParam("orden") String orden
			) {
		
		OutputAlertasDTO response = null;
		
		try {
			
			InputAlertasDTO input = new InputAlertasDTO();
			
			input.setLocale(locale);
			input.setOrden(orden);
			
			SimpleDateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
			
			if(fechaDesde != null && !fechaDesde.isEmpty()) {
				Date fDesde = inFormat.parse(fechaDesde);
				input.setFechaDesde(fDesde);
			}
			
			if(fechaHasta != null && !fechaHasta.isEmpty()) {
				Date fHasta = inFormat.parse(fechaHasta);
				input.setFechaHasta(fHasta);
			}
			
			if(numeroProveedor != null) {
				input.setNumeroProveedor(numeroProveedor);
			}
			
			if(nombreProveedor != null && !nombreProveedor.isEmpty()) {
				input.setNombreProveedor(nombreProveedor);
			}
			
			if(tipoDeAlerta != null && !tipoDeAlerta.isEmpty()) {
				input.setTipoAlerta(tipoDeAlerta);
			}
			
			if(estado != null && !estado.isEmpty()) {
				input.setEstado(estado);
			}
			
			if(codigoDV != null) {
				input.setCodigoDV(codigoDV);
			} 
			
			if(anyoDV != null) {
				input.setAnyoDV(anyoDV);
			}
			
			if(versionDV != null) {
				input.setVersionDV(versionDV);
			}			
			
			BoPage paginacion = new BoPage();
			
			paginacion.setPage(paginaInicio);
			paginacion.setLimit(paginaTamanyo);
			
			response = getAlertasService.getAlertas(input, paginacion);
			
		}catch(Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","AlertasRestful(GESADUAN)","getAlertas",e.getClass().getSimpleName(),e.getMessage());				
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(getError(e)).build();
		}	
		return Response.status(Response.Status.OK).entity(response).build();
	}
	
	@PUT
	@Path("notificaciones-alertas-expedicion/{alerta}-{elemento}-{expedicion}-{fechaAlbaran}")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response actualizarAlertasExpedicion(
				@NotNull @PathParam("alerta") String alerta,
				@NotNull @PathParam("elemento") String elemento,
				@NotNull @PathParam("expedicion") String expedicion,
				@NotNull @PathParam("fechaAlbaran") String fechaAlbaran,
				InputPutAlertasDTO input
			) {
		
		OutputPutAlertasDTO response = new OutputPutAlertasDTO();
		
		try {
			input.getDatos().setAlerta(alerta);
			input.getDatos().setElemento(elemento);
			input.getDatos().setExpedicion(expedicion);
			input.getDatos().setFechaAlbaran(fechaAlbaran);
			
			response = putAlertasService.editarAlertas(input);
		}catch(Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","AlertasRestful(GESADUAN)","actualizarAlertasExpedicion",e.getClass().getSimpleName(),e.getMessage());				
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(getError(e)).build();
		}
		
		return Response.status(Status.OK).entity(response).build();
	}
	
	@PUT
	@Path("notificaciones-alertas/{alerta}-{elemento}")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response actualizarAlertasExpedicion(
				@NotNull @PathParam("alerta") String alerta,
				@NotNull @PathParam("elemento") String elemento,
				InputPutAlertasDTO input
			) {
		
		OutputPutAlertasDTO response = new OutputPutAlertasDTO();
		
		try {
			input.getDatos().setAlerta(alerta);
			input.getDatos().setElemento(elemento);
			
			response = putAlertasService.editarAlertas(input);
		}catch(Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","AlertasRestful(GESADUAN)","actualizarAlertasExpedicion",e.getClass().getSimpleName(),e.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(getError(e)).build();
		}
		
		return Response.status(Status.OK).entity(response).build();
	}
	
	@DELETE
	@Path("notificaciones-alertas-expedicion/{alerta}-{elemento}-{expedicion}-{fechaAlbaran}")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response eliminarAlertasExpedicion(
				@NotNull @PathParam("alerta") String alerta,
				@NotNull @PathParam("elemento") String elemento,
				@NotNull @PathParam("expedicion") String expedicion,
				@NotNull @PathParam("fechaAlbaran") String fechaAlbaran
			) {
		
		OutputDeleteAlertasDTO response = new OutputDeleteAlertasDTO();
		
		try {
			InputDeleteAlertasDTO input = new InputDeleteAlertasDTO();
			input.setAlerta(alerta);
			input.setElemento(elemento);
			input.setExpedicion(expedicion);
			input.setFechaAlbaran(fechaAlbaran);
			
			response = deleteAlertasService.eliminarAlertas(input);
			
		}catch(Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","AlertasRestful(GESADUAN)","eliminarAlertasExpedicion",e.getClass().getSimpleName(),e.getMessage());			
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(getError(e)).build();
		}
		
		return Response.status(Status.OK).entity(response).build();
	}
	
	@DELETE
	@Path("notificaciones-alertas/{alerta}-{elemento}")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response eliminarAlertasExpedicion(
				@NotNull @PathParam("alerta") String alerta,
				@NotNull @PathParam("elemento") String elemento
			) {
		
		OutputDeleteAlertasDTO response = new OutputDeleteAlertasDTO();
		
		try {
			InputDeleteAlertasDTO input = new InputDeleteAlertasDTO();
			input.setAlerta(alerta);
			input.setElemento(elemento);
			
			response = deleteAlertasService.eliminarAlertas(input);
			
		}catch(Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","AlertasRestful(GESADUAN)","eliminarAlertasExpedicion",e.getClass().getSimpleName(),e.getMessage());			
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(getError(e)).build();
		}
		
		return Response.status(Status.OK).entity(response).build();
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
