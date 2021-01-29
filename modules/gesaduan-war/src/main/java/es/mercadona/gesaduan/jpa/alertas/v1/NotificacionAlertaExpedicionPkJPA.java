package es.mercadona.gesaduan.jpa.alertas.v1;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class NotificacionAlertaExpedicionPkJPA implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "COD_N_ALERTA")
    private Long codigoAlerta;
	
	@Column(name = "COD_V_ELEMENTO")
    private String codigoElemento;
	
	@Column(name = "COD_V_EXPEDICION")
	private String codigoExpedicion;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "FEC_D_ALBARAN")
	private Date fechaAlbaran;

	public Long getCodigoAlerta() {
		return codigoAlerta;
	}

	public void setCodigoAlerta(Long codigoAlerta) {
		this.codigoAlerta = codigoAlerta;
	}

	public String getCodigoElemento() {
		return codigoElemento;
	}

	public void setCodigoElemento(String codigoElemento) {
		this.codigoElemento = codigoElemento;
	}

	public String getCodigoExpedicion() {
		return codigoExpedicion;
	}

	public void setCodigoExpedicion(String codigoExpedicion) {
		this.codigoExpedicion = codigoExpedicion;
	}

	public Date getFechaAlbaran() {
		return fechaAlbaran;
	}

	public void setFechaAlbaran(Date fechaAlbaran) {
		this.fechaAlbaran = fechaAlbaran;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoAlerta == null) ? 0 : codigoAlerta.hashCode());
		result = prime * result + ((codigoElemento == null) ? 0 : codigoElemento.hashCode());
		result = prime * result + ((codigoExpedicion == null) ? 0 : codigoExpedicion.hashCode());
		result = prime * result + ((fechaAlbaran == null) ? 0 : fechaAlbaran.hashCode());
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
		NotificacionAlertaExpedicionPkJPA other = (NotificacionAlertaExpedicionPkJPA) obj;
		if (codigoAlerta == null) {
			if (other.codigoAlerta != null)
				return false;
		} else if (!codigoAlerta.equals(other.codigoAlerta))
			return false;
		if (codigoElemento == null) {
			if (other.codigoElemento != null)
				return false;
		} else if (!codigoElemento.equals(other.codigoElemento))
			return false;
		if (codigoExpedicion == null) {
			if (other.codigoExpedicion != null)
				return false;
		} else if (!codigoExpedicion.equals(other.codigoExpedicion))
			return false;
		if (fechaAlbaran == null) {
			if (other.fechaAlbaran != null)
				return false;
		} else if (!fechaAlbaran.equals(other.fechaAlbaran))
			return false;
		return true;
	}
	
	

}
