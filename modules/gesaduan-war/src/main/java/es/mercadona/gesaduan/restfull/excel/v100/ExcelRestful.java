package es.mercadona.gesaduan.restfull.excel.v100;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import es.mercadona.fwk.restful.service.annotate.RESTful;
import es.mercadona.gesaduan.business.excel.getexcel.v1.GetExcelService;
import es.mercadona.gesaduan.dto.excel.getexcel.v1.OutputExcelDTO;
import es.mercadona.gesaduan.exception.EnumGesaduanException;
import es.mercadona.gesaduan.util.ResponseUtil;

@RESTful
@Path("logistica/gestion-aduanas/v1.0")
@RequestScoped
public class ExcelRestful {
	
	@Inject
	private org.slf4j.Logger logger;	
	
	@Inject
	private GetExcelService getVersionExcelService;
	
	@GET
	@Path("excel")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getVersionExcel() {		
		OutputExcelDTO response;
		
		try {
			response = getVersionExcelService.getExcel();
		} catch(Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","GetVersionExcelService(GESADUAN)","getExcel",e.getClass().getSimpleName(),e.getMessage());	
			return Response.status(Status.BAD_REQUEST).entity(ResponseUtil.getError(e, EnumGesaduanException.ERROR_GENERICO.getCodigo(), e.getMessage())).build();
		}
		
		return Response.ok(response, MediaType.APPLICATION_JSON).build();
	}
	


}
