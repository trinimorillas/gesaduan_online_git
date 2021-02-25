package es.mercadona.gesaduan.restfull.cargas.v100;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
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
import es.mercadona.gesaduan.business.cargas.cambiarestado.v1.CambiarEstadoCargaService;
import es.mercadona.gesaduan.business.cargas.deletecargas.v1.DeleteCargaService;
import es.mercadona.gesaduan.business.cargas.deleterelacioncargapedido.v1.DeleteRelacionCargaPedidoService;
import es.mercadona.gesaduan.business.cargas.getcargas.v1.GetCargasService;
import es.mercadona.gesaduan.business.cargas.getpedidoscarga.v1.GetPedidosCargaService;
import es.mercadona.gesaduan.business.cargas.putcarga.v1.PutCargaService;
import es.mercadona.gesaduan.business.cargas.putrelacioncargapedido.v1.PutRelacionCargaPedidoService;
import es.mercadona.gesaduan.business.cargas.validarpedido.v1.ValidarPedidoService;
import es.mercadona.gesaduan.dto.cargas.cambiarestado.v1.CambiarEstadoCargaDTO;
import es.mercadona.gesaduan.dto.cargas.cambiarestado.v1.InputDatosCambiarEstadoCargaDTO;
import es.mercadona.gesaduan.dto.cargas.deletecargas.v1.EliminarCargaDTO;
import es.mercadona.gesaduan.dto.cargas.deletecargas.v1.InputDatosEliminarCargaDTO;
import es.mercadona.gesaduan.dto.cargas.deleterelacioncargapedido.v1.InputDatosEliminarRelacionCargaPedidoDTO;
import es.mercadona.gesaduan.dto.cargas.deleterelacioncargapedido.v1.PedidoEliminarRelacionCargaPedidoDTO;
import es.mercadona.gesaduan.dto.cargas.getcargas.v1.InputDatosCargasDTO;
import es.mercadona.gesaduan.dto.cargas.getcargas.v1.restfull.OutputCargasDTO;
import es.mercadona.gesaduan.dto.cargas.getpedidoscarga.v1.InputDatosPedidosCargaDTO;
import es.mercadona.gesaduan.dto.cargas.getpedidoscarga.v1.InputPedidosCargaDTO;
import es.mercadona.gesaduan.dto.cargas.getpedidoscarga.v1.restfull.OutputPedidosCargaDTO;
import es.mercadona.gesaduan.dto.cargas.putcargas.v1.CargaDTO;
import es.mercadona.gesaduan.dto.cargas.putcargas.v1.InputDatosPutCargaDTO;
import es.mercadona.gesaduan.dto.cargas.putcargas.v1.restfull.OutputPutCargaDTO;
import es.mercadona.gesaduan.dto.cargas.putrelacioncargapedido.v1.InputDatosCrearRelacionCargaPedidoDTO;
import es.mercadona.gesaduan.dto.cargas.putrelacioncargapedido.v1.PedidoDTO;
import es.mercadona.gesaduan.dto.cargas.validapedido.v1.InputDatosValidarPedidoDTO;
import es.mercadona.gesaduan.dto.cargas.validapedido.v1.ValidarPedidoDTO;
import es.mercadona.gesaduan.dto.common.error.ErrorDTO;
import es.mercadona.gesaduan.dto.common.error.OutputResponseErrorDTO;
import es.mercadona.gesaduan.exception.EnumGesaduanException;
import es.mercadona.gesaduan.exception.GesaduanException;
import es.mercadona.gesaduan.util.ResponseUtil;

@RESTful
@Path("logistica/gestion-aduanas/v1.0")
@RequestScoped
public class CargasRestful {
	
	@Inject
	private org.slf4j.Logger logger;	
	
	@Inject
	private GetCargasService getCargasService;
	
	@Inject
	private GetPedidosCargaService getPedidosCargaService;
	
	@Inject
	private PutCargaService putCargaService;
	
	@Inject
	private PutRelacionCargaPedidoService crearRelacionCargaPedidoService;
	
	@Inject
	private DeleteRelacionCargaPedidoService eliminarRelacionCargaPedidoService;
	
	@Inject
	private CambiarEstadoCargaService cambiarEstadoCargaService;
	
	@Inject
	private DeleteCargaService eliminarCargaService;
	
	@Inject
	private ValidarPedidoService validarPedidoService;	
	
	@POST
	@Path("carga/sumario")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCargas(
		@QueryParam("codigoCarga") String codigoCarga,
		@QueryParam("codigoPedido") String codigoPedido,
		@QueryParam("codigoProveedor") String codigoProveedor,
		@QueryParam("codigoTipoCarga") Integer codigoTipoCarga,
		@QueryParam("codigoSuministro") Integer codigoSuministro,
		@QueryParam("fechaServicio") String fechaServicio,
		@QueryParam("fechaEntrega") String fechaEntrega,
		@QueryParam("fechaIntroduccion") String fechaIntroduccion,
		@QueryParam("codigoBloqueOrigen") Integer codigoBloqueOrigen,
		@QueryParam("codigoCentroOrigen") String codigoCentroOrigen,
		@QueryParam("codigoCentroDestino") String codigoCentroDestino,
		@QueryParam("usuarioCreacion") String usuarioCreacion,
		@QueryParam("mcaPedidosSinValidar") String mcaPedidosSinValidar,
		@QueryParam("aplicacionOrigen") String aplicacionOrigen,		
		@DefaultValue("1") @QueryParam("paginaInicio") Integer paginaInicio,
		@DefaultValue("10") @QueryParam("paginaTamanyo") Integer paginaTamanyo,
		@DefaultValue("-codigoCarga") @QueryParam("orden") String orden,
		InputDatosCargasDTO datos) {
		
		OutputCargasDTO response;
		
		try {			
			BoPage pagination = new BoPage();
			pagination.setPage(Long.valueOf(paginaInicio));
			pagination.setLimit(Long.valueOf(paginaTamanyo));
			
			if (codigoCarga != null) datos.getDatos().setCodigoCarga(codigoCarga);			
			if (codigoPedido != null) datos.getDatos().setCodigoPedido(codigoPedido);			
			if (codigoProveedor != null) datos.getDatos().setCodigoProveedor(codigoProveedor);			
			if (codigoTipoCarga != null) datos.getDatos().setCodigoTipoCarga(codigoTipoCarga);			
			if (codigoSuministro != null) datos.getDatos().setCodigoSuministro(codigoSuministro);
			if (fechaServicio != null) datos.getDatos().setFechaServicio(fechaServicio);
			if (fechaEntrega != null) datos.getDatos().setFechaEntrega(fechaEntrega);
			if (fechaIntroduccion != null) datos.getDatos().setFechaIntroduccion(fechaIntroduccion);
			if (codigoBloqueOrigen != null) datos.getDatos().setCodigoBloqueOrigen(codigoBloqueOrigen);
			if (codigoCentroOrigen != null) datos.getDatos().setCodigoCentroOrigen(codigoCentroOrigen);
			if (codigoCentroDestino != null) datos.getDatos().setCodigoCentroDestino(codigoCentroDestino);
			if (usuarioCreacion != null) datos.getDatos().setUsuarioCreacion(usuarioCreacion);
			if (mcaPedidosSinValidar != null) datos.getDatos().setMcaPedidosSinValidar(mcaPedidosSinValidar);
			if (aplicacionOrigen != null) datos.getDatos().setAplicacionOrigen(aplicacionOrigen);			
			datos.getDatos().setOrden(orden);
			
			response = getCargasService.listarCargas(datos, pagination);
			
		} catch(Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","CargasRestful(GESADUAN)","getCargas",e.getClass().getSimpleName(),e.getMessage());	
			return Response.status(Status.BAD_REQUEST).entity(ResponseUtil.getError(e, EnumGesaduanException.ERROR_GENERICO.getCodigo(), e.toString())).build();
		}
		
		return Response.ok(response, MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("carga/pedidos")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPedidosCarga(
		@QueryParam("codigoCarga") String codigoCarga,
		@QueryParam("codigoCentroOrigen") String codigoCentroOrigen,
		@QueryParam("mcaPedidoValidado") String mcaPedidoValidado,
		@DefaultValue("+codigoPedido") @QueryParam("orden") String orden) {
		
		OutputPedidosCargaDTO response;
		
		try {
			if (codigoCarga == null || codigoCentroOrigen == null)
				throw new GesaduanException(EnumGesaduanException.PARAMETROS_OBLIGATORIOS);
			
			InputDatosPedidosCargaDTO datos = new InputDatosPedidosCargaDTO();
			InputPedidosCargaDTO input = new InputPedidosCargaDTO();
			input.setCodigoCarga(codigoCarga);
			input.setCodigoCentroOrigen(codigoCentroOrigen);
			if (mcaPedidoValidado != null) input.setMcaPedidoValidado(mcaPedidoValidado);
			input.setOrden(orden);
			datos.setDatos(input);
			response = getPedidosCargaService.listarPedidosCarga(datos);	
		} catch(GesaduanException ex) {
			this.logger.error("({}-{}) ERROR - {} {}","CargasRestful(GESADUAN)","getPedidosCarga",ex.getClass().getSimpleName(),ex.getMessage());	
			return Response.status(Status.BAD_REQUEST).entity(ResponseUtil.getError(ex, ex.getEnumGesaduan().getCodigo(), ex.getEnumGesaduan().getDescripcion())).build();
		} catch(Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","CargasRestful(GESADUAN)","getPedidosCarga",e.getClass().getSimpleName(),e.getMessage());	
			return Response.status(Status.BAD_REQUEST).entity(ResponseUtil.getError(e, EnumGesaduanException.ERROR_GENERICO.getCodigo(), e.getMessage())).build();
		}
		
		return Response.ok(response, MediaType.APPLICATION_JSON).build();
	}
	
	@PUT
	@Path("carga/")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response crearCarga(InputDatosPutCargaDTO datos) {	
		OutputPutCargaDTO response = null;

		try {
			List<CargaDTO> cargas = datos.getDatos().getCarga();
			if (datos.getMetadatos().getCodigoUsuario() == null || cargas.size() == 0) throw new GesaduanException(EnumGesaduanException.PARAMETROS_OBLIGATORIOS);
			else {				
				int codigoVacio = 0, codigoNoVacio = 0;
				for (CargaDTO carga : cargas) {
					//
					if (carga.getCodigoAlmacenOrigen() == null || 
						carga.getCodigoCentroDestino() == null || 
						/*carga.getCodigoCategoria() == null || carga.getCodigoProveedor() == null ||*/
						/* carga.getCodigoSuministro() == null || */ 
						/*carga.getCodigoTipoCarga() == null ||*/
						carga.getFechaEntrega() == null || 
						carga.getFechaServicio() == null ||
						/*carga.getMarcaLpC() == null ||*/ 
						(carga.getNumeroDivisiones() == null && carga.getCodigoCarga() != null) /*||*/
						/* carga.getNumeroHuecosRestantes() == null ||*/ 
						/*carga.getNumeroPesoRestante() == null ||*/
						/* carga.getNumeroTotalHuecos() == null || */	
						/* carga.getNumeroTotalPeso() == null */
						)
						throw new GesaduanException(EnumGesaduanException.PARAMETROS_OBLIGATORIOS);
					else if (carga.getCodigoCarga() == null || carga.getCodigoCarga() == "") codigoVacio++;
					else if (carga.getCodigoCarga() != null && carga.getCodigoCarga() != "") codigoNoVacio++;
				}				
				if (codigoVacio != cargas.size() && codigoNoVacio != cargas.size()) throw new GesaduanException(EnumGesaduanException.CODIGOS_CARGAS_DISTINTOS);				
			}
			response = putCargaService.putCarga(datos);			
		} catch(GesaduanException ex) {
			this.logger.error("({}-{}) ERROR - {} {}","CargasRestful(GESADUAN)","crearCarga",ex.getClass().getSimpleName(),ex.getMessage());	
			return Response.status(Status.BAD_REQUEST).entity(ResponseUtil.getError(ex, ex.getEnumGesaduan().getCodigo(), ex.getEnumGesaduan().getDescripcion())).build();
		} catch(Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","CargasRestful(GESADUAN)","crearCarga",e.getClass().getSimpleName(),e.getMessage());	
			return Response.status(Status.BAD_REQUEST).entity(ResponseUtil.getError(e, EnumGesaduanException.ERROR_GENERICO.getCodigo(), e.getMessage())).build();
		}
		
		return Response.status(Status.CREATED).entity(response).build();
	}
	
	@PUT
	@Path("carga/cambio-estado")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response cambiarEstadoCarga(InputDatosCambiarEstadoCargaDTO datos) {	
		try {
			if (datos.getDatos().getCarga().size() == 0) throw new GesaduanException(EnumGesaduanException.PARAMETROS_OBLIGATORIOS);
			else {
				for (CambiarEstadoCargaDTO carga : datos.getDatos().getCarga()) {
					if (carga.getCodigoAlmacenOrigen() == null || carga.getCodigoCarga() == null || carga.getCodigoEstado() == null)
						throw new GesaduanException(EnumGesaduanException.PARAMETROS_OBLIGATORIOS);
				}					
			}
			cambiarEstadoCargaService.cambiarEstadoCarga(datos);
		} catch(GesaduanException ex) {
			this.logger.error("({}-{}) ERROR - {} {}","CargasRestful(GESADUAN)","cambiarEstadoCarga",ex.getClass().getSimpleName(),ex.getMessage());	
			return Response.status(Status.BAD_REQUEST).entity(ResponseUtil.getError(ex, ex.getEnumGesaduan().getCodigo(), ex.getEnumGesaduan().getDescripcion())).build();
		} catch(Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","CargasRestful(GESADUAN)","cambiarEstadoCarga",e.getClass().getSimpleName(),e.getMessage());	
			return Response.status(Status.BAD_REQUEST).entity(ResponseUtil.getError(e, EnumGesaduanException.ERROR_GENERICO.getCodigo(), e.getMessage())).build();
		}
		
		return Response.status(Status.NO_CONTENT).build();
	}
	
	@PUT
	@Path("carga/{codigoCarga}-{codigoAlmacenOrigen}/relaciones")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response crearRelacionCargaPedido(@PathParam("codigoCarga") String codigoCarga,
			@PathParam("codigoAlmacenOrigen") String codigoAlmacenOrigen, InputDatosCrearRelacionCargaPedidoDTO datos) {	
		try {
			if (datos.getDatos().getPedido().size() == 0 || datos.getMetadatos().getCodigoUsuario() == null) throw new GesaduanException(EnumGesaduanException.PARAMETROS_OBLIGATORIOS);
			else {
				for (PedidoDTO pedido : datos.getDatos().getPedido()) {
					if (pedido.getCodigoDivision() == null || pedido.getCodigoPedido() == null)
						throw new GesaduanException(EnumGesaduanException.PARAMETROS_OBLIGATORIOS);
				}					
			}
			datos.getDatos().setCodigoCarga(codigoCarga);
			datos.getDatos().setCodigoAlmacenOrigen(codigoAlmacenOrigen);
			crearRelacionCargaPedidoService.crearRelacionCargaPedido(datos);
		} catch(GesaduanException ex) {
			this.logger.error("({}-{}) ERROR - {} {}","CargasRestful(GESADUAN)","crearRelacionCargaPedido",ex.getClass().getSimpleName(),ex.getMessage());	
			return Response.status(Status.BAD_REQUEST).entity(ResponseUtil.getError(ex, ex.getEnumGesaduan().getCodigo(), ex.getEnumGesaduan().getDescripcion())).build();
		} catch(Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","CargasRestful(GESADUAN)","crearRelacionCargaPedido",e.getClass().getSimpleName(),e.getMessage());	
			return Response.status(Status.BAD_REQUEST).entity(ResponseUtil.getError(e, EnumGesaduanException.ERROR_GENERICO.getCodigo(), e.toString())).build();
		}
		
		return Response.status(Status.NO_CONTENT).build();
	}
	
	@POST
	@Path("carga/{codigoCarga}-{codigoAlmacenOrigen}/relaciones")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response eliminarRelacionCargaPedido(@PathParam("codigoCarga") String codigoCarga,
			@PathParam("codigoAlmacenOrigen") String codigoAlmacenOrigen, InputDatosEliminarRelacionCargaPedidoDTO datos) {	
		try {
			if (datos.getDatos().getPedido().size() == 0) throw new GesaduanException(EnumGesaduanException.PARAMETROS_OBLIGATORIOS);
			else {
				for (PedidoEliminarRelacionCargaPedidoDTO pedido : datos.getDatos().getPedido()) {
					if (pedido.getCodigoPedido() == null) throw new GesaduanException(EnumGesaduanException.PARAMETROS_OBLIGATORIOS);
				}					
			}
			datos.getDatos().setCodigoCarga(codigoCarga);
			datos.getDatos().setCodigoAlmacenOrigen(codigoAlmacenOrigen);
			eliminarRelacionCargaPedidoService.eliminarRelacionCargaPedido(datos);
		} catch(GesaduanException ex) {
			this.logger.error("({}-{}) ERROR - {} {}","CargasRestful(GESADUAN)","eliminarRelacionCargaPedido",ex.getClass().getSimpleName(),ex.getMessage());	
			return Response.status(Status.BAD_REQUEST).entity(ResponseUtil.getError(ex, ex.getEnumGesaduan().getCodigo(), ex.getEnumGesaduan().getDescripcion())).build();
		} catch(Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","CargasRestful(GESADUAN)","eliminarRelacionCargaPedido",e.getClass().getSimpleName(),e.getMessage());	
			return Response.status(Status.BAD_REQUEST).entity(ResponseUtil.getError(e, EnumGesaduanException.ERROR_GENERICO.getCodigo(), e.getMessage())).build();
		}
		
		return Response.status(Status.NO_CONTENT).build();
	}
	
	@POST
	@Path("carga/")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response eliminarCarga(InputDatosEliminarCargaDTO datos) {	
		try {
			if (datos.getDatos().getCarga().size() == 0 || datos.getMetadatos().getCodigoUsuario() == null) throw new GesaduanException(EnumGesaduanException.PARAMETROS_OBLIGATORIOS);
			else {
				for (EliminarCargaDTO carga : datos.getDatos().getCarga()) {
					if (carga.getCodigoCarga() == null || carga.getCodigoAlmacenOrigen() == null) throw new GesaduanException(EnumGesaduanException.PARAMETROS_OBLIGATORIOS);
				}					
			}
			eliminarCargaService.eliminarCarga(datos);
		} catch(GesaduanException ex) {
			this.logger.error("({}-{}) ERROR - {} {}","CargasRestful(GESADUAN)","eliminarCarga",ex.getClass().getSimpleName(),ex.getMessage());	
			return Response.status(Status.BAD_REQUEST).entity(ResponseUtil.getError(ex, ex.getEnumGesaduan().getCodigo(), ex.getEnumGesaduan().getDescripcion())).build();
		} catch(Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","CargasRestful(GESADUAN)","eliminarCarga",e.getClass().getSimpleName(),e.getMessage());	
			return Response.status(Status.BAD_REQUEST).entity(ResponseUtil.getError(e, EnumGesaduanException.ERROR_GENERICO.getCodigo(), e.getMessage())).build();
		}
		
		return Response.status(Status.NO_CONTENT).build();
	}
	
	
	@PUT
	@Path("carga/validar-pedidos")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response validarPedidosCargas(InputDatosValidarPedidoDTO datos) {	
		try {
			if (datos.getDatos().getCarga().size() == 0) throw new GesaduanException(EnumGesaduanException.PARAMETROS_OBLIGATORIOS);
			else {
				for (ValidarPedidoDTO carga : datos.getDatos().getCarga()) {
					if (carga.getCodigoAlmacenOrigen() == null || carga.getCodigoCarga() == null)
						throw new GesaduanException(EnumGesaduanException.PARAMETROS_OBLIGATORIOS);
				}					
			}
			validarPedidoService.validarPedido(datos);
		} catch(GesaduanException ex) {
			this.logger.error("({}-{}) ERROR - {} {}","CargasRestful(GESADUAN)","validarPedidosCargas",ex.getClass().getSimpleName(),ex.getMessage());	
			return Response.status(Status.BAD_REQUEST).entity(ResponseUtil.getError(ex, ex.getEnumGesaduan().getCodigo(), ex.getEnumGesaduan().getDescripcion())).build();
		} catch(Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","CargasRestful(GESADUAN)","validarPedidosCargas",e.getClass().getSimpleName(),e.getMessage());	
			return Response.status(Status.BAD_REQUEST).entity(ResponseUtil.getError(e, EnumGesaduanException.ERROR_GENERICO.getCodigo(), e.toString())).build();
		}
		
		return Response.status(Status.NO_CONTENT).build();
	}	
	

}