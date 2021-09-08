package es.mercadona.gesaduan.business.declaracionesdevalorapi.getvddocument.v2.impl;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.declaracionesdevalorapi.getvddocument.v2.VDDocumentCSVService;
import es.mercadona.gesaduan.business.declaracionesdevalorapi.getvddocument.v2.VDDocumentPDFService;
import es.mercadona.gesaduan.dao.declaracionesdevalorapi.getvddocument.v2.GetVDDocApiDAO;
import es.mercadona.gesaduan.business.declaracionesdevalorapi.getvddocument.v2.GetVDDocumentService;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvddocument.v2.InputValueDeclarationDocumentDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvddocument.v2.OutputDeclaracionesDeValorDocCabDTO;
import es.mercadona.gesaduan.exception.EnumGesaduanException;
import es.mercadona.gesaduan.exception.GesaduanException;

public class GetVDDocumentServiceImpl implements GetVDDocumentService {

	@Inject
	private VDDocumentPDFService documentoPDFService;
	
	@Inject
	private VDDocumentCSVService documentoCSVService;
	
	@Inject
	private GetVDDocApiDAO getVDDocumentApiDAO;

	@Override
	public OutputDeclaracionesDeValorDocCabDTO preparaDocumento(InputValueDeclarationDocumentDTO input) throws GesaduanException {
		OutputDeclaracionesDeValorDocCabDTO outDVDocumentoDTO = null;
		
		if (!getVDDocumentApiDAO.esCorrecta(input)) {
			throw new GesaduanException(EnumGesaduanException.DV_INCORRECTA);
		} else {
			if (input.getDocumentType().equals("pdf")) {
				outDVDocumentoDTO = documentoPDFService.preparaDocumentoPDF(input);
			} 		
			if (input.getDocumentType().equals("csv")) {
				outDVDocumentoDTO = documentoCSVService.preparaDocumentoCSV(input);
			}
		}
		
		return outDVDocumentoDTO;
	}

}
