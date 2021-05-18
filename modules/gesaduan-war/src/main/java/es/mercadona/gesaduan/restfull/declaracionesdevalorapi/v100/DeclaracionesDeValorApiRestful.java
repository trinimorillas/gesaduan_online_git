package es.mercadona.gesaduan.restfull.declaracionesdevalorapi.v100;

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
import javax.ws.rs.POST;
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
import es.mercadona.gesaduan.business.declaracionesdevalorapi.getdvdocumento.v1.GetDVDocumentoService;
import es.mercadona.gesaduan.business.declaracionesdevalorapi.getvaluedeclarationdetail.v1.GetValueDeclarationDetailService;
import es.mercadona.gesaduan.business.declaracionesdevalorapi.getvdsumary.v1.GetVDSumaryService;
import es.mercadona.gesaduan.business.declaracionesdevalorapi.putfacturaconfirmadescarga.v1.PutFacturaConfirmaDescargaService;
import es.mercadona.gesaduan.common.Constantes;
import es.mercadona.gesaduan.dto.common.error.ErrorDTO;
import es.mercadona.gesaduan.dto.common.error.OutputResponseErrorDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getdvdocumento.v1.InputValueDeclarationDocumentDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getdvdocumento.v1.OutputDeclaracionesDeValorDocCabDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvaluedeclarationdetail.v1.InputValueDeclarationDetailDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvaluedeclarationdetail.v1.restfull.OutputValueDeclarationDetailDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvdsumary.v1.InputValueDeclarationSumaryDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvdsumary.v1.resfull.OutputValueDeclarationSumaryDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.putfacturaconfirmadescarga.v1.InputPutFacturaConfirmaDescargaDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.putfacturaconfirmadescarga.v1.restfull.OutputPutFacturaConfirmaDescargaDTO;
import es.mercadona.gesaduan.exception.EnumGesaduanException;
import es.mercadona.gesaduan.exception.GesaduanException;

@RESTful
@Path("logistica/gestion-aduanas/v1.0")
@RequestScoped
public class DeclaracionesDeValorApiRestful {

	@Inject
	private org.slf4j.Logger logger;

	@Inject
	private GetValueDeclarationDetailService getValueDeclarationDetailService;
	@Inject
	private ResourceService resourceService;
	@Inject
	private GetDVDocumentoService getDocumentoDVService;
	@Inject
	private GetVDSumaryService getVDSumaryService;
	@Inject
	private PutFacturaConfirmaDescargaService putFacturaConfirmaDescargaService;

	private static final String MIMETYPE_PDF = "application/pdf";
	private static final String MIMETYPE_CSV = "text/csv";
	private static final String FILE_BASE_NAME_PDF = "dv_";
	private static final String FILE_BASE_NAME_CSV = "csv_";

	private static final String LOG_FILE = "DeclaracionesDeValorApiRestful(GESADUAN)"; 	
	
	@POST
	@Path("valueDeclaration/sumary")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getValueDeclarationSumary(@NotNull @DefaultValue("es-ES") @QueryParam("localeId") String localeId,
			@NotNull @QueryParam("userCode") String userCode,
			@DefaultValue("1") @QueryParam("firstPage") Integer firstPage,
			@DefaultValue("10") @QueryParam("sizePage") Integer sizePage,
			@DefaultValue("-valueDeclarationNumber") @QueryParam("order") String order,
			InputValueDeclarationSumaryDTO data) {

		OutputValueDeclarationSumaryDTO response = null;

		try {
			data.setLocaleId(localeId);
			data.setUserCode(userCode);
			data.setFirstPage(firstPage);
			data.setSizePage(sizePage);
			data.setOrder(order);

			response = getVDSumaryService.getValueDeclarationList(data);

		} catch (Exception e) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG,LOG_FILE,"getDeclaracionesDeValorSumario",e.getClass().getSimpleName(),e.getMessage());			
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(getError(e)).build();
		}

		return Response.ok(response, MediaType.APPLICATION_JSON).build();
	}

	@GET
	@Path("valueDeclaration/{valueDeclarationCode}")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDeclaracionDeValorDetalle(@NotNull @PathParam("valueDeclarationCode") String valueDeclaration,
			@NotNull @DefaultValue("es_ES") @QueryParam("localeId") String localeId,
			@NotNull @QueryParam("userCode") String userCode) {
		OutputValueDeclarationDetailDTO result;

		try {
			InputValueDeclarationDetailDTO input = new InputValueDeclarationDetailDTO();
			input.setValueDeclarationCode(valueDeclaration);
			input.setLocaleId(localeId);

			result = getValueDeclarationDetailService.getValueDeclarationDetail(input);

			if (result == null) {
				OutputResponseErrorDTO error = new OutputResponseErrorDTO();
				ErrorDTO errorDesc = new ErrorDTO();
				errorDesc.setCodigo("400");
				errorDesc.setDescripcion("La Declaración de Valor no existe.");
				error.setError(errorDesc);
				
				return Response.status(Status.BAD_REQUEST).entity(error).build();
			} else {
				return Response.ok(result, MediaType.APPLICATION_JSON).build();
			}

		} catch (Exception e) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG,LOG_FILE,"getDeclaracionDeValorDetalle",e.getClass().getSimpleName(),e.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(getError(e)).build();
		}

	}


	@GET
	@Path("valueDeclaration/{valueDeclarationId}/document")
	@Consumes(MediaType.WILDCARD)
	@Produces({ MIMETYPE_PDF, MIMETYPE_CSV, MediaType.APPLICATION_JSON })
	public Response getDeclaracionesDeValorDocumento(@NotNull @PathParam("valueDeclarationId") String valueDeclarationId,
			@Context HttpServletRequest request, 
			@DefaultValue("es-ES") @QueryParam("locale") String locale,
			@NotNull @QueryParam("userId") String userId,
			@NotNull @DefaultValue("pdf") @QueryParam("documentType") String documentType) {

		ResponseBuilder rb = null;

		try {
			String fileExtension = ".";
			String mimeType = null;
			String fileBaseName = null;
			
			String[] valueDeclarationIdArr = valueDeclarationId.split("-");
			
			String valueDeclarationNumber = valueDeclarationIdArr[0];
			String valueDeclarationYear = valueDeclarationIdArr[1];				
			String valueDeclarationVersion = valueDeclarationIdArr[2];			

			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
			Date date = new Date(System.currentTimeMillis());

			// Prepara datos basicos del fichero (extension,mimetype,nombre)
			if (documentType.equalsIgnoreCase("pdf")) {
				fileExtension += documentType.toLowerCase();
				mimeType = MIMETYPE_PDF;
				fileBaseName = FILE_BASE_NAME_PDF.concat(valueDeclarationNumber).concat("_")
						.concat(valueDeclarationYear).concat("_").concat(valueDeclarationVersion).concat("_")
						.concat(formatter.format(date));
			} else if (documentType.equalsIgnoreCase("csv")) {
				fileExtension += documentType.toLowerCase();
				mimeType = MIMETYPE_CSV;
				fileBaseName = FILE_BASE_NAME_CSV.concat(valueDeclarationNumber).concat("_")
						.concat(valueDeclarationYear).concat("_").concat(valueDeclarationVersion).concat("_")
						.concat(formatter.format(date));
			}

			// Prepara llamada al servicio que va a montar el fichero
			InputValueDeclarationDocumentDTO inputVDDocumentDTO = new InputValueDeclarationDocumentDTO();

			inputVDDocumentDTO.setValueDeclarationNumber(valueDeclarationNumber);
			inputVDDocumentDTO.setValueDeclarationYear(valueDeclarationYear);
			inputVDDocumentDTO.setValueDeclarationVersion(valueDeclarationVersion);
			inputVDDocumentDTO.setDocumentType(documentType);

			OutputDeclaracionesDeValorDocCabDTO outputDVDocumentoDTO = null;

			outputDVDocumentoDTO = getDocumentoDVService.preparaDocumento(inputVDDocumentDTO);

			// devuelve el fichero y controla los posibles errores
			if (outputDVDocumentoDTO != null) {

				byte[] file = null;

				if (documentType.equalsIgnoreCase("pdf")) {
					file = outputDVDocumentoDTO.getFicheroPDF();
				} else if (documentType.equalsIgnoreCase("csv")) {
					file = outputDVDocumentoDTO.getFicheroCSV();
				} else {
					throw new WebApplicationException(Status.BAD_REQUEST);
				}

				if (file != null) {

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
					errorDesc.setDescripcion(
							"Formato de envío inválido o error funcional en: ".concat(this.getClass().getSimpleName()));
					errorDesc.setDetalles(partsError);

					error.setError(errorDesc);

					return Response.status(Status.BAD_REQUEST).entity(error).build();
				}

			} else {

				OutputResponseErrorDTO error = new OutputResponseErrorDTO();
				ErrorDTO errorDesc = new ErrorDTO();
				List<String> partsError = new ArrayList<>();

				partsError.add("Recurso no encontrado");

				errorDesc.setCodigo("400");
				errorDesc.setDescripcion(
						"Formato de envío inválido o error funcional en: ".concat(this.getClass().getSimpleName()));
				errorDesc.setDetalles(partsError);

				error.setError(errorDesc);

				return Response.status(Status.BAD_REQUEST).entity(error).build();
			}

		} catch (ResourceNotFoundException e) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG,LOG_FILE,"getDeclaracionesDeValorDocumento-1",e.getClass().getSimpleName(),e.getMessage());			
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(getError(e)).build();
		} catch (IllegalResourceNameException e) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG,LOG_FILE,"getDeclaracionesDeValorDocumento-2",e.getClass().getSimpleName(),e.getMessage());			
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(getError(e)).build();
		} catch (Exception e) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG,LOG_FILE,"getDeclaracionesDeValorDocumento-3",e.getClass().getSimpleName(),e.getMessage());			
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(getError(e)).build();
		}

		return rb.build();
	}

	@PUT
	@Path("valueDeclaration/{codigoFactura}/confirmar-descarga")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response putFacturaConfirmaDescarga(@NotNull @PathParam("codigoFactura") String codigoFactura,
			@NotNull @QueryParam("codigoProveedor") String codigoProveedor,
			@NotNull InputPutFacturaConfirmaDescargaDTO inputData) {
		OutputPutFacturaConfirmaDescargaDTO response = null;

		try {
			if (inputData.getMetadatos().getCodigoUsuario() == null || inputData.getMetadatos().getLocale() == null
					|| inputData.getDatos().getEstaDescargado() == null) {
				throw new GesaduanException(EnumGesaduanException.PARAMETROS_OBLIGATORIOS);
			}

			inputData.getDatos().setCodigoFactura(codigoFactura);
			inputData.getDatos().setCodigoProveedor(codigoProveedor);

			response = putFacturaConfirmaDescargaService.updateEstadoDescarga(inputData);

			if (response.getDatos() == null) {
				OutputResponseErrorDTO error = new OutputResponseErrorDTO();
				ErrorDTO errorDesc = new ErrorDTO();
				errorDesc.setCodigo("400");
				errorDesc.setDescripcion("La Declaración de Valor no existe.");

				error.setError(errorDesc);

				return Response.status(Status.BAD_REQUEST).entity(error).build();
			} else {
				return Response.ok(response, MediaType.APPLICATION_JSON).build();
			}

		} catch (Exception e) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG,LOG_FILE,"putDeclaracionesDeValorConfirmaDescarga",e.getClass().getSimpleName(),e.getMessage());	
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