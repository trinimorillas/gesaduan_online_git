package es.mercadona.gesaduan.dao.dosier.cambiarestado.v1.impl;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import es.mercadona.fwk.core.exceptions.ApplicationException;
import es.mercadona.fwk.data.DaoBaseImpl;
import es.mercadona.gesaduan.common.Constantes;
import es.mercadona.gesaduan.dao.dosier.cambiarestado.v1.GuardarPDFDAO;
import es.mercadona.gesaduan.jpa.dosier.getdocumento.v1.DocumentoDataJPA;

public class GuardarPDFDAOImpl extends DaoBaseImpl<Long, DocumentoDataJPA> implements GuardarPDFDAO {

	@Inject
	private org.slf4j.Logger logger;

	@PersistenceContext
	private EntityManager entityM;

	@Override
	protected EntityManager getEntityManager() {
		return this.entityM;
	}

	@Override
	public void setEntityClass() {
		this.entityClass = DocumentoDataJPA.class;
	}

	private final String LOG_FILE = "GuardarPDFDAOImpl(GESADUAN)";
	
	@Transactional
	@Override
	public void guardarPDF(DocumentoDataJPA documento) {
		try {
			entityM.persist(documento);
			entityM.flush();
		} catch (Exception ex) {
			this.logger.error(Constantes.FORMATO_ERROR_LOG, LOG_FILE, "getDocumentoInvalidado", ex.getClass().getSimpleName(), ex.getMessage());	
			throw new ApplicationException(ex.getMessage());
		}
	}

}