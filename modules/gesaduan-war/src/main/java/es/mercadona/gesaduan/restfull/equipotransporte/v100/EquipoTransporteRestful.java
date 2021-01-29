package es.mercadona.gesaduan.restfull.equipotransporte.v100;

import java.util.List;

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
import es.mercadona.gesaduan.business.equipotransporte.cambiarestado.v1.PutCambiarEstadoService;
import es.mercadona.gesaduan.business.equipotransporte.deleteequipotransporte.v1.DeleteEquipoTransporteService;
import es.mercadona.gesaduan.business.equipotransporte.deleterelacionequipocarga.v1.DeleteRelacionEquipoCargaService;
import es.mercadona.gesaduan.business.equipotransporte.getcontenedores.v1.GetContenedoresService;
import es.mercadona.gesaduan.business.equipotransporte.getequipostransporte.v1.GetEquiposTransporteService;
import es.mercadona.gesaduan.business.equipotransporte.getequipotransportedetalle.v1.GetEquipoTransporteDetalleService;
import es.mercadona.gesaduan.business.equipotransporte.movercargas.v1.PutMoverCargasService;
import es.mercadona.gesaduan.business.equipotransporte.putcontenedor.v1.PutContenedorService;
import es.mercadona.gesaduan.business.equipotransporte.putequipotransporte.v1.PutEquipoTransporteService;
import es.mercadona.gesaduan.business.equipotransporte.putrelacionequipocarga.v1.PutRelacionEquipoCargaService;
import es.mercadona.gesaduan.dto.equipotransporte.cambiarestado.v1.InputDatosCambiarEstadoDTO;
import es.mercadona.gesaduan.dto.equipotransporte.cambiarestado.v1.restfull.OutputCambiarEstadoDTO;
import es.mercadona.gesaduan.dto.equipotransporte.deleteequipotransporte.v1.InputDatosBorrarEquipoTransporteDTO;
import es.mercadona.gesaduan.dto.equipotransporte.deleteequipotransporte.v1.InputEquipoTransporteDeleteDTO;
import es.mercadona.gesaduan.dto.equipotransporte.deleteequipotransporte.v1.InputMetadatosBorrarEquipoTransporteDTO;
import es.mercadona.gesaduan.dto.equipotransporte.deleterelacionequipocarga.v1.EliminarCargaDTO;
import es.mercadona.gesaduan.dto.equipotransporte.deleterelacionequipocarga.v1.InputDatosEliminarRelacionEquipoCargaDTO;
import es.mercadona.gesaduan.dto.equipotransporte.getcontenedores.v1.InputDatosGetContenedorDTO;
import es.mercadona.gesaduan.dto.equipotransporte.getcontenedores.v1.InputGetContenedorDTO;
import es.mercadona.gesaduan.dto.equipotransporte.getcontenedores.v1.restfull.OutputGetContenedoresDTO;
import es.mercadona.gesaduan.dto.equipotransporte.getequipostransporte.v1.InputDatosEquipoTransporteDTO;
import es.mercadona.gesaduan.dto.equipotransporte.getequipostransporte.v1.restfull.OutputEquiposTransporteDTO;
import es.mercadona.gesaduan.dto.equipotransporte.getequipotransportedetalle.v1.InputEquipoTransporteDetalleDTO;
import es.mercadona.gesaduan.dto.equipotransporte.getequipotransportedetalle.v1.restfull.ContenedorDTO;
import es.mercadona.gesaduan.dto.equipotransporte.getequipotransportedetalle.v1.restfull.OutputEquipoTransporteDetalleDTO;
import es.mercadona.gesaduan.dto.equipotransporte.movercargas.v1.CargasDTO;
import es.mercadona.gesaduan.dto.equipotransporte.movercargas.v1.InputDatosMoverCargasDTO;
import es.mercadona.gesaduan.dto.equipotransporte.putcontenedor.v1.InputDatosContenedorDTO;
import es.mercadona.gesaduan.dto.equipotransporte.putequipotransporte.v1.InputDatosPutDTO;
import es.mercadona.gesaduan.dto.equipotransporte.putequipotransporte.v1.restfull.OutputEquipoTransportePutDTO;
import es.mercadona.gesaduan.dto.equipotransporte.putrelacionequipocarga.v1.CargaDTO;
import es.mercadona.gesaduan.dto.equipotransporte.putrelacionequipocarga.v1.InputDatosCrearRelacionEquipoCargaDTO;
import es.mercadona.gesaduan.exception.EnumGesaduanException;
import es.mercadona.gesaduan.exception.GesaduanException;
import es.mercadona.gesaduan.util.ResponseUtil;

@RESTful
@Path("logistica/gestion-aduanas/v1.0")
@RequestScoped
public class EquipoTransporteRestful {
	
	@Inject
	private org.slf4j.Logger logger;	
	
	@Inject
	private GetEquiposTransporteService getEquiposTransporteService;
	
	@Inject
	private GetEquipoTransporteDetalleService getEquipoTransporteDetalleService;
	
	@Inject
	private PutEquipoTransporteService putEquipoTransporteService;
	
	@Inject
	private DeleteEquipoTransporteService deleteEquipoTransporteService;
	
	@Inject
	private PutRelacionEquipoCargaService putRelacionEquipoCargaService;
	
	@Inject
	private DeleteRelacionEquipoCargaService deleteRelacionEquipoCargaService;
	
	@Inject
	private PutCambiarEstadoService putCambiarEstado;
	
	@Inject
	private PutMoverCargasService putMoverCargasCargado;
	
	@Inject
	private PutContenedorService putContenedor;
	
	@Inject
	private GetContenedoresService getContenedores;
	
	@POST
	@Path("equipo-transporte/sumario")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarEquiposTransporte(
		@QueryParam("codigoEmbarque") Long codigoEmbarque,
		@QueryParam("matricula") String matricula,
		@QueryParam("codigoCentroOrigen") Integer codigoCentroOrigen,
		@QueryParam("codigoPuertoDesembarque") Integer codigoPuertoDesembarque,
		@QueryParam("fechaCarga") String fechaCarga,
		@QueryParam("fechaEmbarque") String fechaEmbarque,
		@QueryParam("codigoEstado") Integer codigoEstado,
		@QueryParam("usuarioCreacion") String usuarioCreacion,
		@QueryParam("mcaOcultaLlenos") String mcaOcultaLlenos,		
		@QueryParam("mcaIncluyeCargas") String mcaIncluyeCargas,			
		@DefaultValue("1") @QueryParam("paginaInicio") Integer paginaInicio,
		@DefaultValue("14") @QueryParam("paginaTamanyo") Integer paginaTamanyo,
		@DefaultValue("-fechaCarga") @QueryParam("orden") String orden,
		InputDatosEquipoTransporteDTO datos) {
		
		OutputEquiposTransporteDTO response;
		
		try {
			BoPage pagination = new BoPage();
			pagination.setPage(Long.valueOf(paginaInicio));
			pagination.setLimit(Long.valueOf(paginaTamanyo));
			
			if (codigoEmbarque != null) datos.getDatos().setCodigoEmbarque(codigoEmbarque);			
			if (matricula != null) datos.getDatos().setMatricula(matricula);			
			if (codigoCentroOrigen != null) datos.getDatos().setCodigoCentroOrigen(codigoCentroOrigen);			
			if (codigoPuertoDesembarque != null) datos.getDatos().setCodigoPuertoDesembarque(codigoPuertoDesembarque);			
			if (fechaCarga != null) datos.getDatos().setFechaCarga(fechaCarga);
			if (fechaEmbarque != null) datos.getDatos().setFechaEmbarque(fechaEmbarque);
			if (codigoEstado != null) datos.getDatos().setCodigoEstado(codigoEstado);
			if (usuarioCreacion != null) datos.getDatos().setUsuarioCreacion(usuarioCreacion);
			if (mcaOcultaLlenos != null) datos.getDatos().setMcaOcultaLlenos(mcaOcultaLlenos);
			if (mcaIncluyeCargas != null) {
				datos.getDatos().setMcaIncluyeCargas(mcaIncluyeCargas);
			} else {
				datos.getDatos().setMcaIncluyeCargas("N");			
			}			
			datos.getDatos().setOrden(orden);
			
			response = getEquiposTransporteService.listarEquiposTransporte(datos, pagination);
			
		} catch(Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","EquipoTransporteRestful(GESADUAN)","listarEquiposTransporte",e.getClass().getSimpleName(),e.getMessage());	
			return Response.status(Status.BAD_REQUEST).entity(ResponseUtil.getError(e, EnumGesaduanException.ERROR_GENERICO.getCodigo(), e.getMessage())).build();
		}
		
		return Response.ok(response, MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("equipo-transporte/{codigoEquipo}")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response consultarEquipoTransporte (@NotNull @PathParam("codigoEquipo") Long codigoEquipo,
			@DefaultValue("+codigoCarga") @QueryParam("orden") String orden,
			@DefaultValue("N") @QueryParam("mcaIncluyeContenedores") String mcaIncluyeContenedores) {
		OutputEquipoTransporteDetalleDTO response = null;		
		try {			
			InputEquipoTransporteDetalleDTO input = new InputEquipoTransporteDetalleDTO();
			input.setCodigoEquipo(codigoEquipo);
			input.setMcaIncluyeContenedores(mcaIncluyeContenedores);
			input.setOrden(orden);
			
			response = getEquipoTransporteDetalleService.getEquipoTransporteDetalle(input);			
		} catch(Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","EquipoTransporteRestful(GESADUAN)","consultarEquipoTransporte",e.getClass().getSimpleName(),e.getMessage());	
			return Response.status(Status.BAD_REQUEST).entity(ResponseUtil.getError(e, EnumGesaduanException.ERROR_GENERICO.getCodigo(), e.getMessage())).build();
		}
		
		return Response.ok(response, MediaType.APPLICATION_JSON).build();
	}
	
	@PUT
	@Path("equipo-transporte")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response crearEquipoTransporte(@DefaultValue("1") @QueryParam("codigoEstado") Integer codigoEstado,
			@DefaultValue("0") @QueryParam("ocupacion") Integer ocupacion, InputDatosPutDTO input) {	
		OutputEquipoTransportePutDTO response = null;

		try {
			if (input.getMetadatos() == null || input.getMetadatos().getCodigoUsuario() == null) throw new GesaduanException(EnumGesaduanException.PARAMETROS_OBLIGATORIOS);
			if (input.getDatos().getCodigoEquipo() != null) response = putEquipoTransporteService.modificarEquipoTransporte(input);
			else if (input.getDatos().getCodigoEmbarque() == null ||
					input.getDatos().getMatricula() == null ||
					input.getDatos().getCodigoTemperatura() == null) {
						throw new GesaduanException(EnumGesaduanException.PARAMETROS_OBLIGATORIOS);
			} else {
				input.getDatos().setCodigoEstado(codigoEstado);
				input.getDatos().setOcupacion(ocupacion);
				response = putEquipoTransporteService.crearEquipoTransporte(input);
			}
		} catch(GesaduanException ex) {
			this.logger.error("({}-{}) ERROR - {} {}","EquipoTransporteRestful(GESADUAN)","crearEquipoTransporte",ex.getClass().getSimpleName(),ex.getMessage());	
			return Response.status(Status.BAD_REQUEST).entity(ResponseUtil.getError(ex, ex.getEnumGesaduan().getCodigo(), ex.getEnumGesaduan().getDescripcion())).build();
		} catch(Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","EquipoTransporteRestful(GESADUAN)","crearEquipoTransporte",e.getClass().getSimpleName(),e.getMessage());	
			return Response.status(Status.BAD_REQUEST).entity(ResponseUtil.getError(e, EnumGesaduanException.ERROR_GENERICO.getCodigo(), e.getMessage())).build();
		}
		
		return Response.status(Status.CREATED).entity(response).build();
	}
	
	@DELETE
	@Path("equipo-transporte/{codigoEquipo}")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response eliminarEquipoTransporte(@NotNull @PathParam("codigoEquipo") Long codigoEquipo) {		
		try {
			// if (datos.getMetadatos() == null || datos.getMetadatos().getCodigoUsuario() == null) throw new GesaduanException(EnumGesaduanException.PARAMETROS_OBLIGATORIOS);
			InputEquipoTransporteDeleteDTO input = new InputEquipoTransporteDeleteDTO();
			InputDatosBorrarEquipoTransporteDTO datos = new InputDatosBorrarEquipoTransporteDTO();
			input.setCodigoEquipo(codigoEquipo);
			datos.setDatos(input);
			InputMetadatosBorrarEquipoTransporteDTO metadatos = new InputMetadatosBorrarEquipoTransporteDTO();
			datos.setMetadatos(metadatos);
			deleteEquipoTransporteService.deleteEquipoTransporte(datos);
		} catch(Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","EquipoTransporteRestful(GESADUAN)","eliminarEquipoTransporte",e.getClass().getSimpleName(),e.getMessage());	
			return Response.status(Status.BAD_REQUEST).entity(ResponseUtil.getError(e, EnumGesaduanException.ERROR_GENERICO.getCodigo(), e.getMessage())).build();
		}
		return Response.status(Status.NO_CONTENT).build();
	}
	
	/*
	@POST
	@Path("equipo-transporte/{codigoEquipo}")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response eliminarEquipoTransporte(@NotNull @PathParam("codigoEquipo") Long codigoEquipo,
			InputDatosBorrarEquipoTransporteDTO datos) {		
		try {
			if (datos.getMetadatos() == null || datos.getMetadatos().getCodigoUsuario() == null) throw new GesaduanException(EnumGesaduanException.PARAMETROS_OBLIGATORIOS);
			InputEquipoTransporteDeleteDTO input = new InputEquipoTransporteDeleteDTO();
			input.setCodigoEquipo(codigoEquipo);
			datos.setDatos(input);
			deleteEquipoTransporteService.deleteEquipoTransporte(datos);
		} catch(Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","EquipoTransporteRestful(GESADUAN)","eliminarEquipoTransporte",e.getClass().getSimpleName(),e.getMessage());	
			return Response.status(Status.BAD_REQUEST).entity(ResponseUtil.getError(e, EnumGesaduanException.ERROR_GENERICO.getCodigo(), e.getMessage())).build();
		}
		return Response.status(Status.NO_CONTENT).build();
	}
	*/	
	
	@PUT
	@Path("equipo-transporte/{codigoEquipo}/relaciones")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response crearRelacionEquipoCarga(@NotNull @PathParam("codigoEquipo") Long codigoEquipo,
			InputDatosCrearRelacionEquipoCargaDTO input) {		
		try {
			if (input.getMetadatos() == null || input.getMetadatos().getCodigoUsuario() == null) throw new GesaduanException(EnumGesaduanException.PARAMETROS_OBLIGATORIOS);
			
			input.getDatos().setCodigoEquipo(codigoEquipo);
			List<CargaDTO> cargas = input.getDatos().getCarga();
			
			for (CargaDTO carga : cargas) {
				if (carga.getCodigoCarga() == null ||
					carga.getCodigoCentroOrigen() == null ||
					carga.getNumeroDivision() == null ||
					carga.getNumeroHuecosOcupados() == null ||
					carga.getNumeroPesoOcupado() == null) {
					throw new GesaduanException(EnumGesaduanException.PARAMETROS_OBLIGATORIOS);
				}
			}
			
			putRelacionEquipoCargaService.putRelacionEquipoCarga(input);
		} catch(GesaduanException ex) {
			this.logger.error("({}-{}) ERROR - {} {}","EquipoTransporteRestful(GESADUAN)","crearRelacionEquipoCarga",ex.getClass().getSimpleName(),ex.getMessage());
			return Response.status(Status.BAD_REQUEST).entity(ResponseUtil.getError(ex, ex.getEnumGesaduan().getCodigo(), ex.getEnumGesaduan().getDescripcion())).build();
		} catch(Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","EquipoTransporteRestful(GESADUAN)","crearRelacionEquipoCarga",e.getClass().getSimpleName(),e.getMessage());	
			return Response.status(Status.BAD_REQUEST).entity(ResponseUtil.getError(e, EnumGesaduanException.ERROR_GENERICO.getCodigo(), e.getMessage())).build();
		}
		
		return Response.status(Status.NO_CONTENT).build();
	}
	
	@POST
	@Path("equipo-transporte/{codigoEquipo}/relaciones")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response eliminarRelacionEquipoCarga(@NotNull @PathParam("codigoEquipo") Long codigoEquipo,
			InputDatosEliminarRelacionEquipoCargaDTO input) {		
		try {
			if (input.getMetadatos() == null || input.getMetadatos().getCodigoUsuario() == null) throw new GesaduanException(EnumGesaduanException.PARAMETROS_OBLIGATORIOS);
			input.getDatos().setCodigoEquipo(codigoEquipo);
			List<EliminarCargaDTO> cargas = input.getDatos().getCarga();
			
			for (EliminarCargaDTO carga : cargas) {
				if (carga.getCodigoCarga() == null || carga.getCodigoCentroOrigen() == null)
					throw new GesaduanException(EnumGesaduanException.PARAMETROS_OBLIGATORIOS);
			}
			deleteRelacionEquipoCargaService.deleteRelacionEquipoCarga(input);
		} catch(GesaduanException ex) {
			this.logger.error("({}-{}) ERROR - {} {}","EquipoTransporteRestful(GESADUAN)","eliminarRelacionEquipoCarga",ex.getClass().getSimpleName(),ex.getMessage());
			return Response.status(Status.BAD_REQUEST).entity(ResponseUtil.getError(ex, ex.getEnumGesaduan().getCodigo(), ex.getEnumGesaduan().getDescripcion())).build();
		} catch(Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","EquipoTransporteRestful(GESADUAN)","eliminarRelacionEquipoCarga",e.getClass().getSimpleName(),e.getMessage());	
			return Response.status(Status.BAD_REQUEST).entity(ResponseUtil.getError(e, EnumGesaduanException.ERROR_GENERICO.getCodigo(), e.getMessage())).build();
		}
		
		return Response.status(Status.NO_CONTENT).build();
	}
	
	@PUT
	@Path("equipo-transporte/cambio-estado")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response cambiarIndicadorCargado(InputDatosCambiarEstadoDTO input) {	
		OutputCambiarEstadoDTO response = null;

		try {
			if (input.getDatos().getCodigoEstado() == null || input.getDatos().getEquipo().size() == 0 || input.getMetadatos() == null || input.getMetadatos().getCodigoUsuario() == null)
				throw new GesaduanException(EnumGesaduanException.PARAMETROS_OBLIGATORIOS);
			else response = putCambiarEstado.cambiarEstado(input);
		} catch(GesaduanException ex) {
			this.logger.error("({}-{}) ERROR - {} {}","EquipoTransporteRestful(GESADUAN)","cambiarIndicadorCargado",ex.getClass().getSimpleName(),ex.getMessage());
			return Response.status(Status.BAD_REQUEST).entity(ResponseUtil.getError(ex, ex.getEnumGesaduan().getCodigo(), ex.getEnumGesaduan().getDescripcion())).build();
		} catch(Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","EquipoTransporteRestful(GESADUAN)","cambiarIndicadorCargado",e.getClass().getSimpleName(),e.getMessage());	
			return Response.status(Status.BAD_REQUEST).entity(ResponseUtil.getError(e, EnumGesaduanException.ERROR_GENERICO.getCodigo(), e.getMessage())).build();
		}
		
		return Response.status(Status.CREATED).entity(response).build();
	}
	
	@PUT
	@Path("equipo-transporte/mover-cargas")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response moverCargas(InputDatosMoverCargasDTO datos) {
		try {
			if (datos.getDatos().getCodigoEquipoOrigen() == null ||
				datos.getDatos().getCodigoEquipoDestino() == null ||
				datos.getDatos().getCargas().size() == 0 ||
				datos.getMetadatos().getCodigoUsuario() == null) {
				throw new GesaduanException(EnumGesaduanException.PARAMETROS_OBLIGATORIOS);
			}
			for (CargasDTO carga : datos.getDatos().getCargas()) {
				if (carga.getCodigoCarga() == null || carga.getCodigoCentroOrigen() == null)
					throw new GesaduanException(EnumGesaduanException.PARAMETROS_OBLIGATORIOS);
			}
			putMoverCargasCargado.moverCargas(datos);
		} catch(GesaduanException ex) {
			this.logger.error("({}-{}) ERROR - {} {}","EquipoTransporteRestful(GESADUAN)","moverCargas",ex.getClass().getSimpleName(),ex.getMessage());	
			return Response.status(Status.BAD_REQUEST).entity(ResponseUtil.getError(ex, ex.getEnumGesaduan().getCodigo(), ex.getEnumGesaduan().getDescripcion())).build();
		} catch(Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","EquipoTransporteRestful(GESADUAN)","moverCargas",e.getClass().getSimpleName(),e.getMessage());	
			return Response.status(Status.BAD_REQUEST).entity(ResponseUtil.getError(e, EnumGesaduanException.ERROR_GENERICO.getCodigo(), e.getMessage())).build();
		}
		
		return Response.status(Status.NO_CONTENT).build();
	}
	
	@POST
	@Path("equipo-transporte/{codigoEquipo}/contenedor/cambio-estado")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response actualizarContenedor(@PathParam("codigoEquipo") Long codigoEquipo,
			InputDatosContenedorDTO datos) {
		try {
			if (datos.getMetadatos() == null || datos.getMetadatos().getCodigoUsuario() == null) throw new GesaduanException(EnumGesaduanException.PARAMETROS_OBLIGATORIOS);
			for (ContenedorDTO contenedor : datos.getDatos().getContenedor()) {
				if (contenedor.getCodigoCarga() == null || 
					contenedor.getCodigoCentroOrigen() == null || 
					contenedor.getNumContenedor() == null || 
					contenedor.getMcaFacturado() == null || 
					contenedor.getFechaExpedicion() == null)
					throw new GesaduanException(EnumGesaduanException.PARAMETROS_OBLIGATORIOS);
			}
			datos.getDatos().setCodigoEquipo(codigoEquipo);
			putContenedor.actualizarContenedor(datos);
		} catch(GesaduanException ex) {
			this.logger.error("({}-{}) ERROR - {} {}","EquipoTransporteRestful(GESADUAN)","actualizarContenedor",ex.getClass().getSimpleName(),ex.getMessage());	
			return Response.status(Status.BAD_REQUEST).entity(ResponseUtil.getError(ex, ex.getEnumGesaduan().getCodigo(), ex.getEnumGesaduan().getDescripcion())).build();
		} catch(Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","EquipoTransporteRestful(GESADUAN)","actualizarContenedor",e.getClass().getSimpleName(),e.getMessage());	
			return Response.status(Status.BAD_REQUEST).entity(ResponseUtil.getError(e, EnumGesaduanException.ERROR_GENERICO.getCodigo(), e.getMessage())).build();
		}
		
		return Response.status(Status.NO_CONTENT).build();
	}
	
	@GET
	@Path("equipo-transporte/{codigoEquipo}/contenedor")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarContenedores(@PathParam("codigoEquipo") Long codigoEquipo,
			@QueryParam("codigoCentroOrigen") String codigoCentroOrigen,
			@QueryParam("codigoCentroDestino") String codigoCentroDestino,
			@QueryParam("mcaFacturado") String mcaFacturado,
			@QueryParam("mcaCargasDivididas") String mcaCargasDivididas,
			@QueryParam("codigoSuministro") Long codigoSuministro) {
		OutputGetContenedoresDTO response;
		try {
			InputDatosGetContenedorDTO datos = new InputDatosGetContenedorDTO();
			InputGetContenedorDTO input = new InputGetContenedorDTO();
			input.setCodigoEquipo(codigoEquipo);
			if (codigoCentroOrigen != null) input.setCodigoCentroOrigen(codigoCentroOrigen);
			if (codigoCentroDestino != null) input.setCodigoCentroDestino(codigoCentroDestino);
			if (mcaFacturado != null) input.setMcaFacturado(mcaFacturado);
			if (mcaCargasDivididas != null) input.setMcaCargasDivididas(mcaCargasDivididas);
			if (codigoSuministro != null) input.setCodigoSuministro(codigoSuministro);
			datos.setDatos(input);
			response = getContenedores.listarContenedores(datos);
		} catch(GesaduanException ex) {
			this.logger.error("({}-{}) ERROR - {} {}","EquipoTransporteRestful(GESADUAN)","actualizarContenedor",ex.getClass().getSimpleName(),ex.getMessage());	
			return Response.status(Status.BAD_REQUEST).entity(ResponseUtil.getError(ex, ex.getEnumGesaduan().getCodigo(), ex.getEnumGesaduan().getDescripcion())).build();
		} catch(Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","EquipoTransporteRestful(GESADUAN)","actualizarContenedor",e.getClass().getSimpleName(),e.getMessage());	
			return Response.status(Status.BAD_REQUEST).entity(ResponseUtil.getError(e, EnumGesaduanException.ERROR_GENERICO.getCodigo(), e.getMessage())).build();
		}
		
		return Response.ok(response, MediaType.APPLICATION_JSON).build();
	}
}