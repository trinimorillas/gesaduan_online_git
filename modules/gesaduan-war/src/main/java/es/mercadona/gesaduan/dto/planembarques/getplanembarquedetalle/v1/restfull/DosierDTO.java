package es.mercadona.gesaduan.dto.planembarques.getplanembarquedetalle.v1.restfull;

import java.util.List;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class DosierDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;

	private Long numDosier;
	private Integer anyoDosier;
	private String fechaCreacion;
	private String usuarioCreacion;
	private Long codigoEstado;
	private String nombreEstado;
	private String fechaDescargaExportador;
	private String fechaDescargaImportador;
	private String mcaError;
	private List<EquipoSimpleDTO> equipo;

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
	 * @return the equipo
	 */
	public List<EquipoSimpleDTO> getEquipo() {
		return equipo;
	}

	/**
	 * @param equipo the equipo to set
	 */
	public void setEquipo(List<EquipoSimpleDTO> equipo) {
		this.equipo = equipo;
	}

	/**
	 * @return the fechaDescargaExportador
	 */
	public String getFechaDescargaExportador() {
		return fechaDescargaExportador;
	}

	/**
	 * @param fechaDescargaExportador the fechaDescargaExportador to set
	 */
	public void setFechaDescargaExportador(String fechaDescargaExportador) {
		this.fechaDescargaExportador = fechaDescargaExportador;
	}

	/**
	 * @return the fechaDescargaImportador
	 */
	public String getFechaDescargaImportador() {
		return fechaDescargaImportador;
	}

	/**
	 * @param fechaDescargaImportador the fechaDescargaImportador to set
	 */
	public void setFechaDescargaImportador(String fechaDescargaImportador) {
		this.fechaDescargaImportador = fechaDescargaImportador;
	}

	/**
	 * @return the mcaError
	 */
	public String getMcaError() {
		return mcaError;
	}

	/**
	 * @param mcaError the mcaError to set
	 */
	public void setMcaError(String mcaError) {
		this.mcaError = mcaError;
	}

}
