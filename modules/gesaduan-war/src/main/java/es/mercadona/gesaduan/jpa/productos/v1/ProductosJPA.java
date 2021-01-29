package es.mercadona.gesaduan.jpa.productos.v1;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "D_PRODUCTO_R")
public class ProductosJPA implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "COD_N_PRODUCTO")
	private Long codigoProducto;
	
	@Column(name = "TXT_DENOMINA_ETIQUETA")
	private String denominaEtiqueta;
	
	@Column(name = "TXT_MARCA")
	private String marca;

	@Column(name = "NUM_FORMATO_VENTA")
	private Integer formatoVenta;
	
	@Column(name = "TXT_UNIDAD_MEDIDA")
	private String unidadMedida;
	
	@Column(name = "TXT_ESTADO_COMERCIAL")
	private String estadoComercial;
	
	@Column(name = "NUM_PESO_NETO")
	private Integer pesoNeto;
	
	@Column(name = "NUM_PESO_NETO_ESCURRIDO")
	private Integer pesoNetoEscurrido;
	
	@Column(name = "NUM_PESO_BRUTO")
	private Integer pesoBruto;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "FEC_D_CAMBIO_ESTADO")
	private Date fechaCambioEstado;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "FEC_D_VALOR_ALTA")
	private Date fechaValorAlta;
	
	@Column(name = "TXT_DENOMINA_ALTERNATIVA")
	private String denominaAlternativa;
	
	@Column(name = "TXT_FORMATO_ALTERNATIVO")
	private String formatoAlternativo;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "FEC_D_CREACION", insertable = false)
	private Date fechaCreacion;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "FEC_D_MODIFICACION", insertable = false)
	private Date fechaModificacion;
	
	@Column(name = "COD_V_APLICACION")
	private String codigoAplicacion;
	
	@Column(name = "COD_V_USUARIO_CREACION")
	private String usuarioCreacion;
	
	@Column(name = "COD_V_USUARIO_MODIFICACION")
	private String usuarioModificacion;

	public Long getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(Long codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public String getDenominaEtiqueta() {
		return denominaEtiqueta;
	}

	public void setDenominaEtiqueta(String denominaEtiqueta) {
		this.denominaEtiqueta = denominaEtiqueta;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Integer getFormatoVenta() {
		return formatoVenta;
	}

	public void setFormatoVenta(Integer formatoVenta) {
		this.formatoVenta = formatoVenta;
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

	public Integer getPesoNeto() {
		return pesoNeto;
	}

	public void setPesoNeto(Integer pesoNeto) {
		this.pesoNeto = pesoNeto;
	}

	public Integer getPesoNetoEscurrido() {
		return pesoNetoEscurrido;
	}

	public void setPesoNetoEscurrido(Integer pesoNetoEscurrido) {
		this.pesoNetoEscurrido = pesoNetoEscurrido;
	}

	public Integer getPesoBruto() {
		return pesoBruto;
	}

	public void setPesoBruto(Integer pesoBruto) {
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

	public String getDenominaAlternativa() {
		return denominaAlternativa;
	}

	public void setDenominaAlternativa(String denominaAlternativa) {
		this.denominaAlternativa = denominaAlternativa;
	}

	public String getFormatoAlternativo() {
		return formatoAlternativo;
	}

	public void setFormatoAlternativo(String formatoAlternativo) {
		this.formatoAlternativo = formatoAlternativo;
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
		result = prime * result + ((codigoProducto == null) ? 0 : codigoProducto.hashCode());
		result = prime * result + ((denominaAlternativa == null) ? 0 : denominaAlternativa.hashCode());
		result = prime * result + ((denominaEtiqueta == null) ? 0 : denominaEtiqueta.hashCode());
		result = prime * result + ((estadoComercial == null) ? 0 : estadoComercial.hashCode());
		result = prime * result + ((fechaCambioEstado == null) ? 0 : fechaCambioEstado.hashCode());
		result = prime * result + ((fechaCreacion == null) ? 0 : fechaCreacion.hashCode());
		result = prime * result + ((fechaModificacion == null) ? 0 : fechaModificacion.hashCode());
		result = prime * result + ((fechaValorAlta == null) ? 0 : fechaValorAlta.hashCode());
		result = prime * result + ((formatoAlternativo == null) ? 0 : formatoAlternativo.hashCode());
		result = prime * result + ((formatoVenta == null) ? 0 : formatoVenta.hashCode());
		result = prime * result + ((marca == null) ? 0 : marca.hashCode());
		result = prime * result + ((pesoBruto == null) ? 0 : pesoBruto.hashCode());
		result = prime * result + ((pesoNeto == null) ? 0 : pesoNeto.hashCode());
		result = prime * result + ((pesoNetoEscurrido == null) ? 0 : pesoNetoEscurrido.hashCode());
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
		ProductosJPA other = (ProductosJPA) obj;
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
		if (denominaAlternativa == null) {
			if (other.denominaAlternativa != null)
				return false;
		} else if (!denominaAlternativa.equals(other.denominaAlternativa))
			return false;
		if (denominaEtiqueta == null) {
			if (other.denominaEtiqueta != null)
				return false;
		} else if (!denominaEtiqueta.equals(other.denominaEtiqueta))
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
		if (formatoAlternativo == null) {
			if (other.formatoAlternativo != null)
				return false;
		} else if (!formatoAlternativo.equals(other.formatoAlternativo))
			return false;
		if (formatoVenta == null) {
			if (other.formatoVenta != null)
				return false;
		} else if (!formatoVenta.equals(other.formatoVenta))
			return false;
		if (marca == null) {
			if (other.marca != null)
				return false;
		} else if (!marca.equals(other.marca))
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
