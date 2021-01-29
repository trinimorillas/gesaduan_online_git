package es.mercadona.gesaduan.business.proveedores.getproveedoresdetalle.v1;

import java.math.BigDecimal;

import es.mercadona.gesaduan.dto.proveedores.getproveedoresdetalle.v1.InputProveedoresDetalleDTO;
import es.mercadona.gesaduan.dto.proveedores.getproveedoresdetalle.v1.restfull.OutputProveedoresDetalleDTO;

public interface GetProveedoresDetalleService {
	
	public OutputProveedoresDetalleDTO getProveedoresDetalle(InputProveedoresDetalleDTO input);

	public BigDecimal getCodigoProveedorSap(Long codigoProveedor);
}
