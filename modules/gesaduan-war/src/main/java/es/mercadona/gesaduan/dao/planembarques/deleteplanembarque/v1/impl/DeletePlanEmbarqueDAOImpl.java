package es.mercadona.gesaduan.dao.planembarques.deleteplanembarque.v1.impl;

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
import es.mercadona.gesaduan.dao.planembarques.deleteplanembarque.v1.DeletePlanEmbarqueDAO;
import es.mercadona.gesaduan.dto.equipotransporte.getequipotransportedetalle.v1.restfull.CargaDTO;
import es.mercadona.gesaduan.dto.planembarques.deleteplanembarque.v1.InputDatosDeletePlanEmbarqueDTO;
import es.mercadona.gesaduan.dto.planembarques.deleteplanembarque.v1.InputPlanEmbarqueDeleteDTO;
import es.mercadona.gesaduan.jpa.planembarques.v1.PlanEmbarquesJPA;

@Stateless
public class DeletePlanEmbarqueDAOImpl extends DaoBaseImpl<Long, PlanEmbarquesJPA> implements DeletePlanEmbarqueDAO {
	
	@Inject
	private org.slf4j.Logger logger;	
	
	@PersistenceContext
	private EntityManager entityM;
	
	@Override
	public void setEntityClass() {
		entityClass = PlanEmbarquesJPA.class;		
	}

	@Override
	protected EntityManager getEntityManager() {
		return this.entityM;
	}
	
	@Transactional
	@Override
	public void borrarPlanEmbarque(InputPlanEmbarqueDeleteDTO input) {
		
		Long codigoEmbarque = input.getCodigoEmbarque();		
		eliminarEquipo(codigoEmbarque);
		eliminarPlanEmbarque(codigoEmbarque);

	}
	
	@Override
	public void actualizarDatosCarga(Long codigoEmbarque) {
		
		try {		
		
			String merge = "MERGE INTO D_CARGA CA USING ( " +
						   "SELECT SEC.COD_V_CARGA, SEC.COD_V_ALMACEN_ORIGEN, SUM(DECODE(SEC.NUM_DIVISION,0,0,1)) NUM_DIVISION, " +
						   "SUM(SEC.NUM_HUECO_OCUPADO) NUM_HUECO_OCUPADO, SUM(SEC.NUM_PESO_OCUPADO) NUM_PESO_OCUPADO " +
						   "FROM S_EQUIPO_CARGA SEC " +
						   "WHERE SEC.COD_N_EQUIPO IN (SELECT DET.COD_N_EQUIPO FROM D_EQUIPO_TRANSPORTE DET WHERE DET.COD_N_EMBARQUE = ?codigoEmbarque) " +
						   "GROUP BY SEC.COD_V_CARGA, SEC.COD_V_ALMACEN_ORIGEN " +
						   ") ECA ON (CA.COD_V_CARGA = ECA.COD_V_CARGA AND	CA.COD_V_ALMACEN_ORIGEN = ECA.COD_V_ALMACEN_ORIGEN) " +
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
						   "END ";
		
			final Query query = getEntityManager().createNativeQuery(merge);				
			query.setParameter("codigoEmbarque", codigoEmbarque).executeUpdate();
			
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","DeletePlanEmbarqueDAOImpl(GESADUAN)","actualizarDatosCarga",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}
	}
	
	@Override
	public void eliminarRelacionCargaEquipo(Long codigoEmbarque) {
		
		try {	
		
			String merge = "DELETE FROM S_EQUIPO_CARGA SEC WHERE SEC.COD_N_EQUIPO IN (SELECT DET.COD_N_EQUIPO FROM D_EQUIPO_TRANSPORTE DET WHERE DET.COD_N_EMBARQUE = ?codigoEmbarque)";
		
			final Query query = getEntityManager().createNativeQuery(merge);				
			query.setParameter("codigoEmbarque", codigoEmbarque).executeUpdate();
		
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","DeletePlanEmbarqueDAOImpl(GESADUAN)","eliminarRelacionCargaEquipo",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}		
	}
	
	@Override
	public void reordenarDivisiones() {
		
		try {	
		
			String merge = "MERGE INTO S_EQUIPO_CARGA SEC USING ( " + 
							"SELECT SEC0.COD_N_EQUIPO, SEC0.COD_V_CARGA, SEC0.COD_V_ALMACEN_ORIGEN, SEC0.NUM_DIVISION, " +
							"ROW_NUMBER() OVER (PARTITION BY SEC0.COD_V_CARGA, SEC0.COD_V_ALMACEN_ORIGEN ORDER BY SEC0.NUM_DIVISION ASC) ORDEN_DIVISION " + 
							"FROM S_EQUIPO_CARGA SEC0 " + 
							") SEC1 " + 
							"ON (SEC.COD_V_CARGA = SEC1.COD_V_CARGA AND " + 
							"SEC.COD_V_ALMACEN_ORIGEN = SEC1.COD_V_ALMACEN_ORIGEN AND " + 
							"SEC.COD_N_EQUIPO = SEC1.COD_N_EQUIPO) " + 
							"WHEN MATCHED THEN UPDATE SET " + 
							"SEC.NUM_DIVISION = SEC1.ORDEN_DIVISION " + 
							"WHERE SEC.NUM_DIVISION <> SEC1.ORDEN_DIVISION AND " + 
							"SEC.NUM_DIVISION > 0";
			
			final Query query = getEntityManager().createNativeQuery(merge);
			query.executeUpdate();
		
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","DeletePlanEmbarqueDAOImpl(GESADUAN)","reordenarDivisiones",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}				
	}
	
	@Override
	public void eliminarEquipo(Long codigoEmbarque) {
		
		try {			
		
			Query queryEquipo = entityM.createNativeQuery("DELETE FROM D_EQUIPO_TRANSPORTE WHERE COD_N_EMBARQUE = ?codigoEmbarque");
			queryEquipo.setParameter("codigoEmbarque", codigoEmbarque).executeUpdate();
		
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","DeletePlanEmbarqueDAOImpl(GESADUAN)","eliminarEquipo",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}			
		
	}
	
	@Override
	public void eliminarPlanEmbarque(Long codigoEmbarque) {
		
		try {		
			
			Query queryPlanEmbarque = entityM.createNativeQuery("DELETE FROM D_PLAN_EMBARQUE WHERE COD_N_EMBARQUE = ?codigoEmbarque");
			queryPlanEmbarque.setParameter("codigoEmbarque", codigoEmbarque).executeUpdate();
			
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","DeletePlanEmbarqueDAOImpl(GESADUAN)","eliminarPlanEmbarque",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}			
	}	
	
	@Transactional	
	@Override
	public void eliminarCargasAbortadas(List<CargaDTO> listaCarga) {
		
		try {
			
			// recorre las cargas asociadas al equipo
			if (listaCarga != null) {
				for (CargaDTO carga : listaCarga) {
					String codigoCarga = carga.getCodigoCarga();
					String codigoCentroOrigen = carga.getCodigoAlmacenOrigen();
					borraPedidosCargaAbortadas(codigoCentroOrigen,codigoCarga);
					borraCargaAbortadas(codigoCentroOrigen,codigoCarga);
				}
			}
		
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","DeletePlanEmbarqueDAOImpl(GESADUAN)","eliminarCargasAbortadas",ex.getClass().getSimpleName(),ex.getMessage());	
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
			               "			FROM D_CARGA CA " + 
			               "		   WHERE CA.COD_V_CARGA = CP.COD_V_CARGA AND " + 
			               "  				 CA.COD_V_ALMACEN_ORIGEN= CP.COD_V_ALMACEN_ORIGEN AND " + 
			               "  				 CA.MCA_ELIMINADA = 'S' AND " + 
			               "  				 CA.COD_N_ESTADO = 1)";
			
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
	public List<CargaDTO> getCargasPlanEmbarque(Long codigoEmbarque) {	
		
		List<CargaDTO> listaCarga = null;		
		
		try {
		
			final StringBuilder sql = new StringBuilder();
			
			String select = "SELECT ";		
			String campos = "DISTINCT EC.COD_V_CARGA, EC.COD_V_ALMACEN_ORIGEN ";
			String from = "FROM S_EQUIPO_CARGA EC " +
					      "INNER JOIN D_EQUIPO_TRANSPORTE ET ON (ET.COD_N_EQUIPO = EC.COD_N_EQUIPO)  ";
			String where = "WHERE ET.COD_N_EMBARQUE = ?codigoEmbarque ";			
									
			sql.append(select).append(campos).append(from).append(where);
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			
			query.setParameter("codigoEmbarque", codigoEmbarque);
			
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
	public void eliminarRelacionContenedorEquipo(InputDatosDeletePlanEmbarqueDTO datos) {
		try {			
			String update = "UPDATE O_CONTENEDOR_EXPEDIDO CE SET " + 
				      		"CE.COD_N_EQUIPO = NULL, " +
				      		"CE.FEC_D_MODIFICACION = SYSDATE, " +
				      		"CE.COD_V_USR_MODIFICACION = ?codigoUsuario " +
				      		"WHERE CE.COD_N_EQUIPO IN (SELECT DET.COD_N_EQUIPO FROM D_EQUIPO_TRANSPORTE DET WHERE DET.COD_N_EMBARQUE = ?codigoEmbarque)";
			
			final Query query = getEntityManager().createNativeQuery(update);				
			query.setParameter("codigoUsuario", datos.getMetadatos().getCodigoUsuario());
			query.setParameter("codigoEmbarque", datos.getDatos().getCodigoEmbarque());
			query.executeUpdate();		
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","DeleteEquipoTransporteDAOImpl(GESADUAN)","borraCargaAbortadas",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}
	}

}
