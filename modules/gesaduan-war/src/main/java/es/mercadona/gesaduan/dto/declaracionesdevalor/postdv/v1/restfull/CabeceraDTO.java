package es.mercadona.gesaduan.dto.declaracionesdevalor.postdv.v1.restfull;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class CabeceraDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
	private DatosFacturaDTO datosFactura;
	private DatosPedidoDTO pedidoList;
	private OrigenDTO origen;
	private DestinoDTO destino;

	/**
	 * @return the datosFactura
	 */
	public DatosFacturaDTO getDatosFactura() {
		return datosFactura;
	}

	/**
	 * @param datosFactura the datosFactura to set
	 */
	public void setDatosFactura(DatosFacturaDTO datosFactura) {
		this.datosFactura = datosFactura;
	}

	/**
	 * @return the pedidoList
	 */
	public DatosPedidoDTO getPedidoList() {
		return pedidoList;
	}

	/**
	 * @param pedidoList the pedidoList to set
	 */
	public void setPedidoList(DatosPedidoDTO pedidoList) {
		this.pedidoList = pedidoList;
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
	 * @return the destino
	 */
	public DestinoDTO getDestino() {
		return destino;
	}

	/**
	 * @param destino the destino to set
	 */
	public void setDestino(DestinoDTO destino) {
		this.destino = destino;
	}

}