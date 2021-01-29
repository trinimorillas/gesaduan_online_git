package es.mercadona.gesaduan.dao.equipotransporte.putrelacionequipocarga.v1.impl;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.gesaduan.dao.BaseDAO;
import es.mercadona.gesaduan.dao.equipotransporte.putrelacionequipocarga.v1.PutRelacionEquipoCargaDAO;
import es.mercadona.gesaduan.dto.equipotransporte.putrelacionequipocarga.v1.CargaDTO;
import es.mercadona.gesaduan.dto.equipotransporte.putrelacionequipocarga.v1.InputDatosCrearRelacionEquipoCargaDTO;
import es.mercadona.gesaduan.jpa.equipotransporte.v1.EquipoCargaJPA;
import es.mercadona.gesaduan.jpa.equipotransporte.v1.EquipoCargaPkJPA;

public class PutRelacionEquipoCargaDAOImpl extends BaseDAO<EquipoCargaJPA> implements PutRelacionEquipoCargaDAO {

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
	public Integer cargasNoProcesadas(InputDatosCrearRelacionEquipoCargaDTO input) {
		
		Integer restultadoQuery;
		
		try {
		
			final StringBuilder sql = new StringBuilder();
				
			String select = "SELECT COUNT(*) ";
			String from   = "FROM D_CARGA CA ";
			String where  = "WHERE (CA.COD_V_ALMACEN_ORIGEN, CA.COD_V_CARGA) IN (";
			List<CargaDTO> cargas = input.getDatos().getCarga();
			
			for (int i = 0; i < cargas.size(); i++) {
				if (cargas.size()-1 == i) where += "('"+cargas.get(i).getCodigoCentroOrigen()+"', '"+cargas.get(i).getCodigoCarga()+"')";
				else where += "('"+cargas.get(i).getCodigoCentroOrigen()+"', '"+cargas.get(i).getCodigoCarga()+"'), ";
			}
			where += ") AND CA.COD_N_ESTADO <> 2";
			
			sql.append(select).append(from).append(where);		
			final Query query = getEntityManager().createNativeQuery(sql.toString());	
			restultadoQuery = Integer.parseInt(query.getSingleResult().toString());
		
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","PutRelacionEquipoCargaDAOImpl(GESADUAN)","cargasNoProcesadas",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}		
		
		return restultadoQuery;		
	}
	
	@Transactional
	@Override
	public Integer comprobarRelaciones(InputDatosCrearRelacionEquipoCargaDTO input, CargaDTO carga) {
		
		Integer restultadoQuery;
		
		try {
		
			String select = "SELECT COUNT(*) " +
							"FROM S_EQUIPO_CARGA EC " +
							"WHERE EC.COD_N_EQUIPO = " + input.getDatos().getCodigoEquipo() + " " +
							"AND EC.COD_V_ALMACEN_ORIGEN = '" + carga.getCodigoCentroOrigen() + "' " +
							"AND EC.COD_V_CARGA = '" + carga.getCodigoCarga() + "'";
			final Query query = getEntityManager().createNativeQuery(select);	
			restultadoQuery = Integer.parseInt(query.getSingleResult().toString());
		
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","PutRelacionEquipoCargaDAOImpl(GESADUAN)","comprobarRelaciones",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}			
		
		return restultadoQuery;
	}
	
	@Transactional
	@Override
	public void actualizarRelacion(InputDatosCrearRelacionEquipoCargaDTO input, CargaDTO carga) {	
		
		try {
		
			String sql = "UPDATE S_EQUIPO_CARGA EC " + 
					"SET EC.NUM_HUECO_OCUPADO = EC.NUM_HUECO_OCUPADO + ?numeroHuecosOcupados, " + 
					"EC.NUM_PESO_OCUPADO = EC.NUM_PESO_OCUPADO + ?numeroPesoOcupado, " + 
					"EC.FEC_DT_MODIFICACION = SYSDATE, " + 
					"EC.COD_V_USUARIO_MODIFICACION = ?codigoUsuario " + 
					"WHERE EC.COD_N_EQUIPO = ?codigoEquipo " + 
					"AND EC.COD_V_ALMACEN_ORIGEN = ?codigoCentroOrigen " + 
					"AND EC.COD_V_CARGA = ?codigoCarga";
			
			final Query query = getEntityManager().createNativeQuery(sql);
			query.setParameter("numeroHuecosOcupados", carga.getNumeroHuecosOcupados());
			query.setParameter("numeroPesoOcupado", carga.getNumeroPesoOcupado());
			query.setParameter("codigoUsuario", input.getMetadatos().getCodigoUsuario());
			query.setParameter("codigoEquipo", input.getDatos().getCodigoEquipo());
			query.setParameter("codigoCentroOrigen", carga.getCodigoCentroOrigen());
			query.setParameter("codigoCarga", carga.getCodigoCarga());
			query.executeUpdate();
		
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","PutRelacionEquipoCargaDAOImpl(GESADUAN)","actualizarRelacion",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}			
	}
		
	@Transactional
	@Override
	public void actualizarDivisionesCarga(InputDatosCrearRelacionEquipoCargaDTO input, CargaDTO carga) {
		
		try {
		
			String sql = "MERGE INTO D_CARGA CA " +
						 "USING ( " +                                                    
						 "SELECT EC.COD_N_EQUIPO,EC.COD_V_CARGA,EC.COD_V_ALMACEN_ORIGEN,EC.NUM_HUECO_OCUPADO " +         
						 "FROM S_EQUIPO_CARGA EC " +
						 "WHERE EC.COD_N_EQUIPO = ?codigoEquipo " +
						 ") SEC ON (CA.COD_V_CARGA = SEC.COD_V_CARGA AND " +
						 "CA.COD_V_ALMACEN_ORIGEN = SEC.COD_V_ALMACEN_ORIGEN) " +
						 "WHEN MATCHED THEN UPDATE SET " +
						 "CA.NUM_DIVISIONES = DECODE(CA.NUM_HUECOS,SEC.NUM_HUECO_OCUPADO,0,CA.NUM_DIVISIONES), " +
						 "CA.COD_N_ESTADO = DECODE(CA.NUM_HUECOS,SEC.NUM_HUECO_OCUPADO,3,CA.COD_N_ESTADO), " +
						 "CA.NUM_HUECOS_RESTANTES = CA.NUM_HUECOS_RESTANTES - ?numeroHuecosOcupados, " +
						 "CA.NUM_PESO_RESTANTE = CA.NUM_PESO_RESTANTE - ?numeroPesoOcupado, " +
						 "CA.FEC_DT_MODIFICACION = SYSDATE, " +
						 "CA.COD_V_USUARIO_MODIFICACION = ?codigoUsuario " +
						 "WHERE CA.COD_V_ALMACEN_ORIGEN = ?codigoCentroOrigen " +
						 "AND CA.COD_V_CARGA = ?codigoCarga";
			
			final Query query = getEntityManager().createNativeQuery(sql);
			query.setParameter("codigoUsuario", input.getMetadatos().getCodigoUsuario());
			query.setParameter("codigoEquipo", input.getDatos().getCodigoEquipo());
			query.setParameter("codigoCentroOrigen", carga.getCodigoCentroOrigen());
			query.setParameter("codigoCarga", carga.getCodigoCarga());
			query.setParameter("numeroHuecosOcupados", carga.getNumeroHuecosOcupados());
			query.setParameter("numeroPesoOcupado", carga.getNumeroPesoOcupado());
			query.executeUpdate();
			
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","PutRelacionEquipoCargaDAOImpl(GESADUAN)","actualizarDivisionesCarga",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}
		
	}
		
	@Transactional
	@Override
	public void actualizarDivisionesRelacion(InputDatosCrearRelacionEquipoCargaDTO input, CargaDTO carga) {
		
		try {
		
			String sql = "UPDATE S_EQUIPO_CARGA EC " +
					"SET NUM_DIVISION = 0, " +
					"EC.FEC_DT_MODIFICACION = SYSDATE, " +
					"EC.COD_V_USUARIO_MODIFICACION = ?codigoUsuario " +
					"WHERE EC.COD_N_EQUIPO = ?codigoEquipo " +
					"AND EC.COD_V_ALMACEN_ORIGEN = ?codigoCentroOrigen " +
					"AND EC.COD_V_CARGA = ?codigoCarga " +
					"AND EXISTS (" +
					"SELECT 1 " +
					"FROM D_CARGA C " +
					"WHERE C.COD_V_ALMACEN_ORIGEN = EC.COD_V_ALMACEN_ORIGEN " +
					"AND C.COD_V_CARGA = EC.COD_V_CARGA " +
					"AND C.NUM_DIVISIONES = 0)";
			
			final Query query = getEntityManager().createNativeQuery(sql);
			query.setParameter("codigoUsuario", input.getMetadatos().getCodigoUsuario());
			query.setParameter("codigoEquipo", input.getDatos().getCodigoEquipo());
			query.setParameter("codigoCentroOrigen", carga.getCodigoCentroOrigen());
			query.setParameter("codigoCarga", carga.getCodigoCarga());
			query.executeUpdate();
		
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","PutRelacionEquipoCargaDAOImpl(GESADUAN)","actualizarDivisionesRelacion",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}		
		
	}
	
	@Transactional
	@Override
	public void putRelacionEquipoCarga(InputDatosCrearRelacionEquipoCargaDTO input, CargaDTO carga) {	
		
		try {
			
			Long codigoEquipo = input.getDatos().getCodigoEquipo();	
			Date fechaActual = new Date();
			String aplicacion = "GESADUAN", usuario = input.getMetadatos().getCodigoUsuario().toUpperCase();
			
			EquipoCargaPkJPA id = new EquipoCargaPkJPA();
			String codigoCarga = carga.getCodigoCarga();
			String codigoCentroOrigen = carga.getCodigoCentroOrigen();
			
			id.setCodigoEquipo(codigoEquipo);
			id.setCodigoCarga(codigoCarga);
			id.setCodigoCentroOrigen(codigoCentroOrigen);
			
			EquipoCargaJPA crearEquipoCarga = new EquipoCargaJPA();
			
			crearEquipoCarga.setId(id);
			crearEquipoCarga.setFechaCreacion(fechaActual);
			crearEquipoCarga.setUsuarioCreacion(usuario);
			crearEquipoCarga.setCodigoAplicacion(aplicacion);
			crearEquipoCarga.setNumeroDivision(carga.getNumeroDivision());
			crearEquipoCarga.setNumeroHuecosOcupados(carga.getNumeroHuecosOcupados());
			crearEquipoCarga.setNumeroPesoOcupado(carga.getNumeroPesoOcupado());
			entityM.persist(crearEquipoCarga);
		
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","PutRelacionEquipoCargaDAOImpl(GESADUAN)","putRelacionEquipoCarga",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}			
		
	}
	
	@Transactional
	@Override
	public void actualizarCarga(InputDatosCrearRelacionEquipoCargaDTO input, CargaDTO carga) {
		
		try {
			
			String update = "UPDATE D_CARGA CA SET " +
							"CA.NUM_DIVISIONES = DECODE(CA.NUM_HUECOS, "+carga.getNumeroHuecosOcupados()+", 0, CA.NUM_DIVISIONES + 1), " +
							"CA.NUM_HUECOS_RESTANTES = CA.NUM_HUECOS_RESTANTES - "+carga.getNumeroHuecosOcupados()+", " +
							"CA.NUM_PESO_RESTANTE = CA.NUM_PESO_RESTANTE - "+carga.getNumeroPesoOcupado()+", " +
							"CA.COD_N_ESTADO = DECODE(CA.NUM_HUECOS_RESTANTES - "+carga.getNumeroHuecosOcupados()+", 0, 3, CA.COD_N_ESTADO) " +
							"WHERE CA.COD_V_ALMACEN_ORIGEN = '"+carga.getCodigoCentroOrigen()+"' AND CA.COD_V_CARGA = '"+carga.getCodigoCarga()+"'";
			
			final Query query = getEntityManager().createNativeQuery(update);
			query.executeUpdate();
		
		} catch(Exception ex) {
			this.logger.error("({}-{}) ERROR - {} {}","PutRelacionEquipoCargaDAOImpl(GESADUAN)","actualizarCarga",ex.getClass().getSimpleName(),ex.getMessage());	
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
			this.logger.error("({}-{}) ERROR - {} {}","PutRelacionEquipoCargaDAOImpl(GESADUAN)","actualizarOcupacion",ex.getClass().getSimpleName(),ex.getMessage());	
			throw new ApplicationException(ex.getMessage());			
		}			
		
	}

}