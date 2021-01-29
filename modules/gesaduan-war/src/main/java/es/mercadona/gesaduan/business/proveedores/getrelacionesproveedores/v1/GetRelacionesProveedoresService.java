package es.mercadona.gesaduan.business.proveedores.getrelacionesproveedores.v1;

import es.mercadona.fwk.core.web.BoPage;
import es.mercadona.gesaduan.dto.proveedores.getrelacionesproveedores.v1.InputRelacionesProveedoresDTO;
import es.mercadona.gesaduan.dto.proveedores.getrelacionesproveedores.v1.restfull.OutputRelacionesProveedoresDTO;

public interface GetRelacionesProveedoresService {
	
	public OutputRelacionesProveedoresDTO getRelacionesProveedores(InputRelacionesProveedoresDTO input, BoPage pagination);

}
