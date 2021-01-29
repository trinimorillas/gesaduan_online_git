package es.mercadona.gesaduan.jpa.planembarques.v1;

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
@Table(name = "D_PLAN_EMBARQUE")
public class PlanEmbarquesJPA implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="COD_N_EMBARQUE")
	@SequenceGenerator(name="PLAN_EMBARQUE",sequenceName="PLAN_EMBARQUE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PLAN_EMBARQUE")
	private Long codigoEmbarque;
	
	@Column(name = "FEC_DT_EMBARQUE")
	private Date fechaEmbarque;
	
	@Column(name = "COD_N_BLOQUE_ORIGEN")
	private Integer bloqueOrigen;

	@Column(name = "COD_N_PUERTO_EMBARQUE")
	private Integer puertoOrigen;
	
	@Column(name = "COD_N_PUERTO_DESEMBARQUE")
	private Integer puertoDestino;
	
	@Column(name = "COD_N_NAVIERA")
	private Long naviera;
	
	@Column(name = "COD_N_ESTADO")
	private Integer estado;
	
	@Column(name = "COD_V_USUARIO_VALIDACION")
	private String usuarioValidacion;
	
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

	public Long getCodigoEmbarque() {
		return codigoEmbarque;
	}

	public void setCodigoEmbarque(Long codigoPlanEmbarque) {
		this.codigoEmbarque = codigoPlanEmbarque;
	}

	public Date getFechaEmbarque() {
		return fechaEmbarque;
	}

	public void setFechaEmbarque(Date fechaEmbarque) {
		this.fechaEmbarque = fechaEmbarque;
	}

	public Integer getBloqueOrigen() {
		return bloqueOrigen;
	}

	public void setBloqueOrigen(Integer bloqueOrigen) {
		this.bloqueOrigen = bloqueOrigen;
	}

	public Integer getPuertoOrigen() {
		return puertoOrigen;
	}

	public void setPuertoOrigen(Integer puertoOrigen) {
		this.puertoOrigen = puertoOrigen;
	}

	public Integer getPuertoDestino() {
		return puertoDestino;
	}

	public void setPuertoDestino(Integer puertoDestino) {
		this.puertoDestino = puertoDestino;
	}

	public Long getNaviera() {
		return naviera;
	}

	public void setNaviera(Long naviera) {
		this.naviera = naviera;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
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

	public String getUsuarioValidacion() {
		return usuarioValidacion;
	}

	public void setUsuarioValidacion(String usuarioValidacion) {
		this.usuarioValidacion = usuarioValidacion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bloqueOrigen == null) ? 0 : bloqueOrigen.hashCode());
		result = prime * result + ((codigoAplicacion == null) ? 0 : codigoAplicacion.hashCode());
		result = prime * result + ((codigoEmbarque == null) ? 0 : codigoEmbarque.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((fechaCreacion == null) ? 0 : fechaCreacion.hashCode());
		result = prime * result + ((fechaEmbarque == null) ? 0 : fechaEmbarque.hashCode());
		result = prime * result + ((fechaModificacion == null) ? 0 : fechaModificacion.hashCode());
		result = prime * result + ((naviera == null) ? 0 : naviera.hashCode());
		result = prime * result + ((puertoDestino == null) ? 0 : puertoDestino.hashCode());
		result = prime * result + ((puertoOrigen == null) ? 0 : puertoOrigen.hashCode());
		result = prime * result + ((usuarioCreacion == null) ? 0 : usuarioCreacion.hashCode());
		result = prime * result + ((usuarioModificacion == null) ? 0 : usuarioModificacion.hashCode());
		result = prime * result + ((usuarioValidacion == null) ? 0 : usuarioValidacion.hashCode());
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
		PlanEmbarquesJPA other = (PlanEmbarquesJPA) obj;
		if (bloqueOrigen == null) {
			if (other.bloqueOrigen != null)
				return false;
		} else if (!bloqueOrigen.equals(other.bloqueOrigen))
			return false;
		if (codigoAplicacion == null) {
			if (other.codigoAplicacion != null)
				return false;
		} else if (!codigoAplicacion.equals(other.codigoAplicacion))
			return false;
		if (codigoEmbarque == null) {
			if (other.codigoEmbarque != null)
				return false;
		} else if (!codigoEmbarque.equals(other.codigoEmbarque))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (fechaCreacion == null) {
			if (other.fechaCreacion != null)
				return false;
		} else if (!fechaCreacion.equals(other.fechaCreacion))
			return false;
		if (fechaEmbarque == null) {
			if (other.fechaEmbarque != null)
				return false;
		} else if (!fechaEmbarque.equals(other.fechaEmbarque))
			return false;
		if (fechaModificacion == null) {
			if (other.fechaModificacion != null)
				return false;
		} else if (!fechaModificacion.equals(other.fechaModificacion))
			return false;
		if (naviera == null) {
			if (other.naviera != null)
				return false;
		} else if (!naviera.equals(other.naviera))
			return false;
		if (puertoDestino == null) {
			if (other.puertoDestino != null)
				return false;
		} else if (!puertoDestino.equals(other.puertoDestino))
			return false;
		if (puertoOrigen == null) {
			if (other.puertoOrigen != null)
				return false;
		} else if (!puertoOrigen.equals(other.puertoOrigen))
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
		if (usuarioValidacion == null) {
			if (other.usuarioValidacion != null)
				return false;
		} else if (!usuarioValidacion.equals(other.usuarioValidacion))
			return false;
		return true;
	}
}
