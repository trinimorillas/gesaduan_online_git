package es.mercadona.gesaduan.dao.productos.getproductos.v1;

import es.mercadona.fwk.core.web.BoPage;
import es.mercadona.gesaduan.dto.productos.getproductos.v1.InputGetProductoDTO;
import es.mercadona.gesaduan.dto.productos.getproductos.v1.restfull.OutputProductosDTO;

public interface GetProductosDAO {

	public OutputProductosDTO getProductos(InputGetProductoDTO input, BoPage pagination);
	
}