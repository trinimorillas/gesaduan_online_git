package es.mercadona.gesaduan.business.proveedores.putproveedores.v1.impl;

import java.util.HashMap;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.proveedores.putproveedores.v1.PutProveedoresService;
import es.mercadona.gesaduan.dao.proveedores.putproveedores.v1.PutProveedoresDAO;
import es.mercadona.gesaduan.dto.proveedores.putproveedores.v1.restfull.InputPutProveedores;
import es.mercadona.gesaduan.dto.proveedores.putproveedores.v1.restfull.OutputProveedoresPut;

public class PutProveedoresServiceImpl implements PutProveedoresService{
	
	@Inject
	private PutProveedoresDAO putProveedorDao;

	@Override
	public OutputProveedoresPut activarProveedores(InputPutProveedores input) {
		
		OutputProveedoresPut response = new OutputProveedoresPut();
		putProveedorDao.activarProveedores(input);
		
		HashMap<String, String> metadatos = new HashMap<String, String>();
		response.setMetadatos(metadatos);
		
		return response;
	}

}
