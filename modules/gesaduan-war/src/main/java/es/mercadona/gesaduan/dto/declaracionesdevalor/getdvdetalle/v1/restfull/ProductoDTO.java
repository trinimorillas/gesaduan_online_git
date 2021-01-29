package es.mercadona.gesaduan.dto.declaracionesdevalor.getdvdetalle.v1.restfull;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class ProductoDTO extends AbstractDTO{
	
private static final long serialVersionUID = 1L;
	
    private LineaDTO producto;

	public LineaDTO getProducto() {
		return producto;
	}

	public void setProducto(LineaDTO producto) {
		this.producto = producto;
	}
    
    

}
