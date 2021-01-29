package es.mercadona.gesaduan.dao.productos.getproductosdetalle.v1;

import es.mercadona.gesaduan.dto.productos.getproductosdetalle.v1.InputProductosDetalleDTO;
import es.mercadona.gesaduan.dto.productos.getproductosdetalle.v1.restfull.OutputProductosDetalleDTO;

public interface GetProductosDetalleDAO {
	
	public OutputProductosDetalleDTO getProductosDetalle(InputProductosDetalleDTO input);

}
