package es.mercadona.gesaduan.dao.proveedores.getproveedores.v1;

import es.mercadona.fwk.core.web.BoPage;
import es.mercadona.gesaduan.dto.proveedores.getproveedores.v1.InputGetProveedoresDTO;
import es.mercadona.gesaduan.dto.proveedores.getproveedores.v1.restfull.OutputGetProveedoresDTO;


public interface GetProveedoresDAO {

	public OutputGetProveedoresDTO obtenerProveedores(InputGetProveedoresDTO input,
			BoPage paginacion);
	
}
