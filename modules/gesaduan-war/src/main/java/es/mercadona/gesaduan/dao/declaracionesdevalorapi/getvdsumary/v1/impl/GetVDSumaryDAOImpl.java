package es.mercadona.gesaduan.dao.declaracionesdevalorapi.getvdsumary.v1.impl;

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
import es.mercadona.gesaduan.dao.declaracionesdevalorapi.getvdsumary.v1.GetVDSumaryDAO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvdsumary.v1.InputVDSumaryDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvdsumary.v1.resfull.DataVDDatesDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvdsumary.v1.resfull.DataVDSumaryDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvdsumary.v1.resfull.DataVDSumaryOrderDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvdsumary.v1.resfull.DataVDSumarySourceDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvdsumary.v1.resfull.DataVDeclarationIdsDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvdsumary.v1.resfull.DataVDossierIdsDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvdsumary.v1.resfull.OutputVDSumaryDTO;
import es.mercadona.gesaduan.jpa.declaracionesdevalor.DeclaracionesDeValorJPA;


public class GetVDSumaryDAOImpl extends BaseDAO<DeclaracionesDeValorJPA> implements GetVDSumaryDAO{
	
	
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
		final StringBuilder selectOrder = new StringBuilder();		
		final StringBuilder selectCount = new StringBuilder();		
		final StringBuilder sql = new StringBuilder();
		
		try {
		
			Integer paginaInicio = null; 		
			if(data.getFirstPage() != null) {
				paginaInicio = data.getFirstPage();
			}
			
			Integer paginaTamanyo = null; 		
			if(data.getSizePage() != null) {
				paginaTamanyo = data.getSizePage();
			}
			
			
			select.append("SELECT ");
			select.append("DISTINCT MCA_DV_CORRECTA ");
			select.append(",DV.MCA_CARGA_AUTOMATICA ");
			select.append(",DV.COD_N_DECLARACION_VALOR ");
			select.append(",DV.NUM_ANYO ");
			select.append(",DV.COD_N_VERSION ");
			select.append(",DV.NUM_DOSIER ");
			select.append(",DV.NUM_ANYO_DOSIER ");
			select.append(",NVL((SELECT SUM(NVL(NUM_IMPORTE_TOTAL, 0.0))FROM O_DECLARACION_VALOR_LIN LIN WHERE LIN.COD_N_DECLARACION_VALOR = DV.COD_N_DECLARACION_VALOR AND LIN.COD_N_VERSION = DV.COD_N_VERSION AND LIN.NUM_ANYO = DV.NUM_ANYO), 0.0) AS TOTALDV ");
			select.append(",DECODE(DV.FEC_D_ALBARAN,NULL,DV.FEC_DT_EXPEDICION,DV.FEC_D_ALBARAN) FEC_EXPEDICION ");
			select.append(",DV.FEC_DT_CREACION ");
			select.append(",DV.FEC_DT_DESCARGA ");
			select.append(",DV.FEC_DT_DESCARGA_EXPORTADOR ");
			select.append(",DV.FEC_DT_DESCARGA_IMPORTADOR ");
			select.append(",DECODE(DV.COD_N_PROVEEDOR, NULL, TO_CHAR(DV.COD_N_BLOQUE_LOGISTICO), TO_CHAR(DV.COD_N_PROVEEDOR)) COD_ORIGEN ");
			select.append(",DECODE(DV.COD_N_PROVEEDOR, NULL, BL.TXT_NOMBRE, P.TXT_RAZON_SOCIAL) NOMBRE_ORIGEN ");
			select.append(",DV.COD_N_PROVINCIA_CARGA PROVINCIA_ORIGEN ");
			select.append(",DECODE(DV.COD_N_PROVEEDOR,NULL,'BLOQUE','PROVEEDOR') TIPO_ORIGEN ");
			select.append(",DV.COD_V_EXPEDICION ");
			select.append(",DV.COD_N_PROVEEDOR ");			
			
			selectCount.append("SELECT COUNT(DISTINCT CONCAT(DV.COD_N_DECLARACION_VALOR,DV.NUM_ANYO)) ");			
			
			sql.append("FROM O_DECLARACION_VALOR_CAB DV ");
			sql.append("LEFT JOIN D_PROVEEDOR_R P ON P.COD_N_PROVEEDOR = DV.COD_N_PROVEEDOR ");
			sql.append("LEFT JOIN D_BLOQUE_LOGISTICO_R BL ON BL.COD_N_BLOQUE_LOGISTICO = DV.COD_N_BLOQUE_LOGISTICO ");				
			
			// Si el parámetro agencyLegacyId no es nulo 
			if(data.getAgencyId() != null) {
				sql.append("JOIN S_RELACION_PROVEEDOR_R RPP ON RPP.COD_N_PROVEEDOR = P.COD_N_PROVEEDOR ");
				sql.append("JOIN D_PROVEEDOR_R AGENCIA ON AGENCIA.COD_N_PROVEEDOR = RPP.COD_N_AGENCIA_ADUANA AND (RPP.FEC_D_FIN IS NULL OR TRUNC(RPP.FEC_D_FIN) >= TRUNC(SYSDATE)) ");
			}
			
			// Si el parámetro internalOrderId no es nulo 
			if(data.getInternalOrderId() != null) {		
				sql.append("LEFT JOIN S_DECLARACION_VALOR_PEDIDO DVP ON DVP.NUM_ANYO_DV= DV.NUM_ANYO  AND  DVP.COD_N_DECLARACION_VALOR= DV.COD_N_DECLARACION_VALOR AND DVP.COD_N_VERSION_DV= DV.COD_N_VERSION ");
			}		
			
			// Si el parámetro targetName no es nulo
			if(data.getTargetName() != null) {
				sql.append("LEFT JOIN D_CENTRO_R C ON DV.COD_V_ALMACEN = C.COD_V_CENTRO ");
				sql.append("LEFT JOIN D_PUERTO PU ON PU.COD_N_PUERTO= DV.COD_N_PUERTO_DESEMBARQUE ");		
			}
			
			sql.append(" WHERE 1 = 1");	
			
			sql.append(" AND DV.MCA_ULTIMA_VIGENTE = 'S' ");	
			
			if(data.getAgencyId() != null) {
				sql.append(" AND (AGENCIA.COD_N_LEGACY_PROVEEDOR = ?agencyLegacyId OR AGENCIA.COD_N_PROVEEDOR = ?agencyLegacyId) ");	
			}
			
			if(data.getValueDeclarationNumber() != null) {
				sql.append(" AND DV.COD_N_DECLARACION_VALOR = ?valueDeclarationNumber ");
			}		
			
			if(data.getValueDeclarationYear() != null) {
				sql.append(" AND DV.NUM_ANYO = ?valueDeclarationYear ");
			}	
			
			if(data.getDossierNumber() != null) {
				sql.append(" AND DV.NUM_DOSIER  = ?dossierNumber ");
			}		
			
			if(data.getDossierYear() != null) {
				sql.append(" AND DV.NUM_ANYO_DOSIER  = ?dossierYear ");
			}	
			
			if(data.getInternalOrderId() != null) {
				sql.append(" AND (DVP.COD_V_PEDIDO = ?internalOrderId OR DV.COD_V_PEDIDO = ?internalOrderId ) ");
			}
			
			if(data.getSupplierId() != null) {
				sql.append(" AND (P.COD_N_LEGACY_PROVEEDOR = ?supplierLegacyId OR P.COD_N_PROVEEDOR = ?supplierLegacyId) ");
			}
			
			if(data.getSourceName() != null) {
				sql.append(" AND (UPPER(P.TXT_RAZON_SOCIAL) LIKE ('%'|| UPPER(?sourceName) ||'%')  OR UPPER(BL.TXT_NOMBRE) LIKE ('%'|| UPPER(?sourceName) ||'%')) ");
			}
			
			if(data.getWarehouseId() != null) {
				sql.append(" AND TO_NUMBER(DV.COD_V_ALMACEN) = TO_NUMBER(?warehouseLegacyId) ");
			}
			
			if(data.getTargetName() != null) {
				sql.append(" AND (UPPER(C.TXT_NOMBRE_LARGO) LIKE ('%'|| UPPER(?targetName) ||'%')  OR UPPER(PU.TXT_NOMBRE_PUERTO) LIKE ('%'|| UPPER(?targetName) ||'%')) ");
			}	
			
			if(data.getValueDeclarationStatus() != null && !data.getValueDeclarationStatus().equals("TD")) {
				if(data.getValueDeclarationStatus().equals("CM")) {
					sql.append(" AND DV.MCA_CARGA_AUTOMATICA = ?automaticLoad ");
					sql.append(" AND DV.MCA_DV_CORRECTA = ?isOK ");	
				}
				if(data.getValueDeclarationStatus().equals("VP")) {
					sql.append(" AND DV.MCA_CARGA_AUTOMATICA = ?automaticLoad ");
					sql.append(" AND DV.MCA_DV_CORRECTA = ?isOK ");
				}
				if(data.getValueDeclarationStatus().equals("VI")) {
					sql.append(" AND DV.MCA_CARGA_AUTOMATICA = ?automaticLoad ");
					sql.append(" AND DV.MCA_DV_CORRECTA = ?isOK ");
				}
				if(data.getValueDeclarationStatus().equals("VC")) {
					sql.append(" AND DV.MCA_DV_CORRECTA = ?isOK ");
				}
				if(data.getValueDeclarationStatus().equals("VO")) {
					sql.append(" AND DV.MCA_CARGA_AUTOMATICA = ?automaticLoad ");
					sql.append(" AND DV.MCA_DV_CORRECTA = ?isOK ");
				}
			}	
			
			if(data.getDownloadStatus() != null && !data.getDownloadStatus().equals("T")) {
				if (data.getDownloadStatus().equals("D")) {
					sql.append(" AND (DV.FEC_DT_DESCARGA IS NOT NULL OR DV.FEC_DT_DESCARGA_EXPORTADOR IS NOT NULL OR DV.FEC_DT_DESCARGA_IMPORTADOR IS NOT NULL) ");				
				}
				if (data.getDownloadStatus().equals("N")) {
					sql.append(" AND (DV.FEC_DT_DESCARGA IS NULL AND DV.FEC_DT_DESCARGA_EXPORTADOR IS NULL AND DV.FEC_DT_DESCARGA_IMPORTADOR IS NULL) ");
				}
			}		
			
			if(data.getDateFilterType() != null) {			
				
				if(data.getDateFilterType().equals("FE")) {
					if(data.getStartDate() != null) {
						sql.append(" AND ((TRUNC(DV.FEC_D_ALBARAN) >= TO_DATE(?dateFrom,'DD/MM/YYYY')) OR (TRUNC(DV.FEC_DT_EXPEDICION) >= TO_DATE(?dateFrom,'DD/MM/YYYY'))) ");
					}
					if(data.getEndDate() != null) {
						sql.append(" AND ((TRUNC(DV.FEC_D_ALBARAN) <= TO_DATE(?dateTo,'DD/MM/YYYY')) OR (TRUNC(DV.FEC_DT_EXPEDICION) <= TO_DATE(?dateTo,'DD/MM/YYYY'))) ");
					}
				}			
				if(data.getDateFilterType().equals("FV")) {
					if(data.getStartDate() != null) {
						sql.append(" AND TRUNC(DV.FEC_DT_CREACION) >= TO_DATE(?dateFrom,'DD/MM/YYYY') "); 
					}
					if(data.getEndDate() != null) {
						sql.append(" AND TRUNC(DV.FEC_DT_CREACION) <= TO_DATE(?dateTo,'DD/MM/YYYY') ");
					}
				}			
				if(data.getDateFilterType().equals("FD")) {
					if(data.getStartDate() != null) {
						sql.append(" AND ((TRUNC(DV.FEC_DT_DESCARGA) >= TO_DATE(?dateFrom,'DD/MM/YYYY')) OR (TRUNC(DV.FEC_DT_DESCARGA_EXPORTADOR) >= TO_DATE(?dateFrom,'DD/MM/YYYY')) OR (TRUNC(DV.FEC_DT_DESCARGA_IMPORTADOR) >= TO_DATE(?dateFrom,'DD/MM/YYYY'))) ");
					}
					if(data.getEndDate() != null) {
						sql.append(" AND ((TRUNC(DV.FEC_DT_DESCARGA) <= TO_DATE(?dateTo,'DD/MM/YYYY')) OR (TRUNC(DV.FEC_DT_DESCARGA_EXPORTADOR) <= TO_DATE(?dateTo,'DD/MM/YYYY')) OR (TRUNC(DV.FEC_DT_DESCARGA_IMPORTADOR) <= TO_DATE(?dateTo,'DD/MM/YYYY'))) ");
					}				
				}
				
			}				
			
			String orden = data.getOrder();
			if(orden != null) {
				if(orden.equals("+valueDeclarationNumber"))
					selectOrder.append(" ORDER BY DV.COD_N_DECLARACION_VALOR ASC ");
				else if(orden.equals("-valueDeclarationNumber"))
					selectOrder.append(" ORDER BY DV.COD_N_DECLARACION_VALOR DESC ");
				
				else if(orden.equals("+valueDeclarationYear"))
					selectOrder.append(" ORDER BY DV.NUM_ANYO ASC");
				else if(orden.equals("-valueDeclarationYear"))
					selectOrder.append(" ORDER BY DV.NUM_ANYO DESC");
				
				else if(orden.equals("+dossierNumber"))
					selectOrder.append(" ORDER BY DV.NUM_DOSIER ASC");
				else if(orden.equals("-dossierNumber"))
					selectOrder.append(" ORDER BY DV.NUM_DOSIER DESC");
				
				else if(orden.equals("+dossierYear"))
					selectOrder.append(" ORDER BY DV.NUM_ANYO_DOSIER ASC");
				else if(orden.equals("-dossierYear"))
					selectOrder.append(" ORDER BY DV.NUM_ANYO_DOSIER DESC");
				
				else if(orden.equals("+dispatchDate"))
					selectOrder.append(" ORDER BY FEC_EXPEDICION ASC");
				else if(orden.equals("-dispatchDate"))
					selectOrder.append(" ORDER BY FEC_EXPEDICION DESC");
				
				else if(orden.equals("+valueDeclarationGenerationDate"))
					selectOrder.append(" ORDER BY DV.FEC_DT_CREACION ASC");
				else if(orden.equals("-valueDeclarationGenerationDate"))
					selectOrder.append(" ORDER BY DV.FEC_DT_CREACION DESC");
				
				else if(orden.equals("+supplierLegacyId"))
					selectOrder.append(" ORDER BY CASE WHEN REPLACE(TRANSLATE(TRIM(DV.COD_N_PROVEEDOR), '0123456789', '0'), '0', '') IS NULL THEN TO_NUMBER(DV.COD_N_PROVEEDOR) END, DV.COD_N_PROVEEDOR");
				else if(orden.equals("-supplierLegacyId"))
					selectOrder.append(" ORDER BY CASE WHEN REPLACE(TRANSLATE(TRIM(DV.COD_N_PROVEEDOR), '0123456789', '0'), '0', '') IS NULL THEN TO_NUMBER(DV.COD_N_PROVEEDOR) END DESC, DV.COD_N_PROVEEDOR DESC");
				
				else if(orden.equals("+sourceName"))
					selectOrder.append(" ORDER BY NOMBRE_ORIGEN ASC ");
				else if(orden.equals("-sourceName"))
					selectOrder.append(" ORDER BY NOMBRE_ORIGEN DESC");
				
				else if(orden.equals("+exportDownloadDate"))
					selectOrder.append(" ORDER BY DV.FEC_DT_DESCARGA_EXPORTADOR ASC");
				else if(orden.equals("-exportDownloadDate"))
					selectOrder.append(" ORDER BY DV.FEC_DT_DESCARGA_EXPORTADOR DESC");
				
				else if(orden.equals("+importDownloadDate"))
					selectOrder.append(" ORDER BY DV.FEC_DT_DESCARGA_IMPORTADOR ASC");
				else if(orden.equals("-importDownloadDate"))
					selectOrder.append(" ORDER BY DV.FEC_DT_DESCARGA_IMPORTADOR DESC");			
			}
			
			select.append(sql.toString());
			select.append(selectOrder.toString());			
			selectCount.append(sql.toString());			
			
			final Query query = getEntityManager().createNativeQuery(select.toString());
			final Query queryCount = getEntityManager().createNativeQuery(selectCount.toString());
			
			if(data.getAgencyId() != null) {
				query.setParameter("agencyLegacyId", data.getAgencyId() );
				queryCount.setParameter("agencyLegacyId", data.getAgencyId() );
			}
			
			if(data.getValueDeclarationNumber() != null) {
				query.setParameter("valueDeclarationNumber", data.getValueDeclarationNumber());
				queryCount.setParameter("valueDeclarationNumber", data.getValueDeclarationNumber());				
			}
			
			if(data.getValueDeclarationYear() != null) {
				query.setParameter("valueDeclarationYear", data.getValueDeclarationYear());
				queryCount.setParameter("valueDeclarationYear", data.getValueDeclarationYear());				
			}
			
			if(data.getDossierNumber() != null) {
				query.setParameter("dossierNumber", data.getDossierNumber());
				queryCount.setParameter("dossierNumber", data.getDossierNumber());				
			}
			
			if(data.getDossierYear() != null) {
				query.setParameter("dossierYear", data.getDossierYear());
				queryCount.setParameter("dossierYear", data.getDossierYear());				
			}		
					
			if(data.getInternalOrderId() != null) {
				query.setParameter("internalOrderId", data.getInternalOrderId());
				queryCount.setParameter("internalOrderId", data.getInternalOrderId());				
			}
			
			if(data.getSupplierId() != null) {
				query.setParameter("supplierLegacyId", data.getSupplierId());
				queryCount.setParameter("supplierLegacyId", data.getSupplierId());				
			}
			
			if(data.getSourceName() != null) {
				query.setParameter("sourceName", data.getSourceName());
				queryCount.setParameter("sourceName", data.getSourceName());				
			}		
			
			if(data.getWarehouseId() != null) {
				query.setParameter("warehouseLegacyId", data.getWarehouseId());
				queryCount.setParameter("warehouseLegacyId", data.getWarehouseId());				
			}
			
			if(data.getTargetName() != null) {
				query.setParameter("targetName", data.getTargetName());
				queryCount.setParameter("targetName", data.getTargetName());				
			}
					
			if("CM".equals(data.getValueDeclarationStatus())){
				query.setParameter("automaticLoad", "N");
				query.setParameter("isOK", "S");
				queryCount.setParameter("automaticLoad", "N");
				queryCount.setParameter("isOK", "S");				
			}
			if("VI".equals(data.getValueDeclarationStatus())){
				query.setParameter("automaticLoad", "S");
				query.setParameter("isOK", "N");
				queryCount.setParameter("automaticLoad", "S");
				queryCount.setParameter("isOK", "N");				
			}	
			if("VP".equals(data.getValueDeclarationStatus())){
				query.setParameter("automaticLoad", "N");
				query.setParameter("isOK", "N");
				queryCount.setParameter("automaticLoad", "N");
				queryCount.setParameter("isOK", "N");				
			}		
			if("VC".equals(data.getValueDeclarationStatus())){
				/* El filtro VC es usado por PROV para recuperar todas las DV correctas independientemente de si son cargas manuales o automáticas. */
				query.setParameter("isOK", "S");
				query.setParameter("isOK", "S");				
			}
			if("VO".equals(data.getValueDeclarationStatus())){
				query.setParameter("automaticLoad", "S");
				query.setParameter("isOK", "S");
				queryCount.setParameter("automaticLoad", "S");
				queryCount.setParameter("isOK", "S");				
			}			
		
			String dateMask = "dd/M/yyyy";		
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateMask);
	
			if(data.getStartDate() != null) {
				Date dateFromTemp = new SimpleDateFormat("yyyy-M-dd hh:mm:ss").parse(data.getStartDate());
				String dateFrom = simpleDateFormat.format(dateFromTemp) ;
		        query.setParameter("dateFrom", dateFrom);
		        queryCount.setParameter("dateFrom", dateFrom);		        
			}
				
			if(data.getEndDate() != null) {
				Date dateToTemp = new SimpleDateFormat("yyyy-M-dd hh:mm:ss").parse(data.getEndDate());
				String dateTo = simpleDateFormat.format(dateToTemp) ;
		        query.setParameter("dateTo", dateTo);
		        queryCount.setParameter("dateTo", dateTo);		        
			}
					
			query.setParameter("lastInForce", "S");
			queryCount.setParameter("lastInForce", "S");			
			
			
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
			
			Map<String, String> mapaMetaData = new HashMap<>();
			mapaMetaData.put("totalItemsCount", totalResults.toString());
					
			
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
					
					DataVDossierIdsDTO dossierIds = new DataVDossierIdsDTO();
					
					if (tmp[5] != null) {
						dossierIds.setDossierNumber(String.valueOf(tmp[5]));
					}
					if (tmp[6] != null) {
						dossierIds.setDossierYear(String.valueOf(tmp[6]));
					}
					
					valueDeclaractionSumary.setDossiersIds(dossierIds);
					
					valueDeclaractionSumary.setTotalAmount(Double.parseDouble(String.valueOf(tmp[7])));
					valueDeclaractionSumary.setTotalAmountCurrency("EUR");
					
					DataVDDatesDTO dates = new DataVDDatesDTO();
					
					dates.setDispatchDate(String.valueOf(tmp[8]));
					
					dates.setValueDeclarationGenerationDate(String.valueOf(tmp[9]));
					
					dates.setValueDeclarationDownloadDate(String.valueOf(tmp[10]));
					dates.setExportDownloadDate(String.valueOf(tmp[11]));
					dates.setImportDownloadDate(String.valueOf(tmp[12]));
					
					valueDeclaractionSumary.setDates(dates);
					
					if (tmp[17] != null) {
						valueDeclaractionSumary.setDispatchCode(String.valueOf(tmp[17]));
					}
					
					valueDeclaractionSumary.setValueDeclarationStatus(calculaEstado(tmp[1].toString(),tmp[0].toString(),data.getValueDeclarationStatus()));

					// Obtiene la lista de pedidos
					
					DataVDSumarySourceDTO valueDeclaractionSource = new DataVDSumarySourceDTO();
					
					valueDeclaractionSource.setPublicId(String.valueOf(tmp[13]));
					valueDeclaractionSource.setName(String.valueOf(tmp[14]));
					valueDeclaractionSource.setRegionId(String.valueOf(tmp[15]));
					valueDeclaractionSource.setTypeId(String.valueOf(tmp[16]));				
					
					valueDeclaractionSumary.setSource(valueDeclaractionSource);
					
					// Obtiene la lista de pedidos
					valueDeclaractionSumary.setInternalOrderList(getPedidos(tmp[2].toString(),tmp[3].toString(),tmp[4].toString(),tmp[16].toString()));
					
					resultList.add(valueDeclaractionSumary);
				}
			}
			
			result.setMetadata(mapaMetaData);
			result.setData(resultList);
		
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
