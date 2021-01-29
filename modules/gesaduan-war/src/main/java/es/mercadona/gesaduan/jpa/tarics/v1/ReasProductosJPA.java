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
@Table(name = "S_REA_PRODUCTO")
public class ReasProductosJPA implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    @EmbeddedId
    private ReasProductosPkJPA id;
	
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
	@PrimaryKeyJoinColumn(name="COD_V_REA", referencedColumnName="COD_V_REA")
	private ReasReemplazarJPA reas;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn(name="COD_N_PRODUCTO", referencedColumnName="COD_N_PRODUCTO")
	private ProductosReemplazarJPA productos;

	public ReasProductosPkJPA getId() {
		return id;
	}

	public void setId(ReasProductosPkJPA id) {
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

	public ReasReemplazarJPA getReas() {
		return reas;
	}

	public void setReas(ReasReemplazarJPA reas) {
		this.reas = reas;
	}

	public ProductosReemplazarJPA getProductos() {
		return productos;
	}

	public void setProductos(ProductosReemplazarJPA productos) {
		this.productos = productos;
	}
	
	

	
	
	
	

}
