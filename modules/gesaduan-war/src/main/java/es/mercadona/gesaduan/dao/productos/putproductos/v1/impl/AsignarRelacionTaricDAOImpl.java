package es.mercadona.gesaduan.dao.productos.putproductos.v1.impl;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.fwk.data.DaoBaseImpl;
import es.mercadona.gesaduan.dao.productos.putproductos.v1.AsignarRelacionTaricDAO;
import es.mercadona.gesaduan.jpa.tarics.v1.TaricsProductoJPA;
import es.mercadona.gesaduan.jpa.tarics.v1.TaricsProductosPkJPA;

public class AsignarRelacionTaricDAOImpl extends DaoBaseImpl<TaricsProductosPkJPA, TaricsProductoJPA> implements AsignarRelacionTaricDAO{

	@Transactional
	@Override
	public void asigarTaricProducto(Long codigoTaric, Long codigoProducto, Long nuevoCodigoTaric, String codigoUsuario) {
		try {
			Date fechaActual = new Date();
			Calendar yesterday = Calendar.getInstance();
			yesterday.add(Calendar.DATE, -1);
			Date fechaAyer = yesterday.getTime();
			
			/* Modificar taric */
				
			if(codigoTaric != null && nuevoCodigoTaric != null) {
				if(nuevoCodigoTaric != 0 && !codigoTaric.equals(nuevoCodigoTaric)) {
					TaricsProductosPkJPA id = new TaricsProductosPkJPA();
					id.setCodigoProducto(codigoProducto);
					id.setCodigoTaric(codigoTaric);	
					
					entityM.createQuery("UPDATE TaricsProductoJPA tarics SET tarics.fechaFin = :fechaFin, tarics.fechaModificacion = :fechaActual, "
							+ "tarics.usuarioModificacion = :usuarioModificacion WHERE tarics.id.codigoTaric = :codigoTaric AND tarics.id.codigoProducto = :codigoProducto")
					.setParameter("fechaFin", fechaAyer).setParameter("fechaActual", fechaActual).setParameter("usuarioModificacion", codigoUsuario)
					.setParameter("codigoTaric", codigoTaric).setParameter("codigoProducto", codigoProducto)
					.executeUpdate();
					
					boolean yaExiste = existiaRelacion(codigoProducto, nuevoCodigoTaric);
					boolean yaExisteDia = existiaRelacionDia(codigoProducto, nuevoCodigoTaric);
					
					if(yaExiste) {
						entityM.createQuery("UPDATE TaricsProductoJPA tarics SET tarics.fechaFin = :fechaAyer, tarics.fechaModificacion = :fechaActual, "
								+ "tarics.usuarioModificacion = :usuarioModificacion "
								+ "WHERE tarics.id.codigoTaric = :codigoTaric AND tarics.id.codigoProducto = :codigoProducto "
								+ "AND (tarics.fechaFin IS NULL OR tarics.fechaFin >= :fechaActual) AND tarics.id.fechaInicio < :fechaActual ")
						.setParameter("codigoTaric", nuevoCodigoTaric).setParameter("codigoProducto", codigoProducto)
						.setParameter("fechaActual", fechaActual).setParameter("usuarioModificacion", codigoUsuario).setParameter("fechaAyer", fechaAyer)
						.executeUpdate();
						
						id.setCodigoTaric(nuevoCodigoTaric);
						id.setFechaInicio(fechaActual);
						
						TaricsProductoJPA nuevaRelacion = new TaricsProductoJPA();
						
						nuevaRelacion.setId(id);
						nuevaRelacion.setCodigoAplicacion("GESADUAN");
						nuevaRelacion.setUsuarioCreacion(codigoUsuario);
						nuevaRelacion.setFechaCreacion(fechaActual);
						
						entityM.persist(nuevaRelacion);
						entityM.flush();
					}else if(yaExisteDia){
						entityM.createQuery("UPDATE TaricsProductoJPA tarics "
								+ "SET "
								+ "tarics.fechaFin = :fechaFin, "
								+ "tarics.fechaModificacion = :fechaActual, "
								+ "tarics.usuarioModificacion = :usuarioModificacion "
								+ "WHERE tarics.id.codigoTaric = :codigoTaric "
								+ "AND tarics.id.codigoProducto = :codigoProducto "
								+ "AND tarics.id.fechaInicio = :fechaActual ")
						.setParameter("codigoTaric", nuevoCodigoTaric).setParameter("codigoProducto", codigoProducto)
						.setParameter("fechaActual", fechaActual).setParameter("usuarioModificacion", codigoUsuario).setParameter("fechaFin", null)
						.executeUpdate();
					}
					else {
						id.setCodigoTaric(nuevoCodigoTaric);
						id.setFechaInicio(fechaActual);
						
						TaricsProductoJPA nuevaRelacion = new TaricsProductoJPA();
						
						nuevaRelacion.setId(id);
						nuevaRelacion.setCodigoAplicacion("GESADUAN");
						nuevaRelacion.setUsuarioCreacion(codigoUsuario);
						nuevaRelacion.setFechaCreacion(fechaActual);
						
						entityM.persist(nuevaRelacion);
						entityM.flush();
					}					
				}else if(nuevoCodigoTaric == 0){
					entityM.createQuery("UPDATE TaricsProductoJPA tarics SET tarics.fechaFin = :fechaFin, tarics.fechaModificacion = :fechaActual, "
							+ "tarics.usuarioModificacion = :usuarioModificacion WHERE tarics.id.codigoTaric = :codigoTaric AND tarics.id.codigoProducto = :codigoProducto")
					.setParameter("fechaFin", fechaAyer).setParameter("fechaActual", fechaActual).setParameter("usuarioModificacion", codigoUsuario)
					.setParameter("codigoTaric", codigoTaric).setParameter("codigoProducto", codigoProducto)
					.executeUpdate();
				}							
				
			/* Crear taric */
				
			}else if(codigoTaric == null && nuevoCodigoTaric != null) {
				
				boolean yaExiste = existiaRelacion(codigoProducto, nuevoCodigoTaric);
				boolean yaExisteDia = existiaRelacionDia(codigoProducto, nuevoCodigoTaric);
				
				if(yaExiste) {
					entityM.createQuery("UPDATE TaricsProductoJPA tarics SET tarics.fechaFin = :fechaAyer, tarics.fechaModificacion = :fechaActual, "
							+ "tarics.usuarioModificacion = :usuarioModificacion "
							+ "WHERE tarics.id.codigoTaric = :codigoTaric AND tarics.id.codigoProducto = :codigoProducto "
							+ "AND (tarics.fechaFin IS NULL OR tarics.fechaFin >= :fechaActual) AND tarics.id.fechaInicio < :fechaActual ")
					.setParameter("codigoTaric", nuevoCodigoTaric).setParameter("codigoProducto", codigoProducto)
					.setParameter("fechaActual", fechaActual).setParameter("usuarioModificacion", codigoUsuario).setParameter("fechaAyer", fechaAyer)
					.executeUpdate();

					TaricsProductosPkJPA id = new TaricsProductosPkJPA();
					
					id.setCodigoProducto(codigoProducto);
					id.setCodigoTaric(nuevoCodigoTaric);
					id.setFechaInicio(fechaActual);
					
					TaricsProductoJPA nuevaRelacion = new TaricsProductoJPA();
					
					nuevaRelacion.setId(id);
					nuevaRelacion.setCodigoAplicacion("GESADUAN");
					nuevaRelacion.setUsuarioCreacion(codigoUsuario);
					nuevaRelacion.setFechaCreacion(fechaActual);
					
					entityM.persist(nuevaRelacion);
					entityM.flush();
				}else if(yaExisteDia){
					entityM.createQuery("UPDATE TaricsProductoJPA tarics "
							+ "SET "
							+ "tarics.fechaFin = :fechaFin, "
							+ "tarics.fechaModificacion = :fechaActual, "
							+ "tarics.usuarioModificacion = :usuarioModificacion "
							+ "WHERE tarics.id.codigoTaric = :codigoTaric "
							+ "AND tarics.id.codigoProducto = :codigoProducto "
							+ "AND tarics.id.fechaInicio = :fechaActual ")
					.setParameter("codigoTaric", nuevoCodigoTaric).setParameter("codigoProducto", codigoProducto)
					.setParameter("fechaActual", fechaActual).setParameter("usuarioModificacion", codigoUsuario).setParameter("fechaFin", null)
					.executeUpdate();
				}
				else {
					TaricsProductosPkJPA id = new TaricsProductosPkJPA();
					
					id.setCodigoProducto(codigoProducto);
					id.setCodigoTaric(nuevoCodigoTaric);
					id.setFechaInicio(fechaActual);
					
					TaricsProductoJPA nuevaRelacion = new TaricsProductoJPA();
					
					nuevaRelacion.setId(id);
					nuevaRelacion.setCodigoAplicacion("GESADUAN");
					nuevaRelacion.setUsuarioCreacion(codigoUsuario);
					nuevaRelacion.setFechaCreacion(fechaActual);
					
					entityM.persist(nuevaRelacion);
					entityM.flush();
				}				
			}
			
		} catch (Exception e) {
			
			throw new ApplicationException(e.getMessage());
		}
	}
	
	private boolean existiaRelacion(Long codigoProducto, Long codigoTaric) {
		
		Date fechaActual = new Date();
		
		Long cantidad = (Long) entityM.createQuery("SELECT count(tarics) from TaricsProductoJPA tarics WHERE tarics.id.codigoTaric = :codigoTaric AND tarics.id.codigoProducto = :codigoProducto"
				+ " AND (tarics.fechaFin IS NULL OR tarics.fechaFin >= :fechaActual) AND tarics.id.fechaInicio < :fechaActual")
				.setParameter("codigoTaric", codigoTaric).setParameter("codigoProducto", codigoProducto).setParameter("fechaActual", fechaActual).getSingleResult();
		
		if(cantidad > 0) {
			return true;
		}else {
			return false;
		}
	}
	
	private boolean existiaRelacionDia(Long codigoProducto, Long codigoTaric) {
		
		Date fechaActual = new Date();
		
		Long cantidad = (Long) entityM.createQuery("SELECT count(tarics) "
				+ "from TaricsProductoJPA tarics "
				+ "WHERE tarics.id.codigoTaric = :codigoTaric AND tarics.id.codigoProducto = :codigoProducto"
				+ " AND tarics.id.fechaInicio = :fechaActual")
				.setParameter("codigoTaric", codigoTaric).setParameter("codigoProducto", codigoProducto).setParameter("fechaActual", fechaActual).getSingleResult();
		
		if(cantidad > 0) {
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
		this.entityClass = TaricsProductoJPA.class;
		
	}

}
