package es.mercadona.gesaduan.dao.declaracionesdevalor.getdvdocumento.v2;

import es.mercadona.gesaduan.dto.declaracionesdevalor.getdvdocumento.v2.InputDeclaracionesDeValorDocumentoDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.getdvdocumento.v2.OutputDeclaracionesDeValorDocCabDTO;

public interface GetDVDocumentoOnlineDAO {

	public OutputDeclaracionesDeValorDocCabDTO getDatosDocumento(InputDeclaracionesDeValorDocumentoDTO input);
	
}
