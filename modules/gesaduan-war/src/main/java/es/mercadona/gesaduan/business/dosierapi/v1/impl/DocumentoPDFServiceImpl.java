package es.mercadona.gesaduan.business.dosierapi.v1.impl;


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
import es.mercadona.gesaduan.business.dosierapi.v1.DocumentoPDFService;
import es.mercadona.gesaduan.dao.dosierapi.getdocumento.v1.GetDocumentoApiDAO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getdvdocumento.v1.OutputDeclaracionesDeValorDocCabDTO;
import es.mercadona.gesaduan.dto.dosierapi.getdocumento.v1.InputDosierDocumentoDTO;
import es.mercadona.gesaduan.dto.dosierapi.getdocumento.v1.OutputDosierDocCabDTO;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class DocumentoPDFServiceImpl implements DocumentoPDFService {

	@Inject
	private GetDocumentoApiDAO getDocumentoOnlineDAO;
	
	@Inject
	private FileSystemService fileSystemService;

	@Inject
	private ReportingService reportingService;	
	
	@Inject
	private org.slf4j.Logger logger;		

	@Override
	public OutputDosierDocCabDTO preparaDocumentoPDF(InputDosierDocumentoDTO input) {

		// Obtiene los datos del informe (estructura del informe)
		OutputDosierDocCabDTO outDVDocumentoDTO = getDocumentoOnlineDAO.getDatosDocumento(input);		
				
		// prepara el informe
		outDVDocumentoDTO.setFicheroPDF(preparaDocumento(outDVDocumentoDTO));
		
		// devuelve el objeto con el fichero del informe en bytes 		
		return outDVDocumentoDTO;

	}
	
	private byte[] preparaDocumento(OutputDosierDocCabDTO outDVDocumentoDTO) {
		
		// prepara el informe
		String documentoStr = "";
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
			this.logger.error("({}-{}) ERROR - {} {}","DVDocumentoPDFServiceImpl(GESADUAN)","preparaDocumento",e.getClass().getSimpleName(),e.getMessage());	
			throw new ApplicationException(e.getMessage());			
		}
		
		return ficheroByte;

	}

	private void preparaParametrosReport(OutputDeclaracionesDeValorDocCabDTO outDVDocumentoDTO,final Map<String, Object> mapParams) {
		
		mapParams.put("factura", outDVDocumentoDTO.getCodigoDeclaracion());
		mapParams.put("anyo", outDVDocumentoDTO.getAnyoDeclaracion());
		mapParams.put("fechaDeclaracion", outDVDocumentoDTO.getFechaDeclaracion());
		mapParams.put("proveedor", outDVDocumentoDTO.getNombreOrigen());
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

		mapParams.put("header.factura","FACTURA - Nº");
		mapParams.put("header.declaracionValor","DECLARACIÓN VALOR - Nº");
		mapParams.put("header.fechaDV","Fecha DV");
		mapParams.put("header.fechaFactura","Fecha factura");
		mapParams.put("header.proveedor","Proveedor");
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
