package es.mercadona.gesaduan.business.dosierapi.getdocument.v1.impl;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import es.mercadona.gesaduan.business.dosierapi.getdocument.v1.DocumentPDFService;
import es.mercadona.gesaduan.common.Constantes;
import es.mercadona.gesaduan.dao.dosierapi.getdocument.v1.GetDocumentApiDAO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getdvdocumento.v1.OutputDeclaracionesDeValorDocCabDTO;
import es.mercadona.gesaduan.dto.dosierapi.getdocument.v1.InputDossierDocumentDTO;
import es.mercadona.gesaduan.dto.dosierapi.getdocument.v1.OutputDossierDocHeadDTO;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class DocumentPDFServiceImpl implements DocumentPDFService {

	@Inject
	private GetDocumentApiDAO getDocumentOnlineDAO;
	
	@Inject
	private FileSystemService fileSystemService;

	@Inject
	private ReportingService reportingService;	
	
	@Inject
	private org.slf4j.Logger logger;
	
	private static final String LOG_FILE = "DocumentoPDFServiceImpl(GESADUAN)"; 

	@Override
	public OutputDossierDocHeadDTO preparaDocumentoPDF(InputDossierDocumentDTO input) {

		OutputDossierDocHeadDTO outDVDocumentoDTO = null;
		
		// Obtiene los datos del informe (estructura del informe)
		if (!getDocumentOnlineDAO.isDosierInvalidado(input)) {
			
			outDVDocumentoDTO = getDocumentOnlineDAO.getDatosDocumento(input);		
				
			// prepara el informe
			outDVDocumentoDTO.setFicheroPDF(preparaDocumento(outDVDocumentoDTO));
		} else {
			
			outDVDocumentoDTO = getDocumentOnlineDAO.getDocumentoInvalidado(input);
			
		}
		
		// devuelve el objeto con el fichero del informe en bytes 		
		return outDVDocumentoDTO;

	}
	
	private byte[] preparaDocumento(OutputDossierDocHeadDTO outDVDocumentoDTO) {
		
		// prepara el informe
		byte[] ficheroByte =  null;
		
		try {

		    Locale locale = new Locale("es_ES");
	
		    // Prepara report
            // --------------------------------------

		    final Report report = this.reportingService.newReport("InformeDosier.jrxml", locale);	
		    
		    final Map<String, Object> mapParams = new HashMap<>();	    
		    
		    // Prepara textos traducciones
		    preparaTextosReport(mapParams);
	
		    report.setParameters(mapParams);
		    
		    List<OutputDeclaracionesDeValorDocCabDTO> listaDeclaraciones = new ArrayList<>();
		    List<OutputDeclaracionesDeValorDocCabDTO> declaraciones = outDVDocumentoDTO.getDeclaraciones();
		    
            for (OutputDeclaracionesDeValorDocCabDTO outputDVDocCabDTO : declaraciones) {
              JRBeanCollectionDataSource lineasdataSource = new JRBeanCollectionDataSource(outputDVDocCabDTO.getLineas());
              outputDVDocCabDTO.setLineasDataSource(lineasdataSource);
              listaDeclaraciones.add(outputDVDocCabDTO);
            }

		    report.bindData(listaDeclaraciones, DataRecordType.Bean);		    
		    
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
	        	        
		    report.addSubReport("SUBREPORT", subreportTemplate ,subreportMapParams,listaDeclaraciones, DataRecordType.Bean);
			
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
