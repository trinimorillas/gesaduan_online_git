package es.mercadona.gesaduan.business.declaracionesdevalorapi.getvddocument.v2.impl;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.declaracionesdevalorapi.getvddocument.v2.VDDocumentCSVService;
import es.mercadona.gesaduan.dao.declaracionesdevalorapi.getvddocument.v2.GetVDDocApiDAO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvddocument.v2.InputValueDeclarationDocumentDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvddocument.v2.OutputDeclaracionesDeValorDocCabDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvddocument.v2.OutputDeclaracionesDeValorDocLinDTO;

public class VDDocumentCSVServiceImpl implements VDDocumentCSVService {

	@Inject
	private GetVDDocApiDAO getDVDocumentoOnlineDAO;

	@Override
	public OutputDeclaracionesDeValorDocCabDTO preparaDocumentoCSV(InputValueDeclarationDocumentDTO input) {
		// Obtiene los datos del informe (estructura del informe)
		OutputDeclaracionesDeValorDocCabDTO outDVDocumentoDTO = getDVDocumentoOnlineDAO.getDatosDocumento(input);
				
		// Prepara el informe
		outDVDocumentoDTO.setFicheroCSV(preparaDocumento(outDVDocumentoDTO));
		
		// Devuelve el objeto con el fichero del informe en bytes 		
		return outDVDocumentoDTO;
	}

	private byte[] preparaDocumento(OutputDeclaracionesDeValorDocCabDTO outDVDocumentoDTO) {
				
		// Prepara el informe
		String documentoStr = "";
		
		// Prepara cabecera
		documentoStr += preparaCabeceraDocumento(outDVDocumentoDTO);
		
		// Prepara lineas
		documentoStr += preparaLineasDocumento(outDVDocumentoDTO);
		
		return documentoStr.getBytes();
	}	
	
	private String preparaCabeceraDocumento(OutputDeclaracionesDeValorDocCabDTO outDVDocumentoDTO) {
		// Prepara el informe
		StringBuilder cabeceraStrDV = new StringBuilder();
		
		// Prepara cabecera
		cabeceraStrDV.append(addComillas("CAB")).append("|");
		cabeceraStrDV.append(outDVDocumentoDTO.getCodigoDeclaracion()).append("|");
		cabeceraStrDV.append(outDVDocumentoDTO.getAnyoDeclaracion()).append("|");
		cabeceraStrDV.append(outDVDocumentoDTO.getVersionDeclaracion()).append("|");
		cabeceraStrDV.append(addComillas(outDVDocumentoDTO.getFechaDeclaracion())).append("|");
		cabeceraStrDV.append(addComillas(outDVDocumentoDTO.getNumPedido())).append("|");
		cabeceraStrDV.append(addComillas(outDVDocumentoDTO.getNombreOrigen())).append("|");
		cabeceraStrDV.append(addComillas(outDVDocumentoDTO.getProvinciaOrigen())).append("|");
		cabeceraStrDV.append(addComillas(outDVDocumentoDTO.getCondicionesEntrega())).append("|");
		cabeceraStrDV.append(addComillas(outDVDocumentoDTO.getExportadorNombre())).append("|");
		cabeceraStrDV.append(addComillas(outDVDocumentoDTO.getExportadorDireccion())).append("|");
		cabeceraStrDV.append(addComillas(outDVDocumentoDTO.getExportadorCP())).append("|");
		cabeceraStrDV.append(addComillas(outDVDocumentoDTO.getExportadorPoblacion())).append("|");
		cabeceraStrDV.append(addComillas(outDVDocumentoDTO.getExportadorProvincia())).append("|");
		cabeceraStrDV.append(addComillas(outDVDocumentoDTO.getExportadorNIF())).append("|");
		cabeceraStrDV.append(addComillas(outDVDocumentoDTO.getImportadorNombre())).append("|");
		cabeceraStrDV.append(addComillas(outDVDocumentoDTO.getImportadorDireccion())).append("|");
		cabeceraStrDV.append(addComillas(outDVDocumentoDTO.getImportadorCP())).append("|");
		cabeceraStrDV.append(addComillas(outDVDocumentoDTO.getImportadorPoblacion())).append("|");
		cabeceraStrDV.append(addComillas(outDVDocumentoDTO.getImportadorProvincia())).append("|");
		cabeceraStrDV.append(addComillas(outDVDocumentoDTO.getImportadorNIF())).append("|");
		cabeceraStrDV.append(addComillas(outDVDocumentoDTO.getTxtInfoREA())).append("|");
		cabeceraStrDV.append(addComillas(outDVDocumentoDTO.getTxtInfoLPC())).append("|");
		cabeceraStrDV.append(addComillas(outDVDocumentoDTO.getTxtInfoGeneral())).append("|");	
		cabeceraStrDV.append(addComillas("F")).append("|");			
		
		if (!outDVDocumentoDTO.getNumDosier().equals("null")) {
			cabeceraStrDV.append(outDVDocumentoDTO.getNumDosier()).append("|");
			cabeceraStrDV.append(outDVDocumentoDTO.getAnyoDosier()).append("|");
			cabeceraStrDV.append(addComillas(outDVDocumentoDTO.getFechaDosier())).append("\r\n");
		} else {
			cabeceraStrDV.append("").append("|");
			cabeceraStrDV.append("").append("|");
			cabeceraStrDV.append(addComillas("")).append("\r\n");			
		}
		
		return cabeceraStrDV.toString();
	}		
	
	private String preparaLineasDocumento(OutputDeclaracionesDeValorDocCabDTO outDVDocumentoDTO) {
		// Prepara el informe
		StringBuilder lineasStrDV = new StringBuilder();
		
		// Prepara las lineas
		if (!outDVDocumentoDTO.getLineas().isEmpty()) {
			for (OutputDeclaracionesDeValorDocLinDTO linea : outDVDocumentoDTO.getLineas()) {
				lineasStrDV.append(addComillas("LIN")).append("|");
				lineasStrDV.append(linea.getCodigoDeclaracion()).append("|");				
				lineasStrDV.append(linea.getAnyoDeclaracion()).append("|");
				lineasStrDV.append(linea.getVersionDeclaracion()).append("|");
				if (linea.getTipoItem().equals("PR")) {
					lineasStrDV.append(addComillas("PRODUCTO")).append("|");
				} else if (linea.getTipoItem().equals("EN")) {
					lineasStrDV.append(addComillas("ENVASE")).append("|");
				} else {
					lineasStrDV.append(addComillas(linea.getTipoLinea())).append("|");
				}
				lineasStrDV.append(linea.getCodigoTaric()).append("|");
				if (linea.getTipoItem().equals("PR") || linea.getTipoItem().equals("EN")) {
					lineasStrDV.append(linea.getCodigoProducto()).append("|");
					lineasStrDV.append(addComillas(linea.getNombreProducto())).append("|");
				} else {
					lineasStrDV.append("").append("|");
					lineasStrDV.append(addComillas("")).append("|");
				}				
				lineasStrDV.append(addComillas(linea.getMarca())).append("|");
				lineasStrDV.append(addComillas(linea.getCodigoRea())).append("|");
				lineasStrDV.append(addComillas(linea.getPaisOrigen())).append("|");
				lineasStrDV.append(addComillas(linea.getLpc())).append("|");
				lineasStrDV.append(linea.getNumeroBultos()).append("|");
				lineasStrDV.append(addComillas(linea.getTipoBultos())).append("|");
				lineasStrDV.append(linea.getPesoNeto()).append("|");
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

	private String addComillas (String value) {
		StringBuilder valueTemp = new StringBuilder();
		valueTemp.append("\"").append(value).append("\"");
		return valueTemp.toString();
	}
	
}
