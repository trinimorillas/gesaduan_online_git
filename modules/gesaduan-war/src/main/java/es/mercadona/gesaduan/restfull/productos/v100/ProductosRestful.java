package es.mercadona.gesaduan.restfull.productos.v100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import es.mercadona.fwk.core.web.BoPage;
import es.mercadona.fwk.restful.service.annotate.RESTful;
import es.mercadona.gesaduan.business.productos.getproductos.v1.GetProductosService;
import es.mercadona.gesaduan.business.productos.getproductosdetalle.v1.GetProductosDetalleService;
import es.mercadona.gesaduan.business.productos.putproductos.v1.PutProductosService;
import es.mercadona.gesaduan.dto.common.error.ErrorDTO;
import es.mercadona.gesaduan.dto.common.error.OutputResponseErrorDTO;
import es.mercadona.gesaduan.dto.productos.getproductos.v1.InputGetProductoDTO;
import es.mercadona.gesaduan.dto.productos.getproductos.v1.restfull.OutputProductosDTO;
import es.mercadona.gesaduan.dto.productos.getproductosdetalle.v1.InputProductosDetalleDTO;
import es.mercadona.gesaduan.dto.productos.getproductosdetalle.v1.restfull.OutputProductosDetalleDTO;
import es.mercadona.gesaduan.dto.productos.putproductos.v1.InputMetadatosDTO;
import es.mercadona.gesaduan.dto.productos.putproductos.v1.InputPutProductosDTO;
import es.mercadona.gesaduan.dto.productos.putproductos.v1.restfull.OutputPutProductosDTO;
import es.mercadona.gesaduan.exception.EnumGesaduanException;
import es.mercadona.gesaduan.exception.GesaduanException;
import es.mercadona.gesaduan.util.ResponseUtil;


@RESTful
@Path("logistica/gestion-aduanas/v1.0")
@RequestScoped
public class ProductosRestful {
	
	@Inject
	private org.slf4j.Logger logger;
	
	@Inject
	private GetProductosService getProductosService;
	
	@Inject
	private GetProductosDetalleService getProductosDetalleService;
	
	@Inject
	private PutProductosService putProductosService;
	
	@GET
	@Path("productos")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProductos (
		@NotNull @DefaultValue("es-ES") @QueryParam("locale") String locale,
		@QueryParam("denominacion") String denominacion,
		@QueryParam("marca") String marca,
		@QueryParam("codigoTaric") String codigoTaric,
		@QueryParam("codigoRea") String codigoRea,
		@QueryParam("subvencion") String subvencion,
		@QueryParam("formatoVenta") String formatoVenta,
		@QueryParam("listoParaComer") Boolean listoParaComer,
		@QueryParam("activoAduana") String activoAduana,
		@QueryParam("codigo") String codigo,
		@QueryParam("estado") String estado,
		@DefaultValue("1") @QueryParam("paginaInicio") Integer paginaInicio,
		@DefaultValue("10") @QueryParam("paginaTamanyo") Integer paginaTamanyo,
		@DefaultValue("+codigo") @QueryParam("orden") String orden) {
		
		OutputProductosDTO response;
		
		try {
			BoPage pagination = new BoPage();
			pagination.setPage(Long.valueOf(paginaInicio));
			pagination.setLimit(Long.valueOf(paginaTamanyo));
			
			InputGetProductoDTO input = new InputGetProductoDTO();
			
			input.setLocale(locale);			
			
			if (denominacion != null && !denominacion.isEmpty())
				input.setDenominacion(denominacion);
			
			if (marca != null && !marca.isEmpty())
				input.setMarca(marca);
			
			if (codigoTaric != null && !codigoTaric.isEmpty())
				input.setCodigoTaric(codigoTaric);
			
			if (codigoRea != null && !codigoRea.isEmpty())
				input.setCodigoRea(codigoRea);
			
			if (subvencion != null && !subvencion.isEmpty())
				input.setSubvencion(subvencion);
			
			if (formatoVenta != null && !formatoVenta.isEmpty())
				input.setFormatoVenta(formatoVenta);
			
			if (listoParaComer != null) {
				if (listoParaComer)
					input.setListoParaComer(true);
				else if (!listoParaComer)
					input.setListoParaComer(false);
			}				
			
			if (codigo != null && !codigo.isEmpty()) {
				input.setCodigo(codigo);
			}
			
			if (estado != null && !estado.isEmpty()) {
				input.setEstado(estado);
			}
			
			if (activoAduana != null && !activoAduana.isEmpty()) {
				input.setActivoAduana(activoAduana);
			}
			
			input.setOrden(orden);
			
			response = getProductosService.getProductos(input, pagination);
			
		} catch(Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(getError(e)).build();
		}
		
		return Response.ok(response, MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("productos/{codigoProducto}")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProductoDetalle(
			@NotNull @DefaultValue("es-ES") @QueryParam("locale") String locale,
			@NotNull @PathParam("codigoProducto") String codigoProducto,
			@DefaultValue("+codigoPublico") @QueryParam("orden") String orden) {		
		OutputProductosDetalleDTO response = null;
		
		try {			
			InputProductosDetalleDTO input = new InputProductosDetalleDTO();
			
			input.setCodigoProducto(codigoProducto);
			input.setLocale(locale);
			input.setOrden(orden);
			
			response = getProductosDetalleService.getProductosDetalle(input);			
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(getError(e)).build();
		}
		
		return Response.ok(response, MediaType.APPLICATION_JSON).build();
	}
	
	
	@PUT
	@Path("productos/{codigoProducto}")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateProductos(
			@NotNull @PathParam("codigoProducto") Long codigoProducto,
			InputPutProductosDTO input) {
		
		OutputPutProductosDTO response;
		
		try {
			if (input.getMetadatos() == null || input.getMetadatos().getCodigoUsuario() == null) throw new GesaduanException(EnumGesaduanException.PARAMETROS_OBLIGATORIOS);
			response = putProductosService.actualizarProducto(codigoProducto, input);
		} catch(GesaduanException ex) {
			this.logger.error("({}-{}) ERROR - {} {}","ProductosRestful(GESADUAN)","updateProductos",ex.getClass().getSimpleName(),ex.getMessage());
			return Response.status(Status.BAD_REQUEST).entity(ResponseUtil.getError(ex, ex.getEnumGesaduan().getCodigo(), ex.getEnumGesaduan().getDescripcion())).build();
		} catch(Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","ProductosRestful(GESADUAN)","updateProductos",e.getClass().getSimpleName(),e.getMessage());	
			return Response.status(Status.BAD_REQUEST).entity(ResponseUtil.getError(e, EnumGesaduanException.ERROR_GENERICO.getCodigo(), e.getMessage())).build();
		}
		
		return Response.ok(response, MediaType.APPLICATION_JSON).build();
	}
	
	@SuppressWarnings("rawtypes")
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
