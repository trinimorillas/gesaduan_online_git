package es.mercadona.gesaduan.dto.equipotransporte.getcontenedores.v1.restfull;

import java.util.List;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class CargaDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;

	private String codigoCarga;
	private Integer codigoSuministro;
	private String nombreSuministro;
	private String codigoAlmacenOrigen;
	private String codigoCentroDestino;
	private Double numeroHuecoOcupado;
	private Double numeroPesoOcupado;
	private Number numeroDivision;
	private String pedidosSinValidar;
	private List<ContenedorDTO> contenedor;
	private List<PedidoDTO> pedido;

	public String getCodigoCarga() {
		return codigoCarga;
	}

	public void setCodigoCarga(String codigoCarga) {
		this.codigoCarga = codigoCarga;
	}

	public Integer getCodigoSuministro() {
		return codigoSuministro;
	}

	public void setCodigoSuministro(Integer codigoSuministro) {
		this.codigoSuministro = codigoSuministro;
	}

	public String getNombreSuministro() {
		return nombreSuministro;
	}

	public void setNombreSuministro(String nombreSuministro) {
		this.nombreSuministro = nombreSuministro;
	}

	public String getCodigoAlmacenOrigen() {
		return codigoAlmacenOrigen;
	}

	public void setCodigoAlmacenOrigen(String codigoAlmacenOrigen) {
		this.codigoAlmacenOrigen = codigoAlmacenOrigen;
	}

	public String getCodigoCentroDestino() {
		return codigoCentroDestino;
	}

	public void setCodigoCentroDestino(String codigoCentroDestino) {
		this.codigoCentroDestino = codigoCentroDestino;
	}

	public Double getNumeroHuecoOcupado() {
		return numeroHuecoOcupado;
	}

	public void setNumeroHuecoOcupado(Double numeroHuecoOcupado) {
		this.numeroHuecoOcupado = numeroHuecoOcupado;
	}

	public Double getNumeroPesoOcupado() {
		return numeroPesoOcupado;
	}

	public void setNumeroPesoOcupado(Double numeroPesoOcupado) {
		this.numeroPesoOcupado = numeroPesoOcupado;
	}

	public Number getNumeroDivision() {
		return numeroDivision;
	}

	public void setNumeroDivision(Number numeroDivision) {
		this.numeroDivision = numeroDivision;
	}

	public String getPedidosSinValidar() {
		return pedidosSinValidar;
	}

	public void setPedidosSinValidar(String pedidosSinValidar) {
		this.pedidosSinValidar = pedidosSinValidar;
	}

	public List<ContenedorDTO> getContenedor() {
		return contenedor;
	}

	public void setContenedor(List<ContenedorDTO> contenedor) {
		this.contenedor = contenedor;
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
