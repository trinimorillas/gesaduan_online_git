package es.mercadona.gesaduan.dto.dosier.putdosier.v1;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputDosierEquipoContenedorDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
	
  	private String numContenedor;
	private String codigoAlmacen;
	private String fechaExpedicion;
	
	/**
	 * @return the numContenedor
	 */
	public String getNumContenedor() {
		return numContenedor;
	}
	/**
	 * @param numContenedor the numContenedor to set
	 */
	public void setNumContenedor(String numContenedor) {
		this.numContenedor = numContenedor;
	}
	/**
	 * @return the codigoAlmacen
	 */
	public String getCodigoAlmacen() {
		return codigoAlmacen;
	}
	/**
	 * @param codigoAlmacen the codigoAlmacen to set
	 */
	public void setCodigoAlmacen(String codigoAlmacen) {
		this.codigoAlmacen = codigoAlmacen;
	}
	/**
	 * @return the fechaExpedicion
	 */
	public String getFechaExpedicion() {
		return fechaExpedicion;
	}
	/**
	 * @param fechaExpedicion the fechaExpedicion to set
	 */
	public void setFechaExpedicion(String fechaExpedicion) {
		this.fechaExpedicion = fechaExpedicion;
	}
	
	

}
