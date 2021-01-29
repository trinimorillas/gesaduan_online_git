package es.mercadona.gesaduan.business.proveedores.getrelacionesproveedores.v1.impl;

import javax.inject.Inject;

import es.mercadona.fwk.core.web.BoPage;
import es.mercadona.gesaduan.business.proveedores.getrelacionesproveedores.v1.GetRelacionesProveedoresService;
import es.mercadona.gesaduan.dao.proveedores.getrelacionesproveedores.v1.GetRelacionesProveedoresDAO;
import es.mercadona.gesaduan.dto.proveedores.getrelacionesproveedores.v1.InputRelacionesProveedoresDTO;
import es.mercadona.gesaduan.dto.proveedores.getrelacionesproveedores.v1.restfull.OutputRelacionesProveedoresDTO;

public class GetRelacionesProveedoresServiceImpl implements GetRelacionesProveedoresService{
	
	@Inject
	private GetRelacionesProveedoresDAO getRelacionesProveedoresDao;

	@Override
	public OutputRelacionesProveedoresDTO getRelacionesProveedores(InputRelacionesProveedoresDTO input, BoPage pagination) {
		
		OutputRelacionesProveedoresDTO response = getRelacionesProveedoresDao.getRelacionesProveedores(input, pagination);
		
		return response;
	}

}
