package es.mercadona.gesaduan.dto.declaracionesdevalorapi.getdvdocumento.v1;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class InputDeclaracionesDeValorDocumentoDTO extends AbstractDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer codigoDeclaracion;
	private Integer anyoDeclaracion;
	private Integer versionDeclaracion;	
	private String locale;
	private String tipoDocumento;
	private String codigoUsuario;
	/**
	 * @return the codigoDeclaracion
	 */
	public Integer getCodigoDeclaracion() {
		return codigoDeclaracion;
	}
	/**
	 * @param codigoDeclaracion the codigoDeclaracion to set
	 */
	public void setCodigoDeclaracion(Integer codigoDeclaracion) {
		this.codigoDeclaracion = codigoDeclaracion;
	}
	/**
	 * @return the anyoDeclaracion
	 */
	public Integer getAnyoDeclaracion() {
		return anyoDeclaracion;
	}
	/**
	 * @param anyoDeclaracion the anyoDeclaracion to set
	 */
	public void setAnyoDeclaracion(Integer anyoDeclaracion) {
		this.anyoDeclaracion = anyoDeclaracion;
	}
	/**
	 * @return the versionDeclaracion
	 */
	public Integer getVersionDeclaracion() {
		return versionDeclaracion;
	}
	/**
	 * @param versionDeclaracion the versionDeclaracion to set
	 */
	public void setVersionDeclaracion(Integer versionDeclaracion) {
		this.versionDeclaracion = versionDeclaracion;
	}
	/**
	 * @return the locale
	 */
	public String getLocale() {
		return locale;
	}
	/**
	 * @param locale the locale to set
	 */
	public void setLocale(String locale) {
		this.locale = locale;
	}
	/**
	 * @return the tipoDocumento
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	/**
	 * @param tipoDocumento the tipoDocumento to set
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	/**
	 * @return the codigoUsuario
	 */
	public String getCodigoUsuario() {
		return codigoUsuario;
	}
	/**
	 * @param codigoUsuario the codigoUsuario to set
	 */
	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	
	
	
	

}
