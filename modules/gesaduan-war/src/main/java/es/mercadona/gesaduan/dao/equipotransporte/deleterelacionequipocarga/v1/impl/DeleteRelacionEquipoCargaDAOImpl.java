package es.mercadona.gesaduan.dao.equipotransporte.deleterelacionequipocarga.v1.impl;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.gesaduan.dao.BaseDAO;
import es.mercadona.gesaduan.dao.equipotransporte.deleterelacionequipocarga.v1.DeleteRelacionEquipoCargaDAO;
import es.mercadona.gesaduan.dto.equipotransporte.deleterelacionequipocarga.v1.EliminarCargaDTO;
import es.mercadona.gesaduan.dto.equipotransporte.deleterelacionequipocarga.v1.InputDatosEliminarRelacionEquipoCargaDTO;
import es.mercadona.gesaduan.jpa.equipotransporte.v1.EquipoCargaJPA;
import es.mercadona.gesaduan.jpa.equipotransporte.v1.EquipoCargaPkJPA;

public class DeleteRelacionEquipoCargaDAOImpl extends BaseDAO<EquipoCargaJPA> implements DeleteRelacionEquipoCargaDAO {

	@Inject
	private org.slf4j.Logger logger;	
	
	@Override
	public void setEntityClass() {
		this.entityClass = EquipoCargaJPA.class;		
	}
	
	@PersistenceContext
	private EntityManager entityM;
	
	@Transactional
	@Override
	public void deleteRelacionEquipoCarga(InputDatosEliminarRelacionEquipoCargaDTO input) {		
		try {
		
			Long codigoEquipo = input.getDatos().getCodigoEquipo();
			List<EliminarCargaDTO> cargas = input.getDatos().getCarga();
			
			if (cargas != null && !cargas.isEmpty()) {
				for (EliminarCargaDTO carga : cargas) {
					EquipoCargaPkJPA id = new EquipoCargaPkJPA();
					String codigoCarga = carga.getCodigoCarga();
					String codigoCentroOrigen = carga.getCodigoCentroOrigen();
	
					id.setCodigoEquipo(codigoEquipo);
					id.setCodigoCarga(codigoCarga);
					id.setCodigoCentroOrigen(codigoCentroOrigen);
					
					EquipoCargaJPA equipoCarga = entityM.find(EquipoCargaJPA.class, id);
					entityM.remove(equipoCarga);
					entityM.flush();
				}
			}
		
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","DeleteRelacionEquipoCargaDAOImpl(GESADUAN)","deleteRelacionEquipoCarga",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}		
	}
	
	@Transactional
	@Override
	public void eliminarRelacionContenedorEquipo(InputDatosEliminarRelacionEquipoCargaDTO input) {
		try {			
			List<EliminarCargaDTO> cargas = input.getDatos().getCarga();
			
			if (cargas != null && !cargas.isEmpty()) {
				for (EliminarCargaDTO carga : cargas) {
					String codigoCarga = carga.getCodigoCarga();
					borrarRelacionContenedorEquipo(input.getDatos().getCodigoEquipo(), codigoCarga, input.getMetadatos().getCodigoUsuario());
				}
			}		
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","DeleteRelacionEquipoCargaDAOImpl(GESADUAN)","eliminarRelacionContenedorEquipo",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}	
	}
	
	@Transactional	
	public void borrarRelacionContenedorEquipo(Long codigoEquipo,  String codigoCarga, String codigoUsuario) {
		try {
			String update = "UPDATE O_CONTENEDOR_EXPEDIDO CE SET " + 
				      		"CE.COD_N_EQUIPO = NULL, " +
				      		"CE.FEC_D_MODIFICACION = SYSDATE, " +
				      		"CE.COD_V_USR_MODIFICACION = ?codigoUsuario " +
				      		"WHERE CE.COD_N_EQUIPO = ?codigoEquipo ";
			
			final Query query = getEntityManager().createNativeQuery(update);
			query.setParameter("codigoUsuario", codigoUsuario);
			query.setParameter("codigoEquipo", codigoEquipo);				
			query.setParameter("codigoCarga", codigoCarga);
			query.executeUpdate();		
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","DeleteEquipoTransporteDAOImpl(GESADUAN)","borrarRelacionContenedorEquipo",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}
	}
	
	@Transactional	
	@Override
	public void deleteCargasAbortadas(InputDatosEliminarRelacionEquipoCargaDTO input) {
		try {
			
			List<EliminarCargaDTO> cargas = input.getDatos().getCarga();
			
			if (cargas != null && !cargas.isEmpty()) {
				for (EliminarCargaDTO carga : cargas) {
					String codigoCarga = carga.getCodigoCarga();
					String codigoCentroOrigen = carga.getCodigoCentroOrigen();
					borraPedidosCargaAbortadas(codigoCentroOrigen,codigoCarga);
					borraCargaAbortadas(codigoCentroOrigen,codigoCarga);
				}
			}
		
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","DeleteRelacionEquipoCargaDAOImpl(GESADUAN)","deleteCargasAbortadas",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}			
	}	
	
	@Transactional	
	public void borraPedidosCargaAbortadas(String codigoCentroOrigen,  String codigoCarga) {

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
	public void borraCargaAbortadas(String codigoCentroOrigen,  String codigoCarga) {

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
	
	@Transactional
	@Override
	public void actualizarCargas(InputDatosEliminarRelacionEquipoCargaDTO input) {
		
		try {
		
			Long codigoEquipo = input.getDatos().getCodigoEquipo();
			List<EliminarCargaDTO> cargas = input.getDatos().getCarga();
			
			if (cargas != null && !cargas.isEmpty()) {
				for (EliminarCargaDTO carga : cargas) {
					String merge = 	"MERGE INTO D_CARGA CA USING (" +
									"SELECT SEC.COD_V_CARGA, SEC.COD_V_ALMACEN_ORIGEN, DECODE(SEC.NUM_DIVISION, 0, 0, 1) NUM_DIVISION, " +
									"SEC.NUM_HUECO_OCUPADO NUM_HUECO_OCUPADO, SEC.NUM_PESO_OCUPADO NUM_PESO_OCUPADO " +
									"FROM S_EQUIPO_CARGA SEC " + 									
									"WHERE SEC.COD_N_EQUIPO = "+codigoEquipo+" AND SEC.COD_V_CARGA = '"+carga.getCodigoCarga()+"' AND SEC.COD_V_ALMACEN_ORIGEN = '"+carga.getCodigoCentroOrigen()+"'" +
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
									"END ";
					final Query query = getEntityManager().createNativeQuery(merge);
					query.executeUpdate();
				}
			}
		
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","DeleteRelacionEquipoCargaDAOImpl(GESADUAN)","actualizarCargas",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}			
		
	}
	
	@Transactional
	@Override
	public void actualizarOcupacion(Long codigoEquipo) {
		
		try {
		
			String update = "UPDATE D_EQUIPO_TRANSPORTE ET SET ET.NUM_OCUPACION = (SELECT NVL(SUM(EC.NUM_HUECO_OCUPADO), 0) FROM S_EQUIPO_CARGA EC WHERE EC.COD_N_EQUIPO = ?codigoEquipo) WHERE ET.COD_N_EQUIPO = ?codigoEquipo";
			
			final Query query = getEntityManager().createNativeQuery(update);
			query.setParameter("codigoEquipo", codigoEquipo);
			query.executeUpdate();
		
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","DeleteRelacionEquipoCargaDAOImpl(GESADUAN)","actualizarOcupacion",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}			
	}

}