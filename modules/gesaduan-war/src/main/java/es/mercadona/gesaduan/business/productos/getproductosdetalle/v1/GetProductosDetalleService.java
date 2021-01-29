package es.mercadona.gesaduan.business.productos.getproductosdetalle.v1;

import es.mercadona.gesaduan.dto.productos.getproductosdetalle.v1.InputProductosDetalleDTO;
import es.mercadona.gesaduan.dto.productos.getproductosdetalle.v1.restfull.OutputProductosDetalleDTO;

public interface GetProductosDetalleService {
	
	public OutputProductosDetalleDTO getProductosDetalle(InputProductosDetalleDTO input);

}
