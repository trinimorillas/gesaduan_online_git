package es.mercadona.gesaduan.dao.proveedores.putproveedores.v1.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import es.mercadona.fwk.auth.SecurityService;
import es.mercadona.fwk.core.exceptions.ExceptionUtils;
import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.fwk.data.DaoBaseImpl;
import es.mercadona.gesaduan.dao.proveedores.putproveedores.v1.PutProveedoresDAO;
import es.mercadona.gesaduan.dto.proveedores.putproveedores.v1.restfull.InputPutProveedores;
import es.mercadona.gesaduan.dto.proveedores.putproveedores.v1.restfull.PutProveedor;
import es.mercadona.gesaduan.jpa.proveedores.getproveedores.v1.ProveedoresJPA;

public class PutProveedoresDAOImpl extends DaoBaseImpl<Integer, ProveedoresJPA> implements PutProveedoresDAO{
	
	@Inject
	private org.slf4j.Logger logger;
	
	@Inject
	private SecurityService securityService;

	@Transactional
	@Override
	public void activarProveedores(InputPutProveedores input) {
		
		try {
			String usuarioModificacion = input.getMetadatos().getCodigoUsuario();
			Date fechaActual = new Date();
			String marcaAduana = null;
			String marcaMuelle = null;
			
			if(input.getDatos().getMarcaAgenteAduana() != null) {
				if(input.getDatos().getMarcaAgenteAduana()) {
					marcaAduana = "S";
				}else {
					marcaAduana = "N";
				}
			}
			
			if(input.getDatos().getMarcaCompraSobreMuelle() != null) {
				if(input.getDatos().getMarcaCompraSobreMuelle()) {
					marcaMuelle = "S";
				}else {
					marcaMuelle = "N";
				}
			}
			
			List<String> proveedores = new ArrayList<>();
			
			List<PutProveedor> listaProveedores = input.getDatos().getProveedores();
			
			if (listaProveedores != null && !listaProveedores.isEmpty()) {
				for (PutProveedor tmp : listaProveedores) {
					String codigoProveedor = tmp.getCodigo();
					proveedores.add(codigoProveedor);
					/* Si existen relaciones con el proveedor que se va a desactivar como marca sobre el muelle, se dan por finalizadas */
					if(existeRelacion(codigoProveedor) && marcaMuelle.equals("N")) {
						finalizarRelacion(codigoProveedor);
					}
				}
			}
			
			String sql;
			
			sql = "UPDATE ProveedoresJPA prov ";
			sql += " SET ";
			if(marcaAduana != null) { sql += " prov.mcaAgente = :marcaAduana "; }
			if(marcaAduana != null && marcaMuelle != null) { sql += " , prov.mcaActivoCsm = :marcaMuelle "; }
			if(marcaMuelle != null && marcaAduana == null) { sql += " prov.mcaActivoCsm = :marcaMuelle "; }
			sql += " ,prov.codigoModificacion = :usuarioModificacion, prov.fechaActivacion = :fechaActual, prov.fechaModificacion = :fechaActual ";
			sql += " WHERE prov.codProveedor IN :proveedores";
			
			Query query = entityM.createQuery(sql);
			
			if(marcaAduana != null) { query.setParameter("marcaAduana", marcaAduana); }
			if(marcaMuelle != null) { query.setParameter("marcaMuelle", marcaMuelle); }
			query.setParameter("usuarioModificacion", usuarioModificacion.toUpperCase());
			query.setParameter("fechaActual", fechaActual);
			query.setParameter("proveedores", proveedores);
			
			query.executeUpdate();
			
		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","PutProveedoresDAOImpl(GESADUAN)","activarProveedores",e.getClass().getSimpleName(),e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}
		
		
	}
	
	private boolean existeRelacion(String codigoProveedor) {
		
		try {
		
			BigDecimal result = (BigDecimal) getEntityManager().createNativeQuery("SELECT COUNT(*) FROM S_RELACION_PROVEEDOR_R "
					+ "WHERE (COD_N_AGENCIA_ADUANA = ?codigoProveedor OR COD_N_PROVEEDOR = ?codigoProveedor) "
					+ "AND (FEC_D_FIN IS NULL OR TRUNC(FEC_D_FIN) <= TRUNC(SYSDATE))")
					.setParameter("codigoProveedor", codigoProveedor).getSingleResult();
			if(result != null) {
				return true;
			}else {
				return false;
			}
			
		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","PutProveedoresDAOImpl(GESADUAN)","existeRelacion",e.getClass().getSimpleName(),e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}			
			
	}
	
	private void finalizarRelacion(String codigoProveedor) {
		
		try {
		
			getEntityManager().createNativeQuery("UPDATE S_RELACION_PROVEEDOR_R SET FEC_D_FIN = TRUNC(SYSDATE) "
					+ "WHERE (COD_N_AGENCIA_ADUANA = ?codigoProveedor OR COD_N_PROVEEDOR = ?codigoProveedor) "
					+ "AND (FEC_D_FIN IS NULL OR TRUNC(FEC_D_FIN) <= TRUNC(SYSDATE))")
					.setParameter("codigoProveedor", codigoProveedor).executeUpdate();
			
		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","PutProveedoresDAOImpl(GESADUAN)","finalizarRelacion",e.getClass().getSimpleName(),e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}				
	}
	
	/*
	  private void establecerSalidaError(Exception exception, String metodo, String codError) {

		    String login = this.securityService.getPrincipal().getLogin();
		    
		    this.logger.error("Error ejecutando la clase: PutProveedoresDAOImpl",
		        new Object[] { metodo, login, ExceptionUtils.getStackTrace(exception) });
	  }
	  */

	@Override
	protected EntityManager getEntityManager() {
			return this.entityM;
	}

	@Override
	public void setEntityClass() {
		entityClass = ProveedoresJPA.class;
	}
	
	@PersistenceContext
	private EntityManager entityM;

}
