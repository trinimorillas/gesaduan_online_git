package es.mercadona.gesaduan.dao.proveedores.putrelacionesproveedor.v1.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.fwk.data.DaoBaseImpl;
import es.mercadona.gesaduan.dao.proveedores.putrelacionesproveedor.v1.PutRelacionesProveedorDAO;
import es.mercadona.gesaduan.dto.proveedores.putrelacionesproveedores.v1.InputRelacionesProveedorDTO;
import es.mercadona.gesaduan.dto.proveedores.putrelacionesproveedores.v1.RelacionesProveedorDTO;
import es.mercadona.gesaduan.jpa.proveedores.putrelacionesproveedor.v1.RelacionAgenciaProveedorJPA;
import es.mercadona.gesaduan.jpa.proveedores.putrelacionesproveedor.v1.RelacionAgenciaProveedorPkJPA;
import es.mercadona.gesaduan.common.Constantes;

public class PutRelacionesProveedorDAOImpl extends
		DaoBaseImpl<RelacionAgenciaProveedorPkJPA, RelacionAgenciaProveedorJPA> implements PutRelacionesProveedorDAO {

	@Inject
	private org.slf4j.Logger logger;
	
	@Override
	protected EntityManager getEntityManager() {
		return this.entityM;
	}

	@Override
	public void setEntityClass() {
		entityClass = RelacionAgenciaProveedorJPA.class;
	}

	@PersistenceContext
	private EntityManager entityM;
	
	private final String editarRelacionesProveedor = "editarRelacionesProveedor";
	private final String nombreClase = "PutRelacionesProveedorDAOImpl(GESADUAN)";

	@Transactional
	@Override
	public void editarRelacionesProveedor(InputRelacionesProveedorDTO input) {		
		try {
			boolean esAgencia = (input.getDatos().getEsAgencia()) ? true : false;

			if (esAgencia) {
				editarAgenciaProveedor(input);
			} else {
				editarProveedorAgencia(input);
			}
		} catch (Exception e) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, nombreClase, "editarRelacionesProveedor", e.getClass().getSimpleName(),e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}
	}

	private void editarAgenciaProveedor(InputRelacionesProveedorDTO input) {
		String editarAgenciaProveedor = "editarAgenciaProveedor";
		try {
			List<RelacionesProveedorDTO> relacionesList = input.getDatos().getRelacionProveedor();
	
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date fechaTransform = null;
			Date fechaActual = new Date();
	
			String aplicacion = "GESADUAN";
			String usuario = input.getMetadatos().getCodigoUsuario().toUpperCase();
	
			if (relacionesList != null && !relacionesList.isEmpty()) {
				for (RelacionesProveedorDTO tmp : relacionesList) {
					RelacionAgenciaProveedorPkJPA id = new RelacionAgenciaProveedorPkJPA();
	
					String codigoAgencia = input.getDatos().getCodigo();
					String codigoProveedor = tmp.getCodigo();
	
					id.setCodigoAgencia(codigoAgencia);
					id.setCodigoProveedor(codigoProveedor);
					try {
						fechaTransform = format.parse(tmp.getFechaInicio());
					} catch (ParseException e1) {
						this.logger.error(Constantes.FORMATO_ERROR_LOG, nombreClase, editarAgenciaProveedor, e1.getClass().getSimpleName(),e1.getMessage());
					}
					id.setFechaInicio(fechaTransform);
	
					RelacionAgenciaProveedorJPA crearAgenciaProveedor = new RelacionAgenciaProveedorJPA();
					RelacionAgenciaProveedorJPA actualizarAgenciaProveedor = entityM.find(RelacionAgenciaProveedorJPA.class,
							id);
	
					if (actualizarAgenciaProveedor != null) {
						if (tmp.getFechaFin() != null) {
							try {
								fechaTransform = format.parse(tmp.getFechaFin().substring(0, 10));
							} catch (ParseException e) {
								this.logger.error(Constantes.FORMATO_ERROR_LOG, nombreClase, editarAgenciaProveedor, e.getClass().getSimpleName(),e.getMessage());	
							}
							actualizarAgenciaProveedor.setFechaFin(fechaTransform);
						}else {
							actualizarAgenciaProveedor.setFechaFin(null);
						}
						actualizarAgenciaProveedor.setUsuarioModificacion(usuario);
						actualizarAgenciaProveedor.setFechaModificacion(fechaActual);
					} else {
	
						darDeBajaExistentes(codigoAgencia, codigoProveedor, usuario);
						crearAgenciaProveedor.setId(id);
						crearAgenciaProveedor.setFechaCreacion(fechaActual);
						crearAgenciaProveedor.setUsuarioCreacion(usuario);
						crearAgenciaProveedor.setCodigoAplicacion(aplicacion);
						if (tmp.getFechaFin() != null) {
							try {
								fechaTransform = format.parse(tmp.getFechaFin().substring(0, 10));
							} catch (ParseException e) {
								this.logger.error(Constantes.FORMATO_ERROR_LOG, nombreClase, editarAgenciaProveedor, e.getClass().getSimpleName(),e.getMessage());
							}
							crearAgenciaProveedor.setFechaFin(fechaTransform);
						}
						entityM.persist(crearAgenciaProveedor);
					}
				}
			}
			
		} catch (Exception e) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, nombreClase, editarRelacionesProveedor, e.getClass().getSimpleName(),e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}			

	}

	private void editarProveedorAgencia(InputRelacionesProveedorDTO input) {
		String editarProveedorAgencia = "editarProveedorAgencia";
		try {		
			List<RelacionesProveedorDTO> relacionesList = input.getDatos().getRelacionProveedor();
	
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date fechaTransform = null;
			Date fechaActual = new Date();
	
			String aplicacion = "GESADUAN";
			String usuario = input.getMetadatos().getCodigoUsuario().toUpperCase();
	
			if (relacionesList != null && !relacionesList.isEmpty()) {
				for (RelacionesProveedorDTO tmp : relacionesList) {
					RelacionAgenciaProveedorPkJPA id = new RelacionAgenciaProveedorPkJPA();
	
					String codigoProveedor = input.getDatos().getCodigo();
					String codigoAgencia = tmp.getCodigo();
	
					id.setCodigoProveedor(codigoProveedor);
					id.setCodigoAgencia(codigoAgencia);
					try {
						fechaTransform = format.parse(tmp.getFechaInicio().substring(0, 10));
					} catch (ParseException e1) {
						this.logger.error(Constantes.FORMATO_ERROR_LOG, nombreClase, editarProveedorAgencia, e1.getClass().getSimpleName(),e1.getMessage());	
					}
					id.setFechaInicio(fechaTransform);
	
					RelacionAgenciaProveedorJPA actualizarProveedorAgencia = entityM.find(RelacionAgenciaProveedorJPA.class,
							id);
					if (actualizarProveedorAgencia != null) {
						if (tmp.getFechaFin() != null) {
							try {
								fechaTransform = format.parse(tmp.getFechaFin().substring(0, 10));
							} catch (ParseException e) {
								this.logger.error(Constantes.FORMATO_ERROR_LOG, nombreClase, editarProveedorAgencia, e.getClass().getSimpleName(),e.getMessage());	
							}
							actualizarProveedorAgencia.setFechaFin(fechaTransform);
						}else {
							actualizarProveedorAgencia.setFechaFin(null);
						}
						actualizarProveedorAgencia.setUsuarioModificacion(usuario);
						actualizarProveedorAgencia.setFechaModificacion(fechaActual);
					} else {
	
						darDeBajaExistentes(codigoAgencia, codigoProveedor, usuario);
	
						RelacionAgenciaProveedorJPA crearProveedorAgencia = new RelacionAgenciaProveedorJPA();
						crearProveedorAgencia.setId(id);
						crearProveedorAgencia.setFechaCreacion(fechaActual);
						crearProveedorAgencia.setUsuarioCreacion(usuario);
						crearProveedorAgencia.setCodigoAplicacion(aplicacion);
	
						if (tmp.getFechaFin() != null) {
							try {
								fechaTransform = format.parse(tmp.getFechaFin().substring(0, 10));
							} catch (ParseException e) {
								this.logger.error(Constantes.FORMATO_ERROR_LOG, nombreClase, editarProveedorAgencia, e.getClass().getSimpleName(),e.getMessage());
							}
							crearProveedorAgencia.setFechaFin(fechaTransform);
						}
	
						entityM.persist(crearProveedorAgencia);
					}
	
				}
			}
			
		} catch (Exception e) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, nombreClase, editarProveedorAgencia, e.getClass().getSimpleName(), e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}				
	}

	private void darDeBajaExistentes(String codigoAgencia, String codigoProveedor, String codigoUsuario) {
		try {		
			Date fechaActual = new Date();
			Long result = (Long) entityM.createQuery("SELECT COUNT(rel) " + "FROM RelacionAgenciaProveedorJPA rel "
					+ "WHERE rel.id.codigoAgencia = :codigoAgencia AND rel.id.codigoProveedor = :codigoProveedor AND rel.fechaFin IS NULL OR rel.fechaFin >= :fechaActual")
					.setParameter("codigoAgencia", codigoAgencia).setParameter("codigoProveedor", codigoProveedor)
					.setParameter("fechaActual", fechaActual).getSingleResult();
	
			if (result > 0) {
				Calendar yesterday = Calendar.getInstance();
							 
				Date fecha = yesterday.getTime();
				entityM.createQuery(
						"UPDATE RelacionAgenciaProveedorJPA rel SET rel.fechaFin = :fecha, rel.fechaModificacion = :fecha, rel.usuarioModificacion = :usuarioModificacion WHERE rel.id.codigoAgencia = :codigoAgencia AND rel.id.codigoProveedor = :codigoProveedor "
						+ "AND (rel.fechaFin IS NULL OR rel.fechaFin >= :fecha)")
						.setParameter("codigoAgencia", codigoAgencia).setParameter("codigoProveedor", codigoProveedor).setParameter("usuarioModificacion", codigoUsuario)
						.setParameter("fecha", fecha).executeUpdate();
			}
			
		} catch (Exception e) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, nombreClase, "darDeBajaExistentes", e.getClass().getSimpleName(), e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}				

	}
}
