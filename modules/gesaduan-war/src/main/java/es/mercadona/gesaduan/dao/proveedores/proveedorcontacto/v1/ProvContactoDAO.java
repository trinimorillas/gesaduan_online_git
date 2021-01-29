package es.mercadona.gesaduan.dao.proveedores.proveedorcontacto.v1;

import es.mercadona.gesaduan.dto.proveedores.putproveedores.v1.restfull.InputPutProveedoresContactos;
import es.mercadona.gesaduan.jpa.proveedores.putproveedorescontacto.v1.ProveedorContactoJPA;

public interface ProvContactoDAO {

	
	public ProveedorContactoJPA putContactoProveedor(InputPutProveedoresContactos datos, Long codigoProveedor);
	
	public ProveedorContactoJPA deleteContactoProveedor(Long idProveedor, Long idContacto);
}
