package es.mercadona.gesaduan.dto.productos.getproductosdetalle.v1;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class InputProductosDetalleDTO extends AbstractDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String locale;
	private String codigoProducto;
	
	
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public String getCodigoProducto() {
		return codigoProducto;
	}
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}
		
	

}
