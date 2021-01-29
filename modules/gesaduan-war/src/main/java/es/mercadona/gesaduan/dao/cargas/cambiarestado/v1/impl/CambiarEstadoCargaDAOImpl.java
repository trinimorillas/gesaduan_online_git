package es.mercadona.gesaduan.dao.cargas.cambiarestado.v1.impl;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.gesaduan.dao.BaseDAO;
import es.mercadona.gesaduan.dao.cargas.cambiarestado.v1.CambiarEstadoCargaDAO;
import es.mercadona.gesaduan.dto.cargas.cambiarestado.v1.CambiarEstadoCargaDTO;
import es.mercadona.gesaduan.dto.cargas.cambiarestado.v1.InputDatosCambiarEstadoCargaDTO;
import es.mercadona.gesaduan.jpa.cargas.v1.CargasJPA;

public class CambiarEstadoCargaDAOImpl extends BaseDAO<CargasJPA> implements CambiarEstadoCargaDAO {

	@Inject
	private org.slf4j.Logger logger;		
	
	@Override
	public void setEntityClass() {
		this.entityClass = CargasJPA.class;		
	}
	
	@PersistenceContext
	private EntityManager entityM;
	
	@Override
	public Integer getEstadoActual(CambiarEstadoCargaDTO carga) {
		
		try {			
		
			final StringBuilder sql = new StringBuilder();		
			
			String select = "SELECT COD_N_ESTADO ";
			String from   = "FROM D_CARGA ";
			String where  = "WHERE COD_V_CARGA = ?codigoCarga " +
							"AND COD_V_ALMACEN_ORIGEN = ?codigoAlmacenOrigen";
					
			sql.append(select).append(from).append(where);		
			final Query query = getEntityManager().createNativeQuery(sql.toString());		
			query.setParameter("codigoCarga", carga.getCodigoCarga());
			query.setParameter("codigoAlmacenOrigen", carga.getCodigoAlmacenOrigen());
			Integer restultadoQuery = Integer.parseInt(query.getSingleResult().toString());
			
			return restultadoQuery;
			
		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","CambiarEstadoCargaDAOImpl(GESADUAN)","getEstadoActual",e.getClass().getSimpleName(),e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}			
	}
	
	@Override
	public Integer comprobarPedidosAsociados(CambiarEstadoCargaDTO carga) {
		
		try {
		
			final StringBuilder sql = new StringBuilder();		
			
			String select = "SELECT COUNT(*) ";
			String from   = "FROM D_CARGA C " +
							"INNER JOIN S_CARGA_PEDIDO CP ON (CP.COD_V_CARGA = C.COD_V_CARGA " +
							"AND CP.COD_V_ALMACEN_ORIGEN = C.COD_V_ALMACEN_ORIGEN) ";
			String where =  "WHERE C.COD_V_CARGA = ?codigoCarga " + 
							"AND C.COD_V_ALMACEN_ORIGEN = ?codigoAlmacenOrigen ";
					
			sql.append(select).append(from).append(where);		
			final Query query = getEntityManager().createNativeQuery(sql.toString());		
			query.setParameter("codigoCarga", carga.getCodigoCarga());
			query.setParameter("codigoAlmacenOrigen", carga.getCodigoAlmacenOrigen());
			Integer restultadoQuery = Integer.parseInt(query.getSingleResult().toString());
			
			return restultadoQuery;
		
		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","CambiarEstadoCargaDAOImpl(GESADUAN)","comprobarPedidosAsociados",e.getClass().getSimpleName(),e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}		
	}
	
	@Transactional
	@Override
	public void cambiarEstado(InputDatosCambiarEstadoCargaDTO datos, CambiarEstadoCargaDTO carga) {
		
		String update ="";
		try {
		
			// Si la carga pasa de no validada a no asignada se inicializan los campos de restantes
			if ((carga.getCodigoEstadoActual() == 1 || carga.getCodigoEstadoActual() == 5) && carga.getCodigoEstado() == 2) {
				update = "UPDATE D_CARGA C SET " + 
						 "   COD_N_ESTADO = ?codigoEstado, " + 
						 "   MCA_PEDIDOS_SIN_VALIDAR = 'N', " + 
						 "   NUM_DIVISIONES = 0, " +
						 "   NUM_HUECOS = DECODE(COD_V_APLICACION_ORIGEN,'MTR',NUM_HUECOS_ORIGEN,NUM_HUECOS), " +						 
						 "   NUM_HUECOS_RESTANTES = DECODE(COD_V_APLICACION_ORIGEN,'MTR',NUM_HUECOS_ORIGEN,NUM_HUECOS), " +
						 "   NUM_PESO_RESTANTE = NUM_PESO_TOTAL, " +						 
						 "   FEC_DT_MODIFICACION = SYSDATE, " +
						 "   COD_V_USUARIO_CREACION = ?codigoUsuario, " +
						 "   FEC_DT_VALIDACION = SYSDATE, " +
						 "   COD_V_USUARIO_VALIDACION = ?codigoUsuario " +						 
						 "WHERE " + 
						 "   COD_V_ALMACEN_ORIGEN = ?codigoAlmacenOrigen AND " + 
						 "   COD_V_CARGA = ?codigoCarga";				
			} else {
				update  = "UPDATE D_CARGA C SET " + 
						  "   COD_N_ESTADO = ?codigoEstado, ";
				if (carga.getCodigoEstado() == 2) {
					update += "   MCA_PEDIDOS_SIN_VALIDAR = 'N', " +
							  "   FEC_DT_VALIDACION = SYSDATE, " +
							  "   COD_V_USUARIO_VALIDACION = ?codigoUsuario, ";						 					
				}
				if (carga.getCodigoEstado() == 5) update += "   MCA_PEDIDOS_SIN_VALIDAR = 'S', ";				
				update += "   FEC_DT_MODIFICACION = SYSDATE, " +
						  "   COD_V_USUARIO_CREACION = ?codigoUsuario " +
						  "WHERE " + 
						  "   COD_V_ALMACEN_ORIGEN = ?codigoAlmacenOrigen AND " + 
						  "   COD_V_CARGA = ?codigoCarga";
			}
					
			final Query query = getEntityManager().createNativeQuery(update);	
			query.setParameter("codigoEstado", carga.getCodigoEstado());	
			query.setParameter("codigoCarga", carga.getCodigoCarga());
			query.setParameter("codigoAlmacenOrigen", carga.getCodigoAlmacenOrigen());
			query.setParameter("codigoUsuario", datos.getMetadatos().getCodigoUsuario());
			query.executeUpdate();
		
		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","CambiarEstadoCargaDAOImpl(GESADUAN)","cambiarEstado",e.getClass().getSimpleName(),e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}
		
	}
	
	@Transactional
	@Override
	public void validarPedidosAsociados(InputDatosCambiarEstadoCargaDTO datos, CambiarEstadoCargaDTO carga) {
		
		try {
		
			String update = "UPDATE S_CARGA_PEDIDO " +
					"SET MCA_PEDIDO_VALIDADO = 'S', " +
					"FEC_DT_MODIFICACION = SYSDATE, " +
					"COD_V_USUARIO_MODIFICACION = ?codigoUsuario " +
					"WHERE COD_V_CARGA = ?codigoCarga " +
					"AND COD_V_ALMACEN_ORIGEN = ?codigoAlmacenOrigen";
					
			final Query query = getEntityManager().createNativeQuery(update);		
			query.setParameter("codigoCarga", carga.getCodigoCarga());
			query.setParameter("codigoAlmacenOrigen", carga.getCodigoAlmacenOrigen());
			query.setParameter("codigoUsuario", datos.getMetadatos().getCodigoUsuario());
			query.executeUpdate();
		
		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","CambiarEstadoCargaDAOImpl(GESADUAN)","validarPedidosAsociados",e.getClass().getSimpleName(),e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}		
	}
}