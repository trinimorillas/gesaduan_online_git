package es.mercadona.gesaduan.jpa.dosier;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import es.mercadona.gesaduan.jpa.cargas.v1.CargasPkJPA;

public class DosierContenedorJPA implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long numContenedor;
	private String codigoAlmacen;
	private String fechaExpedicion;
	private String codigoUsuario;
	private Long numDosier;
	private Integer anyoDosier;
	/**
	 * @return the contenedor
	 */
	public Long getNumContenedor() {
		return numContenedor;
	}
	/**
	 * @param contenedor the contenedor to set
	 */
	public void setNumContenedor(Long numContenedor) {
		numContenedor = numContenedor;
	}
	/**
	 * @return the codigoAlmance
	 */
	public String getCodigoAlmacen() {
		return codigoAlmacen;
	}
	/**
	 * @param codigoAlmance the codigoAlmance to set
	 */
	public void setCodigoAlmacen(String codigoAlmacen) {
		codigoAlmacen = codigoAlmacen;
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
	 * @return the codigoUsuario
	 */
	public String getCodigoUsuario() {
		return codigoUsuario;
	}
	/**
	 * @param codigoUsuario the codigoUsuario to set
	 */
	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
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
