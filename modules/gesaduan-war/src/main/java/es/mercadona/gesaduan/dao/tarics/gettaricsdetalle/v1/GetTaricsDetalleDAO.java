package es.mercadona.gesaduan.dao.tarics.gettaricsdetalle.v1;


import es.mercadona.gesaduan.dto.tarics.gettaricsdetalle.v1.InputTaricsDetalleDTO;
import es.mercadona.gesaduan.dto.tarics.gettaricsdetalle.v1.restfull.OutputTaricsDetalleDTO;

public interface GetTaricsDetalleDAO {
	
	public OutputTaricsDetalleDTO getTaricsDetalle(InputTaricsDetalleDTO input);
	
	public boolean checkExistTaric(InputTaricsDetalleDTO input);

}
