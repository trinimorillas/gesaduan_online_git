package es.mercadona.gesaduan.business.productos.putproductos.v1;

import es.mercadona.gesaduan.dto.productos.putproductos.v1.InputPutProductosDTO;
import es.mercadona.gesaduan.dto.productos.putproductos.v1.restfull.OutputPutProductosDTO;

public interface PutProductosService {
	public OutputPutProductosDTO actualizarProducto(Long codigoProducto, InputPutProductosDTO input);
}
