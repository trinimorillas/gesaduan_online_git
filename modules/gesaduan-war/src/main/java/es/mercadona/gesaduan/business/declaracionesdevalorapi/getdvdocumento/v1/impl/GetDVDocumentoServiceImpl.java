package es.mercadona.gesaduan.business.declaracionesdevalorapi.getdvdocumento.v1.impl;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.declaracionesdevalorapi.getdvdocumento.v1.DVDocumentoCSVService;
import es.mercadona.gesaduan.business.declaracionesdevalorapi.getdvdocumento.v1.DVDocumentoPDFService;
import es.mercadona.gesaduan.business.declaracionesdevalorapi.getdvdocumento.v1.GetDVDocumentoService;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getdvdocumento.v1.InputDeclaracionesDeValorDocumentoDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getdvdocumento.v1.OutputDeclaracionesDeValorDocCabDTO;



public class GetDVDocumentoServiceImpl implements GetDVDocumentoService {

	@Inject
	private DVDocumentoPDFService documentoPDFService;
	
	@Inject
	private DVDocumentoCSVService documentoCSVService;	

	@Override
	public OutputDeclaracionesDeValorDocCabDTO preparaDocumento(InputDeclaracionesDeValorDocumentoDTO input) {
				
		OutputDeclaracionesDeValorDocCabDTO outDVDocumentoDTO = null;
		
		// según el tipo de informe
		if (input.getTipoDocumento().equals("pdf")) {
			outDVDocumentoDTO = documentoPDFService.preparaDocumentoPDF(input);
		} 		
		if (input.getTipoDocumento().equals("csv")) {
			outDVDocumentoDTO = documentoCSVService.preparaDocumentoCSV(input);
		} 		
		
		// devuelve el informe 
		
		return outDVDocumentoDTO;

	}

}
