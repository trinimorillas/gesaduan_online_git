package es.mercadona.gesaduan.dao.dosierapi.getdocument.v1.impl;

import java.math.BigDecimal;
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
import es.mercadona.gesaduan.dao.dosierapi.getdocument.v1.GetDocumentApiDAO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getdvdocumento.v1.OutputDeclaracionesDeValorDocCabDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getdvdocumento.v1.OutputDeclaracionesDeValorDocLinDTO;
import es.mercadona.gesaduan.dto.dosierapi.getdocument.v1.InputDossierDocumentDTO;
import es.mercadona.gesaduan.dto.dosierapi.getdocument.v1.OutputDossierDocHeadDTO;
import es.mercadona.gesaduan.jpa.dosier.getdocumento.v1.DocumentoDataJPA;
import es.mercadona.gesaduan.jpa.dosier.getdocumento.v1.DocumentoDataPK;

@Stateless
public class GetDocumentApiDAOImpl extends DaoBaseImpl<DocumentoDataPK, DocumentoDataJPA> implements GetDocumentApiDAO{

	@PersistenceContext
	private EntityManager entityM;
	
	@Inject
	private org.slf4j.Logger logger;
	
	private static final String LOG_FILE = "GetDocumentoApiDAOImpl(GESADUAN)"; 	
	
	@Override
	protected EntityManager getEntityManager() {
		
		return this.entityM;
	}

	@Override
	public void setEntityClass() {
		entityClass = DocumentoDataJPA.class;
		
	}

	@Override
	public OutputDossierDocHeadDTO getDatosDocumento(InputDossierDocumentDTO input) {
		
		return preparaEstructura(getDatosCabecera(input),getDatosLineas(input));
	}
	
	@Override
	public boolean isDosierInvalidado(InputDossierDocumentDTO input) {

		boolean isInvalidado = false;
		
		try {	
			
			// Query
			final StringBuilder sql = new StringBuilder();
			
			StringBuilder select = new StringBuilder();
			
			select.append("SELECT ");
			select.append("COD_N_ESTADO ");
			select.append("FROM D_DOSIER ");
			select.append("WHERE ");
			select.append("NUM_DOSIER = ?dossierNumber AND ");
			select.append("NUM_ANYO = ?dossierYear ");
			
			sql.append(select);		
			
			final Query query = getEntityManager().createNativeQuery(sql.toString());		
			query.setParameter("dossierNumber", input.getDossierNumber());
			query.setParameter("dossierYear", input.getDossierYear());
			
			@SuppressWarnings("unchecked")
			List<BigDecimal> listado = query.getResultList();	
			
			if (listado != null && !listado.isEmpty()) {			
				for (BigDecimal tmp : listado) {					
					int estadoDosier = Integer.parseInt(String.valueOf(tmp));		
					if (estadoDosier == 3) {
						isInvalidado = true;
					}
				}
			}			
			
		} catch(Exception ex) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG,LOG_FILE,"isDosierInvalidado",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}	
		
		return isInvalidado;
	}
	
	@Override
	public OutputDossierDocHeadDTO getDocumentoInvalidado(InputDossierDocumentDTO input) {	
		
		OutputDossierDocHeadDTO outDocumentoDTO;
		
		// Obtiene los datos de cabecera del documento	
		outDocumentoDTO = new OutputDossierDocHeadDTO();	
				
		try {
			
			DocumentoDataJPA documento =  null;
			
			DocumentoDataPK inputPK = new DocumentoDataPK();
			
			inputPK.setNumDosier(Long.parseLong(input.getDossierNumber()));
			inputPK.setAnyo(Integer.parseInt(input.getDossierYear()));			
			
			documento = findById(inputPK);
			
			outDocumentoDTO.setCodigoDosier(input.getDossierNumber());
			outDocumentoDTO.setAnyoDosier(input.getDossierYear());
			outDocumentoDTO.setTipoInforme(input.getDocumentType());
			if (documento != null) {
				outDocumentoDTO.setFicheroPDF(documento.getFicheroPdf());
			}
			
		} catch (Exception ex) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG,LOG_FILE,"getDocumentoInvalidado",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());
		}			
		
		
		return outDocumentoDTO;
	}	
	
	/* carga datos de cabecera de la DV */
	private List<OutputDeclaracionesDeValorDocCabDTO> getDatosCabecera(InputDossierDocumentDTO input) {
		
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
			select.append("D.NUM_DOSIER = ?dossierNumber AND ");
			select.append("D.NUM_ANYO_DOSIER = ?dossierYear ");
			select.append("ORDER BY D.COD_N_DECLARACION_VALOR,D.NUM_ANYO,D.COD_N_VERSION ");
			
			sql.append(select);
			
			final Query query = getEntityManager().createNativeQuery(sql.toString());		
			query.setParameter("dossierNumber", input.getDossierNumber());
			query.setParameter("dossierYear", input.getDossierYear());						
			
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
					outDVDocumentoCabDTO.setTxtInfoREA(emptyStringOrValue(tmp[23]));
					outDVDocumentoCabDTO.setTxtInfoLPC(emptyStringOrValue(tmp[24]));
					outDVDocumentoCabDTO.setTxtInfoGeneral(emptyStringOrValue(tmp[25]));					
					outDVDocumentoCabDTO.setTipoInforme(String.valueOf(tmp[26]));
					outDVDocumentoCabDTO.setNumPedido(emptyStringOrValue(tmp[27]));		
										
					// Si no tiene expedicion y tiene puerto 
					if ((tmp[28] == null) && (tmp[29] != null)) {
						String puertoDesembarque = emptyStringOrValue(tmp[29]);
						getDatosPuertos(puertoDesembarque,outDVDocumentoCabDTO);
					}			
					
					declaraciones.add(outDVDocumentoCabDTO);
				}
			}
			

			
		} catch(Exception ex) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG,LOG_FILE,"getDatosCabecera",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}	
		
		return declaraciones;
	}
	
	/* carga datos de lineas de la DV */
	private List<OutputDeclaracionesDeValorDocLinDTO> getDatosLineas(InputDossierDocumentDTO input) {
		
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
			select.append("INNER JOIN O_DECLARACION_VALOR_CAB DV ON DV.COD_N_DECLARACION_VALOR = DECLARACION_VALOR AND DV.NUM_ANYO= ANYO AND DV.COD_N_VERSION= VERSION_N ");
			select.append("WHERE ");
			select.append("DV.NUM_DOSIER = ?dossierNumber AND ");
			select.append("DV.NUM_ANYO_DOSIER = ?dossierYear ");
			select.append("ORDER BY DECLARACION_VALOR,ANYO,VERSION_N,CODIGO_TARIC,TIPO_LINEA,CODIGO ");
			
			sql.append(select);
			
			final Query query = getEntityManager().createNativeQuery(sql.toString());		
			query.setParameter("dossierNumber", input.getDossierNumber());
			query.setParameter("dossierYear", input.getDossierYear());							
			
			List<Object[]> listado = query.getResultList();	
			
			if (listado != null && !listado.isEmpty()) {			
				for (Object[] tmp : listado) {	
					
					OutputDeclaracionesDeValorDocLinDTO outDVDocumentoLinDTO = new OutputDeclaracionesDeValorDocLinDTO();
					
					outDVDocumentoLinDTO.setCodigoDeclaracion(String.valueOf(tmp[0]));
					outDVDocumentoLinDTO.setAnyoDeclaracion(String.valueOf(tmp[1]));
					outDVDocumentoLinDTO.setVersionDeclaracion(String.valueOf(tmp[2]));
					outDVDocumentoLinDTO.setCodigoProducto(emptyStringOrValue(tmp[3]));					
					outDVDocumentoLinDTO.setNombreProducto(emptyStringOrValue(tmp[4]));
					outDVDocumentoLinDTO.setMarca(emptyStringOrValue(tmp[5]));
					outDVDocumentoLinDTO.setCodigoTaric(emptyStringOrValue(tmp[6]));
					outDVDocumentoLinDTO.setTipoLinea(emptyStringOrValue(tmp[7]));
					outDVDocumentoLinDTO.setCodigoRea(emptyStringOrValue(tmp[8]));
					outDVDocumentoLinDTO.setPaisOrigen(emptyStringOrValue(tmp[9]));
					outDVDocumentoLinDTO.setLpc(emptyStringOrValue(tmp[10]));					
					outDVDocumentoLinDTO.setNumeroBultos(formatNumber(tipoDocumento,String.valueOf(tmp[11])));
					outDVDocumentoLinDTO.setTipoBultos(emptyStringOrFormatNumber(tipoDocumento,tmp[12]));
					outDVDocumentoLinDTO.setPesoBruto(formatNumber(tipoDocumento,String.valueOf(tmp[13])));
					outDVDocumentoLinDTO.setPesoNeto(formatNumber(tipoDocumento,String.valueOf(tmp[14])));
					outDVDocumentoLinDTO.setCantidad(formatNumber(tipoDocumento,String.valueOf(tmp[15])));
					outDVDocumentoLinDTO.setVolumen(emptyStringOrFormatNumber(tipoDocumento,tmp[16]));
					outDVDocumentoLinDTO.setAlcohol(emptyStringOrFormatNumber(tipoDocumento,tmp[17]));
					outDVDocumentoLinDTO.setPlato(emptyStringOrFormatNumber(tipoDocumento,tmp[18]));
					outDVDocumentoLinDTO.setPrecio(emptyStringOrFormatNumber(tipoDocumento,tmp[19]));
					outDVDocumentoLinDTO.setImporte(formatNumber(tipoDocumento,String.valueOf(tmp[20])));
					outDVDocumentoLinDTO.setProcesada(false);
					
					lineas.add(outDVDocumentoLinDTO);

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
	
	/* carga datos de cabecera de la DV */
	private OutputDossierDocHeadDTO preparaEstructura(List<OutputDeclaracionesDeValorDocCabDTO> declaraciones, List<OutputDeclaracionesDeValorDocLinDTO> lineas) {
		
		OutputDossierDocHeadDTO outDocumentoDTO;
		
		// Obtiene los datos de cabecera del documento	
		outDocumentoDTO = new OutputDossierDocHeadDTO();	
		
		

		// Obtiene los datos de lineas del documento
		if (!declaraciones.isEmpty()) {
			
			outDocumentoDTO.setCodigoDosier(declaraciones.get(0).getNumDosier());
			outDocumentoDTO.setAnyoDosier(declaraciones.get(0).getAnyoDosier());
			outDocumentoDTO.setFechaDosier(declaraciones.get(0).getFechaDosier());
			outDocumentoDTO.setTipoInforme(declaraciones.get(0).getTipoInforme());

			for (OutputDeclaracionesDeValorDocCabDTO outDVDocumentoCabDTO : declaraciones) {
				
				List<OutputDeclaracionesDeValorDocLinDTO> lineasDeclaracion = new ArrayList<>();
				
				for (OutputDeclaracionesDeValorDocLinDTO outDVDocumentoLinDTO : lineas) {
					if (!outDVDocumentoLinDTO.isProcesada() && 
					    outDVDocumentoCabDTO.getCodigoDeclaracion().equals(outDVDocumentoLinDTO.getCodigoDeclaracion()) && 
						outDVDocumentoCabDTO.getAnyoDeclaracion().equals(outDVDocumentoLinDTO.getAnyoDeclaracion()) &&
						outDVDocumentoCabDTO.getVersionDeclaracion().equals(outDVDocumentoLinDTO.getVersionDeclaracion())) {
							
						lineasDeclaracion.add(outDVDocumentoLinDTO);
						outDVDocumentoLinDTO.setProcesada(true);
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
