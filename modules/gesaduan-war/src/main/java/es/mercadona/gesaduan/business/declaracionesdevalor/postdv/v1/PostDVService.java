package es.mercadona.gesaduan.business.declaracionesdevalor.postdv.v1;


import es.mercadona.gesaduan.dto.declaracionesdevalor.postdv.v1.restfull.InputPostDeclaracionesDeValorDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalor.postdv.v1.restfull.OutputPostDeclaracionesDeValorDTO;

public interface PostDVService {
	
	public OutputPostDeclaracionesDeValorDTO createDeclaracionesDeValor(InputPostDeclaracionesDeValorDTO input);

}
