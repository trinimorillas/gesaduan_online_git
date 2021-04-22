package es.mercadona.gesaduan.business.declaracionesdevalor.getdvdocumento.v2.impl;


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
import es.mercadona.gesaduan.business.declaracionesdevalor.getdvdocumento.v2.DVDocumentoPDFService;
import es.mercadona.gesaduan.dao.declaracionesdevalor.getdvdocumento.v2.GetDVDocumentoOnlineDAO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.getdvdocumento.v2.InputDeclaracionesDeValorDocumentoDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.getdvdocumento.v2.OutputDeclaracionesDeValorDocCabDTO;

public class DVDocumentoPDFServiceImpl implements DVDocumentoPDFService {

	@Inject
	private GetDVDocumentoOnlineDAO getDVDocumentoOnlineDAO;
	
	@Inject
	private FileSystemService fileSystemService;

	@Inject
	private ReportingService reportingService;	
	
	@Inject
	private org.slf4j.Logger logger;		

	@Override
	public OutputDeclaracionesDeValorDocCabDTO preparaDocumentoPDF(InputDeclaracionesDeValorDocumentoDTO input) {

		// Obtiene los datos del informe (estructura del informe)
		// OutputDeclaracionesDeValorDocCabDTO outDVDocumentoDTO = getDVDocumentoOnlineDAO.getDatosDocumento(input);
		OutputDeclaracionesDeValorDocCabDTO outDVDocumentoDTO = preparaDatos();		
				
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
	
		    // Main Report
		    final Report report = this.reportingService.newReport("InformeDeclaracionValor.jrxml", locale);	
		    
		    final Map<String, Object> mapParams = new HashMap<String, Object>();	    
		    		    
		    mapParams.put("header.factura", "FACTURA");		    
		    mapParams.put("FACTURA", outDVDocumentoDTO.getCodigoDeclaracion());
	
		    report.setParameters(mapParams);
		    
            /*
            if (!outDVDocumentoDTO.getLineas().isEmpty()) {
            	JRBeanCollectionDataSource lineasDataSource = new JRBeanCollectionDataSource(outDVDocumentoDTO.getLineas());
            	outDVDocumentoDTO.setLineasDataSource(lineasDataSource);
            }
            */
            
            report.bindData(outDVDocumentoDTO.getLineas(),DataRecordType.Bean);
		    
		    // Subreport
		    // final ReportTemplate subreportDVTpl = this.reportingService.getReportTemplate("InformeDeclaracionValorSubreport.jrxml");
		    // report.addSubReport("SUBREPORTDV", subreportDVTpl, outDVDocumentoDTO.getLineas(), DataRecordType.Bean);
		    
			
		    // Temporary file to hold the compiled report template
		    final CustomFileAttributes tempFileInfo = new CustomFileAttributes();
		    tempFileInfo.setUuid(UUID.randomUUID().toString());
		    tempFileInfo.setContentType(ResponseTypeInfo.get(ResponseFormat.Pdf).mimeType);
		    tempFileInfo.setFileName("InformeDeclaracionValor.pdf");
		    final FileSystemItem fsi = this.fileSystemService.createTemporary(tempFileInfo);
	
	
		    // Generate main report with data and store in created file
		    report.generate(ResponseFormat.Pdf, fsi.getFile());
		    
		    File file = fsi.getFile();
			
			// prepara cabecera
			/*
			documentoStr += preparaCabeceraDocumento(outDVDocumentoDTO);
			
			// preparalineas
			documentoStr += preparaLineasDocumento(outDVDocumentoDTO);
			*/
		    
		    ficheroByte = FileUtils.readFileToByteArray(file);
	    
		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","DVDocumentoPDFServiceImpl(GESADUAN)","preparaDocumento",e.getClass().getSimpleName(),e.getMessage());	
			throw new ApplicationException(e.getMessage());			
		}
		
		return ficheroByte;

	}		

	
	private OutputDeclaracionesDeValorDocCabDTO preparaDatos() {
		
		// prepara el informe
		OutputDeclaracionesDeValorDocCabDTO outputDocumentoCabDTO = new OutputDeclaracionesDeValorDocCabDTO();
		
		// prepara la cabecera	
		outputDocumentoCabDTO.setCodigoDeclaracion("1234");
		
		// prepara las lineas
		
		return outputDocumentoCabDTO;
	}		
}
