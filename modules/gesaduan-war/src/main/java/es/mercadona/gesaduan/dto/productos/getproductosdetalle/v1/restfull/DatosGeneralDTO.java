package es.mercadona.gesaduan.dto.productos.getproductosdetalle.v1.restfull;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class DatosGeneralDTO extends AbstractDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DatosProductosDetalleDTO producto;

	public DatosProductosDetalleDTO getProducto() {
		return producto;
	}

	public void setProducto(DatosProductosDetalleDTO producto) {
		this.producto = producto;
	}

	
}
