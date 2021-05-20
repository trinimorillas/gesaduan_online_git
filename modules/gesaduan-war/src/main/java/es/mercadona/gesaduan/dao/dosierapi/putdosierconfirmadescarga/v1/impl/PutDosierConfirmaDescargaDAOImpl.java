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
import es.mercadona.gesaduan.dto.dosierapi.putdosierconfirmadescarga.v1.InputPutDossierConfirmDownloadDTO;
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
			sqlAgenciaExportadora.append("UPDATE D_DOSIER SET ");
			sqlAgenciaExportadora.append("FEC_DT_DESCARGA_EXPORTADOR = DECODE(?estaDescargado, 'S', SYSDATE, NULL), ");
			sqlAgenciaExportadora.append("COD_V_USUARIO_MODIFICACION = ?codigoUsuario, ");
			sqlAgenciaExportadora.append("FEC_DT_MODIFICACION = SYSDATE ");
			sqlAgenciaExportadora.append("WHERE NUM_DOSIER = ?numDosier ");
			sqlAgenciaExportadora.append("AND NUM_ANYO = ?anyoDosier ");
			sqlAgenciaExportadora.append("AND COD_V_AGENCIA_EXPORTACION = ?codigoAgencia");

			final Query queryAgenciaExportadora = getEntityManager().createNativeQuery(sqlAgenciaExportadora.toString());
			
			queryAgenciaExportadora.setParameter("estaDescargado", (input.getIsDownloaded().booleanValue()?"S":"N"));
			queryAgenciaExportadora.setParameter("numDosier", input.getDossierNumber());
			queryAgenciaExportadora.setParameter("anyoDosier", input.getDossierYear());
			queryAgenciaExportadora.setParameter("codigoAgencia", input.getAgencyId());
			queryAgenciaExportadora.setParameter("codigoUsuario", input.getUserId());
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
			sqlAgenciaImportadora.append("UPDATE D_DOSIER SET ");
			sqlAgenciaImportadora.append("FEC_DT_DESCARGA_IMPORTADOR = DECODE(?estaDescargado, 'S', SYSDATE, NULL), ");
			sqlAgenciaImportadora.append("COD_V_USUARIO_MODIFICACION = ?codigoUsuario, ");
			sqlAgenciaImportadora.append("FEC_DT_MODIFICACION = SYSDATE ");
			sqlAgenciaImportadora.append("WHERE NUM_DOSIER = ?numDosier ");
			sqlAgenciaImportadora.append("AND NUM_ANYO = ?anyoDosier ");
			sqlAgenciaImportadora.append("AND COD_V_AGENCIA_IMPORTACION = ?codigoAgencia");

			final Query queryAgenciaImportadora = getEntityManager().createNativeQuery(sqlAgenciaImportadora.toString());

			queryAgenciaImportadora.setParameter("estaDescargado", (input.getIsDownloaded().booleanValue()?"S":"N"));
			queryAgenciaImportadora.setParameter("numDosier", input.getDossierNumber());
			queryAgenciaImportadora.setParameter("anyoDosier", input.getDossierYear());
			queryAgenciaImportadora.setParameter("codigoAgencia", input.getAgencyId());
			queryAgenciaImportadora.setParameter("codigoUsuario", input.getUserId());
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
			
			sqlAgenciaExportadora.append("MERGE  INTO  O_DECLARACION_VALOR_CAB DV ");
			sqlAgenciaExportadora.append("USING ( ");
			sqlAgenciaExportadora.append("SELECT NUM_DOSIER,NUM_ANYO ");
			sqlAgenciaExportadora.append("FROM D_DOSIER  ");
			sqlAgenciaExportadora.append("WHERE COD_V_AGENCIA_EXPORTACION = ?codigoAgencia ");
			sqlAgenciaExportadora.append(") DD ");
			sqlAgenciaExportadora.append("ON (DV.NUM_DOSIER = DD.NUM_DOSIER AND DV.NUM_ANYO = DD.NUM_ANYO) ");
			sqlAgenciaExportadora.append("WHEN MATCHED ");
			sqlAgenciaExportadora.append("THEN ");
			sqlAgenciaExportadora.append("UPDATE  ");
			sqlAgenciaExportadora.append("SET FEC_DT_DESCARGA_EXPORTADOR= DECODE(?estaDescargado,'S',systimestamp,NULL)  ");
			sqlAgenciaExportadora.append(",COD_V_USR_MODIFICACION = ?codigoUsuario ");
			sqlAgenciaExportadora.append("WHERE ");
			sqlAgenciaExportadora.append("DV.NUM_DOSIER = ?numDosier AND ");
			sqlAgenciaExportadora.append("DV.NUM_ANYO = ?anyoDosier ");			

			final Query queryAgenciaExportadora = getEntityManager().createNativeQuery(sqlAgenciaExportadora.toString());
			
			queryAgenciaExportadora.setParameter("estaDescargado", (input.getIsDownloaded().booleanValue()?"S":"N"));
			queryAgenciaExportadora.setParameter("numDosier", input.getDossierNumber());
			queryAgenciaExportadora.setParameter("anyoDosier", input.getDossierYear());
			queryAgenciaExportadora.setParameter("codigoAgencia", input.getAgencyId());
			queryAgenciaExportadora.setParameter("codigoUsuario", input.getUserId());
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
			sqlAgenciaImportadora.append("MERGE  INTO O_DECLARACION_VALOR_CAB DV ");	
			sqlAgenciaImportadora.append("USING ( ");	
			sqlAgenciaImportadora.append("SELECT NUM_DOSIER,NUM_ANYO ");	
			sqlAgenciaImportadora.append("FROM D_DOSIER  ");	
			sqlAgenciaImportadora.append("WHERE COD_V_AGENCIA_IMPORTACION = ?codigoAgencia ");	
			sqlAgenciaImportadora.append(") DD ");	
			sqlAgenciaImportadora.append("ON (DV.NUM_DOSIER = DD.NUM_DOSIER AND DV.NUM_ANYO = DD.NUM_ANYO) ");	
			sqlAgenciaImportadora.append("WHEN MATCHED ");	
			sqlAgenciaImportadora.append("THEN ");	
			sqlAgenciaImportadora.append("UPDATE  ");	
			sqlAgenciaImportadora.append("SET FEC_DT_DESCARGA_IMPORTADOR = DECODE(?estaDescargado,'S',systimestamp,NULL)  ");	
			sqlAgenciaImportadora.append(",COD_V_USR_MODIFICACION = ?codigoUsuario ");	
			sqlAgenciaImportadora.append("WHERE ");	
			sqlAgenciaImportadora.append("DV.NUM_DOSIER = ?numDosier AND ");	
			sqlAgenciaImportadora.append("DV.NUM_ANYO = ?anyoDosier ");			

			final Query queryAgenciaImportadora = getEntityManager().createNativeQuery(sqlAgenciaImportadora.toString());

			queryAgenciaImportadora.setParameter("estaDescargado", (input.getIsDownloaded().booleanValue()?"S":"N"));
			queryAgenciaImportadora.setParameter("numDosier", input.getDossierNumber());
			queryAgenciaImportadora.setParameter("anyoDosier", input.getDossierYear());
			queryAgenciaImportadora.setParameter("codigoAgencia", input.getAgencyId());
			queryAgenciaImportadora.setParameter("codigoUsuario", input.getUserId());
			queryAgenciaImportadora.executeUpdate();			
			
		} catch (Exception e) {			
			throw new ApplicationException(e.getMessage());
		}

	}			
	
}