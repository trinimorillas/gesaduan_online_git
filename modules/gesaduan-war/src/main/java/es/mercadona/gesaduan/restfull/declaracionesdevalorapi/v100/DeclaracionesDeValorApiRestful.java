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
import es.mercadona.fwk.core.web.BoPage;
import es.mercadona.fwk.restful.service.annotate.RESTful;
import es.mercadona.gesaduan.business.declaracionesdevalor.getdvdetalle.v1.GetDVDetalleService;
import es.mercadona.gesaduan.business.declaracionesdevalor.getdvsumario.v1.GetDVSumarioService;
import es.mercadona.gesaduan.business.declaracionesdevalor.postdv.v1.PostDVService;
import es.mercadona.gesaduan.business.declaracionesdevalor.putdvestadodescarga.v1.PutDVEstadoDescargaService;
import es.mercadona.gesaduan.business.declaracionesdevalorapi.getdvdocumento.v1.GetDVDocumentoService;
import es.mercadona.gesaduan.business.declaracionesdevalorapi.putfacturaconfirmadescarga.v1.PutFacturaConfirmaDescargaService;
import es.mercadona.gesaduan.dto.common.error.ErrorDTO;
import es.mercadona.gesaduan.dto.common.error.OutputResponseErrorDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.getdvdetalle.v1.InputDeclaracionesDeValorDetalleDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.getdvdetalle.v1.restfull.OutputDeclaracionesDeValorDetalleDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.getdvsumario.v1.InputDeclaracionesDeValorDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.getdvsumario.v1.restfull.OutputDeclaracionesDeValorDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getdvdocumento.v1.InputDeclaracionesDeValorDocumentoDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getdvdocumento.v1.OutputDeclaracionesDeValorDocCabDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.putfacturaconfirmadescarga.v1.InputPutFacturaConfirmaDescargaDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.putfacturaconfirmadescarga.v1.restfull.OutputPutFacturaConfirmaDescargaDTO;
import es.mercadona.gesaduan.exception.EnumGesaduanException;
import es.mercadona.gesaduan.exception.GesaduanException;
import es.mercadona.gesaduan.common.Constantes;

@RESTful
@Path("logistica/gestion-aduanas/v2.0")
@RequestScoped
public class DeclaracionesDeValorApiRestful {

	@Inject
	private org.slf4j.Logger logger;

	@Inject
	private GetDVDetalleService getDVDetalleService;
	@Inject
	private ResourceService resourceService;
	@Inject
	private GetDVDocumentoService getDocumentoDVService;
	@Inject
	private GetDVSumarioService getDVSumarioService;
	@Inject
	private PutDVEstadoDescargaService putDVEstadoDescargaService;
	@Inject
	private PutFacturaConfirmaDescargaService putFacturaConfirmaDescargaService;
	@Inject
	private PostDVService postDVService;

	private static final String MIMETYPE_PDF = "application/pdf";
	private static final String MIMETYPE_CSV = "text/csv";
	private static final String FILE_BASE_NAME_PDF = "dv_";
	private static final String FILE_BASE_NAME_CSV = "csv_";

	private static final String LOG_FILE = "DeclaracionesDeValorRestful(GESADUAN)";

	@GET
	@Path("declaraciones-valor/sumario")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDeclaracionesDeValorSumario(@NotNull @DefaultValue("es-ES") @QueryParam("locale") String locale,
			@NotNull @QueryParam("codigoUsuario") String codigoUsuario,
			@QueryParam("codigoAgencia") String codigoAgencia,
			@DefaultValue("1") @QueryParam("paginaInicio") Integer paginaInicio, @QueryParam("anyo") Integer anyo,
			@QueryParam("numeroDeclaracion") Integer numeroDeclaracion, @QueryParam("codigoPedido") String codigoPedido,
			@QueryParam("codigoAlmacen") String codigoAlmacen, @QueryParam("nombreAlmacen") String nombreAlmacen,
			@DefaultValue("10") @QueryParam("paginaTamanyo") Integer paginaTamanyo,
			@DefaultValue("-numeroDeclaracion") @QueryParam("orden") String orden,
			@QueryParam("codigoProveedor") Integer codigoProveedor,
			@QueryParam("nombreProveedor") String nombreProveedor,
			@DefaultValue("T") @QueryParam("tipoDeclaracion") String tipoDeclaracion,
			@DefaultValue("TD") @QueryParam("estadoDeclaracion") String estadoDeclaracion,
			@DefaultValue("T") @QueryParam("estadoDescarga") String estadoDescarga,
			@QueryParam("tipoFechaFiltro") String tipoFechaFiltro, @QueryParam("fechaDesde") String fechaDesde,
			@QueryParam("fechaHasta") String fechaHasta) {

		OutputDeclaracionesDeValorDTO response = null;

		try {
			InputDeclaracionesDeValorDTO inputParams = new InputDeclaracionesDeValorDTO();

			inputParams.setLocale(locale);
			inputParams.setCodigoUsuario(codigoUsuario);

			if (codigoAgencia != null) {
				inputParams.setCodigoAgencia(codigoAgencia);
			}

			if (anyo != null) {
				inputParams.setAnyo(anyo);
			}
			if (numeroDeclaracion != null) {
				inputParams.setNumeroDeclaracion(numeroDeclaracion);
			}

			if (codigoPedido != null) {
				inputParams.setCodigoPedido(codigoPedido);
			}

			if (codigoAlmacen != null) {
				inputParams.setCodigoAlmacen(codigoAlmacen);
			}

			if (nombreAlmacen != null) {
				inputParams.setNombreAlmacen(nombreAlmacen);
			}

			if (orden != null) {
				inputParams.setOrden(orden);
			}

			if (codigoProveedor != null) {
				inputParams.setCodigoProveedor(Integer.toString(codigoProveedor));
			}

			if (nombreProveedor != null) {
				inputParams.setNombreProveedor(nombreProveedor);
			}

			inputParams.setTipoDeclaracion(tipoDeclaracion);
			inputParams.setEstadoDeclaracion(estadoDeclaracion);
			inputParams.setEstadoDescarga(estadoDescarga);

			if (tipoFechaFiltro != null) {

				inputParams.setTipoFechaFiltro(tipoFechaFiltro);
				SimpleDateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");

				if (fechaDesde != null) {

					Date fDesde = inFormat.parse(fechaDesde);
					inputParams.setFechaDesde(fDesde);
				}

				if (fechaHasta != null) {
					Date fHasta = inFormat.parse(fechaHasta);
					inputParams.setFechaHasta(fHasta);

				}

			}

			BoPage paginacion = new BoPage();

			if (paginaInicio != null) {
				paginacion.setPage(Long.valueOf(paginaInicio));
			}

			if (paginaTamanyo != null) {
				paginacion.setLimit(Long.valueOf(paginaTamanyo));
			}

			response = getDVSumarioService.getDeclaracionesDeValorList(inputParams, paginacion);

		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}", LOG_FILE, "getDeclaracionesDeValorSumario",
					e.getClass().getSimpleName(), e.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(getError(e)).build();
		}

		return Response.ok(response, MediaType.APPLICATION_JSON).build();
	}

	@GET
	@Path("declaraciones-valor/{codigoDeclaracion}-{anyo}-{version}")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDeclaracionDeValorDetalle(@NotNull @PathParam("codigoDeclaracion") Integer codigoDeclaracion,
			@NotNull @PathParam("anyo") Integer anyo, @NotNull @PathParam("version") Integer version,
			@NotNull @DefaultValue("es_ES") @QueryParam("locale") String locale) {

		OutputDeclaracionesDeValorDetalleDTO declaracionesPorCodigo;

		try {
			InputDeclaracionesDeValorDetalleDTO input = new InputDeclaracionesDeValorDetalleDTO();

			input.setCodigoDeclaracion(codigoDeclaracion);
			input.setAnyo(anyo);
			input.setVersion(version);

			declaracionesPorCodigo = getDVDetalleService.getDeclaracionesDeValorPorCodigoList(input, null);

			if (declaracionesPorCodigo == null) {

				OutputResponseErrorDTO error = new OutputResponseErrorDTO();
				ErrorDTO errorDesc = new ErrorDTO();
				errorDesc.setCodigo("400");
				errorDesc.setDescripcion("La Declaración de Valor no existe.");

				error.setError(errorDesc);

				return Response.status(Status.BAD_REQUEST).entity(error).build();
			} else {
				return Response.ok(declaracionesPorCodigo, MediaType.APPLICATION_JSON).build();
			}

		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}", LOG_FILE, "getDeclaracionDeValorDetalle",
					e.getClass().getSimpleName(), e.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(getError(e)).build();
		}

	}

	@GET
	@Path("declaraciones-valor/{codigoDeclaracion}-{anyo}-{version}/documento")
	@Consumes(MediaType.WILDCARD)
	@Produces({ MIMETYPE_PDF, MIMETYPE_CSV, MediaType.APPLICATION_JSON })
	public Response getDeclaracionesDeValorDocumento(@NotNull @PathParam("codigoDeclaracion") Integer codigoDeclaracion,
			@NotNull @PathParam("anyo") Integer anyo, @NotNull @PathParam("version") Integer version,
			@Context HttpServletRequest request, @DefaultValue("es-ES") @QueryParam("locale") String locale,
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
				fileBaseName = FILE_BASE_NAME_PDF.concat(codigoDeclaracion.toString()).concat("_")
						.concat(anyo.toString()).concat("_").concat(version.toString()).concat("_")
						.concat(formatter.format(date));
			} else if (tipoDocumento.equalsIgnoreCase("csv")) {
				fileExtension += tipoDocumento.toLowerCase();
				mimeType = MIMETYPE_CSV;
				fileBaseName = FILE_BASE_NAME_CSV.concat(codigoDeclaracion.toString()).concat("_")
						.concat(anyo.toString()).concat("_").concat(version.toString()).concat("_")
						.concat(formatter.format(date));
			}

			// Prepara llamada al servicio que va a montar el fichero
			InputDeclaracionesDeValorDocumentoDTO inputDVDocumentoDTO = new InputDeclaracionesDeValorDocumentoDTO();

			inputDVDocumentoDTO.setCodigoDeclaracion(codigoDeclaracion);
			inputDVDocumentoDTO.setAnyoDeclaracion(anyo);
			inputDVDocumentoDTO.setVersionDeclaracion(version);
			inputDVDocumentoDTO.setTipoDocumento(tipoDocumento);

			OutputDeclaracionesDeValorDocCabDTO outputDVDocumentoDTO = null;

			outputDVDocumentoDTO = getDocumentoDVService.preparaDocumento(inputDVDocumentoDTO);

			// devuelve el fichero y controla los posibles errores
			if (outputDVDocumentoDTO != null) {

				byte[] file = null;

				if (tipoDocumento.equalsIgnoreCase("pdf")) {
					file = outputDVDocumentoDTO.getFicheroPDF();
				} else if (tipoDocumento.equalsIgnoreCase("csv")) {
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
			this.logger.error("({}-{}) ERROR - {} {}", LOG_FILE, "getDeclaracionesDeValorDocumento-1",
					e.getClass().getSimpleName(), e.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(getError(e)).build();
		} catch (IllegalResourceNameException e) {
			this.logger.error("({}-{}) ERROR - {} {}", LOG_FILE, "getDeclaracionesDeValorDocumento-2",
					e.getClass().getSimpleName(), e.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(getError(e)).build();
		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}", LOG_FILE, "getDeclaracionesDeValorDocumento-3",
					e.getClass().getSimpleName(), e.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(getError(e)).build();
		}

		return rb.build();
	}

	@PUT
	@Path("factura/{codigoFactura}/confirmar-descarga")
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
			this.logger.error(Constantes.FORMATO_ERROR_LOG, LOG_FILE, "putFacturaConfirmaDescarga", e.getClass().getSimpleName(), e.getMessage());
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