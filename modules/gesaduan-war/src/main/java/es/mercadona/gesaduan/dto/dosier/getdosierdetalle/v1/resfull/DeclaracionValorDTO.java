package es.mercadona.gesaduan.dto.dosier.getdosierdetalle.v1.resfull;

import java.util.List;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class DeclaracionValorDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
	private Long codigoDV;
	private Integer anyoDV;
	private Integer versionDV;
	private String fechaExpedicion;
	private OrigenDTO origen;
	private String esDVCorrecta;
	private List<PedidoDTO> pedido;

	/**
	 * @return the codigoDV
	 */
	public Long getCodigoDV() {
		return codigoDV;
	}

	/**
	 * @param codigoDV the codigoDV to set
	 */
	public void setCodigoDV(Long codigoDV) {
		this.codigoDV = codigoDV;
	}

	/**
	 * @return the anyoDV
	 */
	public Integer getAnyoDV() {
		return anyoDV;
	}

	/**
	 * @param anyoDV the anyoDV to set
	 */
	public void setAnyoDV(Integer anyoDV) {
		this.anyoDV = anyoDV;
	}

	/**
	 * @return the versionDV
	 */
	public Integer getVersionDV() {
		return versionDV;
	}

	/**
	 * @param versionDV the versionDV to set
	 */
	public void setVersionDV(Integer versionDV) {
		this.versionDV = versionDV;
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
	 * @return the origen
	 */
	public OrigenDTO getOrigen() {
		return origen;
	}

	/**
	 * @param origen the origen to set
	 */
	public void setOrigen(OrigenDTO origen) {
		this.origen = origen;
	}

	/**
	 * @return the esDVCorrecta
	 */
	public String getEsDVCorrecta() {
		return esDVCorrecta;
	}

	/**
	 * @param esDVCorrecta the esDVCorrecta to set
	 */
	public void setEsDVCorrecta(String esDVCorrecta) {
		this.esDVCorrecta = esDVCorrecta;
	}

	/**
	 * @return the pedido
	 */
	public List<PedidoDTO> getPedido() {
		return pedido;
	}

	/**
	 * @param pedido the pedido to set
	 */
	public void setPedido(List<PedidoDTO> pedido) {
		this.pedido = pedido;
	}

}
