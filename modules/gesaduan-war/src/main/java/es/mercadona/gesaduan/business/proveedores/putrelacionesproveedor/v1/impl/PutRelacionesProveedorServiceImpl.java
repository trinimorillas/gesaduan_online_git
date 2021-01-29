package es.mercadona.gesaduan.business.proveedores.putrelacionesproveedor.v1.impl;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.proveedores.putrelacionesproveedor.v1.PutRelacionesProveedorService;
import es.mercadona.gesaduan.dao.proveedores.putrelacionesproveedor.v1.PutRelacionesProveedorDAO;
import es.mercadona.gesaduan.dto.proveedores.putrelacionesproveedores.v1.InputRelacionesProveedorDTO;

public class PutRelacionesProveedorServiceImpl implements PutRelacionesProveedorService{
	
	@Inject
	private PutRelacionesProveedorDAO putRelacionesProveedorDao;

	@Override
	public void editRelacionesProveedor(InputRelacionesProveedorDTO input) {
		
		putRelacionesProveedorDao.editarRelacionesProveedor(input);
	}

}
