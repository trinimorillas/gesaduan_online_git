package es.mercadona.gesaduan.dto.equipotransporte.getequipotransportedetalle.v1.restfull;

import java.util.List;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class ContenedorDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;

	private String numContenedor;
	private String codigoCarga;
	private Integer codigoTipoCarga;
	private Integer codigoSuministro;
	private String nombreSuministro;
	private Long codigoProveedor;
	private String nombreProveedor;
	private String codigoCentroOrigen;
	private String codigoCentroDestino;
	private String marcaLpC;
	private String mcaFacturado;
	private String fechaExpedicion;
	private String fechaEntrega;
	private String fechaServicio;
	private String codigoAlmacen;
	private Integer codigoCategoria;
	private String nombreCategoria;
	private Integer numDosier;
	private Integer anyoDosier;
	private List<PedidoDTO> pedido;

	public String getNumContenedor() {
		return numContenedor;
	}

	public void setNumContenedor(String numContenedor) {
		this.numContenedor = numContenedor;
	}

	public String getCodigoCarga() {
		return codigoCarga;
	}

	public void setCodigoCarga(String codigoCarga) {
		this.codigoCarga = codigoCarga;
	}

	/**
	 * @return the codigoTipoCarga
	 */
	public Integer getCodigoTipoCarga() {
		return codigoTipoCarga;
	}

	/**
	 * @param codigoTipoCarga the codigoTipoCarga to set
	 */
	public void setCodigoTipoCarga(Integer codigoTipoCarga) {
		this.codigoTipoCarga = codigoTipoCarga;
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

	public Long getCodigoProveedor() {
		return codigoProveedor;
	}

	public void setCodigoProveedor(Long codigoProveedor) {
		this.codigoProveedor = codigoProveedor;
	}

	public String getNombreProveedor() {
		return nombreProveedor;
	}

	public void setNombreProveedor(String nombreProveedor) {
		this.nombreProveedor = nombreProveedor;
	}

	public String getCodigoCentroOrigen() {
		return codigoCentroOrigen;
	}

	public void setCodigoCentroOrigen(String codigoCentroOrigen) {
		this.codigoCentroOrigen = codigoCentroOrigen;
	}

	public String getCodigoCentroDestino() {
		return codigoCentroDestino;
	}

	public void setCodigoCentroDestino(String codigoCentroDestino) {
		this.codigoCentroDestino = codigoCentroDestino;
	}

	public String getMarcaLpC() {
		return marcaLpC;
	}

	public void setMarcaLpC(String marcaLpC) {
		this.marcaLpC = marcaLpC;
	}

	public String getFechaExpedicion() {
		return fechaExpedicion;
	}

	public void setFechaExpedicion(String fechaExpedicion) {
		this.fechaExpedicion = fechaExpedicion;
	}

	public String getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(String fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	/**
	 * @return the fechaServicio
	 */
	public String getFechaServicio() {
		return fechaServicio;
	}

	/**
	 * @param fechaServicio the fechaServicio to set
	 */
	public void setFechaServicio(String fechaServicio) {
		this.fechaServicio = fechaServicio;
	}

	public String getCodigoAlmacen() {
		return codigoAlmacen;
	}

	public void setCodigoAlmacen(String codigoAlmacen) {
		this.codigoAlmacen = codigoAlmacen;
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

	public Integer getNumDosier() {
		return numDosier;
	}

	public void setNumDosier(Integer numDosier) {
		this.numDosier = numDosier;
	}

	public Integer getAnyoDosier() {
		return anyoDosier;
	}

	public void setAnyoDosier(Integer anyoDosier) {
		this.anyoDosier = anyoDosier;
	}

	public String getMcaFacturado() {
		return mcaFacturado;
	}

	public void setMcaFacturado(String mcaFacturado) {
		this.mcaFacturado = mcaFacturado;
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
