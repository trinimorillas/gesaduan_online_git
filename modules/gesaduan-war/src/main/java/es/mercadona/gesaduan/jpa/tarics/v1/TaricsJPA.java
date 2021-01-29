package es.mercadona.gesaduan.jpa.tarics.v1;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import es.mercadona.gesaduan.jpa.productos.v1.ProductosJPA;
import es.mercadona.gesaduan.jpa.reas.deletereas.v1.ReasDeleteJPA;

@Entity
@Table(name = "D_CODIGO_TARIC")
public class TaricsJPA implements Serializable{
	
	/*
	 * D_CODIGO_TARIC
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	@Id
	@Column(name = "COD_N_TARIC")
	private Long codigoTaric;
	
	@Column(name = "COD_V_APLICACION")
	private String codigoAplicacion;

	@Column(name = "COD_V_USUARIO_CREACION")
	private String usuarioCreacion;
	
	@Column(name = "COD_V_USUARIO_MODIFICACION")
	private String usuarioModificacion;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FEC_D_CREACION", insertable=false)
	private Date fechaCreacion;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FEC_D_MODIFICACION", insertable=false)
	private Date fechaModificacion;
	
	@Column(name = "TXT_CAPITULO")
	private String capitulo;
	
	@Column(name = "TXT_DESCRIPCION_APERTURA")
	private String descApertura;
	
	@Column(name = "TXT_PARTIDA")
	private String partida;
	
	@Column(name = "TXT_SUBDIVISION")
	private String subdivision;
	
	@Column(name = "TXT_SUBPARTIDA")
	private String subpartida;
	
    @JoinTable(
    		name = "S_TARIC_REA",
    		joinColumns = @JoinColumn(name = "COD_N_TARIC", nullable = false),
    		inverseJoinColumns = @JoinColumn(name = "COD_V_REA", nullable = false)
	)
    @OneToMany
    private List<ReasDeleteJPA> taricsReas;
    
    @JoinTable(
    		name = "S_TARIC_PRODUCTO",
    		joinColumns = @JoinColumn(name = "COD_N_TARIC", nullable = false),
    		inverseJoinColumns = @JoinColumn(name = "COD_N_PRODUCTO", nullable = false)
	)
    @OneToMany
    private List<ProductosJPA> taricsProductos;

	public Long getCodigoTaric() {
		return codigoTaric;
	}

	public void setCodigoTaric(Long codigoTaric) {
		this.codigoTaric = codigoTaric;
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

	public String getCapitulo() {
		return capitulo;
	}

	public void setCapitulo(String capitulo) {
		this.capitulo = capitulo;
	}

	public String getDescApertura() {
		return descApertura;
	}

	public void setDescApertura(String descApertura) {
		this.descApertura = descApertura;
	}

	public String getPartida() {
		return partida;
	}

	public void setPartida(String partida) {
		this.partida = partida;
	}

	public String getSubdivision() {
		return subdivision;
	}

	public void setSubdivision(String subdivision) {
		this.subdivision = subdivision;
	}

	public String getSubpartida() {
		return subpartida;
	}

	public void setSubpartida(String subpartida) {
		this.subpartida = subpartida;
	}

	public List<ReasDeleteJPA> getTaricsReas() {
		return taricsReas;
	}

	public void setTaricsReas(List<ReasDeleteJPA> taricsReas) {
		this.taricsReas = taricsReas;
	}

	public List<ProductosJPA> getTaricsProductos() {
		return taricsProductos;
	}

	public void setTaricsProductos(List<ProductosJPA> taricsProductos) {
		this.taricsProductos = taricsProductos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((capitulo == null) ? 0 : capitulo.hashCode());
		result = prime * result + ((codigoAplicacion == null) ? 0 : codigoAplicacion.hashCode());
		result = prime * result + ((codigoTaric == null) ? 0 : codigoTaric.hashCode());
		result = prime * result + ((descApertura == null) ? 0 : descApertura.hashCode());
		result = prime * result + ((fechaCreacion == null) ? 0 : fechaCreacion.hashCode());
		result = prime * result + ((fechaModificacion == null) ? 0 : fechaModificacion.hashCode());
		result = prime * result + ((partida == null) ? 0 : partida.hashCode());
		result = prime * result + ((subdivision == null) ? 0 : subdivision.hashCode());
		result = prime * result + ((subpartida == null) ? 0 : subpartida.hashCode());
		result = prime * result + ((taricsProductos == null) ? 0 : taricsProductos.hashCode());
		result = prime * result + ((taricsReas == null) ? 0 : taricsReas.hashCode());
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
		TaricsJPA other = (TaricsJPA) obj;
		if (capitulo == null) {
			if (other.capitulo != null)
				return false;
		} else if (!capitulo.equals(other.capitulo))
			return false;
		if (codigoAplicacion == null) {
			if (other.codigoAplicacion != null)
				return false;
		} else if (!codigoAplicacion.equals(other.codigoAplicacion))
			return false;
		if (codigoTaric == null) {
			if (other.codigoTaric != null)
				return false;
		} else if (!codigoTaric.equals(other.codigoTaric))
			return false;
		if (descApertura == null) {
			if (other.descApertura != null)
				return false;
		} else if (!descApertura.equals(other.descApertura))
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
		if (partida == null) {
			if (other.partida != null)
				return false;
		} else if (!partida.equals(other.partida))
			return false;
		if (subdivision == null) {
			if (other.subdivision != null)
				return false;
		} else if (!subdivision.equals(other.subdivision))
			return false;
		if (subpartida == null) {
			if (other.subpartida != null)
				return false;
		} else if (!subpartida.equals(other.subpartida))
			return false;
		if (taricsProductos == null) {
			if (other.taricsProductos != null)
				return false;
		} else if (!taricsProductos.equals(other.taricsProductos))
			return false;
		if (taricsReas == null) {
			if (other.taricsReas != null)
				return false;
		} else if (!taricsReas.equals(other.taricsReas))
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
    
    
    
    
    
    
    
    
    
    
    
    
    
    