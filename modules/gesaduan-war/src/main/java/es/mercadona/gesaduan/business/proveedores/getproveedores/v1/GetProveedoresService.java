package es.mercadona.gesaduan.business.proveedores.getproveedores.v1;

import es.mercadona.fwk.core.web.BoPage;
import es.mercadona.gesaduan.dto.proveedores.getproveedores.v1.InputGetProveedoresDTO;
import es.mercadona.gesaduan.dto.proveedores.getproveedores.v1.restfull.OutputGetProveedoresDTO;


public interface GetProveedoresService {

	public OutputGetProveedoresDTO getProveedoresList(InputGetProveedoresDTO bodata, BoPage paginacion);
	
}
