package es.mercadona.gesaduan.jpa.equipotransporte.v1;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "S_EQUIPO_CARGA")
public class EquipoCargaJPA implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private EquipoCargaPkJPA id;
	
	@Column(name = "NUM_DIVISION")
	private Integer numeroDivision;

	@Column(name = "NUM_HUECO_OCUPADO")
	private Double numeroHuecosOcupados;
	
	@Column(name = "NUM_PESO_OCUPADO")
	private Double numeroPesoOcupado;
	
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
	
	public EquipoCargaPkJPA getId() {
		return id;
	}

	public void setId(EquipoCargaPkJPA id) {
		this.id = id;
	}

	public Integer getNumeroDivision() {
		return numeroDivision;
	}

	public void setNumeroDivision(Integer numeroDivision) {
		this.numeroDivision = numeroDivision;
	}

	public Double getNumeroHuecosOcupados() {
		return numeroHuecosOcupados;
	}

	public void setNumeroHuecosOcupados(Double numeroHuecosOcupados) {
		this.numeroHuecosOcupados = numeroHuecosOcupados;
	}

	public Double getNumeroPesoOcupado() {
		return numeroPesoOcupado;
	}

	public void setNumeroPesoOcupado(Double numeroPesoOcupado) {
		this.numeroPesoOcupado = numeroPesoOcupado;
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
		result = prime * result + ((codigoAplicacion == null) ? 0 : codigoAplicacion.hashCode());
		result = prime * result + ((fechaCreacion == null) ? 0 : fechaCreacion.hashCode());
		result = prime * result + ((fechaModificacion == null) ? 0 : fechaModificacion.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((numeroDivision == null) ? 0 : numeroDivision.hashCode());
		result = prime * result + ((numeroHuecosOcupados == null) ? 0 : numeroHuecosOcupados.hashCode());
		result = prime * result + ((numeroPesoOcupado == null) ? 0 : numeroPesoOcupado.hashCode());
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
		EquipoCargaJPA other = (EquipoCargaJPA) obj;
		if (codigoAplicacion == null) {
			if (other.codigoAplicacion != null)
				return false;
		} else if (!codigoAplicacion.equals(other.codigoAplicacion))
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (numeroDivision == null) {
			if (other.numeroDivision != null)
				return false;
		} else if (!numeroDivision.equals(other.numeroDivision))
			return false;
		if (numeroHuecosOcupados == null) {
			if (other.numeroHuecosOcupados != null)
				return false;
		} else if (!numeroHuecosOcupados.equals(other.numeroHuecosOcupados))
			return false;
		if (numeroPesoOcupado == null) {
			if (other.numeroPesoOcupado != null)
				return false;
		} else if (!numeroPesoOcupado.equals(other.numeroPesoOcupado))
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
}
