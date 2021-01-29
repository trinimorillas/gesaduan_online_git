package es.mercadona.gesaduan.business.productos.getproductos.v1;

import es.mercadona.fwk.core.web.BoPage;
import es.mercadona.gesaduan.dto.productos.getproductos.v1.InputGetProductoDTO;
import es.mercadona.gesaduan.dto.productos.getproductos.v1.restfull.OutputProductosDTO;

public interface GetProductosService {

	public OutputProductosDTO getProductos(InputGetProductoDTO input, BoPage pagination);
	
}
