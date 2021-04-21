package es.mercadona.gesaduan.business.declaracionesdevalor.getdvdocumento.v2.impl;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.declaracionesdevalor.getdvdocumento.v2.DVDocumentoCSVService;
import es.mercadona.gesaduan.business.declaracionesdevalor.getdvdocumento.v2.DVDocumentoPDFService;
import es.mercadona.gesaduan.business.declaracionesdevalor.getdvdocumento.v2.GetDVDocumentoService;
import es.mercadona.gesaduan.dto.declaracionesdevalor.getdvdocumento.v2.InputDeclaracionesDeValorDocumentoDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.getdvdocumento.v2.OutputDeclaracionesDeValorDocCabDTO;

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
