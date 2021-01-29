package es.mercadona.gesaduan.business.tarics.posttarics.v1;

import es.mercadona.gesaduan.dto.tarics.common.v1.restfull.OutputTaricsDTO;
import es.mercadona.gesaduan.dto.tarics.posttarics.v1.restful.InputDatosPostDTO;


public interface PostTaricsService {
	
	public OutputTaricsDTO createTarics(InputDatosPostDTO input);

}
