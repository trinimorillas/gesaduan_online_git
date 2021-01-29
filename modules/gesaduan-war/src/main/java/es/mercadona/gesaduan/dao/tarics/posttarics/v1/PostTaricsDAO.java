package es.mercadona.gesaduan.dao.tarics.posttarics.v1;


import es.mercadona.gesaduan.dto.tarics.common.v1.restfull.OutputTaricsDTO;
import es.mercadona.gesaduan.jpa.tarics.v1.TaricsJPA;

public interface PostTaricsDAO {
	
	public OutputTaricsDTO crearTarics(TaricsJPA input);

}
