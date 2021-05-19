package es.mercadona.gesaduan.business.declaracionesdevalorapi.getdvdocumento.v1.impl;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.declaracionesdevalorapi.getdvdocumento.v1.DVDocumentoCSVService;
import es.mercadona.gesaduan.dao.declaracionesdevalorapi.getdvdocumento.v1.GetDVDocumentoApiDAO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getdvdocumento.v1.InputValueDeclarationDocumentDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getdvdocumento.v1.OutputDeclaracionesDeValorDocCabDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getdvdocumento.v1.OutputDeclaracionesDeValorDocLinDTO;


public class DVDocumentoCSVServiceImpl implements DVDocumentoCSVService {

	@Inject
	private GetDVDocumentoApiDAO getDVDocumentoOnlineDAO;

	@Override
	public OutputDeclaracionesDeValorDocCabDTO preparaDocumentoCSV(InputValueDeclarationDocumentDTO input) {

		// Obtiene los datos del informe (estructura del informe)
		OutputDeclaracionesDeValorDocCabDTO outDVDocumentoDTO = getDVDocumentoOnlineDAO.getDatosDocumento(input);
				
		// prepara el informe
		outDVDocumentoDTO.setFicheroCSV(preparaDocumento(outDVDocumentoDTO));
		
		// devuelve el objeto con el fichero del informe en bytes 		
		return outDVDocumentoDTO;

	}

	private byte[] preparaDocumento(OutputDeclaracionesDeValorDocCabDTO outDVDocumentoDTO) {
				
		// prepara el informe
		String documentoStr = "";
		
		// prepara cabecera
		documentoStr += preparaCabeceraDocumento(outDVDocumentoDTO);
		
		// preparalineas
		documentoStr += preparaLineasDocumento(outDVDocumentoDTO);
		
		return documentoStr.getBytes();

	}	
	
	private String preparaCabeceraDocumento(OutputDeclaracionesDeValorDocCabDTO outDVDocumentoDTO) {
		
		// prepara el informe
		StringBuilder cabeceraStrDV = new StringBuilder();
		
		// prepara cabecera
		cabeceraStrDV.append("CAB").append("|");
		cabeceraStrDV.append(outDVDocumentoDTO.getCodigoDeclaracion()).append("|");
		cabeceraStrDV.append(outDVDocumentoDTO.getAnyoDeclaracion()).append("|");
		cabeceraStrDV.append(outDVDocumentoDTO.getVersionDeclaracion()).append("|");
		cabeceraStrDV.append(outDVDocumentoDTO.getFechaDeclaracion()).append("|");
		cabeceraStrDV.append(outDVDocumentoDTO.getNumPedido()).append("|"); /* nunmero de pedido ??? */
		cabeceraStrDV.append(outDVDocumentoDTO.getNombreOrigen()).append("|");
		cabeceraStrDV.append(outDVDocumentoDTO.getProvinciaOrigen()).append("|");
		cabeceraStrDV.append(outDVDocumentoDTO.getCondicionesEntrega()).append("|");
		cabeceraStrDV.append(outDVDocumentoDTO.getExportadorNombre()).append("|");		
		cabeceraStrDV.append(outDVDocumentoDTO.getExportadorDireccion()).append("|");
		cabeceraStrDV.append(outDVDocumentoDTO.getExportadorCP()).append("|");
		cabeceraStrDV.append(outDVDocumentoDTO.getExportadorPoblacion()).append("|");
		cabeceraStrDV.append(outDVDocumentoDTO.getExportadorProvincia()).append("|");
		cabeceraStrDV.append(outDVDocumentoDTO.getExportadorNIF()).append("|");
		cabeceraStrDV.append(outDVDocumentoDTO.getImportadorNombre()).append("|");
		cabeceraStrDV.append(outDVDocumentoDTO.getImportadorDireccion()).append("|");
		cabeceraStrDV.append(outDVDocumentoDTO.getImportadorCP()).append("|");
		cabeceraStrDV.append(outDVDocumentoDTO.getImportadorPoblacion()).append("|");
		cabeceraStrDV.append(outDVDocumentoDTO.getImportadorProvincia()).append("|");				
		cabeceraStrDV.append(outDVDocumentoDTO.getImportadorNIF()).append("|");
		cabeceraStrDV.append(outDVDocumentoDTO.getTxtInfoREA()).append("|");
		cabeceraStrDV.append(outDVDocumentoDTO.getTxtInfoLPC()).append("|");				
		cabeceraStrDV.append(outDVDocumentoDTO.getTxtInfoGeneral()).append("|");
		cabeceraStrDV.append("F").append("|");
		cabeceraStrDV.append(outDVDocumentoDTO.getNumDosier()).append("|");
		cabeceraStrDV.append(outDVDocumentoDTO.getAnyoDosier()).append("|");
		cabeceraStrDV.append(outDVDocumentoDTO.getFechaDosier()).append("\r\n");		
		
		return cabeceraStrDV.toString();

	}		
	
	private String preparaLineasDocumento(OutputDeclaracionesDeValorDocCabDTO outDVDocumentoDTO) {
		
		// prepara el informe
		StringBuilder lineasStrDV = new StringBuilder();
		
		// prepara las lineas
		if (!outDVDocumentoDTO.getLineas().isEmpty()) {
			
			for (OutputDeclaracionesDeValorDocLinDTO linea : outDVDocumentoDTO.getLineas()) {
				
				lineasStrDV.append("LIN").append("|");
				lineasStrDV.append(linea.getCodigoDeclaracion()).append("|");				
				lineasStrDV.append(linea.getAnyoDeclaracion()).append("|");
				lineasStrDV.append(linea.getVersionDeclaracion()).append("|");
				lineasStrDV.append(linea.getTipoLinea()).append("|");
				lineasStrDV.append(linea.getCodigoTaric()).append("|");
				lineasStrDV.append(linea.getCodigoProducto()).append("|");
				lineasStrDV.append(linea.getNombreProducto()).append("|");
				lineasStrDV.append(linea.getMarca()).append("|");
				lineasStrDV.append(linea.getCodigoRea()).append("|");
				lineasStrDV.append(linea.getPaisOrigen()).append("|");
				lineasStrDV.append(linea.getLpc()).append("|");
				lineasStrDV.append(linea.getNumeroBultos()).append("|");
				lineasStrDV.append(linea.getTipoBultos()).append("|");
				lineasStrDV.append(linea.getPesoBruto()).append("|");
				lineasStrDV.append(linea.getCantidad()).append("|");
				lineasStrDV.append(linea.getVolumen()).append("|");
				lineasStrDV.append(linea.getAlcohol()).append("|");
				lineasStrDV.append(linea.getPlato()).append("|");
				lineasStrDV.append(linea.getPrecio()).append("|");
				lineasStrDV.append(linea.getImporte()).append("\r\n");				
				
			}
			
		}
		
		return lineasStrDV.toString();

	}	
	

	
}
