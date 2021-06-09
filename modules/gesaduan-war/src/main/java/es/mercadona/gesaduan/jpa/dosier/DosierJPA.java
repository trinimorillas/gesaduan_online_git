package es.mercadona.gesaduan.jpa.dosier;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "D_DOSIER")
public class DosierJPA implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private DosierPkJPA id;
	
	@Column(name = "COD_N_EMBARQUE")
	private Long codigoEmbarque;

	@Column(name = "COD_V_AGENCIA_EXPORTACION")
	private String agenciaExportacion;
	
	@Column(name = "COD_V_AGENCIA_IMPORTACION")
	private String agenciaImportacion;
	
	@Column(name = "COD_N_ESTADO")
	private Integer codigoEstado;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "FEC_DT_DESCARGA", insertable = false)
	private Date fechaDescarga;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "FEC_DT_CREACION", insertable = false)
	private Date fechaCreacion;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "FEC_DT_MODIFICACION", insertable = false)
	private Date fechaModificacion;
	
	@Column(name = "COD_V_APLICACION")
	private String codigoAplicacion;
	
	@Column(name = "COD_V_USUARIO_CREACION")
	private String usuarioCreacion;
	
	@Column(name = "COD_V_USUARIO_MODIFICACION")
	private String usuarioModificacion;	

	@Column(name = "MCA_ERROR")
	private String mcaError;	

	/**
	 * @return the id
	 */
	public DosierPkJPA getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(DosierPkJPA id) {
		this.id = id;
	}

	/**
	 * @return the codigoEmbarque
	 */
	public Long getCodigoEmbarque() {
		return codigoEmbarque;
	}

	/**
	 * @param codigoEmbarque the codigoEmbarque to set
	 */
	public void setCodigoEmbarque(Long codigoEmbarque) {
		this.codigoEmbarque = codigoEmbarque;
	}

	/**
	 * @return the agenciaExportacion
	 */
	public String getAgenciaExportacion() {
		return agenciaExportacion;
	}

	/**
	 * @param agenciaExportacion the agenciaExportacion to set
	 */
	public void setAgenciaExportacion(String agenciaExportacion) {
		this.agenciaExportacion = agenciaExportacion;
	}

	/**
	 * @return the agenciaImportacion
	 */
	public String getAgenciaImportacion() {
		return agenciaImportacion;
	}

	/**
	 * @param agenciaImportacion the agenciaImportacion to set
	 */
	public void setAgenciaImportacion(String agenciaImportacion) {
		this.agenciaImportacion = agenciaImportacion;
	}

	/**
	 * @return the codigoEstado
	 */
	public Integer getCodigoEstado() {
		return codigoEstado;
	}

	/**
	 * @param codigoEstado the codigoEstado to set
	 */
	public void setCodigoEstado(Integer codigoEstado) {
		this.codigoEstado = codigoEstado;
	}

	/**
	 * @return the fechaDescarga
	 */
	public Date getFechaDescarga() {
		return fechaDescarga;
	}

	/**
	 * @param fechaDescarga the fechaDescarga to set
	 */
	public void setFechaDescarga(Date fechaDescarga) {
		this.fechaDescarga = fechaDescarga;
	}

	/**
	 * @return the fechaCreacion
	 */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	/**
	 * @param fechaCreacion the fechaCreacion to set
	 */
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	/**
	 * @return the fechaModificacion
	 */
	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	/**
	 * @param fechaModificacion the fechaModificacion to set
	 */
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	/**
	 * @return the codigoAplicacion
	 */
	public String getCodigoAplicacion() {
		return codigoAplicacion;
	}

	/**
	 * @param codigoAplicacion the codigoAplicacion to set
	 */
	public void setCodigoAplicacion(String codigoAplicacion) {
		this.codigoAplicacion = codigoAplicacion;
	}

	/**
	 * @return the usuarioCreacion
	 */
	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}

	/**
	 * @param usuarioCreacion the usuarioCreacion to set
	 */
	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	/**
	 * @return the usuarioModificacion
	 */
	public String getUsuarioModificacion() {
		return usuarioModificacion;
	}

	/**
	 * @param usuarioModificacion the usuarioModificacion to set
	 */
	public void setUsuarioModificacion(String usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}

	/**
	 * @return the mcaError
	 */
	public String getMcaError() {
		return mcaError;
	}

	/**
	 * @param mcaError the mcaError to set
	 */
	public void setMcaError(String mcaError) {
		this.mcaError = mcaError;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((agenciaExportacion == null) ? 0 : agenciaExportacion.hashCode());
		result = prime * result + ((agenciaImportacion == null) ? 0 : agenciaImportacion.hashCode());
		result = prime * result + ((codigoAplicacion == null) ? 0 : codigoAplicacion.hashCode());
		result = prime * result + ((codigoEmbarque == null) ? 0 : codigoEmbarque.hashCode());
		result = prime * result + ((codigoEstado == null) ? 0 : codigoEstado.hashCode());
		result = prime * result + ((fechaCreacion == null) ? 0 : fechaCreacion.hashCode());
		result = prime * result + ((fechaDescarga == null) ? 0 : fechaDescarga.hashCode());
		result = prime * result + ((fechaModificacion == null) ? 0 : fechaModificacion.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((mcaError == null) ? 0 : mcaError.hashCode());
		result = prime * result + ((usuarioCreacion == null) ? 0 : usuarioCreacion.hashCode());
		result = prime * result + ((usuarioModificacion == null) ? 0 : usuarioModificacion.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		DosierJPA other = (DosierJPA) obj;
		if (agenciaExportacion == null) {
			if (other.agenciaExportacion != null) {
				return false;
			}
		} else {
			if (!agenciaExportacion.equals(other.agenciaExportacion)) {
				return false;
			}
		}
		if (agenciaImportacion == null) {
			if (other.agenciaImportacion != null) {
				return false;
			}
		} else {
			if (!agenciaImportacion.equals(other.agenciaImportacion)) {
				return false;
			}
		}
		if (codigoAplicacion == null) {
			if (other.codigoAplicacion != null) {
				return false;
			}
		} else {
			if (!codigoAplicacion.equals(other.codigoAplicacion)) {
				return false;
			}
		}
		if (codigoEmbarque == null) {
			if (other.codigoEmbarque != null) {
				return false;
			}
		} else {
			if (!codigoEmbarque.equals(other.codigoEmbarque)) {
				return false;
			}
		}
		if (codigoEstado == null) {
			if (other.codigoEstado != null) {
				return false;
			}
		} else {
			if (!codigoEstado.equals(other.codigoEstado)) {
				return false;
			}
		}
		if (fechaCreacion == null) {
			if (other.fechaCreacion != null) {
				return false;
			}
		} else {
			if (!fechaCreacion.equals(other.fechaCreacion)) {
				return false;
			}
		}
		if (fechaDescarga == null) {
			if (other.fechaDescarga != null) {
				return false;
			}
		} else {
			if (!fechaDescarga.equals(other.fechaDescarga)) {
				return false;
			}
		}
		if (fechaModificacion == null) {
			if (other.fechaModificacion != null) {
				return false;
			}
		} else {
			if (!fechaModificacion.equals(other.fechaModificacion)) {
				return false;
			}
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else {
			if (!id.equals(other.id)) {
				return false;
			}
		}
		if (mcaError == null) {
			if (other.mcaError != null) {
				return false;
			}
		} else {
			if (!mcaError.equals(other.mcaError)) {
				return false;
			}
		}
		if (usuarioCreacion == null) {
			if (other.usuarioCreacion != null) {
				return false;
			}
		} else {
			if (!usuarioCreacion.equals(other.usuarioCreacion)) {
				return false;
			}
		}
		if (usuarioModificacion == null) {
			if (other.usuarioModificacion != null) {
				return false;
			}
		} else {
			if (!usuarioModificacion.equals(other.usuarioModificacion)) {
				return false;
			}
		}
		return true;
	}
}
