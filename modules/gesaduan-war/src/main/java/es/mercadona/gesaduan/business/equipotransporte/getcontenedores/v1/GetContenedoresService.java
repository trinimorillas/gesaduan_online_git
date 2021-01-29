package es.mercadona.gesaduan.business.equipotransporte.getcontenedores.v1;

import es.mercadona.gesaduan.dto.equipotransporte.getcontenedores.v1.InputDatosGetContenedorDTO;
import es.mercadona.gesaduan.dto.equipotransporte.getcontenedores.v1.restfull.OutputGetContenedoresDTO;

public interface GetContenedoresService {	
	public OutputGetContenedoresDTO listarContenedores(InputDatosGetContenedorDTO datos);
}
