package es.mercadona.gesaduan.dto.cargas.cambiarestado.v1;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class CambiarEstadoCargaDTO extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;	
	private String codigoCarga;
	private String codigoAlmacenOrigen;
	private Integer codigoEstado;
	private Integer codigoEstadoActual;	
	
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
	
	public Integer getCodigoEstado() {
		return codigoEstado;
	}
	
	public void setCodigoEstado(Integer codigoEstado) {
		this.codigoEstado = codigoEstado;
	}

	public Integer getCodigoEstadoActual() {
		return codigoEstadoActual;
	}

	public void setCodigoEstadoActual(Integer codigoEstadoActual) {
		this.codigoEstadoActual = codigoEstadoActual;
	}	
	
	
}
