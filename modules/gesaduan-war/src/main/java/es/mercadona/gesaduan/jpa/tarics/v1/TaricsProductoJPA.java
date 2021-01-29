package es.mercadona.gesaduan.jpa.tarics.v1;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "S_TARIC_PRODUCTO")
public class TaricsProductoJPA implements Serializable{

	/*
	 * S_TARIC_PRODUCTO
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    @EmbeddedId
    private TaricsProductosPkJPA id;
	
    @Temporal(TemporalType.DATE)
	@Column(name = "FEC_D_FIN")
    private Date fechaFin;
	
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

	@ManyToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn(name="COD_N_TARIC", referencedColumnName="COD_N_TARIC")
	private TaricsJPA tarics;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn(name="COD_N_PRODUCTO", referencedColumnName="COD_N_PRODUCTO")
	private ProductosReemplazarJPA productos;

	public TaricsProductosPkJPA getId() {
		return id;
	}

	public void setId(TaricsProductosPkJPA id) {
		this.id = id;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
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

	public TaricsJPA getTarics() {
		return tarics;
	}

	public void setTarics(TaricsJPA tarics) {
		this.tarics = tarics;
	}

	public ProductosReemplazarJPA getProductos() {
		return productos;
	}

	public void setProductos(ProductosReemplazarJPA productos) {
		this.productos = productos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoAplicacion == null) ? 0 : codigoAplicacion.hashCode());
		result = prime * result + ((fechaCreacion == null) ? 0 : fechaCreacion.hashCode());
		result = prime * result + ((fechaFin == null) ? 0 : fechaFin.hashCode());
		result = prime * result + ((fechaModificacion == null) ? 0 : fechaModificacion.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		TaricsProductoJPA other = (TaricsProductoJPA) obj;
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
		if (fechaFin == null) {
			if (other.fechaFin != null)
				return false;
		} else if (!fechaFin.equals(other.fechaFin))
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
