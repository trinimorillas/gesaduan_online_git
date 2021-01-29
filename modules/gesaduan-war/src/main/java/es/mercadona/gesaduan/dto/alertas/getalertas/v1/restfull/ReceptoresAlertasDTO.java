package es.mercadona.gesaduan.dto.alertas.getalertas.v1.restfull;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class ReceptoresAlertasDTO extends AbstractDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private String nombre;
	private String apellidos;
	private String email;
	private String telefono;
	private Boolean envioSms;
	private Boolean envioEmail;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public Boolean getEnvioSms() {
		return envioSms;
	}
	public void setEnvioSms(Boolean envioSms) {
		this.envioSms = envioSms;
	}
	public Boolean getEnvioEmail() {
		return envioEmail;
	}
	public void setEnvioEmail(Boolean envioEmail) {
		this.envioEmail = envioEmail;
	}
	
	

}
