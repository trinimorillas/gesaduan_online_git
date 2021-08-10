package es.mercadona.gesaduan.dao.declaracionesdevalorapi.getvdsumary.v2.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.Query;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.gesaduan.common.Constantes;
import es.mercadona.gesaduan.dao.BaseDAO;
import es.mercadona.gesaduan.dao.declaracionesdevalorapi.getvdsumary.v2.GetVDSumaryDAO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvdsumary.v2.InputVDSumaryDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvdsumary.v2.restful.DataVDDatesDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvdsumary.v2.restful.DataVDPaginationDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvdsumary.v2.restful.DataVDSumaryDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvdsumary.v2.restful.DataVDSumaryOrderDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvdsumary.v2.restful.DataVDSumarySourceDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvdsumary.v2.restful.DataVDeclarationIdsDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvdsumary.v2.restful.DataVDossierIdsDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvdsumary.v2.restful.OutputVDSumaryDTO;
import es.mercadona.gesaduan.jpa.declaracionesdevalor.DeclaracionesDeValorJPA;


public class GetVDSumaryDAOImpl extends BaseDAO<DeclaracionesDeValorJPA> implements GetVDSumaryDAO{
	
	private static final String LOG_FILE = "GetVDSumaryDAOImpl(GESADUAN)"; 		
	
	@Override
	public void setEntityClass() {
		this.entityClass = DeclaracionesDeValorJPA.class;
		
	}
	
	@Inject
	private org.slf4j.Logger logger;		
	
	private static final String NOMBRE_CLASE = "GetVDSumaryDAOImpl(GESADUAN)";	
	
	@SuppressWarnings({ "unchecked"})
	@Override
	public OutputVDSumaryDTO getValueDeclarationList(InputVDSumaryDTO data) {
				
		List<DataVDSumaryDTO> resultList = new ArrayList<>();
		OutputVDSumaryDTO result = new OutputVDSumaryDTO();
		
		final StringBuilder select = new StringBuilder();		
		final StringBuilder query1 = new StringBuilder();
		final StringBuilder query2 = new StringBuilder();
		final StringBuilder query3 = new StringBuilder();	
		final StringBuilder ambasQuery = new StringBuilder();		
		final StringBuilder selectOrder = new StringBuilder();		
		final StringBuilder selectCount = new StringBuilder();		
		// final StringBuilder sql = new StringBuilder();
		
		try {
		
			Integer paginaInicio = null; 		
			if(data.getFirstPage() != null) {
				paginaInicio = data.getFirstPage();
			}
			
			Integer paginaTamanyo = null; 		
			if(data.getSizePage() != null) {
				paginaTamanyo = data.getSizePage();
			}
			
			// Query agrupada 
			select.append("SELECT ");
			select.append("MCA_DV_CORRECTA ");
			select.append(",MCA_CARGA_AUTOMATICA ");
			select.append(",COD_N_DECLARACION_VALOR ");
			select.append(",NUM_ANYO ");
			select.append(",COD_N_VERSION ");
			select.append(",COD_V_TIPO_FACTURA ");
			select.append(",NUM_DOSIER ");
			select.append(",NUM_ANYO_DOSIER ");
			select.append(",TOTALDV ");
			select.append(",FEC_EXPEDICION ");
			select.append(",FEC_DT_CREACION ");
			select.append(",FEC_DT_DESCARGA ");
			select.append(",FEC_DT_DESCARGA_EXPORTADOR ");
			select.append(",FEC_DT_DESCARGA_IMPORTADOR ");
			select.append(",COD_ORIGEN ");
			select.append(",NOMBRE_ORIGEN ");
			select.append(",PROVINCIA_ORIGEN ");
			select.append(",TIPO_ORIGEN ");
			select.append(",COD_V_EXPEDICION ");
			select.append(",COD_N_PROVEEDOR ");	
			select.append(",COD_ORIGEN_ORDER ");				
			select.append("FROM ");			
			select.append("( ");			
			
			query1.append("SELECT ");
			query1.append("DISTINCT MCA_DV_CORRECTA ");
			query1.append(",DV.MCA_CARGA_AUTOMATICA ");
			query1.append(",DV.COD_N_DECLARACION_VALOR ");
			query1.append(",DV.NUM_ANYO ");
			query1.append(",DV.COD_N_VERSION ");
			query1.append(",COD_V_TIPO_FACTURA ");
			query1.append(",DV.NUM_DOSIER ");
			query1.append(",DV.NUM_ANYO_DOSIER ");
			query1.append(",NVL((SELECT SUM(NVL(NUM_IMPORTE_TOTAL, 0.0))FROM O_DECLARACION_VALOR_LIN LIN WHERE LIN.COD_N_DECLARACION_VALOR = DV.COD_N_DECLARACION_VALOR AND LIN.COD_N_VERSION = DV.COD_N_VERSION AND LIN.NUM_ANYO = DV.NUM_ANYO), 0.0) AS TOTALDV ");
			query1.append(",DECODE(DV.FEC_D_ALBARAN,NULL,DV.FEC_DT_EXPEDICION,DV.FEC_D_ALBARAN) FEC_EXPEDICION ");
			query1.append(",DV.FEC_DT_CREACION ");
			query1.append(",DV.FEC_DT_DESCARGA ");
			query1.append(",DV.FEC_DT_DESCARGA_EXPORTADOR ");
			query1.append(",DV.FEC_DT_DESCARGA_IMPORTADOR ");
			query1.append(",P.COD_N_LEGACY_PROVEEDOR COD_ORIGEN ");
			query1.append(",P.TXT_RAZON_SOCIAL NOMBRE_ORIGEN ");
			query1.append(",DV.COD_N_PROVINCIA_CARGA PROVINCIA_ORIGEN ");
			query1.append(",'PROVEEDOR' TIPO_ORIGEN ");
			query1.append(",DV.COD_V_EXPEDICION ");
			query1.append(",DV.COD_N_PROVEEDOR ");	
			query1.append(",P.COD_N_LEGACY_PROVEEDOR COD_ORIGEN_ORDER ");
			query1.append("FROM O_DECLARACION_VALOR_CAB DV ");
			query1.append("LEFT JOIN D_PROVEEDOR_R P ON (P.COD_N_PROVEEDOR = DV.COD_N_PROVEEDOR) ");			
			
			query2.append("SELECT ");
			query2.append("DISTINCT MCA_DV_CORRECTA ");
			query2.append(",DV.MCA_CARGA_AUTOMATICA ");
			query2.append(",DV.COD_N_DECLARACION_VALOR ");
			query2.append(",DV.NUM_ANYO ");
			query2.append(",DV.COD_N_VERSION ");
			query2.append(",COD_V_TIPO_FACTURA ");
			query2.append(",DV.NUM_DOSIER ");
			query2.append(",DV.NUM_ANYO_DOSIER ");
			query2.append(",NVL((SELECT SUM(NVL(NUM_IMPORTE_TOTAL, 0.0))FROM O_DECLARACION_VALOR_LIN LIN WHERE LIN.COD_N_DECLARACION_VALOR = DV.COD_N_DECLARACION_VALOR AND LIN.COD_N_VERSION = DV.COD_N_VERSION AND LIN.NUM_ANYO = DV.NUM_ANYO), 0.0) AS TOTALDV ");
			query2.append(",DECODE(DV.FEC_D_ALBARAN,NULL,DV.FEC_DT_EXPEDICION,DV.FEC_D_ALBARAN) FEC_EXPEDICION ");
			query2.append(",DV.FEC_DT_CREACION ");
			query2.append(",DV.FEC_DT_DESCARGA ");
			query2.append(",DV.FEC_DT_DESCARGA_EXPORTADOR ");
			query2.append(",DV.FEC_DT_DESCARGA_IMPORTADOR ");
			query2.append(",TO_CHAR(DV.COD_N_BLOQUE_LOGISTICO) COD_ORIGEN ");
			query2.append(",BL.TXT_NOMBRE NOMBRE_ORIGEN ");
			query2.append(",DV.COD_N_PROVINCIA_CARGA PROVINCIA_ORIGEN ");
			query2.append(",'BLOQUE' TIPO_ORIGEN ");
			query2.append(",DV.COD_V_EXPEDICION ");
			query2.append(",DV.COD_N_PROVEEDOR ");	
			query2.append(",NULL COD_ORIGEN_ORDER ");	
			query2.append("FROM O_DECLARACION_VALOR_CAB DV ");
			query2.append("LEFT JOIN D_BLOQUE_LOGISTICO_R BL ON (BL.COD_N_BLOQUE_LOGISTICO = DV.COD_N_BLOQUE_LOGISTICO) ");	
			
			query3.append("SELECT ");
			query3.append("DISTINCT MCA_DV_CORRECTA ");
			query3.append(",DV.MCA_CARGA_AUTOMATICA ");
			query3.append(",DV.COD_N_DECLARACION_VALOR ");
			query3.append(",DV.NUM_ANYO ");
			query3.append(",DV.COD_N_VERSION ");
			query3.append(",COD_V_TIPO_FACTURA ");
			query3.append(",DV.NUM_DOSIER ");
			query3.append(",DV.NUM_ANYO_DOSIER ");
			query3.append(",NVL((SELECT SUM(NVL(NUM_IMPORTE_TOTAL, 0.0))FROM O_DECLARACION_VALOR_LIN LIN WHERE LIN.COD_N_DECLARACION_VALOR = DV.COD_N_DECLARACION_VALOR AND LIN.COD_N_VERSION = DV.COD_N_VERSION AND LIN.NUM_ANYO = DV.NUM_ANYO), 0.0) AS TOTALDV ");
			query3.append(",DECODE(DV.FEC_D_ALBARAN,NULL,DV.FEC_DT_EXPEDICION,DV.FEC_D_ALBARAN) FEC_EXPEDICION ");
			query3.append(",DV.FEC_DT_CREACION ");
			query3.append(",DV.FEC_DT_DESCARGA ");
			query3.append(",DV.FEC_DT_DESCARGA_EXPORTADOR ");
			query3.append(",DV.FEC_DT_DESCARGA_IMPORTADOR ");
			query3.append(",TO_CHAR(DV.COD_N_BLOQUE_LOGISTICO) COD_ORIGEN ");
			query3.append(",BL.TXT_NOMBRE NOMBRE_ORIGEN ");
			query3.append(",DV.COD_N_PROVINCIA_CARGA PROVINCIA_ORIGEN ");
			query3.append(",'BLOQUE' TIPO_ORIGEN ");
			query3.append(",DV.COD_V_EXPEDICION ");
			query3.append(",DV.COD_N_PROVEEDOR ");	
			query3.append(",NULL COD_ORIGEN_ORDER ");	
			query3.append("FROM O_DECLARACION_VALOR_CAB DV ");
			query3.append("LEFT JOIN D_BLOQUE_LOGISTICO_R BL ON (BL.COD_N_BLOQUE_LOGISTICO = DV.COD_N_BLOQUE_LOGISTICO) ");
			
			selectCount.append("SELECT COUNT(DISTINCT CONCAT(COD_N_DECLARACION_VALOR,NUM_ANYO)) FROM (");			
			
			// Si el parámetro agencyLegacyId no es nulo 
			if(data.getAgencyId() != null) {
				query1.append("JOIN S_RELACION_PROVEEDOR_R RPP ON (RPP.COD_N_PROVEEDOR = P.COD_N_PROVEEDOR) ");
				query1.append("JOIN D_PROVEEDOR_R AGENCIA ON (AGENCIA.COD_N_PROVEEDOR = RPP.COD_N_AGENCIA_ADUANA AND (RPP.FEC_D_FIN IS NULL OR TRUNC(RPP.FEC_D_FIN) >= TRUNC(SYSDATE))) ");
				query2.append("JOIN D_DOSIER DO ON (DO.NUM_DOSIER = DV.NUM_DOSIER AND DO.NUM_ANYO = DV.NUM_ANYO_DOSIER) ");		
				query3.append("LEFT JOIN D_PUERTO PU ON (PU.COD_V_PROVINCIA = CE.COD_N_PROVINCIA) ");
				query3.append("LEFT JOIN S_PUERTO_AGENCIA PA ON  (PU.COD_N_PUERTO = PA.COD_N_PUERTO) ");				
			}
			
			// Si el parámetro internalOrderId no es nulo 
			if(data.getInternalOrderId() != null) {		
				query2.append("LEFT JOIN S_DECLARACION_VALOR_PEDIDO DVP ON (DVP.NUM_ANYO_DV=DV.NUM_ANYO  AND  DVP.COD_N_DECLARACION_VALOR=DV.COD_N_DECLARACION_VALOR AND DVP.COD_N_VERSION_DV=DV.COD_N_VERSION) ");
			}		
			
			// Si el parámetro targetName no es nulo
			if(data.getTargetName() != null) {
				query1.append("LEFT JOIN D_CENTRO_R C ON (DV.COD_V_ALMACEN = C.COD_V_CENTRO) ");
				query2.append("LEFT JOIN D_PUERTO PU ON (PU.COD_N_PUERTO= DV.COD_N_PUERTO_DESEMBARQUE) ");
				query3.append("LEFT JOIN D_BLOQUE_LOGISTICO_R BL ON (BL.COD_N_BLOQUE_LOGISTICO = DV.COD_N_BLOQUE_LOGISTICO) ");
			}
			
			query1.append(" WHERE 1 = 1");	
			query2.append(" WHERE 1 = 1");
			query3.append(" WHERE 1 = 1");
			
			query1.append(" AND DV.MCA_ULTIMA_VIGENTE = 'S' ");
			query2.append(" AND DV.MCA_ULTIMA_VIGENTE = 'S' ");
			query3.append(" AND DV.MCA_ULTIMA_VIGENTE = 'S' ");
			
			query1.append(" AND DV.COD_V_TIPO_FACTURA = 'CSM' ");
			query2.append(" AND DV.COD_V_TIPO_FACTURA = 'PED' ");
			query3.append(" AND DV.COD_V_TIPO_FACTURA = 'DEV' ");
			
			query1.append(" AND DV.COD_N_PROVEEDOR IS NOT NULL ");
			query2.append(" AND DV.COD_N_PROVEEDOR IS NULL ");
			
			if(data.getAgencyId() != null) {
				query1.append(" AND (AGENCIA.COD_N_LEGACY_PROVEEDOR = ?agencyLegacyId OR AGENCIA.COD_N_PROVEEDOR = ?agencyLegacyId) ");	
				query2.append(" AND (DO.COD_V_AGENCIA_EXPORTACION= ?agencyLegacyId OR DO.COD_V_AGENCIA_IMPORTACION = ?agencyLegacyId) ");
				query3.append(" PA.COD_V_AGENCIA_ADUANA = agencyId ");
			}
			
			if(data.getValueDeclarationNumber() != null) {
				query1.append(" AND DV.COD_N_DECLARACION_VALOR = ?valueDeclarationNumber ");
				query2.append(" AND DV.COD_N_DECLARACION_VALOR = ?valueDeclarationNumber ");				
			}		
			
			if(data.getValueDeclarationYear() != null) {
				query1.append(" AND DV.NUM_ANYO = ?valueDeclarationYear ");
				query2.append(" AND DV.NUM_ANYO = ?valueDeclarationYear ");
			}	
			
			if(data.getDossierNumber() != null) {
				query2.append(" AND DV.NUM_DOSIER  = ?dossierNumber ");
			}		
			
			if(data.getDossierYear() != null) {
				query2.append(" AND DV.NUM_ANYO_DOSIER  = ?dossierYear ");				
			}	
			
			if(data.getInternalOrderId() != null) {
				query1.append(" AND DV.COD_V_PEDIDO = ?internalOrderId  ");
				query2.append(" AND DVP.COD_V_PEDIDO = ?internalOrderId ");				
			}
			
			if(data.getWarehouseId() != null) {
				query1.append(" AND TO_NUMBER(DV.COD_V_ALMACEN) = TO_NUMBER(?warehouseLegacyId) ");
			}
			
			if(data.getTargetName() != null) {
				query1.append(" AND UPPER(C.TXT_NOMBRE_LARGO) LIKE ('%'|| UPPER(?targetName) ||'%') ");
				query2.append(" AND UPPER(PU.TXT_NOMBRE_PUERTO) LIKE ('%'|| UPPER(?targetName) ||'%') ");				
			}				
			
			if(data.getSupplierId() != null) {
				query1.append(" AND (P.COD_N_LEGACY_PROVEEDOR = ?supplierLegacyId OR P.COD_N_PROVEEDOR = ?supplierLegacyId) ");
			}
			
			if(data.getSourceName() != null) {
				query1.append(" AND UPPER(P.TXT_RAZON_SOCIAL) LIKE ('%'|| UPPER(?sourceName) ||'%')  ");
				query2.append(" AND UPPER(BL.TXT_NOMBRE) LIKE ('%'|| UPPER(?sourceName) ||'%') ");	
				query3.append(" AND UPPER(CE.TXT_RAZON_SOCIAL) LIKE ('%'|| UPPER(sourceName) ||'%') ");	
			}
			
			if(data.getValueDeclarationStatus() != null && !data.getValueDeclarationStatus().equals("TD")) {
				if(data.getValueDeclarationStatus().equals("CM")) {
					query1.append(" AND DV.MCA_CARGA_AUTOMATICA = ?automaticLoad AND DV.MCA_DV_CORRECTA = ?isOK ");	
					query2.append(" AND DV.MCA_CARGA_AUTOMATICA = ?automaticLoad AND DV.MCA_DV_CORRECTA = ?isOK ");					
				}
				if(data.getValueDeclarationStatus().equals("VP")) {
					query1.append(" AND DV.MCA_CARGA_AUTOMATICA = ?automaticLoad AND DV.MCA_DV_CORRECTA = ?isOK ");
					query2.append(" AND DV.MCA_CARGA_AUTOMATICA = ?automaticLoad AND DV.MCA_DV_CORRECTA = ?isOK ");					
				}
				if(data.getValueDeclarationStatus().equals("VI")) {
					query1.append(" AND DV.MCA_CARGA_AUTOMATICA = ?automaticLoad  AND DV.MCA_DV_CORRECTA = ?isOK ");
					query2.append(" AND DV.MCA_CARGA_AUTOMATICA = ?automaticLoad  AND DV.MCA_DV_CORRECTA = ?isOK ");					
				}
				if(data.getValueDeclarationStatus().equals("VC")) {
					query1.append(" AND DV.MCA_DV_CORRECTA = ?isOK ");
					query2.append(" AND DV.MCA_DV_CORRECTA = ?isOK ");					
				}
				if(data.getValueDeclarationStatus().equals("VO")) {
					query1.append(" AND DV.MCA_CARGA_AUTOMATICA = ?automaticLoad AND DV.MCA_DV_CORRECTA = ?isOK ");
					query2.append(" AND DV.MCA_CARGA_AUTOMATICA = ?automaticLoad AND DV.MCA_DV_CORRECTA = ?isOK ");					
				}
			}	
			
			if(data.getDownloadStatus() != null && !data.getDownloadStatus().equals("T")) {
				if (data.getDownloadStatus().equals("D")) {
					query1.append(" AND DV.FEC_DT_DESCARGA IS NOT NULL ");		
					if(data.getAgencyId() != null) {
						query2.append(" AND ((DO.COD_V_AGENCIA_EXPORTACION=?agencyLegacyId AND DV.FEC_DT_DESCARGA_EXPORTADOR IS NOT NULL) OR (DO.COD_V_AGENCIA_IMPORTACION=?agencyLegacyId AND DV.FEC_DT_DESCARGA_IMPORTADOR IS NOT NULL)) ");
					} else {
						query2.append(" AND ((DV.FEC_DT_DESCARGA_EXPORTADOR IS NOT NULL) OR (DV.FEC_DT_DESCARGA_IMPORTADOR IS NOT NULL)) ");
					}
					query3.append(" AND (PA.COD_V_AGENCIA_ADUANA = agencyId AND DV.FEC_DT_DESCARGA_EXPORTADOR IS NOT NULL AND DV.FEC_DT_DESCARGA_IMPORTADOR IS NOT NULL) ");
				}
				if (data.getDownloadStatus().equals("N")) {
					query1.append(" AND DV.FEC_DT_DESCARGA IS NULL ");
					if(data.getAgencyId() != null) {
						query2.append(" AND ((DO.COD_V_AGENCIA_EXPORTACION=?agencyLegacyId AND DV.FEC_DT_DESCARGA_EXPORTADOR IS NULL) OR (DO.COD_V_AGENCIA_IMPORTACION=?agencyLegacyId AND DV.FEC_DT_DESCARGA_IMPORTADOR IS NULL)) ");
						query3.append(" AND (DV.FEC_DT_DESCARGA_EXPORTADOR IS NOT NULL AND DV.FEC_DT_DESCARGA_IMPORTADOR IS NOT NULL) ");
					} else {
						query2.append(" AND ((DV.FEC_DT_DESCARGA_EXPORTADOR IS NULL) AND (DV.FEC_DT_DESCARGA_IMPORTADOR IS NULL)) ");
						query3.append(" AND (DV.FEC_DT_DESCARGA_EXPORTADOR IS NULL AND DV.FEC_DT_DESCARGA_IMPORTADOR IS NULL) ");
					}
					
				}
			}		
			
			if(data.getDateFilterType() != null) {			
				
				if(data.getDateFilterType().equals("FE")) {
					if(data.getStartDate() != null) {
						query1.append(" AND ((TRUNC(DV.FEC_D_ALBARAN) >= TO_DATE(?dateFrom,'DD/MM/YYYY')) OR (TRUNC(DV.FEC_DT_EXPEDICION) >= TO_DATE(?dateFrom,'DD/MM/YYYY'))) ");
						query2.append(" AND ((TRUNC(DV.FEC_D_ALBARAN) >= TO_DATE(?dateFrom,'DD/MM/YYYY')) OR (TRUNC(DV.FEC_DT_EXPEDICION) >= TO_DATE(?dateFrom,'DD/MM/YYYY'))) ");						
					}
					if(data.getEndDate() != null) {
						query1.append(" AND ((TRUNC(DV.FEC_D_ALBARAN) <= TO_DATE(?dateTo,'DD/MM/YYYY')) OR (TRUNC(DV.FEC_DT_EXPEDICION) <= TO_DATE(?dateTo,'DD/MM/YYYY'))) ");
						query2.append(" AND ((TRUNC(DV.FEC_D_ALBARAN) <= TO_DATE(?dateTo,'DD/MM/YYYY')) OR (TRUNC(DV.FEC_DT_EXPEDICION) <= TO_DATE(?dateTo,'DD/MM/YYYY'))) ");						
					}
				}			
				if(data.getDateFilterType().equals("FV")) {
					if(data.getStartDate() != null) {
						query1.append(" AND TRUNC(DV.FEC_DT_CREACION) >= TO_DATE(?dateFrom,'DD/MM/YYYY') "); 
						query2.append(" AND TRUNC(DV.FEC_DT_CREACION) >= TO_DATE(?dateFrom,'DD/MM/YYYY') ");						
					}
					if(data.getEndDate() != null) {
						query1.append(" AND TRUNC(DV.FEC_DT_CREACION) <= TO_DATE(?dateTo,'DD/MM/YYYY') ");
						query2.append(" AND TRUNC(DV.FEC_DT_CREACION) <= TO_DATE(?dateTo,'DD/MM/YYYY') ");						
					}
				}			
				if(data.getDateFilterType().equals("FD")) {
					if(data.getStartDate() != null) {
						query1.append(" AND (TRUNC(DV.FEC_DT_DESCARGA)>=TO_DATE(?dateFrom,'DD/MM/YYYY')) ");
						if(data.getAgencyId() != null) {
							query2.append(" AND ((DO.COD_V_AGENCIA_EXPORTACION=?agencyLegacyId AND TRUNC(DV.FEC_DT_DESCARGA_EXPORTADOR)>=TO_DATE(?dateFrom,'DD/MM/YYYY')) OR (DO.COD_V_AGENCIA_IMPORTACION=?agencyLegacyId AND TRUNC(DV.FEC_DT_DESCARGA_IMPORTADOR)>=TO_DATE(?dateFrom,'DD/MM/YYYY'))) ");
							query3.append(" AND (PA.COD_V_AGENCIA_ADUANA = agencyId AND  TRUNC(DV.FEC_DT_DESCARGA_EXPORTADOR ) >= TO_DATE(startDate, 'DD/MM/YYYY') ");
							query3.append(" AND (PA.COD_V_AGENCIA_ADUANA = agencyId AND  TRUNC(DV.FEC_DT_DESCARGA_IMPORTADOR ) >= TO_DATE(startDate, 'DD/MM/YYYY') ");
						} else {
							query2.append(" AND (TRUNC(DV.FEC_DT_DESCARGA_EXPORTADOR ) >= TO_DATE(startDate, 'DD/MM/YYYY') ");
							query2.append(" AND (TRUNC(DV.FEC_DT_DESCARGA_IMPORTADOR ) >= TO_DATE(startDate, 'DD/MM/YYYY') ");
							query3.append(" AND (TRUNC(DV.FEC_DT_DESCARGA_EXPORTADOR ) >= TO_DATE(startDate, 'DD/MM/YYYY') ");
							query3.append(" AND (TRUNC(DV.FEC_DT_DESCARGA_IMPORTADOR ) >= TO_DATE(startDate, 'DD/MM/YYYY') ");
						}
					}
					if(data.getEndDate() != null) {
						query1.append(" AND (TRUNC(DV.FEC_DT_DESCARGA)<=TO_DATE(?dateTo,'DD/MM/YYYY')) ");
						if(data.getAgencyId() != null) {
							query2.append(" AND ((DO.COD_V_AGENCIA_EXPORTACION=?agencyLegacyId AND TRUNC(DV.FEC_DT_DESCARGA_EXPORTADOR) <= TO_DATE(?dateTo,'DD/MM/YYYY')) OR (DO.COD_V_AGENCIA_IMPORTACION=?agencyLegacyId AND TRUNC(DV.FEC_DT_DESCARGA_IMPORTADOR)<=TO_DATE(?dateTo,'DD/MM/YYYY'))) ");
							query3.append(" AND TRUNC(DV.FEC_DT_DESCARGA_EXPORTADO) <= TO_DATE(endDate, 'DD/MM/YYYY')) ");
							query3.append(" AND TRUNC(DV.FEC_DT_DESCARGA_IMPORTADOR) <= TO_DATE(endDate, 'DD/MM/YYYY')) ");
						} else {
							query2.append(" AND TRUNC(DV.FEC_DT_DESCARGA_EXPORTADO) <= TO_DATE(endDate, 'DD/MM/YYYY')) ");
							query2.append(" AND TRUNC(DV.FEC_DT_DESCARGA_IMPORTADOR) <= TO_DATE(endDate, 'DD/MM/YYYY')) ");
							query3.append(" AND TRUNC(DV.FEC_DT_DESCARGA_EXPORTADO) <= TO_DATE(endDate, 'DD/MM/YYYY')) ");
							query3.append(" AND TRUNC(DV.FEC_DT_DESCARGA_IMPORTADOR) <= TO_DATE(endDate, 'DD/MM/YYYY')) ");
						}
					}				
				}
				
			}				
			
			String orden = data.getOrder();
			if(orden != null) {
				if(orden.equals("+valueDeclarationNumber"))
					selectOrder.append(" ORDER BY COD_N_DECLARACION_VALOR ASC ");
				else if(orden.equals("-valueDeclarationNumber"))
					selectOrder.append(" ORDER BY COD_N_DECLARACION_VALOR DESC ");
				
				else if(orden.equals("+valueDeclarationYear"))
					selectOrder.append(" ORDER BY NUM_ANYO ASC");
				else if(orden.equals("-valueDeclarationYear"))
					selectOrder.append(" ORDER BY NUM_ANYO DESC");
				
				else if(orden.equals("+dossierNumber"))
					selectOrder.append(" ORDER BY NUM_DOSIER ASC");
				else if(orden.equals("-dossierNumber"))
					selectOrder.append(" ORDER BY NUM_DOSIER DESC");
				
				else if(orden.equals("+dossierYear"))
					selectOrder.append(" ORDER BY NUM_ANYO_DOSIER ASC");
				else if(orden.equals("-dossierYear"))
					selectOrder.append(" ORDER BY NUM_ANYO_DOSIER DESC");
				
				else if(orden.equals("+dispatchDate"))
					selectOrder.append(" ORDER BY FEC_EXPEDICION ASC");
				else if(orden.equals("-dispatchDate"))
					selectOrder.append(" ORDER BY FEC_EXPEDICION DESC");
				
				else if(orden.equals("+valueDeclarationGenerationDate"))
					selectOrder.append(" ORDER BY FEC_DT_CREACION ASC");
				else if(orden.equals("-valueDeclarationGenerationDate"))
					selectOrder.append(" ORDER BY FEC_DT_CREACION DESC");
				
				else if(orden.equals("+supplierId"))
					selectOrder.append(" ORDER BY CASE WHEN REPLACE(TRANSLATE(TRIM(COD_ORIGEN_ORDER), '0123456789', '0'), '0', '') IS NULL THEN TO_NUMBER(COD_ORIGEN_ORDER) END, COD_ORIGEN_ORDER");
				else if(orden.equals("-supplierId"))
					selectOrder.append(" ORDER BY CASE WHEN REPLACE(TRANSLATE(TRIM(COD_ORIGEN_ORDER), '0123456789', '0'), '0', '') IS NULL THEN TO_NUMBER(COD_ORIGEN_ORDER) END DESC, COD_ORIGEN_ORDER DESC");
				
				else if(orden.equals("+sourceName"))
					selectOrder.append(" ORDER BY NOMBRE_ORIGEN ASC ");
				else if(orden.equals("-sourceName"))
					selectOrder.append(" ORDER BY NOMBRE_ORIGEN DESC");
				
				else if(orden.equals("+exportDownloadDate"))
					selectOrder.append(" ORDER BY FEC_DT_DESCARGA_EXPORTADOR ASC");
				else if(orden.equals("-exportDownloadDate"))
					selectOrder.append(" ORDER BY FEC_DT_DESCARGA_EXPORTADOR DESC");
				
				else if(orden.equals("+importDownloadDate"))
					selectOrder.append(" ORDER BY FEC_DT_DESCARGA_IMPORTADOR ASC");
				else if(orden.equals("-importDownloadDate"))
					selectOrder.append(" ORDER BY FEC_DT_DESCARGA_IMPORTADOR DESC");			
			}
			
			ambasQuery.append(query1);
			if(data.getWarehouseId() == null && data.getSupplierId() == null) {
				ambasQuery.append(" UNION ");			
				ambasQuery.append(query2);			
			}
			ambasQuery.append(")");
			
			select.append(ambasQuery.toString());
			select.append(selectOrder.toString());			
			selectCount.append(ambasQuery.toString());	
			
			this.logger.error("### sql: " + select.toString());			
			
			final Query query = getEntityManager().createNativeQuery(select.toString());
			final Query queryCount = getEntityManager().createNativeQuery(selectCount.toString());
			
			if(data.getAgencyId() != null) {
				this.logger.error("### agencyLegacyId: " + data.getAgencyId());			
				query.setParameter("agencyLegacyId", data.getAgencyId() );
				queryCount.setParameter("agencyLegacyId", data.getAgencyId() );
			}
			
			if(data.getValueDeclarationNumber() != null) {
				this.logger.error("### valueDeclarationNumber: " + data.getValueDeclarationNumber());				
				query.setParameter("valueDeclarationNumber", data.getValueDeclarationNumber());
				queryCount.setParameter("valueDeclarationNumber", data.getValueDeclarationNumber());				
			}
			
			if(data.getValueDeclarationYear() != null) {
				this.logger.error("### valueDeclarationYear: " + data.getValueDeclarationYear());				
				query.setParameter("valueDeclarationYear", data.getValueDeclarationYear());
				queryCount.setParameter("valueDeclarationYear", data.getValueDeclarationYear());				
			}
			
			if(data.getDossierNumber() != null) {
				this.logger.error("### dossierNumber: " + data.getDossierNumber());				
				query.setParameter("dossierNumber", data.getDossierNumber());
				queryCount.setParameter("dossierNumber", data.getDossierNumber());				
			}
			
			if(data.getDossierYear() != null) {
				this.logger.error("### dossierYear: " + data.getDossierYear());				
				query.setParameter("dossierYear", data.getDossierYear());
				queryCount.setParameter("dossierYear", data.getDossierYear());				
			}		
					
			if(data.getInternalOrderId() != null) {
				this.logger.error("### internalOrderId: " + data.getInternalOrderId());				
				query.setParameter("internalOrderId", data.getInternalOrderId());
				queryCount.setParameter("internalOrderId", data.getInternalOrderId());				
			}
			
			if(data.getSupplierId() != null) {
				this.logger.error("### supplierLegacyId: " + data.getSupplierId());				
				query.setParameter("supplierLegacyId", data.getSupplierId());
				queryCount.setParameter("supplierLegacyId", data.getSupplierId());				
			}
			
			if(data.getSourceName() != null) {
				this.logger.error("### sourceName: " + data.getSourceName());				
				query.setParameter("sourceName", data.getSourceName());
				queryCount.setParameter("sourceName", data.getSourceName());				
			}		
			
			if(data.getWarehouseId() != null) {
				this.logger.error("### warehouseLegacyId: " + data.getWarehouseId());				
				query.setParameter("warehouseLegacyId", data.getWarehouseId());
				queryCount.setParameter("warehouseLegacyId", data.getWarehouseId());				
			}
			
			if(data.getTargetName() != null) {
				this.logger.error("### targetName: " + data.getTargetName());				
				query.setParameter("targetName", data.getTargetName());
				queryCount.setParameter("targetName", data.getTargetName());				
			}
					
			if("CM".equals(data.getValueDeclarationStatus())){
				query.setParameter("automaticLoad", "N");
				query.setParameter("isOK", "S");
				queryCount.setParameter("automaticLoad", "N");
				queryCount.setParameter("isOK", "S");				
				this.logger.error("### automaticLoad: N");
				this.logger.error("### isOK: S");				
			}
			if("VI".equals(data.getValueDeclarationStatus())){
				query.setParameter("automaticLoad", "S");
				query.setParameter("isOK", "N");
				queryCount.setParameter("automaticLoad", "S");
				queryCount.setParameter("isOK", "N");				
				this.logger.error("### automaticLoad: S");
				this.logger.error("### isOK: N");				
			}	
			if("VP".equals(data.getValueDeclarationStatus())){
				query.setParameter("automaticLoad", "N");
				query.setParameter("isOK", "N");
				queryCount.setParameter("automaticLoad", "N");
				queryCount.setParameter("isOK", "N");				
				this.logger.error("### automaticLoad: N");
				this.logger.error("### isOK: N");				
			}		
			if("VC".equals(data.getValueDeclarationStatus())){
				/* El filtro VC es usado por PROV para recuperar todas las DV correctas independientemente de si son cargas manuales o automáticas. */
				query.setParameter("isOK", "S");
				query.setParameter("isOK", "S");				
				this.logger.error("### isOK: S");				
			}
			if("VO".equals(data.getValueDeclarationStatus())){
				query.setParameter("automaticLoad", "S");
				query.setParameter("isOK", "S");
				queryCount.setParameter("automaticLoad", "S");
				queryCount.setParameter("isOK", "S");				
				this.logger.error("### automaticLoad: S");
				this.logger.error("### isOK: S");				
			}			
		
			String dateMask = "dd/M/yyyy";		
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateMask);
	
			if(data.getStartDate() != null) {
				Date dateFromTemp = new SimpleDateFormat("yyyy-M-dd hh:mm:ss").parse(data.getStartDate());
				String dateFrom = simpleDateFormat.format(dateFromTemp) ;
		        query.setParameter("dateFrom", dateFrom);
		        queryCount.setParameter("dateFrom", dateFrom);		        
				this.logger.error("### dateFrom: " + dateFrom);		        
			}
				
			if(data.getEndDate() != null) {
				Date dateToTemp = new SimpleDateFormat("yyyy-M-dd hh:mm:ss").parse(data.getEndDate());
				String dateTo = simpleDateFormat.format(dateToTemp) ;
		        query.setParameter("dateTo", dateTo);
		        queryCount.setParameter("dateTo", dateTo);		        
				this.logger.error("### dateTo: " + dateTo);		        
			}
					
			query.setParameter("lastInForce", "S");
			queryCount.setParameter("lastInForce", "S");		
			this.logger.error("### lastInForce: S");			
			
			if(paginaInicio != null) {
				if(paginaTamanyo != null) {
					paginaInicio = (paginaInicio * paginaTamanyo) - paginaTamanyo;
				}else {
					paginaInicio = (paginaInicio * 10) - 10;
				}
			}
						
			query.setFirstResult(paginaInicio);
			query.setMaxResults(paginaTamanyo);
			
			
			/**
			 * Contamos el total de resultados para la paginación.
			 * */
			
			Integer totalResults = Integer.parseInt(String.valueOf(queryCount.getSingleResult()));
			
			Map<String, String> mapMetaData = new HashMap<>();
			mapMetaData.put("totalItemsCount", totalResults.toString());
					
			
			List<Object[]> listado = query.getResultList();
			
			
			if(listado != null && !listado.isEmpty()) {
				
				for (Object[] tmp : listado) {
					
					DataVDSumaryDTO valueDeclaractionSumary = new DataVDSumaryDTO();
					
					valueDeclaractionSumary.setValueDeclarationOk(tmp[0] != null && tmp[0].equals("S"));
					
					valueDeclaractionSumary.setAutomaticLoading(tmp[1] != null && tmp[1].equals("S"));
					
					DataVDeclarationIdsDTO valueDeclarationIds = new DataVDeclarationIdsDTO();
					
					valueDeclarationIds.setValueDeclarationNumber(String.valueOf(tmp[2]));
					valueDeclarationIds.setValueDeclarationYear(String.valueOf(tmp[3]));
					valueDeclarationIds.setValueDeclarationVersion(String.valueOf(tmp[4]));	
					
					valueDeclaractionSumary.setValueDeclarationIds(valueDeclarationIds);
					
					valueDeclaractionSumary.setValueDeclarationType(String.valueOf(tmp[5]));
					
					DataVDossierIdsDTO dossierIds = new DataVDossierIdsDTO();
					
					if (tmp[6] != null) {
						dossierIds.setDossierNumber(String.valueOf(tmp[6]));
					}
					if (tmp[7] != null) {
						dossierIds.setDossierYear(String.valueOf(tmp[7]));
					}
					
					valueDeclaractionSumary.setDossiersIds(dossierIds);
					
					valueDeclaractionSumary.setTotalAmount(Double.parseDouble(String.valueOf(tmp[8])));
					valueDeclaractionSumary.setTotalAmountCurrency("EUR");
					
					DataVDDatesDTO dates = new DataVDDatesDTO();
					
					dates.setDispatchDate(String.valueOf(tmp[9]));
					
					dates.setValueDeclarationGenerationDate(String.valueOf(tmp[10]));
					
					dates.setValueDeclarationDownloadDate(String.valueOf(tmp[11]));
					dates.setExportDownloadDate(String.valueOf(tmp[12]));
					dates.setImportDownloadDate(String.valueOf(tmp[13]));
					
					valueDeclaractionSumary.setDates(dates);
					
					if (tmp[18] != null) {
						valueDeclaractionSumary.setDispatchCode(String.valueOf(tmp[18]));
					}
					
					valueDeclaractionSumary.setValueDeclarationStatus(calculaEstado(tmp[1].toString(),tmp[0].toString(),data.getValueDeclarationStatus()));

					// Obtiene la lista de pedidos
					
					DataVDSumarySourceDTO valueDeclaractionSource = new DataVDSumarySourceDTO();
					
					valueDeclaractionSource.setPublicId(String.valueOf(tmp[14]));
					valueDeclaractionSource.setName(String.valueOf(tmp[15]));
					valueDeclaractionSource.setRegionId(String.valueOf(tmp[16]));
					valueDeclaractionSource.setTypeId(String.valueOf(tmp[17]));				
					
					valueDeclaractionSumary.setSource(valueDeclaractionSource);
					
					// Obtiene la lista de pedidos
					valueDeclaractionSumary.setInternalOrderList(getPedidos(tmp[2].toString(),tmp[3].toString(),tmp[4].toString(),tmp[17].toString()));
					
					resultList.add(valueDeclaractionSumary);
				}
			}
			
			DataVDPaginationDTO pagination = new DataVDPaginationDTO();
			
			pagination.setRequestedPage(0L);
			pagination.setRequestedSize(0L);
			pagination.setRetrievedResults(0L);
			pagination.setTotalResults(0L);
			pagination.setNextPage("");
			pagination.setPreviousPage("");			
			
			result.setMetadata(mapMetaData);
			result.setData(resultList);
			result.setPagination(pagination);
		
		} catch (Exception e) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG,NOMBRE_CLASE,"getValueDeclarationList",e.getClass().getSimpleName(),e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}	
			
			
		return result;
	}

	
	private List<DataVDSumaryOrderDTO> getPedidos(String valueDeclarationCode, String valueDeclarationYear, String valueDeclarationVersion, String typeId) {

		StringBuilder sqlPedido = new StringBuilder();
		
		if (typeId.equals("PROVEEDOR")) {
			sqlPedido.append("SELECT COD_V_PEDIDO ");
			sqlPedido.append("FROM O_DECLARACION_VALOR_CAB ");
			sqlPedido.append("WHERE COD_N_DECLARACION_VALOR = ?valueDeclarationNumber ");
			sqlPedido.append("AND NUM_ANYO = ?valueDeclarationYear ");
			sqlPedido.append("AND COD_N_VERSION = ?valueDeclarationVersion");
		} else if (typeId.equals("BLOQUE")) {
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
		List<DataVDSumaryOrderDTO> pedidos = null;

		if (listadoPedido != null && !listadoPedido.isEmpty()) {
			pedidos = new ArrayList<>();
			for (String pedido : listadoPedido) {
				DataVDSumaryOrderDTO internalOrderList = new DataVDSumaryOrderDTO();
				if (pedido != null) {
					internalOrderList.setInternalOrderId(pedido);
				}
				pedidos.add(internalOrderList);
			}
		}

		return pedidos;
	} 	

	private String calculaEstado(String mcaCargaAuto, String mcaDVCorrecta, String filtroEstado) {
		
		String resultado = null;
		
		if (filtroEstado != null) {	
			if(filtroEstado.equals("TD")) {
				
				if(mcaCargaAuto.equals("N")) {
					if(mcaDVCorrecta.equals("S")) {
						resultado = "MANUAL";
					}else if(mcaDVCorrecta.equals("N")) {
						resultado = "PENDIENTE";
					}
				}else if(mcaCargaAuto.equals("S")) {
					if(mcaDVCorrecta.equals("S")) {
						resultado = "OK";
					}else if(mcaDVCorrecta.equals("N")) {
						resultado = "KO";
					}
				} else if(mcaCargaAuto.equals("E")) {
					resultado = "PENDIENTE";
				}
			}else {
				if(filtroEstado.equals("CM")) {
					resultado = "MANUAL";
				}
				else if(filtroEstado.equals("VP")) {
					resultado = "PENDIENTE";
				}
				else if(filtroEstado.equals("VI")) {
					resultado = "KO";
				}
				else if(filtroEstado.equals("VO")) {
					resultado = "OK";
				}
				else if(filtroEstado.equals("VC")) {
					resultado = "OK";
				}
			}
		}	
	
		return resultado;
	}
	
}
