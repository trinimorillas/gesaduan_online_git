package es.mercadona.gesaduan.dao.equipotransporte.movercargas.v1.impl;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.gesaduan.dao.BaseDAO;
import es.mercadona.gesaduan.dao.equipotransporte.movercargas.v1.PutMoverCargasDAO;
import es.mercadona.gesaduan.dto.equipotransporte.movercargas.v1.CargasDTO;
import es.mercadona.gesaduan.dto.equipotransporte.movercargas.v1.InputDatosMoverCargasDTO;
import es.mercadona.gesaduan.dto.equipotransporte.putrelacionequipocarga.v1.CargaDTO;
import es.mercadona.gesaduan.jpa.equipotransporte.v1.EquipoTransporteJPA;

public class PutMoverCargasDAOImpl extends BaseDAO<EquipoTransporteJPA> implements PutMoverCargasDAO {

	@Inject
	private org.slf4j.Logger logger;	
	
	@Override
	public void setEntityClass() {
		this.entityClass = EquipoTransporteJPA.class;		
	}
	
	@PersistenceContext
	private EntityManager entityM;
	
	@Override
	public Integer comprobarEquipoCargado(Long codigoEquipo) {
		
		Integer restultadoQuery;
		
		try {
		
			final StringBuilder sql = new StringBuilder();
	
			String select = "SELECT COUNT(ET.COD_N_EQUIPO) ";
			String from   = "FROM D_EQUIPO_TRANSPORTE ET ";
			String where  = "WHERE ET.COD_N_EQUIPO = ?codigoEquipo AND ET.COD_N_ESTADO = 1";
			
			sql.append(select).append(from).append(where);
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			query.setParameter("codigoEquipo", codigoEquipo);
			restultadoQuery = Integer.parseInt(query.getSingleResult().toString());
		
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","PutMoverCargasDAOImpl(GESADUAN)","comprobarEquipoCargado",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}		
		
		return restultadoQuery;	
	}
	
	@Override
	public Integer comprobarRelacion(InputDatosMoverCargasDTO datos, CargasDTO carga) {
		
		Integer restultadoQuery;
		
		try {
		
			final StringBuilder sql = new StringBuilder();
			String select = "SELECT COUNT(*) ";
			String from   = "FROM S_EQUIPO_CARGA EC ";
			String where  = "WHERE EC.COD_N_EQUIPO = ?codigoEquipoDestino " +
							"AND EC.COD_V_ALMACEN_ORIGEN = ?codigoCentroOrigen " +
							"AND EC.COD_V_CARGA = ?codigoCarga";
			
			sql.append(select).append(from).append(where);
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			query.setParameter("codigoEquipoDestino", datos.getDatos().getCodigoEquipoDestino());
			query.setParameter("codigoCentroOrigen", carga.getCodigoCentroOrigen());
			query.setParameter("codigoCarga", carga.getCodigoCarga());
			restultadoQuery = Integer.parseInt(query.getSingleResult().toString());

		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","PutMoverCargasDAOImpl(GESADUAN)","comprobarRelacion",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}			
			
		return restultadoQuery;	
	}
	
	@Transactional
	@Override
	public void actualizarRelacion(InputDatosMoverCargasDTO datos, CargasDTO carga) {
		
		try {
		
			String update = "MERGE INTO S_EQUIPO_CARGA EC " +
							"USING (SELECT " +
							"EC1.COD_V_CARGA, EC1.COD_V_ALMACEN_ORIGEN, EC1.NUM_HUECO_OCUPADO, EC1.NUM_PESO_OCUPADO, CA.NUM_HUECOS TOTAL_HUECOS " +
							"FROM S_EQUIPO_CARGA EC1 " +
							"LEFT JOIN D_CARGA CA ON (CA.COD_V_CARGA = EC1.COD_V_CARGA AND CA.COD_V_ALMACEN_ORIGEN = EC1.COD_V_ALMACEN_ORIGEN) " +
							"WHERE EC1.COD_N_EQUIPO = ?codigoEquipoOrigen " +
							"AND EC1.COD_V_CARGA = ?codigoCarga " + 
							"AND EC1.COD_V_ALMACEN_ORIGEN = ?codigoCentroOrigen" +
							") SEC " +
							"ON (" +
							"EC.COD_N_EQUIPO = ?codigoEquipoDestino " +
							"AND EC.COD_V_CARGA = SEC.COD_V_CARGA " +
							"AND EC.COD_V_ALMACEN_ORIGEN = SEC.COD_V_ALMACEN_ORIGEN) " +
							"WHEN MATCHED THEN UPDATE SET " +
							"EC.NUM_HUECO_OCUPADO = EC.NUM_HUECO_OCUPADO + SEC.NUM_HUECO_OCUPADO, " +
							"EC.NUM_PESO_OCUPADO = EC.NUM_PESO_OCUPADO + SEC.NUM_PESO_OCUPADO, " +
							"EC.NUM_DIVISION = DECODE(EC.NUM_HUECO_OCUPADO + SEC.NUM_HUECO_OCUPADO, SEC.TOTAL_HUECOS, 0, EC.NUM_DIVISION), " +
							"EC.COD_V_USUARIO_MODIFICACION = ?codigoUsuario, " +
							"EC.FEC_DT_MODIFICACION = SYSDATE";
			
			final Query query = getEntityManager().createNativeQuery(update);
			query.setParameter("codigoEquipoOrigen", datos.getDatos().getCodigoEquipoOrigen());
			query.setParameter("codigoCarga", carga.getCodigoCarga());
			query.setParameter("codigoCentroOrigen", carga.getCodigoCentroOrigen());
			query.setParameter("codigoEquipoDestino", datos.getDatos().getCodigoEquipoDestino());
			query.setParameter("codigoUsuario", datos.getMetadatos().getCodigoUsuario());
			query.executeUpdate();
			
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","PutMoverCargasDAOImpl(GESADUAN)","actualizarRelacion",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}			
	}
	
	@Transactional
	@Override
	public void actualizarDivisiones(InputDatosMoverCargasDTO datos, CargasDTO carga) {
		
		try {
		
			String update = "MERGE INTO D_CARGA CA " +
							"USING (SELECT EC1.COD_V_CARGA, EC1.COD_V_ALMACEN_ORIGEN, EC1.NUM_HUECO_OCUPADO " +
							"FROM S_EQUIPO_CARGA EC1 " + 
							"WHERE EC1.COD_N_EQUIPO = ?codigoEquipoDestino " +
							"AND EC1.COD_V_CARGA = ?codigoCarga " +
							"AND EC1.COD_V_ALMACEN_ORIGEN = ?codigoCentroOrigen" +
							") SEC " +
							"ON (CA.COD_V_CARGA = SEC.COD_V_CARGA AND CA.COD_V_ALMACEN_ORIGEN = SEC.COD_V_ALMACEN_ORIGEN) " +
							"WHEN MATCHED THEN UPDATE SET " +
							"CA.NUM_DIVISIONES = DECODE(CA.NUM_HUECOS, SEC.NUM_HUECO_OCUPADO, 0, CA.NUM_DIVISIONES - 1), " +
							"CA.COD_V_USUARIO_MODIFICACION = ?codigoUsuario, " +
							"CA.FEC_DT_MODIFICACION = SYSDATE";
			
			final Query query = getEntityManager().createNativeQuery(update);
			query.setParameter("codigoEquipoDestino", datos.getDatos().getCodigoEquipoDestino());
			query.setParameter("codigoCentroOrigen", carga.getCodigoCentroOrigen());
			query.setParameter("codigoCarga", carga.getCodigoCarga());
			query.setParameter("codigoUsuario", datos.getMetadatos().getCodigoUsuario());
			query.executeUpdate();
			
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","PutMoverCargasDAOImpl(GESADUAN)","actualizarDivisiones",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}				
	}
	
	@Transactional
	@Override
	public void borrarRelacionOrigen(Long codigoEquipoOrigen, CargasDTO carga) {
		
		try {
		
			String delete = "DELETE S_EQUIPO_CARGA EC " +
							"WHERE EC.COD_N_EQUIPO = ?codigoEquipoOrigen " +
							"AND EC.COD_V_CARGA = ?codigoCarga AND " +
							"EC.COD_V_ALMACEN_ORIGEN = ?codigoCentroOrigen";
			
			final Query query = getEntityManager().createNativeQuery(delete);
			query.setParameter("codigoEquipoOrigen", codigoEquipoOrigen);
			query.setParameter("codigoCentroOrigen", carga.getCodigoCentroOrigen());
			query.setParameter("codigoCarga", carga.getCodigoCarga());
			query.executeUpdate();
		
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","PutMoverCargasDAOImpl(GESADUAN)","borrarRelacionOrigen",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}		
	}
	
	@Transactional
	@Override
	public void actualizarOcupacionEquipoOrigen(InputDatosMoverCargasDTO datos, CargasDTO carga) {
		
		try {
		
			String update = "UPDATE D_EQUIPO_TRANSPORTE ET " +
							"SET ET.NUM_OCUPACION = ET.NUM_OCUPACION - (" +
							"SELECT EC.NUM_HUECO_OCUPADO " +
							"FROM S_EQUIPO_CARGA EC " +
							"WHERE EC.COD_N_EQUIPO = ?codigoEquipoOrigen " +
							"AND EC.COD_V_CARGA = ?codigoCarga " +
							"AND EC.COD_V_ALMACEN_ORIGEN = ?codigoCentroOrigen) " +
							"WHERE ET.COD_N_EQUIPO = ?codigoEquipoOrigen";
			
			final Query query = getEntityManager().createNativeQuery(update);
			query.setParameter("codigoEquipoOrigen", datos.getDatos().getCodigoEquipoOrigen());
			query.setParameter("codigoCarga", carga.getCodigoCarga());
			query.setParameter("codigoCentroOrigen", carga.getCodigoCentroOrigen());
			query.executeUpdate();
		
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","PutMoverCargasDAOImpl(GESADUAN)","actualizarOcupacionEquipoOrigen",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}		
		
	}
	
	@Transactional
	@Override
	public void actualizarOcupacionEquipoDestino(InputDatosMoverCargasDTO datos, CargasDTO carga) {
		
		try {
		
			String update = "UPDATE D_EQUIPO_TRANSPORTE ET " +
							"SET ET.NUM_OCUPACION = ET.NUM_OCUPACION + (" +
							"SELECT EC.NUM_HUECO_OCUPADO " +
							"FROM S_EQUIPO_CARGA EC " +
							"WHERE EC.COD_N_EQUIPO = ?codigoEquipoOrigen " +
							"AND EC.COD_V_CARGA = ?codigoCarga " +
							"AND EC.COD_V_ALMACEN_ORIGEN = ?codigoCentroOrigen) " +
							"WHERE ET.COD_N_EQUIPO = ?codigoEquipoDestino";
			
			final Query query = getEntityManager().createNativeQuery(update);
			query.setParameter("codigoEquipoOrigen", datos.getDatos().getCodigoEquipoOrigen());
			query.setParameter("codigoCarga", carga.getCodigoCarga());
			query.setParameter("codigoCentroOrigen", carga.getCodigoCentroOrigen());
			query.setParameter("codigoEquipoDestino", datos.getDatos().getCodigoEquipoDestino());
			query.executeUpdate();
		
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","PutMoverCargasDAOImpl(GESADUAN)","actualizarOcupacionEquipoDestino",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}			
	}
	
	@Transactional
	@Override
	public void cambiarRelacion(InputDatosMoverCargasDTO datos, CargasDTO carga) {
		
		try {
		
			String update = "UPDATE S_EQUIPO_CARGA EC " +
							"SET EC.COD_N_EQUIPO = ?codigoEquipoDestino " +
							"WHERE EC.COD_N_EQUIPO = ?codigoEquipoOrigen " +
							"AND EC.COD_V_CARGA = ?codigoCarga " +
							"AND EC.COD_V_ALMACEN_ORIGEN = ?codigoCentroOrigen";
			
			final Query query = getEntityManager().createNativeQuery(update);
			query.setParameter("codigoEquipoDestino", datos.getDatos().getCodigoEquipoDestino());
			query.setParameter("codigoEquipoOrigen", datos.getDatos().getCodigoEquipoOrigen());
			query.setParameter("codigoCarga", carga.getCodigoCarga());
			query.setParameter("codigoCentroOrigen", carga.getCodigoCentroOrigen());
			query.executeUpdate();
			
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","PutMoverCargasDAOImpl(GESADUAN)","cambiarRelacion",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}			
	}
	
	@Transactional
	@Override
	public Integer comprobarCargaContenedorFacturado(InputDatosMoverCargasDTO datos) {
		Integer restultadoQuery;
		
		try {		
			final StringBuilder sql = new StringBuilder();
	
			String select = "SELECT COUNT(NUM_CONTENEDOR) ";
			String from   = "FROM O_CONTENEDOR_EXPEDIDO ";
			String where  = "WHERE COD_N_EQUIPO = ?codigoEquipoOrigen AND (";
			List<CargasDTO> cargas = datos.getDatos().getCargas();
			
			for (int i = 0; i < cargas.size(); i++) {
				if (cargas.size()-1 == i) where += "(COD_V_CARGA = '"+cargas.get(i).getCodigoCarga()+"' AND COD_V_ALMACEN = '"+cargas.get(i).getCodigoCentroOrigen()+"'))";
				else where += "(COD_V_CARGA = '"+cargas.get(i).getCodigoCarga()+"' AND COD_V_ALMACEN = '"+cargas.get(i).getCodigoCentroOrigen()+"') OR ";
			}
			
			sql.append(select).append(from).append(where);
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			query.setParameter("codigoEquipoOrigen", datos.getDatos().getCodigoEquipoOrigen());
			restultadoQuery = Integer.parseInt(query.getSingleResult().toString());		
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","PutMoverCargasDAOImpl(GESADUAN)","comprobarCargaContenedorFacturado",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}		
		
		return restultadoQuery;	
	}

}