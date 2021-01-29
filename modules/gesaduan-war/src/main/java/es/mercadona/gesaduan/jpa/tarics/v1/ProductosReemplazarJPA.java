package es.mercadona.gesaduan.jpa.tarics.v1;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "D_PRODUCTO_R")
public class ProductosReemplazarJPA implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "COD_N_PRODUCTO")
	private Long codigoProducto;
	
	@Column(name = "TXT_DENOMINA_ETIQUETA")
	private String denominacionEtiqueta;
	
	@Column(name = "TXT_MARCA")
	private String marca;
	
	@Column(name = "NUM_FORMATO_VENTA")
	private Integer numFormatoVenta;
	
	@Column(name = "TXT_UNIDAD_MEDIDA")
	private String unidadMedida;

	@Column(name = "TXT_ESTADO_COMERCIAL")
	private String estadoComercial;
	
	@Column(name = "NUM_PESO_NETO")
	private Double pesoNeto;
	
	@Column(name = "NUM_PESO_NETO_ESCURRIDO")
	private Double pesoNetoEscurrido;
	
	@Column(name = "NUM_PESO_BRUTO")
	private Double pesoBruto;
	
	@Column(name = "FEC_D_CAMBIO_ESTADO")
	private Date fechaCambioEstado;
	
	@Column(name = "FEC_D_VALOR_ALTA")
	private Date fechaValorAlta;
	
	@Column(name = "TXT_DENOMINA_ALTERNATIVA")
	private String denominacionAlternativa;
	
	@Column(name = "TXT_FORMATO_ALTERNATIVO")
	private String formatoAlternativa;
	
	@Column(name = "FEC_D_CREACION")
	private Date fechaCreacion;
	
	@Column(name = "FEC_D_MODIFICACION")
	private Date fechaModificacion;
	
	@Column(name = "COD_V_APLICACION")
	private String codigoAplicacion;
	
	@Column(name = "COD_V_USUARIO_CREACION")
	private String usuarioCreacion;
	
	@Column(name = "COD_V_USUARIO_MODIFICACION")
	private String usuarioModificacion;
	
	@OneToMany(
	        mappedBy = "productos",
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )
    private List<ReasProductosJPA> productosPorRea;
	
	@OneToMany(
	        mappedBy = "productos",
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )
    private List<TaricsProductoJPA> productosPorTaric;

	public Long getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(Long codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public String getDenominacionEtiqueta() {
		return denominacionEtiqueta;
	}

	public void setDenominacionEtiqueta(String denominacionEtiqueta) {
		this.denominacionEtiqueta = denominacionEtiqueta;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Integer getNumFormatoVenta() {
		return numFormatoVenta;
	}

	public void setNumFormatoVenta(Integer numFormatoVenta) {
		this.numFormatoVenta = numFormatoVenta;
	}

	public String getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public String getEstadoComercial() {
		return estadoComercial;
	}

	public void setEstadoComercial(String estadoComercial) {
		this.estadoComercial = estadoComercial;
	}

	public Double getPesoNeto() {
		return pesoNeto;
	}

	public void setPesoNeto(Double pesoNeto) {
		this.pesoNeto = pesoNeto;
	}

	public Double getPesoNetoEscurrido() {
		return pesoNetoEscurrido;
	}

	public void setPesoNetoEscurrido(Double pesoNetoEscurrido) {
		this.pesoNetoEscurrido = pesoNetoEscurrido;
	}

	public Double getPesoBruto() {
		return pesoBruto;
	}

	public void setPesoBruto(Double pesoBruto) {
		this.pesoBruto = pesoBruto;
	}

	public Date getFechaCambioEstado() {
		return fechaCambioEstado;
	}

	public void setFechaCambioEstado(Date fechaCambioEstado) {
		this.fechaCambioEstado = fechaCambioEstado;
	}

	public Date getFechaValorAlta() {
		return fechaValorAlta;
	}

	public void setFechaValorAlta(Date fechaValorAlta) {
		this.fechaValorAlta = fechaValorAlta;
	}

	public String getDenominacionAlternativa() {
		return denominacionAlternativa;
	}

	public void setDenominacionAlternativa(String denominacionAlternativa) {
		this.denominacionAlternativa = denominacionAlternativa;
	}

	public String getFormatoAlternativa() {
		return formatoAlternativa;
	}

	public void setFormatoAlternativa(String formatoAlternativa) {
		this.formatoAlternativa = formatoAlternativa;
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

	public List<ReasProductosJPA> getProductosPorRea() {
		return productosPorRea;
	}

	public void setProductosPorRea(List<ReasProductosJPA> productosPorRea) {
		this.productosPorRea = productosPorRea;
	}

	public List<TaricsProductoJPA> getProductosPorTaric() {
		return productosPorTaric;
	}

	public void setProductosPorTaric(List<TaricsProductoJPA> productosPorTaric) {
		this.productosPorTaric = productosPorTaric;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoAplicacion == null) ? 0 : codigoAplicacion.hashCode());
		result = prime * result + ((codigoProducto == null) ? 0 : codigoProducto.hashCode());
		result = prime * result + ((denominacionAlternativa == null) ? 0 : denominacionAlternativa.hashCode());
		result = prime * result + ((denominacionEtiqueta == null) ? 0 : denominacionEtiqueta.hashCode());
		result = prime * result + ((estadoComercial == null) ? 0 : estadoComercial.hashCode());
		result = prime * result + ((fechaCambioEstado == null) ? 0 : fechaCambioEstado.hashCode());
		result = prime * result + ((fechaCreacion == null) ? 0 : fechaCreacion.hashCode());
		result = prime * result + ((fechaModificacion == null) ? 0 : fechaModificacion.hashCode());
		result = prime * result + ((fechaValorAlta == null) ? 0 : fechaValorAlta.hashCode());
		result = prime * result + ((formatoAlternativa == null) ? 0 : formatoAlternativa.hashCode());
		result = prime * result + ((marca == null) ? 0 : marca.hashCode());
		result = prime * result + ((numFormatoVenta == null) ? 0 : numFormatoVenta.hashCode());
		result = prime * result + ((pesoBruto == null) ? 0 : pesoBruto.hashCode());
		result = prime * result + ((pesoNeto == null) ? 0 : pesoNeto.hashCode());
		result = prime * result + ((pesoNetoEscurrido == null) ? 0 : pesoNetoEscurrido.hashCode());
		result = prime * result + ((productosPorRea == null) ? 0 : productosPorRea.hashCode());
		result = prime * result + ((productosPorTaric == null) ? 0 : productosPorTaric.hashCode());
		result = prime * result + ((unidadMedida == null) ? 0 : unidadMedida.hashCode());
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
		ProductosReemplazarJPA other = (ProductosReemplazarJPA) obj;
		if (codigoAplicacion == null) {
			if (other.codigoAplicacion != null)
				return false;
		} else if (!codigoAplicacion.equals(other.codigoAplicacion))
			return false;
		if (codigoProducto == null) {
			if (other.codigoProducto != null)
				return false;
		} else if (!codigoProducto.equals(other.codigoProducto))
			return false;
		if (denominacionAlternativa == null) {
			if (other.denominacionAlternativa != null)
				return false;
		} else if (!denominacionAlternativa.equals(other.denominacionAlternativa))
			return false;
		if (denominacionEtiqueta == null) {
			if (other.denominacionEtiqueta != null)
				return false;
		} else if (!denominacionEtiqueta.equals(other.denominacionEtiqueta))
			return false;
		if (estadoComercial == null) {
			if (other.estadoComercial != null)
				return false;
		} else if (!estadoComercial.equals(other.estadoComercial))
			return false;
		if (fechaCambioEstado == null) {
			if (other.fechaCambioEstado != null)
				return false;
		} else if (!fechaCambioEstado.equals(other.fechaCambioEstado))
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
		if (fechaValorAlta == null) {
			if (other.fechaValorAlta != null)
				return false;
		} else if (!fechaValorAlta.equals(other.fechaValorAlta))
			return false;
		if (formatoAlternativa == null) {
			if (other.formatoAlternativa != null)
				return false;
		} else if (!formatoAlternativa.equals(other.formatoAlternativa))
			return false;
		if (marca == null) {
			if (other.marca != null)
				return false;
		} else if (!marca.equals(other.marca))
			return false;
		if (numFormatoVenta == null) {
			if (other.numFormatoVenta != null)
				return false;
		} else if (!numFormatoVenta.equals(other.numFormatoVenta))
			return false;
		if (pesoBruto == null) {
			if (other.pesoBruto != null)
				return false;
		} else if (!pesoBruto.equals(other.pesoBruto))
			return false;
		if (pesoNeto == null) {
			if (other.pesoNeto != null)
				return false;
		} else if (!pesoNeto.equals(other.pesoNeto))
			return false;
		if (pesoNetoEscurrido == null) {
			if (other.pesoNetoEscurrido != null)
				return false;
		} else if (!pesoNetoEscurrido.equals(other.pesoNetoEscurrido))
			return false;
		if (productosPorRea == null) {
			if (other.productosPorRea != null)
				return false;
		} else if (!productosPorRea.equals(other.productosPorRea))
			return false;
		if (productosPorTaric == null) {
			if (other.productosPorTaric != null)
				return false;
		} else if (!productosPorTaric.equals(other.productosPorTaric))
			return false;
		if (unidadMedida == null) {
			if (other.unidadMedida != null)
				return false;
		} else if (!unidadMedida.equals(other.unidadMedida))
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
