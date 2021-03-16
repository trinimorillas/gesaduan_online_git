package es.mercadona.gesaduan.restfull.proveedores.v100;

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
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import es.mercadona.fwk.core.web.BoPage;
import es.mercadona.fwk.restful.service.annotate.RESTful;
import es.mercadona.gesaduan.business.proveedores.contactosproveedor.v1.ContactosProvService;
import es.mercadona.gesaduan.business.proveedores.getproveedores.v1.GetProveedoresService;
import es.mercadona.gesaduan.business.proveedores.getproveedoresdetalle.v1.GetProveedoresDetalleService;
import es.mercadona.gesaduan.business.proveedores.getrelacionesproveedores.v1.GetRelacionesProveedoresService;
import es.mercadona.gesaduan.business.proveedores.putproveedores.v1.PutProveedoresService;
import es.mercadona.gesaduan.business.proveedores.putpuertoagencia.v1.PutPuertoAgenciaService;
import es.mercadona.gesaduan.business.proveedores.putrelacionesproveedor.v1.PutRelacionesProveedorService;
import es.mercadona.gesaduan.dto.proveedores.getproveedores.v1.InputGetProveedoresDTO;
import es.mercadona.gesaduan.dto.proveedores.getproveedores.v1.restfull.OutputGetProveedoresDTO;
import es.mercadona.gesaduan.dto.proveedores.getproveedoresdetalle.v1.InputProveedoresDetalleDTO;
import es.mercadona.gesaduan.dto.proveedores.getproveedoresdetalle.v1.restfull.OutputProveedoresDetalleDTO;
import es.mercadona.gesaduan.dto.proveedores.getrelacionesproveedores.v1.InputRelacionesProveedoresDTO;
import es.mercadona.gesaduan.dto.proveedores.getrelacionesproveedores.v1.restfull.OutputRelacionesProveedoresDTO;
import es.mercadona.gesaduan.dto.proveedores.putproveedores.v1.restfull.InputPutProveedores;
import es.mercadona.gesaduan.dto.proveedores.putproveedores.v1.restfull.InputPutProveedoresContactos;
import es.mercadona.gesaduan.dto.proveedores.putproveedores.v1.restfull.OutputProveedoresContactos;
import es.mercadona.gesaduan.dto.proveedores.putproveedores.v1.restfull.OutputProveedoresPut;
import es.mercadona.gesaduan.dto.proveedores.putproveedores.v1.restfull.PutProveedorContacto;
import es.mercadona.gesaduan.dto.proveedores.putpuertoagencia.v1.InputDatosPuertoAgenciaDTO;
import es.mercadona.gesaduan.dto.proveedores.putpuertoagencia.v1.PuertoDTO;
import es.mercadona.gesaduan.dto.proveedores.putrelacionesproveedores.v1.InputRelacionesProveedorDTO;
import es.mercadona.gesaduan.exception.EnumGesaduanException;
import es.mercadona.gesaduan.exception.GesaduanException;
import es.mercadona.gesaduan.jpa.proveedores.putproveedorescontacto.v1.ProveedorContactoJPA;
import es.mercadona.gesaduan.util.ResponseUtil;

@RESTful
@Path("logistica/gestion-aduanas/v1.0")
@RequestScoped
public class ProveedoresRestful {
	
	@Inject
	private org.slf4j.Logger logger;	

	@Inject
	private GetProveedoresService getProveedoresService;

	@Inject
	private GetProveedoresDetalleService getProveedoresDetalleService;

	@Inject
	private PutProveedoresService putProveedoresService;
	
	@Inject
	private GetRelacionesProveedoresService getRelacionesProveedoresService;
	
	@Inject
	private PutRelacionesProveedorService putRelacionesProveedorService;
	
	@Inject
	private ContactosProvService contactosProvService;
	
	@Inject
	private PutPuertoAgenciaService putPuertoAgenciaService;

	/*
	 * ProveedoresRestful
	 * 
	 */
	
	@GET
	@Path("proveedores")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarProveedor(@DefaultValue("es-ES") @QueryParam("locale") String locale,
			@NotNull @DefaultValue("true") @QueryParam("estaActivo") Boolean estaActivo,
			@DefaultValue("false") @QueryParam("esAgencia") Boolean esAgencia,
			@QueryParam("codigoProveedor") String codigoProveedor,
			@QueryParam("codigoProveedorPublico") String codigoProveedorPublico,
			@QueryParam("nombreProveedor") String nombreProveedor,
			@QueryParam("mcaNaviera") String mcaNaviera,
			@QueryParam("mcaTransportista") String mcaTransportista,
			@QueryParam("mcaProducto") String mcaProducto,			
			@DefaultValue("1") @QueryParam("paginaInicio") Integer paginaInicio,
			@DefaultValue("10") @QueryParam("paginaTamanyo") Integer paginaTamanyo,
			@DefaultValue("+codigoProveedor") @QueryParam("orden") String orden) {

		OutputGetProveedoresDTO proveedores = null;

		try {
			InputGetProveedoresDTO inputRest = new InputGetProveedoresDTO();

			/* INICIO PAGINACION */

			BoPage paginacion = new BoPage();

			if (paginaInicio != null) {
				paginacion.setPage(Long.valueOf(paginaInicio));
			}

			if (paginaTamanyo != null) {
				paginacion.setLimit(Long.valueOf(paginaTamanyo));
			}

			/* FIN PAGINIACION */

			if (nombreProveedor != null) {
				inputRest.setNombreProveedor(nombreProveedor);
			}

			if (codigoProveedor != null) {
				inputRest.setCodigoProveedorLegacy(codigoProveedor);
			}
			
			if (codigoProveedorPublico != null) {
				inputRest.setCodigoProveedorPublico(codigoProveedorPublico);
			}
			
			if (mcaNaviera != null) {
				inputRest.setMcaNaviera(mcaNaviera);
			}
			
			if (mcaTransportista != null) {
				inputRest.setMcaTransportista(mcaTransportista);
			}
			
			if (mcaProducto != null) {
				inputRest.setMcaProducto(mcaProducto);
			}			

			inputRest.setEsAgencia(esAgencia);
			inputRest.setEstaActivo(estaActivo);
			inputRest.setOrden(orden);

			proveedores = getProveedoresService.getProveedoresList(inputRest, paginacion);
		} catch(Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","ProveedoresRestful(GESADUAN)","getProveedores",e.getClass().getSimpleName(),e.getMessage());	
			return Response.status(Status.BAD_REQUEST).entity(ResponseUtil.getError(e, EnumGesaduanException.ERROR_GENERICO.getCodigo(), e.getMessage())).build();
		}
		
		if (proveedores.getDatos().isEmpty() || proveedores == null) {
			return Response.status(Response.Status.OK).entity(proveedores).build();

		} else {

			ResponseBuilder rb = Response.ok(proveedores, MediaType.APPLICATION_JSON);			
			return rb.build();
		}
	}

	@GET
	@Path("proveedores/{codigoProveedor}")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProveedorDetalle(
			@DefaultValue("es-ES") @QueryParam("locale") String locale,
			@NotNull @PathParam("codigoProveedor") String codigoProveedor
			) {

		OutputProveedoresDetalleDTO proveedoresDetalle = null;

		try {

			InputProveedoresDetalleDTO input = new InputProveedoresDetalleDTO();

			input.setCodigoProveedor(codigoProveedor);
			input.setLocale(locale);
				
			proveedoresDetalle = getProveedoresDetalleService.getProveedoresDetalle(input);

		} catch(Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","ProveedoresRestful(GESADUAN)","getProveedores",e.getClass().getSimpleName(),e.getMessage());	
			return Response.status(Status.BAD_REQUEST).entity(ResponseUtil.getError(e, EnumGesaduanException.ERROR_GENERICO.getCodigo(),e.getMessage())).build();
		}

		return Response.ok(proveedoresDetalle, MediaType.APPLICATION_JSON).build();
	}
	
	@PUT
	@Path("proveedores")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response putProveedor(InputPutProveedores input) {

		OutputProveedoresPut response = new OutputProveedoresPut();

		try {

			response = putProveedoresService.activarProveedores(input);

		} catch(Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","ProveedoresRestful(GESADUAN)","putProveedores",e.getClass().getSimpleName(),e.getMessage());	
			return Response.status(Status.BAD_REQUEST).entity(ResponseUtil.getError(e, EnumGesaduanException.ERROR_GENERICO.getCodigo(), e.getMessage())).build();
		}

		return Response.status(Status.OK).entity(response).build();

	}
	
	@PUT
	@Path("proveedores/{codigoProveedor}/contacto")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response putContactoProveedor(
			@NotNull @PathParam("codigoProveedor") String codigoProveedor,
			InputPutProveedoresContactos datos
			) {
	
		OutputProveedoresContactos salida = new OutputProveedoresContactos();
		PutProveedorContacto contacto = new PutProveedorContacto();
		
		try {
			
			ProveedorContactoJPA result = contactosProvService.putContactoProveedor(datos, codigoProveedor);
			
			if(result != null) {
				
				contacto.setCodMecanismoContactoEmail(result.getCodMecanismoContactoEmail());
				contacto.setCodLocalizacionEmail(result.getCodLocalizacionEmail());
				contacto.setCodMecanismoContactoSMS(result.getCodMecanismoContactoSMS());
				contacto.setCodLocalizacionSMS(result.getCodLocalizacionSMS());
				
				
				//contacto.setEnvioEmail();
				//contacto.setEnvioSMS();
				
				
				salida.setDatos(contacto);
				
				
				return Response.status(Status.OK).entity(salida).build();
			}else {
				return Response.status(Status.BAD_REQUEST).entity(salida).build();
			}

		} catch(Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","ProveedoresRestful(GESADUAN)","putContactoProveedor",e.getClass().getSimpleName(),e.getMessage());	
			return Response.status(Status.BAD_REQUEST).entity(ResponseUtil.getError(e, EnumGesaduanException.ERROR_GENERICO.getCodigo(), e.getMessage())).build();
		}
		
	}
	
	
	@DELETE
	@Path("proveedores/{codigoProveedor}/contacto/{codigoContacto}")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteContactoProveedor(
			@NotNull @PathParam("codigoProveedor") String codigoProveedor,
			@NotNull @PathParam("codigoContacto") Long codigoContacto
			) {
	
		OutputProveedoresContactos salida = new OutputProveedoresContactos();
		PutProveedorContacto contacto = new PutProveedorContacto();
		
		try {

			ProveedorContactoJPA result = contactosProvService.deleteContactoProveedor(codigoProveedor, codigoContacto);
			
			if(result != null) {
				
				contacto.setCodigoContacto(result.getCodContacto());
				contacto.setCodMecanismoContactoEmail(result.getCodMecanismoContactoEmail());
				contacto.setCodMecanismoContactoSMS(result.getCodMecanismoContactoSMS());
				contacto.setCodLocalizacionEmail(result.getCodLocalizacionEmail());
				contacto.setCodLocalizacionSMS(result.getCodLocalizacionSMS());				
				
				salida.setDatos(contacto);
						
				return Response.status(Status.OK).entity(salida).build();
				
			}else {
				salida.setDatos(contacto);
				
			}

		} catch(Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","ProveedoresRestful(GESADUAN)","deleteContactoProveedor",e.getClass().getSimpleName(),e.getMessage());	
			return Response.status(Status.BAD_REQUEST).entity(ResponseUtil.getError(e, EnumGesaduanException.ERROR_GENERICO.getCodigo(), e.getMessage())).build();
		}

		return Response.status(Status.NO_CONTENT).entity(salida).build();
		
		
	}	

	
	/*
	 * 
	 * Los servicios de relaciones no se usan ya, toda la parte de relaciones se gestiona llamando a los 
	 * servicios de TERCE
	 * 
	 * */
	
	@GET
	@Path("proveedores/{codigoProveedor}/relaciones")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRelacionProveedores(@DefaultValue("es-ES") @QueryParam("locale") String locale,
			@NotNull @PathParam("codigoProveedor") String codigoProveedor,
			@NotNull @QueryParam("tipoBusqueda") String tipoBusqueda,
			@NotNull @QueryParam("relacionesVigentes") boolean relacionesVigentes ,
			@QueryParam("numeroProveedorRelacionado") String numeroProveedorRelacionado,
			@QueryParam("nombreProveedorRelacionado") String nombreProveedorRelacionado,
			@DefaultValue("1") @QueryParam("paginaInicio") Long paginaInicio,
			@DefaultValue("10") @QueryParam("paginaTamanyo") Long paginaTamanyo,
			@DefaultValue("+codigo") @QueryParam("orden") String orden) {
		
		OutputRelacionesProveedoresDTO relacionesProveedores = null;
		
		try {

			InputRelacionesProveedoresDTO input = new InputRelacionesProveedoresDTO();
			
			input.setCodigoProveedor(codigoProveedor);
			input.setTipoBusqueda(tipoBusqueda);
			input.setRelacionesVigentes(relacionesVigentes);
			
			if(numeroProveedorRelacionado != null) {
				input.setNumeroProveedorRelacionado(numeroProveedorRelacionado);
			}
			
			if(nombreProveedorRelacionado != null) {
				input.setNombreProveedorRelacionado(nombreProveedorRelacionado);
			}
			
			input.setOrden(orden);
			
			BoPage pagination = new BoPage();
			
			pagination.setPage(paginaInicio);
			pagination.setLimit(paginaTamanyo);
			
			relacionesProveedores = getRelacionesProveedoresService.getRelacionesProveedores(input, pagination);

		} catch(Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","ProveedoresRestful(GESADUAN)","getRelacionProveedores",e.getClass().getSimpleName(),e.getMessage());	
			return Response.status(Status.BAD_REQUEST).entity(ResponseUtil.getError(e, EnumGesaduanException.ERROR_GENERICO.getCodigo(), e.getMessage())).build();
		}

		return Response.ok(relacionesProveedores, MediaType.APPLICATION_JSON).build();
	}

	
	/*
	 * 
	 * Los servicios de relaciones no se usan ya, toda la parte de relaciones se gestiona llamando a los 
	 * servicios de TERCE
	 * 
	 * */	
	
	@PUT
	@Path("proveedores/{codigoProveedor}/relaciones")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response putRelacionesProveedores(
			InputRelacionesProveedorDTO input, 
			@NotNull @PathParam("codigoProveedor") String codigoProveedor) {
		
		
		input.getDatos().setCodigo(String.valueOf(codigoProveedor));
		
		try {

			putRelacionesProveedorService.editRelacionesProveedor(input);

		} catch(Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","ProveedoresRestful(GESADUAN)","putRelacionesProveedores",e.getClass().getSimpleName(),e.getMessage());	
			return Response.status(Status.BAD_REQUEST).entity(ResponseUtil.getError(e, EnumGesaduanException.ERROR_GENERICO.getCodigo(), e.getMessage())).build();
		}

		return Response.status(Status.CREATED).type("text/plain").entity("Ejecución correcta.").build();
	}
	
	@PUT
	@Path("proveedores/{codigoAgencia}/puerto")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response putPuertoAgencia(@NotNull @PathParam("codigoAgencia") String codigoAgencia,
			InputDatosPuertoAgenciaDTO datos) {
		try {
			if (datos.getMetadatos() == null || datos.getMetadatos().getCodigoUsuario() == null) throw new GesaduanException(EnumGesaduanException.PARAMETROS_OBLIGATORIOS);
			if (datos.getDatos().getOperacion() == null) throw new GesaduanException(EnumGesaduanException.PARAMETROS_OBLIGATORIOS);
			if (datos.getDatos().getPuerto().size() == 0) throw new GesaduanException(EnumGesaduanException.PARAMETROS_OBLIGATORIOS);
			else {
				for (PuertoDTO puerto : datos.getDatos().getPuerto()) {
					if (puerto.getCodigoPuerto() == null) throw new GesaduanException(EnumGesaduanException.PARAMETROS_OBLIGATORIOS);
				}
			}
			datos.getDatos().setCodigoAgencia(codigoAgencia);
			putPuertoAgenciaService.putPuertoAgencia(datos);
		} catch(Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","ProveedoresRestful(GESADUAN)","putPuertoAgencia",e.getClass().getSimpleName(),e.getMessage());	
			return Response.status(Status.BAD_REQUEST).entity(ResponseUtil.getError(e, EnumGesaduanException.ERROR_GENERICO.getCodigo(), e.getMessage())).build();
		}
		return Response.status(Status.NO_CONTENT).build();
	}	

}
