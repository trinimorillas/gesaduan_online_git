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
import es.mercadona.gesaduan.dao.declaracionesdevalor.postdv.v1.PostDeclaracionDeValorDAO;
import es.mercadona.gesaduan.jpa.declaracionesdevalor.postdv.v1.DeclaracionesDeValorPostJPA;
import es.mercadona.gesaduan.jpa.declaracionesdevalor.postdv.v1.DeclaracionesDeValorPostPK;
import es.mercadona.gesaduan.jpa.declaracionesdevalor.postdv.v1.LineaDeclaracionJPA;

@Stateless
public class PostDeclaracionDeValorDAOImpl extends DaoBaseImpl<DeclaracionesDeValorPostPK, DeclaracionesDeValorPostJPA>
		implements PostDeclaracionDeValorDAO {

	@Inject
	private org.slf4j.Logger logger;

	@PersistenceContext
	private EntityManager entityM;

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
					
					// dv.setMcaDvCorrecta("S");

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
		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}", "PostDeclaracionDeValorDAOImpl(GESADUAN)", "postCabecera",
					e.getClass().getSimpleName(), e.getMessage());
			throw new ApplicationException(e.getMessage());
		}

		return pk;

	}

	@Override
	protected EntityManager getEntityManager() {

		return this.entityM;
	}

	@Override
	public void setEntityClass() {
		entityClass = DeclaracionesDeValorPostJPA.class;

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
	private void alertasSolucionadas (Integer codDeclaracionValor,Integer anyo) {
		
		String sql ="";
		
		try {
		
			sql = "UPDATE S_NOTIF_ALERTA_EXPED_DV " + 
				  "   SET MCA_RESUELTA = 'S' " + 
				  " WHERE COD_N_DECLARACION_VALOR = ?codDeclaracionValor " + 
				  "   AND NUM_ANYO = ?anyo ";	
			
			final Query query = getEntityManager().createNativeQuery(sql);	
			query.setParameter("codDeclaracionValor", codDeclaracionValor);	
			query.setParameter("anyo", anyo);	
			
			query.executeUpdate();
		
		} catch (Exception e) {
			this.logger.error("({}-{}) ERROR - {} {}","PostDeclaracionDeValorDAOImpl(GESADUAN)","alertasSolucionadas",e.getClass().getSimpleName(),e.getMessage());	
			throw new ApplicationException(e.getMessage());
		}		
		
	}

	private DeclaracionesDeValorPostJPA checkCambios(DeclaracionesDeValorPostJPA dvEdit,
			DeclaracionesDeValorPostJPA dvJpa) {

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
			this.logger.error("({}-{}) ERROR - {} {}", "PostDeclaracionDeValorDAOImpl(GESADUAN)", "checkCambios",
					e.getClass().getSimpleName(), e.getMessage());
			throw new ApplicationException(e.getMessage());
		}

		return dvJpa;
	}

}
