package es.mercadona.gesaduan.dao.declaracionesdevalorapi.getvddocument.v2.impl;

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
import es.mercadona.gesaduan.dao.declaracionesdevalorapi.getvddocument.v2.GetVDDocApiDAO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvddocument.v2.InputValueDeclarationDocumentDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvddocument.v2.OutputDeclaracionesDeValorDocCabDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvddocument.v2.OutputDeclaracionesDeValorDocLinDTO;
import es.mercadona.gesaduan.jpa.declaracionesdevalorapi.getvddocument.v2.VDDocumentJPA;
import es.mercadona.gesaduan.jpa.declaracionesdevalorapi.getvddocument.v2.VDDocumentPK;

@Stateless
public class GetVDDocApiDAOImpl extends DaoBaseImpl<VDDocumentPK, VDDocumentJPA> implements GetVDDocApiDAO {

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
		entityClass = VDDocumentJPA.class;
	}
	
	private static final String LOG_FILE = "GetDVDocumentoApiDAOImpl(GESADUAN)";
	private static final String VALUE_DECLARATION_NUMBER = "valueDeclarationNumber";
	private static final String VALUE_DECLARATION_YEAR = "valueDeclarationYear";
	private static final String VALUE_DECLARATION_VERSION = "valueDeclarationVersion";

	@Override
	public OutputDeclaracionesDeValorDocCabDTO getDatosDocumento(InputValueDeclarationDocumentDTO input) {
		// Obtiene los datos de cabecera del documento
		return getDatosCabecera(input);
	}

	/* Carga datos de cabecera de la DV */
	private OutputDeclaracionesDeValorDocCabDTO getDatosCabecera(InputValueDeclarationDocumentDTO input) {
		OutputDeclaracionesDeValorDocCabDTO outDVDocumentoDTO;

		// Obtiene los datos de cabecera del documento
		outDVDocumentoDTO = new OutputDeclaracionesDeValorDocCabDTO();

		try {
			final StringBuilder sql = new StringBuilder();

			sql.append("SELECT DISTINCT TO_CHAR(D.COD_N_DECLARACION_VALOR) FACTURA, ");
			sql.append("TO_CHAR(D.NUM_ANYO) ANYO, ");
			sql.append("TO_CHAR(D.COD_N_VERSION) VERSION, ");
			sql.append("D.COD_V_TIPO_FACTURA TIPO_FACTURA, ");
			sql.append("TO_CHAR(D.FEC_DT_CREACION, 'DD/MM/YYYY') FECHA_DECLARACION, ");
			sql.append("TO_CHAR(D.NUM_DOSIER) DOSIER, ");
			sql.append("TO_CHAR(D.NUM_ANYO_DOSIER) ANYO_DOSIER, ");
			sql.append("TO_CHAR(DOS.FEC_DT_CREACION, 'DD/MM/YYYY') FECHA_DOSIER, ");
			sql.append("CASE D.COD_V_TIPO_FACTURA WHEN 'CSM' THEN REGEXP_REPLACE(P.TXT_RAZON_SOCIAL, '\"|;', '') ");
			sql.append("WHEN 'PED' THEN REGEXP_REPLACE(BL.TXT_NOMBRE, '\"|;', '') ");
			sql.append("WHEN 'DEV' THEN REGEXP_REPLACE(C2.TXT_RAZON_SOCIAL, '\"|;', '') ");
			sql.append("ELSE NULL END NOMBRE_ORIGEN, ");
			sql.append("R.TXT_NOMBRE PROVINCIA_ORIGEN, ");
			sql.append("CASE D.COD_V_TIPO_FACTURA WHEN 'CSM' THEN 'PROVEEDOR' ");
			sql.append("WHEN 'PED' THEN 'BLOQUE' ");
			sql.append("WHEN 'DEV' THEN 'TIENDA' ");
			sql.append("ELSE '' END TIPO_ORIGEN, ");
			sql.append("D.TXT_CONDICIONES_ENTREGA CONDICIONES_ENTREGA, ");
			sql.append("REGEXP_REPLACE(E.TXT_RAZON_SOCIAL, '\"|;', '') EXP_NOMBRE, ");
			sql.append("REGEXP_REPLACE(E.TXT_DIRECCION, '\"|;', '') EXP_DIRECCION, ");
			sql.append("E.TXT_CODIGO_POSTAL EXP_CP, ");
			sql.append("E.TXT_POBLACION EXP_POBLACION, ");
			sql.append("E.TXT_PROVINCIA EXP_PROVINCIA, ");
			sql.append("E.NUM_CIF EXP_NIF, ");
			sql.append("CASE D.COD_V_TIPO_FACTURA WHEN 'CSM' THEN REGEXP_REPLACE(C1.TXT_RAZON_SOCIAL, '\"|;', '') ");
			sql.append("WHEN 'PED' THEN REGEXP_REPLACE(PU.TXT_NOMBRE_PUERTO, '\"|;', '') ");
			sql.append("WHEN 'DEV' THEN REGEXP_REPLACE(BL.TXT_NOMBRE, '\"|;', '') ");
			sql.append("ELSE '' END IMP_NOMBRE, ");
			sql.append("CASE D.COD_V_TIPO_FACTURA WHEN 'CSM' THEN REGEXP_REPLACE(C1.TXT_DIRECCION, '\\\"|;', '') ");
			sql.append("WHEN 'PED' THEN SUBSTR(PU.TXT_DIRECCION,0,INSTR(PU.TXT_DIRECCION,CHR(10))) ");
			sql.append("WHEN 'DEV' THEN REGEXP_REPLACE(C3.TXT_DIRECCION, '\\\"|;', '') ");
			sql.append("ELSE '' END IMP_DIRECCION, ");
			sql.append("CASE D.COD_V_TIPO_FACTURA WHEN 'CSM' THEN REGEXP_REPLACE(C1.TXT_COD_POSTAL, '\\\"|;', '') ");
			sql.append("WHEN 'PED' THEN SUBSTR(SUBSTR(PU.TXT_DIRECCION,INSTR(PU.TXT_DIRECCION,CHR(10))+1, ");
			sql.append("INSTR(PU.TXT_DIRECCION,CHR(32),INSTR(PU.TXT_DIRECCION,CHR(10))+1,1)),0, ");
			sql.append("INSTR(SUBSTR(PU.TXT_DIRECCION,INSTR(PU.TXT_DIRECCION,CHR(10))+1, ");
			sql.append("INSTR(PU.TXT_DIRECCION,CHR(32),INSTR(PU.TXT_DIRECCION,CHR(10))+1,1)),CHR(32))-1) ");
			sql.append("WHEN 'DEV' THEN REGEXP_REPLACE(C3.TXT_COD_POSTAL, '\\\"|;', '') ");
			sql.append("ELSE '' END IMP_CP, ");
			sql.append("CASE D.COD_V_TIPO_FACTURA WHEN 'CSM' THEN REGEXP_REPLACE(C1.TXT_LOCALIDAD, '\\\"|;', '') ");
			sql.append("WHEN 'PED' THEN SUBSTR(PU.TXT_DIRECCION,INSTR(PU.TXT_DIRECCION,CHR(32), ");
			sql.append("INSTR(PU.TXT_DIRECCION,CHR(10))+1,1)+1,LENGTH(SUBSTR(PU.TXT_DIRECCION, ");
			sql.append("INSTR(PU.TXT_DIRECCION,CHR(32),INSTR(PU.TXT_DIRECCION,CHR(10))+1,1)+1, ");
			sql.append("INSTR(PU.TXT_DIRECCION,CHR(10),1,2)))-(LENGTH(PU.TXT_DIRECCION)- ");
			sql.append("INSTR(PU.TXT_DIRECCION,CHR(10),1,2))) ");
			sql.append("WHEN 'DEV' THEN REGEXP_REPLACE(C3.TXT_LOCALIDAD, '\\\"|;', '') ");
			sql.append("ELSE '' END IMP_POBLACION, ");
			sql.append("CASE D.COD_V_TIPO_FACTURA WHEN 'CSM' THEN REGEXP_REPLACE(C1.TXT_PROVINCIA, '\\\"|;', '') ");
			sql.append("WHEN 'PED' THEN SUBSTR(PU.TXT_DIRECCION,INSTR(PU.TXT_DIRECCION,CHR(10),1,2)+1,LENGTH(PU.TXT_DIRECCION)-INSTR(PU.TXT_DIRECCION,CHR(10),1,2)) ");
			sql.append("WHEN 'DEV' THEN REGEXP_REPLACE(C3.TXT_PROVINCIA, '\\\"|;', '') ");
			sql.append("ELSE '' END IMP_PROVINCIA, ");
			sql.append("E.NUM_CIF IMP_NIF, ");
			sql.append("( ");
			sql.append("SELECT TXT_VALOR ");
			sql.append("FROM C_VARIABLE ");
			sql.append("WHERE COD_V_VARIABLE = 'MERCANCIA_REA' ");
			sql.append("AND EXISTS ( ");
			sql.append("SELECT L.COD_N_MERCA ");
			sql.append("FROM O_DECLARACION_VALOR_LIN L ");
			sql.append("WHERE L.COD_N_DECLARACION_VALOR = D.COD_N_DECLARACION_VALOR ");
			sql.append("AND L.NUM_ANYO = D.NUM_ANYO ");
			sql.append("AND L.COD_N_VERSION = D.COD_N_VERSION ");
			sql.append("AND L.COD_V_REA IS NOT NULL ");
			sql.append(") ");
			sql.append(") AS TXT_INFO_REA, ");
			sql.append("( ");
			sql.append("SELECT TXT_VALOR ");
			sql.append("FROM C_VARIABLE ");
			sql.append("WHERE COD_V_VARIABLE = 'MERCANCIA_REG_CANARIO' ");
			sql.append("AND EXISTS ( ");
			sql.append("SELECT L.COD_N_MERCA ");
			sql.append("FROM O_DECLARACION_VALOR_LIN L ");
			sql.append("WHERE L.COD_N_DECLARACION_VALOR = D.COD_N_DECLARACION_VALOR ");
			sql.append("AND L.NUM_ANYO = D.NUM_ANYO ");
			sql.append("AND L.COD_N_VERSION = D.COD_N_VERSION ");
			sql.append("AND L.MCA_PRODUCTO_LPC = 'S' ");
			sql.append(") ");
			sql.append(") AS TXT_INFO_LPC, ");
			sql.append("( ");
			sql.append("SELECT TXT_VALOR ");
			sql.append("FROM C_VARIABLE ");
			sql.append("WHERE COD_V_VARIABLE = 'MERCANCIA_EXENTA_IVA' ");
			sql.append(") AS TXT_INFO_GENERAL, ");
			sql.append("( ");
			sql.append("SELECT ( ");
			sql.append("CASE ");
			sql.append("WHEN ((COUNT(L.NUM_GRADO_ALCOHOL) > 0) AND ((COUNT(L.NUM_VOLUMEN)) > 0)) THEN 'VOLUMEN_ALCOHOL' ");
			sql.append("WHEN COUNT(L.NUM_VOLUMEN) > 0 THEN 'VOLUMEN' ");
			sql.append("ELSE 'BASE' ");
			sql.append("END ");
			sql.append(") ");
			sql.append("FROM O_DECLARACION_VALOR_LIN L ");
			sql.append("WHERE L.COD_N_DECLARACION_VALOR = D.COD_N_DECLARACION_VALOR ");
			sql.append("AND L.NUM_ANYO = D.NUM_ANYO ");
			sql.append("AND L.COD_N_VERSION = D.COD_N_VERSION ");
			sql.append("GROUP BY L.COD_N_DECLARACION_VALOR, L.NUM_ANYO, L.COD_N_VERSION ");
			sql.append(") AS TIPO_INFORME, ");
			sql.append("D.COD_V_PEDIDO COD_PEDIDO, ");
			sql.append("D.COD_V_EXPEDICION COD_EXPEDICION, ");
			sql.append("D.COD_N_PUERTO_DESEMBARQUE COD_PUERTO, ");
			sql.append("D.NUM_DEVOLUCION NUM_DEVOLUCION, ");
			sql.append("D.NUM_ANYO_DEVOLUCION NUM_ANYO_DEVOLUCION ");
			sql.append("FROM O_DECLARACION_VALOR_CAB D ");
			sql.append("LEFT JOIN D_PROVEEDOR_R P ON D.COD_N_PROVEEDOR = P.COD_N_PROVEEDOR ");
			sql.append("LEFT JOIN D_PROVINCIA_R R ON D.COD_N_PROVINCIA_CARGA = R.COD_N_PROVINCIA ");
			sql.append("LEFT JOIN D_EMPRESA_R E ON E.COD_V_EMPRESA = '08' ");
			sql.append("LEFT JOIN D_BLOQUE_LOGISTICO_R BL ON BL.COD_N_BLOQUE_LOGISTICO = D.COD_N_BLOQUE_LOGISTICO ");
			sql.append("LEFT JOIN D_PUERTO PU ON PU.COD_N_PUERTO = D.COD_N_PUERTO_DESEMBARQUE ");
			sql.append("LEFT JOIN D_CENTRO_R C1 ON D.COD_V_ALMACEN = C1.COD_V_CENTRO ");
			sql.append("LEFT JOIN D_CENTRO_R C2 ON C2.COD_V_CENTRO = D.COD_V_TIENDA ");
			sql.append("LEFT JOIN D_CENTRO_R C3 ON C3.COD_V_CENTRO = BL.COD_V_CENTRO ");
			sql.append("LEFT JOIN D_DOSIER DOS ON DOS.NUM_DOSIER = D.NUM_DOSIER AND DOS.NUM_ANYO = D.NUM_ANYO_DOSIER ");
			sql.append("WHERE ");
			sql.append("D.COD_N_DECLARACION_VALOR = ?valueDeclarationNumber ");
			sql.append("AND D.NUM_ANYO = ?valueDeclarationYear ");
			sql.append("AND D.COD_N_VERSION = ?valueDeclarationVersion");

			final Query query = getEntityManager().createNativeQuery(sql.toString());
			query.setParameter(VALUE_DECLARATION_NUMBER, input.getValueDeclarationNumber());
			query.setParameter(VALUE_DECLARATION_YEAR, input.getValueDeclarationYear());
			query.setParameter(VALUE_DECLARATION_VERSION, input.getValueDeclarationVersion());

			@SuppressWarnings("unchecked")
			List<Object[]> listado = query.getResultList();

			if (listado != null && !listado.isEmpty()) {
				for (Object[] tmp : listado) {
					outDVDocumentoDTO.setCodigoDeclaracion(String.valueOf(tmp[0]));
					outDVDocumentoDTO.setAnyoDeclaracion(String.valueOf(tmp[1]));
					outDVDocumentoDTO.setVersionDeclaracion(String.valueOf(tmp[2]));
					outDVDocumentoDTO.setTipoFactura(String.valueOf(tmp[3]));
					outDVDocumentoDTO.setFechaDeclaracion(String.valueOf(tmp[4]));
					outDVDocumentoDTO.setNumDosier(String.valueOf(tmp[5]));
					outDVDocumentoDTO.setAnyoDosier(String.valueOf(tmp[6]));
					outDVDocumentoDTO.setFechaDosier(String.valueOf(tmp[7]));
					outDVDocumentoDTO.setNombreOrigen(String.valueOf(tmp[8]));
					outDVDocumentoDTO.setProvinciaOrigen(String.valueOf(tmp[9]));
					outDVDocumentoDTO.setTipoOrigen(String.valueOf(tmp[10]));
					outDVDocumentoDTO.setCondicionesEntrega(String.valueOf(tmp[11]));
					outDVDocumentoDTO.setExportadorNombre(String.valueOf(tmp[12]));
					outDVDocumentoDTO.setExportadorDireccion(String.valueOf(tmp[13]));
					outDVDocumentoDTO.setExportadorCP(String.valueOf(tmp[14]));
					outDVDocumentoDTO.setExportadorPoblacion(String.valueOf(tmp[15]));
					outDVDocumentoDTO.setExportadorProvincia(String.valueOf(tmp[16]));
					outDVDocumentoDTO.setExportadorNIF(String.valueOf(tmp[17]));

					outDVDocumentoDTO.setImportadorNombre(String.valueOf(tmp[18]));
					outDVDocumentoDTO.setImportadorDireccion(String.valueOf(tmp[19]));
					outDVDocumentoDTO.setImportadorCP(String.valueOf(tmp[20]));
					outDVDocumentoDTO.setImportadorPoblacion(String.valueOf(tmp[21]));
					outDVDocumentoDTO.setImportadorProvincia(String.valueOf(tmp[22]));
					outDVDocumentoDTO.setImportadorNIF(String.valueOf(tmp[23]));

					outDVDocumentoDTO.setTxtInfoREA(emptyStringOrValue(tmp[24]));
					outDVDocumentoDTO.setTxtInfoLPC(emptyStringOrValue(tmp[25]));
					outDVDocumentoDTO.setTxtInfoGeneral(emptyStringOrValue(tmp[26]));
					outDVDocumentoDTO.setTipoInforme(String.valueOf(tmp[27]));
					outDVDocumentoDTO.setNumPedido(emptyStringOrValue(tmp[28]));

					// Si no tiene expedicion y tiene puerto
					if ((tmp[29] == null) && (tmp[30] != null)) {
						String puertoDesembarque = emptyStringOrValue(tmp[30]);
						getDatosPuertos(puertoDesembarque, outDVDocumentoDTO);
					}
				}
			}
			// Obtiene los datos de lineas del documento
			outDVDocumentoDTO.setLineas(getDatosLineas(input));
		} catch(Exception ex) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG,LOG_FILE, "getDatosCabecera", ex.getClass().getSimpleName(), ex.getMessage());
			throw new ApplicationException(ex.getMessage());
		}

		return outDVDocumentoDTO;
	}

	/* carga datos de lineas de la DV */
	private List<OutputDeclaracionesDeValorDocLinDTO> getDatosLineas(InputValueDeclarationDocumentDTO input) {
		List<OutputDeclaracionesDeValorDocLinDTO> lineas = new ArrayList<>();
		String tipoDocumento = input.getDocumentType();

		try {
			final StringBuilder sql = new StringBuilder();

			sql.append("SELECT TO_CHAR(DECLARACION_VALOR), TO_CHAR(ANYO), TO_CHAR(VERSION_N), TO_CHAR(CODIGO), TIPO_ITEM, ");
			sql.append("PRODUCTO, MARCA, TO_CHAR(CODIGO_TARIC), TIPO_LINEA, CODIGO_REA, PAIS_ORIGEN, LPC, ");
			sql.append("TO_CHAR(BULTOS, 'FM9G999G990D999'), TIPO, TO_CHAR(PESO_BRUTO, 'FM9G999G990D999'), ");
			sql.append("TO_CHAR(PESO_NETO, 'FM9G999G990D999'), TO_CHAR(CANTIDAD, 'FM9G999G990D999'), ");
			sql.append("TO_CHAR(VOLUMEN, 'FM9G999G990D999'), TO_CHAR(ALCOHOL, 'FM9G999G990D999'), ");
			sql.append("TO_CHAR(PLATO, 'FM9G999G990D999'), TO_CHAR(PRECIO, 'FM9G999G990D9999'), ");
			sql.append("TO_CHAR(IMPORTE, 'FM9G999G990D999') ");
			sql.append("FROM ( ");
			sql.append("SELECT ");
			sql.append("L.COD_N_DECLARACION_VALOR DECLARACION_VALOR, L.NUM_ANYO ANYO, ");
			sql.append("L.COD_N_VERSION VERSION_N, TO_CHAR(L.COD_N_MERCA) CODIGO, 'PR' TIPO_ITEM, ");
			sql.append("DECODE(P.TXT_DENOMINA_ALTERNATIVA, NULL, DP.TXT_DESCRIPCION, P.TXT_DENOMINA_ALTERNATIVA) AS PRODUCTO, ");
			sql.append("P.TXT_MARCA MARCA, L.COD_N_TARIC CODIGO_TARIC, 'PRODUCTO' TIPO_LINEA, L.COD_V_REA CODIGO_REA, ");
			sql.append("I.TXT_NOMBRE PAIS_ORIGEN, L.MCA_PRODUCTO_LPC LPC, L.NUM_BULTOS BULTOS, L.TXT_NOMBRE_BULTO_DV TIPO, ");
			sql.append("L.NUM_PESO_BRUTO PESO_BRUTO, L.NUM_PESO_NETO PESO_NETO, L.NUM_CANTIDAD CANTIDAD, L.NUM_VOLUMEN VOLUMEN, ");
			sql.append("L.NUM_GRADO_ALCOHOL ALCOHOL, L.NUM_GRADO_PLATO PLATO, L.NUM_PRECIO_UNIDAD PRECIO, L.NUM_IMPORTE_TOTAL IMPORTE ");
			sql.append("FROM O_DECLARACION_VALOR_LIN L ");
			sql.append("LEFT JOIN D_PRODUCTO_R P ON L.COD_N_MERCA = P.COD_N_PRODUCTO ");
			sql.append("LEFT JOIN S_DENOMINACION_PRODUCTO_I18N_R DP ON (DP.COD_N_PRODUCTO = P.COD_N_PRODUCTO AND COD_V_LOCALE = 'es_ES') ");
			sql.append("LEFT JOIN D_PAIS_R I ON L.COD_V_PAIS = I.COD_V_ISO_ALFA2 ");
			sql.append("UNION ");
			sql.append("SELECT ");
			sql.append("E.COD_N_DECLARACION_VALOR DECLARACION_VALOR, E.NUM_ANYO ANYO, E.COD_N_VERSION VERSION_N, TO_CHAR(E.COD_V_ENVASE) CODIGO, ");
			sql.append("'EN' TIPO_ITEM, E.TXT_DENOMINACION AS PRODUCTO, '' MARCA, E.COD_N_TARIC CODIGO_TARIC, ");
			sql.append("'ENVASE' TIPO_LINEA, '' CODIGO_REA, '' PAIS_ORIGEN, '' LPC, E.NUM_CANTIDAD BULTOS, '' TIPO, ");
			sql.append("E.NUM_PESO_BRUTO PESO_BRUTO, E.NUM_PESO_NETO PESO_NETO, E.NUM_CANTIDAD CANTIDAD, NULL VOLUMEN, ");
			sql.append("NULL ALCOHOL, NULL PLATO, E.NUM_PRECIO_UNIDAD PRECIO, E.NUM_IMPORTE IMPORTE ");
			sql.append("FROM O_DECLARACION_VALOR_ENV E ");
			sql.append("UNION ");
			sql.append("SELECT ");
			sql.append("DECLARACION_VALOR, ANYO, VERSION_N, 'TARIC' CODIGO, 'TA' TIPO_ITEM, PRODUCTO, ");
			sql.append("'' MARCA, CODIGO_TARIC, 'TARIC' TIPO_LINEA, '' CODIGO_REA, '' PAIS_ORIGEN, '' LPC, ");
			sql.append("SUM(BULTOS) BULTOS, '' TIPO, SUM(PESO_BRUTO) PESO_BRUTO, SUM(PESO_NETO) PESO_NETO, ");
			sql.append("SUM(CANTIDAD) CANTIDAD, SUM(VOLUMEN) VOLUMEN, NULL ALCOHOL, NULL PLATO, NULL PRECIO, ");
			sql.append("SUM(IMPORTE) IMPORTE ");
			sql.append("FROM ( ");
			sql.append("SELECT ");
			sql.append("L.COD_N_DECLARACION_VALOR DECLARACION_VALOR, L.NUM_ANYO ANYO, L.COD_N_VERSION VERSION_N, ");
			sql.append("TO_CHAR(L.COD_N_TARIC) PRODUCTO, L.COD_N_TARIC CODIGO_TARIC, SUM(L.NUM_BULTOS) BULTOS, ");
			sql.append("SUM(L.NUM_PESO_BRUTO) PESO_BRUTO, SUM(L.NUM_PESO_NETO) PESO_NETO, SUM(L.NUM_CANTIDAD) CANTIDAD, ");
			sql.append("SUM(L.NUM_VOLUMEN) VOLUMEN, SUM(L.NUM_IMPORTE_TOTAL) IMPORTE ");
			sql.append("FROM O_DECLARACION_VALOR_LIN L ");
			sql.append("GROUP BY L.COD_N_DECLARACION_VALOR ,L.NUM_ANYO,L.COD_N_VERSION,L.COD_N_TARIC ");
			sql.append("UNION ");
			sql.append("SELECT ");
			sql.append("E.COD_N_DECLARACION_VALOR DECLARACION_VALOR, E.NUM_ANYO ANYO, ");
			sql.append("E.COD_N_VERSION VERSION_N, TO_CHAR(E.COD_N_TARIC) PRODUCTO, ");
			sql.append("E.COD_N_TARIC CODIGO_TARIC, SUM(E.NUM_CANTIDAD) BULTOS, ");
			sql.append("SUM(E.NUM_PESO_BRUTO) PESO_BRUTO, SUM(E.NUM_PESO_NETO) PESO_NETO, ");
			sql.append("SUM(E.NUM_CANTIDAD) CANTIDAD, NULL VOLUMEN, SUM(E.NUM_IMPORTE) IMPORTE ");
			sql.append("FROM O_DECLARACION_VALOR_ENV E ");
			sql.append("GROUP BY E.COD_N_DECLARACION_VALOR, E.NUM_ANYO, E.COD_N_VERSION, E.COD_N_TARIC ");
			sql.append(") ");
			sql.append("GROUP BY DECLARACION_VALOR, ANYO, VERSION_N, PRODUCTO, CODIGO_TARIC ");
			sql.append(") ");
			sql.append("WHERE DECLARACION_VALOR = ?valueDeclarationNumber AND ANYO = ?valueDeclarationYear AND VERSION_N = ?valueDeclarationVersion ");
			sql.append("ORDER BY CODIGO_TARIC, TIPO_LINEA, CODIGO");

			final Query query = getEntityManager().createNativeQuery(sql.toString());
			query.setParameter(VALUE_DECLARATION_NUMBER, input.getValueDeclarationNumber());
			query.setParameter(VALUE_DECLARATION_YEAR, input.getValueDeclarationYear());
			query.setParameter(VALUE_DECLARATION_VERSION, input.getValueDeclarationVersion());

			@SuppressWarnings("unchecked")
			List<Object[]> listado = query.getResultList();

			if (listado != null && !listado.isEmpty()) {
				for (Object[] tmp : listado) {
					OutputDeclaracionesDeValorDocLinDTO outputDVDocLinDTO = new OutputDeclaracionesDeValorDocLinDTO();

					outputDVDocLinDTO.setCodigoDeclaracion(String.valueOf(tmp[0]));
					outputDVDocLinDTO.setAnyoDeclaracion(String.valueOf(tmp[1]));
					outputDVDocLinDTO.setVersionDeclaracion(String.valueOf(tmp[2]));
					outputDVDocLinDTO.setCodigoProducto(emptyStringOrValue(tmp[3]));
					outputDVDocLinDTO.setTipoItem(emptyStringOrValue(tmp[4]));
					outputDVDocLinDTO.setNombreProducto(emptyStringOrValue(tmp[5]));
					outputDVDocLinDTO.setMarca(emptyStringOrValue(tmp[6]));
					outputDVDocLinDTO.setCodigoTaric(emptyStringOrValue(tmp[7]));
					outputDVDocLinDTO.setTipoLinea(String.valueOf(tmp[8]));
					outputDVDocLinDTO.setCodigoRea(emptyStringOrValue(tmp[9]));
					outputDVDocLinDTO.setPaisOrigen(emptyStringOrValue(tmp[10]));
					outputDVDocLinDTO.setLpc(emptyStringOrValue(tmp[11]));
					outputDVDocLinDTO.setNumeroBultos(formatNumber(tipoDocumento,String.valueOf(tmp[12])));
					outputDVDocLinDTO.setTipoBultos(emptyStringOrValue(tmp[13]));
					outputDVDocLinDTO.setPesoBruto(formatNumber(tipoDocumento,String.valueOf(tmp[14])));
					outputDVDocLinDTO.setPesoNeto(formatNumber(tipoDocumento,String.valueOf(tmp[15])));
					outputDVDocLinDTO.setCantidad(formatNumber(tipoDocumento,String.valueOf(tmp[16])));
					outputDVDocLinDTO.setVolumen(emptyStringOrFormatNumber(tipoDocumento,tmp[17]));
					outputDVDocLinDTO.setAlcohol(emptyStringOrFormatNumber(tipoDocumento,tmp[18]));
					outputDVDocLinDTO.setPlato(emptyStringOrFormatNumber(tipoDocumento,tmp[19]));
					outputDVDocLinDTO.setPrecio(emptyStringOrFormatNumber(tipoDocumento,tmp[20]));
					outputDVDocLinDTO.setImporte(formatNumber(tipoDocumento,String.valueOf(tmp[21])));

					lineas.add(outputDVDocLinDTO);
				}
			}
			getLineasTotal(input, lineas);
		} catch(Exception ex) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG,LOG_FILE, "getDatosLineas", ex.getClass().getSimpleName(), ex.getMessage());
			throw new ApplicationException(ex.getMessage());
		}
		return lineas;
	}

	/* carga datos de lineas de la DV */
	private List<OutputDeclaracionesDeValorDocLinDTO> getLineasTotal(InputValueDeclarationDocumentDTO input, List<OutputDeclaracionesDeValorDocLinDTO> lineas) {
		String tipoDocumento = input.getDocumentType();

		try {
			final StringBuilder sql = new StringBuilder();

			sql.append("SELECT ");
			sql.append("DECLARACION_VALOR, ");
			sql.append("ANYO, ");
			sql.append("VERSION_N, ");
			sql.append("'TOTAL' CODIGO, ");
			sql.append("'TO' TIPO_ITEM, ");
			sql.append("'' PRODUCTO, ");
			sql.append("'' MARCA, ");
			sql.append("NULL CODIGO_TARIC, ");
			sql.append("'TOTAL' TIPO_LINEA, ");
			sql.append("'' CODIGO_REA, ");
			sql.append("'' PAIS_ORIGEN, ");
			sql.append("'' LPC, ");
			sql.append("TO_CHAR(SUM(BULTOS), 'FM9G999G990D999'), ");
			sql.append("'' TIPO, ");
			sql.append("TO_CHAR(SUM(PESO_BRUTO), 'FM9G999G990D999'), ");
			sql.append("TO_CHAR(SUM(PESO_NETO), 'FM9G999G990D999'), ");
			sql.append("TO_CHAR(SUM(CANTIDAD), 'FM9G999G990D999'), ");
			sql.append("TO_CHAR(SUM(VOLUMEN), 'FM9G999G990D999'), ");
			sql.append("TO_CHAR(SUM(ALCOHOL), 'FM9G999G990D999'), ");
			sql.append("TO_CHAR(SUM(PLATO), 'FM9G999G990D999'), ");
			sql.append("TO_CHAR(SUM(PRECIO), 'FM9G999G990D9999'), ");
			sql.append("TO_CHAR(SUM(IMPORTE), 'FM9G999G990D999') ");
			sql.append("FROM ( ");
			sql.append("SELECT ");
			sql.append("L.COD_N_DECLARACION_VALOR DECLARACION_VALOR, ");
			sql.append("L.NUM_ANYO ANYO, ");
			sql.append("L.COD_N_VERSION VERSION_N, ");
			sql.append("SUM(L.NUM_BULTOS) BULTOS, ");
			sql.append("SUM(L.NUM_PESO_BRUTO) PESO_BRUTO, ");
			sql.append("SUM(L.NUM_PESO_NETO) PESO_NETO, ");
			sql.append("SUM(L.NUM_CANTIDAD) CANTIDAD, ");
			sql.append("SUM(L.NUM_VOLUMEN) VOLUMEN, ");
			sql.append("NULL ALCOHOL, ");
			sql.append("NULL PLATO, ");
			sql.append("NULL PRECIO, ");
			sql.append("SUM(L.NUM_IMPORTE_TOTAL) IMPORTE ");
			sql.append("FROM O_DECLARACION_VALOR_LIN L ");
			sql.append("GROUP BY L.COD_N_DECLARACION_VALOR,L.NUM_ANYO,L.COD_N_VERSION ");
			sql.append("UNION ");
			sql.append("SELECT ");
			sql.append("E.COD_N_DECLARACION_VALOR DECLARACION_VALOR, ");
			sql.append("E.NUM_ANYO ANYO, ");
			sql.append("E.COD_N_VERSION VERSION_N, ");
			sql.append("SUM(E.NUM_CANTIDAD) BULTOS, ");
			sql.append("SUM(E.NUM_PESO_BRUTO) PESO_BRUTO, ");
			sql.append("SUM(E.NUM_PESO_NETO) PESO_NETO, ");
			sql.append("SUM(E.NUM_CANTIDAD) CANTIDAD, ");
			sql.append("NULL VOLUMEN, ");
			sql.append("NULL ALCOHOL, ");
			sql.append("NULL PLATO, ");
			sql.append("NULL PRECIO, ");
			sql.append("SUM(E.NUM_IMPORTE) IMPORTE ");
			sql.append("FROM O_DECLARACION_VALOR_ENV E ");
			sql.append("GROUP BY E.COD_N_DECLARACION_VALOR,E.NUM_ANYO,E.COD_N_VERSION ");
			sql.append(")WHERE ");
			sql.append("DECLARACION_VALOR = ?valueDeclarationNumber ");
			sql.append("AND ANYO = ?valueDeclarationYear ");
			sql.append("AND VERSION_N = ?valueDeclarationVersion ");
			sql.append("GROUP BY DECLARACION_VALOR, ANYO, VERSION_N");

			final Query query = getEntityManager().createNativeQuery(sql.toString());
			query.setParameter(VALUE_DECLARATION_NUMBER, input.getValueDeclarationNumber());
			query.setParameter(VALUE_DECLARATION_YEAR, input.getValueDeclarationYear());
			query.setParameter(VALUE_DECLARATION_VERSION, input.getValueDeclarationVersion());

			@SuppressWarnings("unchecked")
			List<Object[]> listado = query.getResultList();

			if (listado != null && !listado.isEmpty()) {
				for (Object[] tmp : listado) {
					OutputDeclaracionesDeValorDocLinDTO outputDVDocLinDTO = new OutputDeclaracionesDeValorDocLinDTO();

					outputDVDocLinDTO.setCodigoDeclaracion(String.valueOf(tmp[0]));
					outputDVDocLinDTO.setAnyoDeclaracion(String.valueOf(tmp[1]));
					outputDVDocLinDTO.setVersionDeclaracion(String.valueOf(tmp[2]));
					outputDVDocLinDTO.setCodigoProducto(emptyStringOrValue(tmp[3]));
					outputDVDocLinDTO.setTipoItem(emptyStringOrValue(tmp[4]));
					outputDVDocLinDTO.setNombreProducto(emptyStringOrValue(tmp[5]));
					outputDVDocLinDTO.setMarca(emptyStringOrValue(tmp[6]));
					outputDVDocLinDTO.setCodigoTaric(emptyStringOrValue(tmp[7]));
					outputDVDocLinDTO.setTipoLinea(String.valueOf(tmp[8]));
					outputDVDocLinDTO.setCodigoRea(emptyStringOrValue(tmp[9]));
					outputDVDocLinDTO.setPaisOrigen(emptyStringOrValue(tmp[10]));
					outputDVDocLinDTO.setLpc(emptyStringOrValue(tmp[11]));
					outputDVDocLinDTO.setNumeroBultos(formatNumber(tipoDocumento,String.valueOf(tmp[12])));
					outputDVDocLinDTO.setTipoBultos(emptyStringOrValue(tmp[13]));
					outputDVDocLinDTO.setPesoBruto(formatNumber(tipoDocumento,String.valueOf(tmp[14])));
					outputDVDocLinDTO.setPesoNeto(formatNumber(tipoDocumento,String.valueOf(tmp[15])));
					outputDVDocLinDTO.setCantidad(formatNumber(tipoDocumento,String.valueOf(tmp[16])));
					outputDVDocLinDTO.setVolumen(emptyStringOrFormatNumber(tipoDocumento,tmp[17]));
					outputDVDocLinDTO.setAlcohol(emptyStringOrFormatNumber(tipoDocumento,tmp[18]));
					outputDVDocLinDTO.setPlato(emptyStringOrFormatNumber(tipoDocumento,tmp[19]));
					outputDVDocLinDTO.setPrecio(emptyStringOrFormatNumber(tipoDocumento,tmp[20]));
					outputDVDocLinDTO.setImporte(formatNumber(tipoDocumento,String.valueOf(tmp[21])));

					lineas.add(outputDVDocLinDTO);
				}
			}
		} catch(Exception ex) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG,LOG_FILE, "getDatosLineas", ex.getClass().getSimpleName(), ex.getMessage());
			throw new ApplicationException(ex.getMessage());
		}
		return lineas;
	}

	private void getDatosPuertos(String puertoDesembarque, OutputDeclaracionesDeValorDocCabDTO outDVDocumentoDTO) {
		try {
			final StringBuilder sql = new StringBuilder();

			sql.append("SELECT ");
			sql.append("SUBSTR(TXT_DIRECCION,0,INSTR(TXT_DIRECCION,CHR(10))) AS DIRECCION, ");
			sql.append("SUBSTR(SUBSTR(TXT_DIRECCION,INSTR(TXT_DIRECCION,CHR(10))+1,INSTR(TXT_DIRECCION,CHR(32),INSTR(TXT_DIRECCION,CHR(10))+1,1)),0,INSTR(SUBSTR(TXT_DIRECCION,INSTR(TXT_DIRECCION,CHR(10))+1,INSTR(TXT_DIRECCION,CHR(32),INSTR(TXT_DIRECCION,CHR(10))+1,1)),CHR(32))-1) AS CODIGO_POSTAL, ");
			sql.append("SUBSTR(TXT_DIRECCION,INSTR(TXT_DIRECCION,CHR(32),INSTR(TXT_DIRECCION,CHR(10))+1,1)+1,LENGTH(SUBSTR(TXT_DIRECCION,INSTR(TXT_DIRECCION,CHR(32),INSTR(TXT_DIRECCION,CHR(10))+1,1)+1,INSTR(TXT_DIRECCION,CHR(10),1,2)))-(LENGTH(TXT_DIRECCION)-INSTR(TXT_DIRECCION,CHR(10),1,2))) AS POBLACION, ");
			sql.append("SUBSTR(TXT_DIRECCION,INSTR(TXT_DIRECCION,CHR(10),1,2)+1,LENGTH(TXT_DIRECCION)-INSTR(TXT_DIRECCION,CHR(10),1,2))AS PROVINCIA ");
			sql.append("FROM D_PUERTO ");
			sql.append("WHERE COD_N_PUERTO = ?puertoDesembarque ");

			final Query query = getEntityManager().createNativeQuery(sql.toString());
			query.setParameter("puertoDesembarque", puertoDesembarque);

			@SuppressWarnings("unchecked")
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

	private String replaceSpecialChars (String value) {
		if (value != null ) {
			value = value.replace("\"", "");
			value = value.replace("|", "");
			value = value.replace(";", "");
		}
		return value;
	}

	private String replaceReturn (String value) {
		if (value != null ) {
			value = value.replace("\n", " ");
			value = value.replace("\r", " ");
		}
		return value;
	}

	private String emptyStringOrFormatNumber (String tipoDocumento,Object value) {
		String result = "";
		if (value != null) {
			result = formatNumber(tipoDocumento,String.valueOf(value));
		} else {
			result = "";
		}
		return result;
	}

	private String emptyStringOrValue (Object value) {
		String result = "";
		if (value != null) {
			result = String.valueOf(String.valueOf(value));
		} else {
			result = "";
		}
		return result;
	}
	
	public boolean esCorrecta(InputValueDeclarationDocumentDTO input) {
		try {
			boolean esCorrecta = false;
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT MCA_DV_CORRECTA " );
			sql.append("FROM O_DECLARACION_VALOR_CAB ");
			sql.append("WHERE COD_N_DECLARACION_VALOR = ?numDV AND NUM_ANYO = ?anyoDV AND COD_N_VERSION = ?versionDV");

			final Query query = getEntityManager().createNativeQuery(sql.toString());
			query.setParameter("numDV", input.getValueDeclarationNumber());
			query.setParameter("anyoDV", input.getValueDeclarationYear());
			query.setParameter("versionDV", input.getValueDeclarationVersion());
			
			String restultadoQuery = query.getSingleResult().toString();
			
			if ("S".equals(restultadoQuery)) {
				esCorrecta = true;
			}
			
			return esCorrecta;
		} catch (Exception e) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, LOG_FILE, "esCorrecta", e.getClass().getSimpleName(), e.getMessage());
			throw new ApplicationException(e.getMessage());
		}
	}

}
