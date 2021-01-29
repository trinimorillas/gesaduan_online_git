package es.mercadona.gesaduan.restfull.datosfiscales.v100;

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
import es.mercadona.gesaduan.business.datosfiscales.getdatosfiscalesempresa.v1.GetDatosFiscalesEmpresaService;
import es.mercadona.gesaduan.dto.common.error.ErrorDTO;
import es.mercadona.gesaduan.dto.common.error.OutputResponseErrorDTO;
import es.mercadona.gesaduan.dto.datosfiscales.getdatosfiscalesporempresa.v1.restfull.OutputGetDatosFiscalesPorEmpresaDTO;


@RESTful
@Path("logistica/gestion-aduanas/v1.0")
@RequestScoped
public class DatosFiscalesRestFul {

	@Inject
	private GetDatosFiscalesEmpresaService getDatosFiscalesEmpresaService;
	
	@GET
	@Path("empresas/{codigoEmpresa}/datos-fiscales")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDatosFiscalesEmpresa(
			@NotNull @PathParam("codigoEmpresa") String codEmpresa,
			@NotNull @DefaultValue("es_ES") @QueryParam("locale") String locale
			) {

		OutputGetDatosFiscalesPorEmpresaDTO output = new OutputGetDatosFiscalesPorEmpresaDTO();

		try {

			
			output = getDatosFiscalesEmpresaService.getDatosFiscalesPorEmpresa(codEmpresa);
			
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(getError(e)).build();
		}

		return Response.ok(output, MediaType.APPLICATION_JSON).build();

	}

	private OutputResponseErrorDTO getError(Exception ex) {

		OutputResponseErrorDTO error = new OutputResponseErrorDTO();
		ErrorDTO errorDesc = new ErrorDTO();

		List<String> partsError = Arrays
				.asList(ex.getMessage().toString().replaceAll("\\t", "").replaceAll("\\r", "").split("\\n"));

		errorDesc.setCodigo("500");
		errorDesc.setDescripcion("Se ha producido un error en el servicio ".concat(this.getClass().getSimpleName()));
		errorDesc.setDetalles(partsError);

		error.setError(errorDesc);

		return error;

	}

}
