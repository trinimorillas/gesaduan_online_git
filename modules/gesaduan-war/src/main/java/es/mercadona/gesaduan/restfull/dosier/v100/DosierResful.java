package es.mercadona.gesaduan.restfull.dosier.v100;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
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

import es.mercadona.fwk.restful.service.annotate.RESTful;
import es.mercadona.gesaduan.business.dosier.cambiarestado.v1.CambiarEstadoService;
import es.mercadona.gesaduan.business.dosier.getdosierdetalle.v1.GetDosierDetalleService;
import es.mercadona.gesaduan.business.dosier.putdosier.v1.PutDosierService;
import es.mercadona.gesaduan.dto.dosier.cambiarestado.v1.InputDatosCambiarEstadoDTO;
import es.mercadona.gesaduan.dto.dosier.cambiarestado.v1.resfull.OutputCambiarEstadoDTO;
import es.mercadona.gesaduan.dto.dosier.getdosierdetalle.v1.InputDatosDetalleDTO;
import es.mercadona.gesaduan.dto.dosier.getdosierdetalle.v1.InputDosierDetalleDTO;
import es.mercadona.gesaduan.dto.dosier.getdosierdetalle.v1.resfull.OutputDosierDetalleDTO;
import es.mercadona.gesaduan.dto.dosier.putdosier.v1.InputDatosPutDTO;
import es.mercadona.gesaduan.dto.dosier.putdosier.v1.restfull.OutputDosierPutDTO;
import es.mercadona.gesaduan.exception.EnumGesaduanException;
import es.mercadona.gesaduan.exception.GesaduanException;
import es.mercadona.gesaduan.util.ResponseUtil;

@RESTful
@Path("logistica/gestion-aduanas/v1.0")
@RequestScoped
public class DosierResful {
	
	@Inject
	private GetDosierDetalleService getDosierDetalleService;
	
	@Inject
	private CambiarEstadoService cambiarEstadoService;
	
	@Inject
	private PutDosierService putDosierService;	
	
	@Inject
	private org.slf4j.Logger logger;		
	
	
	@GET
	@Path("dosier/{anyoDosier}-{numDosier}")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDosierDetalle (
			@NotNull @PathParam("anyoDosier") Integer anyoDosier,
			@NotNull @PathParam("numDosier") Long numDosier,			
			@DefaultValue("+numContenedor") @QueryParam("orden") String orden) {
		 
		OutputDosierDetalleDTO response = null;
		InputDatosDetalleDTO input = new InputDatosDetalleDTO();
		
		try {			
			
			InputDosierDetalleDTO datos= new InputDosierDetalleDTO();
			
			input.setDatos(datos) ;
			
			input.getDatos().setAnyoDosier(anyoDosier);
			input.getDatos().setNumDosier(numDosier);
			input.getDatos().setOrden(orden);			
			
			response = getDosierDetalleService.getDosierDetalle(input);	
			
		} catch(Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","DosierResful(GESADUAN)","getDosierDetalle",e.getClass().getSimpleName(),e.getMessage());	
			return Response.status(Status.BAD_REQUEST).entity(ResponseUtil.getError(e, EnumGesaduanException.ERROR_GENERICO.getCodigo(), e.getMessage())).build();
		}
		
		return Response.ok(response, MediaType.APPLICATION_JSON).build();
	}
	
	@PUT
	@Path("dosier")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response putDosier (InputDatosPutDTO datos) {
		 
		OutputDosierPutDTO response = null;
		
		try {			
			
			if (datos.getDatos() == null || datos.getDatos().getDosier() == null || datos.getDatos().getDosier().isEmpty())
				throw new GesaduanException(EnumGesaduanException.PARAMETROS_OBLIGATORIOS);
			
			response = putDosierService.putDosier(datos);	
			
		} catch(GesaduanException ex) {
			this.logger.error("({}-{}) ERROR - {} {}","DosierResful(GESADUAN)","putDosier",ex.getClass().getSimpleName(),ex.getMessage());	
			return Response.status(Status.BAD_REQUEST).entity(ResponseUtil.getError(ex, ex.getEnumGesaduan().getCodigo(), ex.getEnumGesaduan().getDescripcion())).build();
		} catch(Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","DosierResful(GESADUAN)","putDosier",e.getClass().getSimpleName(),e.getMessage());	
			return Response.status(Status.BAD_REQUEST).entity(ResponseUtil.getError(e, EnumGesaduanException.ERROR_GENERICO.getCodigo(), e.getMessage())).build();
		}		
		
		return Response.ok(response, MediaType.APPLICATION_JSON).build();
	}		
	

	@PUT
	@Path("dosier/cambio-estado")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response putCambiarEstado (InputDatosCambiarEstadoDTO datos) {
		 
		OutputCambiarEstadoDTO response = null;
		
		try {			
			
			if (datos.getDatos().getNumDosier() == null || datos.getDatos().getAnyoDosier() == null || datos.getMetadatos().getCodigoUsuario() == null)
				throw new GesaduanException(EnumGesaduanException.PARAMETROS_OBLIGATORIOS);
			
			response = cambiarEstadoService.cambiarEstado(datos);	
			
		} catch(GesaduanException ex) {
			this.logger.error("({}-{}) ERROR - {} {}","DosierResful(GESADUAN)","putCambiarEstado",ex.getClass().getSimpleName(),ex.getMessage());	
			return Response.status(Status.BAD_REQUEST).entity(ResponseUtil.getError(ex, ex.getEnumGesaduan().getCodigo(), ex.getEnumGesaduan().getDescripcion())).build();
		} catch(Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","DosierResful(GESADUAN)","putCambiarEstado",e.getClass().getSimpleName(),e.getMessage());	
			return Response.status(Status.BAD_REQUEST).entity(ResponseUtil.getError(e, EnumGesaduanException.ERROR_GENERICO.getCodigo(), e.getMessage())).build();
		}		
		
		return Response.ok(response, MediaType.APPLICATION_JSON).build();
	}	


}
