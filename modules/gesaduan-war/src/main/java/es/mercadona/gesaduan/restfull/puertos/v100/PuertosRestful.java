package es.mercadona.gesaduan.restfull.puertos.v100;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import es.mercadona.fwk.restful.service.annotate.RESTful;
import es.mercadona.gesaduan.business.puertos.getpuertoagencia.v1.GetPuertoAgenciaService;
import es.mercadona.gesaduan.business.puertos.getpuertos.v1.GetPuertosService;
import es.mercadona.gesaduan.dto.puertos.getpuertoagencia.v1.InputDatosPuertoAgenciaDTO;
import es.mercadona.gesaduan.dto.puertos.getpuertoagencia.v1.restfull.OutputPuertoAgenciaDTO;
import es.mercadona.gesaduan.dto.puertos.getpuertos.v1.OutputPuertosDTO;
import es.mercadona.gesaduan.exception.EnumGesaduanException;
import es.mercadona.gesaduan.exception.GesaduanException;
import es.mercadona.gesaduan.util.ResponseUtil;

@RESTful
@Path("logistica/gestion-aduanas/v1.0")
@RequestScoped
public class PuertosRestful {
	
	@Inject
	private org.slf4j.Logger logger;		
	
	@Inject
	private GetPuertosService getPuertosService;
	@Inject
	private GetPuertoAgenciaService getPuertoAgenciaService;
	
	@GET
	@Path("puerto/sumario")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPuertos() {
		
		OutputPuertosDTO response;
		
		try {			
			response = getPuertosService.listarPuertos();			
		} catch(Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","PuertosRestful(GESADUAN)","getPuertos",e.getClass().getSimpleName(),e.getMessage());	
			return Response.status(Status.BAD_REQUEST).entity(ResponseUtil.getError(e, EnumGesaduanException.ERROR_GENERICO.getCodigo(), e.getMessage())).build();
		}
		
		return Response.ok(response, MediaType.APPLICATION_JSON).build();
	}
	
	@POST
	@Path("puerto/listar")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response eliminarRelacionEquipoCarga(@DefaultValue("+nombrePuerto") @QueryParam("orden") String orden,
			InputDatosPuertoAgenciaDTO datos) {
		OutputPuertoAgenciaDTO response;
		try {
			datos.getDatos().setOrden(orden);
			response = getPuertoAgenciaService.listarPuertoAgencia(datos);
		} catch(GesaduanException ex) {
			this.logger.error("({}-{}) ERROR - {} {}","PuertosRestful(GESADUAN)","listarPuertoAgencia",ex.getClass().getSimpleName(),ex.getMessage());
			return Response.status(Status.BAD_REQUEST).entity(ResponseUtil.getError(ex, ex.getEnumGesaduan().getCodigo(), ex.getEnumGesaduan().getDescripcion())).build();
		} catch(Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","PuertosRestful(GESADUAN)","listarPuertoAgencia",e.getClass().getSimpleName(),e.getMessage());	
			return Response.status(Status.BAD_REQUEST).entity(ResponseUtil.getError(e, EnumGesaduanException.ERROR_GENERICO.getCodigo(), e.getMessage())).build();
		}
		
		return Response.ok(response, MediaType.APPLICATION_JSON).build();
	}

}
