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
			String numeroDosier = input.getDossierNumber();
			String anyoDosier = input.getDossierYear();			
			String codigoAgencia = input.getAgencyId();
			String codigoUsuario = input.getUserId();
			Boolean estaDescargado = input.getIsDownloaded();
			
			String descargado;
			if (estaDescargado) {
				descargado = "S";
			} else {
				descargado = "N";
			}
			
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
			
			queryAgenciaExportadora.setParameter("estaDescargado", descargado);
			queryAgenciaExportadora.setParameter("numDosier", numeroDosier);
			queryAgenciaExportadora.setParameter("anyoDosier", anyoDosier);
			queryAgenciaExportadora.setParameter("codigoAgencia", codigoAgencia);
			queryAgenciaExportadora.setParameter("codigoUsuario", codigoUsuario);
			queryAgenciaExportadora.executeUpdate();
			
			
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

			queryAgenciaImportadora.setParameter("estaDescargado", descargado);
			queryAgenciaImportadora.setParameter("numDosier", numeroDosier);
			queryAgenciaImportadora.setParameter("anyoDosier", anyoDosier);
			queryAgenciaImportadora.setParameter("codigoAgencia", codigoAgencia);
			queryAgenciaExportadora.setParameter("codigoUsuario", codigoUsuario);
			queryAgenciaImportadora.executeUpdate();			
			
			result = new OutputPutDosierConfirmaDescargaDTO();
			OutputDatosDTO datosSalida = new OutputDatosDTO();
			datosSalida.setNumDosier(Long.parseLong(numeroDosier));
			datosSalida.setAnyoDosier(Integer.parseInt(anyoDosier));
			result.setDatos(datosSalida);
			Map<String, String> metadata = new HashMap<>();
			metadata.put("userId", codigoUsuario);
			// metadata.put("locale", input.getMetadata().getLocale());
			result.setMetadatos(metadata);
		} catch (Exception e) {			
			throw new ApplicationException(e.getMessage());
		}
		
		return result;
	}	

}