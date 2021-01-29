package es.mercadona.gesaduan.dto.reas.getreasdetalle.v1;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class InputReasDetalleDTO extends AbstractDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    private String codigoRea;
	private String locale;
	private String codigoUsuario;
	
	public String getCodigoRea() {
		return codigoRea;
	}
	public void setCodigoRea(String codigoRea) {
		this.codigoRea = codigoRea;
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
