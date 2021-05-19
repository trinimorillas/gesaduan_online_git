package es.mercadona.gesaduan.business.declaracionesdevalorapi.getdvdocumento.v1.impl;


import java.io.File;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;

import org.apache.commons.io.FileUtils;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.fwk.core.io.CustomFileAttributes;
import es.mercadona.fwk.core.io.FileSystemItem;
import es.mercadona.fwk.core.io.FileSystemService;
import es.mercadona.fwk.core.jaxrs.ResponseFormat;
import es.mercadona.fwk.core.jaxrs.ResponseTypeInfo;
import es.mercadona.fwk.reporting.DataRecordType;
import es.mercadona.fwk.reporting.Report;
import es.mercadona.fwk.reporting.ReportTemplate;
import es.mercadona.fwk.reporting.ReportingService;
import es.mercadona.gesaduan.business.declaracionesdevalorapi.getdvdocumento.v1.DVDocumentoPDFService;
import es.mercadona.gesaduan.common.Constantes;
import es.mercadona.gesaduan.dao.declaracionesdevalorapi.getdvdocumento.v1.GetDVDocumentoApiDAO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getdvdocumento.v1.InputValueDeclarationDocumentDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getdvdocumento.v1.OutputDeclaracionesDeValorDocCabDTO;

public class DVDocumentoPDFServiceImpl implements DVDocumentoPDFService {

	@Inject
	private GetDVDocumentoApiDAO getDVDocumentoOnlineDAO;
	
	@Inject
	private FileSystemService fileSystemService;

	@Inject
	private ReportingService reportingService;	
	
	@Inject
	private org.slf4j.Logger logger;	
	
	private static final String LOG_FILE = "DVDocumentoPDFServiceImpl(GESADUAN)"; 		

	@Override
	public OutputDeclaracionesDeValorDocCabDTO preparaDocumentoPDF(InputValueDeclarationDocumentDTO input) {

		// Obtiene los datos del informe (estructura del informe)
		OutputDeclaracionesDeValorDocCabDTO outDVDocumentoDTO = getDVDocumentoOnlineDAO.getDatosDocumento(input);		
				
		// prepara el informe
		outDVDocumentoDTO.setFicheroPDF(preparaDocumento(outDVDocumentoDTO));
		
		// devuelve el objeto con el fichero del informe en bytes 		
		return outDVDocumentoDTO;

	}
	
	private byte[] preparaDocumento(OutputDeclaracionesDeValorDocCabDTO outDVDocumentoDTO) {
		
		// prepara el informe
		String documentoStr = "";
		byte[] ficheroByte =  null;
		
		try {
		
		    Locale locale = new Locale("es_ES");
	
		    // Prepara report
            // --------------------------------------

		    final Report report = this.reportingService.newReport("InformeDeclaracionValor.jrxml", locale);	
		    
		    final Map<String, Object> mapParams = new HashMap<>();	    
		    
		    // Prepara textos traducciones
		    preparaTextosReport(mapParams);
		    
		    // Prepara parametros de cabecera
		    preparaParametrosReport(outDVDocumentoDTO, mapParams);
	
		    report.setParameters(mapParams);
		    
            report.bindData(outDVDocumentoDTO.getLineas(),DataRecordType.Bean);
            		    
		    // Prepara Subreport 
            // --------------------------------------
                        
		    ReportTemplate subreportTemplate = null;
	        switch(outDVDocumentoDTO.getTipoInforme())
	        {
	            case "BASE":
	            	subreportTemplate = this.reportingService.getReportTemplate("InformeDeclaracionValorLinBase.jrxml");
	                break;
	            case "VOLUMEN":
	            	subreportTemplate = this.reportingService.getReportTemplate("InformeDeclaracionValorLinVolumen.jrxml");
	                break;
	            case "VOLUMEN_ALCOHOL":
	            	subreportTemplate = this.reportingService.getReportTemplate("InformeDeclaracionValorLinVolumenAlcohol.jrxml");
	                break;
	            default:
	            	subreportTemplate = this.reportingService.getReportTemplate("InformeDeclaracionValorLinBase.jrxml");
	        }
	        
		    final Map<String, Object> subreportMapParams = new HashMap<>();
		    
		    // Prepara textos traducciones
		    preparaTextosSubReport(subreportMapParams);		    
	        	        
		    report.addSubReport("SUBREPORT", subreportTemplate ,subreportMapParams,outDVDocumentoDTO.getLineas(), DataRecordType.Bean);
			
		    // Prepra un fichero temporal para genera el PDF
		    final CustomFileAttributes tempFileInfo = new CustomFileAttributes();
		    tempFileInfo.setUuid(UUID.randomUUID().toString());
		    tempFileInfo.setContentType(ResponseTypeInfo.get(ResponseFormat.Pdf).mimeType);
		    tempFileInfo.setFileName("InformeDeclaracionValor.pdf");
		    final FileSystemItem fsi = this.fileSystemService.createTemporary(tempFileInfo);
	
		    // Generate main report with data and store in created file
		    report.generate(ResponseFormat.Pdf, fsi.getFile());
		    
		    // Obtiene un fichero 
		    File file = fsi.getFile();
			
		    ficheroByte = FileUtils.readFileToByteArray(file);
	    
		} catch (Exception e) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG,LOG_FILE,"preparaDocumento",e.getClass().getSimpleName(),e.getMessage());	
			throw new ApplicationException(e.getMessage());			
		}
		
		return ficheroByte;

	}

	private void preparaParametrosReport(OutputDeclaracionesDeValorDocCabDTO outDVDocumentoDTO,final Map<String, Object> mapParams) {
		
		mapParams.put("codigoDeclaracion", outDVDocumentoDTO.getCodigoDeclaracion());
		mapParams.put("anyoDeclaracion", outDVDocumentoDTO.getAnyoDeclaracion());
		mapParams.put("fechaDeclaracion", outDVDocumentoDTO.getFechaDeclaracion());
		mapParams.put("numDosier", outDVDocumentoDTO.getNumDosier());
		mapParams.put("anyoDosier", outDVDocumentoDTO.getAnyoDosier());		
		mapParams.put("fechaDosier", outDVDocumentoDTO.getFechaDosier());
		mapParams.put("numPedido", outDVDocumentoDTO.getNumPedido());		
		mapParams.put("tipoOrigen", outDVDocumentoDTO.getTipoOrigen());		
		mapParams.put("nombreOrigen", outDVDocumentoDTO.getNombreOrigen());
		mapParams.put("provinciaOrigen", outDVDocumentoDTO.getProvinciaOrigen());
		mapParams.put("condicionesEntrega", outDVDocumentoDTO.getCondicionesEntrega());
		mapParams.put("exportadorNombre", outDVDocumentoDTO.getExportadorNombre());
		mapParams.put("exportadorDireccion", outDVDocumentoDTO.getExportadorDireccion());
		mapParams.put("exportadorCP", outDVDocumentoDTO.getExportadorCP());
		mapParams.put("exportadorPoblacion", outDVDocumentoDTO.getExportadorPoblacion());
		mapParams.put("exportadorProvincia", outDVDocumentoDTO.getExportadorProvincia());
		mapParams.put("exportadorNIF", outDVDocumentoDTO.getExportadorNIF());
		mapParams.put("importadorNombre", outDVDocumentoDTO.getImportadorNombre());
		mapParams.put("importadorDireccion", outDVDocumentoDTO.getImportadorDireccion());
		mapParams.put("importadorCP", outDVDocumentoDTO.getImportadorCP());
		mapParams.put("importadorPoblacion", outDVDocumentoDTO.getImportadorPoblacion());
		mapParams.put("importadorProvincia", outDVDocumentoDTO.getImportadorProvincia());
		mapParams.put("importadorNIF", outDVDocumentoDTO.getImportadorNIF());
		mapParams.put("txtInfoREA", outDVDocumentoDTO.getTxtInfoREA());
		mapParams.put("txtInfoLPC", outDVDocumentoDTO.getTxtInfoLPC());
		mapParams.put("txtInfoGeneral", outDVDocumentoDTO.getTxtInfoGeneral());				
		
	}

	private void preparaTextosReport(final Map<String, Object> mapParams) {
		
		mapParams.put("report.title","Titulo Reporte");
		mapParams.put("report.subtitle","Informe.");

		mapParams.put("header.declaracionValor","FACTURA - Nº");
		mapParams.put("header.declaracionValor2","DECLARACIÓN VALOR - Nº");
		mapParams.put("header.fechaDV","Fecha DV");
		mapParams.put("header.fechaDeclaracion","FECHA FACTURA");
		mapParams.put("header.dosier","DOSIER - Nº");
		mapParams.put("header.fechaDosier","FECHA DOSIER");
		mapParams.put("header.pedido","Nº Pedido");		
		mapParams.put("header.proveedor","Proveedor");
		mapParams.put("header.bloqueLogistico","Bloque Logístico");		
		mapParams.put("header.provinciaCarga","Provincia de carga (Origen)");
		mapParams.put("header.condicionesEntrega","Condiciones de entrega");
		mapParams.put("header.exportador","EXPEDIDOR / EXPORTADOR");
		mapParams.put("header.destinatario","DESTINATARIO / IMPORTADOR");
		mapParams.put("header.nif","NIF");

		mapParams.put("label.capacidad","CAP");
		mapParams.put("label.nombre","Nombre");
		mapParams.put("label.planta","P");
		mapParams.put("report.footer.page","Pagina {0} de"); 		
		
	}	
	
	private void preparaTextosSubReport(final Map<String, Object> mapParams) {
		
		mapParams.put("column.codigo","Código");
		mapParams.put("column.producto","Producto");
		mapParams.put("column.marca","Marca");
		mapParams.put("column.lpc","LpC");
		mapParams.put("column.bultos","Bultos");
		mapParams.put("column.tipo","Tipo");
		mapParams.put("column.pesoNeto","P.Neto");
		mapParams.put("column.pesoBruto","P.Bruto");
		mapParams.put("column.cantidad","Cantidad");
		mapParams.put("column.volumen","Vol.");
		mapParams.put("column.alcohol","Alc.");
		mapParams.put("column.plato","Plato");
		mapParams.put("column.precio","Precio");
		mapParams.put("column.importe","Importe");
		mapParams.put("column.kg","(Kg)");
		mapParams.put("column.l","(L)");
		mapParams.put("column.g","(º)");
		mapParams.put("column.euro","(eur)");
		mapParams.put("column.codigoREA","Cód. REA");
		mapParams.put("column.paisOrigen","País de origen");
		
	}			

}
