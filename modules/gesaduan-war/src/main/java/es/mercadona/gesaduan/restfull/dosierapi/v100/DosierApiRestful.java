package es.mercadona.gesaduan.restfull.dosierapi.v100;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import es.mercadona.fwk.core.io.Resource;
import es.mercadona.fwk.core.io.ResourceService;
import es.mercadona.fwk.core.io.exceptions.IllegalResourceNameException;
import es.mercadona.fwk.core.io.exceptions.ResourceNotFoundException;
import es.mercadona.fwk.restful.service.annotate.RESTful;
import es.mercadona.gesaduan.business.declaracionesdevalor.putdvestadodescarga.v1.PutDVEstadoDescargaService;
import es.mercadona.gesaduan.business.dosierapi.getdocumento.v1.GetDocumentoService;
import es.mercadona.gesaduan.business.dosierapi.putdosierconfirmadescarga.v1.PutDosierConfirmaDescargaService;
import es.mercadona.gesaduan.common.Constantes;
import es.mercadona.gesaduan.dto.common.error.ErrorDTO;
import es.mercadona.gesaduan.dto.common.error.OutputResponseErrorDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.putdvinddescarga.v1.DeclaracionesDeValorEstadoDescargaServiceDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.putdvinddescarga.v1.restfull.InputDatosComunesDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.putdvinddescarga.v1.restfull.OutputDeclaracionesDeValorEstadoDescargaDTO;
import es.mercadona.gesaduan.dto.dosierapi.getdocumento.v1.InputDosierDocumentoDTO;
import es.mercadona.gesaduan.dto.dosierapi.getdocumento.v1.OutputDosierDocCabDTO;
import es.mercadona.gesaduan.dto.dosierapi.putdosierconfirmadescarga.v1.InputPutDosierConfirmaDescargaDTO;
import es.mercadona.gesaduan.dto.dosierapi.putdosierconfirmadescarga.v1.restfull.OutputPutDosierConfirmaDescargaDTO;
import es.mercadona.gesaduan.exception.EnumGesaduanException;
import es.mercadona.gesaduan.exception.GesaduanException;

@RESTful
@Path("logistica/gestion-aduanas/v2.0")
@RequestScoped
public class DosierApiRestful {
	
	@Inject
	private org.slf4j.Logger logger;		

	@Inject
	private ResourceService resourceService;
	@Inject
	private GetDocumentoService getDocumentoService;
	@Inject
	private PutDVEstadoDescargaService putDVEstadoDescargaService;
	@Inject
	private PutDosierConfirmaDescargaService putDosierConfirmaDescargaService;

	private static final String MIMETYPE_PDF = "application/pdf";
	private static final String MIMETYPE_CSV = "text/csv";
	private static final String FILE_BASE_NAME_PDF = "dv_";
	private static final String FILE_BASE_NAME_CSV = "csv_";

	private static final String LOG_FILE = "DeclaracionesDeValorRestful(GESADUAN)"; 	
	
	

	@GET
	@Path("dosier/{codigoDosier}-{anyo}/documento")
	@Consumes(MediaType.WILDCARD)
	@Produces({ MIMETYPE_PDF, MIMETYPE_CSV, MediaType.APPLICATION_JSON })
	public Response getDeclaracionesDeValorDocumento(
			@NotNull @PathParam("codigoDosier") Integer codigoDosier,
			@NotNull @PathParam("anyo") Integer anyo,  
			@Context HttpServletRequest request,
			@DefaultValue("es-ES") @QueryParam("locale") String locale,
			@NotNull @QueryParam("codigoUsuario") String codigoUsuario,
			@NotNull @DefaultValue("pdf") @QueryParam("tipoDocumento") String tipoDocumento) {
		
		ResponseBuilder rb = null;

		try {
			String fileExtension = ".";
			String mimeType = null;
			String fileBaseName = null;

			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
			Date date = new Date(System.currentTimeMillis());

			// Prepara datos basicos del fichero (extension,mimetype,nombre)
			if (tipoDocumento.equalsIgnoreCase("pdf")) {
				fileExtension += tipoDocumento.toLowerCase();
				mimeType = MIMETYPE_PDF;
				fileBaseName = FILE_BASE_NAME_PDF.concat(codigoDosier.toString()).concat("_")
						.concat(anyo.toString()).concat("_").concat(formatter.format(date));
			} else if (tipoDocumento.equalsIgnoreCase("csv")) {
				fileExtension += tipoDocumento.toLowerCase();
				mimeType = MIMETYPE_CSV;
				fileBaseName = FILE_BASE_NAME_CSV.concat(codigoDosier.toString()).concat("_")
						.concat(anyo.toString()).concat("_").concat(formatter.format(date));
			}

			// Prepara llamada al servicio que va a montar el fichero			
			InputDosierDocumentoDTO inputDocumentoDTO = new InputDosierDocumentoDTO();
			
			inputDocumentoDTO.setCodigoDosier(codigoDosier);
			inputDocumentoDTO.setAnyoDosier(anyo);
			inputDocumentoDTO.setTipoDocumento(tipoDocumento);			

			OutputDosierDocCabDTO outputDocumentoDTO = null;

			outputDocumentoDTO = getDocumentoService.preparaDocumento(inputDocumentoDTO);

			// devuelve el fichero y controla los posibles errores			
			if(outputDocumentoDTO != null) {
			
				byte[] file = null;
	
				if (tipoDocumento.equalsIgnoreCase("pdf")) { 
					file = outputDocumentoDTO.getFicheroPDF();
				} else if (tipoDocumento.equalsIgnoreCase("csv")) {
					file = outputDocumentoDTO.getFicheroCSV();
				} else {
					throw new WebApplicationException(Status.BAD_REQUEST);
				}
				
				if(file != null) {
						
					Resource resource = resourceService.createResource(file, fileBaseName, fileExtension);
		
					rb = Response.ok(resource.getInputStream(), mimeType);		
					rb.header("Content-Disposition", "attachment; filename=" + fileBaseName + fileExtension);
					rb.header("file-md5", resource.getMd5());
					
				} else {
					OutputResponseErrorDTO error = new OutputResponseErrorDTO();
					ErrorDTO errorDesc = new ErrorDTO();
					List<String> partsError = new ArrayList<>();
					
					partsError.add("Recurso no encontrado");
					
					errorDesc.setCodigo("400");
					errorDesc.setDescripcion("Formato de envío inválido o error funcional en: ".concat(this.getClass().getSimpleName()));
					errorDesc.setDetalles(partsError);

					error.setError(errorDesc);
									
					return Response.status(Status.BAD_REQUEST).entity(error).build();
				}
			
			}
			else {
				
				OutputResponseErrorDTO error = new OutputResponseErrorDTO();
				ErrorDTO errorDesc = new ErrorDTO();
				List<String> partsError = new ArrayList<>();
				
				partsError.add("Recurso no encontrado");
				
				errorDesc.setCodigo("400");
				errorDesc.setDescripcion("Formato de envío inválido o error funcional en: ".concat(this.getClass().getSimpleName()));
				errorDesc.setDetalles(partsError);

				error.setError(errorDesc);
								
				return Response.status(Status.BAD_REQUEST).entity(error).build();
			}

		} catch (ResourceNotFoundException e) {
			this.logger.error("({}-{}) ERROR - {} {}",LOG_FILE,"getDeclaracionesDeValorDocumento-1",e.getClass().getSimpleName(),e.getMessage());			
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(getError(e)).build();
		} catch (IllegalResourceNameException e) {
			this.logger.error("({}-{}) ERROR - {} {}",LOG_FILE,"getDeclaracionesDeValorDocumento-2",e.getClass().getSimpleName(),e.getMessage());			
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(getError(e)).build();
		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}",LOG_FILE,"getDeclaracionesDeValorDocumento-3",e.getClass().getSimpleName(),e.getMessage());			
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(getError(e)).build();
		}

		return rb.build();
	}


	@PUT
	@Path("dosier/{codigoDosier}/confirmar-descarga")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response putDosierConfirmaDescarga(
			@NotNull @PathParam("codigoDosier") String codigoDosier,
			@NotNull @QueryParam("codigoAgencia") String codigoAgencia,
			@NotNull InputPutDosierConfirmaDescargaDTO inputData) {
		OutputPutDosierConfirmaDescargaDTO response = null;

		try {
			if (inputData.getMetadatos().getCodigoUsuario() == null || inputData.getMetadatos().getLocale() == null
					|| inputData.getDatos().getEstaDescargado() == null) {
				throw new GesaduanException(EnumGesaduanException.PARAMETROS_OBLIGATORIOS);
			}

			inputData.getDatos().setCodigoDosier(codigoDosier);
			inputData.getDatos().setCodigoAgencia(codigoAgencia);

			response = putDosierConfirmaDescargaService.updateEstadoDescarga(inputData);

			if (response.getDatos() == null) {
				OutputResponseErrorDTO error = new OutputResponseErrorDTO();
				ErrorDTO errorDesc = new ErrorDTO();
				errorDesc.setCodigo("400");
				errorDesc.setDescripcion("El Dosier no existe.");

				error.setError(errorDesc);

				return Response.status(Status.BAD_REQUEST).entity(error).build();
			} else {
				return Response.ok(response, MediaType.APPLICATION_JSON).build();
			}

		} catch (Exception e) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, LOG_FILE, "putDosierConfirmaDescarga", e.getClass().getSimpleName(), e.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(getError(e)).build();
		}
	}

	
	private OutputResponseErrorDTO getError(Exception ex) {

		OutputResponseErrorDTO error = new OutputResponseErrorDTO();
		ErrorDTO errorDesc = new ErrorDTO();
		
		List<String> partsError = Arrays.asList(ex.getMessage().replace("\\t", "").replace("\\r", "").split("\\n"));

		errorDesc.setCodigo("500");
		errorDesc.setDescripcion("Se ha producido un error en el servicio: ".concat(this.getClass().getSimpleName()));
		errorDesc.setDetalles(partsError);

		error.setError(errorDesc);

		return error;

	}

}