package es.mercadona.gesaduan.dao.equipotransporte.deleteequipotransporte.v1.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.fwk.data.DaoBaseImpl;
import es.mercadona.gesaduan.dao.equipotransporte.deleteequipotransporte.v1.DeleteEquipoTransporteDAO;
import es.mercadona.gesaduan.dto.equipotransporte.deleteequipotransporte.v1.InputEquipoTransporteDeleteDTO;
import es.mercadona.gesaduan.dto.equipotransporte.getequipotransportedetalle.v1.restfull.CargaDTO;
import es.mercadona.gesaduan.jpa.equipotransporte.v1.EquipoTransporteJPA;

@Stateless
public class DeleteEquipoTransporteDAOImpl extends DaoBaseImpl<Long, EquipoTransporteJPA> implements DeleteEquipoTransporteDAO {
	
	@Inject
	private org.slf4j.Logger logger;		
	
	@PersistenceContext
	private EntityManager entityM;
	
	@Override
	public void setEntityClass() {
		entityClass = EquipoTransporteJPA.class;		
	}

	@Override
	protected EntityManager getEntityManager() {
		return this.entityM;
	}
	
	@Transactional
	@Override
	public void borrarEquipoTransporte(InputEquipoTransporteDeleteDTO input) {		
		try {		
			Long codigoEquipo = input.getCodigoEquipo();
			EquipoTransporteJPA equipoTransporte = entityM.find(EquipoTransporteJPA.class, codigoEquipo);
			entityM.remove(equipoTransporte);
			entityM.flush();		
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","DeleteEquipoTransporteDAOImpl(GESADUAN)","borrarEquipoTransporte",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}
	}
	
	@Override
	public Integer getEstadoActual(Long codigoEquipo) {		
		Integer restultadoQuery;
		
		try {		
			final StringBuilder sql = new StringBuilder();
				
			String select = "SELECT PE.COD_N_ESTADO ";
			String from   = "FROM D_PLAN_EMBARQUE PE " +
							"INNER JOIN D_EQUIPO_TRANSPORTE ET ON ET.COD_N_EMBARQUE = PE.COD_N_EMBARQUE ";
			String where  = "WHERE ET.COD_N_EQUIPO = ?codigoEquipo";
			
			sql.append(select).append(from).append(where);		
			final Query query = getEntityManager().createNativeQuery(sql.toString());				
			query.setParameter("codigoEquipo", codigoEquipo);		
			restultadoQuery = Integer.parseInt(query.getSingleResult().toString());		
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","DeleteEquipoTransporteDAOImpl(GESADUAN)","getEstadoActual",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}		
		
		return restultadoQuery;		
	}
	
	@Transactional
	@Override
	public void actualizarDatosCarga(Long codigoEquipo) {		
		try {		
			String merge = "MERGE INTO D_CARGA CA USING ( " +
						  	"SELECT SEC.COD_V_CARGA, SEC.COD_V_ALMACEN_ORIGEN, SUM(DECODE(SEC.NUM_DIVISION, 0, 0, 1)) NUM_DIVISION, " +
						  	"SUM(SEC.NUM_HUECO_OCUPADO) NUM_HUECO_OCUPADO, SUM(SEC.NUM_PESO_OCUPADO) NUM_PESO_OCUPADO " +
						  	"FROM S_EQUIPO_CARGA SEC " +						  	
						  	"WHERE SEC.COD_N_EQUIPO = ?codigoEquipo " +
						  	"GROUP BY SEC.COD_V_CARGA, SEC.COD_V_ALMACEN_ORIGEN" +
						  	") ECA ON (CA.COD_V_CARGA = ECA.COD_V_CARGA AND CA.COD_V_ALMACEN_ORIGEN = ECA.COD_V_ALMACEN_ORIGEN) " +
						  	"WHEN MATCHED THEN UPDATE SET " +
						  	"CA.NUM_DIVISIONES = CA.NUM_DIVISIONES - ECA.NUM_DIVISION, " +
						  	"CA.NUM_HUECOS_RESTANTES = CA.NUM_HUECOS_RESTANTES + ECA.NUM_HUECO_OCUPADO, " +
						  	"CA.NUM_PESO_RESTANTE = CA.NUM_PESO_RESTANTE + ECA.NUM_PESO_OCUPADO, " +
						  	"CA.COD_N_ESTADO = CASE " + 
						  	"    WHEN (CA.COD_V_APLICACION_ORIGEN = 'GESADUAN' AND CA.COD_N_ESTADO = 3) THEN 2 " + 
						  	"    WHEN (CA.COD_V_APLICACION_ORIGEN != 'GESADUAN' AND CA.MCA_PEDIDOS_SIN_VALIDAR = 'S'  AND CA.COD_N_ESTADO = 3 AND CA.NUM_DIVISIONES = 0) THEN 1 " +
						  	"    WHEN (CA.COD_V_APLICACION_ORIGEN != 'GESADUAN' AND CA.MCA_PEDIDOS_SIN_VALIDAR = 'S'  AND CA.COD_N_ESTADO = 2 AND CA.NUM_DIVISIONES = 1) THEN 1 " +						  	
						  	"    WHEN (CA.COD_V_APLICACION_ORIGEN != 'GESADUAN' AND CA.MCA_PEDIDOS_SIN_VALIDAR != 'S' AND CA.COD_N_ESTADO = 3) THEN 2 " + 
						  	"    ELSE CA.COD_N_ESTADO " + 
							"END, "	+ 
							"CA.NUM_HUECOS = CASE " + 
							"	 WHEN (CA.COD_V_APLICACION_ORIGEN != 'GESADUAN' AND CA.MCA_PEDIDOS_SIN_VALIDAR = 'S' AND CA.COD_N_ESTADO = 3 AND CA.NUM_DIVISIONES = 0) THEN CA.NUM_HUECOS_ORIGEN " + 
							"	 WHEN (CA.COD_V_APLICACION_ORIGEN != 'GESADUAN' AND CA.MCA_PEDIDOS_SIN_VALIDAR = 'S' AND CA.COD_N_ESTADO = 2 AND CA.NUM_DIVISIONES = 1) THEN CA.NUM_HUECOS_ORIGEN " +
							"    ELSE CA.NUM_HUECOS " +									
							"END ";
			
			final Query query = getEntityManager().createNativeQuery(merge);				
			query.setParameter("codigoEquipo", codigoEquipo).executeUpdate();				
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","DeleteEquipoTransporteDAOImpl(GESADUAN)","actualizarDatosCarga",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}			
	}
	
	@Transactional
	@Override
	public void eliminarRelacionContenedorEquipo(Long codigoEquipo, String codigoUsuario) {
		try {		
			String update = "UPDATE O_CONTENEDOR_EXPEDIDO CE SET " + 
				      "CE.COD_N_EQUIPO = NULL, " +
				      "CE.FEC_D_MODIFICACION = SYSDATE, " +
				      "CE.COD_V_USR_MODIFICACION = ?codigoUsuario " +
				      "WHERE CE.COD_N_EQUIPO = ?codigoEquipo";
			
			final Query query = getEntityManager().createNativeQuery(update);				
			query.setParameter("codigoUsuario", codigoUsuario);
			query.setParameter("codigoEquipo", codigoEquipo);
			query.executeUpdate();
		
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","DeleteEquipoTransporteDAOImpl(GESADUAN)","eliminarRelacionContenedorEquipo",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}		
	}
	
	@Transactional
	@Override
	public void eliminarRelacionCargaEquipo(Long codigoEquipo) {		
		try {		
			String merge = "DELETE FROM S_EQUIPO_CARGA SEC WHERE SEC.COD_N_EQUIPO = ?codigoEquipo";
			
			final Query query = getEntityManager().createNativeQuery(merge);				
			query.setParameter("codigoEquipo", codigoEquipo).executeUpdate();
		
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","DeleteEquipoTransporteDAOImpl(GESADUAN)","eliminarRelacionCargaEquipo",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}		
	}
	
	@Transactional
	@Override
	public void actualizarNumeroEquipos(Long codigoEquipo) {		
		try {			
			String merge = 	"MERGE INTO D_PLAN_EMBARQUE PE0 USING (" +
							"SELECT ET0.COD_N_EMBARQUE, COUNT(*) NUM_EQUIPOS " +
							"FROM D_EQUIPO_TRANSPORTE ET0 " +
							"INNER JOIN D_PLAN_EMBARQUE PE1 ON (PE1.COD_N_EMBARQUE = ET0.COD_N_EMBARQUE) " +
							"INNER JOIN D_EQUIPO_TRANSPORTE ET1 ON (ET1.COD_N_EMBARQUE = PE1.COD_N_EMBARQUE AND ET1.COD_N_EQUIPO = ?codigoEquipo) " +
							"GROUP BY ET0.COD_N_EMBARQUE " +
							") PECA " +
							"ON (PE0.COD_N_EMBARQUE = PECA.COD_N_EMBARQUE) " +
							"WHEN MATCHED THEN UPDATE SET " +
							"PE0.NUM_EQUIPOS = PECA.NUM_EQUIPOS - 1";
			
			final Query query = getEntityManager().createNativeQuery(merge);				
			query.setParameter("codigoEquipo", codigoEquipo).executeUpdate();		
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","DeleteEquipoTransporteDAOImpl(GESADUAN)","actualizarNumeroEquipos",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}		
		
	}
	
	@Transactional	
	@Override
	public void eliminarCargasAbortadas(List<CargaDTO> listaCarga) {		
		try {			
			// Recorre las cargas asociadas al equipo
			if (listaCarga != null) {
				for (CargaDTO carga : listaCarga) {
					String codigoCarga = carga.getCodigoCarga();
					String codigoCentroOrigen = carga.getCodigoAlmacenOrigen();
					borraPedidosCargaAbortadas(codigoCentroOrigen,codigoCarga);
					borraCargaAbortadas(codigoCentroOrigen,codigoCarga);
				}
			}
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","DeleteEquipoTransporteDAOImpl(GESADUAN)","eliminarCargasAbortadas",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}		
	}	
	
	@Transactional	
	private void borraPedidosCargaAbortadas(String codigoCentroOrigen,  String codigoCarga) {
		try {		
			String merge = "DELETE FROM S_CARGA_PEDIDO CP " + 
			               "WHERE " + 
			               "  CP.COD_V_CARGA = ?codigoCarga AND " + 
			               "  CP.COD_V_ALMACEN_ORIGEN= ?codigoCentroOrigen AND " + 
			               "  EXISTS (SELECT 1 " + 
			               "		  FROM D_CARGA CA " + 
			               "		  WHERE CA.COD_V_CARGA = CP.COD_V_CARGA AND " + 
			               "  				CA.COD_V_ALMACEN_ORIGEN= CP.COD_V_ALMACEN_ORIGEN AND " + 
			               "  				CA.MCA_ELIMINADA = 'S' AND " + 
			               "  				CA.COD_N_ESTADO = 1)";
			
			final Query query = getEntityManager().createNativeQuery(merge);				
			query.setParameter("codigoCarga", codigoCarga);
			query.setParameter("codigoCentroOrigen", codigoCentroOrigen);			
			query.executeUpdate();		
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","DeleteEquipoTransporteDAOImpl(GESADUAN)","borraPedidosCargaAbortadas",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}
	}	
	
	@Transactional	
	private void borraCargaAbortadas(String codigoCentroOrigen,  String codigoCarga) {
		try {		
			String merge = "DELETE FROM D_CARGA CA " + 
			               "WHERE " + 
			               "  CA.COD_V_CARGA = ?codigoCarga AND " + 
			               "  CA.COD_V_ALMACEN_ORIGEN= ?codigoCentroOrigen AND " + 
			               "  CA.MCA_ELIMINADA = 'S' AND " + 
			               "  CA.COD_N_ESTADO = 1 ";
			
			final Query query = getEntityManager().createNativeQuery(merge);				
			query.setParameter("codigoCarga", codigoCarga);
			query.setParameter("codigoCentroOrigen", codigoCentroOrigen);			
			query.executeUpdate();		
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","DeleteEquipoTransporteDAOImpl(GESADUAN)","borraCargaAbortadas",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}
	}			

	@Override	
	public List<CargaDTO> getCargasEquipo(Long codigoEquipo) {		
		List<CargaDTO> listaCarga = null;		
		
		try {		
			final StringBuilder sql = new StringBuilder();
			
			String select = "SELECT ";		
			String campos = "DISTINCT EC.COD_V_CARGA, EC.COD_V_ALMACEN_ORIGEN ";
			String from = "FROM S_EQUIPO_CARGA EC " +
					      "INNER JOIN D_EQUIPO_TRANSPORTE ET ON (ET.COD_N_EQUIPO = EC.COD_N_EQUIPO)  ";
			String where = "WHERE ET.COD_N_EQUIPO = ?codigoEquipo ";
						
			sql.append(select).append(campos).append(from).append(where);
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			
			query.setParameter("codigoEquipo", codigoEquipo);
			
			@SuppressWarnings("unchecked")
			List<Object[]> listado = query.getResultList();		
			
			if (listado != null && !listado.isEmpty()) {
				listaCarga = new ArrayList<>(); 
				for (Object[] tmp : listado) {		
					CargaDTO carga = new CargaDTO();
					carga.setCodigoCarga(String.valueOf(tmp[0]));
					carga.setCodigoAlmacenOrigen(String.valueOf(tmp[1]));
					listaCarga.add(carga);
				}
			}		
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","DeleteEquipoTransporteDAOImpl(GESADUAN)","getCargasEquipo",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}			
		
		return listaCarga;
	}
	
	
	@Transactional	
	@Override	
	public void borraRelacionEquipoDosier(Long codigoEquipo) {
		try {		
			
			StringBuilder delete = new StringBuilder();
			
			delete.append("DELETE FROM S_DOSIER_EQUIPO DE "); 
			delete.append("WHERE COD_N_EQUIPO = ?codigoEquipo "); 
			delete.append("AND EXISTS ( "); 
			delete.append("    SELECT 1 "); 
			delete.append("    FROM D_DOSIER D "); 
			delete.append("    WHERE D.NUM_DOSIER = DE.NUM_DOSIER "); 
			delete.append("    AND D.NUM_ANYO = DE.NUM_ANYO "); 
			delete.append("    AND D.COD_N_ESTADO = 3 "); 
			delete.append(")"); 
			delete.append("AND NOT EXISTS ( "); 
			delete.append("    SELECT 1 "); 
			delete.append("    FROM D_DOSIER D "); 
			delete.append("    WHERE D.NUM_DOSIER = DE.NUM_DOSIER "); 
			delete.append("    AND D.NUM_ANYO = DE.NUM_ANYO "); 
			delete.append("    AND D.COD_N_ESTADO = 1 "); 
			delete.append(")");
			
			final Query query = getEntityManager().createNativeQuery(delete.toString());				
			query.setParameter("codigoEquipo", codigoEquipo);			
			query.executeUpdate();		
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","DeleteEquipoTransporteDAOImpl(GESADUAN)","borraRelacionDosier",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}
	}	
	
}
