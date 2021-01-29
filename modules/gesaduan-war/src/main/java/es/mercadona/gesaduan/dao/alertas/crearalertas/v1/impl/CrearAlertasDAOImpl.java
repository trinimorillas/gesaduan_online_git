package es.mercadona.gesaduan.dao.alertas.crearalertas.v1.impl;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import es.mercadona.fwk.data.DaoBaseImpl;
import es.mercadona.gesaduan.dao.alertas.crearalertas.v1.CrearAlertasDAO;
import es.mercadona.gesaduan.jpa.alertas.v1.NotificacionAlertaJPA;
import es.mercadona.gesaduan.jpa.alertas.v1.NotificacionAlertaPkJPA;

public class CrearAlertasDAOImpl extends DaoBaseImpl<NotificacionAlertaPkJPA, NotificacionAlertaJPA> implements CrearAlertasDAO{

	@Transactional
	@Override
	public void crearAlertas(Integer codigoAlerta, String codigoElemento, String codigoUsuario) {
		NotificacionAlertaJPA alerta = new NotificacionAlertaJPA();
		NotificacionAlertaPkJPA id = new NotificacionAlertaPkJPA();
		
		id.setCodigoAlerta(codigoAlerta);
		id.setCodigoElemento(codigoElemento);
		
		alerta.setId(id);
		
		alerta.setMarcaResuelto("N");
		alerta.setSmsEnviado("N");
		alerta.setCorreoEnviado("N");
		
		Date fecha = new Date();
		alerta.setFechaCreacion(fecha);
		
		alerta.setCodigoAplicacion("GESADUAN");
		alerta.setUsuarioCreacion(codigoUsuario.toUpperCase());
		
		entityM.persist(alerta);
		entityM.flush();
		
		
	}
	
	@PersistenceContext
	private EntityManager entityM;

	@Override
	protected EntityManager getEntityManager() {
		return this.entityM;
	}

	@Override
	public void setEntityClass() {
		
		entityClass = NotificacionAlertaJPA.class;
		
	}

}
