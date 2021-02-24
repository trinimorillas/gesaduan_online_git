package es.mercadona.gesaduan.business.datosfiscales.getdatosfiscalesempresa.v1.impl;

import javax.inject.Inject;

import es.mercadona.fwk.auth.SecurityService;
import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.gesaduan.business.datosfiscales.getdatosfiscalesempresa.v1.GetDatosFiscalesEmpresaService;
import es.mercadona.gesaduan.dao.datosfiscales.getdatosfiscalesempresa.v1.GetDatosFiscalesEmpresaDAO;
import es.mercadona.gesaduan.dto.datosfiscales.getdatosfiscalesporempresa.v1.restfull.DatosFiscalesDTO;
import es.mercadona.gesaduan.dto.datosfiscales.getdatosfiscalesporempresa.v1.restfull.OutputGetDatosFiscalesPorEmpresaDTO;
import es.mercadona.gesaduan.jpa.datosfiscales.v1.DatosFiscalesEmpresaJPA;

public class GetDatosFiscalesEmpresaServiceImpl implements GetDatosFiscalesEmpresaService {

	@Inject
	private org.slf4j.Logger logger;
	
	@Inject
	private GetDatosFiscalesEmpresaDAO getDatosFiscalesEmpresaDAO;
		  
	@Inject
	private SecurityService securityService;

	@Override
	public OutputGetDatosFiscalesPorEmpresaDTO getDatosFiscalesPorEmpresa(String codEmpresa) {

			
		OutputGetDatosFiscalesPorEmpresaDTO output = new OutputGetDatosFiscalesPorEmpresaDTO();

		try {

			DatosFiscalesEmpresaJPA datosFiscales = getDatosFiscalesEmpresaDAO.getDatosFiscalesPorEmpresa(codEmpresa);

			DatosFiscalesDTO data = new DatosFiscalesDTO();

			data.setDescCodigoPostal(datosFiscales.getCodPostal());
			data.setDescDireccion(datosFiscales.getDireccion());
			data.setDescPoblacion(datosFiscales.getPoblacion());
			data.setDescProvincia(datosFiscales.getProvincia());
			data.setDescRazonSocial(datosFiscales.getProvincia());
			data.setNumCif(datosFiscales.getCif());
			data.setCodigo(datosFiscales.getCodEmpresa());
			
			String codigoPais = datosFiscales.getCodPais();			
			data.setDescPais(this.getDatosFiscalesEmpresaDAO.getDescPais(codigoPais));

			output.setDatos(data);

		} catch (Exception e) {

			establecerSalidaError(e, "getDatosFiscalesPorEmpresa");
			throw new ApplicationException(e.getMessage());
		}

			
		return output;
	}
	
	
	
	  private void establecerSalidaError(Exception exception, String metodo) {

		    String login = this.securityService.getPrincipal().getLogin();
		    
		    this.logger.error("({}-{}) ERROR - {}{} {} {}","setPOSIXFilePermissions(GESADUAN)","establecerSalidaError",metodo,login,exception.getClass().getSimpleName(),exception.getMessage());
	  }

}
