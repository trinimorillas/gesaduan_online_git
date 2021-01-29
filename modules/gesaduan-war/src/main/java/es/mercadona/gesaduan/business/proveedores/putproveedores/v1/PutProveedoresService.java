package es.mercadona.gesaduan.business.proveedores.putproveedores.v1;

import es.mercadona.gesaduan.dto.proveedores.putproveedores.v1.restfull.InputPutProveedores;
import es.mercadona.gesaduan.dto.proveedores.putproveedores.v1.restfull.OutputProveedoresPut;

public interface PutProveedoresService {
	
	public OutputProveedoresPut activarProveedores(InputPutProveedores input);

}
