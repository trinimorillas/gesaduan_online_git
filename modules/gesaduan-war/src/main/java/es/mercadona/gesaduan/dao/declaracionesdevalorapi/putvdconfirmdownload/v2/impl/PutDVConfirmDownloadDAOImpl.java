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

			if(esCompraSobreMuelle (input)) {
				final StringBuilder sqlExisteProveedor = new StringBuilder();
				sqlExisteProveedor.append("UPDATE O_DECLARACION_VALOR_CAB SET ");
				sqlExisteProveedor.append("MCA_DESCARGA = ?isDownloaded, ");
				sqlExisteProveedor.append("FEC_DT_DESCARGA = SYSDATE, ");
				sqlExisteProveedor.append("COD_V_USR_MODIFICACION = ?userId ");
				sqlExisteProveedor.append("WHERE COD_N_DECLARACION_VALOR = ?valueDeclarationNumber ");
				sqlExisteProveedor.append("AND NUM_ANYO = ?valueDeclarationYear ");
				sqlExisteProveedor.append("AND COD_N_VERSION = ?valueDeclarationVersion");
				     
				final Query queryExisteProveedor = getEntityManager().createNativeQuery(sqlExisteProveedor.toString());

				queryExisteProveedor.setParameter(IS_DOWNLOADED, descargado);
				queryExisteProveedor.setParameter(USER_ID, input.getUserId());
				queryExisteProveedor.setParameter(VALUE_DECLARATION_NUMBER, input.getValueDeclarationNumber());
				queryExisteProveedor.setParameter(VALUE_DECLARATION_YEAR, input.getValueDeclarationYear());
				queryExisteProveedor.setParameter(VALUE_DECLARATION_VERSION, input.getValueDeclarationVersion());
				queryExisteProveedor.executeUpdate();
			} else {
				// PROVEEDOR EXPORTADOR
				final StringBuilder sqlProveedorExportador = new StringBuilder();
				sqlProveedorExportador.append("MERGE INTO O_DECLARACION_VALOR_CAB DV USING (");
				sqlProveedorExportador.append("SELECT NUM_DOSIER, NUM_ANYO ");
				sqlProveedorExportador.append("FROM D_DOSIER ");
				sqlProveedorExportador.append("WHERE COD_V_AGENCIA_EXPORTACION = ?supplierId) DD ");
				sqlProveedorExportador.append("ON (DV.NUM_DOSIER = DD.NUM_DOSIER AND DV.NUM_ANYO = DD.NUM_ANYO) ");
				sqlProveedorExportador.append("WHEN MATCHED THEN UPDATE SET ");
				sqlProveedorExportador.append("FEC_DT_DESCARGA_EXPORTADOR = DECODE(?isDownloaded, 'S', SYSDATE, NULL), ");
				sqlProveedorExportador.append("COD_V_USR_MODIFICACION = ?userId ");
				sqlProveedorExportador.append("WHERE COD_N_DECLARACION_VALOR = ?valueDeclarationNumber AND NUM_ANYO = ?valueDeclarationYear AND COD_N_VERSION = ?valueDeclarationVersion");
				     
				final Query queryProveedorExportador = getEntityManager().createNativeQuery(sqlProveedorExportador.toString());
				
				queryProveedorExportador.setParameter(SUPPLIER_ID, input.getSupplierId());
				queryProveedorExportador.setParameter(IS_DOWNLOADED, descargado);
				queryProveedorExportador.setParameter(USER_ID, input.getUserId());
				queryProveedorExportador.setParameter(VALUE_DECLARATION_NUMBER, input.getValueDeclarationNumber());
				queryProveedorExportador.setParameter(VALUE_DECLARATION_YEAR, input.getValueDeclarationYear());
				queryProveedorExportador.setParameter(VALUE_DECLARATION_VERSION, input.getValueDeclarationVersion());
				
				queryProveedorExportador.executeUpdate();
				
				// PROVEEDOR IMPORTADOR
				final StringBuilder sqlProveedorImportador = new StringBuilder();
				sqlProveedorImportador.append("MERGE INTO O_DECLARACION_VALOR_CAB DV USING (");
				sqlProveedorImportador.append("SELECT NUM_DOSIER, NUM_ANYO ");
				sqlProveedorImportador.append("FROM D_DOSIER ");
				sqlProveedorImportador.append("WHERE COD_V_AGENCIA_IMPORTACION = ?supplierId) DD ");
				sqlProveedorImportador.append("ON (DV.NUM_DOSIER = DD.NUM_DOSIER AND DV.NUM_ANYO = DD.NUM_ANYO) ");
				sqlProveedorImportador.append("WHEN MATCHED THEN UPDATE SET ");
				sqlProveedorImportador.append("FEC_DT_DESCARGA_IMPORTADOR = DECODE(?isDownloaded, 'S', SYSDATE, NULL), ");
				sqlProveedorImportador.append("COD_V_USR_MODIFICACION = ?userId ");
				sqlProveedorImportador.append("WHERE COD_N_DECLARACION_VALOR = ?valueDeclarationNumber AND NUM_ANYO = ?valueDeclarationYear AND COD_N_VERSION = ?valueDeclarationVersion");
				     
				final Query queryProveedorImportador = getEntityManager().createNativeQuery(sqlProveedorImportador.toString());
				
				queryProveedorImportador.setParameter(SUPPLIER_ID, input.getSupplierId());
				queryProveedorImportador.setParameter(IS_DOWNLOADED, descargado);
				queryProveedorImportador.setParameter(USER_ID, input.getUserId());
				queryProveedorImportador.setParameter(VALUE_DECLARATION_NUMBER, input.getValueDeclarationNumber());
				queryProveedorImportador.setParameter(VALUE_DECLARATION_YEAR, input.getValueDeclarationYear());
				queryProveedorImportador.setParameter(VALUE_DECLARATION_VERSION, input.getValueDeclarationVersion());
				
				queryProveedorImportador.executeUpdate();
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

	private boolean esCompraSobreMuelle (InputDataDTO input) {
		boolean retorno = false;
		
		try {
			final StringBuilder sqlFactura = new StringBuilder();
			
			sqlFactura.append("SELECT COD_N_PROVEEDOR ");
			sqlFactura.append("FROM O_DECLARACION_VALOR_CAB DV ");
			sqlFactura.append("WHERE DV.COD_N_DECLARACION_VALOR = ?valueDeclarationNumber ");
			sqlFactura.append("AND DV.NUM_ANYO = ?valueDeclarationYear ");
			sqlFactura.append("AND DV.COD_N_VERSION = ?valueDeclarationVersion");
	
			final Query queryFactura = getEntityManager().createNativeQuery(sqlFactura.toString());
	
			queryFactura.setParameter(VALUE_DECLARATION_NUMBER, input.getValueDeclarationNumber());
			queryFactura.setParameter(VALUE_DECLARATION_YEAR, input.getValueDeclarationYear());
			queryFactura.setParameter(VALUE_DECLARATION_VERSION, input.getValueDeclarationVersion());
			
			Object codProveedor = queryFactura.getSingleResult();
			
			if (codProveedor != null) {
				retorno = true;
			}
						
		} catch (Exception e) {			
			throw new ApplicationException(e.getMessage());
		}		
		
		return retorno;
	}
	
}