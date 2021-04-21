package es.mercadona.gesaduan.business.declaracionesdevalor.getdvdocumento.v2.impl;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.declaracionesdevalor.getdvdocumento.v2.DVDocumentoPDFService;
import es.mercadona.gesaduan.dao.declaracionesdevalor.getdvdocumento.v2.GetDVDocumentoDAO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.getdvdocumento.v2.InputDeclaracionesDeValorDocumentoDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.getdvdocumento.v2.OutputDeclaracionesDeValorDocCabDTO;

public class DVDocumentoPDFServiceImpl implements DVDocumentoPDFService {

	@Inject
	private GetDVDocumentoDAO getDVDocumentoDAO;

	@Override
	public OutputDeclaracionesDeValorDocCabDTO preparaDocumentoPDF(InputDeclaracionesDeValorDocumentoDTO input) {

		// Obtiene los datos del informe (estructura del informe)
		OutputDeclaracionesDeValorDocCabDTO outDVDocumentoDTO = getDVDocumentoDAO.getDatosDocumento(input);
				
		// prepara el informe
		outDVDocumentoDTO.setFicheroPDF(preparaDocumento(outDVDocumentoDTO));
		
		// devuelve el objeto con el fichero del informe en bytes 		
		return outDVDocumentoDTO;

	}
	
	private byte[] preparaDocumento(OutputDeclaracionesDeValorDocCabDTO outDVDocumentoDTO) {
		
		// prepara el informe
		String documentoStr = "";
		
		// prepara cabecera
		/*
		documentoStr += preparaCabeceraDocumento(outDVDocumentoDTO);
		
		// preparalineas
		documentoStr += preparaLineasDocumento(outDVDocumentoDTO);
		*/
		
		return documentoStr.getBytes();

	}		

}
