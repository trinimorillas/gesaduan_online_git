package es.mercadona.gesaduan.dao.dosierapi.getdocumento.v1.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.fwk.data.DaoBaseImpl;
import es.mercadona.gesaduan.dao.dosierapi.getdocumento.v1.GetDocumentoApiDAO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getdvdocumento.v1.OutputDeclaracionesDeValorDocCabDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getdvdocumento.v1.OutputDeclaracionesDeValorDocLinDTO;
import es.mercadona.gesaduan.dto.dosierapi.getdocumento.v1.InputDosierDocumentoDTO;
import es.mercadona.gesaduan.dto.dosierapi.getdocumento.v1.OutputDosierDocCabDTO;
import es.mercadona.gesaduan.jpa.declaracionesdevalor.getdocumentodv.v1.DocumentoDVDataJPA;
import es.mercadona.gesaduan.jpa.declaracionesdevalor.getdocumentodv.v1.DocumentoDVDataPK;

@Stateless
public class GetDocumentoApiDAOImpl extends DaoBaseImpl<DocumentoDVDataPK, DocumentoDVDataJPA> implements GetDocumentoApiDAO{

	@PersistenceContext
	private EntityManager entityM;
	
	@Inject
	private org.slf4j.Logger logger;	
	
	@Override
	protected EntityManager getEntityManager() {
		
		return this.entityM;
	}

	@Override
	public void setEntityClass() {
		entityClass = DocumentoDVDataJPA.class;
		
	}

	@Override
	public OutputDosierDocCabDTO getDatosDocumento(InputDosierDocumentoDTO input) {
		
		return preparaEstructura(getDatosCabecera(input),getDatosLineas(input));
	}
	
	/* carga datos de cabecera de la DV */
	private List<OutputDeclaracionesDeValorDocCabDTO> getDatosCabecera(InputDosierDocumentoDTO input) {
		
		List<OutputDeclaracionesDeValorDocCabDTO> declaraciones = new ArrayList<>();
		
		try {	
		
			// Query
			final StringBuilder sql = new StringBuilder();		
			
			StringBuilder select = new StringBuilder();			
			
			select.append("SELECT ");
			select.append("DISTINCT TO_CHAR(D.COD_N_DECLARACION_VALOR) FACTURA "); 
			select.append(",TO_CHAR(D.NUM_ANYO) ANYO ");
			select.append(",TO_CHAR(D.COD_N_VERSION) VERSION ");
			select.append(",TO_CHAR(D.FEC_DT_CREACION, 'DD/MM/YYYY') FECHA_DECLARACION ");
			select.append(",TO_CHAR(D.NUM_DOSIER) DOSIER ");
			select.append(",TO_CHAR(D.NUM_ANYO_DOSIER) ANYO_DOSIER ");
			select.append(",TO_CHAR(DOS.FEC_DT_CREACION, 'DD/MM/YYYY') FECHA_DOSIER ");
			select.append(",DECODE(D.COD_N_PROVEEDOR, NULL, BL.TXT_NOMBRE, REGEXP_REPLACE(P.TXT_RAZON_SOCIAL, '\"|;', '')) NOMBRE_ORIGEN ");
			select.append(",R.TXT_NOMBRE PROVINCIA_ORIGEN ");
			select.append(",DECODE(D.COD_N_PROVEEDOR, NULL, 'BLOQUE', 'PROVEEDOR') TIPO_ORIGEN ");
			select.append(",D.TXT_CONDICIONES_ENTREGA CONDICIONES_ENTREGA ");
			select.append(",REGEXP_REPLACE(E.TXT_RAZON_SOCIAL, '\"|;', '') EXP_NOMBRE ");
			select.append(",REGEXP_REPLACE(E.TXT_DIRECCION, '\"|;', '') EXP_DIRECCION ");
			select.append(",E.TXT_CODIGO_POSTAL EXP_CP ");
			select.append(",E.TXT_POBLACION EXP_POBLACION ");
			select.append(",E.TXT_PROVINCIA EXP_PROVINCIA ");
			select.append(",E.NUM_CIF EXP_NIF ");
			select.append(",DECODE(D.COD_N_PROVEEDOR,NULL,PU.TXT_NOMBRE_PUERTO,REGEXP_REPLACE(C.TXT_RAZON_SOCIAL, '\"|;', '')) IMP_NOMBRE ");
			select.append(",REGEXP_REPLACE(C.TXT_DIRECCION, '\"|;', '') IMP_DIRECCION ");
			select.append(",C.TXT_COD_POSTAL IMP_CP ");
			select.append(",C.TXT_LOCALIDAD IMP_POBLACION ");
			select.append(",C.TXT_PROVINCIA IMP_PROVINCIA ");
			select.append(",E.NUM_CIF IMP_NIF ");
			select.append(",(SELECT TXT_VALOR FROM C_VARIABLE WHERE COD_V_VARIABLE = 'MERCANCIA_REA' AND EXISTS ( SELECT L.COD_N_MERCA FROM O_DECLARACION_VALOR_LIN L WHERE L.COD_N_DECLARACION_VALOR = D.COD_N_DECLARACION_VALOR AND L.NUM_ANYO = D.NUM_ANYO AND L.COD_N_VERSION = D.COD_N_VERSION AND L.COD_V_REA IS NOT NULL ) ) AS TXT_INFO_REA ");
			select.append(",(SELECT TXT_VALOR FROM C_VARIABLE WHERE COD_V_VARIABLE = 'MERCANCIA_REG_CANARIO' AND EXISTS ( SELECT L.COD_N_MERCA FROM O_DECLARACION_VALOR_LIN L WHERE L.COD_N_DECLARACION_VALOR = D.COD_N_DECLARACION_VALOR AND L.NUM_ANYO = D.NUM_ANYO AND L.COD_N_VERSION = D.COD_N_VERSION AND L.MCA_PRODUCTO_LPC = 'S' ) ) AS TXT_INFO_LPC ");
			select.append(",(SELECT TXT_VALOR FROM C_VARIABLE WHERE COD_V_VARIABLE = 'MERCANCIA_EXENTA_IVA' ) AS TXT_INFO_GENERAL ");
			select.append(",(SELECT (CASE WHEN ((COUNT(L.NUM_GRADO_ALCOHOL) > 0) AND ((COUNT(L.NUM_VOLUMEN)) > 0) ) THEN 'VOLUMEN_ALCOHOL' WHEN COUNT(L.NUM_VOLUMEN) > 0 THEN 'VOLUMEN' ELSE 'BASE' END ) FROM O_DECLARACION_VALOR_LIN L WHERE L.COD_N_DECLARACION_VALOR = D.COD_N_DECLARACION_VALOR AND L.NUM_ANYO = D.NUM_ANYO AND L.COD_N_VERSION = D.COD_N_VERSION GROUP BY L.COD_N_DECLARACION_VALOR ,L.NUM_ANYO ,L.COD_N_VERSION ) AS TIPO_INFORME ");
			select.append("FROM O_DECLARACION_VALOR_CAB D ");
			select.append("LEFT JOIN D_PROVEEDOR_R P ON D.COD_N_PROVEEDOR = P.COD_N_PROVEEDOR ");
			select.append("LEFT JOIN D_PROVINCIA_R R ON D.COD_N_PROVINCIA_CARGA = R.COD_N_PROVINCIA ");
			select.append("LEFT JOIN D_EMPRESA_R E ON E.COD_V_EMPRESA = '08' ");
			select.append("LEFT JOIN D_BLOQUE_LOGISTICO_R BL ON BL.COD_N_BLOQUE_LOGISTICO = D.COD_N_BLOQUE_LOGISTICO ");
			select.append("LEFT JOIN D_PUERTO PU ON PU.COD_N_PUERTO=D.COD_N_PUERTO_DESEMBARQUE ");
			select.append("LEFT JOIN D_CENTRO_R C ON D.COD_V_ALMACEN = C.COD_V_CENTRO ");
			select.append("LEFT JOIN D_DOSIER DOS ON DOS.NUM_DOSIER = D.NUM_DOSIER AND DOS.NUM_ANYO= D.NUM_ANYO_DOSIER ");
			select.append("WHERE ");
			select.append("D.NUM_DOSIER = ?codigoDosier AND ");
			select.append("D.NUM_ANYO_DOSIER = ?anyoDosier ");
			select.append("ORDER BY D.COD_N_DECLARACION_VALOR,D.NUM_ANYO,D.COD_N_VERSION ");
			
			sql.append(select);
			
			final Query query = getEntityManager().createNativeQuery(sql.toString());		
			query.setParameter("codigoDosier", input.getCodigoDosier());
			query.setParameter("anyoDosier", input.getAnyoDosier());						
			
			List<Object[]> listado = query.getResultList();	
			
			if (listado != null && !listado.isEmpty()) {			
				for (Object[] tmp : listado) {	
					
					OutputDeclaracionesDeValorDocCabDTO  outDVDocumentoCabDTO  = new OutputDeclaracionesDeValorDocCabDTO() ;

					// Obtiene los datos de cabecera del documento						
					outDVDocumentoCabDTO.setCodigoDeclaracion(String.valueOf(tmp[0]));
					outDVDocumentoCabDTO.setAnyoDeclaracion(String.valueOf(tmp[1]));
					outDVDocumentoCabDTO.setVersionDeclaracion(String.valueOf(tmp[2]));
					outDVDocumentoCabDTO.setFechaDeclaracion(String.valueOf(tmp[3]));
					outDVDocumentoCabDTO.setNumDosier(String.valueOf(tmp[4]));
					outDVDocumentoCabDTO.setAnyoDosier(String.valueOf(tmp[5]));
					outDVDocumentoCabDTO.setFechaDosier(String.valueOf(tmp[6]));		
					outDVDocumentoCabDTO.setNombreOrigen(String.valueOf(tmp[7]));	
					outDVDocumentoCabDTO.setProvinciaOrigen(String.valueOf(tmp[8]));
					outDVDocumentoCabDTO.setTipoOrigen(String.valueOf(tmp[9]));
					outDVDocumentoCabDTO.setCondicionesEntrega(String.valueOf(tmp[10]));
					outDVDocumentoCabDTO.setExportadorNombre(String.valueOf(tmp[11]));	
					outDVDocumentoCabDTO.setExportadorDireccion(String.valueOf(tmp[12]));	
					outDVDocumentoCabDTO.setExportadorCP(String.valueOf(tmp[13]));
					outDVDocumentoCabDTO.setExportadorPoblacion(String.valueOf(tmp[14]));
					outDVDocumentoCabDTO.setExportadorProvincia(String.valueOf(tmp[15]));
					outDVDocumentoCabDTO.setExportadorNIF(String.valueOf(tmp[16]));
					outDVDocumentoCabDTO.setImportadorNombre(String.valueOf(tmp[17]));	
					outDVDocumentoCabDTO.setImportadorDireccion(String.valueOf(tmp[18]));	
					outDVDocumentoCabDTO.setImportadorCP(String.valueOf(tmp[19]));
					outDVDocumentoCabDTO.setImportadorPoblacion(String.valueOf(tmp[20]));
					outDVDocumentoCabDTO.setImportadorProvincia(String.valueOf(tmp[21]));
					outDVDocumentoCabDTO.setImportadorNIF(String.valueOf(tmp[22]));		
					if (tmp[23] != null) outDVDocumentoCabDTO.setTxtInfoREA(String.valueOf(tmp[23]));	
					if (tmp[24] != null) outDVDocumentoCabDTO.setTxtInfoLPC(String.valueOf(tmp[24]));
					if (tmp[25] != null) outDVDocumentoCabDTO.setTxtInfoGeneral(String.valueOf(tmp[25]));
					outDVDocumentoCabDTO.setTipoInforme(String.valueOf(tmp[26]));				
					
					declaraciones.add(outDVDocumentoCabDTO);
				}
			}
			

			
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","GetDocumentoApiDAOImpl(GESADUAN)","getDatosCabecera",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}	
		
		return declaraciones;
	}
	
	/* carga datos de lineas de la DV */
	private List<OutputDeclaracionesDeValorDocLinDTO> getDatosLineas(InputDosierDocumentoDTO input) {
		
		List<OutputDeclaracionesDeValorDocLinDTO> lineas;
		
		// Obtiene los datos de cabecera del documento	
		lineas = new ArrayList<>();	
		
		try {	
			
			// Query
			final StringBuilder sql = new StringBuilder();		
			
			StringBuilder select = new StringBuilder();			
						
			select.append("SELECT ");
			select.append("TO_CHAR(DECLARACION_VALOR) ");
			select.append(",TO_CHAR(ANYO) ");
			select.append(",TO_CHAR(VERSION_N) ");
			select.append(",TO_CHAR(CODIGO) ");
			select.append(",PRODUCTO ");
			select.append(",MARCA ");
			select.append(",TO_CHAR(CODIGO_TARIC) ");
			select.append(",TIPO_LINEA ");
			select.append(",CODIGO_REA ");
			select.append(",PAIS_ORIGEN ");
			select.append(",LPC ");
			select.append(",TO_CHAR(BULTOS, 'FM9G999G990D999') ");
			select.append(",TIPO ");
			select.append(",TO_CHAR(PESO_BRUTO, 'FM9G999G990D999') ");
			select.append(",TO_CHAR(PESO_NETO, 'FM9G999G990D999') ");
			select.append(",TO_CHAR(CANTIDAD, 'FM9G999G990D999') ");
			select.append(",DECODE(VOLUMEN,NULL,VOLUMEN,TO_CHAR(VOLUMEN, 'FM9G999G990D999')) ");
			select.append(",DECODE(ALCOHOL,NULL,ALCOHOL,TO_CHAR(ALCOHOL, 'FM9G999G990D999')) ");
			select.append(",DECODE(PLATO,NULL,PLATO,TO_CHAR(PLATO, 'FM9G999G990D999')) ");
			select.append(",TO_CHAR(PRECIO, 'FM9G999G990D9999') ");
			select.append(",TO_CHAR(IMPORTE, 'FM9G999G990D999') ");			
			select.append("FROM ( ");
			/* lineas */
			select.append("SELECT ");
			select.append("L.COD_N_DECLARACION_VALOR DECLARACION_VALOR ");			
			select.append(",L.NUM_ANYO ANYO ");
			select.append(",L.COD_N_VERSION VERSION_N ");
			select.append(",TO_CHAR(L.COD_N_MERCA) CODIGO ");
			select.append(",DECODE(P.TXT_DENOMINA_ALTERNATIVA, NULL, DP.TXT_DESCRIPCION, P.TXT_DENOMINA_ALTERNATIVA) AS PRODUCTO ");
			select.append(",P.TXT_MARCA MARCA ");
			select.append(",L.COD_N_TARIC CODIGO_TARIC ");
			select.append(",'LINEA' TIPO_LINEA ");
			select.append(",L.COD_V_REA CODIGO_REA ");
			select.append(",I.TXT_NOMBRE PAIS_ORIGEN ");
			select.append(",L.MCA_PRODUCTO_LPC LPC ");
			select.append(",L.NUM_BULTOS BULTOS ");
			select.append(",L.TXT_NOMBRE_BULTO_DV TIPO ");
			select.append(",L.NUM_PESO_BRUTO PESO_BRUTO ");
			select.append(",L.NUM_PESO_NETO PESO_NETO ");
			select.append(",L.NUM_CANTIDAD CANTIDAD ");
			select.append(",L.NUM_VOLUMEN VOLUMEN ");
			select.append(",L.NUM_GRADO_ALCOHOL ALCOHOL ");
			select.append(",L.NUM_GRADO_PLATO PLATO ");
			select.append(",L.NUM_PRECIO_UNIDAD PRECIO ");
			select.append(",L.NUM_IMPORTE_TOTAL IMPORTE ");
			select.append("FROM O_DECLARACION_VALOR_LIN L ");
			select.append("LEFT JOIN D_PRODUCTO_R P ON L.COD_N_MERCA = P.COD_N_PRODUCTO ");
			select.append("LEFT JOIN S_DENOMINACION_PRODUCTO_I18N_R DP ON (DP.COD_N_PRODUCTO = P.COD_N_PRODUCTO AND COD_V_LOCALE = 'es_ES' ) ");
			select.append("LEFT JOIN D_PAIS_R I ON L.COD_V_PAIS = I.COD_V_ISO_ALFA2 ");
			select.append("UNION ");
			/* totales taric */
			select.append("SELECT ");
			select.append("L.COD_N_DECLARACION_VALOR DECLARACION_VALOR ");
			select.append(",L.NUM_ANYO ANYO ");
			select.append(",L.COD_N_VERSION VERSION_N ");
			select.append(",'TARIC' CODIGO ");
			select.append(",TO_CHAR(L.COD_N_TARIC) PRODUCTO ");
			select.append(",'' MARCA ");
			select.append(",L.COD_N_TARIC CODIGO_TARIC ");
			select.append(",'TARIC' TIPO_LINEA ");
			select.append(",'' CODIGO_REA ");
			select.append(",'' PAIS_ORIGEN ");
			select.append(",'' LPC ");
			select.append(",SUM(L.NUM_BULTOS) BULTOS ");
			select.append(",'' TIPO ");
			select.append(",SUM(L.NUM_PESO_BRUTO) PESO_BRUTO1 ");
			select.append(",SUM(L.NUM_PESO_NETO) PESO_NETO ");
			select.append(",SUM(L.NUM_CANTIDAD) CANTIDAD ");
			select.append(",SUM(L.NUM_VOLUMEN) VOLUMEN ");
			select.append(",NULL ALCOHOL ");
			select.append(",NULL PLATO ");
			select.append(",NULL PRECIO ");
			select.append(",SUM(L.NUM_IMPORTE_TOTAL) IMPORTE ");
			select.append("FROM O_DECLARACION_VALOR_LIN L ");
			select.append("GROUP BY L.COD_N_DECLARACION_VALOR ,L.NUM_ANYO,L.COD_N_VERSION,L.COD_N_TARIC ");
			select.append("UNION ");
			/* totales factura */
			select.append("SELECT ");
			select.append("L.COD_N_DECLARACION_VALOR DECLARACION_VALOR ");
			select.append(",L.NUM_ANYO ANYO ");
			select.append(",L.COD_N_VERSION VERSION_N ");
			select.append(",'TOTAL' CODIGO ");
			select.append(",'' PRODUCTO ");
			select.append(",'' MARCA ");
			select.append(",NULL CODIGO_TARIC ");
			select.append(",'TOTAL' TIPO_LINEA ");
			select.append(",'' CODIGO_REA ");
			select.append(",'' PAIS_ORIGEN ");
			select.append(",'' LPC ");
			select.append(",SUM(L.NUM_BULTOS) BULTOS ");
			select.append(",'' TIPO ");
			select.append(",SUM(L.NUM_PESO_BRUTO) PESO_BRUTO ");
			select.append(",SUM(L.NUM_PESO_NETO) PESO_NETO ");
			select.append(",SUM(L.NUM_CANTIDAD) CANTIDAD ");
			select.append(",SUM(L.NUM_VOLUMEN) VOLUMEN ");
			select.append(",NULL ALCOHOL ");
			select.append(",NULL PLATO ");
			select.append(",NULL PRECIO ");
			select.append(",SUM(L.NUM_IMPORTE_TOTAL) IMPORTE ");
			select.append("FROM O_DECLARACION_VALOR_LIN L ");
			select.append("GROUP BY L.COD_N_DECLARACION_VALOR,L.NUM_ANYO,L.COD_N_VERSION ");
			select.append(") ");	
			select.append("INNER JOIN O_DECLARACION_VALOR_CAB DV ON DV.COD_N_DECLARACION_VALOR = DECLARACION_VALOR AND DV.NUM_ANYO= ANYO AND DV.COD_N_VERSION= VERSION_N ");
			select.append("WHERE ");
			select.append("DV.NUM_DOSIER = ?codigoDosier AND ");
			select.append("DV.NUM_ANYO_DOSIER = ?anyoDosier ");
			select.append("ORDER BY DECLARACION_VALOR,ANYO,VERSION_N,CODIGO_TARIC,TIPO_LINEA,CODIGO ");
			
			sql.append(select);
			
			final Query query = getEntityManager().createNativeQuery(sql.toString());		
			query.setParameter("codigoDosier", input.getCodigoDosier());
			query.setParameter("anyoDosier", input.getAnyoDosier());							
			
			List<Object[]> listado = query.getResultList();	
			
			if (listado != null && !listado.isEmpty()) {			
				for (Object[] tmp : listado) {	
					
					OutputDeclaracionesDeValorDocLinDTO outDVDocumentoLinDTO = new OutputDeclaracionesDeValorDocLinDTO();
					
					outDVDocumentoLinDTO.setCodigoDeclaracion(String.valueOf(tmp[0]));
					outDVDocumentoLinDTO.setAnyoDeclaracion(String.valueOf(tmp[1]));
					outDVDocumentoLinDTO.setVersionDeclaracion(String.valueOf(tmp[2]));
					if (tmp[3] != null) {
						outDVDocumentoLinDTO.setCodigoProducto(String.valueOf(tmp[3]));
					} else {
						outDVDocumentoLinDTO.setCodigoProducto("");
					}
					if (tmp[4] != null) {
						outDVDocumentoLinDTO.setNombreProducto(replaceSpecialChars(String.valueOf(tmp[4])));
					} else {
						outDVDocumentoLinDTO.setNombreProducto("");
					}
					if (tmp[5] != null) {
						outDVDocumentoLinDTO.setMarca(replaceSpecialChars(String.valueOf(tmp[5])));
					} else {
						outDVDocumentoLinDTO.setMarca("");
					}
					if (tmp[6] != null) {
						outDVDocumentoLinDTO.setCodigoTaric(String.valueOf(tmp[6]));
					} else {
						outDVDocumentoLinDTO.setCodigoTaric("");
					}
					outDVDocumentoLinDTO.setTipoLinea(String.valueOf(tmp[7]));
					if (tmp[8] != null) {
						outDVDocumentoLinDTO.setCodigoRea(String.valueOf(tmp[8]));
					} else {
						outDVDocumentoLinDTO.setCodigoRea("");
					}
					if (tmp[9] != null) {
						outDVDocumentoLinDTO.setPaisOrigen(String.valueOf(tmp[9]));
					} else {
						outDVDocumentoLinDTO.setPaisOrigen("");
					}
					if (tmp[10] != null) {
						outDVDocumentoLinDTO.setLpc(String.valueOf(tmp[10]));
					} else {
						outDVDocumentoLinDTO.setLpc("");
					}
					outDVDocumentoLinDTO.setNumeroBultos(formatNumber(input.getTipoDocumento(),String.valueOf(tmp[11])));
					if (tmp[12] != null) {
						outDVDocumentoLinDTO.setTipoBultos(String.valueOf(tmp[12]));
					} else {
						outDVDocumentoLinDTO.setTipoBultos("");
					}
					outDVDocumentoLinDTO.setPesoBruto(formatNumber(input.getTipoDocumento(),String.valueOf(tmp[13])));
					outDVDocumentoLinDTO.setPesoNeto(formatNumber(input.getTipoDocumento(),String.valueOf(tmp[14])));
					outDVDocumentoLinDTO.setCantidad(formatNumber(input.getTipoDocumento(),String.valueOf(tmp[15])));
					if (tmp[16] != null) {
						outDVDocumentoLinDTO.setVolumen(formatNumber(input.getTipoDocumento(),String.valueOf(tmp[16])));
					} else {
						outDVDocumentoLinDTO.setVolumen("");
					}
					if (tmp[17] != null) {
						outDVDocumentoLinDTO.setAlcohol(formatNumber(input.getTipoDocumento(),String.valueOf(tmp[17])));
					} else {
						outDVDocumentoLinDTO.setAlcohol("");
					}
					if (tmp[18] != null) {
						outDVDocumentoLinDTO.setPlato(formatNumber(input.getTipoDocumento(),String.valueOf(tmp[18])));
					} else {
						outDVDocumentoLinDTO.setPlato("");
					}
					if (tmp[19] != null) {
						outDVDocumentoLinDTO.setPrecio(formatNumber(input.getTipoDocumento(),String.valueOf(tmp[19])));
					} else {
						outDVDocumentoLinDTO.setPrecio("");
					}
					outDVDocumentoLinDTO.setImporte(formatNumber(input.getTipoDocumento(),String.valueOf(tmp[20])));
					outDVDocumentoLinDTO.setProcesada(false);
					
					lineas.add(outDVDocumentoLinDTO);

				}
			}
			
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","GetDocumentoApiDAOImpl(GESADUAN)","getDatosLineas",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}				
		
		return lineas;
	}
	
	/* carga datos de cabecera de la DV */
	private OutputDosierDocCabDTO preparaEstructura(List<OutputDeclaracionesDeValorDocCabDTO> declaraciones, List<OutputDeclaracionesDeValorDocLinDTO> lineas) {
		
		OutputDosierDocCabDTO outDocumentoDTO;
		
		// Obtiene los datos de cabecera del documento	
		outDocumentoDTO = new OutputDosierDocCabDTO();	
		
		

		// Obtiene los datos de lineas del documento
		if (!declaraciones.isEmpty()) {
			
			outDocumentoDTO.setCodigoDosier(declaraciones.get(0).getNumDosier());
			outDocumentoDTO.setAnyoDosier(declaraciones.get(0).getAnyoDosier());
			outDocumentoDTO.setFechaDosier(declaraciones.get(0).getFechaDosier());
			outDocumentoDTO.setTipoInforme(declaraciones.get(0).getTipoInforme());

			for (OutputDeclaracionesDeValorDocCabDTO outDVDocumentoCabDTO : declaraciones) {
				
				List<OutputDeclaracionesDeValorDocLinDTO> lineasDeclaracion = new ArrayList<>();
				
				for (OutputDeclaracionesDeValorDocLinDTO outDVDocumentoLinDTO : lineas) {
					if (!outDVDocumentoLinDTO.isProcesada()) {
						if (outDVDocumentoCabDTO.getCodigoDeclaracion().equals(outDVDocumentoLinDTO.getCodigoDeclaracion()) && 
							outDVDocumentoCabDTO.getAnyoDeclaracion().equals(outDVDocumentoLinDTO.getAnyoDeclaracion()) &&
							outDVDocumentoCabDTO.getVersionDeclaracion().equals(outDVDocumentoLinDTO.getVersionDeclaracion())) {
							
							lineasDeclaracion.add(outDVDocumentoLinDTO);
							outDVDocumentoLinDTO.setProcesada(true);
						}
					}
				}
				
				outDVDocumentoCabDTO.setLineas(lineasDeclaracion);				
				
			}
			
			outDocumentoDTO.setDeclaraciones(declaraciones);			
			
		}		
		
		return outDocumentoDTO;
		
	}
	

	private String formatNumber(String tipoDocumento, String value) {
		if ("csv".equals(tipoDocumento)) {
			if (value != null ) {
				value = value.replace(".", "");
				value = value.replace(",", ".");

				if (".".equals(value.substring(value.length()-1))) {
					value = value.substring(0,value.length()-1);
				}
			}											
		} else {
			if (value != null ) {
				if (",".equals(value.substring(value.length()-1))) {
					value = value.substring(0,value.length()-1);
				}
			}											
		}
		return value;
	}

	private String replaceSpecialChars(String value) {
		if (value != null ) {
			value = value.replace("\"", "");
			value = value.replace("|", "");
			value = value.replace(";", "");
		}
		return value;
	}	
	
}
