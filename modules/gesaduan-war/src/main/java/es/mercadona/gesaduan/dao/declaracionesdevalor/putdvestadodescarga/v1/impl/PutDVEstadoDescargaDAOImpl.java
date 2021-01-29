package es.mercadona.gesaduan.dao.declaracionesdevalor.putdvestadodescarga.v1.impl;

import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.fwk.data.DaoBaseImpl;

import es.mercadona.gesaduan.dao.declaracionesdevalor.putdvestadodescarga.v1.PutDVEstadoDescargaDAO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.putdvinddescarga.v1.DeclaracionesDeValorEstadoDescargaServiceDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.putdvinddescarga.v1.restfull.DeclaracionesDeValorEstadoDescargaDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.putdvinddescarga.v1.restfull.OutputDeclaracionesDeValorEstadoDescargaDTO;

import es.mercadona.gesaduan.jpa.declaracionesdevalor.postdv.v1.DeclaracionesDeValorPostJPA;
import es.mercadona.gesaduan.jpa.declaracionesdevalor.postdv.v1.DeclaracionesDeValorPostPK;

@Stateless
public class PutDVEstadoDescargaDAOImpl extends DaoBaseImpl<DeclaracionesDeValorPostPK, DeclaracionesDeValorPostJPA> implements PutDVEstadoDescargaDAO {

	@PersistenceContext
	private EntityManager entityM;
	
	@Override
	public void setEntityClass() {
		this.entityClass = DeclaracionesDeValorPostJPA.class;

	}
	
	@Override
	protected EntityManager getEntityManager() {
		
		return this.entityM;
	}

	@Transactional
	@Override
	public OutputDeclaracionesDeValorEstadoDescargaDTO actualizarEstadoDescarga(
			DeclaracionesDeValorEstadoDescargaServiceDTO input) {

		OutputDeclaracionesDeValorEstadoDescargaDTO result = new OutputDeclaracionesDeValorEstadoDescargaDTO();

		final StringBuilder sql = new StringBuilder();

		try {
			Integer codigoDeclaracion = input.getCodigoDeclaracion();
			Integer anyo = input.getAnyo();
			Integer version = input.getVersion();
			boolean estaDescargado = input.isEstaDescargado();

			sql.append(" UPDATE O_DECLARACION_VALOR_CAB ");
			sql.append(" SET MCA_DESCARGA = ?estaDescargado ");
			sql.append(" , FEC_DT_DESCARGA = systimestamp ");
			sql.append(" , COD_V_USR_MODIFICACION = ?usuario ");
			sql.append(" WHERE COD_N_DECLARACION_VALOR = ?codigoDeclaracion ");
			sql.append(" AND NUM_ANYO = ?anyo ");
			sql.append(" AND COD_N_VERSION = ?version ");

			final Query query = getEntityManager().createNativeQuery(sql.toString());

			if (estaDescargado) {
				query.setParameter("estaDescargado", new String("S"));
			}
			if (!estaDescargado) {
				query.setParameter("estaDescargado", new String("N"));
			}

			query.setParameter("usuario", input.getUsuario());
			query.setParameter("codigoDeclaracion", codigoDeclaracion);
			query.setParameter("anyo", anyo);
			query.setParameter("version", version);

			Integer resultado = query.executeUpdate();
	
			DeclaracionesDeValorEstadoDescargaDTO decEstaDesc = new DeclaracionesDeValorEstadoDescargaDTO();

			if(resultado == 1) {
				decEstaDesc.setCodigoDeclaracion(codigoDeclaracion);
				decEstaDesc.setAnyo(anyo);
				decEstaDesc.setVersion(version);
				result.setDatos(decEstaDesc);
			}else {
				result.setDatos(decEstaDesc);
			}

		} catch (Exception e) {
			
			throw new ApplicationException(e.getMessage());
		}

		return result;
	}

	

}
