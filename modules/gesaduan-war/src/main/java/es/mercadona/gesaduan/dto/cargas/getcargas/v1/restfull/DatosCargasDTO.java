package es.mercadona.gesaduan.dto.cargas.getcargas.v1.restfull;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class DatosCargasDTO extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String codigoCarga;
	private Integer codigoTipoCarga;
	private String nombreTipoCarga;
	private Integer codigoTipoSuministro;
	private String nombreTipoSuministro;
	private Long codigoProveedor;
	private String nombreProveedor;
	private String codigoCentroOrigen;
	private String codigoCentroDestino;
	private String fechaServicio;
	private String fechaEntrega;
	private String fechaIntroduccion;
	private Integer numeroDivisiones;
	private Double numeroHuecosOrigen;
	private Double numeroHuecos;
	private Double numeroHuecosRestantes;
	private Double numeroPesoTotal;
	private Double numeroPesoRestante;
	private Double numeroVolumenTotal;
	private Integer codigoCategoria;
	private String nombreCategoria;
	private String lpc;
	private Integer codigoEstado;
	private String nombreEstado;
	private String mcaPedidosSinValidar;
	private String aplicacionOrigen;
	private String codigoUsuarioCreacion;
	private String fechaValidacion;	
	private String codigoUsuarioValidacion;	
	
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
	
	public Integer getCodigoTipoSuministro() {
		return codigoTipoSuministro;
	}
	
	public void setCodigoTipoSuministro(Integer codigoTipoSuministro) {
		this.codigoTipoSuministro = codigoTipoSuministro;
	}
	
	public String getNombreTipoSuministro() {
		return nombreTipoSuministro;
	}
	
	public void setNombreTipoSuministro(String nombreTipoSuministro) {
		this.nombreTipoSuministro = nombreTipoSuministro;
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
	
	public String getFechaIntroduccion() {
		return fechaIntroduccion;
	}
	
	public void setFechaIntroduccion(String fechaIntroduccion) {
		this.fechaIntroduccion = fechaIntroduccion;
	}
	
	public Integer getNumeroDivisiones() {
		return numeroDivisiones;
	}
	
	public void setNumeroDivisiones(Integer numeroDivisiones) {
		this.numeroDivisiones = numeroDivisiones;
	}
	
	public Double getNumeroHuecosOrigen() {
		return numeroHuecosOrigen;
	}

	public void setNumeroHuecosOrigen(Double numeroHuecosOrigen) {
		this.numeroHuecosOrigen = numeroHuecosOrigen;
	}

	public Double getNumeroHuecos() {
		return numeroHuecos;
	}
	
	public void setNumeroHuecos(Double numeroHuecos) {
		this.numeroHuecos = numeroHuecos;
	}
	
	public Double getNumeroHuecosRestantes() {
		return numeroHuecosRestantes;
	}
	
	public void setNumeroHuecosRestantes(Double numeroHuecosRestantes) {
		this.numeroHuecosRestantes = numeroHuecosRestantes;
	}
	
	public Double getNumeroPesoTotal() {
		return numeroPesoTotal;
	}
	
	public void setNumeroPesoTotal(Double numeroPesoTotal) {
		this.numeroPesoTotal = numeroPesoTotal;
	}
	
	public Double getNumeroPesoRestante() {
		return numeroPesoRestante;
	}
	
	public void setNumeroPesoRestante(Double numeroPesoRestante) {
		this.numeroPesoRestante = numeroPesoRestante;
	}
	
	public Double getNumeroVolumenTotal() {
		return numeroVolumenTotal;
	}

	public void setNumeroVolumenTotal(Double numeroVolumenTotal) {
		this.numeroVolumenTotal = numeroVolumenTotal;
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
	
	public String getLpc() {
		return lpc;
	}
	
	public void setLpc(String lpc) {
		this.lpc = lpc;
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
	
	public String getMcaPedidosSinValidar() {
		return mcaPedidosSinValidar;
	}

	public void setMcaPedidosSinValidar(String mcaPedidosSinValidar) {
		this.mcaPedidosSinValidar = mcaPedidosSinValidar;
	}

	public String getAplicacionOrigen() {
		return aplicacionOrigen;
	}

	public void setAplicacionOrigen(String aplicacionOrigen) {
		this.aplicacionOrigen = aplicacionOrigen;
	}

	public String getCodigoUsuarioCreacion() {
		return codigoUsuarioCreacion;
	}
	
	public void setCodigoUsuarioCreacion(String codigoUsuarioCreacion) {
		this.codigoUsuarioCreacion = codigoUsuarioCreacion;
	}

	public String getFechaValidacion() {
		return fechaValidacion;
	}

	public void setFechaValidacion(String fechaValidacion) {
		this.fechaValidacion = fechaValidacion;
	}

	public String getCodigoUsuarioValidacion() {
		return codigoUsuarioValidacion;
	}

	public void setCodigoUsuarioValidacion(String codigoUsuarioValidacion) {
		this.codigoUsuarioValidacion = codigoUsuarioValidacion;
	}
	
}