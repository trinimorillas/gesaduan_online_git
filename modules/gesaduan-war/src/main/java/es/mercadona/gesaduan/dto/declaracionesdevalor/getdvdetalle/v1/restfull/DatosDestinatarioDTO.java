package es.mercadona.gesaduan.dto.declaracionesdevalor.getdvdetalle.v1.restfull;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class DatosDestinatarioDTO extends AbstractDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoAlmacen;
	private String nombreAlmacen;
	
	public String getCodigoAlmacen() {
		return codigoAlmacen;
	}
	public void setCodigoAlmacen(String codigoAlmacen) {
		this.codigoAlmacen = codigoAlmacen;
	}
	public String getNombreAlmacen() {
		return nombreAlmacen;
	}
	public void setNombreAlmacen(String nombreAlmacen) {
		this.nombreAlmacen = nombreAlmacen;
	}
	
	
	
	

}
