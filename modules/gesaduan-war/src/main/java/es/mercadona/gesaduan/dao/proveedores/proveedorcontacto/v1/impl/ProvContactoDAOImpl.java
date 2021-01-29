package es.mercadona.gesaduan.dao.proveedores.proveedorcontacto.v1.impl;


import java.math.BigDecimal;
import java.util.Date;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.fwk.data.DaoBaseImpl;
import es.mercadona.gesaduan.dao.proveedores.proveedorcontacto.v1.ProvContactoDAO;
import es.mercadona.gesaduan.dto.proveedores.putproveedores.v1.restfull.InputPutProveedoresContactos;
import es.mercadona.gesaduan.jpa.proveedores.putproveedorescontacto.v1.ProveedorContactoJPA;


@Stateless
public class ProvContactoDAOImpl extends DaoBaseImpl<Long, ProveedorContactoJPA> implements ProvContactoDAO {

	@Inject
	private org.slf4j.Logger logger;		
	
	@PersistenceContext
	private EntityManager entityM;

	@Transactional
	@Override
	public ProveedorContactoJPA putContactoProveedor(InputPutProveedoresContactos datos, Long codigoProveedor) {

		ProveedorContactoJPA contacto = null;

		try {
			if (datos.getDatosContacto().getCodigoContacto() != null) {
				contacto = findById(datos.getDatosContacto().getCodigoContacto());

				if (contacto == null) {

					contacto = new ProveedorContactoJPA();

					contacto.setCodContacto(getNextValue().longValue());
					contacto.setCodProveedor(codigoProveedor);
					contacto.setFechaCreacion(new Date());
					contacto.setFechaModificacion(new Date());
					contacto.setUsuarioCreacion(datos.getMetadatos().getCodigoUsuario().toUpperCase());
					contacto.setUsuarioModificacion(datos.getMetadatos().getCodigoUsuario().toUpperCase());
					contacto.setCodApp("GESADUAN");

					if(datos.getDatosContacto().getCodMecanismoContactoEmail() != null) {
						contacto.setCodMecanismoContactoEmail(datos.getDatosContacto().getCodMecanismoContactoEmail());
					}
					if(datos.getDatosContacto().getCodMecanismoContactoSMS() != null) {
						contacto.setCodMecanismoContactoSMS(datos.getDatosContacto().getCodMecanismoContactoSMS());
					}

					if(datos.getDatosContacto().getEnvioEmail() != null) {
						if(datos.getDatosContacto().getEnvioEmail())
							contacto.setMcaEmail("S");
						else
							contacto.setMcaEmail("N");
					}
					if(datos.getDatosContacto().getEnvioSMS() != null) {
						if(datos.getDatosContacto().getEnvioSMS())
							contacto.setMcaSMS("S");
						else
							contacto.setMcaSMS("N");
					}

					if(datos.getDatosContacto().getCodLocalizacionEmail() != null) {
						contacto.setCodLocalizacionEmail(datos.getDatosContacto().getCodLocalizacionEmail());
					}
					if(datos.getDatosContacto().getCodLocalizacionSMS() != null) {
						contacto.setCodLocalizacionSMS(datos.getDatosContacto().getCodLocalizacionSMS());
					}

					persist(contacto);

				}else {

					contacto.setFechaModificacion(new Date());
					contacto.setUsuarioModificacion(datos.getMetadatos().getCodigoUsuario().toUpperCase());

					if(datos.getDatosContacto().getCodMecanismoContactoEmail() != null) {
						contacto.setCodMecanismoContactoEmail(datos.getDatosContacto().getCodMecanismoContactoEmail());
					}else {
						contacto.setCodMecanismoContactoEmail(null);
					}
					if(datos.getDatosContacto().getCodMecanismoContactoSMS() != null) {
						contacto.setCodMecanismoContactoSMS(datos.getDatosContacto().getCodMecanismoContactoSMS());
					}else {
						contacto.setCodMecanismoContactoSMS(null);
					}
					if(datos.getDatosContacto().getEnvioEmail() != null) {
						if(datos.getDatosContacto().getEnvioEmail())
							contacto.setMcaEmail("S");
						else
							contacto.setMcaEmail("N");
					}
					if(datos.getDatosContacto().getEnvioSMS() != null) {
						if(datos.getDatosContacto().getEnvioSMS())
							contacto.setMcaSMS("S");
						else
							contacto.setMcaSMS("N");
					}

					if(datos.getDatosContacto().getCodLocalizacionEmail() != null) {
						contacto.setCodLocalizacionEmail(datos.getDatosContacto().getCodLocalizacionEmail());
					}else {
						contacto.setCodLocalizacionEmail(null);
					}
					if(datos.getDatosContacto().getCodLocalizacionSMS() != null) {
						contacto.setCodLocalizacionSMS(datos.getDatosContacto().getCodLocalizacionSMS());
					}else {
						contacto.setCodLocalizacionSMS(null);
					}

				}

			} else {
				contacto = new ProveedorContactoJPA();

				contacto.setCodContacto(getNextValue().longValue());
				contacto.setCodProveedor(codigoProveedor);
				contacto.setFechaCreacion(new Date());
				contacto.setFechaModificacion(new Date());
				contacto.setUsuarioCreacion(datos.getMetadatos().getCodigoUsuario().toUpperCase());
				contacto.setUsuarioModificacion(datos.getMetadatos().getCodigoUsuario().toUpperCase());
				contacto.setCodApp("GESADUAN");

				if(datos.getDatosContacto().getCodMecanismoContactoEmail() != null) {
					contacto.setCodMecanismoContactoEmail(datos.getDatosContacto().getCodMecanismoContactoEmail());
				}
				if(datos.getDatosContacto().getCodMecanismoContactoSMS() != null) {
					contacto.setCodMecanismoContactoSMS(datos.getDatosContacto().getCodMecanismoContactoSMS());
				}
				if(datos.getDatosContacto().getEnvioEmail() != null) {
					if(datos.getDatosContacto().getEnvioEmail())
						contacto.setMcaEmail("S");
					else
						contacto.setMcaEmail("N");
				}
				if(datos.getDatosContacto().getEnvioSMS() != null) {
					if(datos.getDatosContacto().getEnvioSMS())
						contacto.setMcaSMS("S");
					else
						contacto.setMcaSMS("N");
				}

				if(datos.getDatosContacto().getCodLocalizacionEmail() != null) {
					contacto.setCodLocalizacionEmail(datos.getDatosContacto().getCodLocalizacionEmail());
				}
				if(datos.getDatosContacto().getCodLocalizacionSMS() != null) {
					contacto.setCodLocalizacionSMS(datos.getDatosContacto().getCodLocalizacionSMS());
				}

				persist(contacto);
			}
		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","ProvContactoDAOImpl(GESADUAN)","putContactoProveedor",e.getClass().getSimpleName(),e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}

		return contacto;
	}

	@Transactional
	@Override
	public ProveedorContactoJPA deleteContactoProveedor(Long idProveedor, Long idContacto) {

		ProveedorContactoJPA contactoDelete = null;

		try {
			contactoDelete = this.entityM.find(ProveedorContactoJPA.class, idContacto);
			if(contactoDelete != null) {

			    Query queryUsuario = this.entityM.createNativeQuery("DELETE FROM S_CONTACTO_NOTIF_ALERTA_EXPED WHERE cod_n_contacto = ?cod_n_contacto");
	            queryUsuario.setParameter("cod_n_contacto", idContacto).executeUpdate();

				this.entityM.remove(contactoDelete);
				this.entityM.flush();

			}else {

			}
			/*
			final StringBuilder sql = new StringBuilder();
			String select = " DELETE FROM S_CONTACTO_PROVEEDOR_R ";
			String where = " WHERE (COD_N_CONTACTO = ?codigo and COD_N_PROVEEDOR = ?proveedor) ";
			sql.append(select).append(where);
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			query.setParameter("codigo", idContacto).setParameter("proveedor", idProveedor).executeUpdate();
			*/


		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","ProvContactoDAOImpl(GESADUAN)","deleteContactoProveedor",e.getClass().getSimpleName(),e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}

		return contactoDelete;
	}

	private BigDecimal getNextValue() {
		
		try {
		
			final StringBuilder sql = new StringBuilder();
			String select = " SELECT MAX(COD_N_CONTACTO)+1 FROM S_CONTACTO_PROVEEDOR_R ";
			sql.append(select);
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			return (BigDecimal) query.getSingleResult();
		
		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","ProvContactoDAOImpl(GESADUAN)","getNextValue",e.getClass().getSimpleName(),e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}		
	}

	@Override
	protected EntityManager getEntityManager() {
		return this.entityM;
	}

	@Override
	public void setEntityClass() {
		this.entityClass = ProveedorContactoJPA.class;

	}

}
