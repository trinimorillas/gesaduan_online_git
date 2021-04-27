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
import es.mercadona.gesaduan.dto.dosierapi.putdosierconfirmadescarga.v1.InputPutDosierConfirmaDescargaDTO;
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
	public OutputPutDosierConfirmaDescargaDTO actualizarEstadoDescarga(InputPutDosierConfirmaDescargaDTO input) {
		OutputPutDosierConfirmaDescargaDTO result = null;

		try {
			String codigoDosier = input.getDatos().getCodigoDosier();
			String[] dosier = codigoDosier.split("-");
			String numDosier = dosier[0];
			String anyoDosier = dosier[1];
			String codigoAgencia = input.getDatos().getCodigoAgencia();
			String codigoUsuario = input.getMetadatos().getCodigoUsuario();
			Boolean estaDescargado = input.getDatos().getEstaDescargado();			
			
			// Agencia exportadora
			final StringBuilder sqlAgenciaExportadora = new StringBuilder();
			sqlAgenciaExportadora.append("UPDATE D_DOSIER SET ");
			sqlAgenciaExportadora.append("FEC_DT_DESCARGA_EXPORTADOR = DECODE(?estaDescargado, true, SYSDATE, NULL) ");
			sqlAgenciaExportadora.append("COD_V_USUARIO_MODIFICACION = ?codigoUsuario, ");
			sqlAgenciaExportadora.append("FEC_DT_MODIFICACION = SYSDATE ");
			sqlAgenciaExportadora.append("WHERE NUM_DOSIER = ?numDosier ");
			sqlAgenciaExportadora.append("AND NUM_ANYO = ?anyoDosier ");
			sqlAgenciaExportadora.append("COD_V_AGENCIA_EXPORTACION = ?codigoAgencia");

			final Query queryAgenciaExportadora = getEntityManager().createNativeQuery(sqlAgenciaExportadora.toString());
			
			queryAgenciaExportadora.setParameter("estaDescargado", estaDescargado);
			queryAgenciaExportadora.setParameter("numDosier", numDosier);
			queryAgenciaExportadora.setParameter("anyoDosier", anyoDosier);
			queryAgenciaExportadora.setParameter("codigoAgencia", codigoAgencia);
			queryAgenciaExportadora.executeUpdate();
			
			
			// Agencia importadora
			final StringBuilder sqlAgenciaImportadora = new StringBuilder();
			sqlAgenciaImportadora.append("UPDATE D_DOSIER SET ");
			sqlAgenciaImportadora.append("FEC_DT_DESCARGA_IMPORTADOR = DECODE(?estaDescargado, true, SYSDATE, NULL), ");
			sqlAgenciaImportadora.append("COD_V_USUARIO_MODIFICACION = ?codigoUsuario, ");
			sqlAgenciaImportadora.append("FEC_DT_MODIFICACION = SYSDATE ");
			sqlAgenciaImportadora.append("WHERE NUM_DOSIER = ?numDosier ");
			sqlAgenciaImportadora.append("AND NUM_ANYO = ?anyoDosier ");
			sqlAgenciaImportadora.append("COD_V_AGENCIA_IMPORTACION = ?codigoAgencia");

			final Query queryAgenciaImportadora = getEntityManager().createNativeQuery(sqlAgenciaImportadora.toString());

			queryAgenciaImportadora.setParameter("numDosier", numDosier);
			queryAgenciaImportadora.setParameter("anyoDosier", anyoDosier);
			queryAgenciaImportadora.setParameter("codigoAgencia", codigoAgencia);
			queryAgenciaImportadora.executeUpdate();			
			
			result = new OutputPutDosierConfirmaDescargaDTO();
			OutputDatosDTO datosSalida = new OutputDatosDTO();
			datosSalida.setNumDosier(Long.parseLong(numDosier));
			datosSalida.setAnyoDosier(Integer.parseInt(anyoDosier));
			result.setDatos(datosSalida);
			Map<String, String> metadatos = new HashMap<>();
			metadatos.put("codigoUsuario", codigoUsuario);
			metadatos.put("locale", input.getMetadatos().getLocale());
			result.setMetadatos(metadatos);
		} catch (Exception e) {			
			throw new ApplicationException(e.getMessage());
		}
		
		return result;
	}	

}