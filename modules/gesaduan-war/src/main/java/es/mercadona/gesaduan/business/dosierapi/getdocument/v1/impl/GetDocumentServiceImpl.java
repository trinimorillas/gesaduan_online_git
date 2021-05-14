package es.mercadona.gesaduan.business.dosierapi.getdocument.v1.impl;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.dosierapi.getdocument.v1.DocumentCSVService;
import es.mercadona.gesaduan.business.dosierapi.getdocument.v1.DocumentPDFService;
import es.mercadona.gesaduan.business.dosierapi.getdocument.v1.GetDocumentService;
import es.mercadona.gesaduan.dto.dosierapi.getdocument.v1.InputDossierDocumentDTO;
import es.mercadona.gesaduan.dto.dosierapi.getdocument.v1.OutputDossierDocHeadDTO;


public class GetDocumentServiceImpl implements GetDocumentService {

	@Inject
	private DocumentPDFService documentPDFService;
	
	@Inject
	private DocumentCSVService documentCSVService;	

	@Override
	public OutputDossierDocHeadDTO preparaDocumento(InputDossierDocumentDTO input) {
				
		OutputDossierDocHeadDTO outDVDocumentoDTO = null;
		
		// según el tipo de informe
		if (input.getDocumentType().equals("pdf")) {
			outDVDocumentoDTO = documentPDFService.preparaDocumentoPDF(input);
		} 		
		if (input.getDocumentType().equals("csv")) {
			outDVDocumentoDTO = documentCSVService.preparaDocumentoCSV(input);
		} 		
		
		// devuelve el informe 
		
		return outDVDocumentoDTO;

	}

}
