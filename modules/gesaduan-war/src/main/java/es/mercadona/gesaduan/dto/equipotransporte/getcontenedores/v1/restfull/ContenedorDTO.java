package es.mercadona.gesaduan.dto.equipotransporte.getcontenedores.v1.restfull;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class ContenedorDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;

	private Long numContenedor;
	private String mcaFacturado;
	private String fechaExpedicion;

	public Long getNumContenedor() {
		return numContenedor;
	}

	public void setNumContenedor(Long numContenedor) {
		this.numContenedor = numContenedor;
	}

	/**
	 * @return the mcaFacturado
	 */
	public String getMcaFacturado() {
		return mcaFacturado;
	}

	/**
	 * @param mcaFacturado the mcaFacturado to set
	 */
	public void setMcaFacturado(String mcaFacturado) {
		this.mcaFacturado = mcaFacturado;
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
