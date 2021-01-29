package es.mercadona.gesaduan.restfull.expediciones.v100;

import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import es.mercadona.fwk.core.io.Resource;
import es.mercadona.fwk.core.io.ResourceUtils;
import es.mercadona.fwk.restful.service.annotate.RESTful;
import es.mercadona.gesaduan.business.expediciones.v1.PostExpedicionesCargarService;
import es.mercadona.gesaduan.dto.common.error.ErrorDTO;
import es.mercadona.gesaduan.dto.common.error.OutputResponseErrorDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.postdv.v1.restfull.DVInsertPKDTO;
import es.mercadona.gesaduan.dto.expediciones.postCargarExpedicion.v1.restfull.OutputPostExpedicionesDTO;
import es.mercadona.gesaduan.exception.EnumGesaduanException;
import es.mercadona.gesaduan.exception.GesaduanException;
import es.mercadona.gesaduan.jpa.declaracionesdevalor.DeclaracionesDeValorPK;
import es.mercadona.gesaduan.util.ResponseUtil;

@RESTful
@Path("logistica/gestion-aduanas/v1.0")
@RequestScoped
public class ExpedicionesRestful {

	@Inject
	private org.slf4j.Logger logger;		
	
	@Inject
	private PostExpedicionesCargarService postExpedicionesCargarService;
	
	
	@POST
	@Path("expediciones/cargar")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response upload(
			@FormDataParam("ficheroExpedicion") InputStream uploadedInputStream,
            @FormDataParam("ficheroExpedicion") FormDataContentDisposition filePartContentDispositionDetail,
            @NotNull @FormDataParam("codigoUsuario") String codUsusario,
            @DefaultValue("es-ES") @FormDataParam("locale") String locale,
            @Context HttpServletRequest request
			) {

		OutputPostExpedicionesDTO response =  new OutputPostExpedicionesDTO();
		DVInsertPKDTO dv = new DVInsertPKDTO();
		
		
		try {
			Map<String, String> mapaMetaData = new HashMap<String, String>();
			Resource resource = ResourceUtils.createResource(uploadedInputStream, filePartContentDispositionDetail.getFileName(), "application/vnd.ms-excel.sheet.macroEnabled.main+xml", true);
			DeclaracionesDeValorPK pk = postExpedicionesCargarService.cargarExpedicion(resource, codUsusario.toUpperCase(), locale);
			
			if(pk.getCodDeclaracionValor() != null) {
				
				dv.setNumeroDecalaracion(pk.getCodDeclaracionValor());
				dv.setAnyo(pk.getAnyo());
				dv.setVersion(pk.getVersion());	
				
				response.setNumeroDeclaracion(dv);
				
				mapaMetaData.put("RESULTADO", "Expedición generada. Se ha generado una nueva Declaración de Valor");
				response.setMetadatos(mapaMetaData);
				
			}else {
				mapaMetaData.put("RESULTADO", "Expedición actualizada.");
				response.setMetadatos(mapaMetaData);
			}
			
			response.setNumeroDeclaracion(dv);
			
			return Response.status(Status.CREATED).entity(response).build();

		} catch(GesaduanException ex) {
			this.logger.error("({}-{}) ERROR - {} {}","ExpedicionesRestful(GESADUAN)","upload",ex.getClass().getSimpleName(),ex.getMessage());	
			return Response.status(Status.BAD_REQUEST).entity(ResponseUtil.getError(ex, ex.getEnumGesaduan().getCodigo(), ex.getEnumGesaduan().getDescripcion())).build();
		} catch(Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","ExpedicionesRestful(GESADUAN)","upload",e.getClass().getSimpleName(),e.getMessage());	
			return Response.status(Status.BAD_REQUEST).entity(ResponseUtil.getError(e, EnumGesaduanException.ERROR_GENERICO.getCodigo(), e.getMessage())).build();
		}			
	}
	
	
	
	private OutputResponseErrorDTO getError(Exception ex) {

		OutputResponseErrorDTO error = new OutputResponseErrorDTO();
		ErrorDTO errorDesc = new ErrorDTO();
		
		List<String> partsError = Arrays.asList(ex.getMessage().toString().replaceAll("\\t", "").replaceAll("\\r", "").split("\\n"));

		errorDesc.setCodigo("500");
		errorDesc.setDescripcion("Se ha producido un error en el servicio ".concat(this.getClass().getSimpleName()));
		errorDesc.setDetalles(partsError);

		error.setError(errorDesc);

		return error;

	}
	
}
