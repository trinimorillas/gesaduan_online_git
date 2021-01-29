package es.mercadona.gesaduan.jpa.tarics.v1;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class TaricsReaPkJPA implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "COD_N_TARIC")
	private Long codigoTaric;
	
	@Column(name = "COD_V_REA")
	private String codigoRea;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "FEC_D_INICIO", nullable = false)
	private Date fechaInicio;

	public Long getCodigoTaric() {
		return codigoTaric;
	}

	public void setCodigoTaric(Long codigoTaric) {
		this.codigoTaric = codigoTaric;
	}

	public String getCodigoRea() {
		return codigoRea;
	}

	public void setCodigoRea(String codigoRea) {
		this.codigoRea = codigoRea;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoRea == null) ? 0 : codigoRea.hashCode());
		result = prime * result + ((codigoTaric == null) ? 0 : codigoTaric.hashCode());
		result = prime * result + ((fechaInicio == null) ? 0 : fechaInicio.hashCode());
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
		TaricsReaPkJPA other = (TaricsReaPkJPA) obj;
		if (codigoRea == null) {
			if (other.codigoRea != null)
				return false;
		} else if (!codigoRea.equals(other.codigoRea))
			return false;
		if (codigoTaric == null) {
			if (other.codigoTaric != null)
				return false;
		} else if (!codigoTaric.equals(other.codigoTaric))
			return false;
		if (fechaInicio == null) {
			if (other.fechaInicio != null)
				return false;
		} else if (!fechaInicio.equals(other.fechaInicio))
			return false;
		return true;
	}

	
	
	

}
