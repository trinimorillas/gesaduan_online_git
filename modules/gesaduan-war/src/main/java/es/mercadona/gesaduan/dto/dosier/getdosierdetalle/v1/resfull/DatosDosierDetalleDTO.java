package es.mercadona.gesaduan.dto.dosier.getdosierdetalle.v1.resfull;

import java.util.List;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class DatosDosierDetalleDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;

	private Long numDosier;
	private Integer anyoDosier;

	private String fechaCreacion;
	private String usuarioCreacion;
	private Integer codigoEstado;
	private String nombreEstado;
	private String fechaDescarga;
	private String mcaError;

	private List<EquipoDTO> equipo;
	private List<ContenedorDTO> contenedor;
	private List<FacturaDTO> factura;

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
	public Integer getCodigoEstado() {
		return codigoEstado;
	}

	/**
	 * @param codigoEstado the codigoEstado to set
	 */
	public void setCodigoEstado(Integer codigoEstado) {
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
	public List<EquipoDTO> getEquipo() {
		return equipo;
	}

	/**
	 * @param equipo the equipo to set
	 */
	public void setEquipo(List<EquipoDTO> equipo) {
		this.equipo = equipo;
	}

	/**
	 * @return the contenedor
	 */
	public List<ContenedorDTO> getContenedor() {
		return contenedor;
	}

	/**
	 * @param contenedor the contenedor to set
	 */
	public void setContenedor(List<ContenedorDTO> contenedor) {
		this.contenedor = contenedor;
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

	/**
	 * @return the factura
	 */
	public List<FacturaDTO> getFactura() {
		return factura;
	}

	/**
	 * @param factura the factura to set
	 */
	public void setFactura(List<FacturaDTO> factura) {
		this.factura = factura;
	}

}
