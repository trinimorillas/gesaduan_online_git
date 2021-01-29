package es.mercadona.gesaduan.dto.declaracionesdevalor.putdvinddescarga.v1;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class DeclaracionesDeValorEstadoDescargaServiceDTO extends AbstractDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer codigoDeclaracion;
	private Integer anyo;
	private Integer version;
	private boolean estaDescargado;
	private String usuario;
	
	

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Integer getAnyo() {
		return anyo;
	}

	public void setAnyo(Integer anyo) {
		this.anyo = anyo;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getCodigoDeclaracion() {
		return codigoDeclaracion;
	}

	public void setCodigoDeclaracion(Integer codigoDeclaracion) {
		this.codigoDeclaracion = codigoDeclaracion;
	}

	public boolean isEstaDescargado() {
		return estaDescargado;
	}

	public void setEstaDescargado(boolean estaDescargado) {
		this.estaDescargado = estaDescargado;
	}

}
