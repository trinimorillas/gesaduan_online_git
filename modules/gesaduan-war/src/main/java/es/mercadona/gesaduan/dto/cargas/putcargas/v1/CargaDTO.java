package es.mercadona.gesaduan.dto.cargas.putcargas.v1;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class CargaDTO extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;	
	private String codigoCarga;
	private String codigoAlmacenOrigen;
	private Integer codigoTipoCarga;
	private Integer codigoSuministro;
	private Integer codigoCategoria;
	private String codigoProveedor;
	private String codigoCentroDestino;
	private String fechaServicio;
	private String fechaEntrega;
	private Double numeroTotalHuecos;
	private Double numeroTotalPeso;
	private String marcaLpC;
	private Integer numeroDivisiones;
	private Double numeroHuecosRestantes;
	private Double numeroPesoRestante;
	
	public String getCodigoCarga() {
		return codigoCarga;
	}
	
	public void setCodigoCarga(String codigoCarga) {
		this.codigoCarga = codigoCarga;
	}
	
	public String getCodigoAlmacenOrigen() {
		return codigoAlmacenOrigen;
	}
	
	public void setCodigoAlmacenOrigen(String codigoAlmacenOrigen) {
		this.codigoAlmacenOrigen = codigoAlmacenOrigen;
	}
	
	public Integer getCodigoTipoCarga() {
		return codigoTipoCarga;
	}
	
	public void setCodigoTipoCarga(Integer codigoTipoCarga) {
		this.codigoTipoCarga = codigoTipoCarga;
	}
	
	public Integer getCodigoSuministro() {
		return codigoSuministro;
	}
	
	public void setCodigoSuministro(Integer codigoSuministro) {
		this.codigoSuministro = codigoSuministro;
	}
	
	public Integer getCodigoCategoria() {
		return codigoCategoria;
	}
	
	public void setCodigoCategoria(Integer codigoCategoria) {
		this.codigoCategoria = codigoCategoria;
	}
	
	public String getCodigoProveedor() {
		return codigoProveedor;
	}
	
	public void setCodigoProveedor(String codigoProveedor) {
		this.codigoProveedor = codigoProveedor;
	}
	
	public String getCodigoCentroDestino() {
		return codigoCentroDestino;
	}
	
	public void setCodigoCentroDestino(String codigoCentroDestino) {
		this.codigoCentroDestino = codigoCentroDestino;
	}
	
	public String getFechaServicio() {
		return fechaServicio;
	}
	
	public void setFechaServicio(String fechaServicio) {
		this.fechaServicio = fechaServicio;
	}
	
	public String getFechaEntrega() {
		return fechaEntrega;
	}
	
	public void setFechaEntrega(String fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	
	public Double getNumeroTotalHuecos() {
		return numeroTotalHuecos;
	}
	
	public void setNumeroTotalHuecos(Double numeroTotalHuecos) {
		this.numeroTotalHuecos = numeroTotalHuecos;
	}
	
	public Double getNumeroTotalPeso() {
		return numeroTotalPeso;
	}
	
	public void setNumeroTotalPeso(Double numeroTotalPeso) {
		this.numeroTotalPeso = numeroTotalPeso;
	}
	
	public String getMarcaLpC() {
		return marcaLpC;
	}
	
	public void setMarcaLpC(String marcaLpC) {
		this.marcaLpC = marcaLpC;
	}
	
	public Integer getNumeroDivisiones() {
		return numeroDivisiones;
	}
	
	public void setNumeroDivisiones(Integer numeroDivisiones) {
		this.numeroDivisiones = numeroDivisiones;
	}

	public Double getNumeroHuecosRestantes() {
		return numeroHuecosRestantes;
	}

	public void setNumeroHuecosRestantes(Double numeroHuecosRestantes) {
		this.numeroHuecosRestantes = numeroHuecosRestantes;
	}

	public Double getNumeroPesoRestante() {
		return numeroPesoRestante;
	}

	public void setNumeroPesoRestante(Double numeroPesoRestante) {
		this.numeroPesoRestante = numeroPesoRestante;
	}

}
