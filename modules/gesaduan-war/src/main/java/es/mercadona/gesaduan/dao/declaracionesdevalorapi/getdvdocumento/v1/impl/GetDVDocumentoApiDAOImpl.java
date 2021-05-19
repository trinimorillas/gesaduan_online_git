package es.mercadona.gesaduan.dao.declaracionesdevalorapi.getdvdocumento.v1.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.fwk.data.DaoBaseImpl;
import es.mercadona.gesaduan.common.Constantes;
import es.mercadona.gesaduan.dao.declaracionesdevalorapi.getdvdocumento.v1.GetDVDocumentoApiDAO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getdvdocumento.v1.InputValueDeclarationDocumentDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getdvdocumento.v1.OutputDeclaracionesDeValorDocCabDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getdvdocumento.v1.OutputDeclaracionesDeValorDocLinDTO;
import es.mercadona.gesaduan.jpa.declaracionesdevalor.getdocumentodv.v1.DocumentoDVDataJPA;
import es.mercadona.gesaduan.jpa.declaracionesdevalor.getdocumentodv.v1.DocumentoDVDataPK;

@Stateless
public class GetDVDocumentoApiDAOImpl extends DaoBaseImpl<DocumentoDVDataPK, DocumentoDVDataJPA> implements GetDVDocumentoApiDAO{

	@PersistenceContext
	private EntityManager entityM;
	
	@Inject
	private org.slf4j.Logger logger;	
	
	private static final String LOG_FILE = "GetDVDocumentoApiDAOImpl(GESADUAN)"; 	
	
	@Override
	protected EntityManager getEntityManager() {
		
		return this.entityM;
	}

	@Override
	public void setEntityClass() {
		entityClass = DocumentoDVDataJPA.class;
		
	}

	@Override
	public OutputDeclaracionesDeValorDocCabDTO getDatosDocumento(InputValueDeclarationDocumentDTO input) {
		
		OutputDeclaracionesDeValorDocCabDTO outDVDocumentoDTO;
		
		// Obtiene los datos de cabecera del documento	
		outDVDocumentoDTO = getDatosCabecera(input);
		
		return outDVDocumentoDTO;
	}
	
	/* carga datos de cabecera de la DV */
	private OutputDeclaracionesDeValorDocCabDTO getDatosCabecera(InputValueDeclarationDocumentDTO input) {
		
		OutputDeclaracionesDeValorDocCabDTO outDVDocumentoDTO;
		List<OutputDeclaracionesDeValorDocLinDTO> lineas;

		// Obtiene los datos de cabecera del documento	
		outDVDocumentoDTO = new OutputDeclaracionesDeValorDocCabDTO();	
		
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
			select.append(",DECODE(D.COD_V_EXPEDICION, NULL, REGEXP_REPLACE(BL.TXT_NOMBRE, '\"|;', ''), REGEXP_REPLACE(P.TXT_RAZON_SOCIAL, '\"|;', '')) NOMBRE_ORIGEN ");
			select.append(",R.TXT_NOMBRE PROVINCIA_ORIGEN ");
			select.append(",DECODE(D.COD_V_EXPEDICION, NULL, 'BLOQUE', 'PROVEEDOR') TIPO_ORIGEN ");
			select.append(",D.TXT_CONDICIONES_ENTREGA CONDICIONES_ENTREGA ");
			select.append(",REGEXP_REPLACE(E.TXT_RAZON_SOCIAL, '\"|;', '') EXP_NOMBRE ");
			select.append(",REGEXP_REPLACE(E.TXT_DIRECCION, '\"|;', '') EXP_DIRECCION ");
			select.append(",E.TXT_CODIGO_POSTAL EXP_CP ");
			select.append(",E.TXT_POBLACION EXP_POBLACION ");
			select.append(",E.TXT_PROVINCIA EXP_PROVINCIA ");
			select.append(",E.NUM_CIF EXP_NIF ");			
			select.append(",DECODE(D.COD_V_EXPEDICION,NULL,REGEXP_REPLACE(PU.TXT_NOMBRE_PUERTO, '\"|;', ''),REGEXP_REPLACE(C.TXT_RAZON_SOCIAL, '\"|;', '')) IMP_NOMBRE ");
			select.append(",DECODE(D.COD_V_EXPEDICION,NULL,REGEXP_REPLACE(PU.TXT_DIRECCION, '\\\"|;', ''),REGEXP_REPLACE(C.TXT_DIRECCION, '\"|;', '')) IMP_DIRECCION ");
			select.append(",DECODE(D.COD_V_EXPEDICION,NULL,'',C.TXT_COD_POSTAL) IMP_CP ");
			select.append(",DECODE(D.COD_V_EXPEDICION,NULL,'',C.TXT_LOCALIDAD) IMP_POBLACION ");
			select.append(",DECODE(D.COD_V_EXPEDICION,NULL,'',C.TXT_PROVINCIA) IMP_PROVINCIA ");
			select.append(",E.NUM_CIF IMP_NIF ");
			select.append(",(SELECT TXT_VALOR FROM C_VARIABLE WHERE COD_V_VARIABLE = 'MERCANCIA_REA' AND EXISTS ( SELECT L.COD_N_MERCA FROM O_DECLARACION_VALOR_LIN L WHERE L.COD_N_DECLARACION_VALOR = D.COD_N_DECLARACION_VALOR AND L.NUM_ANYO = D.NUM_ANYO AND L.COD_N_VERSION = D.COD_N_VERSION AND L.COD_V_REA IS NOT NULL ) ) AS TXT_INFO_REA ");
			select.append(",(SELECT TXT_VALOR FROM C_VARIABLE WHERE COD_V_VARIABLE = 'MERCANCIA_REG_CANARIO' AND EXISTS ( SELECT L.COD_N_MERCA FROM O_DECLARACION_VALOR_LIN L WHERE L.COD_N_DECLARACION_VALOR = D.COD_N_DECLARACION_VALOR AND L.NUM_ANYO = D.NUM_ANYO AND L.COD_N_VERSION = D.COD_N_VERSION AND L.MCA_PRODUCTO_LPC = 'S' ) ) AS TXT_INFO_LPC ");
			select.append(",(SELECT TXT_VALOR FROM C_VARIABLE WHERE COD_V_VARIABLE = 'MERCANCIA_EXENTA_IVA' ) AS TXT_INFO_GENERAL ");
			select.append(",(SELECT (CASE WHEN ((COUNT(L.NUM_GRADO_ALCOHOL) > 0) AND ((COUNT(L.NUM_VOLUMEN)) > 0) ) THEN 'VOLUMEN_ALCOHOL' WHEN COUNT(L.NUM_VOLUMEN) > 0 THEN 'VOLUMEN' ELSE 'BASE' END ) FROM O_DECLARACION_VALOR_LIN L WHERE L.COD_N_DECLARACION_VALOR = D.COD_N_DECLARACION_VALOR AND L.NUM_ANYO = D.NUM_ANYO AND L.COD_N_VERSION = D.COD_N_VERSION GROUP BY L.COD_N_DECLARACION_VALOR ,L.NUM_ANYO ,L.COD_N_VERSION ) AS TIPO_INFORME ");
			select.append(",D.COD_V_PEDIDO COD_PEDIDO ");			
			select.append(",D.COD_V_EXPEDICION COD_EXPEDICION ");	
			select.append(",D.COD_N_PUERTO_DESEMBARQUE COD_PUERTO ");				
			select.append("FROM O_DECLARACION_VALOR_CAB D ");
			select.append("LEFT JOIN D_PROVEEDOR_R P ON D.COD_N_PROVEEDOR = P.COD_N_PROVEEDOR ");
			select.append("LEFT JOIN D_PROVINCIA_R R ON D.COD_N_PROVINCIA_CARGA = R.COD_N_PROVINCIA ");
			select.append("LEFT JOIN D_EMPRESA_R E ON E.COD_V_EMPRESA = '08' ");
			select.append("LEFT JOIN D_BLOQUE_LOGISTICO_R BL ON BL.COD_N_BLOQUE_LOGISTICO = D.COD_N_BLOQUE_LOGISTICO ");
			select.append("LEFT JOIN D_PUERTO PU ON PU.COD_N_PUERTO=D.COD_N_PUERTO_DESEMBARQUE ");
			select.append("LEFT JOIN D_CENTRO_R C ON D.COD_V_ALMACEN = C.COD_V_CENTRO ");
			select.append("LEFT JOIN D_DOSIER DOS ON DOS.NUM_DOSIER = D.NUM_DOSIER AND DOS.NUM_ANYO= D.NUM_ANYO_DOSIER ");
			select.append("WHERE ");
			select.append("D.COD_N_DECLARACION_VALOR = ?valueDeclarationNumber AND ");
			select.append("D.NUM_ANYO = ?valueDeclarationYear AND ");
			select.append("D.COD_N_VERSION = ?valueDeclarationVersion ");
			
			sql.append(select);
			
			final Query query = getEntityManager().createNativeQuery(sql.toString());		
			query.setParameter("valueDeclarationNumber", input.getValueDeclarationNumber());
			query.setParameter("valueDeclarationYear", input.getValueDeclarationYear());				
			query.setParameter("valueDeclarationVersion", input.getValueDeclarationVersion());		
			
			List<Object[]> listado = query.getResultList();	
			
			if (listado != null && !listado.isEmpty()) {			
				for (Object[] tmp : listado) {	
					
					outDVDocumentoDTO.setCodigoDeclaracion(String.valueOf(tmp[0]));
					outDVDocumentoDTO.setAnyoDeclaracion(String.valueOf(tmp[1]));
					outDVDocumentoDTO.setVersionDeclaracion(String.valueOf(tmp[2]));
					outDVDocumentoDTO.setFechaDeclaracion(String.valueOf(tmp[3]));
					outDVDocumentoDTO.setNumDosier(String.valueOf(tmp[4]));
					outDVDocumentoDTO.setAnyoDosier(String.valueOf(tmp[5]));
					outDVDocumentoDTO.setFechaDosier(String.valueOf(tmp[6]));		
					outDVDocumentoDTO.setNombreOrigen(String.valueOf(tmp[7]));	
					outDVDocumentoDTO.setProvinciaOrigen(String.valueOf(tmp[8]));
					outDVDocumentoDTO.setTipoOrigen(String.valueOf(tmp[9]));
					outDVDocumentoDTO.setCondicionesEntrega(String.valueOf(tmp[10]));
					outDVDocumentoDTO.setExportadorNombre(String.valueOf(tmp[11]));	
					outDVDocumentoDTO.setExportadorDireccion(String.valueOf(tmp[12]));	
					outDVDocumentoDTO.setExportadorCP(String.valueOf(tmp[13]));
					outDVDocumentoDTO.setExportadorPoblacion(String.valueOf(tmp[14]));
					outDVDocumentoDTO.setExportadorProvincia(String.valueOf(tmp[15]));
					outDVDocumentoDTO.setExportadorNIF(String.valueOf(tmp[16]));
										
					outDVDocumentoDTO.setImportadorNombre(String.valueOf(tmp[17]));	
					outDVDocumentoDTO.setImportadorDireccion(String.valueOf(tmp[18]));	
					outDVDocumentoDTO.setImportadorCP(String.valueOf(tmp[19]));
					outDVDocumentoDTO.setImportadorPoblacion(String.valueOf(tmp[20]));
					outDVDocumentoDTO.setImportadorProvincia(String.valueOf(tmp[21]));					
					outDVDocumentoDTO.setImportadorNIF(String.valueOf(tmp[22]));		
					
					outDVDocumentoDTO.setTxtInfoREA(emptyStringOrValue(tmp[23]));
					outDVDocumentoDTO.setTxtInfoLPC(emptyStringOrValue(tmp[24]));
					outDVDocumentoDTO.setTxtInfoGeneral(emptyStringOrValue(tmp[25]));	
					outDVDocumentoDTO.setTipoInforme(String.valueOf(tmp[26]));				
					outDVDocumentoDTO.setNumPedido(emptyStringOrValue(tmp[27]));
					
					// Si no tiene expedicion y tiene puerto 
					if ((tmp[28] == null) && (tmp[29] != null)) {
						String puertoDesembarque = emptyStringOrValue(tmp[29]);
						getDatosPuertos(puertoDesembarque,outDVDocumentoDTO);
					}
					
				}
			}
			
			// Obtiene los datos de lineas del documento 
			lineas = getDatosLineas(input);
			outDVDocumentoDTO.setLineas(lineas);
			
		} catch(Exception ex) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG,LOG_FILE,"getDatosCabecera",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}	
		
		return outDVDocumentoDTO;
	}
	
	/* carga datos de lineas de la DV */
	private List<OutputDeclaracionesDeValorDocLinDTO> getDatosLineas(InputValueDeclarationDocumentDTO input) {
		
		List<OutputDeclaracionesDeValorDocLinDTO> lineas;
		
		// Obtiene los datos de cabecera del documento	
		lineas = new ArrayList<>();	
		String tipoDocumento = input.getDocumentType();
		
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
			select.append(",TO_CHAR(VOLUMEN, 'FM9G999G990D999') ");
			select.append(",TO_CHAR(ALCOHOL, 'FM9G999G990D999') ");
			select.append(",TO_CHAR(PLATO, 'FM9G999G990D999') ");
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
			select.append("WHERE ");
			select.append("DECLARACION_VALOR = ?valueDeclarationNumber AND ");
			select.append("ANYO = ?valueDeclarationYear AND ");
			select.append("VERSION_N = ?valueDeclarationVersion ");
			select.append("ORDER BY CODIGO_TARIC,TIPO_LINEA,CODIGO ");
			
			sql.append(select);
			
			final Query query = getEntityManager().createNativeQuery(sql.toString());		
			query.setParameter("valueDeclarationNumber", input.getValueDeclarationNumber());
			query.setParameter("valueDeclarationYear", input.getValueDeclarationYear());				
			query.setParameter("valueDeclarationVersion", input.getValueDeclarationVersion());			
			
			List<Object[]> listado = query.getResultList();	
			
			if (listado != null && !listado.isEmpty()) {			
				for (Object[] tmp : listado) {	
					
					OutputDeclaracionesDeValorDocLinDTO outputDVDocLinDTO = new OutputDeclaracionesDeValorDocLinDTO();
					
					outputDVDocLinDTO.setCodigoDeclaracion(String.valueOf(tmp[0]));
					outputDVDocLinDTO.setAnyoDeclaracion(String.valueOf(tmp[1]));
					outputDVDocLinDTO.setVersionDeclaracion(String.valueOf(tmp[2]));
					outputDVDocLinDTO.setCodigoProducto(emptyStringOrValue(tmp[3]));					
					outputDVDocLinDTO.setNombreProducto(emptyStringOrValue(tmp[4]));
					outputDVDocLinDTO.setMarca(emptyStringOrValue(tmp[5]));
					outputDVDocLinDTO.setCodigoTaric(emptyStringOrValue(tmp[6]));
					outputDVDocLinDTO.setTipoLinea(String.valueOf(tmp[7]));
					outputDVDocLinDTO.setCodigoRea(emptyStringOrValue(tmp[8]));
					outputDVDocLinDTO.setPaisOrigen(emptyStringOrValue(tmp[9]));
					outputDVDocLinDTO.setLpc(emptyStringOrValue(tmp[10]));	
					outputDVDocLinDTO.setNumeroBultos(formatNumber(tipoDocumento,String.valueOf(tmp[11])));
					outputDVDocLinDTO.setTipoBultos(emptyStringOrFormatNumber(tipoDocumento,tmp[12]));
					outputDVDocLinDTO.setPesoBruto(formatNumber(tipoDocumento,String.valueOf(tmp[13])));
					outputDVDocLinDTO.setPesoNeto(formatNumber(tipoDocumento,String.valueOf(tmp[14])));
					outputDVDocLinDTO.setCantidad(formatNumber(tipoDocumento,String.valueOf(tmp[15])));
					outputDVDocLinDTO.setVolumen(emptyStringOrFormatNumber(tipoDocumento,tmp[16]));
					outputDVDocLinDTO.setAlcohol(emptyStringOrFormatNumber(tipoDocumento,tmp[17]));
					outputDVDocLinDTO.setPlato(emptyStringOrFormatNumber(tipoDocumento,tmp[18]));
					outputDVDocLinDTO.setPrecio(emptyStringOrFormatNumber(tipoDocumento,tmp[19]));
					outputDVDocLinDTO.setImporte(formatNumber(tipoDocumento,String.valueOf(tmp[20])));
					
					lineas.add(outputDVDocLinDTO);

				}
			}
			
		} catch(Exception ex) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG,LOG_FILE,"getDatosLineas",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}				
		
		return lineas;
	}
	
	
	private void getDatosPuertos(String puertoDesembarque,OutputDeclaracionesDeValorDocCabDTO outDVDocumentoDTO) {
		
		try {		
		
			// Query
			final StringBuilder sql = new StringBuilder();		
			
			StringBuilder select = new StringBuilder();	
			

			select.append("SELECT "); 
			select.append("SUBSTR(TXT_DIRECCION,0,INSTR(TXT_DIRECCION,CHR(10))) AS DIRECCION, ");
			select.append("SUBSTR(SUBSTR(TXT_DIRECCION,INSTR(TXT_DIRECCION,CHR(10))+1,INSTR(TXT_DIRECCION,CHR(32),INSTR(TXT_DIRECCION,CHR(10))+1,1)),0,INSTR(SUBSTR(TXT_DIRECCION,INSTR(TXT_DIRECCION,CHR(10))+1,INSTR(TXT_DIRECCION,CHR(32),INSTR(TXT_DIRECCION,CHR(10))+1,1)),CHR(32))-1) AS CODIGO_POSTAL, ");
			select.append("SUBSTR(TXT_DIRECCION,INSTR(TXT_DIRECCION,CHR(32),INSTR(TXT_DIRECCION,CHR(10))+1,1)+1,LENGTH(SUBSTR(TXT_DIRECCION,INSTR(TXT_DIRECCION,CHR(32),INSTR(TXT_DIRECCION,CHR(10))+1,1)+1,INSTR(TXT_DIRECCION,CHR(10),1,2)))-(LENGTH(TXT_DIRECCION)-INSTR(TXT_DIRECCION,CHR(10),1,2))) AS POBLACION, ");
			select.append("SUBSTR(TXT_DIRECCION,INSTR(TXT_DIRECCION,CHR(10),1,2)+1,LENGTH(TXT_DIRECCION)-INSTR(TXT_DIRECCION,CHR(10),1,2))AS PROVINCIA ");
			select.append("FROM D_PUERTO ");
			select.append("WHERE COD_N_PUERTO = ?puertoDesembarque ");			
							
			sql.append(select);
			
			final Query query = getEntityManager().createNativeQuery(sql.toString());		
			query.setParameter("puertoDesembarque", puertoDesembarque);
			
			List<Object[]> listado = query.getResultList();	
			
			if (listado != null && !listado.isEmpty()) {			
				for (Object[] tmp : listado) {	
					
					outDVDocumentoDTO.setImportadorDireccion(replaceReturn(emptyStringOrValue(tmp[0])));
					outDVDocumentoDTO.setImportadorCP(replaceReturn(emptyStringOrValue(tmp[1])));
					outDVDocumentoDTO.setImportadorPoblacion(replaceReturn(emptyStringOrValue(tmp[2])));
					outDVDocumentoDTO.setImportadorProvincia(replaceReturn(emptyStringOrValue(tmp[3])));					
				}						
			}		
		

		} catch (Exception e) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, LOG_FILE, "getDatosPuertos", e.getClass().getSimpleName(), e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}
		
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
			if ((value != null ) && (",".equals(value.substring(value.length()-1)))) {
				value = value.substring(0,value.length()-1);
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
	
	private String replaceReturn(String value) {
		if (value != null ) {
			value = value.replace("\n", " ");
			value = value.replace("\r", " ");
		}
		return value;
	}		
	
	private String emptyStringOrFormatNumber(String tipoDocumento,Object value) {
		String result = "";
		if (value != null) {
			result = formatNumber(tipoDocumento,String.valueOf(value));
		} else {
			result = "";
		}
		return result;
	}	
	
	private String emptyStringOrValue(Object value) {
		String result = "";
		if (value != null) {
			result = String.valueOf(String.valueOf(value));
		} else {
			result = "";
		}
		return result;
	}		
		
	
}
