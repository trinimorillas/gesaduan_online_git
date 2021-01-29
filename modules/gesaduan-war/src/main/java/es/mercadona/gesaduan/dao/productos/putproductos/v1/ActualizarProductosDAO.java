package es.mercadona.gesaduan.dao.productos.putproductos.v1;

import es.mercadona.gesaduan.dto.productos.putproductos.v1.restfull.OutputPutProductosDTO;

public interface ActualizarProductosDAO {
	
	OutputPutProductosDTO actualizarProductos(Long codigoProducto, String denominacionAlt, String denominacionFormato, String codigoUsuario);

}
