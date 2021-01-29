package es.mercadona.gesaduan.jpa.tarics.v1;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "S_TARIC_REA")
public class TaricsReaJPA implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private TaricsReaPkJPA id;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "FEC_D_FIN")
	private Date fechaFin;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "FEC_D_CREACION")
	private Date fechaCreacionTaricRea;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "FEC_D_MODIFICACION")
	private Date fechaModificacionTaricRea;
	
	@Column(name = "COD_V_APLICACION")
	private String codigoAplicacionTaricRea;
	
	@Column(name = "COD_V_USUARIO_CREACION")
	private String codigoCreacionTaricRea;
	
	@Column(name = "COD_V_USUARIO_MODIFICACION")
	private String codigoModificacionTaricRea;
	
	@ManyToOne
	@PrimaryKeyJoinColumn(name = "COD_N_TARIC", referencedColumnName = "COD_N_TARIC")
	private TaricsJPA tarics;

	public TaricsReaPkJPA getId() {
		return id;
	}

	public void setId(TaricsReaPkJPA id) {
		this.id = id;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Date getFechaCreacionTaricRea() {
		return fechaCreacionTaricRea;
	}

	public void setFechaCreacionTaricRea(Date fechaCreacionTaricRea) {
		this.fechaCreacionTaricRea = fechaCreacionTaricRea;
	}

	public Date getFechaModificacionTaricRea() {
		return fechaModificacionTaricRea;
	}

	public void setFechaModificacionTaricRea(Date fechaModificacionTaricRea) {
		this.fechaModificacionTaricRea = fechaModificacionTaricRea;
	}

	public String getCodigoAplicacionTaricRea() {
		return codigoAplicacionTaricRea;
	}

	public void setCodigoAplicacionTaricRea(String codigoAplicacionTaricRea) {
		this.codigoAplicacionTaricRea = codigoAplicacionTaricRea;
	}

	public String getCodigoCreacionTaricRea() {
		return codigoCreacionTaricRea;
	}

	public void setCodigoCreacionTaricRea(String codigoCreacionTaricRea) {
		this.codigoCreacionTaricRea = codigoCreacionTaricRea;
	}

	public String getCodigoModificacionTaricRea() {
		return codigoModificacionTaricRea;
	}

	public void setCodigoModificacionTaricRea(String codigoModificacionTaricRea) {
		this.codigoModificacionTaricRea = codigoModificacionTaricRea;
	}

	public TaricsJPA getTarics() {
		return tarics;
	}

	public void setTarics(TaricsJPA tarics) {
		this.tarics = tarics;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoAplicacionTaricRea == null) ? 0 : codigoAplicacionTaricRea.hashCode());
		result = prime * result + ((codigoCreacionTaricRea == null) ? 0 : codigoCreacionTaricRea.hashCode());
		result = prime * result + ((codigoModificacionTaricRea == null) ? 0 : codigoModificacionTaricRea.hashCode());
		result = prime * result + ((fechaCreacionTaricRea == null) ? 0 : fechaCreacionTaricRea.hashCode());
		result = prime * result + ((fechaFin == null) ? 0 : fechaFin.hashCode());
		result = prime * result + ((fechaModificacionTaricRea == null) ? 0 : fechaModificacionTaricRea.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((tarics == null) ? 0 : tarics.hashCode());
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
		TaricsReaJPA other = (TaricsReaJPA) obj;
		if (codigoAplicacionTaricRea == null) {
			if (other.codigoAplicacionTaricRea != null)
				return false;
		} else if (!codigoAplicacionTaricRea.equals(other.codigoAplicacionTaricRea))
			return false;
		if (codigoCreacionTaricRea == null) {
			if (other.codigoCreacionTaricRea != null)
				return false;
		} else if (!codigoCreacionTaricRea.equals(other.codigoCreacionTaricRea))
			return false;
		if (codigoModificacionTaricRea == null) {
			if (other.codigoModificacionTaricRea != null)
				return false;
		} else if (!codigoModificacionTaricRea.equals(other.codigoModificacionTaricRea))
			return false;
		if (fechaCreacionTaricRea == null) {
			if (other.fechaCreacionTaricRea != null)
				return false;
		} else if (!fechaCreacionTaricRea.equals(other.fechaCreacionTaricRea))
			return false;
		if (fechaFin == null) {
			if (other.fechaFin != null)
				return false;
		} else if (!fechaFin.equals(other.fechaFin))
			return false;
		if (fechaModificacionTaricRea == null) {
			if (other.fechaModificacionTaricRea != null)
				return false;
		} else if (!fechaModificacionTaricRea.equals(other.fechaModificacionTaricRea))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (tarics == null) {
			if (other.tarics != null)
				return false;
		} else if (!tarics.equals(other.tarics))
			return false;
		return true;
	}



}
