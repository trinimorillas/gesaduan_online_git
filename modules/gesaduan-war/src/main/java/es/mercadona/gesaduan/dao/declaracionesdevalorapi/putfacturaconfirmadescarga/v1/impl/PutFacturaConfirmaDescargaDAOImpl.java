package es.mercadona.gesaduan.dao.declaracionesdevalorapi.putfacturaconfirmadescarga.v1.impl;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.fwk.data.DaoBaseImpl;
import es.mercadona.gesaduan.dao.declaracionesdevalorapi.putfacturaconfirmadescarga.v1.PutFacturaConfirmaDescargaDAO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.putfacturaconfirmadescarga.v1.InputPutFacturaConfirmaDescargaDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.putfacturaconfirmadescarga.v1.restfull.OutputDatosDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.putfacturaconfirmadescarga.v1.restfull.OutputPutFacturaConfirmaDescargaDTO;
import es.mercadona.gesaduan.jpa.declaracionesdevalor.postdv.v1.DeclaracionesDeValorPostJPA;
import es.mercadona.gesaduan.jpa.declaracionesdevalor.postdv.v1.DeclaracionesDeValorPostPK;

@Stateless
public class PutFacturaConfirmaDescargaDAOImpl extends DaoBaseImpl<DeclaracionesDeValorPostPK, DeclaracionesDeValorPostJPA> implements PutFacturaConfirmaDescargaDAO {

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
	public OutputPutFacturaConfirmaDescargaDTO actualizarEstadoDescarga(InputPutFacturaConfirmaDescargaDTO input) {
		OutputPutFacturaConfirmaDescargaDTO result = null;

		try {
			final StringBuilder sqlFactura = new StringBuilder();
			
			String codigoFactura = input.getDatos().getCodigoFactura();
			String[] factura = codigoFactura.split("-");
			String numFactura = factura[0];
			String anyoFactura = factura[1];
			String version = factura[2];
			String codigoProveedor = input.getDatos().getCodigoProveedor();
			String codigoUsuario = input.getMetadatos().getCodigoUsuario();
			Boolean estaDescargado = input.getDatos().getEstaDescargado();

			sqlFactura.append("SELECT COD_N_PROVEEDOR ");
			sqlFactura.append("FROM O_DECLARACION_VALOR_CAB DV ");
			sqlFactura.append("WHERE DV.COD_N_DECLARACION_VALOR = ?numFactura ");
			sqlFactura.append("AND DV.NUM_ANYO = ?anyoFactura ");
			sqlFactura.append("AND DV.COD_N_VERSION = ?version");

			final Query queryFactura = getEntityManager().createNativeQuery(sqlFactura.toString());

			queryFactura.setParameter("numFactura", numFactura);
			queryFactura.setParameter("anyoFactura", anyoFactura);
			queryFactura.setParameter("version", version);
			Integer resultado = queryFactura.executeUpdate();

			if(resultado == 1) {
				final StringBuilder sqlExisteProveedor = new StringBuilder();
				sqlExisteProveedor.append("UPDATE O_DECLARACION_VALOR_CAB SET ");
				sqlExisteProveedor.append("MCA_DESCARGA = ?estaDescargado, ");
				sqlExisteProveedor.append("FEC_DT_DESCARGA = SYSDATE, ");
				sqlExisteProveedor.append("COD_V_USR_MODIFICACION = ?codigoUsuario ");
				sqlExisteProveedor.append("WHERE COD_N_DECLARACION_VALOR = ?numFactura ");
				sqlExisteProveedor.append("AND NUM_ANYO = ?anyoFactura ");
				sqlExisteProveedor.append("AND COD_N_VERSION = ?version");
				     
				final Query queryExisteProveedor = getEntityManager().createNativeQuery(sqlExisteProveedor.toString());

				queryExisteProveedor.setParameter("estaDescargado", estaDescargado);
				queryExisteProveedor.setParameter("codigoUsuario", codigoUsuario);
				queryExisteProveedor.setParameter("numFactura", numFactura);
				queryExisteProveedor.setParameter("anyoFactura", anyoFactura);
				queryExisteProveedor.setParameter("version", version);
				queryExisteProveedor.executeUpdate();
			} else {
				// PROVEEDOR EXPORTADOR
				final StringBuilder sqlProveedorExportador = new StringBuilder();
				sqlProveedorExportador.append("MERGE O_DECLARACION_VALOR_CAB DV USING (");
				sqlProveedorExportador.append("SELECT NUM_DOSIER, NUM_ANYO ");
				sqlProveedorExportador.append("FROM D_DOSIER ");
				sqlProveedorExportador.append("WHERE COD_V_AGENCIA_EXPORTACION = ?codProveedor) DD ");
				sqlProveedorExportador.append("ON (DV.NUM_DOSIER = DD.NUM_DOSIER AND DV.NUM_ANYO = DD.NUM_ANYO) ");
				sqlProveedorExportador.append("WHEN MATCHED THEN UPDATE SET ");
				sqlProveedorExportador.append("FEC_DT_DESCARGA_EXPORTADOR = DECODE(?estaDescargado, true, SYSDATE, NULL), ");
				sqlProveedorExportador.append("COD_V_USR_MODIFICACION = ?codigoUsuario ");
				sqlProveedorExportador.append("WHERE COD_N_DECLARACION_VALOR = ?numFactura AND NUM_ANYO = ?anyoFactura AND COD_N_VERSION = ?version");
				     
				final Query queryProveedorExportador = getEntityManager().createNativeQuery(sqlProveedorExportador.toString());
				
				queryProveedorExportador.setParameter("codProveedor", codigoProveedor);
				queryProveedorExportador.setParameter("estaDescargado", estaDescargado);
				queryProveedorExportador.setParameter("codigoUsuario", codigoUsuario);
				queryProveedorExportador.setParameter("numFactura", numFactura);
				queryProveedorExportador.setParameter("anyoFactura", anyoFactura);
				queryProveedorExportador.setParameter("version", version);
				
				queryProveedorExportador.executeUpdate();
				
				// PROVEEDOR IMPORTADOR
				final StringBuilder sqlProveedorImportador = new StringBuilder();
				sqlProveedorImportador.append("MERGE O_DECLARACION_VALOR_CAB DV USING (");
				sqlProveedorImportador.append("SELECT NUM_DOSIER, NUM_ANYO ");
				sqlProveedorImportador.append("FROM D_DOSIER ");
				sqlProveedorImportador.append("WHERE COD_V_AGENCIA_IMPORTACION = ?codProveedor) DD ");
				sqlProveedorImportador.append("ON (DV.NUM_DOSIER = DD.NUM_DOSIER AND DV.NUM_ANYO = DD.NUM_ANYO) ");
				sqlProveedorImportador.append("WHEN MATCHED THEN UPDATE SET ");
				sqlProveedorImportador.append("FEC_DT_DESCARGA_IMPORTADOR = DECODE(?estaDescargado, true, SYSDATE, NULL), ");
				sqlProveedorImportador.append("COD_V_USR_MODIFICACION = ?codigoUsuario ");
				sqlProveedorImportador.append("WHERE COD_N_DECLARACION_VALOR = ?numFactura AND NUM_ANYO = ?anyoFactura AND COD_N_VERSION = ?version");
				     
				final Query queryProveedorImportador = getEntityManager().createNativeQuery(sqlProveedorImportador.toString());
				
				queryProveedorImportador.setParameter("codProveedor", codigoProveedor);
				queryProveedorImportador.setParameter("estaDescargado", estaDescargado);
				queryProveedorImportador.setParameter("codigoUsuario", codigoUsuario);
				queryProveedorImportador.setParameter("numFactura", numFactura);
				queryProveedorImportador.setParameter("anyoFactura", anyoFactura);
				queryProveedorImportador.setParameter("version", version);
				
				queryProveedorImportador.executeUpdate();
			}
			
			result = new OutputPutFacturaConfirmaDescargaDTO();
			OutputDatosDTO datosSalida = new OutputDatosDTO();
			datosSalida.setNumFactura(Long.parseLong(numFactura));
			datosSalida.setAnyoFactura(Integer.parseInt(anyoFactura));
			datosSalida.setVersion(version);
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