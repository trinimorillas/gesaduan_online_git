package es.mercadona.gesaduan.business.declaracionesdevalor.getdvdocumento.v1.impl;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.declaracionesdevalor.getdvdocumento.v1.GetDVDocumentoService;
import es.mercadona.gesaduan.dao.declaracionesdevalor.getdvdocumento.v1.GetDVDocumentoDAO;
import es.mercadona.gesaduan.jpa.declaracionesdevalor.getdocumentodv.v1.DocumentoDVDataJPA;
import es.mercadona.gesaduan.jpa.declaracionesdevalor.getdocumentodv.v1.DocumentoDVDataPK;

public class GetDVDocumentoServiceImpl implements GetDVDocumentoService {

	@Inject
	private GetDVDocumentoDAO getDVDocumentoDAO;

	@Override
	public DocumentoDVDataJPA descargaDocumento(DocumentoDVDataPK inputPK) {

		return getDVDocumentoDAO.descargaDocumento(inputPK);

	}

}
