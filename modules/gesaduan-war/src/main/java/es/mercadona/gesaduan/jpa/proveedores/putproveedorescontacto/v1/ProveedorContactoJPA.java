package es.mercadona.gesaduan.jpa.proveedores.putproveedorescontacto.v1;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "S_CONTACTO_PROVEEDOR_R")
public class ProveedorContactoJPA {
	
	@Id
	@Column(name="COD_N_CONTACTO")
	private Long codContacto;
	
	@Column(name="COD_N_PROVEEDOR")
	private Long codProveedor;
		
	@Column(name="COD_N_MECANISMO_CONTACTO_SMS")
	private Long codMecanismoContactoSMS;
	
	@Column(name="COD_N_MECANISMO_CONTACTO_EMAIL")
	private Long codMecanismoContactoEmail;
	
	@Column(name="COD_N_LOCALIZACION_SMS")
	private Long codLocalizacionSMS;
	
	@Column(name="COD_N_LOCALIZACION_EMAIL")
	private Long codLocalizacionEmail;
			
	@Column(name="MCA_ENVIO_SMS")
	private String mcaSMS;
	
	@Column(name="MCA_ENVIO_EMAIL")
	private String mcaEmail;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FEC_D_CREACION")
	private Date fechaCreacion;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FEC_D_MODIFICACION")
	private Date fechaModificacion;
	
	@Column(name="COD_V_APLICACION")
	private String codApp;
	
	@Column(name="COD_V_USUARIO_CREACION")
	private String usuarioCreacion;
	
	@Column(name="COD_V_USUARIO_MODIFICACION")
	private String usuarioModificacion;

	public Long getCodContacto() {
		return codContacto;
	}

	public String getCodApp() {
		return codApp;
	}

	public void setCodApp(String codApp) {
		this.codApp = codApp;
	}

	public void setCodContacto(Long codContacto) {
		this.codContacto = codContacto;
	}

	public Long getCodProveedor() {
		return codProveedor;
	}

	public void setCodProveedor(Long codProveedor) {
		this.codProveedor = codProveedor;
	}

	
	public String getMcaSMS() {
		return mcaSMS;
	}

	public void setMcaSMS(String mcaSMS) {
		this.mcaSMS = mcaSMS;
	}

	public String getMcaEmail() {
		return mcaEmail;
	}

	public void setMcaEmail(String mcaEmail) {
		this.mcaEmail = mcaEmail;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public String getUsuarioModificacion() {
		return usuarioModificacion;
	}

	public void setUsuarioModificacion(String usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
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

	public ProveedorContactoJPA() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codApp == null) ? 0 : codApp.hashCode());
		result = prime * result + ((codContacto == null) ? 0 : codContacto.hashCode());
		result = prime * result + ((codLocalizacionEmail == null) ? 0 : codLocalizacionEmail.hashCode());
		result = prime * result + ((codLocalizacionSMS == null) ? 0 : codLocalizacionSMS.hashCode());
		result = prime * result + ((codMecanismoContactoEmail == null) ? 0 : codMecanismoContactoEmail.hashCode());
		result = prime * result + ((codMecanismoContactoSMS == null) ? 0 : codMecanismoContactoSMS.hashCode());
		result = prime * result + ((codProveedor == null) ? 0 : codProveedor.hashCode());
		result = prime * result + ((fechaCreacion == null) ? 0 : fechaCreacion.hashCode());
		result = prime * result + ((fechaModificacion == null) ? 0 : fechaModificacion.hashCode());
		result = prime * result + ((mcaEmail == null) ? 0 : mcaEmail.hashCode());
		result = prime * result + ((mcaSMS == null) ? 0 : mcaSMS.hashCode());
		result = prime * result + ((usuarioCreacion == null) ? 0 : usuarioCreacion.hashCode());
		result = prime * result + ((usuarioModificacion == null) ? 0 : usuarioModificacion.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProveedorContactoJPA other = (ProveedorContactoJPA) obj;
		if (codApp == null) {
			if (other.codApp != null)
				return false;
		} else if (!codApp.equals(other.codApp))
			return false;
		if (codContacto == null) {
			if (other.codContacto != null)
				return false;
		} else if (!codContacto.equals(other.codContacto))
			return false;
		if (codLocalizacionEmail == null) {
			if (other.codLocalizacionEmail != null)
				return false;
		} else if (!codLocalizacionEmail.equals(other.codLocalizacionEmail))
			return false;
		if (codLocalizacionSMS == null) {
			if (other.codLocalizacionSMS != null)
				return false;
		} else if (!codLocalizacionSMS.equals(other.codLocalizacionSMS))
			return false;
		if (codMecanismoContactoEmail == null) {
			if (other.codMecanismoContactoEmail != null)
				return false;
		} else if (!codMecanismoContactoEmail.equals(other.codMecanismoContactoEmail))
			return false;
		if (codMecanismoContactoSMS == null) {
			if (other.codMecanismoContactoSMS != null)
				return false;
		} else if (!codMecanismoContactoSMS.equals(other.codMecanismoContactoSMS))
			return false;
		if (codProveedor == null) {
			if (other.codProveedor != null)
				return false;
		} else if (!codProveedor.equals(other.codProveedor))
			return false;
		if (fechaCreacion == null) {
			if (other.fechaCreacion != null)
				return false;
		} else if (!fechaCreacion.equals(other.fechaCreacion))
			return false;
		if (fechaModificacion == null) {
			if (other.fechaModificacion != null)
				return false;
		} else if (!fechaModificacion.equals(other.fechaModificacion))
			return false;
		if (mcaEmail == null) {
			if (other.mcaEmail != null)
				return false;
		} else if (!mcaEmail.equals(other.mcaEmail))
			return false;
		if (mcaSMS == null) {
			if (other.mcaSMS != null)
				return false;
		} else if (!mcaSMS.equals(other.mcaSMS))
			return false;
		if (usuarioCreacion == null) {
			if (other.usuarioCreacion != null)
				return false;
		} else if (!usuarioCreacion.equals(other.usuarioCreacion))
			return false;
		if (usuarioModificacion == null) {
			if (other.usuarioModificacion != null)
				return false;
		} else if (!usuarioModificacion.equals(other.usuarioModificacion))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProveedorContactoJPA [codContacto=" + codContacto + ", codProveedor=" + codProveedor
				+ ", codMecanismoContactoSMS=" + codMecanismoContactoSMS + ", codMecanismoContactoEmail="
				+ codMecanismoContactoEmail + ", codLocalizacionSMS=" + codLocalizacionSMS + ", codLocalizacionEmail="
				+ codLocalizacionEmail + ", mcaSMS=" + mcaSMS + ", mcaEmail=" + mcaEmail + ", fechaCreacion="
				+ fechaCreacion + ", fechaModificacion=" + fechaModificacion + ", codApp=" + codApp
				+ ", usuarioCreacion=" + usuarioCreacion + ", usuarioModificacion=" + usuarioModificacion + "]";
	}
		

	
	
}
