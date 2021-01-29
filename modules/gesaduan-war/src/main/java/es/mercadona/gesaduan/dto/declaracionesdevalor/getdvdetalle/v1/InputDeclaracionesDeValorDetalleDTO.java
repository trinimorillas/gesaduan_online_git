package es.mercadona.gesaduan.dto.declaracionesdevalor.getdvdetalle.v1;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class InputDeclaracionesDeValorDetalleDTO extends AbstractDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer codigoDeclaracion;
	private String locale;
	private String codigoUsuario;
	private Integer anyo;
	private Integer version;
	
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
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public String getCodigoUsuario() {
		return codigoUsuario;
	}
	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}
	
	
	
	
	
	

}
