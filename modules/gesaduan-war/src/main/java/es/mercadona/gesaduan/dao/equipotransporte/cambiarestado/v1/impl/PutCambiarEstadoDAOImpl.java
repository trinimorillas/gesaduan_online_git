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

public class PutCambiarEstadoDAOImpl extends BaseDAO<EquipoTransporteJPA> implements PutCambiarEstadoDAO {

	@Inject
	private org.slf4j.Logger logger;	
	
	@Override
	public void setEntityClass() {
		this.entityClass = EquipoTransporteJPA.class;		
	}
	
	@PersistenceContext
	private EntityManager entityM;
	
	@Override
	public List<EquipoDTO> comprobarPedidosSinValidar(List<EquipoDTO> input) {
		
		List<EquipoDTO> equipos = null;		
		
		try {
		
			final StringBuilder sql = new StringBuilder();
			
			String select = "SELECT ET.COD_N_EQUIPO ";
			String from   = "FROM D_EQUIPO_TRANSPORTE ET " +
							"INNER JOIN S_EQUIPO_CARGA EC ON (EC.COD_N_EQUIPO = ET.COD_N_EQUIPO) " +
							"INNER JOIN D_CARGA CA ON (CA.COD_V_CARGA = EC.COD_V_CARGA AND CA.COD_V_ALMACEN_ORIGEN = EC.COD_V_ALMACEN_ORIGEN) ";
			String where  = "WHERE ET.COD_N_EQUIPO IN (";
			
			for (int i = 0; i < input.size(); i++) {
				if (input.size()-1 == i) where += ""+input.get(i).getCodigoEquipo()+"";
				else where += ""+input.get(i).getCodigoEquipo()+", ";
			}
			where += ") AND CA.MCA_PEDIDOS_SIN_VALIDAR = 'S' ";
			String groupBy = "GROUP BY ET.COD_N_EQUIPO";
			
			sql.append(select).append(from).append(where).append(groupBy);		
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
			this.logger.error("({}-{}) ERROR - {} {}","PutCambiarEstadoDAOImpl(GESADUAN)","comprobarPedidosSinValidar",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}		
		
		return equipos;
	}
	
	@Override
	public List<EquipoDTO> comprobarEstadoPlanEmbarqueConfirmado(List<EquipoDTO> input) {
		
		List<EquipoDTO> equipos = null;
		
		try {
		
			final StringBuilder sql = new StringBuilder();
			
			String select = "SELECT ET.COD_N_EQUIPO ";
			String from   = "FROM D_EQUIPO_TRANSPORTE ET " +
							"INNER JOIN D_PLAN_EMBARQUE PE ON (PE.COD_N_EMBARQUE = ET.COD_N_EMBARQUE) ";
			String where  = "WHERE ET.COD_N_EQUIPO IN (";
			
			for (int i = 0; i < input.size(); i++) {
				if (input.size()-1 == i) where += ""+input.get(i).getCodigoEquipo()+"";
				else where += ""+input.get(i).getCodigoEquipo()+", ";
			}
			where += ") AND PE.COD_N_ESTADO <> 2 ";
			String groupBy = "GROUP BY ET.COD_N_EQUIPO";
			
			sql.append(select).append(from).append(where).append(groupBy);		
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
			this.logger.error("({}-{}) ERROR - {} {}","PutCambiarEstadoDAOImpl(GESADUAN)","comprobarEstadoPlanEmbarqueConfirmado",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}					
		
		return equipos;
	}
	
	@Override
	public List<EquipoDTO> comprobarEstadoPlanEmbarqueFacturado(List<EquipoDTO> input) {
		
		List<EquipoDTO> equipos = null;
		
		try {
		
			final StringBuilder sql = new StringBuilder();
			
			String select = "SELECT ET.COD_N_EQUIPO ";
			String from   = "FROM D_EQUIPO_TRANSPORTE ET " +
							"INNER JOIN D_PLAN_EMBARQUE PE ON (PE.COD_N_EMBARQUE = ET.COD_N_EMBARQUE) ";
			String where  = "WHERE ET.COD_N_EQUIPO IN (";
			
			for (int i = 0; i < input.size(); i++) {
				if (input.size()-1 == i) where += ""+input.get(i).getCodigoEquipo()+"";
				else where += ""+input.get(i).getCodigoEquipo()+", ";
			}
			where += ") AND PE.COD_N_ESTADO <> 3 ";
			String groupBy = "GROUP BY ET.COD_N_EQUIPO";
			
			sql.append(select).append(from).append(where).append(groupBy);		
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
			this.logger.error("({}-{}) ERROR - {} {}","PutCambiarEstadoDAOImpl(GESADUAN)","comprobarEstadoPlanEmbarqueFacturado",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}				
			
		return equipos;
	}
	
	@Transactional
	@Override
	public void actualizarEstados(InputDatosCambiarEstadoDTO datos) {
		
		try {
		
			List<EquipoDTO> equipos = datos.getDatos().getEquipo();
			
			String update = "UPDATE D_EQUIPO_TRANSPORTE ET SET ET.COD_N_ESTADO = ?codigoEstado, " +
							"ET.FEC_DT_MODIFICACION = SYSDATE, ET.COD_V_USUARIO_MODIFICACION = ?codigoUsuario " +
							"WHERE ET.COD_N_EQUIPO IN (";
					
			for (int i = 0; i < equipos.size(); i++) {
				if (equipos.size()-1 == i) update += ""+equipos.get(i).getCodigoEquipo()+")";
				else update += ""+equipos.get(i).getCodigoEquipo()+", ";
			}
			
			final Query query = getEntityManager().createNativeQuery(update);
			query.setParameter("codigoUsuario", datos.getMetadatos().getCodigoUsuario());
			query.setParameter("codigoEstado", datos.getDatos().getCodigoEstado());
			query.executeUpdate();
		
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","PutCambiarEstadoDAOImpl(GESADUAN)","actualizarEstados",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}		
		
	}
	
	@Transactional
	@Override
	public void confirmarPlanEmbarque(InputDatosCambiarEstadoDTO datos) {
		
		try {
		
			List<EquipoDTO> input = datos.getDatos().getEquipo();
			
			String update = "MERGE INTO D_PLAN_EMBARQUE PE0 USING (" +
				     		"	SELECT DISTINCT PE1.COD_N_EMBARQUE " +
				     		"	FROM D_PLAN_EMBARQUE PE1 " +
				     		"	INNER JOIN D_EQUIPO_TRANSPORTE ET1 ON (ET1.COD_N_EMBARQUE = PE1.COD_N_EMBARQUE AND ET1.COD_N_EQUIPO IN (";
			
			for (int i = 0; i < input.size(); i++) {
				if (input.size()-1 == i) update += ""+input.get(i).getCodigoEquipo()+") ";
				else update += ""+input.get(i).getCodigoEquipo()+", ";
			}
			
			update += "	AND ET1.COD_N_ESTADO = 1)) PECA ON (PE0.COD_N_EMBARQUE = PECA.COD_N_EMBARQUE) " +
					  "WHEN MATCHED THEN UPDATE " + 
					  "		SET	PE0.COD_N_ESTADO = 2, " +
					  "		PE0.FEC_DT_MODIFICACION = SYSDATE, " +
					  "		PE0.COD_V_USUARIO_MODIFICACION = ?codigoUsuario " + 
					  "WHERE PE0.COD_N_ESTADO = 3 OR PE0.COD_N_ESTADO = 4";
			
			final Query query = getEntityManager().createNativeQuery(update);
			query.setParameter("codigoUsuario", datos.getMetadatos().getCodigoUsuario()).executeUpdate();
			
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","PutCambiarEstadoDAOImpl(GESADUAN)","confirmarPlanEmbarque",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}				
	}
	
	@Transactional
	@Override
	public void asignarCarga(InputDatosCambiarEstadoDTO datos) {
		
		try {
		
			List<EquipoDTO> input = datos.getDatos().getEquipo();
			
			String update = "MERGE INTO D_CARGA CA0 " +
					 "USING ( " +
					 " 	 SELECT DISTINCT EC1.COD_V_CARGA,EC1.COD_V_ALMACEN_ORIGEN " +
					 " 	 FROM S_EQUIPO_CARGA EC1 " +
					 " 	 WHERE EC1.COD_N_EQUIPO IN (";
					
			for (int i = 0; i < input.size(); i++) {
				if (input.size()-1 == i) update += ""+input.get(i).getCodigoEquipo()+") ";
				else update += ""+input.get(i).getCodigoEquipo()+", ";
			}			
			
			update+= ") PECA " +
					 "ON ( " +
					 "   CA0.COD_V_CARGA = PECA.COD_V_CARGA AND " +
					 "   CA0.COD_V_ALMACEN_ORIGEN = PECA.COD_V_ALMACEN_ORIGEN " +
					 ") " +
					 "WHEN MATCHED " +
					 "   THEN " +
					 "       UPDATE " +
					 "       SET CA0.COD_N_ESTADO = 3, " +
					 "           CA0.FEC_DT_MODIFICACION = SYSDATE, " +
					 "           CA0.COD_V_USUARIO_MODIFICACION = ?codigoUsuario " +
					 " WHERE CA0.COD_N_ESTADO IN (4,6) " ;

			
			final Query query = getEntityManager().createNativeQuery(update);
			query.setParameter("codigoUsuario", datos.getMetadatos().getCodigoUsuario()).executeUpdate();
			
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","PutCambiarEstadoDAOImpl(GESADUAN)","asignarCarga",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}			
	}
	
	@Transactional
	@Override
	public void facturarPlanEmbarque(InputDatosCambiarEstadoDTO datos) {
		
		try {
		
			List<EquipoDTO> input = datos.getDatos().getEquipo();
			
			String update = "MERGE INTO D_PLAN_EMBARQUE PE0 USING (" +
							"SELECT DISTINCT PE1.COD_N_EMBARQUE " +
							"FROM D_PLAN_EMBARQUE PE1 " +
							"INNER JOIN D_EQUIPO_TRANSPORTE ET1 ON (ET1.COD_N_EMBARQUE = PE1.COD_N_EMBARQUE " +
							"AND ET1.COD_N_EQUIPO IN (";
							
			for (int i = 0; i < input.size(); i++) {
				if (input.size()-1 == i) update += ""+input.get(i).getCodigoEquipo()+") ";
				else update += ""+input.get(i).getCodigoEquipo()+", ";
			}
			
			update += "AND ET1.COD_N_ESTADO = 2) " +
					  "WHERE NOT EXISTS (SELECT * FROM D_EQUIPO_TRANSPORTE ET2 WHERE " +
					  "ET2.COD_N_EMBARQUE = PE1.COD_N_EMBARQUE AND ET2.COD_N_ESTADO <> 2 AND ET2.COD_N_ESTADO <> 3) " +
					  ") PECA ON (PE0.COD_N_EMBARQUE = PECA.COD_N_EMBARQUE) " +
					  "WHEN MATCHED THEN UPDATE SET PE0.COD_N_ESTADO = 3, PE0.FEC_DT_MODIFICACION = SYSDATE, " +
					  "PE0.COD_V_USUARIO_MODIFICACION = ?codigoUsuario WHERE PE0.COD_N_ESTADO = 2";
			
			final Query query = getEntityManager().createNativeQuery(update);
			query.setParameter("codigoUsuario", datos.getMetadatos().getCodigoUsuario()).executeUpdate();
			
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","PutCambiarEstadoDAOImpl(GESADUAN)","facturarPlanEmbarque",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}				
			
	}
	
	@Transactional
	@Override
	public void facturarCarga(InputDatosCambiarEstadoDTO datos) {
		
		try {
		
			List<EquipoDTO> input = datos.getDatos().getEquipo();
			
			String update = "MERGE INTO D_CARGA CA0 " +
					 "USING ( " +
					 "   SELECT DISTINCT EC1.COD_V_CARGA,EC1.COD_V_ALMACEN_ORIGEN " +
					 "   FROM S_EQUIPO_CARGA EC1 " +
					 "   WHERE EC1.COD_N_EQUIPO IN (";
			
			for (int i = 0; i < input.size(); i++) {
				if (input.size()-1 == i) update += ""+input.get(i).getCodigoEquipo()+") ";
				else update += ""+input.get(i).getCodigoEquipo()+", ";
			}			
					
			update+= "     AND NOT EXISTS ( " +
					 "         SELECT ET2.COD_N_EQUIPO " +
					 "         FROM D_EQUIPO_TRANSPORTE ET2 " +
					 "         INNER JOIN S_EQUIPO_CARGA EC2 ON (EC2.COD_N_EQUIPO = ET2.COD_N_EQUIPO) " +
					 "         WHERE ET2.COD_N_ESTADO = 1 " +
					 "           AND EC2.COD_V_CARGA = EC1.COD_V_CARGA " +
					 "           AND EC2.COD_V_ALMACEN_ORIGEN = EC1.COD_V_ALMACEN_ORIGEN " +			
					 "      ) " +
					 ") PECA " +
					 "ON ( " +
					 "   CA0.COD_V_CARGA = PECA.COD_V_CARGA AND " +
					 "   CA0.COD_V_ALMACEN_ORIGEN = PECA.COD_V_ALMACEN_ORIGEN " +
					 ") " +
					 "WHEN MATCHED " +
					 "   THEN " +
					 "      UPDATE " +
					 "         SET CA0.COD_N_ESTADO = 4, " +
					 "             CA0.FEC_DT_MODIFICACION = SYSDATE, " +
					 "             CA0.COD_V_USUARIO_MODIFICACION = ?codigoUsuario " +
					 "WHERE CA0.COD_N_ESTADO = 3 ";				
			
						
			final Query query = getEntityManager().createNativeQuery(update);
			query.setParameter("codigoUsuario", datos.getMetadatos().getCodigoUsuario()).executeUpdate();
			
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","PutCambiarEstadoDAOImpl(GESADUAN)","facturarCarga",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}	
		 
	}
	
	@Transactional
	@Override
	public void cargarPlanEmbarque(InputDatosCambiarEstadoDTO datos) {
		
		try {
		
			List<EquipoDTO> input = datos.getDatos().getEquipo();
			
			String update = "MERGE INTO D_PLAN_EMBARQUE PE0 USING (" +
						"SELECT DISTINCT PE1.COD_N_EMBARQUE " +
							"FROM D_PLAN_EMBARQUE PE1 " +
							"INNER JOIN D_EQUIPO_TRANSPORTE ET1 ON (ET1.COD_N_EMBARQUE = PE1.COD_N_EMBARQUE " +
							"AND ET1.COD_N_EQUIPO IN (";
							
			for (int i = 0; i < input.size(); i++) {
				if (input.size()-1 == i) update += ""+input.get(i).getCodigoEquipo()+") ";
				else update += ""+input.get(i).getCodigoEquipo()+", ";
			}
			
			update += "AND ET1.COD_N_ESTADO = 3) " +
					  "WHERE NOT EXISTS (SELECT * FROM D_EQUIPO_TRANSPORTE ET2 WHERE " +
					  "ET2.COD_N_EMBARQUE = PE1.COD_N_EMBARQUE AND ET2.COD_N_ESTADO <> 3) " +
					  ") PECA ON (PE0.COD_N_EMBARQUE = PECA.COD_N_EMBARQUE) " +
					  "WHEN MATCHED THEN UPDATE SET PE0.COD_N_ESTADO = 4, PE0.FEC_DT_MODIFICACION = SYSDATE, " +
					  "PE0.COD_V_USUARIO_MODIFICACION = ?codigoUsuario WHERE PE0.COD_N_ESTADO = 3";
			
			final Query query = getEntityManager().createNativeQuery(update);
			query.setParameter("codigoUsuario", datos.getMetadatos().getCodigoUsuario()).executeUpdate();
			
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","PutCambiarEstadoDAOImpl(GESADUAN)","cargarPlanEmbarque",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}
		
	}
	
	@Transactional
	@Override
	public void cargarCarga(InputDatosCambiarEstadoDTO datos) {
		
		try {
		
			List<EquipoDTO> input = datos.getDatos().getEquipo();
			
			String update = "MERGE INTO D_CARGA CA0 " +
					 "USING ( " + 
					 "   SELECT DISTINCT EC1.COD_V_CARGA,EC1.COD_V_ALMACEN_ORIGEN " +  
					 "   FROM S_EQUIPO_CARGA EC1 " + 
					 "   WHERE EC1.COD_N_EQUIPO IN (";
				
				for (int i = 0; i < input.size(); i++) {
					if (input.size()-1 == i) update += ""+input.get(i).getCodigoEquipo()+") ";
					else update += ""+input.get(i).getCodigoEquipo()+", ";
				}				
				
			update+= "     AND NOT EXISTS ( " +
					 "        SELECT ET2.COD_N_EQUIPO " +
					 "        FROM D_EQUIPO_TRANSPORTE ET2 " +
					 "        INNER JOIN S_EQUIPO_CARGA EC2 ON (EC2.COD_N_EQUIPO = ET2.COD_N_EQUIPO) " +
					 "        WHERE ET2.COD_N_ESTADO <> 3 " +
					 "          AND EC2.COD_V_CARGA = EC1.COD_V_CARGA " +
					 "          AND EC2.COD_V_ALMACEN_ORIGEN = EC1.COD_V_ALMACEN_ORIGEN " +			
					 "        ) " +
					 ") PECA " +
					 "ON ( " +
					 "   CA0.COD_V_CARGA = PECA.COD_V_CARGA AND " + 
					 "   CA0.COD_V_ALMACEN_ORIGEN = PECA.COD_V_ALMACEN_ORIGEN " +
					 ") " + 
					 "WHEN MATCHED " +
					 "   THEN " + 
					 "      UPDATE "+ 
					 "      SET CA0.COD_N_ESTADO = 6, " + 
					 "          CA0.FEC_DT_MODIFICACION = SYSDATE, " + 
					 "          CA0.COD_V_USUARIO_MODIFICACION = ?codigoUsuario " + 
					 "WHERE CA0.COD_N_ESTADO = 4";
			
			
			final Query query = getEntityManager().createNativeQuery(update);
			query.setParameter("codigoUsuario", datos.getMetadatos().getCodigoUsuario()).executeUpdate();
		
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","PutCambiarEstadoDAOImpl(GESADUAN)","cargarCarga",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}		
		
	}

}