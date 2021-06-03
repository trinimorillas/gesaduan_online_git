package es.mercadona.gesaduan.business.dosierapi.getdossierdocument.v1.impl;

import java.util.List;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.dosierapi.getdossierdocument.v1.DocumentCSVService;
import es.mercadona.gesaduan.dao.dosierapi.getdossierdocument.v1.GetDocumentApiDAO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvddocument.v1.OutputDeclaracionesDeValorDocCabDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvddocument.v1.OutputDeclaracionesDeValorDocLinDTO;
import es.mercadona.gesaduan.dto.dosierapi.getdossierdocument.v1.InputDossierDocumentDTO;
import es.mercadona.gesaduan.dto.dosierapi.getdossierdocument.v1.OutputDossierDocHeadDTO;


public class DocumentCSVServiceImpl implements DocumentCSVService {

	@Inject
	private GetDocumentApiDAO getDocumentOnlineDAO;

	@Override
	public OutputDossierDocHeadDTO preparaDocumentoCSV(InputDossierDocumentDTO input) {

		// Obtiene los datos del informe (estructura del informe)
		OutputDossierDocHeadDTO outDVDocumentoDTO = getDocumentOnlineDAO.getDatosDocumento(input);
				
		// prepara el informe
		outDVDocumentoDTO.setFicheroCSV(preparaDocumento(outDVDocumentoDTO));
		
		// devuelve el objeto con el fichero del informe en bytes 		
		return outDVDocumentoDTO;

	}

	private byte[] preparaDocumento(OutputDossierDocHeadDTO outDVDocumentoDTO) {
				
		// prepara el informe
		StringBuilder documentoStr = new StringBuilder();
		
		List<OutputDeclaracionesDeValorDocCabDTO> declaraciones = outDVDocumentoDTO.getDeclaraciones();
		
		// prepara cabecera
		if (!declaraciones.isEmpty()) {

			for (OutputDeclaracionesDeValorDocCabDTO outDVDocumentoCabDTO : declaraciones) {
				
				documentoStr.append(preparaCabeceraDocumento(outDVDocumentoCabDTO));
			
				// preparalineas
				documentoStr.append(preparaLineasDocumento(outDVDocumentoCabDTO));
			
			}
			
		}
		
		return documentoStr.toString().getBytes();

	}	
	
	private String preparaCabeceraDocumento(OutputDeclaracionesDeValorDocCabDTO outDVDocumentoDTO) {
		
		// prepara el informe
		StringBuilder cabeceraStr = new StringBuilder();
		
		// prepara cabecera
		cabeceraStr.append(addComillas("CAB")).append("|");
		cabeceraStr.append(outDVDocumentoDTO.getCodigoDeclaracion()).append("|");
		cabeceraStr.append(outDVDocumentoDTO.getAnyoDeclaracion()).append("|");
		cabeceraStr.append(outDVDocumentoDTO.getVersionDeclaracion()).append("|");
		cabeceraStr.append(addComillas(outDVDocumentoDTO.getFechaDeclaracion())).append("|");
		cabeceraStr.append(addComillas(outDVDocumentoDTO.getNumPedido())).append("|");
		cabeceraStr.append(addComillas(outDVDocumentoDTO.getNombreOrigen())).append("|");
		cabeceraStr.append(addComillas(outDVDocumentoDTO.getProvinciaOrigen())).append("|");
		cabeceraStr.append(addComillas(outDVDocumentoDTO.getCondicionesEntrega())).append("|");
		cabeceraStr.append(addComillas(outDVDocumentoDTO.getExportadorNombre())).append("|");
		cabeceraStr.append(addComillas(outDVDocumentoDTO.getExportadorDireccion())).append("|");
		cabeceraStr.append(addComillas(outDVDocumentoDTO.getExportadorCP())).append("|");
		cabeceraStr.append(addComillas(outDVDocumentoDTO.getExportadorPoblacion())).append("|");
		cabeceraStr.append(addComillas(outDVDocumentoDTO.getExportadorProvincia())).append("|");
		cabeceraStr.append(addComillas(outDVDocumentoDTO.getExportadorNIF())).append("|");
		cabeceraStr.append(addComillas(outDVDocumentoDTO.getImportadorNombre())).append("|");
		cabeceraStr.append(addComillas(outDVDocumentoDTO.getImportadorDireccion())).append("|");
		cabeceraStr.append(addComillas(outDVDocumentoDTO.getImportadorCP())).append("|");
		cabeceraStr.append(addComillas(outDVDocumentoDTO.getImportadorPoblacion())).append("|");
		cabeceraStr.append(addComillas(outDVDocumentoDTO.getImportadorProvincia())).append("|");
		cabeceraStr.append(addComillas(outDVDocumentoDTO.getImportadorNIF())).append("|");
		cabeceraStr.append(addComillas(outDVDocumentoDTO.getTxtInfoREA())).append("|");
		cabeceraStr.append(addComillas(outDVDocumentoDTO.getTxtInfoLPC())).append("|");
		cabeceraStr.append(addComillas(outDVDocumentoDTO.getTxtInfoGeneral())).append("|");		
		cabeceraStr.append(addComillas(outDVDocumentoDTO.getTxtInfoGeneral())).append("|");
		
		if (!outDVDocumentoDTO.getNumDosier().equals("null")) {
			cabeceraStr.append(outDVDocumentoDTO.getNumDosier()).append("|");
			cabeceraStr.append(outDVDocumentoDTO.getAnyoDosier()).append("|");
			cabeceraStr.append(addComillas(outDVDocumentoDTO.getFechaDosier())).append("\r\n");
		} else {
			cabeceraStr.append("").append("|");
			cabeceraStr.append("").append("|");
			cabeceraStr.append(addComillas("")).append("\r\n");			
		}
		
		return cabeceraStr.toString();

	}		
	
	private String preparaLineasDocumento(OutputDeclaracionesDeValorDocCabDTO outDocumentoDTO) {
		
		// prepara el informe

		StringBuilder lineasStr = new StringBuilder();

		// prepara las lineas
		if (!outDocumentoDTO.getLineas().isEmpty()) {
			
			for (OutputDeclaracionesDeValorDocLinDTO linea : outDocumentoDTO.getLineas()) {
				
				lineasStr.append(addComillas("LIN")).append("\"|");
				lineasStr.append(linea.getCodigoDeclaracion()).append("|");				
				lineasStr.append(linea.getAnyoDeclaracion()).append("|");
				lineasStr.append(linea.getVersionDeclaracion()).append("|");
				lineasStr.append(addComillas(linea.getTipoLinea())).append("|");
				lineasStr.append(linea.getCodigoTaric()).append("|");
				if (linea.getTipoLinea().equals("PRODUCTO")) {
					lineasStr.append(linea.getCodigoProducto()).append("|");
					lineasStr.append(addComillas(linea.getNombreProducto())).append("|");
				} else {
					lineasStr.append("").append("|");
					lineasStr.append(addComillas("")).append("|");
				}				
				lineasStr.append(addComillas(linea.getMarca())).append("|");
				lineasStr.append(addComillas(linea.getCodigoRea())).append("|");
				lineasStr.append(addComillas(linea.getPaisOrigen())).append("|");
				lineasStr.append(addComillas(linea.getLpc())).append("|");
				lineasStr.append(linea.getNumeroBultos()).append("|");
				lineasStr.append(addComillas(linea.getTipoBultos())).append("|");
				lineasStr.append(linea.getPesoNeto()).append("|");
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
	
	private String addComillas (String value) {
		
		StringBuilder valueTemp = new StringBuilder();
		
		valueTemp.append("\"").append(value).append("\"");
		
		return valueTemp.toString();
	}	
	
}
