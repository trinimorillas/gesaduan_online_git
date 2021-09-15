package es.mercadona.gesaduan.restfull.declaracionesdevalorapi.v200;

import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import es.mercadona.fwk.restful.service.annotate.RESTful;
import es.mercadona.gesaduan.business.declaracionesdevalorapi.getvddetail.v2.GetVDDetailService;
import es.mercadona.gesaduan.common.Constantes;
import es.mercadona.gesaduan.dto.common.error.ErrorDTO;
import es.mercadona.gesaduan.dto.common.error.OutputResponseErrorDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvddetail.v2.InputVDDetailDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvddetail.v2.restful.OutputVDDetailDTO;
import es.mercadona.gesaduan.exception.EnumGesaduanException;
import es.mercadona.gesaduan.exception.GesaduanException;

@RESTful
@Path("logistica/gestion-aduanas/v2.0")
@RequestScoped
public class DeclaracionesDeValorApiRestful {

	@Inject
	private org.slf4j.Logger logger;

	@Inject
	private GetVDDetailService getValueDeclarationDetailService;

	private static final String LOG_FILE = "DeclaracionesDeValorApiRestful(GESADUAN)";

	@GET
	@Path("value-declarations/{valueDeclarationId}")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDeclaracionDeValorDetalle(@NotNull @PathParam("valueDeclarationId") String valueDeclarationId,
			@DefaultValue("es_ES") @QueryParam("locale") String locale,
			@NotNull @QueryParam("userId") String userId) {
		OutputVDDetailDTO result;

		try {
			
			InputVDDetailDTO input = new InputVDDetailDTO();			
			
			if (userId == null) {
				throw new GesaduanException(EnumGesaduanException.PARAMETROS_OBLIGATORIOS);
			}

			String[] valueDeclarationIdArr = valueDeclarationId.split("-");
			String valueDeclarationNumber = valueDeclarationIdArr[0];
			String valueDeclarationYear = valueDeclarationIdArr[1];				
			String valueDeclarationVersion = valueDeclarationIdArr[2];				
			
			input.setValueDeclarationNumber(valueDeclarationNumber);
			input.setValueDeclarationYear(valueDeclarationYear);
			input.setValueDeclarationVersion(valueDeclarationVersion);			
			input.setLocale(locale);			
			input.setUserId(userId);

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
			this.logger.error(Constantes.FORMATO_ERROR_LOG, LOG_FILE, "getDeclaracionDeValorDetalle", e.getClass().getSimpleName(), e.getMessage());
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
