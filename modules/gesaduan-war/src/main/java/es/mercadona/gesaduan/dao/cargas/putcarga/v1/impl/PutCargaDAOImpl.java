package es.mercadona.gesaduan.dao.cargas.putcarga.v1.impl;

import java.util.Date;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.fwk.data.DaoBaseImpl;
import es.mercadona.gesaduan.dao.cargas.putcarga.v1.PutCargaDAO;
import es.mercadona.gesaduan.dto.cargas.putcargas.v1.CargaDTO;
import es.mercadona.gesaduan.jpa.cargas.v1.CargasJPA;

public class PutCargaDAOImpl extends DaoBaseImpl<Long, CargasJPA> implements PutCargaDAO {

	@Inject
	private org.slf4j.Logger logger;	
	
	@Override
	public void setEntityClass() {
		this.entityClass = CargasJPA.class;		
	}
	
	@Override
	protected EntityManager getEntityManager() {
		return this.entityM;
	}
	
	@PersistenceContext
	private EntityManager entityM;
	
	@Transactional
	@Override
	public String crearCarga(CargasJPA crearCarga) {
		
		try {	
		
			String gesaduan = "GESADUAN";
			Date fechaHoy = new Date();
			crearCarga.setCodigoEstado(1);
			crearCarga.setNumeroDivisiones(0);
			crearCarga.setMcaPedidosSinValidar("S");
			crearCarga.setAplicacionOrigen(gesaduan);		
			crearCarga.setFechaCreacion(fechaHoy);
			crearCarga.setCodigoAplicacion(gesaduan);
			entityM.persist(crearCarga);
			entityM.flush();
			
			return crearCarga.getId().getCodigoCarga();
			
		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","PutCargaDAOImpl(GESADUAN)","crearCarga",e.getClass().getSimpleName(),e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}			
	}
	
	@Transactional
	@Override
	public Integer validarEstadoCarga(CargaDTO carga) {
		
		try {	
		
			final StringBuilder sql = new StringBuilder();
			
			String select = "SELECT COUNT(*) ";
			String from   = "FROM D_CARGA C ";
			String where  = "WHERE C.COD_V_CARGA = ?codigoCarga " + 
						"AND C.COD_V_ALMACEN_ORIGEN = ?codigoAlmacenOrigen " + 
						"AND ((C.COD_N_ESTADO <> 1 AND C.COD_N_ESTADO <> 2)  OR C.NUM_DIVISIONES > 0)";
					
			sql.append(select).append(from).append(where);		
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			query.setParameter("codigoCarga", carga.getCodigoCarga());
			query.setParameter("codigoAlmacenOrigen", carga.getCodigoAlmacenOrigen());
			Integer restultadoQuery = Integer.parseInt(query.getSingleResult().toString());
			
			return restultadoQuery;		
		
		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","PutCargaDAOImpl(GESADUAN)","validarEstadoCarga",e.getClass().getSimpleName(),e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}			
			
	}
	
	@Transactional
	@Override
	public String modificarCarga(CargasJPA datosCarga) {
		
		try {	
		
			CargasJPA put = entityM.find(CargasJPA.class, datosCarga.getId());
			if (datosCarga.getCodigoTipoCarga() != null) put.setCodigoTipoCarga(datosCarga.getCodigoTipoCarga());
			if (datosCarga.getCodigoTipoSuministro() != null) put.setCodigoTipoSuministro(datosCarga.getCodigoTipoSuministro());
			// if (datosCarga.getCategoria() != null) put.setCategoria(datosCarga.getCategoria());
			put.setCategoria(datosCarga.getCategoria());		
			//if (datosCarga.getCodigoProveedor() != null) put.setCodigoProveedor(datosCarga.getCodigoProveedor());
			put.setCodigoProveedor(datosCarga.getCodigoProveedor());		
			if (datosCarga.getCodigoCentroDestino() != null) put.setCodigoCentroDestino(datosCarga.getCodigoCentroDestino());
			if (datosCarga.getFechaServicio() != null) put.setFechaServicio(datosCarga.getFechaServicio());
			if (datosCarga.getFechaEntrega() != null) put.setFechaEntrega(datosCarga.getFechaEntrega());
			if (datosCarga.getNumeroHuecos() != null) put.setNumeroHuecos(datosCarga.getNumeroHuecos());
			if (datosCarga.getNumeroPesoTotal() != null) put.setNumeroPesoTotal(datosCarga.getNumeroPesoTotal());
			if (datosCarga.getMcaLPC() != null) put.setMcaLPC(datosCarga.getMcaLPC());
			if (datosCarga.getNumeroHuecosRestantes() != null) put.setNumeroHuecosRestantes(datosCarga.getNumeroHuecosRestantes());
			if (datosCarga.getNumeroPesoRestante() != null) put.setNumeroPesoRestante(datosCarga.getNumeroPesoRestante());
			if (datosCarga.getNumeroHuecos() != null) put.setNumeroHuecos(datosCarga.getNumeroHuecos());
			Date fechaHoy = new Date();
			put.setFechaModificacion(fechaHoy);
			// put.setCodigoAplicacion("GESADUAN");
			put.setUsuarioModificacion(datosCarga.getUsuarioModificacion());
			entityM.flush();
			
			return datosCarga.getId().getCodigoCarga();
			
		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","PutCargaDAOImpl(GESADUAN)","modificarCarga",e.getClass().getSimpleName(),e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}			
			
	}

}
