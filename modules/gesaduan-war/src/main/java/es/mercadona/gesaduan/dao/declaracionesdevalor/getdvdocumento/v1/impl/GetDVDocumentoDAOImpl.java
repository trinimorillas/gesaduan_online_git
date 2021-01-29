package es.mercadona.gesaduan.dao.declaracionesdevalor.getdvdocumento.v1.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import es.mercadona.fwk.data.DaoBaseImpl;
import es.mercadona.gesaduan.dao.declaracionesdevalor.getdvdocumento.v1.GetDVDocumentoDAO;
import es.mercadona.gesaduan.jpa.declaracionesdevalor.getdocumentodv.v1.DocumentoDVDataJPA;
import es.mercadona.gesaduan.jpa.declaracionesdevalor.getdocumentodv.v1.DocumentoDVDataPK;
import es.mercadona.fwk.core.exceptions.ApplicationException;

@Stateless
public class GetDVDocumentoDAOImpl extends DaoBaseImpl<DocumentoDVDataPK, DocumentoDVDataJPA> implements GetDVDocumentoDAO{

	@PersistenceContext
	private EntityManager entityM;
	
	@Override
	public DocumentoDVDataJPA descargaDocumento(DocumentoDVDataPK inputPK) {
		
		DocumentoDVDataJPA documento =  null;
		
		try {
			documento = findById(inputPK);
		} catch (Exception e) {
			throw new ApplicationException(e.getMessage());
		}
	
		
		return documento;
	}

	@Override
	protected EntityManager getEntityManager() {
		
		return this.entityM;
	}

	@Override
	public void setEntityClass() {
		entityClass = DocumentoDVDataJPA.class;
		
	}

	
	
	
	
}
