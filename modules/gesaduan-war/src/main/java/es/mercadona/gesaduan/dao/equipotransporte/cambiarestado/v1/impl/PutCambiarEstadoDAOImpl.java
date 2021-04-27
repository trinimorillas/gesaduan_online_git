package es.mercadona.gesaduan.dao.equipotransporte.cambiarestado.v1.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.gesaduan.dao.BaseDAO;
import es.mercadona.gesaduan.dao.equipotransporte.cambiarestado.v1.PutCambiarEstadoDAO;
import es.mercadona.gesaduan.dto.equipotransporte.cambiarestado.v1.EquipoDTO;
import es.mercadona.gesaduan.dto.equipotransporte.cambiarestado.v1.InputDatosCambiarEstadoDTO;
import es.mercadona.gesaduan.jpa.equipotransporte.v1.EquipoTransporteJPA;
import es.mercadona.gesaduan.common.Constantes;

public class PutCambiarEstadoDAOImpl extends BaseDAO<EquipoTransporteJPA> implements PutCambiarEstadoDAO {

	@Inject
	private org.slf4j.Logger logger;	
	
	@Override
	public void setEntityClass() {
		this.entityClass = EquipoTransporteJPA.class;		
	}
	
	@PersistenceContext
	private EntityManager entityM;
	
	private static final String NOMBRE_CLASE = "PutCambiarEstadoDAOImpl(GESADUAN)"; 
	
	@Override
	public List<EquipoDTO> comprobarPedidosSinValidar(List<EquipoDTO> input) {		
		List<EquipoDTO> equipos = null;
		
		try {		
			final StringBuilder sql = new StringBuilder();
			
			sql.append("SELECT ET.COD_N_EQUIPO ");
			sql.append("FROM D_EQUIPO_TRANSPORTE ET ");
			sql.append("INNER JOIN S_EQUIPO_CARGA EC ON (EC.COD_N_EQUIPO = ET.COD_N_EQUIPO) ");
			sql.append("INNER JOIN D_CARGA CA ON (CA.COD_V_CARGA = EC.COD_V_CARGA AND CA.COD_V_ALMACEN_ORIGEN = EC.COD_V_ALMACEN_ORIGEN) ");
			sql.append("WHERE ET.COD_N_EQUIPO IN (");
			
			for (int i = 0; i < input.size(); i++) {
				if (input.size()-1 == i) sql.append(""+input.get(i).getCodigoEquipo()+"");
				else sql.append(""+input.get(i).getCodigoEquipo()+", ");
			}
			sql.append(") AND CA.MCA_PEDIDOS_SIN_VALIDAR = 'S' ");
			sql.append("GROUP BY ET.COD_N_EQUIPO");
			
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			
			@SuppressWarnings("unchecked")
			List<BigDecimal> listado = query.getResultList();
			
			if (listado != null && !listado.isEmpty()) {
				equipos = new ArrayList<>();
				EquipoDTO equipo = null;
				
				for (BigDecimal tmp : listado) {
					equipo = new EquipoDTO();
					equipo.setCodigoEquipo(Long.parseLong(String.valueOf(tmp)));
					equipos.add(equipo);
				}
			}		
		} catch(Exception ex) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, NOMBRE_CLASE, "comprobarPedidosSinValidar", ex.getClass().getSimpleName(), ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}		
		
		return equipos;
	}
	
	@Override
	public List<EquipoDTO> comprobarEstadoPlanEmbarqueConfirmado(List<EquipoDTO> input) {		
		List<EquipoDTO> equipos = null;
		
		try {		
			final StringBuilder sql = new StringBuilder();
			
			sql.append("SELECT ET.COD_N_EQUIPO ");
			sql.append("FROM D_EQUIPO_TRANSPORTE ET ");
					sql.append("INNER JOIN D_PLAN_EMBARQUE PE ON (PE.COD_N_EMBARQUE = ET.COD_N_EMBARQUE) ");
					sql.append("WHERE ET.COD_N_EQUIPO IN (");
			
			for (int i = 0; i < input.size(); i++) {
				if (input.size()-1 == i) sql.append(""+input.get(i).getCodigoEquipo()+"");
				else sql.append(""+input.get(i).getCodigoEquipo()+", ");
			}
			sql.append(") AND PE.COD_N_ESTADO <> 2 ");
			sql.append("GROUP BY ET.COD_N_EQUIPO");
				
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			
			@SuppressWarnings("unchecked")
			List<BigDecimal> listado = query.getResultList();
			
			if (listado != null && !listado.isEmpty()) {
				equipos = new ArrayList<>();
				EquipoDTO equipo = null;
				
				for (BigDecimal tmp : listado) {
					equipo = new EquipoDTO();
					equipo.setCodigoEquipo(Long.parseLong(String.valueOf(tmp)));
					equipos.add(equipo);
				}
			}			
		} catch(Exception ex) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, NOMBRE_CLASE, "comprobarEstadoPlanEmbarqueConfirmado", ex.getClass().getSimpleName(), ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}					
		
		return equipos;
	}
	
	@Override
	public List<EquipoDTO> comprobarEstadoPlanEmbarqueFacturado(List<EquipoDTO> input) {		
		List<EquipoDTO> equipos = null;
		
		try {		
			final StringBuilder sql = new StringBuilder();
			
			sql.append("SELECT ET.COD_N_EQUIPO ");
			sql.append("FROM D_EQUIPO_TRANSPORTE ET ");
					sql.append("INNER JOIN D_PLAN_EMBARQUE PE ON (PE.COD_N_EMBARQUE = ET.COD_N_EMBARQUE) ");
					sql.append("WHERE ET.COD_N_EQUIPO IN (");
			
			for (int i = 0; i < input.size(); i++) {
				if (input.size()-1 == i) sql.append(""+input.get(i).getCodigoEquipo()+"");
				else sql.append(""+input.get(i).getCodigoEquipo()+", ");
			}
			sql.append(") AND PE.COD_N_ESTADO <> 3 ");
			sql.append("GROUP BY ET.COD_N_EQUIPO");
				
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			
			@SuppressWarnings("unchecked")
			List<BigDecimal> listado = query.getResultList();
			
			if (listado != null && !listado.isEmpty()) {
				equipos = new ArrayList<>();
				EquipoDTO equipo = null;
				
				for (BigDecimal tmp : listado) {
					equipo = new EquipoDTO();
					equipo.setCodigoEquipo(Long.parseLong(String.valueOf(tmp)));
					equipos.add(equipo);
				}
			}		
		} catch(Exception ex) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, NOMBRE_CLASE, "comprobarEstadoPlanEmbarqueFacturado", ex.getClass().getSimpleName(), ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}				
			
		return equipos;
	}
	
	@Transactional
	@Override
	public void actualizarEstados(InputDatosCambiarEstadoDTO datos) {		
		try {		
			List<EquipoDTO> equipos = datos.getDatos().getEquipo();
			final StringBuilder sql = new StringBuilder();
			
			sql.append("UPDATE D_EQUIPO_TRANSPORTE ET SET ET.COD_N_ESTADO = ?codigoEstado, ");
			sql.append("ET.FEC_DT_MODIFICACION = SYSDATE, ET.COD_V_USUARIO_MODIFICACION = ?codigoUsuario ");
			sql.append("WHERE ET.COD_N_EQUIPO IN (");
					
			for (int i = 0; i < equipos.size(); i++) {
				if (equipos.size()-1 == i) sql.append(""+equipos.get(i).getCodigoEquipo()+")");
				else sql.append(""+equipos.get(i).getCodigoEquipo()+", ");
			}
			
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			query.setParameter("codigoUsuario", datos.getMetadatos().getCodigoUsuario());
			query.setParameter("codigoEstado", datos.getDatos().getCodigoEstado());
			query.executeUpdate();
		
		} catch(Exception ex) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, NOMBRE_CLASE, "actualizarEstados", ex.getClass().getSimpleName(), ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}		
		
	}
	
	@Transactional
	@Override
	public void confirmarPlanEmbarque(InputDatosCambiarEstadoDTO datos) {		
		try {		
			List<EquipoDTO> input = datos.getDatos().getEquipo();
			final StringBuilder sql = new StringBuilder();
			
			sql.append("MERGE INTO D_PLAN_EMBARQUE PE0 USING (");
			sql.append("SELECT DISTINCT PE1.COD_N_EMBARQUE ");
			sql.append("FROM D_PLAN_EMBARQUE PE1 ");
			sql.append("INNER JOIN D_EQUIPO_TRANSPORTE ET1 ON (ET1.COD_N_EMBARQUE = PE1.COD_N_EMBARQUE AND ET1.COD_N_EQUIPO IN (");
			
			for (int i = 0; i < input.size(); i++) {
				if (input.size()-1 == i) sql.append(""+input.get(i).getCodigoEquipo()+") ");
				else sql.append(""+input.get(i).getCodigoEquipo()+", ");
			}
			
			sql.append("AND ET1.COD_N_ESTADO = 1)) PECA ON (PE0.COD_N_EMBARQUE = PECA.COD_N_EMBARQUE) ");
			sql.append("WHEN MATCHED THEN UPDATE ");
			sql.append("SET PE0.COD_N_ESTADO = 2, ");
			sql.append("PE0.FEC_DT_MODIFICACION = SYSDATE, ");
			sql.append("PE0.COD_V_USUARIO_MODIFICACION = ?codigoUsuario "); 
			sql.append("WHERE PE0.COD_N_ESTADO = 3 OR PE0.COD_N_ESTADO = 4");
			
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			query.setParameter("codigoUsuario", datos.getMetadatos().getCodigoUsuario()).executeUpdate();
			
		} catch(Exception ex) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, NOMBRE_CLASE, "confirmarPlanEmbarque", ex.getClass().getSimpleName(), ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}				
	}
	
	@Transactional
	@Override
	public void asignarCarga(InputDatosCambiarEstadoDTO datos) {		
		try {		
			List<EquipoDTO> input = datos.getDatos().getEquipo();
			final StringBuilder sql = new StringBuilder();
			
			sql.append("MERGE INTO D_CARGA CA0 USING (");
			sql.append("SELECT DISTINCT EC1.COD_V_CARGA, EC1.COD_V_ALMACEN_ORIGEN ");
			sql.append("FROM S_EQUIPO_CARGA EC1 ");
			sql.append("WHERE EC1.COD_N_EQUIPO IN (");
					
			for (int i = 0; i < input.size(); i++) {
				if (input.size()-1 == i) sql.append(""+input.get(i).getCodigoEquipo()+") ");
				else sql.append(""+input.get(i).getCodigoEquipo()+", ");
			}			
			
			sql.append(") PECA ");
			sql.append("ON (");
			sql.append("CA0.COD_V_CARGA = PECA.COD_V_CARGA AND ");
			sql.append("CA0.COD_V_ALMACEN_ORIGEN = PECA.COD_V_ALMACEN_ORIGEN");
			sql.append(") ");
			sql.append("WHEN MATCHED THEN UPDATE SET ");
			sql.append("CA0.COD_N_ESTADO = 3, ");
			sql.append("CA0.FEC_DT_MODIFICACION = SYSDATE, ");
			sql.append("CA0.COD_V_USUARIO_MODIFICACION = ?codigoUsuario ");
			sql.append("WHERE CA0.COD_N_ESTADO IN (4,6) ");
			
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			query.setParameter("codigoUsuario", datos.getMetadatos().getCodigoUsuario()).executeUpdate();			
		} catch(Exception ex) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, NOMBRE_CLASE,"asignarCarga",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}			
	}
	
	@Transactional
	@Override
	public void facturarPlanEmbarque(InputDatosCambiarEstadoDTO datos) {		
		try {		
			List<EquipoDTO> input = datos.getDatos().getEquipo();
			final StringBuilder sql = new StringBuilder();
			
			sql.append("MERGE INTO D_PLAN_EMBARQUE PE0 USING (");
			sql.append("SELECT DISTINCT PE1.COD_N_EMBARQUE ");
			sql.append("FROM D_PLAN_EMBARQUE PE1 ");
			sql.append("INNER JOIN D_EQUIPO_TRANSPORTE ET1 ON (ET1.COD_N_EMBARQUE = PE1.COD_N_EMBARQUE ");
			sql.append("AND ET1.COD_N_EQUIPO IN (");
							
			for (int i = 0; i < input.size(); i++) {
				if (input.size()-1 == i) sql.append(""+input.get(i).getCodigoEquipo()+") ");
				else sql.append(""+input.get(i).getCodigoEquipo()+", ");
			}
			
			sql.append("AND ET1.COD_N_ESTADO = 2) ");
			sql.append("WHERE NOT EXISTS (SELECT * FROM D_EQUIPO_TRANSPORTE ET2 WHERE ");
			sql.append("ET2.COD_N_EMBARQUE = PE1.COD_N_EMBARQUE AND ET2.COD_N_ESTADO <> 2 AND ET2.COD_N_ESTADO <> 3)");
			sql.append(") PECA ON (PE0.COD_N_EMBARQUE = PECA.COD_N_EMBARQUE) ");
			sql.append("WHEN MATCHED THEN UPDATE SET PE0.COD_N_ESTADO = 3, PE0.FEC_DT_MODIFICACION = SYSDATE, ");
			sql.append("PE0.COD_V_USUARIO_MODIFICACION = ?codigoUsuario WHERE PE0.COD_N_ESTADO = 2");
			
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			query.setParameter("codigoUsuario", datos.getMetadatos().getCodigoUsuario()).executeUpdate();			
		} catch(Exception ex) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, NOMBRE_CLASE, "facturarPlanEmbarque", ex.getClass().getSimpleName(), ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}			
	}
	
	@Transactional
	@Override
	public void facturarCarga(InputDatosCambiarEstadoDTO datos) {		
		try {		
			List<EquipoDTO> input = datos.getDatos().getEquipo();
			final StringBuilder sql = new StringBuilder();
			
			sql.append("MERGE INTO D_CARGA CA0 USING (");
			sql.append("SELECT DISTINCT EC1.COD_V_CARGA, EC1.COD_V_ALMACEN_ORIGEN ");
			sql.append("FROM S_EQUIPO_CARGA EC1 ");
			sql.append("WHERE EC1.COD_N_EQUIPO IN (");
			
			for (int i = 0; i < input.size(); i++) {
				if (input.size()-1 == i) sql.append(""+input.get(i).getCodigoEquipo()+") ");
				else sql.append(""+input.get(i).getCodigoEquipo()+", ");
			}			
					
			sql.append("AND NOT EXISTS (");
			sql.append("SELECT ET2.COD_N_EQUIPO ");
			sql.append("FROM D_EQUIPO_TRANSPORTE ET2 ");
			sql.append("INNER JOIN S_EQUIPO_CARGA EC2 ON (EC2.COD_N_EQUIPO = ET2.COD_N_EQUIPO) ");
			sql.append("WHERE ET2.COD_N_ESTADO = 1 ");
			sql.append("AND EC2.COD_V_CARGA = EC1.COD_V_CARGA ");
			sql.append("AND EC2.COD_V_ALMACEN_ORIGEN = EC1.COD_V_ALMACEN_ORIGEN");			
			sql.append(")");
			sql.append(") PECA ");
			sql.append("ON (");
			sql.append("CA0.COD_V_CARGA = PECA.COD_V_CARGA AND ");
			sql.append("CA0.COD_V_ALMACEN_ORIGEN = PECA.COD_V_ALMACEN_ORIGEN");
			sql.append(") ");
			sql.append("WHEN MATCHED THEN UPDATE SET ");
			sql.append("CA0.COD_N_ESTADO = 4, ");
			sql.append("CA0.FEC_DT_MODIFICACION = SYSDATE, ");
			sql.append("CA0.COD_V_USUARIO_MODIFICACION = ?codigoUsuario ");
			sql.append("WHERE CA0.COD_N_ESTADO = 3");			
						
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			query.setParameter("codigoUsuario", datos.getMetadatos().getCodigoUsuario()).executeUpdate();
			
		} catch(Exception ex) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, NOMBRE_CLASE, "facturarCarga", ex.getClass().getSimpleName(), ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}	
		 
	}
	
	@Transactional
	@Override
	public void cargarPlanEmbarque(InputDatosCambiarEstadoDTO datos) {		
		try {		
			List<EquipoDTO> input = datos.getDatos().getEquipo();
			final StringBuilder sql = new StringBuilder();
			
			sql.append("MERGE INTO D_PLAN_EMBARQUE PE0 USING (");
			sql.append("SELECT DISTINCT PE1.COD_N_EMBARQUE ");
			sql.append("FROM D_PLAN_EMBARQUE PE1 ");
			sql.append("INNER JOIN D_EQUIPO_TRANSPORTE ET1 ON (ET1.COD_N_EMBARQUE = PE1.COD_N_EMBARQUE ");
			sql.append("AND ET1.COD_N_EQUIPO IN (");
							
			for (int i = 0; i < input.size(); i++) {
				if (input.size()-1 == i) sql.append(""+input.get(i).getCodigoEquipo()+") ");
				else sql.append(""+input.get(i).getCodigoEquipo()+", ");
			}
			
			sql.append("AND ET1.COD_N_ESTADO = 3) ");
			sql.append("WHERE NOT EXISTS (SELECT * FROM D_EQUIPO_TRANSPORTE ET2 WHERE ");
			sql.append("ET2.COD_N_EMBARQUE = PE1.COD_N_EMBARQUE AND ET2.COD_N_ESTADO <> 3) ");
			sql.append(") PECA ON (PE0.COD_N_EMBARQUE = PECA.COD_N_EMBARQUE) ");
			sql.append("WHEN MATCHED THEN UPDATE SET PE0.COD_N_ESTADO = 4, PE0.FEC_DT_MODIFICACION = SYSDATE, ");
			sql.append("PE0.COD_V_USUARIO_MODIFICACION = ?codigoUsuario WHERE PE0.COD_N_ESTADO = 3");
			
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			query.setParameter("codigoUsuario", datos.getMetadatos().getCodigoUsuario()).executeUpdate();			
		} catch(Exception ex) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, NOMBRE_CLASE, "cargarPlanEmbarque", ex.getClass().getSimpleName(), ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}
		
	}
	
	@Transactional
	@Override
	public void cargarCarga(InputDatosCambiarEstadoDTO datos) {		
		try {		
			List<EquipoDTO> input = datos.getDatos().getEquipo();
			final StringBuilder sql = new StringBuilder();
			
			sql.append("MERGE INTO D_CARGA CA0 ");
			sql.append("USING (");
			sql.append("SELECT DISTINCT EC1.COD_V_CARGA,EC1.COD_V_ALMACEN_ORIGEN ");
			sql.append("FROM S_EQUIPO_CARGA EC1 ");
			sql.append("WHERE EC1.COD_N_EQUIPO IN (");
				
			for (int i = 0; i < input.size(); i++) {
				if (input.size()-1 == i) sql.append(""+input.get(i).getCodigoEquipo()+") ");
				else sql.append(""+input.get(i).getCodigoEquipo()+", ");
			}				
				
			sql.append("AND NOT EXISTS (");
			sql.append("SELECT ET2.COD_N_EQUIPO ");
			sql.append("FROM D_EQUIPO_TRANSPORTE ET2 ");
			sql.append("INNER JOIN S_EQUIPO_CARGA EC2 ON (EC2.COD_N_EQUIPO = ET2.COD_N_EQUIPO) ");
			sql.append("WHERE ET2.COD_N_ESTADO <> 3 ");
			sql.append("AND EC2.COD_V_CARGA = EC1.COD_V_CARGA ");
			sql.append("AND EC2.COD_V_ALMACEN_ORIGEN = EC1.COD_V_ALMACEN_ORIGEN ");			
			sql.append(")");
			sql.append(") PECA ");
			sql.append("ON (");
			sql.append("CA0.COD_V_CARGA = PECA.COD_V_CARGA AND "); 
			sql.append("CA0.COD_V_ALMACEN_ORIGEN = PECA.COD_V_ALMACEN_ORIGEN ");
			sql.append(") ");
			sql.append("WHEN MATCHED THEN UPDATE SET ");
			sql.append("CA0.COD_N_ESTADO = 6, ");
			sql.append("CA0.FEC_DT_MODIFICACION = SYSDATE, ");
			sql.append("CA0.COD_V_USUARIO_MODIFICACION = ?codigoUsuario "); 
			sql.append("WHERE CA0.COD_N_ESTADO = 4");			
			
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			query.setParameter("codigoUsuario", datos.getMetadatos().getCodigoUsuario()).executeUpdate();		
		} catch(Exception ex) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, NOMBRE_CLASE, "cargarCarga", ex.getClass().getSimpleName(), ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}		
	}
	
	@Override
	public List<EquipoDTO> comprobarEquiposDosierGenerado(List<EquipoDTO> input) {
		List<EquipoDTO> equipos = null;
		
		try {		
			final StringBuilder sql = new StringBuilder();
			
			sql.append("SELECT DISTINCT DE.COD_N_EQUIPO ");
			sql.append("FROM S_DOSIER_EQUIPO DE ");
			sql.append("INNER JOIN D_DOSIER DO ON (DE.NUM_ANYO = DO.NUM_ANYO AND DE.NUM_DOSIER = DO.NUM_DOSIER) ");
			sql.append("WHERE DE.COD_N_EQUIPO IN (");
			
			for (int i = 0; i < input.size(); i++) {
				if (input.size()-1 == i) sql.append(""+input.get(i).getCodigoEquipo()+"");
				else sql.append(""+input.get(i).getCodigoEquipo()+", ");
			}
			sql.append(") AND DO.COD_N_ESTADO = 1 ");
				
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			
			@SuppressWarnings("unchecked")
			List<BigDecimal> listado = query.getResultList();
			
			if (listado != null && !listado.isEmpty()) {
				equipos = new ArrayList<>();
				EquipoDTO equipo = null;
				
				for (BigDecimal tmp : listado) {
					equipo = new EquipoDTO();
					equipo.setCodigoEquipo(Long.parseLong(String.valueOf(tmp)));
					equipos.add(equipo);
				}
			}		
		} catch(Exception ex) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, NOMBRE_CLASE, "comprobarPedidosSinValidar", ex.getClass().getSimpleName(), ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}		
		
		return equipos;
	}

}