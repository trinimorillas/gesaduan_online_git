package es.mercadona.gesaduan.dto.proveedores.putproveedores.v1.restfull;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.proveedores.AbstractDTO;

public class PutProveedorContacto extends AbstractDTO implements Serializable{

	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Long codigoContacto;

    private Boolean envioSMS;
    private Boolean envioEmail;
    
    private Long codMecanismoContactoSMS;
	private Long codMecanismoContactoEmail;
	private Long codLocalizacionSMS;
	private Long codLocalizacionEmail;
    
    
    
	public Long getCodigoContacto() {
		return codigoContacto;
	}
	public void setCodigoContacto(Long codigoContacto) {
		this.codigoContacto = codigoContacto;
	}
	
	public Boolean getEnvioSMS() {
		return envioSMS;
	}
	public void setEnvioSMS(Boolean envioSMS) {
		this.envioSMS = envioSMS;
	}
	public Boolean getEnvioEmail() {
		return envioEmail;
	}
	public void setEnvioEmail(Boolean envioEmail) {
		this.envioEmail = envioEmail;
	}
	public Long getCodMecanismoContactoSMS() {
		return codMecanismoContactoSMS;
	}
	public void setCodMecanismoContactoSMS(Long codMecanismoContactoSMS) {
		this.codMecanismoContactoSMS = codMecanismoContactoSMS;
	}
	public Long getCodMecanismoContactoEmail() {
		return codMecanismoContactoEmail;
	}
	public void setCodMecanismoContactoEmail(Long codMecanismoContactoEmail) {
		this.codMecanismoContactoEmail = codMecanismoContactoEmail;
	}
	public Long getCodLocalizacionSMS() {
		return codLocalizacionSMS;
	}
	public void setCodLocalizacionSMS(Long codLocalizacionSMS) {
		this.codLocalizacionSMS = codLocalizacionSMS;
	}
	public Long getCodLocalizacionEmail() {
		return codLocalizacionEmail;
	}
	public void setCodLocalizacionEmail(Long codLocalizacionEmail) {
		this.codLocalizacionEmail = codLocalizacionEmail;
	}

    
    
}
