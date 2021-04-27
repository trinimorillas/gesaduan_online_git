package es.mercadona.gesaduan.business.dosierapi.getdocumento.v1.impl;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.dosierapi.getdocumento.v1.DocumentoCSVService;
import es.mercadona.gesaduan.business.dosierapi.getdocumento.v1.DocumentoPDFService;
import es.mercadona.gesaduan.business.dosierapi.getdocumento.v1.GetDocumentoService;
import es.mercadona.gesaduan.dto.dosierapi.getdocumento.v1.InputDosierDocumentoDTO;
import es.mercadona.gesaduan.dto.dosierapi.getdocumento.v1.OutputDosierDocCabDTO;



public class GetDocumentoServiceImpl implements GetDocumentoService {

	@Inject
	private DocumentoPDFService documentoPDFService;
	
	@Inject
	private DocumentoCSVService documentoCSVService;	

	@Override
	public OutputDosierDocCabDTO preparaDocumento(InputDosierDocumentoDTO input) {
				
		OutputDosierDocCabDTO outDVDocumentoDTO = null;
		
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
