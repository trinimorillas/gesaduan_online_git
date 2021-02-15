package es.mercadona.gesaduan.business.proveedores.contactosproveedor.v1;

import es.mercadona.gesaduan.dto.proveedores.putproveedores.v1.restfull.InputPutProveedoresContactos;
import es.mercadona.gesaduan.jpa.proveedores.putproveedorescontacto.v1.ProveedorContactoJPA;

public interface ContactosProvService {

	public ProveedorContactoJPA putContactoProveedor(InputPutProveedoresContactos datos, String codigoProveedor);
	
	public ProveedorContactoJPA deleteContactoProveedor(String idProveedor, Long idContacto);
	
}
