package es.mercadona.gesaduan.business.declaracionesdevalor.postdv.v1;

import es.mercadona.gesaduan.dto.declaracionesdevalor.postdv.v1.InputPutVDDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.postdv.v1.restfull.OutputPutVDDTO;

public interface PostDVService {	
	public OutputPutVDDTO createValueDeclaration(InputPutVDDTO input);
}