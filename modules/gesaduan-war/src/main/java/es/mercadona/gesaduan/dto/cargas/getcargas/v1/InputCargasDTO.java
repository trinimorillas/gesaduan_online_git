package es.mercadona.gesaduan.dto.cargas.getcargas.v1;

import java.io.Serializable;
import java.util.List;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputCargasDTO extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String codigoCarga;
	private String codigoPedido;
	private Long codigoProveedor;
	private Integer codigoTipoCarga;
	private Integer codigoSuministro;
	private String fechaServicio;
	private String fechaEntrega;
	private String fechaIntroduccion;
	private Integer codigoBloqueOrigen;
	private String codigoCentroOrigen;
	private String codigoCentroDestino;
	private String usuarioCreacion;
	private String mcaPedidosSinValidar;
	private String aplicacionOrigen;
	private String orden;
	private List<EstadoDTO> estado;
	
	public String getCodigoCarga() {
		return codigoCarga;
	}
	
	public void setCodigoCarga(String codigoCarga) {
		this.codigoCarga = codigoCarga;
	}
	
	public String getCodigoPedido() {
		return codigoPedido;
	}
	
	public void setCodigoPedido(String codigoPedido) {
		this.codigoPedido = codigoPedido;
	}
	
	public Long getCodigoProveedor() {
		return codigoProveedor;
	}
	
	public void setCodigoProveedor(Long codigoProveedor) {
		this.codigoProveedor = codigoProveedor;
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
	
	public Integer getCodigoBloqueOrigen() {
		return codigoBloqueOrigen;
	}
	
	public void setCodigoBloqueOrigen(Integer codigoBloqueOrigen) {
		this.codigoBloqueOrigen = codigoBloqueOrigen;
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
	
	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}
	
	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
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

	public String getOrden() {
		return orden;
	}

	public void setOrden(String orden) {
		this.orden = orden;
	}

	public List<EstadoDTO> getEstado() {
		return estado;
	}

	public void setEstado(List<EstadoDTO> estado) {
		this.estado = estado;
	}

}
