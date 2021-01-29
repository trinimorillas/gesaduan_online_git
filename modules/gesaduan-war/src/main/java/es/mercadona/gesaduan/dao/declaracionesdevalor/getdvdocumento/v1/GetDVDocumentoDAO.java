package es.mercadona.gesaduan.dao.declaracionesdevalor.getdvdocumento.v1;

import es.mercadona.gesaduan.jpa.declaracionesdevalor.getdocumentodv.v1.DocumentoDVDataJPA;
import es.mercadona.gesaduan.jpa.declaracionesdevalor.getdocumentodv.v1.DocumentoDVDataPK;

public interface GetDVDocumentoDAO {

	public DocumentoDVDataJPA descargaDocumento(DocumentoDVDataPK inputPK);
	
}
