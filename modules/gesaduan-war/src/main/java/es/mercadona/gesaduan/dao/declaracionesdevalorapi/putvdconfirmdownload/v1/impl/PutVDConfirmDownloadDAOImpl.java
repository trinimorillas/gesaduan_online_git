package es.mercadona.gesaduan.dao.declaracionesdevalorapi.putvdconfirmdownload.v1.impl;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.fwk.data.DaoBaseImpl;
import es.mercadona.gesaduan.dao.declaracionesdevalorapi.putvdconfirmdownload.v1.PutVDConfirmDownloadDAO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.putvdconfirmdownload.v1.InputDataDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.putvdconfirmdownload.v1.restfull.OutputDataDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.putvdconfirmdownload.v1.restfull.OutputPutVDConfirmDownloadDTO;
import es.mercadona.gesaduan.jpa.declaracionesdevalor.postdv.v1.DeclaracionesDeValorPostJPA;
import es.mercadona.gesaduan.jpa.declaracionesdevalor.postdv.v1.DeclaracionesDeValorPostPK;

@Stateless
public class PutVDConfirmDownloadDAOImpl extends DaoBaseImpl<DeclaracionesDeValorPostPK, DeclaracionesDeValorPostJPA> implements PutVDConfirmDownloadDAO {

	@PersistenceContext
	private EntityManager entityM;
	
	@Override
	public void setEntityClass() {
		this.entityClass = DeclaracionesDeValorPostJPA.class;
	}
	
	@Override
	protected EntityManager getEntityManager() {		
		return this.entityM;
	}

	@Transactional
	@Override
	public OutputPutVDConfirmDownloadDTO actualizarEstadoDescarga(InputDataDTO input) {
		OutputPutVDConfirmDownloadDTO result = null;

		try {
			final StringBuilder sqlFactura = new StringBuilder();
			
			Boolean estaDescargado = input.getIsDownloaded();
			
			String descargado;
			if (estaDescargado) {
				descargado = "S";
			} else {
				descargado = "N";
			}

			sqlFactura.append("SELECT COD_N_PROVEEDOR ");
			sqlFactura.append("FROM O_DECLARACION_VALOR_CAB DV ");
			sqlFactura.append("WHERE DV.COD_N_DECLARACION_VALOR = ?valueDeclarationNumber ");
			sqlFactura.append("AND DV.NUM_ANYO = ?valueDeclarationYear ");
			sqlFactura.append("AND DV.COD_N_VERSION = ?valueDeclarationVersion");

			final Query queryFactura = getEntityManager().createNativeQuery(sqlFactura.toString());

			queryFactura.setParameter("valueDeclarationNumber", input.getValueDeclarationNumber());
			queryFactura.setParameter("valueDeclarationYear", input.getValueDeclarationYear());
			queryFactura.setParameter("valueDeclarationVersion", input.getValueDeclarationVersion());
			Integer resultado = queryFactura.executeUpdate();

			if(resultado == 1) {
				final StringBuilder sqlExisteProveedor = new StringBuilder();
				sqlExisteProveedor.append("UPDATE O_DECLARACION_VALOR_CAB SET ");
				sqlExisteProveedor.append("MCA_DESCARGA = ?isDownloaded, ");
				sqlExisteProveedor.append("FEC_DT_DESCARGA = SYSDATE, ");
				sqlExisteProveedor.append("COD_V_USR_MODIFICACION = ?userId ");
				sqlExisteProveedor.append("WHERE COD_N_DECLARACION_VALOR = ?valueDeclarationNumber ");
				sqlExisteProveedor.append("AND NUM_ANYO = ?valueDeclarationYear ");
				sqlExisteProveedor.append("AND COD_N_VERSION = ?valueDeclarationVersion");
				     
				final Query queryExisteProveedor = getEntityManager().createNativeQuery(sqlExisteProveedor.toString());

				queryExisteProveedor.setParameter("isDownloaded", descargado);
				queryExisteProveedor.setParameter("userId", input.getUserId());
				queryExisteProveedor.setParameter("valueDeclarationNumber", input.getValueDeclarationNumber());
				queryExisteProveedor.setParameter("valueDeclarationYear", input.getValueDeclarationYear());
				queryExisteProveedor.setParameter("valueDeclarationVersion", input.getValueDeclarationVersion());
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
				
				queryProveedorExportador.setParameter("supplierId", input.getSupplierId());
				queryProveedorExportador.setParameter("isDownloaded", descargado);
				queryProveedorExportador.setParameter("userId", input.getUserId());
				queryProveedorExportador.setParameter("valueDeclarationNumber", input.getValueDeclarationNumber());
				queryProveedorExportador.setParameter("valueDeclarationYear", input.getValueDeclarationYear());
				queryProveedorExportador.setParameter("valueDeclarationVersion", input.getValueDeclarationVersion());
				
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
				
				queryProveedorImportador.setParameter("supplierId", input.getSupplierId());
				queryProveedorImportador.setParameter("isDownloaded", descargado);
				queryProveedorImportador.setParameter("userId", input.getUserId());
				queryProveedorImportador.setParameter("valueDeclarationNumber", input.getValueDeclarationNumber());
				queryProveedorImportador.setParameter("valueDeclarationYear", input.getValueDeclarationYear());
				queryProveedorImportador.setParameter("valueDeclarationVersion", input.getValueDeclarationVersion());
				
				queryProveedorImportador.executeUpdate();
			}
			
			result = new OutputPutVDConfirmDownloadDTO();
			OutputDataDTO datosSalida = new OutputDataDTO();
			datosSalida.setNumFactura(Long.parseLong(input.getValueDeclarationNumber()));
			datosSalida.setAnyoFactura(Integer.parseInt(input.getValueDeclarationYear()));
			datosSalida.setVersion(input.getValueDeclarationVersion());
			result.setDatos(datosSalida);
			Map<String, String> metadatos = new HashMap<>();
			metadatos.put("userId", input.getUserId());
			metadatos.put("locale", input.getLocale());
			result.setMetadatos(metadatos);
		} catch (Exception e) {			
			throw new ApplicationException(e.getMessage());
		}
		
		return result;
	}	

}