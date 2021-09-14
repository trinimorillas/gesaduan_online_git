package es.mercadona.gesaduan.jpa.dosier;

import java.io.Serializable;

public class DosierContenedorJPA implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String numContenedor;
	private String codigoAlmacen;
	private String fechaExpedicion;
	private String usuarioCreacion;
	private Long codigoEquipo;
	private Long numDosier;
	private Integer anyoDosier;
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
	/**
	 * @return the usuarioCreacion
	 */
	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}
	/**
	 * @param usuarioCreacion the usuarioCreacion to set
	 */
	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}
	/**
	 * @return the codigoEquipo
	 */
	public Long getCodigoEquipo() {
		return codigoEquipo;
	}
	/**
	 * @param codigoEquipo the codigoEquipo to set
	 */
	public void setCodigoEquipo(Long codigoEquipo) {
		this.codigoEquipo = codigoEquipo;
	}
	/**
	 * @return the numDosier
	 */
	public Long getNumDosier() {
		return numDosier;
	}
	/**
	 * @param numDosier the numDosier to set
	 */
	public void setNumDosier(Long numDosier) {
		this.numDosier = numDosier;
	}
	/**
	 * @return the anyoDosier
	 */
	public Integer getAnyoDosier() {
		return anyoDosier;
	}
	/**
	 * @param anyoDosier the anyoDosier to set
	 */
	public void setAnyoDosier(Integer anyoDosier) {
		this.anyoDosier = anyoDosier;
	}
	
	
}
