package es.mercadona.gesaduan.dao.productos.putproductos.v1.impl;

import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.fwk.data.DaoBaseImpl;
import es.mercadona.gesaduan.business.alertas.crearalertas.v1.CrearAlertasService;
import es.mercadona.gesaduan.dao.productos.putproductos.v1.AsignarRelacionReaDAO;
import es.mercadona.gesaduan.jpa.tarics.v1.ReasProductosJPA;
import es.mercadona.gesaduan.jpa.tarics.v1.ReasProductosPkJPA;

public class AsignarRelacionReaDAOImpl extends DaoBaseImpl<ReasProductosPkJPA, ReasProductosJPA> implements AsignarRelacionReaDAO{

	@Inject
	private CrearAlertasService crearAlertasService;
	
	@Transactional
	@Override
	public void asignarReaProducto(String codigoRea, Long codigoProducto, String nuevoCodigoRea, String codigoUsuario) {
		try {
			Date fechaActual = new Date(); 
			Calendar yesterday = Calendar.getInstance();
			yesterday.add(Calendar.DATE, -1);
			Date fechaAyer = yesterday.getTime();
			
			/* Dar de baja a una relacion y crear la nueva  SI es 0 se borra la relacion actual */
						
			if(codigoRea != null && nuevoCodigoRea != null) {
				if(!nuevoCodigoRea.equals("0") && !codigoRea.equals(nuevoCodigoRea)) {
					ReasProductosPkJPA id = new ReasProductosPkJPA();
					id.setCodigoProducto(codigoProducto);
					id.setCodigoRea(codigoRea);
					
					entityM.createQuery("UPDATE ReasProductosJPA reas SET reas.usuarioModificacion = :codigoUsuario, "
							+ "reas.fechaModificacion = :fechaActual, reas.fechaFin = :fechaFin WHERE reas.id.codigoRea = :codigoRea AND reas.id.codigoProducto = :codigoProducto")
					.setParameter("fechaFin", fechaAyer).setParameter("codigoUsuario", codigoUsuario)
					.setParameter("fechaActual", fechaActual).setParameter("codigoProducto", codigoProducto).setParameter("codigoRea", codigoRea)
					.executeUpdate();
					
					boolean yaExiste = existiaRelacion(codigoProducto, nuevoCodigoRea);
					boolean yaExisteHoy = existeRelacionDia(codigoProducto, nuevoCodigoRea);
					
					if(yaExiste) {
						entityM.createQuery("UPDATE ReasProductosJPA reas SET reas.fechaFin = :fechaAyer, reas.usuarioModificacion = :codigoUsuario, reas.fechaModificacion = :fechaActual"
								+ " WHERE reas.id.codigoRea = :codigoRea AND reas.id.codigoProducto = :codigoProducto "
								+ "AND (reas.fechaFin IS NULL OR reas.fechaFin >= :fechaActual) AND reas.id.fechaInicio < :fechaActual ")
						.setParameter("codigoRea", nuevoCodigoRea).setParameter("codigoProducto", codigoProducto)
						.setParameter("codigoUsuario", codigoUsuario).setParameter("fechaActual", fechaActual).setParameter("fechaAyer", fechaAyer)
						.executeUpdate();
						
						id.setCodigoRea(nuevoCodigoRea);
						id.setFechaInicio(fechaActual);
						
						ReasProductosJPA nuevaRelacion = new ReasProductosJPA();
						
						nuevaRelacion.setId(id);
						nuevaRelacion.setCodigoAplicacion("GESADUAN");
						nuevaRelacion.setFechaCreacion(fechaActual);
						nuevaRelacion.setUsuarioCreacion(codigoUsuario);
						entityM.persist(nuevaRelacion);
					}else if(yaExisteHoy) {
						entityM.createQuery("UPDATE ReasProductosJPA reas "
								+ "SET reas.fechaFin = :fechaFin, reas.usuarioModificacion = :codigoUsuario, reas.fechaModificacion = :fechaActual "
								+ "WHERE reas.id.codigoRea = :codigoRea "
								+ "AND reas.id.codigoProducto = :codigoProducto "
								+ "AND reas.id.fechaInicio = :fechaActual ")
						.setParameter("codigoRea", nuevoCodigoRea).setParameter("codigoProducto", codigoProducto)
						.setParameter("codigoUsuario", codigoUsuario).setParameter("fechaActual", fechaActual).setParameter("fechaFin", null)
						.executeUpdate();		
					}
					else {					
						id.setCodigoRea(nuevoCodigoRea);
						id.setFechaInicio(fechaActual);
						
						ReasProductosJPA nuevaRelacion = new ReasProductosJPA();
						
						nuevaRelacion.setId(id);
						nuevaRelacion.setCodigoAplicacion("GESADUAN");
						nuevaRelacion.setFechaCreacion(fechaActual);
						nuevaRelacion.setUsuarioCreacion(codigoUsuario);
						entityM.persist(nuevaRelacion);
					}

				}else if(nuevoCodigoRea.equals("0")){
					ReasProductosPkJPA id = new ReasProductosPkJPA();
					id.setCodigoProducto(codigoProducto);
					id.setCodigoRea(codigoRea);
					
					entityM.createQuery("UPDATE ReasProductosJPA reas SET reas.usuarioModificacion = :codigoUsuario, "
							+ "reas.fechaModificacion = :fechaActual, reas.fechaFin = :fechaFin WHERE reas.id.codigoRea = :codigoRea AND reas.id.codigoProducto = :codigoProducto")
					.setParameter("fechaFin", fechaAyer).setParameter("codigoUsuario", codigoUsuario)
					.setParameter("fechaActual", fechaActual).setParameter("codigoProducto", codigoProducto).setParameter("codigoRea", codigoRea)
					.executeUpdate();
				}
				
			/* Crear Rea */
				
			}else if(codigoRea == null && nuevoCodigoRea != null) {
				
				boolean yaExiste = existiaRelacion(codigoProducto, nuevoCodigoRea);
				boolean yaExisteHoy = existeRelacionDia(codigoProducto, nuevoCodigoRea);
				
				if(yaExiste) {
					entityM.createQuery("UPDATE ReasProductosJPA reas SET reas.fechaFin = :fechaAyer, reas.usuarioModificacion = :codigoUsuario, reas.fechaModificacion = :fechaActual "
							+ "WHERE reas.id.codigoRea = :codigoRea AND reas.id.codigoProducto = :codigoProducto "
							+ "AND (reas.fechaFin IS NULL OR reas.fechaFin >= :fechaActual) AND reas.id.fechaInicio < :fechaActual ")
					.setParameter("codigoRea", nuevoCodigoRea).setParameter("codigoProducto", codigoProducto)
					.setParameter("codigoUsuario", codigoUsuario).setParameter("fechaActual", fechaActual).setParameter("fechaAyer", fechaAyer)
					.executeUpdate();
					
					ReasProductosPkJPA id = new ReasProductosPkJPA();
					
					id.setCodigoProducto(codigoProducto);
					id.setCodigoRea(nuevoCodigoRea);
					id.setFechaInicio(fechaActual);
					
					ReasProductosJPA nuevaRelacion = new ReasProductosJPA();
					
					nuevaRelacion.setId(id);
					nuevaRelacion.setCodigoAplicacion("GESADUAN");
					nuevaRelacion.setUsuarioCreacion(codigoUsuario);
					nuevaRelacion.setFechaCreacion(fechaActual);
					
					entityM.persist(nuevaRelacion);
					entityM.flush();
				}else if(yaExisteHoy) {
					entityM.createQuery("UPDATE ReasProductosJPA reas SET reas.fechaFin = :fechaFin, reas.usuarioModificacion = :codigoUsuario, reas.fechaModificacion = :fechaActual "
							+ "WHERE reas.id.codigoRea = :codigoRea AND reas.id.codigoProducto = :codigoProducto AND reas.id.fechaInicio = :fechaActual ")
					.setParameter("codigoRea", nuevoCodigoRea).setParameter("codigoProducto", codigoProducto)
					.setParameter("codigoUsuario", codigoUsuario).setParameter("fechaActual", fechaActual).setParameter("fechaFin", null)
					.executeUpdate();				
				}
				
				else {
					ReasProductosPkJPA id = new ReasProductosPkJPA();
					
					id.setCodigoProducto(codigoProducto);
					id.setCodigoRea(nuevoCodigoRea);
					id.setFechaInicio(fechaActual);
					
					ReasProductosJPA nuevaRelacion = new ReasProductosJPA();
					
					nuevaRelacion.setId(id);
					nuevaRelacion.setCodigoAplicacion("GESADUAN");
					nuevaRelacion.setUsuarioCreacion(codigoUsuario);
					nuevaRelacion.setFechaCreacion(fechaActual);
					
					entityM.persist(nuevaRelacion);
					entityM.flush();
				}
			}
			
		}catch (Exception e) {			
			throw new ApplicationException(e.getMessage());
		}
	}
	
	private boolean existiaRelacion(Long codigoProducto, String codigoRea) {
		
		Date fechaActual = new Date();
		
		Long cantidad = (Long) entityM.createQuery("SELECT count(reas) from ReasProductosJPA reas WHERE reas.id.codigoRea = :codigoRea AND reas.id.codigoProducto = :codigoProducto "
				+ "AND (reas.fechaFin IS NULL OR reas.fechaFin >= :fechaActual) AND reas.id.fechaInicio < :fechaActual")
				.setParameter("codigoRea", codigoRea).setParameter("codigoProducto", codigoProducto).setParameter("fechaActual", fechaActual)
				.getSingleResult();
		
		if(cantidad > 0) {
			return true;
		}else {
			return false;
		}
	}
	
	private boolean existeRelacionDia(Long codigoProducto, String codigoRea) {
		
		Date fechaActual = new Date();
		
		Long cantidad = (Long) entityM.createQuery("SELECT count(reas) "
				+ "from ReasProductosJPA reas "
				+ "WHERE reas.id.codigoRea = :codigoRea "
				+ "AND reas.id.codigoProducto = :codigoProducto "
				+ "AND reas.id.fechaInicio = :fechaActual")
				.setParameter("codigoRea", codigoRea).setParameter("codigoProducto", codigoProducto).setParameter("fechaActual", fechaActual)
				.getSingleResult();
		
		if(cantidad > 0) {
			return true;
		}else {
			return false;
		}
	}
	
	@Transactional
	@Override
	public void desasociarReaProducto(Long codigoProducto, String codigoUsuario) {
		if(existeRelacionProducto(codigoProducto)) {
			Calendar yesterday = Calendar.getInstance();
			yesterday.add(Calendar.DATE, -1);
			Date fechaFin = yesterday.getTime();
			Date fechaActual = new Date();
			
			entityM.createQuery("UPDATE ReasProductosJPA reas SET reas.fechaFin = :fechaFin, reas.usuarioModificacion = :codigoUsuario, reas.fechaModificacion = :fechaActual "
					+ "WHERE reas.id.codigoProducto = :codigoProducto ")
			.setParameter("codigoProducto", codigoProducto).setParameter("fechaActual", fechaActual)
			.setParameter("codigoUsuario", codigoUsuario).setParameter("fechaFin", fechaFin)
			.executeUpdate();
			
			Integer codigoAlerta = 6;
			String codigoElemento = String.valueOf(codigoProducto);
			crearAlertasService.crearAlerta(codigoAlerta, codigoElemento, codigoUsuario);
		}		
	}
	
	private boolean existeRelacionProducto(Long codigoProducto) {
		Date fechaActual = new Date();
		
		Long cantidad = (Long) entityM.createQuery("SELECT count(reas) from ReasProductosJPA reas WHERE reas.id.codigoProducto = :codigoProducto "
				+ "AND (reas.fechaFin IS NULL OR reas.fechaFin >= :fechaActual)")
				.setParameter("codigoProducto", codigoProducto).setParameter("fechaActual", fechaActual)
				.getSingleResult();
		
		if(cantidad == 1) {
			return true;
		}else {
			return false;
		}
	}
	
	@PersistenceContext
	private EntityManager entityM;

	@Override
	protected EntityManager getEntityManager() {
		return this.entityM;
	}

	@Override
	public void setEntityClass() {
		this.entityClass = ReasProductosJPA.class;
		
	}



}
