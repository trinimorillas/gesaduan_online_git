package es.mercadona.gesaduan.dao.declaracionesdevalorapi.putvdconfirmdownload.v2.impl;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.fwk.data.DaoBaseImpl;
import es.mercadona.gesaduan.dao.declaracionesdevalorapi.putvdconfirmdownload.v2.PutDVConfirmDownloadDAO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.putvdconfirmdownload.v2.InputDataDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.putvdconfirmdownload.v2.restfull.OutputDataDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.putvdconfirmdownload.v2.restfull.OutputPutVDConfirmDownloadDTO;
import es.mercadona.gesaduan.jpa.declaracionesdevalorapi.putvdconfirmdownload.v2.ConfirmDownloadDVJPA;
import es.mercadona.gesaduan.jpa.declaracionesdevalorapi.putvdconfirmdownload.v2.ConfirmDownloadDVPK;

@Stateless
public class PutDVConfirmDownloadDAOImpl extends DaoBaseImpl<ConfirmDownloadDVPK, ConfirmDownloadDVJPA> implements PutDVConfirmDownloadDAO {
	
	@PersistenceContext
	private EntityManager entityM;
	
	@Override
	public void setEntityClass() {
		this.entityClass = ConfirmDownloadDVJPA.class;
	}
	
	@Override
	protected EntityManager getEntityManager() {		
		return this.entityM;
	}
	
	private static final String VALUE_DECLARATION_NUMBER = "valueDeclarationNumber";
	private static final String VALUE_DECLARATION_YEAR = "valueDeclarationYear";
	private static final String VALUE_DECLARATION_VERSION = "valueDeclarationVersion";
	private static final String IS_DOWNLOADED = "isDownloaded";
	private static final String USER_ID = "userId";
	private static final String SUPPLIER_ID = "supplierId";

	@Transactional
	@Override
	public OutputPutVDConfirmDownloadDTO actualizarEstadoDescarga(InputDataDTO input) {
		OutputPutVDConfirmDownloadDTO result = null;

		try {
			Boolean estaDescargado = input.getIsDownloaded();
			
			String descargado;
			if (Boolean.TRUE.equals(estaDescargado)) {
				descargado = "S";
			} else {
				descargado = "N";
			}
			
			String tipoFactura = getTipoFactura(input);

			if(tipoFactura.equals("CSM")) {
				final StringBuilder sqlCompraSobreMuelle = new StringBuilder();
				sqlCompraSobreMuelle.append("UPDATE O_DECLARACION_VALOR_CAB SET ");
				sqlCompraSobreMuelle.append("MCA_DESCARGA = ?isDownloaded, ");
				sqlCompraSobreMuelle.append("FEC_DT_DESCARGA = SYSDATE, ");
				sqlCompraSobreMuelle.append("COD_V_USR_MODIFICACION = ?userId ");
				sqlCompraSobreMuelle.append("WHERE COD_N_DECLARACION_VALOR = ?valueDeclarationNumber ");
				sqlCompraSobreMuelle.append("AND NUM_ANYO = ?valueDeclarationYear ");
				sqlCompraSobreMuelle.append("AND COD_N_VERSION = ?valueDeclarationVersion");
				     
				final Query queryCompraSobreMuelle = getEntityManager().createNativeQuery(sqlCompraSobreMuelle.toString());

				queryCompraSobreMuelle.setParameter(IS_DOWNLOADED, descargado);
				queryCompraSobreMuelle.setParameter(USER_ID, input.getUserId());
				queryCompraSobreMuelle.setParameter(VALUE_DECLARATION_NUMBER, input.getValueDeclarationNumber());
				queryCompraSobreMuelle.setParameter(VALUE_DECLARATION_YEAR, input.getValueDeclarationYear());
				queryCompraSobreMuelle.setParameter(VALUE_DECLARATION_VERSION, input.getValueDeclarationVersion());
				queryCompraSobreMuelle.executeUpdate();
			} else {
				if(tipoFactura.equals("PED")) {
					// PROVEEDOR EXPORTADOR
					final StringBuilder sqlPedidoExportador = new StringBuilder();
					sqlPedidoExportador.append("MERGE INTO O_DECLARACION_VALOR_CAB DV USING (");
					sqlPedidoExportador.append("SELECT NUM_DOSIER, NUM_ANYO ");
					sqlPedidoExportador.append("FROM D_DOSIER DO ");
					sqlPedidoExportador.append("LEFT JOIN D_PROVEEDOR_R P ON (P.COD_N_PROVEEDOR = DO.COD_V_AGENCIA_EXPORTACION) ");				
					sqlPedidoExportador.append("WHERE (DO.COD_V_AGENCIA_EXPORTACION = ?supplierId OR P.COD_N_LEGACY_PROVEEDOR = ?supplierId)) DD ");
					sqlPedidoExportador.append("ON (DV.NUM_DOSIER = DD.NUM_DOSIER AND DV.NUM_ANYO = DD.NUM_ANYO) ");
					sqlPedidoExportador.append("WHEN MATCHED THEN UPDATE SET ");
					sqlPedidoExportador.append("FEC_DT_DESCARGA_EXPORTADOR = DECODE(?isDownloaded, 'S', SYSDATE, NULL), ");
					sqlPedidoExportador.append("COD_V_USR_MODIFICACION = ?userId ");
					sqlPedidoExportador.append("WHERE COD_N_DECLARACION_VALOR = ?valueDeclarationNumber AND NUM_ANYO = ?valueDeclarationYear AND COD_N_VERSION = ?valueDeclarationVersion");
					     
					final Query queryPedidoExportador = getEntityManager().createNativeQuery(sqlPedidoExportador.toString());
					
					queryPedidoExportador.setParameter(SUPPLIER_ID, input.getSupplierId());
					queryPedidoExportador.setParameter(IS_DOWNLOADED, descargado);
					queryPedidoExportador.setParameter(USER_ID, input.getUserId());
					queryPedidoExportador.setParameter(VALUE_DECLARATION_NUMBER, input.getValueDeclarationNumber());
					queryPedidoExportador.setParameter(VALUE_DECLARATION_YEAR, input.getValueDeclarationYear());
					queryPedidoExportador.setParameter(VALUE_DECLARATION_VERSION, input.getValueDeclarationVersion());
					
					queryPedidoExportador.executeUpdate();
					
					// PROVEEDOR IMPORTADOR
					final StringBuilder sqlPedidoImportador = new StringBuilder();
					sqlPedidoImportador.append("MERGE INTO O_DECLARACION_VALOR_CAB DV USING (");
					sqlPedidoImportador.append("SELECT NUM_DOSIER, NUM_ANYO ");
					sqlPedidoImportador.append("FROM D_DOSIER ");
					sqlPedidoImportador.append("LEFT JOIN D_PROVEEDOR_R P ON (P.COD_N_PROVEEDOR = DO.COD_V_AGENCIA_IMPORTACION ) ");				
					sqlPedidoImportador.append("WHERE (DO.COD_V_AGENCIA_IMPORTACION = ?supplierId OR P.COD_N_LEGACY_PROVEEDOR = ?supplierId)) DD ");
					sqlPedidoImportador.append("ON (DV.NUM_DOSIER = DD.NUM_DOSIER AND DV.NUM_ANYO = DD.NUM_ANYO) ");
					sqlPedidoImportador.append("WHEN MATCHED THEN UPDATE SET ");
					sqlPedidoImportador.append("FEC_DT_DESCARGA_IMPORTADOR = DECODE(?isDownloaded, 'S', SYSDATE, NULL), ");
					sqlPedidoImportador.append("COD_V_USR_MODIFICACION = ?userId ");
					sqlPedidoImportador.append("WHERE COD_N_DECLARACION_VALOR = ?valueDeclarationNumber AND NUM_ANYO = ?valueDeclarationYear AND COD_N_VERSION = ?valueDeclarationVersion");
					     
					final Query queryPedidoImportador = getEntityManager().createNativeQuery(sqlPedidoImportador.toString());
					
					queryPedidoImportador.setParameter(SUPPLIER_ID, input.getSupplierId());
					queryPedidoImportador.setParameter(IS_DOWNLOADED, descargado);
					queryPedidoImportador.setParameter(USER_ID, input.getUserId());
					queryPedidoImportador.setParameter(VALUE_DECLARATION_NUMBER, input.getValueDeclarationNumber());
					queryPedidoImportador.setParameter(VALUE_DECLARATION_YEAR, input.getValueDeclarationYear());
					queryPedidoImportador.setParameter(VALUE_DECLARATION_VERSION, input.getValueDeclarationVersion());
					
					queryPedidoImportador.executeUpdate();
				} else {
					if(tipoFactura.equals("DEV")) {
						final StringBuilder sqlDevolucion = new StringBuilder();
						
						sqlDevolucion.append("UPDATE O_DECLARACION_VALOR_CAB DVC ");
						sqlDevolucion.append("SET ");
						sqlDevolucion.append("  FEC_DT_DESCARGA_EXPORTADOR= DECODE(?isDownloaded, 'S', SYSDATE, NULL)), ");
						sqlDevolucion.append("  FEC_DT_DESCARGA_IMPORTADOR = DECODE(?isDownloaded, 'S', SYSDATE, NULL), ");
						sqlDevolucion.append("  COD_V_USR_MODIFICACION = ?userId ");
						sqlDevolucion.append("WHERE ");
						sqlDevolucion.append("  DVC.COD_N_DECLARACION_VALOR = ?valueDeclarationNumber AND ");
						sqlDevolucion.append("  DVC.NUM_ANYO = ?valueDeclarationYear AND ");
						sqlDevolucion.append("  DVC.COD_N_VERSION = ?valueDeclarationVersion AND ");
						sqlDevolucion.append("  EXISTS (SELECT 1 FROM D_CENTRO_R CE ");
						sqlDevolucion.append("          WHERE CE.COD_V_CENTRO = DVC.COD_V_TIENDA AND ");
						sqlDevolucion.append("            EXISTS (SELECT 1 FROM D_PUERTO PU ");
						sqlDevolucion.append("                    WHERE PU.COD_V_PROVINCIA = CE.COD_N_PROVINCIA AND ");
						sqlDevolucion.append("                      EXISTS (SELECT 1 FROM S_PUERTO_AGENCIA PA ");
						sqlDevolucion.append("                              LEFT JOIN D_PROVEEDOR_R PAP ON (PAP.COD_N_PROVEEDOR = PA.COD_V_AGENCIA_ADUANA) "); 
						sqlDevolucion.append("                              WHERE PU.COD_N_PUERTO = PA.COD_N_PUERTO AND ");
						sqlDevolucion.append("                                (PA.COD_V_AGENCIA_ADUANA = ??supplierId OR PAP.COD_N_LEGACY_PROVEEDOR = ?supplierId ) ");
						sqlDevolucion.append("                       ) ");
						sqlDevolucion.append("            ) ");
						sqlDevolucion.append("  ) ");				
												     
						final Query queryDevolucion = getEntityManager().createNativeQuery(sqlDevolucion.toString());

						queryDevolucion.setParameter(SUPPLIER_ID, input.getSupplierId());						
						queryDevolucion.setParameter(IS_DOWNLOADED, descargado);
						queryDevolucion.setParameter(USER_ID, input.getUserId());
						queryDevolucion.setParameter(VALUE_DECLARATION_NUMBER, input.getValueDeclarationNumber());
						queryDevolucion.setParameter(VALUE_DECLARATION_YEAR, input.getValueDeclarationYear());
						queryDevolucion.setParameter(VALUE_DECLARATION_VERSION, input.getValueDeclarationVersion());
						queryDevolucion.executeUpdate();						
					}
				}
			}
			
			result = new OutputPutVDConfirmDownloadDTO();
			OutputDataDTO datosSalida = new OutputDataDTO();
			datosSalida.setNumFactura(Long.parseLong(input.getValueDeclarationNumber()));
			datosSalida.setAnyoFactura(Integer.parseInt(input.getValueDeclarationYear()));
			datosSalida.setVersion(input.getValueDeclarationVersion());
			result.setDatos(datosSalida);
			Map<String, String> metadatos = new HashMap<>();
			metadatos.put(USER_ID, input.getUserId());
			metadatos.put("locale", input.getLocale());
			result.setMetadatos(metadatos);
		} catch (Exception e) {			
			throw new ApplicationException(e.getMessage());
		}
		
		return result;
	}	

	private String getTipoFactura (InputDataDTO input) {
		String tipoFactura = "";
		
		try {
			final StringBuilder sqlFactura = new StringBuilder();
			
			sqlFactura.append("SELECT COD_V_TIPO_FACTURA ");
			sqlFactura.append("FROM O_DECLARACION_VALOR_CAB DV ");
			sqlFactura.append("WHERE DV.COD_N_DECLARACION_VALOR = ?valueDeclarationNumber ");
			sqlFactura.append("AND DV.NUM_ANYO = ?valueDeclarationYear ");
			sqlFactura.append("AND DV.COD_N_VERSION = ?valueDeclarationVersion");
	
			final Query queryFactura = getEntityManager().createNativeQuery(sqlFactura.toString());
	
			queryFactura.setParameter(VALUE_DECLARATION_NUMBER, input.getValueDeclarationNumber());
			queryFactura.setParameter(VALUE_DECLARATION_YEAR, input.getValueDeclarationYear());
			queryFactura.setParameter(VALUE_DECLARATION_VERSION, input.getValueDeclarationVersion());
			
			tipoFactura = (String)queryFactura.getSingleResult();
			
						
		} catch (Exception e) {			
			throw new ApplicationException(e.getMessage());
		}		
		
		return tipoFactura;
	}
	
}