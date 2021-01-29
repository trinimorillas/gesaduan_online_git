package es.mercadona.gesaduan.business.proveedores.contactosproveedor.v1.impl;

import javax.inject.Inject;

import es.mercadona.gesaduan.business.proveedores.contactosproveedor.v1.ContactosProvService;
import es.mercadona.gesaduan.dao.proveedores.proveedorcontacto.v1.ProvContactoDAO;
import es.mercadona.gesaduan.dto.proveedores.putproveedores.v1.restfull.InputPutProveedoresContactos;
import es.mercadona.gesaduan.jpa.proveedores.putproveedorescontacto.v1.ProveedorContactoJPA;

public class ContactosProvServiceImpl implements ContactosProvService {

	@Inject
	private ProvContactoDAO provContactoDAO;
	
	@Override
	public ProveedorContactoJPA putContactoProveedor(InputPutProveedoresContactos datos, Long codigoProveedor) {
		
		return provContactoDAO.putContactoProveedor(datos, codigoProveedor);
		
	}

	@Override
	public ProveedorContactoJPA deleteContactoProveedor(Long idProveedor, Long idContacto) {
		
		return provContactoDAO.deleteContactoProveedor(idProveedor, idContacto);
		
	}

	
	
}
