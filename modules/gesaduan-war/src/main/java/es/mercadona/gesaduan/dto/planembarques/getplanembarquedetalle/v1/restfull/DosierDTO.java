package es.mercadona.gesaduan.dto.planembarques.getplanembarquedetalle.v1.restfull;

import java.util.List;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class DosierDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
    	
    private Long numDosier;
	private Long anyoDosier;
	private String fechaCreacion;
	private String usuarioCreacion;
	private Long codigoEstado;
	private String nombreEstado;
	private String fechaDescarga;
	private EquipoSimpleDTO equipo;
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
	public Long getAnyoDosier() {
		return anyoDosier;
	}
	/**
	 * @param anyoDosier the anyoDosier to set
	 */
	public void setAnyoDosier(Long anyoDosier) {
		this.anyoDosier = anyoDosier;
	}
	/**
	 * @return the fechaCreacion
	 */
	public String getFechaCreacion() {
		return fechaCreacion;
	}
	/**
	 * @param fechaCreacion the fechaCreacion to set
	 */
	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
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
	 * @return the codigoEstado
	 */
	public Long getCodigoEstado() {
		return codigoEstado;
	}
	/**
	 * @param codigoEstado the codigoEstado to set
	 */
	public void setCodigoEstado(Long codigoEstado) {
		this.codigoEstado = codigoEstado;
	}
	/**
	 * @return the nombreEstado
	 */
	public String getNombreEstado() {
		return nombreEstado;
	}
	/**
	 * @param nombreEstado the nombreEstado to set
	 */
	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}
	/**
	 * @return the fechaDescarga
	 */
	public String getFechaDescarga() {
		return fechaDescarga;
	}
	/**
	 * @param fechaDescarga the fechaDescarga to set
	 */
	public void setFechaDescarga(String fechaDescarga) {
		this.fechaDescarga = fechaDescarga;
	}
	/**
	 * @return the equipo
	 */
	public EquipoSimpleDTO getEquipo() {
		return equipo;
	}
	/**
	 * @param equipo the equipo to set
	 */
	public void setEquipo(EquipoSimpleDTO equipo) {
		this.equipo = equipo;
	}


	
}
