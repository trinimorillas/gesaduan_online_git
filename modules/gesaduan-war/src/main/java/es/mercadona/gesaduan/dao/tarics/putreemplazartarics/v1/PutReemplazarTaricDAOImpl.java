package es.mercadona.gesaduan.dao.tarics.putreemplazartarics.v1;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import es.mercadona.fwk.auth.SecurityService;
import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.fwk.core.exceptions.ExceptionUtils;
import es.mercadona.gesaduan.business.alertas.crearalertas.v1.CrearAlertasService;
import es.mercadona.gesaduan.dao.tarics.putreemplazartarics.PutReemplazarTaricsDAO;
import es.mercadona.gesaduan.dto.tarics.putreemplazartaric.v1.restful.InputReemplazarTaricsDTO;
import es.mercadona.gesaduan.jpa.tarics.v1.TaricsProductoJPA;
import es.mercadona.gesaduan.jpa.tarics.v1.TaricsProductosPkJPA;

public class PutReemplazarTaricDAOImpl implements PutReemplazarTaricsDAO {
	
	@PersistenceContext
	private EntityManager entityM;

	@Inject
	private CrearAlertasService crearAlertasService;
	
	@Inject
	private org.slf4j.Logger logger;
	  
	@Inject
	private SecurityService securityService;

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public void reemplazarTarics(InputReemplazarTaricsDTO input) {

		Long taricOrigen = Long.parseLong(input.getDatos().getCodigoTaricOrigen());
		Long taricDestino = Long.parseLong(input.getDatos().getCodigoTaricDestino());
		String codigoUsuario = input.getMetadatos().getCodigoUsuario().toUpperCase();
		Date fechaActual = new Date();
		
		Calendar yesterday = Calendar.getInstance();
		yesterday.add(Calendar.DATE, -1);
		Date fechaAyer = yesterday.getTime();

		try {
			
			/* Se buscan los productos que estaban asociado al taric a reemplzar */
			List<Long> productos = entityM.createQuery(
					"SELECT tp.id.codigoProducto FROM TaricsProductoJPA tp WHERE tp.id.codigoTaric = :taricOrigen ")
					.setParameter("taricOrigen", taricOrigen).getResultList();

			/* Se buscan que productos de los asociados con tarics tienen relacion con reas */
			List<Long> productosAEliminar = new ArrayList<>();
			if (!productos.isEmpty()) {
				productosAEliminar = entityM.createQuery(
						"SELECT rp.id.codigoProducto FROM ReasProductosJPA rp WHERE rp.id.codigoProducto IN :productos ")
						.setParameter("productos", productos).getResultList();
			}
			
			/* Se da por finalizadas las relaciones de productos taric a reemplazar */
			entityM.createQuery("UPDATE TaricsProductoJPA tp SET tp.fechaModificacion = :fechaModificacion, tp.usuarioModificacion = :codigoUsuario, tp.fechaFin = :fechaFin "
					+ "WHERE tp.id.codigoTaric = :taricOrigen AND (tp.fechaFin IS NULL OR tp.fechaFin >= :fecha) ")
					.setParameter("fechaModificacion", fechaActual).setParameter("fechaFin", fechaAyer).setParameter("codigoUsuario", codigoUsuario)
					.setParameter("taricOrigen", taricOrigen).setParameter("fecha", fechaActual)
					.executeUpdate();
			
			/* Se crea la relacion de los productos del taric antiguo con el taric nuevo */
			for (Long tmp : productos) {	
				TaricsProductosPkJPA id = new TaricsProductosPkJPA();
				id.setCodigoTaric(taricDestino);
				id.setCodigoProducto(tmp);
				id.setFechaInicio(fechaActual);
				TaricsProductoJPA relacionExistente = entityM.find(TaricsProductoJPA.class, id);
				if(relacionExistente != null) {
					relacionExistente.setUsuarioModificacion(codigoUsuario);
					relacionExistente.setFechaFin(null);
					relacionExistente.setFechaModificacion(fechaActual);
				}else {
					TaricsProductoJPA taricsProducto = new TaricsProductoJPA();
					taricsProducto.setId(id);
					taricsProducto.setFechaCreacion(fechaActual);
					taricsProducto.setUsuarioCreacion(codigoUsuario);
					taricsProducto.setCodigoAplicacion("GESADUAN");
					entityM.persist(taricsProducto);
				}

			}
			
			/* Si alguno de los productos asociado al taric a reemplzar este asociado al rea se elimina la relacion */
			if (!productosAEliminar.isEmpty()) {
				
					
				/*
				* Se deja comentada el DELETE a falta de confirmar si se da de baja o se elimina la relacion
				* 
				* entityM.createQuery("DELETE FROM ReasProductosJPA r WHERE r.id.codigoProducto IN :productos")
					.setParameter("productos", productosAEliminar).executeUpdate();
				* 
				* 
				* */
				
				entityM.flush();
				entityM.clear();
				
				entityM.createQuery("UPDATE ReasProductosJPA r SET r.fechaFin = :fechaFin, r.usuarioModificacion = :codigoUsuario, r.fechaModificacion = :fechaModificacion WHERE r.id.codigoProducto IN :productos AND (r.fechaFin IS NULL OR r.fechaFin >= :fecha)")
				.setParameter("productos", productosAEliminar).setParameter("fechaFin", fechaAyer).setParameter("fecha", fechaActual)
				.setParameter("codigoUsuario", codigoUsuario).setParameter("fechaModificacion", fechaActual)
				.executeUpdate();

				Integer codigoAlerta = 6;

				/* Por cada relacion producto - rea se crea una alerta */
				for (Long tmp : productosAEliminar) {
					String codigoElemento = String.valueOf(tmp);
					crearAlertasService.crearAlerta(codigoAlerta, codigoElemento, codigoUsuario);
				}

			}

		} catch (Exception e) {
			establecerSalidaError(e, "reemplazarTarics", "error.Exception");
			throw new ApplicationException(e.getMessage());
		}
	}
	
	private void establecerSalidaError(Exception exception, String metodo, String codError) {

	    String login = this.securityService.getPrincipal().getLogin();
	    
	    this.logger.error("Error ejecutando la clase: PutReemplazarTaricDAOImpl",
	        new Object[] { metodo, login, ExceptionUtils.getStackTrace(exception) });
  }



}
