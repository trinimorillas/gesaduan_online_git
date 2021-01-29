package es.mercadona.gesaduan.business.declaracionesdevalor.getdvdocumento.v1;

import es.mercadona.gesaduan.jpa.declaracionesdevalor.getdocumentodv.v1.DocumentoDVDataJPA;
import es.mercadona.gesaduan.jpa.declaracionesdevalor.getdocumentodv.v1.DocumentoDVDataPK;

public interface GetDVDocumentoService {

	public DocumentoDVDataJPA descargaDocumento(DocumentoDVDataPK inputPK);
	
}
