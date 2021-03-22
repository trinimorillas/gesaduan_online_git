package es.mercadona.gesaduan.dto.equipotransporte.getequipotransportedetalle.v1.restfull;

import java.util.List;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class CargaDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
    	
    private String codigoCarga;
	private Integer codigoTipoCarga;
	private String nombreTipoCarga;
	private Integer codigoSuministro;
	private String nombreSuministro;
	private Integer codigoCategoria;
	private String nombreCategoria;
	private String codigoProveedor;
	private String nombreProveedor;
	private String codigoAlmacenOrigen;
	private String codigoCentroDestino;
	private String fechaEntrega;
	private String fechaServicio;
	private Integer codigoEstado;
	private String nombreEstado;
	private Double numeroHuecoOcupado;
	private Double numeroPesoOcupado;
	private Number numeroDivision;
	private String marcaLpC;
	private String pedidosSinValidar;
	private List<PedidoDTO> pedido;
	private List<ContenedorDTO> contenedor;
	
	public String getCodigoCarga() {
		return codigoCarga;
	}
	
	public void setCodigoCarga(String codigoCarga) {
		this.codigoCarga = codigoCarga;
	}
	
	public Integer getCodigoTipoCarga() {
		return codigoTipoCarga;
	}
	
	public void setCodigoTipoCarga(Integer codigoTipoCarga) {
		this.codigoTipoCarga = codigoTipoCarga;
	}
	
	public String getNombreTipoCarga() {
		return nombreTipoCarga;
	}
	
	public void setNombreTipoCarga(String nombreTipoCarga) {
		this.nombreTipoCarga = nombreTipoCarga;
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
	
	public Integer getCodigoCategoria() {
		return codigoCategoria;
	}
	
	public void setCodigoCategoria(Integer codigoCategoria) {
		this.codigoCategoria = codigoCategoria;
	}
	
	public String getNombreCategoria() {
		return nombreCategoria;
	}
	
	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}
	
	public String getCodigoProveedor() {
		return codigoProveedor;
	}
	
	public void setCodigoProveedor(String codigoProveedor) {
		this.codigoProveedor = codigoProveedor;
	}
	
	public String getNombreProveedor() {
		return nombreProveedor;
	}
	
	public void setNombreProveedor(String nombreProveedor) {
		this.nombreProveedor = nombreProveedor;
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
	
	public String getFechaEntrega() {
		return fechaEntrega;
	}
	
	public void setFechaEntrega(String fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	
	public String getFechaServicio() {
		return fechaServicio;
	}
	
	public void setFechaServicio(String fechaServicio) {
		this.fechaServicio = fechaServicio;
	}
	
	public Integer getCodigoEstado() {
		return codigoEstado;
	}
	
	public void setCodigoEstado(Integer codigoEstado) {
		this.codigoEstado = codigoEstado;
	}
	
	public String getNombreEstado() {
		return nombreEstado;
	}
	
	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
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

	public String getMarcaLpC() {
		return marcaLpC;
	}

	public void setMarcaLpC(String marcaLpC) {
		this.marcaLpC = marcaLpC;
	}

	public String getPedidosSinValidar() {
		return pedidosSinValidar;
	}

	public void setPedidosSinValidar(String pedidosSinValidar) {
		this.pedidosSinValidar = pedidosSinValidar;
	}

	public List<PedidoDTO> getPedido() {
		return pedido;
	}

	public void setPedido(List<PedidoDTO> pedido) {
		this.pedido = pedido;
	}

	public List<ContenedorDTO> getContenedor() {
		return contenedor;
	}

	public void setContenedor(List<ContenedorDTO> contenedor) {
		this.contenedor = contenedor;
	}
}
