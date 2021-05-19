package es.mercadona.gesaduan.dao.declaracionesdevalorapi.getdeclarationvaluedetail.v1.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.Query;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.gesaduan.common.Constantes;
import es.mercadona.gesaduan.dao.BaseDAO;
import es.mercadona.gesaduan.dao.declaracionesdevalorapi.getdeclarationvaluedetail.v1.GetValueDeclarationDetailDAO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvaluedeclarationdetail.v1.InputValueDeclarationDetailDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvaluedeclarationdetail.v1.restfull.DataDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvaluedeclarationdetail.v1.restfull.InternalOrderListDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvaluedeclarationdetail.v1.restfull.OutputValueDeclarationDetailDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvaluedeclarationdetail.v1.restfull.SourceDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvaluedeclarationdetail.v1.restfull.TargetDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvaluedeclarationdetail.v1.restfull.ValueDeclarationCommonDataDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvaluedeclarationdetail.v1.restfull.ValueDeclarationHeaderDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvaluedeclarationdetail.v1.restfull.ValueDeclarationHistoricalDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvaluedeclarationdetail.v1.restfull.ValueDeclarationLineDTO;
import es.mercadona.gesaduan.jpa.declaracionesdevalor.DeclaracionesDeValorJPA;

@SuppressWarnings("unchecked")
public class GetValueDeclarationDetailDAOImpl extends BaseDAO<DeclaracionesDeValorJPA> implements GetValueDeclarationDetailDAO {

	@Inject
	private org.slf4j.Logger logger;

	@Override
	public void setEntityClass() {
		this.entityClass = DeclaracionesDeValorJPA.class;
	}
	
	private static final String LOG_FILE = "getValueDeclarationDetail(GESADUAN)";

	@Override
	public OutputValueDeclarationDetailDTO getValueDeclarationDetail(InputValueDeclarationDetailDTO input) {
		OutputValueDeclarationDetailDTO result = new OutputValueDeclarationDetailDTO();

		try {
			final StringBuilder sql = new StringBuilder();
			Long valueDeclarationCode = Long.parseLong(input.getValueDeclarationCode().split("-")[0]);
			Integer valueDeclarationYear = Integer.parseInt(input.getValueDeclarationCode().split("-")[1]);
			Integer valueDeclarationVersion = Integer.parseInt(input.getValueDeclarationCode().split("-")[2]);
			String locale = input.getLocaleId();

			sql.append("SELECT DVC.MCA_ULTIMA_VIGENTE, DVC.MCA_DV_CORRECTA, DVC.MCA_ENVIO, TO_CHAR(DVC.FEC_DT_DESCARGA, 'DD/MM/YYYY HH24:MI:SS'), ");
			sql.append("TO_CHAR(DVC.FEC_DT_DESCARGA_EXPORTADOR, 'DD/MM/YYYY HH24:MI:SS'), TO_CHAR(DVC.FEC_DT_DESCARGA_IMPORTADOR, 'DD/MM/YYYY HH24:MI:SS'), DVC.MCA_CARGA_AUTOMATICA, ");
			sql.append("DVC.COD_N_DECLARACION_VALOR, DVC.NUM_ANYO, DVC.COD_N_VERSION, DVC.NUM_DOSIER, DVC.NUM_ANYO_DOSIER, ");
			sql.append("NVL((SELECT SUM(NVL(NUM_IMPORTE_TOTAL, 0.0)) ");
			sql.append("FROM O_DECLARACION_VALOR_LIN LIN ");
			sql.append("WHERE LIN.COD_N_DECLARACION_VALOR = DVC.COD_N_DECLARACION_VALOR AND LIN.COD_N_VERSION = DVC.COD_N_VERSION AND LIN.NUM_ANYO = DVC.NUM_ANYO), 0.0) AS TOTALDV, ");
			sql.append("TO_CHAR(DVC.FEC_DT_CREACION, 'DD/MM/YYYY HH24:MI:SS'), DVC.COD_V_EXPEDICION, DVC.TXT_CONDICIONES_ENTREGA, ");
			sql.append("TO_CHAR(DVC.FEC_D_ALBARAN, 'DD/MM/YYYY HH24:MI:SS'), TO_CHAR(DVC.FEC_D_ENVIO, 'DD/MM/YYYY HH24:MI:SS'), ");
			sql.append("DECODE(DVC.COD_N_PROVEEDOR, NULL, TO_CHAR(DVC.COD_N_BLOQUE_LOGISTICO), TO_CHAR(DVC.COD_N_PROVEEDOR)) COD_ORIGEN, ");
			sql.append("DECODE(DVC.COD_N_PROVEEDOR, NULL, BL.TXT_NOMBRE, P.TXT_RAZON_SOCIAL) NOMBRE_ORIGEN, ");
			sql.append("DVC.COD_N_PROVINCIA_CARGA COD_PROVINCIA_ORIGEN, ");
			sql.append("DECODE(DVC.COD_N_PROVEEDOR, NULL, 'BLOQUE','PROVEEDOR') TIPO_ORIGEN, ");
			sql.append("DECODE(DVC.COD_N_PROVEEDOR, NULL, DVC.COD_V_ALMACEN, DVC.COD_V_ALMACEN) COD_DESTINO, ");
			sql.append("DECODE(DVC.COD_N_PROVEEDOR, NULL, PU.TXT_NOMBRE_PUERTO, CENTRO.TXT_NOMBRE_LARGO) NOMBRE_DESTINO, ");
			sql.append("DECODE(DVC.COD_N_PROVEEDOR, NULL, 'PUERTO', 'ALMACEN') TIPO_DESTINO ");
			sql.append("FROM O_DECLARACION_VALOR_CAB DVC ");
			sql.append("LEFT JOIN D_PROVEEDOR_R P ON P.COD_N_PROVEEDOR = DVC.COD_N_PROVEEDOR ");
			sql.append("LEFT JOIN D_CENTRO_R CENTRO ON CENTRO.COD_V_CENTRO = DVC.COD_V_ALMACEN ");
			sql.append("LEFT JOIN D_BLOQUE_LOGISTICO_R BL ON BL.COD_N_BLOQUE_LOGISTICO = DVC.COD_N_BLOQUE_LOGISTICO ");
			sql.append("LEFT JOIN D_PUERTO PU ON PU.COD_N_PUERTO = DVC.COD_N_PUERTO_DESEMBARQUE ");
			sql.append("WHERE DVC.COD_N_DECLARACION_VALOR = ?valueDeclarationCode ");
			sql.append("AND DVC.NUM_ANYO = ?valueDeclarationYear AND DVC.COD_N_VERSION = ?valueDeclarationVersion");

			final Query query = getEntityManager().createNativeQuery(sql.toString());
			query.setParameter("valueDeclarationCode", valueDeclarationCode);
			query.setParameter("valueDeclarationYear", valueDeclarationYear);
			query.setParameter("valueDeclarationVersion", valueDeclarationVersion);

			List<Object[]> listado = query.getResultList();

			DataDTO data = new DataDTO();
			ValueDeclarationCommonDataDTO commonData = new ValueDeclarationCommonDataDTO();
			ValueDeclarationHeaderDTO header = new ValueDeclarationHeaderDTO();

			if (listado != null && !listado.isEmpty()) {
				for (Object[] tmp : listado) {
					// COMMON DATA
					if ("S".equals(tmp[0])) {
						commonData.setLastCurrent(true);
					} else {
						commonData.setLastCurrent(false);
					}
					if ("S".equals(tmp[1])) {
						commonData.setValueDeclarationOk(true);
					} else {
						commonData.setValueDeclarationOk(false);
					}
					if ("S".equals(tmp[2])) {
						commonData.setNotified(true);
					} else {
						commonData.setNotified(false);
					}
					if (tmp[3] != null) {
						commonData.setValueDeclarationDownloadDate(String.valueOf(tmp[3]));
					}
					if (tmp[4] != null) {
						commonData.setExportDownloadDate(String.valueOf(tmp[4]));
					}
					if (tmp[5] != null) {
						commonData.setImportDownloadDate(String.valueOf(tmp[5]));
					}
					if ("S".equals(tmp[6])) {
						commonData.setManualLoading(false);
					} else {
						commonData.setManualLoading(true);
					}

					// HEADER
					header.setValueDeclarationNumber(String.valueOf(tmp[7]));
					header.setValueDeclarationYear(String.valueOf(tmp[8]));
					header.setValueDeclarationVersion(String.valueOf(tmp[9]));
					if (tmp[10] != null) {
						header.setDossierNumber(String.valueOf(tmp[10]));
					}
					if (tmp[11] != null) {
						header.setDossierYear(String.valueOf(tmp[11]));
					}
					if (tmp[12] != null) {
						header.setTotalAmount(Double.parseDouble(String.valueOf(tmp[12])));
					}
					header.setTotalAmountCurrency("EUR");
					if (tmp[13] != null) {
						header.setValueDeclarationDate(String.valueOf(tmp[13]));
					}
					if (tmp[14] != null) {
						header.setDispatchId(String.valueOf(tmp[14]));
					}
					if (tmp[15] != null) {
						header.setIncoterm(String.valueOf(tmp[15]));
					}
					if (tmp[16] != null) {
						header.setDeliveryNoteDate(String.valueOf(tmp[16]));
					}
					if (tmp[17] != null) {
						header.setDispatchDate(String.valueOf(tmp[17]));
					}

					// ORIGEN
					SourceDTO source = new SourceDTO();
					if (tmp[18] != null) {
						source.setId(String.valueOf(tmp[18]));
					}
					if (tmp[19] != null) {
						source.setName(String.valueOf(tmp[19]));
					}
					if (tmp[20] != null) {
						source.setRegionId(String.valueOf(tmp[20]));
					}
					if (tmp[21] != null) {
						source.setTypeId(String.valueOf(tmp[21]));
					}
					header.setSource(source);

					// DESTINO
					TargetDTO target = new TargetDTO();
					if (tmp[22] != null) {
						target.setId(String.valueOf(tmp[22]));
					}
					if (tmp[23] != null) {
						target.setName(String.valueOf(tmp[23]));
					}
					if (tmp[24] != null) {
						target.setTypeId(String.valueOf(tmp[24]));
					}
					header.setTarget(target);
					
					data.setValueDeclarationCommonData(commonData);
					// Pedidos
					header.setInternalOrderList(getPedidos(valueDeclarationCode, valueDeclarationYear, valueDeclarationVersion, header.getDispatchId()));
					data.setValueDeclarationHeader(header);
					// Lineas
					data.setValueDeclarationLine(getLines(valueDeclarationCode, valueDeclarationYear, valueDeclarationVersion, locale, source.getId()));
					data.setValueDeclarationHistorical(getHistorico(valueDeclarationCode, valueDeclarationYear));
				}
			}
			result.setData(data);
		} catch (Exception e) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, LOG_FILE, "getValueDeclarationDetail", e.getClass().getSimpleName(), e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}

		return result;
	}

	private List<InternalOrderListDTO> getPedidos(Long valueDeclarationCode, Integer valueDeclarationYear, Integer valueDeclarationVersion, String expedicion) {
		StringBuilder sqlPedido = new StringBuilder();
		
		if (expedicion != null) {
			sqlPedido.append("SELECT COD_V_PEDIDO ");			
			sqlPedido.append("FROM O_DECLARACION_VALOR_CAB ");
			sqlPedido.append("WHERE COD_N_DECLARACION_VALOR = ?valueDeclarationNumber ");
			sqlPedido.append("AND NUM_ANYO = ?valueDeclarationYear ");
			sqlPedido.append("AND COD_N_VERSION = ?valueDeclarationVersion");			
		} else {
			sqlPedido.append("SELECT COD_V_PEDIDO ");			
			sqlPedido.append("FROM S_DECLARACION_VALOR_PEDIDO ");
			sqlPedido.append("WHERE COD_N_DECLARACION_VALOR = ?valueDeclarationNumber ");
			sqlPedido.append("AND NUM_ANYO_DV = ?valueDeclarationYear ");
			sqlPedido.append("AND COD_N_VERSION_DV = ?valueDeclarationVersion");						
		}

		final Query queryPedido = getEntityManager().createNativeQuery(sqlPedido.toString());
		queryPedido.setParameter("valueDeclarationNumber", valueDeclarationCode);
		queryPedido.setParameter("valueDeclarationYear", valueDeclarationYear);
		queryPedido.setParameter("valueDeclarationVersion", valueDeclarationVersion);

		List<String> listadoPedido = queryPedido.getResultList();
		List<InternalOrderListDTO> pedidos = null;

		if (listadoPedido != null && !listadoPedido.isEmpty()) {
			pedidos = new ArrayList<>();
			for (String pedido : listadoPedido) {
				InternalOrderListDTO internalOrderList = new InternalOrderListDTO();
				if (pedido != null) {
					internalOrderList.setInternalOrderId(pedido);
				}
				pedidos.add(internalOrderList);
			}
		}

		return pedidos;
	}

	private List<ValueDeclarationLineDTO> getLines(Long valueDeclarationCode, Integer valueDeclarationYear, Integer valueDeclarationVersion, String locale, String sourceId) {
		StringBuilder sqlLineas = new StringBuilder();
		sqlLineas.append("SELECT DVL.COD_N_MERCA, DVL.COD_N_TARIC, DVL.COD_V_REA, EAN.COD_V_EAN13, ");
		sqlLineas.append("DECODE(PROD.TXT_DENOMINA_ALTERNATIVA, NULL, DEN.TXT_DESCRIPCION, PROD.TXT_DENOMINA_ALTERNATIVA), ");
		sqlLineas.append("DECODE(PROD.TXT_DENOMINA_ALTERNATIVA, NULL, DEN.TXT_DESCRIPCION, PROD.TXT_DENOMINA_ALTERNATIVA) AS PRODUCTO, ");
		sqlLineas.append("DECODE(PROD.TXT_FORMATO_ALTERNATIVO, NULL, CONCAT(CONCAT(PROD.NUM_FORMATO_VENTA, ' '), PROD.TXT_UNIDAD_MEDIDA), PROD.TXT_FORMATO_ALTERNATIVO), ");
		sqlLineas.append("DECODE(PROD.TXT_FORMATO_ALTERNATIVO, NULL, CONCAT(CONCAT(PROD.NUM_FORMATO_VENTA, ' '), PROD.TXT_UNIDAD_MEDIDA), PROD.TXT_FORMATO_ALTERNATIVO) AS FORMATO, ");
		sqlLineas.append("PROD.TXT_MARCA, NULL, DVL.TXT_NOMBRE_BULTO_DV, DVL.NUM_BULTOS, DVL.NUM_PESO_NETO, DVL.NUM_PESO_BRUTO, ");
		sqlLineas.append("DVL.NUM_VOLUMEN, DVL.NUM_CANTIDAD, DVL.NUM_PRECIO_UNIDAD, DVL.NUM_IMPORTE_TOTAL, ");
		sqlLineas.append("DVL.NUM_GRADO_ALCOHOL, DVL.NUM_GRADO_PLATO, DVL.COD_V_PAIS, DVL.MCA_PRODUCTO_LPC, DVL.MCA_ERROR ");
		sqlLineas.append("FROM O_DECLARACION_VALOR_LIN DVL ");
		sqlLineas.append("LEFT JOIN D_PRODUCTO_R PROD ON DVL.COD_N_MERCA = PROD.COD_N_PRODUCTO ");
		sqlLineas.append("LEFT JOIN S_DENOMINACION_PRODUCTO_I18N_R DEN ON DVL.COD_N_MERCA = DEN.COD_N_PRODUCTO AND DEN.COD_V_LOCALE = ?locale ");
		sqlLineas.append("LEFT JOIN S_EAN_PRODUCTO_PROVEEDOR_R EAN ON EAN.COD_N_PRODUCTO = DVL.COD_N_MERCA AND EAN.COD_N_LEGACY_PROVEEDOR = ?sourceId ");
		sqlLineas.append("WHERE DVL.COD_N_DECLARACION_VALOR = ?valueDeclarationNumber AND DVL.NUM_ANYO = ?valueDeclarationYear ");
		sqlLineas.append("AND DVL.COD_N_VERSION = ?valueDeclarationVersion");

		final Query queryLineas = getEntityManager().createNativeQuery(sqlLineas.toString());
		queryLineas.setParameter("valueDeclarationNumber", valueDeclarationCode);
		queryLineas.setParameter("valueDeclarationYear", valueDeclarationYear);
		queryLineas.setParameter("valueDeclarationVersion", valueDeclarationVersion);
		queryLineas.setParameter("locale", locale);
		queryLineas.setParameter("sourceId", sourceId);

		List<Object[]> listadoLineas = queryLineas.getResultList();
		List<ValueDeclarationLineDTO> lineas = null;

		if (listadoLineas != null && !listadoLineas.isEmpty()) {
			lineas = new ArrayList<>();
			for (Object[] tmp : listadoLineas) {
				ValueDeclarationLineDTO linea = new ValueDeclarationLineDTO();
				if (tmp[0] != null) {
					linea.setProductPublicId(Integer.parseInt(String.valueOf(tmp[0])));
				}
				if (tmp[1] != null) {
					linea.setTaricId(Long.parseLong(String.valueOf(tmp[1])));
				}
				if (tmp[2] != null) {
					linea.setRea(String.valueOf(tmp[2]));
				}
				if (tmp[3] != null) {
					linea.setGtin13(String.valueOf(tmp[3]));
				}
				if (tmp[4] != null) {
					linea.setProductName(String.valueOf(tmp[4]));
				}
				if (tmp[5] != null) {
					linea.setProductAlternativeName(String.valueOf(tmp[5]));
				}
				if (tmp[6] != null) {
					linea.setStandardSalesFormat(String.valueOf(tmp[6]));
				}
				if (tmp[7] != null) {
					linea.setAlternativeSalesFormatDescription(String.valueOf(tmp[7]));
				}
				if (tmp[8] != null) {
					linea.setBrandName(String.valueOf(tmp[8]));
				}
				if (tmp[9] != null) {
					linea.setPackageTypeId(String.valueOf(tmp[9]));
				}
				if (tmp[10] != null) {
					linea.setPackageName(String.valueOf(tmp[10]));
				}
				if (tmp[11] != null) {
					linea.setPackageQuantity(Integer.parseInt(String.valueOf(tmp[11])));
				}
				if (tmp[12] != null) {
					linea.setLineNetWeight(Double.parseDouble(String.valueOf(tmp[12])));
				}
				if (tmp[12] != null) {
					linea.setLineNetWeightUnit("KG");
				}
				if (tmp[13] != null) {
					linea.setLineGrossWeight(Double.parseDouble(String.valueOf(tmp[13])));
				}
				if (tmp[13] != null) {
					linea.setLineGrossWeightUnit("KG");
				}
				if (tmp[14] != null) {
					linea.setVolume(Double.parseDouble(String.valueOf(tmp[14])));
				}
				if (tmp[14] != null) {
					linea.setVolumeUnit("L");
				}
				if (tmp[15] != null) {
					linea.setFormatQuantity(Double.parseDouble(String.valueOf(tmp[15])));
				}
				if (tmp[16] != null) {
					linea.setUnitPrice(Double.parseDouble(String.valueOf(tmp[16])));
				}
				if (tmp[16] != null) {
					linea.setUnitPriceCurrency("EUR");
				}
				if (tmp[17] != null) {
					linea.setTotalLineAmount(Double.parseDouble(String.valueOf(tmp[17])));
				}
				if (tmp[17] != null) {
					linea.setTotalLineAmountCurrency("EUR");
				}
				if (tmp[18] != null) {
					linea.setAlcoholPercentage(Double.parseDouble(String.valueOf(tmp[18])));
				}
				if (tmp[18] != null) {
					linea.setAlcoholPercentageUnit(null);
				}
				if (tmp[19] != null) {
					linea.setPlateGrade(Double.parseDouble(String.valueOf(tmp[19])));
				}
				if (tmp[19] != null) {
					linea.setPlateGradeUnit(null);
				}
				if (tmp[20] != null) {
					linea.setSourceCountryId(String.valueOf(tmp[20]));
				}
				if (tmp[21] != null) {
					if ("S".equals(tmp[21])) {
						linea.setIsLpc(true);
					} else {
						linea.setIsLpc(false);
					}
				}
				if (tmp[22] != null) {
					linea.setHasError(String.valueOf(tmp[22]));
				}
				if (linea.getTaricId() == null) {
					StringBuilder sqlTaric = new StringBuilder();
					sqlTaric.append("SELECT TP.COD_N_TARIC FROM S_TARIC_PRODUCTO TP WHERE TP.COD_N_PRODUCTO = ?productPublicId");

					final Query queryTaric = getEntityManager().createNativeQuery(sqlTaric.toString());
					queryTaric.setParameter("productPublicId", linea.getProductPublicId());
					List<BigDecimal> tarics = queryTaric.getResultList();
					if (tarics != null && !tarics.isEmpty()) {
						for (BigDecimal taric : tarics) {
							linea.setActualTaricId(Long.parseLong(String.valueOf(taric)));
						}
					}
				}
				lineas.add(linea);
			}
		}

		return lineas;
	}

	@Override
	public List<ValueDeclarationHistoricalDTO> getHistorico(Long valueDeclarationNumber, Integer valueDeclarationYear) {
		List<ValueDeclarationHistoricalDTO> historicos = new ArrayList<>();

		try {
			final StringBuilder sql = new StringBuilder();

			sql.append("SELECT EVENTO, TO_CHAR(FECHA, 'DD/MM/YYYY') AS FECHA, VERSION, VIGENTE, CORRECTA ");
			sql.append("FROM (");
			sql.append("SELECT EVENTO, FECHA, VERSION, VIGENTE, CORRECTA, ROW_NUMBER() OVER (PARTITION BY EVENTO, FECHA ORDER BY VERSION) PRIMERA ");
			sql.append("FROM (");
			sql.append("SELECT DISTINCT 'Generación Factura - Versión ' || DV.COD_N_VERSION AS EVENTO, DV.FEC_DT_CREACION AS FECHA, ");
			sql.append("DV.COD_N_VERSION AS VERSION, DV.MCA_ULTIMA_VIGENTE AS VIGENTE, DV.MCA_DV_CORRECTA AS CORRECTA ");
			sql.append("FROM O_DECLARACION_VALOR_CAB DV ");
			sql.append("WHERE DV.COD_N_DECLARACION_VALOR = ?valueDeclarationNumber ");
			sql.append("AND DV.NUM_ANYO = ?valueDeclarationYear ");
			sql.append("UNION ");
			sql.append("SELECT DISTINCT 'Realización pedido', PED.FEC_D_PEDIDO, DV.COD_N_VERSION, NULL, DV.MCA_DV_CORRECTA AS CORRECTA ");
			sql.append("FROM O_PEDIDO_CAB_R PED ");
			sql.append("INNER JOIN O_EXPEDICION_CAB EXP ON (EXP.COD_V_PEDIDO = PED.COD_V_PEDIDO) ");
			sql.append("INNER JOIN O_DECLARACION_VALOR_CAB DV ON (DV.COD_V_EXPEDICION = EXP.COD_V_EXPEDICION ");
			sql.append("AND DV.FEC_D_ALBARAN = EXP.FEC_D_ALBARAN) ");
			sql.append("WHERE DV.COD_N_DECLARACION_VALOR = ?valueDeclarationNumber ");
			sql.append("AND DV.NUM_ANYO = ?valueDeclarationYear ");
			sql.append("UNION ");
			sql.append("SELECT DISTINCT 'Expedición pedido', DECODE(DV.COD_V_EXPEDICION, NULL, DV.FEC_DT_EXPEDICION, DV.FEC_D_ALBARAN), ");
			sql.append("DV.COD_N_VERSION, NULL, DV.MCA_DV_CORRECTA AS CORRECTA ");
			sql.append("FROM O_DECLARACION_VALOR_CAB DV ");
			sql.append("WHERE DV.COD_N_DECLARACION_VALOR = ?valueDeclarationNumber ");
			sql.append("AND DV.NUM_ANYO = ?valueDeclarationYear ");
			sql.append("UNION ");
			sql.append("SELECT DISTINCT 'Envío notificación', NOTIF.FEC_DT_ENVIO, DV.COD_N_VERSION, ");
			sql.append("NULL, DV.MCA_DV_CORRECTA AS CORRECTA ");
			sql.append("FROM S_NOTIF_ALERTA_EXPED_DV NOTIF ");
			sql.append("INNER JOIN O_DECLARACION_VALOR_CAB DV ON (");
			sql.append("DV.COD_N_DECLARACION_VALOR = NOTIF.COD_N_DECLARACION_VALOR ");
			sql.append("AND DV.NUM_ANYO = NOTIF.NUM_ANYO ");
			sql.append("AND DV.COD_N_VERSION = NOTIF.COD_N_VERSION) ");
			sql.append("WHERE DV.COD_N_DECLARACION_VALOR = ?valueDeclarationNumber ");
			sql.append("AND DV.NUM_ANYO = ?valueDeclarationYear ");
			sql.append("AND NOTIF.FEC_DT_ENVIO IS NOT NULL ");
			sql.append("UNION ");
			sql.append("SELECT DISTINCT 'Descarga Factura por el agente de aduanas', DV.FEC_DT_DESCARGA, ");
			sql.append("DV.COD_N_VERSION, NULL, DV.MCA_DV_CORRECTA AS CORRECTA ");
			sql.append("FROM O_DECLARACION_VALOR_CAB DV ");
			sql.append("WHERE DV.COD_N_DECLARACION_VALOR = ?valueDeclarationNumber ");
			sql.append("AND DV.NUM_ANYO = ?valueDeclarationYear ");
			sql.append("AND DV.MCA_DESCARGA = 'S' ");
			sql.append("AND DV.FEC_DT_DESCARGA IS NOT NULL ");
			sql.append("UNION ");
			sql.append("SELECT DISTINCT 'Generación dosier', DO.FEC_DT_CREACION, DV.COD_N_VERSION, ");
			sql.append("NULL, DV.MCA_DV_CORRECTA AS CORRECTA ");
			sql.append("FROM D_DOSIER DO ");
			sql.append("INNER JOIN O_DECLARACION_VALOR_CAB DV ON (");
			sql.append("DV.NUM_DOSIER = DO.NUM_DOSIER ");
			sql.append("AND DV.NUM_ANYO_DOSIER = DO.NUM_ANYO) ");
			sql.append("WHERE DV.COD_N_DECLARACION_VALOR = ?valueDeclarationNumber ");
			sql.append("AND DV.NUM_ANYO = ?valueDeclarationYear ");
			sql.append("AND DV.MCA_DESCARGA = 'S' ");
			sql.append("AND DV.FEC_DT_DESCARGA IS NOT NULL ");
			sql.append("UNION ");
			sql.append("SELECT DISTINCT 'Envío notificación dosier', NOTIF.FEC_DT_ENVIO, DV.COD_N_VERSION, ");
			sql.append("NULL, DV.MCA_DV_CORRECTA AS CORRECTA ");
			sql.append("FROM S_NOTIFICACION_ALERTA NOTIF ");
			sql.append("INNER JOIN O_DECLARACION_VALOR_CAB DV ON (NOTIF.COD_V_ELEMENTO = SUBSTR(DV.NUM_ANYO_DOSIER, 3, 2)||'-'||LPAD(DV.NUM_DOSIER, 5, '0')) ");
			sql.append("WHERE DV.COD_N_DECLARACION_VALOR = ?valueDeclarationNumber ");
			sql.append("AND DV.NUM_ANYO = ?valueDeclarationYear ");
			sql.append("AND NOTIF.FEC_DT_ENVIO IS NOT NULL ");
			sql.append("UNION ");
			sql.append("SELECT DISTINCT 'Descarga factura por el agente de aduanas exportador', DV.FEC_DT_DESCARGA_EXPORTADOR, ");
			sql.append("DV.COD_N_VERSION, NULL, DV.MCA_DV_CORRECTA AS CORRECTA ");
			sql.append("FROM O_DECLARACION_VALOR_CAB DV ");
			sql.append("WHERE DV.COD_N_DECLARACION_VALOR = ?valueDeclarationNumber ");
			sql.append("AND DV.NUM_ANYO = ?valueDeclarationYear ");
			sql.append("AND DV.FEC_DT_DESCARGA_EXPORTADOR IS NOT NULL ");
			sql.append("UNION ");
			sql.append("SELECT DISTINCT 'Descarga factura por el agente de aduanas importador', DV.FEC_DT_DESCARGA_IMPORTADOR, ");
			sql.append("DV.COD_N_VERSION, NULL, DV.MCA_DV_CORRECTA AS CORRECTA ");
			sql.append("FROM O_DECLARACION_VALOR_CAB DV ");
			sql.append("WHERE DV.COD_N_DECLARACION_VALOR = ?valueDeclarationNumber ");
			sql.append("AND DV.NUM_ANYO = ?valueDeclarationYear ");
			sql.append("AND DV.FEC_DT_DESCARGA_IMPORTADOR IS NOT NULL ");
			sql.append(")");
			sql.append("ORDER BY VERSION, FECHA ASC ");
			sql.append(")");
			sql.append("WHERE PRIMERA = 1");

			final Query query = getEntityManager().createNativeQuery(sql.toString());

			query.setParameter("valueDeclarationNumber", valueDeclarationNumber);
			query.setParameter("valueDeclarationYear", valueDeclarationYear);

			List<Object[]> listado = query.getResultList();

			if (listado != null && !listado.isEmpty()) {
				for (Object[] tmp : listado) {
					ValueDeclarationHistoricalDTO historico = new ValueDeclarationHistoricalDTO();

					if (tmp[0] != null) {
						historico.setEventId(String.valueOf(tmp[0]));
					}
					if (tmp[1] != null) {
						historico.setEventCreateDate(String.valueOf(tmp[1]));
					}
					if (tmp[2] != null) {
						historico.setValueDeclarationVersion(Integer.parseInt(String.valueOf(tmp[2])));
					}
					if (tmp[3] != null) {
						if ("S".equals(tmp[3])) {
							historico.setIsCurrent(true);
						} else {
							historico.setIsCurrent(false);
						}
					}
					if (tmp[4] != null) {
						if ("S".equals(tmp[4])) {
							historico.setIsValueDeclarationOk(true);
						} else {
							historico.setIsValueDeclarationOk(false);
						}
					}

					historicos.add(historico);
				}
			}
		} catch (Exception e) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, LOG_FILE, "getHistorico", e.getClass().getSimpleName(), e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}

		return historicos;
	}

}