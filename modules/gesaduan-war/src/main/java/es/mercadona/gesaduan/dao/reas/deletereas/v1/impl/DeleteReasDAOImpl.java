package es.mercadona.gesaduan.dao.reas.deletereas.v1.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import es.mercadona.fwk.data.DaoBaseImpl;
import es.mercadona.gesaduan.business.alertas.crearalertas.v1.CrearAlertasService;
import es.mercadona.gesaduan.dao.reas.deletereas.v1.DeleteReasDAO;
import es.mercadona.gesaduan.dto.reas.deletereas.v1.InputDeleteReasDTO;
import es.mercadona.gesaduan.jpa.reas.deletereas.v1.ReasDeleteJPA;

@SuppressWarnings("unchecked")
@Stateless
public class DeleteReasDAOImpl extends DaoBaseImpl<String, ReasDeleteJPA> implements DeleteReasDAO{
	
	@Inject
	private CrearAlertasService crearAlertasService;
	
	@PersistenceContext
	private EntityManager entityM;

	@Override
	protected EntityManager getEntityManager() {
		return this.entityM;
	}

	@Override
	public void setEntityClass() {
		entityClass = ReasDeleteJPA.class;
		
	}

	@Transactional
	@Override
	public ReasDeleteJPA deleteRea(InputDeleteReasDTO input) {
		
		String codigoRea = input.getCodigoRea();
		ReasDeleteJPA reasDelete = entityM.find(ReasDeleteJPA.class, codigoRea);
		entityM.remove(reasDelete);
		entityM.flush();
		
		return reasDelete;
	}
	
	@Transactional
	@Override
	public void desasociarReaProductos(String codigoRea, String codigoUsuario) {
		Calendar yesterday = Calendar.getInstance();
		yesterday.add(Calendar.DATE, -1);
		Date fechaFin = yesterday.getTime();
		Date fechaActual = new Date();
		
		/* Buscar todos los productos con una relacion vigente con el Rea informado */
		
		List<Long> productos = entityM.createQuery(
				"SELECT reas.id.codigoProducto FROM ReasProductosJPA reas WHERE reas.id.codigoRea = :codigoRea AND (reas.fechaFin IS NULL OR reas.fechaFin >= :fecha) ")
				.setParameter("codigoRea", codigoRea).setParameter("fecha", fechaActual).getResultList();
		
		/* Dar por finalizadas las relaciones de reas productos vigentes */
		
		entityM.flush();
		entityM.clear();
		
		if(!productos.isEmpty()) {
		
			entityM.createQuery("UPDATE ReasProductosJPA reas SET reas.fechaFin = :fechaFin, reas.usuarioModificacion = :codigoUsuario, reas.fechaModificacion = :fechaActual "
					+ "WHERE reas.id.codigoProducto IN :codigosProducto AND reas.id.codigoRea = :codigoRea AND (reas.fechaFin IS NULL OR reas.fechaFin >= :fechaActual) ")
			.setParameter("codigosProducto", productos).setParameter("fechaActual", fechaActual).setParameter("codigoRea", codigoRea)
			.setParameter("codigoUsuario", codigoUsuario).setParameter("fechaFin", fechaFin)
			.executeUpdate();
			
			
			Integer codigoAlerta = 6;
			for (Long tmp : productos) {
				String codigoElemento = String.valueOf(tmp);
				crearAlertasService.crearAlerta(codigoAlerta, codigoElemento, codigoUsuario);
			}
		}
		
	}
	
	

}
