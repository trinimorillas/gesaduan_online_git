package es.mercadona.gesaduan.business.declaracionesdevalorapi.getdvdocumento.v1.impl;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.declaracionesdevalorapi.getdvdocumento.v1.DVDocumentoCSVService;
import es.mercadona.gesaduan.dao.declaracionesdevalorapi.getdvdocumento.v1.GetDVDocumentoApiDAO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getdvdocumento.v1.InputDeclaracionesDeValorDocumentoDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getdvdocumento.v1.OutputDeclaracionesDeValorDocCabDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getdvdocumento.v1.OutputDeclaracionesDeValorDocLinDTO;


public class DVDocumentoCSVServiceImpl implements DVDocumentoCSVService {

	@Inject
	private GetDVDocumentoApiDAO getDVDocumentoOnlineDAO;

	@Override
	public OutputDeclaracionesDeValorDocCabDTO preparaDocumentoCSV(InputDeclaracionesDeValorDocumentoDTO input) {

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
		StringBuilder cabeceraStr = new StringBuilder();
		
		// prepara cabecera
		cabeceraStr.append("CAB").append("|");
		cabeceraStr.append(outDVDocumentoDTO.getCodigoDeclaracion()).append("|");
		cabeceraStr.append(outDVDocumentoDTO.getAnyoDeclaracion()).append("|");
		cabeceraStr.append(outDVDocumentoDTO.getVersionDeclaracion()).append("|");
		cabeceraStr.append(outDVDocumentoDTO.getFechaDeclaracion()).append("|");
		cabeceraStr.append(outDVDocumentoDTO.getCodigoDeclaracion()).append("|"); /* nunmero de pedido ??? */
		cabeceraStr.append(outDVDocumentoDTO.getNombreOrigen()).append("|");
		cabeceraStr.append(outDVDocumentoDTO.getProvinciaOrigen()).append("|");
		cabeceraStr.append(outDVDocumentoDTO.getCondicionesEntrega()).append("|");
		cabeceraStr.append(outDVDocumentoDTO.getExportadorNombre()).append("|");		
		cabeceraStr.append(outDVDocumentoDTO.getExportadorDireccion()).append("|");
		cabeceraStr.append(outDVDocumentoDTO.getExportadorCP()).append("|");
		cabeceraStr.append(outDVDocumentoDTO.getExportadorPoblacion()).append("|");
		cabeceraStr.append(outDVDocumentoDTO.getExportadorProvincia()).append("|");
		cabeceraStr.append(outDVDocumentoDTO.getExportadorNIF()).append("|");
		cabeceraStr.append(outDVDocumentoDTO.getImportadorNombre()).append("|");
		cabeceraStr.append(outDVDocumentoDTO.getImportadorDireccion()).append("|");
		cabeceraStr.append(outDVDocumentoDTO.getImportadorCP()).append("|");
		cabeceraStr.append(outDVDocumentoDTO.getImportadorPoblacion()).append("|");
		cabeceraStr.append(outDVDocumentoDTO.getImportadorProvincia()).append("|");				
		cabeceraStr.append(outDVDocumentoDTO.getImportadorNIF()).append("|");
		cabeceraStr.append(outDVDocumentoDTO.getTxtInfoREA()).append("|");
		cabeceraStr.append(outDVDocumentoDTO.getTxtInfoLPC()).append("|");				
		cabeceraStr.append(outDVDocumentoDTO.getTxtInfoGeneral()).append("|");
		cabeceraStr.append("F").append("|");
		cabeceraStr.append(outDVDocumentoDTO.getNumDosier()).append("|");
		cabeceraStr.append(outDVDocumentoDTO.getAnyoDosier()).append("|");
		cabeceraStr.append(outDVDocumentoDTO.getFechaDosier()).append("\r\n");		
		
		return cabeceraStr.toString();

	}		
	
	private String preparaLineasDocumento(OutputDeclaracionesDeValorDocCabDTO outDVDocumentoDTO) {
		
		// prepara el informe
		StringBuilder lineasStr = new StringBuilder();
		
		// prepara las lineas
		if (!outDVDocumentoDTO.getLineas().isEmpty()) {
			
			for (OutputDeclaracionesDeValorDocLinDTO linea : outDVDocumentoDTO.getLineas()) {
				
				lineasStr.append("LIN").append("|");
				lineasStr.append(linea.getCodigoDeclaracion()).append("|");				
				lineasStr.append(linea.getAnyoDeclaracion()).append("|");
				lineasStr.append(linea.getVersionDeclaracion()).append("|");
				lineasStr.append(linea.getTipoLinea()).append("|");
				lineasStr.append(linea.getCodigoTaric()).append("|");
				lineasStr.append(linea.getCodigoProducto()).append("|");
				lineasStr.append(linea.getNombreProducto()).append("|");
				lineasStr.append(linea.getMarca()).append("|");
				lineasStr.append(linea.getCodigoRea()).append("|");
				lineasStr.append(linea.getPaisOrigen()).append("|");
				lineasStr.append(linea.getLpc()).append("|");
				lineasStr.append(linea.getNumeroBultos()).append("|");
				lineasStr.append(linea.getTipoBultos()).append("|");
				lineasStr.append(linea.getPesoBruto()).append("|");
				lineasStr.append(linea.getCantidad()).append("|");
				lineasStr.append(linea.getVolumen()).append("|");
				lineasStr.append(linea.getAlcohol()).append("|");
				lineasStr.append(linea.getPlato()).append("|");
				lineasStr.append(linea.getPrecio()).append("|");
				lineasStr.append(linea.getImporte()).append("\r\n");				
				
			}
			
		}
		
		return lineasStr.toString();

	}	
	

	
}
