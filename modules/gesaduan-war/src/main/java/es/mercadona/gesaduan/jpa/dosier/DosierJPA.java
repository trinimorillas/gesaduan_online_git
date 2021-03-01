package es.mercadona.gesaduan.jpa.dosier;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "D_DOSIER")
public class DosierJPA implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="NUM_DOSIER")
	private Long numDosier;
		
	@Column(name = "NUM_ANYO")
	private Integer anyDosier;
	
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



	/**
	 * @return the numDosier
	 */
	public Long getNumDosier() {
		return numDosier;
	}

	/**
	 * @param numDosier the numDosier to set
	 */
	public void setNumDosier(Long numDosier) {
		this.numDosier = numDosier;
	}

	/**
	 * @return the anyDosier
	 */
	public Integer getAnyDosier() {
		return anyDosier;
	}

	/**
	 * @param anyDosier the anyDosier to set
	 */
	public void setAnyDosier(Integer anyDosier) {
		this.anyDosier = anyDosier;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numDosier == null) ? 0 : numDosier.hashCode());
		result = prime * result + ((anyDosier == null) ? 0 : anyDosier.hashCode());
		result = prime * result + ((codigoEmbarque == null) ? 0 : codigoEmbarque.hashCode());
		result = prime * result + ((agenciaExportacion == null) ? 0 : agenciaExportacion.hashCode());
		result = prime * result + ((agenciaImportacion == null) ? 0 : agenciaImportacion.hashCode());
		result = prime * result + ((codigoEstado == null) ? 0 : codigoEstado.hashCode());
		result = prime * result + ((fechaDescarga == null) ? 0 : fechaDescarga.hashCode());
		result = prime * result + ((fechaCreacion == null) ? 0 : fechaCreacion.hashCode());
		result = prime * result + ((fechaModificacion == null) ? 0 : fechaModificacion.hashCode());
		result = prime * result + ((codigoAplicacion == null) ? 0 : codigoAplicacion.hashCode());
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
		DosierJPA other = (DosierJPA) obj;
		if (numDosier == null) {
			if (other.numDosier != null)
				return false;
		} else if (!numDosier.equals(other.numDosier))
			return false;
		if (anyDosier == null) {
			if (other.anyDosier != null)
				return false;
		} else if (!anyDosier.equals(other.anyDosier))
			return false;
		if (codigoEmbarque == null) {
			if (other.codigoEmbarque != null)
				return false;
		} else if (!codigoEmbarque.equals(other.codigoEmbarque))
			return false;
		if (agenciaExportacion == null) {
			if (other.agenciaExportacion != null)
				return false;
		} else if (!agenciaExportacion.equals(other.agenciaExportacion))
			return false;
		if (agenciaImportacion == null) {
			if (other.agenciaImportacion != null)
				return false;
		} else if (!agenciaImportacion.equals(other.agenciaImportacion))
			return false;
		if (codigoEstado == null) {
			if (other.codigoEstado != null)
				return false;
		} else if (!codigoEstado.equals(other.codigoEstado))
			return false;
		if (fechaDescarga == null) {
			if (other.fechaDescarga != null)
				return false;
		} else if (!fechaDescarga.equals(other.fechaDescarga))
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
		if (codigoAplicacion == null) {
			if (other.codigoAplicacion != null)
				return false;
		} else if (!codigoAplicacion.equals(other.codigoAplicacion))
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
		if (usuarioModificacion == null) {
			if (other.usuarioModificacion != null)
				return false;
		} else if (!usuarioModificacion.equals(other.usuarioModificacion))
			return false;
		return true;
	}
}
