package es.mercadona.gesaduan.dao.dosierapi.putdosierconfirmadescarga.v1.impl;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.fwk.data.DaoBaseImpl;
import es.mercadona.gesaduan.dao.dosierapi.putdosierconfirmadescarga.v1.PutDosierConfirmaDescargaDAO;
import es.mercadona.gesaduan.dto.dosierapi.putdosierconfirmadescarga.v1.InputDataDTO;
import es.mercadona.gesaduan.dto.dosierapi.putdosierconfirmadescarga.v1.restfull.OutputDatosDTO;
import es.mercadona.gesaduan.dto.dosierapi.putdosierconfirmadescarga.v1.restfull.OutputPutDosierConfirmaDescargaDTO;
import es.mercadona.gesaduan.jpa.dosier.DosierJPA;

@Stateless
public class PutDosierConfirmaDescargaDAOImpl extends DaoBaseImpl<Long, DosierJPA> implements PutDosierConfirmaDescargaDAO {

	@PersistenceContext
	private EntityManager entityM;
	
	@Override
	public void setEntityClass() {
		this.entityClass = DosierJPA.class;
	}
	
	@Override
	protected EntityManager getEntityManager() {		
		return this.entityM;
	}

	@Transactional
	@Override
	public OutputPutDosierConfirmaDescargaDTO actualizarEstadoDescarga(InputDataDTO input) {
		OutputPutDosierConfirmaDescargaDTO result = null;

		try {
			// Agencia exportadora en dosier
			actualizarEstadoDescargaDosierExp(input);
			
			// Agencia importadora en dosier
			actualizarEstadoDescargaDosierImp(input);	
			
			// Agencia exportadora en dosier
			actualizarEstadoDescargaDVExp(input);
			
			// Agencia importadora en dosier
			actualizarEstadoDescargaDVImp(input);				
			
			
			result = new OutputPutDosierConfirmaDescargaDTO();
			OutputDatosDTO datosSalida = new OutputDatosDTO();
			datosSalida.setNumDosier(Long.parseLong(input.getDossierNumber()));
			datosSalida.setAnyoDosier(Integer.parseInt(input.getDossierYear()));
			result.setDatos(datosSalida);
			Map<String, String> metadata = new HashMap<>();
			metadata.put("userId", input.getUserId());
			// metadata.put("locale", input.getMetadata().getLocale());
			result.setMetadatos(metadata);
		} catch (Exception e) {			
			throw new ApplicationException(e.getMessage());
		}
		
		return result;
	}	

	
	@Transactional
	private void actualizarEstadoDescargaDosierExp(InputDataDTO input) {
		try {
			// Agencia exportadora
			final StringBuilder sqlAgenciaExportadora = new StringBuilder();
			sqlAgenciaExportadora.append("MERGE INTO D_DOSIER DO USING ( ");
			sqlAgenciaExportadora.append("SELECT COD_N_PROVEEDOR ");
			sqlAgenciaExportadora.append("FROM D_PROVEEDOR_R ");
			sqlAgenciaExportadora.append("WHERE COD_N_PROVEEDOR = ?agencyId OR COD_N_LEGACY_PROVEEDOR = ?agencyId ");
			sqlAgenciaExportadora.append(") PR ");
			sqlAgenciaExportadora.append("ON (DO.COD_V_AGENCIA_EXPORTACION = PR.COD_N_PROVEEDOR) ");
			sqlAgenciaExportadora.append("WHEN MATCHED THEN UPDATE SET ");
			sqlAgenciaExportadora.append("FEC_DT_DESCARGA_EXPORTADOR = DECODE(?isDownloaded, 'S', systimestamp, NULL), ");
			sqlAgenciaExportadora.append("COD_V_USUARIO_MODIFICACION = ?userId, ");
			sqlAgenciaExportadora.append("FEC_DT_MODIFICACION = systimestamp ");
			sqlAgenciaExportadora.append("WHERE NUM_DOSIER = ?dossierNumber AND NUM_ANYO = ?dossierYear");

			final Query queryAgenciaExportadora = getEntityManager().createNativeQuery(sqlAgenciaExportadora.toString());
			
			queryAgenciaExportadora.setParameter("isDownloaded", (input.getIsDownloaded().booleanValue()?"S":"N"));
			queryAgenciaExportadora.setParameter("dossierNumber", input.getDossierNumber());
			queryAgenciaExportadora.setParameter("dossierYear", input.getDossierYear());
			queryAgenciaExportadora.setParameter("agencyId", input.getAgencyId());
			queryAgenciaExportadora.setParameter("userId", input.getUserId());
			queryAgenciaExportadora.executeUpdate();
		} catch (Exception e) {
			throw new ApplicationException(e.getMessage());
		}
	}
	
	@Transactional
	private void actualizarEstadoDescargaDosierImp(InputDataDTO input) {
		try {
			// Agencia importadora
			final StringBuilder sqlAgenciaImportadora = new StringBuilder();
			sqlAgenciaImportadora.append("MERGE INTO D_DOSIER DO USING ( ");
			sqlAgenciaImportadora.append("SELECT COD_N_PROVEEDOR ");
			sqlAgenciaImportadora.append("FROM D_PROVEEDOR_R ");
			sqlAgenciaImportadora.append("WHERE COD_N_PROVEEDOR = ?agencyId OR COD_N_LEGACY_PROVEEDOR = ?agencyId ");
			sqlAgenciaImportadora.append(") PR ");
			sqlAgenciaImportadora.append("ON (DO.COD_V_AGENCIA_IMPORTACION = PR.COD_N_PROVEEDOR) ");
			sqlAgenciaImportadora.append("WHEN MATCHED THEN UPDATE SET ");
			sqlAgenciaImportadora.append("FEC_DT_DESCARGA_IMPORTADOR = DECODE(?isDownloaded, 'S', systimestamp, NULL), ");
			sqlAgenciaImportadora.append("COD_V_USUARIO_MODIFICACION = ?userId, ");
			sqlAgenciaImportadora.append("FEC_DT_MODIFICACION = systimestamp ");
			sqlAgenciaImportadora.append("WHERE NUM_DOSIER = ?dossierNumber AND NUM_ANYO = ?dossierYear ");

			final Query queryAgenciaImportadora = getEntityManager().createNativeQuery(sqlAgenciaImportadora.toString());

			queryAgenciaImportadora.setParameter("isDownloaded", (input.getIsDownloaded().booleanValue()?"S":"N"));
			queryAgenciaImportadora.setParameter("dossierNumber", input.getDossierNumber());
			queryAgenciaImportadora.setParameter("dossierYear", input.getDossierYear());
			queryAgenciaImportadora.setParameter("agencyId", input.getAgencyId());
			queryAgenciaImportadora.setParameter("userId", input.getUserId());
			queryAgenciaImportadora.executeUpdate();
		} catch (Exception e) {
			throw new ApplicationException(e.getMessage());
		}
	}		
	
	@Transactional
	private void actualizarEstadoDescargaDVExp(InputDataDTO input) {
		try {
			// Agencia exportadora
			final StringBuilder sqlAgenciaExportadora = new StringBuilder();
			
			sqlAgenciaExportadora.append("MERGE INTO O_DECLARACION_VALOR_CAB DV USING ( ");
			sqlAgenciaExportadora.append("SELECT NUM_DOSIER, NUM_ANYO ");
			sqlAgenciaExportadora.append("FROM D_DOSIER DO ");
			sqlAgenciaExportadora.append("LEFT JOIN D_PROVEEDOR_R P ON (P.COD_N_PROVEEDOR = DO.COD_V_AGENCIA_EXPORTACION) ");
			sqlAgenciaExportadora.append("WHERE (DO.COD_V_AGENCIA_EXPORTACION = ?agencyId OR P.COD_N_LEGACY_PROVEEDOR = ?agencyId) ");
			sqlAgenciaExportadora.append(") DD ");
			sqlAgenciaExportadora.append("ON (DV.NUM_DOSIER = DD.NUM_DOSIER AND DV.NUM_ANYO = DD.NUM_ANYO) ");
			sqlAgenciaExportadora.append("WHEN MATCHED THEN UPDATE ");
			sqlAgenciaExportadora.append("SET FEC_DT_DESCARGA_EXPORTADOR = DECODE(?isDownloadedSN, 'S', systimestamp, NULL), ");
			sqlAgenciaExportadora.append("COD_V_USR_MODIFICACION = ?userId ");
			sqlAgenciaExportadora.append("WHERE DV.NUM_DOSIER = ?dossierNumber AND DV.NUM_ANYO = ?dossierYear");			

			final Query queryAgenciaExportadora = getEntityManager().createNativeQuery(sqlAgenciaExportadora.toString());
			
			queryAgenciaExportadora.setParameter("isDownloadedSN", (input.getIsDownloaded().booleanValue()?"S":"N"));
			queryAgenciaExportadora.setParameter("dossierNumber", input.getDossierNumber());
			queryAgenciaExportadora.setParameter("dossierYear", input.getDossierYear());
			queryAgenciaExportadora.setParameter("agencyId", input.getAgencyId());
			queryAgenciaExportadora.setParameter("userId", input.getUserId());
			queryAgenciaExportadora.executeUpdate();
		} catch (Exception e) {
			throw new ApplicationException(e.getMessage());
		}
	}
	
	@Transactional
	private void actualizarEstadoDescargaDVImp(InputDataDTO input) {
		try {
			// Agencia importadora
			final StringBuilder sqlAgenciaImportadora = new StringBuilder();
			sqlAgenciaImportadora.append("MERGE INTO O_DECLARACION_VALOR_CAB DV USING ( ");
			sqlAgenciaImportadora.append("SELECT NUM_DOSIER, NUM_ANYO ");
			sqlAgenciaImportadora.append("FROM D_DOSIER DO ");
			sqlAgenciaImportadora.append("LEFT JOIN D_PROVEEDOR_R P ON (P.COD_N_PROVEEDOR = DO.COD_V_AGENCIA_IMPORTACION) ");
			sqlAgenciaImportadora.append("WHERE (DO.COD_V_AGENCIA_IMPORTACION = ?agencyId OR P.COD_N_LEGACY_PROVEEDOR = ?agencyId) ");
			sqlAgenciaImportadora.append(") DD ");
			sqlAgenciaImportadora.append("ON (DV.NUM_DOSIER = DD.NUM_DOSIER AND DV.NUM_ANYO = DD.NUM_ANYO) ");
			sqlAgenciaImportadora.append("WHEN MATCHED THEN UPDATE SET ");
			sqlAgenciaImportadora.append("FEC_DT_DESCARGA_IMPORTADOR = DECODE(?isDownloadedSN, 'S', systimestamp, NULL), ");
			sqlAgenciaImportadora.append("COD_V_USR_MODIFICACION = ?userId ");
			sqlAgenciaImportadora.append("WHERE DV.NUM_DOSIER = ?dossierNumber AND DV.NUM_ANYO = ?dossierYear");		

			final Query queryAgenciaImportadora = getEntityManager().createNativeQuery(sqlAgenciaImportadora.toString());

			queryAgenciaImportadora.setParameter("isDownloadedSN", (input.getIsDownloaded().booleanValue()?"S":"N"));
			queryAgenciaImportadora.setParameter("dossierNumber", input.getDossierNumber());
			queryAgenciaImportadora.setParameter("dossierYear", input.getDossierYear());
			queryAgenciaImportadora.setParameter("agencyId", input.getAgencyId());
			queryAgenciaImportadora.setParameter("userId", input.getUserId());
			queryAgenciaImportadora.executeUpdate();
		} catch (Exception e) {
			throw new ApplicationException(e.getMessage());
		}
	}			
	
}