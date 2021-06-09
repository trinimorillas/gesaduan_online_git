package es.mercadona.gesaduan.business.declaracionesdevalorapi.getvddocument.v1.impl;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.declaracionesdevalorapi.getvddocument.v1.VDDocumentCSVService;
import es.mercadona.gesaduan.business.declaracionesdevalorapi.getvddocument.v1.VDDocumentPDFService;
import es.mercadona.gesaduan.business.declaracionesdevalorapi.getvddocument.v1.GetVDDocumentService;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvddocument.v1.InputValueDeclarationDocumentDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvddocument.v1.OutputDeclaracionesDeValorDocCabDTO;



public class GetVDDocumentServiceImpl implements GetVDDocumentService {

	@Inject
	private VDDocumentPDFService documentoPDFService;
	
	@Inject
	private VDDocumentCSVService documentoCSVService;	

	@Override
	public OutputDeclaracionesDeValorDocCabDTO preparaDocumento(InputValueDeclarationDocumentDTO input) {
				
		OutputDeclaracionesDeValorDocCabDTO outDVDocumentoDTO = null;
		
		// según el tipo de informe
		if (input.getDocumentType().equals("pdf")) {
			outDVDocumentoDTO = documentoPDFService.preparaDocumentoPDF(input);
		} 		
		if (input.getDocumentType().equals("csv")) {
			outDVDocumentoDTO = documentoCSVService.preparaDocumentoCSV(input);
		} 		
		
		// devuelve el informe 
		
		return outDVDocumentoDTO;

	}

}
