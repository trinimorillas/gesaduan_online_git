package es.mercadona.gesaduan.jpa.alertas.v1;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "S_NOTIF_ALERTA_EXPED_DV")
public class NotificacionAlertaExpedicionJPA implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private NotificacionAlertaExpedicionPkJPA id;
	
	@Column(name = "COD_N_DECLARACION_VALOR")
	private Long codigoDecalracion;
	
	@Column(name = "NUM_ANYO")
	private Integer anyo;
	
	@Column(name = "COD_N_VERSION")
	private Integer version;
	
	@Column(name = "MCA_CORREO_ENVIADO")
    private String correoEnviado;
	
	@Column(name = "MCA_SMS_ENVIADO")
    private String smsEnviado;
	
	@Column(name = "MCA_RESUELTA")
    private String marcaResuelto;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FEC_DT_ENVIO")
    private Date fechaEnvio;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "FEC_D_CREACION")
    private Date fechaCreacion;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "FEC_D_MODIFICACION")
    private Date fechaModificacion;
	
	@Column(name = "COD_V_APLICACION")
    private String codigoAplicacion;
	
	@Column(name = "COD_V_USUARIO_CREACION")
    private String usuarioCreacion;
	
	@Column(name = "COD_V_USUARIO_MODIFICACION")
    private String usuarioModificacion;

	public NotificacionAlertaExpedicionPkJPA getId() {
		return id;
	}

	public void setId(NotificacionAlertaExpedicionPkJPA id) {
		this.id = id;
	}

	public Long getCodigoDecalracion() {
		return codigoDecalracion;
	}

	public void setCodigoDecalracion(Long codigoDecalracion) {
		this.codigoDecalracion = codigoDecalracion;
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

	public String getCorreoEnviado() {
		return correoEnviado;
	}

	public void setCorreoEnviado(String correoEnviado) {
		this.correoEnviado = correoEnviado;
	}

	public String getSmsEnviado() {
		return smsEnviado;
	}

	public void setSmsEnviado(String smsEnviado) {
		this.smsEnviado = smsEnviado;
	}

	public String getMarcaResuelto() {
		return marcaResuelto;
	}

	public void setMarcaResuelto(String marcaResuelto) {
		this.marcaResuelto = marcaResuelto;
	}

	public Date getFechaEnvio() {
		return fechaEnvio;
	}

	public void setFechaEnvio(Date fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
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

	public String getCodigoAplicacion() {
		return codigoAplicacion;
	}

	public void setCodigoAplicacion(String codigoAplicacion) {
		this.codigoAplicacion = codigoAplicacion;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((anyo == null) ? 0 : anyo.hashCode());
		result = prime * result + ((codigoAplicacion == null) ? 0 : codigoAplicacion.hashCode());
		result = prime * result + ((codigoDecalracion == null) ? 0 : codigoDecalracion.hashCode());
		result = prime * result + ((correoEnviado == null) ? 0 : correoEnviado.hashCode());
		result = prime * result + ((fechaCreacion == null) ? 0 : fechaCreacion.hashCode());
		result = prime * result + ((fechaEnvio == null) ? 0 : fechaEnvio.hashCode());
		result = prime * result + ((fechaModificacion == null) ? 0 : fechaModificacion.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((marcaResuelto == null) ? 0 : marcaResuelto.hashCode());
		result = prime * result + ((smsEnviado == null) ? 0 : smsEnviado.hashCode());
		result = prime * result + ((usuarioCreacion == null) ? 0 : usuarioCreacion.hashCode());
		result = prime * result + ((usuarioModificacion == null) ? 0 : usuarioModificacion.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
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
		NotificacionAlertaExpedicionJPA other = (NotificacionAlertaExpedicionJPA) obj;
		if (anyo == null) {
			if (other.anyo != null)
				return false;
		} else if (!anyo.equals(other.anyo))
			return false;
		if (codigoAplicacion == null) {
			if (other.codigoAplicacion != null)
				return false;
		} else if (!codigoAplicacion.equals(other.codigoAplicacion))
			return false;
		if (codigoDecalracion == null) {
			if (other.codigoDecalracion != null)
				return false;
		} else if (!codigoDecalracion.equals(other.codigoDecalracion))
			return false;
		if (correoEnviado == null) {
			if (other.correoEnviado != null)
				return false;
		} else if (!correoEnviado.equals(other.correoEnviado))
			return false;
		if (fechaCreacion == null) {
			if (other.fechaCreacion != null)
				return false;
		} else if (!fechaCreacion.equals(other.fechaCreacion))
			return false;
		if (fechaEnvio == null) {
			if (other.fechaEnvio != null)
				return false;
		} else if (!fechaEnvio.equals(other.fechaEnvio))
			return false;
		if (fechaModificacion == null) {
			if (other.fechaModificacion != null)
				return false;
		} else if (!fechaModificacion.equals(other.fechaModificacion))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (marcaResuelto == null) {
			if (other.marcaResuelto != null)
				return false;
		} else if (!marcaResuelto.equals(other.marcaResuelto))
			return false;
		if (smsEnviado == null) {
			if (other.smsEnviado != null)
				return false;
		} else if (!smsEnviado.equals(other.smsEnviado))
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
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	}
	
	

}
