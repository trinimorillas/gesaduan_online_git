package es.mercadona.gesaduan.jpa.reas.deletereas.v1;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import es.mercadona.gesaduan.jpa.productos.v1.ProductosJPA;


@Entity
@Table(name = "D_CODIGO_REA")
public class ReasDeleteJPA implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "COD_V_REA")
	private String codigoRea;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FEC_D_CREACION", insertable=false)
	private Date fechaCreacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FEC_D_MODIFICACION", insertable=false)
	private Date fechaModificacion;
	
	@Column(name = "COD_V_APLICACION")
	private String codigoAplicacion;
	
	@Column(name = "COD_V_USUARIO_CREACION")
	private String usuarioCreacion;
	
	@Column(name = "COD_V_USUARIO_MODIFICACION")
	private String usuarioModificacion;

	@JoinTable(
			name = "S_TARIC_REA",
			joinColumns = @JoinColumn(name = "COD_V_REA", nullable = false),
			inverseJoinColumns = @JoinColumn(name = "COD_N_TARIC", nullable = false)
	)
	@ManyToOne
	private TaricsDeleteJPA tarics;
	
	@JoinTable(
			name = "S_REA_PRODUCTO",
			joinColumns = @JoinColumn(name = "COD_V_REA", nullable = false),
			inverseJoinColumns = @JoinColumn(name = "COD_N_PRODUCTO", nullable = false)
	)
	@OneToMany
	private List<ProductosJPA> productos;

	public String getCodigoRea() {
		return codigoRea;
	}

	public void setCodigoRea(String codigoRea) {
		this.codigoRea = codigoRea;
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

	public TaricsDeleteJPA getTarics() {
		return tarics;
	}

	public void setTarics(TaricsDeleteJPA tarics) {
		this.tarics = tarics;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoAplicacion == null) ? 0 : codigoAplicacion.hashCode());
		result = prime * result + ((codigoRea == null) ? 0 : codigoRea.hashCode());
		result = prime * result + ((fechaCreacion == null) ? 0 : fechaCreacion.hashCode());
		result = prime * result + ((fechaModificacion == null) ? 0 : fechaModificacion.hashCode());
		result = prime * result + ((productos == null) ? 0 : productos.hashCode());
		result = prime * result + ((tarics == null) ? 0 : tarics.hashCode());
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
		ReasDeleteJPA other = (ReasDeleteJPA) obj;
		if (codigoAplicacion == null) {
			if (other.codigoAplicacion != null)
				return false;
		} else if (!codigoAplicacion.equals(other.codigoAplicacion))
			return false;
		if (codigoRea == null) {
			if (other.codigoRea != null)
				return false;
		} else if (!codigoRea.equals(other.codigoRea))
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
		if (productos == null) {
			if (other.productos != null)
				return false;
		} else if (!productos.equals(other.productos))
			return false;
		if (tarics == null) {
			if (other.tarics != null)
				return false;
		} else if (!tarics.equals(other.tarics))
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
