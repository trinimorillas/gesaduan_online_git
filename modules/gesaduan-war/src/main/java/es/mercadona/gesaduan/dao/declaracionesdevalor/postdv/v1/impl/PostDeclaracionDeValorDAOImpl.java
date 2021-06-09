package es.mercadona.gesaduan.dao.declaracionesdevalor.postdv.v1.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.fwk.data.DaoBaseImpl;
import es.mercadona.gesaduan.common.Constantes;
import es.mercadona.gesaduan.dao.declaracionesdevalor.postdv.v1.PostDeclaracionDeValorDAO;
import es.mercadona.gesaduan.jpa.declaracionesdevalor.postdv.v1.DeclaracionesDeValorPostJPA;
import es.mercadona.gesaduan.jpa.declaracionesdevalor.postdv.v1.DeclaracionesDeValorPostPK;
import es.mercadona.gesaduan.jpa.declaracionesdevalor.postdv.v1.LineaDeclaracionJPA;

@Stateless
public class PostDeclaracionDeValorDAOImpl extends DaoBaseImpl<DeclaracionesDeValorPostPK, DeclaracionesDeValorPostJPA> implements PostDeclaracionDeValorDAO {
	
	@Override
	protected EntityManager getEntityManager() {
		return this.entityM;
	}

	@Override
	public void setEntityClass() {
		entityClass = DeclaracionesDeValorPostJPA.class;
	}

	@Inject
	private org.slf4j.Logger logger;

	@PersistenceContext
	private EntityManager entityM;
	
	private static final String NOMBRE_CLASE = "PostDeclaracionDeValorDAOImpl(GESADUAN)";

	@Override
	@Transactional
	public DeclaracionesDeValorPostPK postCabecera(DeclaracionesDeValorPostJPA input) {
		DeclaracionesDeValorPostPK pk = new DeclaracionesDeValorPostPK();

		try {
			input = savePk(input);

			pk.setCodDeclaracionValor(input.getCodDeclaracionValor());
			pk.setAnyo(input.getAnyo());
			pk.setVersion(input.getVersion());

			/* Si el cod declaracion está informado es modificacion de pantalla */
			if (input.getCodDeclaracionValor() != null) {
				/* Buscamos la DV por si existe y nos guardamos la entidad */
				DeclaracionesDeValorPostJPA dv = findById(pk);

				/* Si la dv que envian no existe: */
				/* Creamos una nueva con la secuencia. */

				if (dv == null) {
					entityM.persist(input);
					entityM.flush();

					pk.setCodDeclaracionValor(input.getCodDeclaracionValor());
					pk.setAnyo(input.getAnyo());
					pk.setVersion(input.getVersion());
				}
				/* Si la dv existe subimos de versión */
				else {
					/* Actualizamos la vigencia de la dv existente a N */
					dv.setMcaUltimaVigente("N");
					entityM.persist(dv);
					entityM.flush();

					dv = checkCambios(input, dv);
					dv.setVersion(dv.getVersion() + 1);
					dv = savePk(dv);

					entityM.detach(dv);

					dv.setMcaUltimaVigente("S");
					
					// Si la declaracion es correcta marca las alertas como solucionadas
					if ("S".equals(dv.getMcaDvCorrecta())) {
						alertasSolucionadas(input.getCodDeclaracionValor(),input.getAnyo());
					}

					entityM.merge(dv);
					entityM.flush();

					pk.setCodDeclaracionValor(dv.getCodDeclaracionValor());
					pk.setAnyo(dv.getAnyo());
					pk.setVersion(dv.getVersion());

				}
				/* Si el cod declaracion no está informado es nueva. */
				/* Carga manual excel */
			} else {
				entityM.persist(input);
				entityM.flush();

				pk.setCodDeclaracionValor(input.getCodDeclaracionValor());
				pk.setAnyo(input.getAnyo());
				pk.setVersion(input.getVersion());

			}
			
			if (input.getExpedicion() == null) {
				insertarPedidos(input);
			}
			
			updateContenedor(input);
			
			generarAlerta(input.getUsuarioCreacion(), Long.toString(pk.getCodDeclaracionValor()), Integer.toString(pk.getAnyo()));
			marcarDosierOK(Long.toString(pk.getCodDeclaracionValor()), Integer.toString(pk.getAnyo()));
			generaAlertaDosierOK(input.getUsuarioCreacion(),Long.toString(pk.getCodDeclaracionValor()), Integer.toString(pk.getAnyo()));
			
		} catch (Exception e) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, NOMBRE_CLASE, "postCabecera", e.getClass().getSimpleName(), e.getMessage());
			throw new ApplicationException(e.getMessage());
		}

		return pk;

	}

	private DeclaracionesDeValorPostJPA savePk(DeclaracionesDeValorPostJPA declaracion) {
		if (declaracion.getLineasProductos() != null && !declaracion.getLineasProductos().isEmpty()) {
			for (LineaDeclaracionJPA linea : declaracion.getLineasProductos()) {
				linea.setCodigoDv(declaracion);
			}
		}
		return declaracion;
	}
	
	@Transactional	
	private void insertarPedidos (DeclaracionesDeValorPostJPA input) {
		StringBuilder sql = new StringBuilder();
		
		try {		
			sql.append("INSERT INTO S_DECLARACION_VALOR_PEDIDO (COD_N_DECLARACION_VALOR, NUM_ANYO_DV, COD_N_VERSION_DV, COD_V_PEDIDO, FEC_DT_CREACION, COD_V_APLICACION, COD_V_USUARIO_CREACION) "); 
			sql.append("SELECT COD_N_DECLARACION_VALOR, NUM_ANYO_DV, ?nuevaVersion, COD_V_PEDIDO, SYSDATE, 'GESADUAN', ?codigoUsuario ");
			sql.append("FROM S_DECLARACION_VALOR_PEDIDO ");
			sql.append("WHERE COD_N_DECLARACION_VALOR = ?codDeclaracionValor ");
			sql.append("AND NUM_ANYO_DV = ?anyo AND COD_N_VERSION_DV = ?versionAntigua");	
			
			final Query query = getEntityManager().createNativeQuery(sql.toString());	
			query.setParameter("codDeclaracionValor", input.getCodDeclaracionValor());	
			query.setParameter("anyo", input.getAnyo());
			query.setParameter("versionAntigua", input.getVersion());
			query.setParameter("nuevaVersion", input.getVersion()+1);
			query.setParameter("codigoUsuario", input.getUsuarioCreacion());
			query.executeUpdate();		
		} catch (Exception e) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, NOMBRE_CLASE, "insertarPedidos", e.getClass().getSimpleName(), e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}		
	}
	
	@Transactional	
	private void updateContenedor (DeclaracionesDeValorPostJPA input) {
		StringBuilder sql = new StringBuilder();
		
		try {		
			sql.append("UPDATE O_CONTENEDOR_EXPEDIDO "); 
			sql.append("SET COD_N_VERSION_DV = ?nuevaVersion, FEC_D_MODIFICACION = SYSDATE, COD_V_USR_MODIFICACION = ?codigoUsuario ");
			sql.append("WHERE COD_N_DECLARACION_VALOR = ?codDeclaracionValor ");
			sql.append("AND NUM_ANYO_DV = ?anyo AND COD_N_VERSION_DV = ?versionAntigua");
			
			final Query query = getEntityManager().createNativeQuery(sql.toString());	
			query.setParameter("codDeclaracionValor", input.getCodDeclaracionValor());	
			query.setParameter("anyo", input.getAnyo());
			query.setParameter("versionAntigua", input.getVersion());
			query.setParameter("nuevaVersion", input.getVersion()+1);
			query.setParameter("codigoUsuario", input.getUsuarioCreacion());
			query.executeUpdate();
		} catch (Exception e) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, NOMBRE_CLASE, "updateContenedor", e.getClass().getSimpleName(), e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}		
	}
	
	@Transactional	
	private void alertasSolucionadas (Integer codDeclaracionValor, Integer anyo) {
		StringBuilder sql = new StringBuilder();
		
		try {		
			sql.append("UPDATE S_NOTIF_ALERTA_EXPED_DV "); 
			sql.append("SET MCA_RESUELTA = 'S' ");
			sql.append("WHERE COD_N_DECLARACION_VALOR = ?codDeclaracionValor ");
			sql.append("AND NUM_ANYO = ?anyo ");	
			
			final Query query = getEntityManager().createNativeQuery(sql.toString());	
			query.setParameter("codDeclaracionValor", codDeclaracionValor);	
			query.setParameter("anyo", anyo);			
			query.executeUpdate();		
		} catch (Exception e) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, NOMBRE_CLASE, "alertasSolucionadas", e.getClass().getSimpleName(), e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}		
	}

	private DeclaracionesDeValorPostJPA checkCambios(DeclaracionesDeValorPostJPA dvEdit, DeclaracionesDeValorPostJPA dvJpa) {
		Date fechaInicio = new Date();

		try {
			/* Comprobar provincia de Carga Editable */
			if (dvEdit.getProvinciaCarga() != null) {
				dvJpa.setProvinciaCarga(dvEdit.getProvinciaCarga());
			}
			/* Comprobar marcaCorrecta */
			if (dvEdit.getMcaDvCorrecta() != null) {
				dvJpa.setMcaDvCorrecta(dvEdit.getMcaDvCorrecta());
			}

			dvJpa.setFechaCreacion(fechaInicio);
			dvJpa.setFechaCreacionRegistro(fechaInicio);
			dvJpa.setFechaModificacionRegistro(fechaInicio);
			dvJpa.setApp("GESADUAN");
			dvJpa.setUsuarioEdit(dvEdit.getUsuarioCreacion().toUpperCase());
			dvJpa.setUsuarioCreacion(dvEdit.getUsuarioCreacion().toUpperCase());

			/* Comprobar lineas de las DV */
			if (dvEdit.getLineasProductos() != null && !dvEdit.getLineasProductos().isEmpty()) {
				List<LineaDeclaracionJPA> aux = new ArrayList();

				for (LineaDeclaracionJPA lineaJPA : dvJpa.getLineasProductos()) {
					DeclaracionesDeValorPostJPA pkLinea = new DeclaracionesDeValorPostJPA();
					pkLinea.setCodDeclaracionValor(dvJpa.getCodDeclaracionValor());
					pkLinea.setVersion(dvJpa.getVersion() + 1);
					pkLinea.setAnyo(dvJpa.getAnyo());

					lineaJPA.setCodigoDv(pkLinea);

					for (LineaDeclaracionJPA lineaEdit : dvEdit.getLineasProductos()) {
						if (lineaEdit.getCodMerca().equals(lineaJPA.getCodMerca())) {
							if (lineaEdit.getNombreTipoBulto() != null) {
								lineaJPA.setNombreTipoBulto(lineaEdit.getNombreTipoBulto());
							}
							if (lineaEdit.getNumeroDeBultos() != null) {
								lineaJPA.setNumeroDeBultos(lineaEdit.getNumeroDeBultos());
							}
							if (lineaEdit.getPesoNetoLinea() != null) {
								lineaJPA.setPesoNetoLinea(lineaEdit.getPesoNetoLinea());
							}
							if (lineaEdit.getVolumenUnidad() != null) {
								lineaJPA.setVolumenUnidad(lineaEdit.getVolumenUnidad());
							}
							if (lineaEdit.getPesoBrutoLinea() != null) {
								lineaJPA.setPesoBrutoLinea(lineaEdit.getPesoBrutoLinea());
							}
							if (lineaEdit.getCantidadFormato() != null) {
								lineaJPA.setCantidadFormato(lineaEdit.getCantidadFormato());
							}
							if (lineaEdit.getPaisOrigen() != null) {
								lineaJPA.setPaisOrigen(lineaEdit.getPaisOrigen());
							}
							if (lineaEdit.getPrecioUnidad() != null) {
								lineaJPA.setPrecioUnidad(lineaEdit.getPrecioUnidad());
							}
							if (lineaEdit.getImporteTotal() != null) {
								lineaJPA.setImporteTotal(lineaEdit.getImporteTotal());
							}
							if (lineaEdit.getGradoAlcohol() != null) {
								lineaJPA.setGradoAlcohol(lineaEdit.getGradoAlcohol());
							}
							if (lineaEdit.getGradoPlato() != null) {
								lineaJPA.setGradoPlato(lineaEdit.getGradoPlato());
							}
							if (lineaEdit.getCodigoTaric() != null) {
								lineaJPA.setCodigoTaric(lineaEdit.getCodigoTaric());
							}

							lineaJPA.setMarcaError(lineaEdit.getMarcaError());
							lineaJPA.setFechaCreacion(fechaInicio);
							lineaJPA.setFechaModificacion(fechaInicio);
							lineaJPA.setCodAplicacion("GESADUAN");
							lineaJPA.setUsuarioEdit(dvEdit.getUsuarioCreacion().toUpperCase());
							lineaJPA.setUsuarioCreacion(dvEdit.getUsuarioCreacion().toUpperCase());

							aux.add(lineaJPA);
						} else {
							aux.add(lineaJPA);
						}
					}
				}

				dvJpa.setLineasProductos(aux);
			}

		} catch (Exception e) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, NOMBRE_CLASE, "checkCambios", e.getClass().getSimpleName(), e.getMessage());
			throw new ApplicationException(e.getMessage());
		}

		return dvJpa;
	}
	
	@Override
	@Transactional	
	public void generarAlerta(String codigoUsuario, String numFactura, String anyoFactura) {
		StringBuilder sql = new StringBuilder();
		
		try {		
			sql.append("INSERT INTO S_NOTIF_ALERTA_EXPED_DV (COD_N_ALERTA, COD_V_ELEMENTO, COD_V_EXPEDICION, FEC_D_ALBARAN, COD_N_DECLARACION_VALOR, NUM_ANYO, COD_N_VERSION, ");
			sql.append("MCA_CORREO_ENVIADO,MCA_SMS_ENVIADO, MCA_RESUELTA, FEC_D_CREACION, COD_V_APLICACION, COD_V_USUARIO_CREACION) ");
			sql.append("SELECT 46, SUBSTR(NUM_ANYO_DOSIER,3,2)||'-'||LPAD(NUM_DOSIER,5,'0'), '-', SYSDATE, COD_N_DECLARACION_VALOR, NUM_ANYO, COD_N_VERSION, ");
			sql.append("'N', 'N', 'N', SYSDATE, 'GESADUAN', ?codigoUsuario ");
			sql.append("FROM ( ");
			sql.append("SELECT DV.COD_N_DECLARACION_VALOR, DV.NUM_ANYO, DV.COD_N_VERSION,DV.NUM_ANYO_DOSIER,DV.NUM_DOSIER, ");
			sql.append("ROW_NUMBER() OVER(PARTITION BY DV.COD_N_DECLARACION_VALOR,DV.NUM_ANYO,DV.COD_N_VERSION ORDER BY DV.COD_N_VERSION) NUMERO ");
			sql.append("FROM O_DECLARACION_VALOR_CAB DV ");
			sql.append("INNER JOIN D_DOSIER D ON (D.NUM_DOSIER = DV.NUM_DOSIER) ");
			sql.append("WHERE DV.COD_N_DECLARACION_VALOR = ?numFactura ");
			sql.append("AND DV.NUM_ANYO = ?anyoFactura ");
			sql.append("AND DV.MCA_ULTIMA_VIGENTE = 'S' ");
			sql.append("AND (D.FEC_DT_DESCARGA_EXPORTADOR IS NOT NULL OR D.FEC_DT_DESCARGA_IMPORTADOR IS NOT NULL) ");
			sql.append("AND NOT EXISTS (");
			sql.append("SELECT 1 ");
			sql.append("FROM S_NOTIF_ALERTA_EXPED_DV NA ");
			sql.append("WHERE NA.COD_N_DECLARACION_VALOR = DV.COD_N_DECLARACION_VALOR ");
			sql.append("AND NA.NUM_ANYO = DV.NUM_ANYO ");
			sql.append("AND NA.COD_N_VERSION = DV.COD_N_VERSION ");
			sql.append("AND NA.COD_N_ALERTA = 46) ");
			sql.append(") TABLA ");
			sql.append("WHERE NUMERO = 1 ");
			sql.append("AND EXISTS ( ");
			sql.append("SELECT 1 ");
			sql.append("FROM D_ALERTA ");
			sql.append("WHERE MCA_ACTIVA = 'S' ");
			sql.append("AND COD_N_ALERTA = 46 ");
			sql.append(")");	
			
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			query.setParameter("codigoUsuario", codigoUsuario);
			query.setParameter("numFactura", numFactura);
			query.setParameter("anyoFactura", anyoFactura);
			query.executeUpdate();
		} catch (Exception e) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, NOMBRE_CLASE, "generarAlerta", e.getClass().getSimpleName(), e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}
	}
	
	
	@Override
	
	@Transactional	
	public void marcarDosierOK(String numFactura, String anyoFactura) {
		try {
			StringBuilder sql = new StringBuilder();
			
			
			
			sql.append("UPDATE D_DOSIER D ");
			sql.append("SET D.MCA_ERROR = 'N'  ");
			sql.append("WHERE (D.NUM_DOSIER, D.NUM_ANYO) IN ( ");
			sql.append("SELECT DV.NUM_DOSIER,DV.NUM_ANYO_DOSIER ");
			sql.append("FROM O_DECLARACION_VALOR_CAB DV ");
			sql.append("WHERE DV.COD_N_DECLARACION_VALOR = ?numFactura ");
			sql.append("AND DV.NUM_ANYO = ?anyoFactura ");
			sql.append("AND NOT EXISTS ( ");
			sql.append("SELECT 1 ");
			sql.append("FROM O_DECLARACION_VALOR_CAB DVC ");
			sql.append("WHERE DVC.NUM_DOSIER = DV.NUM_DOSIER ");
			sql.append("AND DVC.NUM_ANYO_DOSIER = DV.NUM_ANYO_DOSIER ");
			sql.append("AND DVC.MCA_ULTIMA_VIGENTE = 'S' ");
			sql.append("AND DVC.MCA_DV_CORRECTA = 'N' ");
			sql.append(") ");
			sql.append(")");	
			
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			query.setParameter("numFactura", numFactura);
			query.setParameter("anyoFactura", anyoFactura);
			query.executeUpdate();
		} catch (Exception e) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, NOMBRE_CLASE, "marcarDosierOK", e.getClass().getSimpleName(), e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}
	}	
	
	@Override
	@Transactional	
	public void generaAlertaDosierOK(String codigoUsuario, String numFactura, String anyoFactura) {
		try {
			StringBuilder sql = new StringBuilder();
			
			sql.append("INSERT INTO S_NOTIFICACION_ALERTA (COD_N_ALERTA, COD_V_ELEMENTO, MCA_CORREO_ENVIADO, ");
			sql.append("MCA_SMS_ENVIADO, MCA_RESUELTA, FEC_D_CREACION, COD_V_APLICACION, COD_V_USUARIO_CREACION) ");
			sql.append("SELECT 0, SUBSTR(NUM_ANYO, 3, 2)||'-'||LPAD(NUM_DOSIER, 5, '0'), ");
			sql.append("'N', 'N', 'N', SYSDATE, 'GESADUAN', ?codigoUsuario ");
			sql.append("FROM ( ");
			sql.append("SELECT D.NUM_DOSIER,D.NUM_ANYO,SUBSTR(D.NUM_ANYO,3,2),LPAD(D.NUM_DOSIER,5,'0') ");
			sql.append("FROM  D_DOSIER D ");
			sql.append("INNER JOIN O_DECLARACION_VALOR_CAB DV ON (DV.NUM_DOSIER = D.NUM_DOSIER AND DV.NUM_ANYO_DOSIER = D.NUM_ANYO) ");
			sql.append("WHERE DV.COD_N_DECLARACION_VALOR = ?numFactura ");
			sql.append("AND DV.NUM_ANYO = ?anyoFactura ");
			sql.append("AND DV.MCA_ULTIMA_VIGENTE = 'S' ");
			sql.append("AND D.MCA_ERROR = 'N' ");
			sql.append("AND NOT EXISTS ( ");
			sql.append("SELECT 1 ");
			sql.append("FROM S_NOTIFICACION_ALERTA NA ");
			sql.append("WHERE SUBSTR(NA.COD_V_ELEMENTO, 1, 2) = SUBSTR(D.NUM_ANYO, 3, 2) ");
			sql.append("AND SUBSTR(NA.COD_V_ELEMENTO, 4, LENGTH(NA.COD_V_ELEMENTO)) = LPAD(D.NUM_DOSIER, 5, '0') ");
			sql.append("AND NA.COD_N_ALERTA = 0) ");
			sql.append(") TABLA ");
			sql.append("WHERE EXISTS ( ");
			sql.append("SELECT 1 ");
			sql.append("FROM D_ALERTA ");
			sql.append("WHERE MCA_ACTIVA = 'S' ");
			sql.append("AND COD_N_ALERTA = 0 ");
			sql.append(")");
			
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			query.setParameter("codigoUsuario", codigoUsuario);
			query.setParameter("numFactura", numFactura);
			query.setParameter("anyoFactura", anyoFactura);
			query.executeUpdate();
		} catch (Exception e) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, NOMBRE_CLASE, "generaAlertaDosierOK", e.getClass().getSimpleName(), e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}
	}
	
	@Override
	@Transactional	
	public String getProveedor(String publicId) {
		String result;
		try {
			StringBuilder sql = new StringBuilder();
			
			sql.append("SELECT COD_N_PROVEEDOR "); 
			sql.append("FROM D_PROVEEDOR_R ");
			sql.append("WHERE COD_N_PROVEEDOR = ?publicId OR COD_N_LEGACY_PROVEEDOR = ?publicId"); 
			
			final Query query = getEntityManager().createNativeQuery(sql.toString());
			query.setParameter("publicId", publicId);
			result = String.valueOf(query.getSingleResult());
		} catch (Exception e) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, NOMBRE_CLASE, "getProveedor", e.getClass().getSimpleName(), e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}
		
		return result;
	}

}