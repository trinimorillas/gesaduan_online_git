package es.mercadona.gesaduan.business.declaracionesdevalor.getdvdocumento.v2.impl;


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
import es.mercadona.gesaduan.business.declaracionesdevalor.getdvdocumento.v2.DVDocumentoPDFService;
import es.mercadona.gesaduan.dao.declaracionesdevalor.getdvdocumento.v2.GetDVDocumentoOnlineDAO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.getdvdocumento.v2.InputDeclaracionesDeValorDocumentoDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.getdvdocumento.v2.OutputDeclaracionesDeValorDocCabDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.getdvdocumento.v2.OutputDeclaracionesDeValorDocLinDTO;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

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
		OutputDeclaracionesDeValorDocCabDTO outDVDocumentoDTO = preparaDatosPrueba();		
				
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

	
	private OutputDeclaracionesDeValorDocCabDTO preparaDatosPrueba() {
		
		// prepara el informe
		OutputDeclaracionesDeValorDocCabDTO outputDocumentoCabDTO = new OutputDeclaracionesDeValorDocCabDTO();
		
		// prepara la cabecera	
		outputDocumentoCabDTO.setCodigoDeclaracion("33710");
		outputDocumentoCabDTO.setAnyoDeclaracion("2021");
		outputDocumentoCabDTO.setVersionDeclaracion("1");
		outputDocumentoCabDTO.setFechaDeclaracion("26/03/2021");
		outputDocumentoCabDTO.setNumDosier("6");
		outputDocumentoCabDTO.setAnyoDosier("2021");
		outputDocumentoCabDTO.setFechaDosier("09/04/2021");		
		outputDocumentoCabDTO.setNombreOrigen("PALETAS MARPA SL");	
		outputDocumentoCabDTO.setProvinciaOrigen("TERUEL");
		outputDocumentoCabDTO.setTipoOrigen("PROVEEDOR");
		outputDocumentoCabDTO.setCondicionesEntrega("EXW");
		outputDocumentoCabDTO.setExportadorNombre("MERCADONA S.A.");	
		outputDocumentoCabDTO.setExportadorDireccion("C/ VALENCIA 5");	
		outputDocumentoCabDTO.setExportadorCP("46016");
		outputDocumentoCabDTO.setExportadorPoblacion("TAVERNES BLANQUES");
		outputDocumentoCabDTO.setExportadorProvincia("VALENCIA");
		outputDocumentoCabDTO.setExportadorNIF("A46103834");
		outputDocumentoCabDTO.setImportadorNombre("MERCADONA S.A.");	
		outputDocumentoCabDTO.setImportadorDireccion("POLÍGONO INDUSTRIAL DE LAS MAJONERAS");	
		outputDocumentoCabDTO.setImportadorCP("35250");
		outputDocumentoCabDTO.setImportadorPoblacion("INGENIO");
		outputDocumentoCabDTO.setImportadorProvincia("Las Palmas");
		outputDocumentoCabDTO.setImportadorNIF("A46103834");		
		outputDocumentoCabDTO.setTxtInfoREA("");	
		outputDocumentoCabDTO.setTxtInfoLPC("");
		outputDocumentoCabDTO.setTxtInfoGeneral("Mercancía exenta de IVA, según Artículo 21 de la Ley 37/1992 de 28 diciembre");
		
		outputDocumentoCabDTO.setTipoInforme("BASE");
		
		// prepara las lineas
		
		List<OutputDeclaracionesDeValorDocLinDTO> lineas = new ArrayList<>();		
		
		OutputDeclaracionesDeValorDocLinDTO outputDocumentoLinDTO = new OutputDeclaracionesDeValorDocLinDTO();	
		
		outputDocumentoLinDTO.setCodigoDeclaracion("33710");
		outputDocumentoLinDTO.setAnyoDeclaracion("2021");
		outputDocumentoLinDTO.setVersionDeclaracion("1");
		outputDocumentoLinDTO.setCodigoProducto("59173");
		outputDocumentoLinDTO.setNombreProducto("PALETA CURADA LONCHA");
		outputDocumentoLinDTO.setMarca("MARPA");
		outputDocumentoLinDTO.setCodigoTaric("210198190");
		outputDocumentoLinDTO.setTipoLinea("LINEA");
		outputDocumentoLinDTO.setCodigoRea("");
		outputDocumentoLinDTO.setPaisOrigen("");
		outputDocumentoLinDTO.setLpc("N");
		outputDocumentoLinDTO.setNumeroBultos("1");
		outputDocumentoLinDTO.setTipoBultos("Palet");
		outputDocumentoLinDTO.setPesoBruto("729");
		outputDocumentoLinDTO.setPesoNeto("648");
		outputDocumentoLinDTO.setCantidad("2,700");
		outputDocumentoLinDTO.setVolumen("");
		outputDocumentoLinDTO.setAlcohol("");
		outputDocumentoLinDTO.setPlato("");
		outputDocumentoLinDTO.setPrecio("2,93");
		outputDocumentoLinDTO.setImporte("7,911");		
		
		lineas.add(outputDocumentoLinDTO);
		
		OutputDeclaracionesDeValorDocLinDTO outputDocumentoLinTaricDTO = new OutputDeclaracionesDeValorDocLinDTO();

		outputDocumentoLinTaricDTO.setCodigoDeclaracion("33710");
		outputDocumentoLinTaricDTO.setAnyoDeclaracion("2021");
		outputDocumentoLinTaricDTO.setVersionDeclaracion("1");
		outputDocumentoLinTaricDTO.setCodigoProducto("210198190");
		outputDocumentoLinTaricDTO.setNombreProducto("");
		outputDocumentoLinTaricDTO.setMarca("");
		outputDocumentoLinTaricDTO.setCodigoTaric("210198190");
		outputDocumentoLinTaricDTO.setTipoLinea("TARIC");
		outputDocumentoLinTaricDTO.setCodigoRea("");
		outputDocumentoLinTaricDTO.setPaisOrigen("");
		outputDocumentoLinTaricDTO.setLpc("");
		outputDocumentoLinTaricDTO.setNumeroBultos("1");
		outputDocumentoLinTaricDTO.setTipoBultos("");
		outputDocumentoLinTaricDTO.setPesoBruto("729");
		outputDocumentoLinTaricDTO.setPesoNeto("648");
		outputDocumentoLinTaricDTO.setCantidad("2,700");
		outputDocumentoLinTaricDTO.setVolumen("");
		outputDocumentoLinTaricDTO.setAlcohol("");
		outputDocumentoLinTaricDTO.setPlato("");
		outputDocumentoLinTaricDTO.setPrecio("");
		outputDocumentoLinTaricDTO.setImporte("7,911");		
		
		lineas.add(outputDocumentoLinTaricDTO);		

		OutputDeclaracionesDeValorDocLinDTO outputDocumentoLinTotalDTO = new OutputDeclaracionesDeValorDocLinDTO();		
		
		outputDocumentoLinTotalDTO.setCodigoDeclaracion("33710");
		outputDocumentoLinTotalDTO.setAnyoDeclaracion("2021");
		outputDocumentoLinTotalDTO.setVersionDeclaracion("1");
		outputDocumentoLinTotalDTO.setCodigoProducto("");
		outputDocumentoLinTotalDTO.setNombreProducto("");
		outputDocumentoLinTotalDTO.setMarca("");
		outputDocumentoLinTotalDTO.setCodigoTaric("");
		outputDocumentoLinTotalDTO.setTipoLinea("TOTAL");
		outputDocumentoLinTotalDTO.setCodigoRea("");
		outputDocumentoLinTotalDTO.setPaisOrigen("");
		outputDocumentoLinTotalDTO.setLpc("");
		outputDocumentoLinTotalDTO.setNumeroBultos("1");
		outputDocumentoLinTotalDTO.setTipoBultos("");
		outputDocumentoLinTotalDTO.setPesoBruto("729");
		outputDocumentoLinTotalDTO.setPesoNeto("648");
		outputDocumentoLinTotalDTO.setCantidad("2,700");
		outputDocumentoLinTotalDTO.setVolumen("");
		outputDocumentoLinTotalDTO.setAlcohol("");
		outputDocumentoLinTotalDTO.setPlato("");
		outputDocumentoLinTotalDTO.setPrecio("");
		outputDocumentoLinTotalDTO.setImporte("7,911");		
		
		lineas.add(outputDocumentoLinTotalDTO);
		
		outputDocumentoCabDTO.setLineas(lineas);
		
		return outputDocumentoCabDTO;
	}		
}
