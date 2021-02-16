package es.mercadona.gesaduan.jpa.cargas.v1;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "D_CARGA")
public class CargasJPA implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private CargasPkJPA id;
	
	@Column(name = "COD_N_TIPO_CARGA")
	private Integer codigoTipoCarga;

	@Column(name = "COD_N_TIPO_SUMINISTRO")
	private Integer codigoTipoSuministro;
	
	@Column(name = "COD_N_CATEGORIA")
	private Integer categoria;
	
	@Column(name = "COD_N_PROVEEDOR")
	private String codigoProveedor;
	
	@Column(name = "COD_V_CENTRO_DESTINO")
	private String codigoCentroDestino;
	
	@Column(name = "FEC_DT_SERVICIO")
	private Date fechaServicio;
	
	@Column(name = "FEC_D_ENTREGA")
	private Date fechaEntrega;

	@Column(name = "COD_N_ESTADO")
	private Integer codigoEstado;
	
	@Column(name = "NUM_HUECOS_ORIGEN")
	private Double numeroHuecosOrigen;
	
	@Column(name = "NUM_HUECOS")
	private Double numeroHuecos;
	
	@Column(name = "NUM_PESO_TOTAL")
	private Double numeroPesoTotal;
	
	@Column(name = "MCA_CONTIENE_LPC")
	private String mcaLPC;
	
	@Column(name = "NUM_DIVISIONES")
	private Integer numeroDivisiones;
	
	@Column(name = "NUM_HUECOS_RESTANTES")
	private Double numeroHuecosRestantes;
	
	@Column(name = "NUM_PESO_RESTANTE")
	private Double numeroPesoRestante;
	
	@Column(name = "MCA_PEDIDOS_SIN_VALIDAR")
	private String mcaPedidosSinValidar;
	
	@Column(name = "COD_V_APLICACION_ORIGEN")
	private String aplicacionOrigen;
	
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

	public CargasPkJPA getId() {
		return id;
	}

	public void setId(CargasPkJPA id) {
		this.id = id;
	}

	public Integer getCodigoTipoCarga() {
		return codigoTipoCarga;
	}

	public void setCodigoTipoCarga(Integer codigoTipoCarga) {
		this.codigoTipoCarga = codigoTipoCarga;
	}

	public Integer getCodigoTipoSuministro() {
		return codigoTipoSuministro;
	}

	public void setCodigoTipoSuministro(Integer codigoTipoSuministro) {
		this.codigoTipoSuministro = codigoTipoSuministro;
	}

	public Integer getCategoria() {
		return categoria;
	}

	public void setCategoria(Integer categoria) {
		this.categoria = categoria;
	}

	public String getCodigoProveedor() {
		return codigoProveedor;
	}

	public void setCodigoProveedor(String codigoProveedor) {
		this.codigoProveedor = codigoProveedor;
	}

	public String getCodigoCentroDestino() {
		return codigoCentroDestino;
	}

	public void setCodigoCentroDestino(String codigoCentroDestino) {
		this.codigoCentroDestino = codigoCentroDestino;
	}

	public Date getFechaServicio() {
		return fechaServicio;
	}

	public void setFechaServicio(Date fechaServicio) {
		this.fechaServicio = fechaServicio;
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public Integer getCodigoEstado() {
		return codigoEstado;
	}

	public void setCodigoEstado(Integer codigoEstado) {
		this.codigoEstado = codigoEstado;
	}

	public Double getNumeroHuecosOrigen() {
		return numeroHuecosOrigen;
	}

	public void setNumeroHuecosOrigen(Double numeroHuecosOrigen) {
		this.numeroHuecosOrigen = numeroHuecosOrigen;
	}

	public Double getNumeroHuecos() {
		return numeroHuecos;
	}

	public void setNumeroHuecos(Double numeroHuecos) {
		this.numeroHuecos = numeroHuecos;
	}

	public Double getNumeroPesoTotal() {
		return numeroPesoTotal;
	}

	public void setNumeroPesoTotal(Double numeroPesoTotal) {
		this.numeroPesoTotal = numeroPesoTotal;
	}

	public String getMcaLPC() {
		return mcaLPC;
	}

	public void setMcaLPC(String mcaLPC) {
		this.mcaLPC = mcaLPC;
	}

	public Integer getNumeroDivisiones() {
		return numeroDivisiones;
	}

	public void setNumeroDivisiones(Integer numeroDivisiones) {
		this.numeroDivisiones = numeroDivisiones;
	}

	public Double getNumeroHuecosRestantes() {
		return numeroHuecosRestantes;
	}

	public void setNumeroHuecosRestantes(Double numeroHuecosRestantes) {
		this.numeroHuecosRestantes = numeroHuecosRestantes;
	}

	public Double getNumeroPesoRestante() {
		return numeroPesoRestante;
	}

	public void setNumeroPesoRestante(Double numeroPesoRestante) {
		this.numeroPesoRestante = numeroPesoRestante;
	}

	public String getMcaPedidosSinValidar() {
		return mcaPedidosSinValidar;
	}

	public void setMcaPedidosSinValidar(String mcaPedidosSinValidar) {
		this.mcaPedidosSinValidar = mcaPedidosSinValidar;
	}

	public String getAplicacionOrigen() {
		return aplicacionOrigen;
	}

	public void setAplicacionOrigen(String aplicacionOrigen) {
		this.aplicacionOrigen = aplicacionOrigen;
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
		result = prime * result + ((aplicacionOrigen == null) ? 0 : aplicacionOrigen.hashCode());
		result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result + ((codigoAplicacion == null) ? 0 : codigoAplicacion.hashCode());
		result = prime * result + ((codigoCentroDestino == null) ? 0 : codigoCentroDestino.hashCode());
		result = prime * result + ((codigoEstado == null) ? 0 : codigoEstado.hashCode());
		result = prime * result + ((codigoProveedor == null) ? 0 : codigoProveedor.hashCode());
		result = prime * result + ((codigoTipoCarga == null) ? 0 : codigoTipoCarga.hashCode());
		result = prime * result + ((codigoTipoSuministro == null) ? 0 : codigoTipoSuministro.hashCode());
		result = prime * result + ((fechaCreacion == null) ? 0 : fechaCreacion.hashCode());
		result = prime * result + ((fechaEntrega == null) ? 0 : fechaEntrega.hashCode());
		result = prime * result + ((fechaModificacion == null) ? 0 : fechaModificacion.hashCode());
		result = prime * result + ((fechaServicio == null) ? 0 : fechaServicio.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((mcaLPC == null) ? 0 : mcaLPC.hashCode());
		result = prime * result + ((mcaPedidosSinValidar == null) ? 0 : mcaPedidosSinValidar.hashCode());
		result = prime * result + ((numeroDivisiones == null) ? 0 : numeroDivisiones.hashCode());
		result = prime * result + ((numeroHuecos == null) ? 0 : numeroHuecos.hashCode());
		result = prime * result + ((numeroHuecosOrigen == null) ? 0 : numeroHuecosOrigen.hashCode());
		result = prime * result + ((numeroHuecosRestantes == null) ? 0 : numeroHuecosRestantes.hashCode());
		result = prime * result + ((numeroPesoRestante == null) ? 0 : numeroPesoRestante.hashCode());
		result = prime * result + ((numeroPesoTotal == null) ? 0 : numeroPesoTotal.hashCode());
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
		CargasJPA other = (CargasJPA) obj;
		if (aplicacionOrigen == null) {
			if (other.aplicacionOrigen != null)
				return false;
		} else if (!aplicacionOrigen.equals(other.aplicacionOrigen))
			return false;
		if (categoria == null) {
			if (other.categoria != null)
				return false;
		} else if (!categoria.equals(other.categoria))
			return false;
		if (codigoAplicacion == null) {
			if (other.codigoAplicacion != null)
				return false;
		} else if (!codigoAplicacion.equals(other.codigoAplicacion))
			return false;
		if (codigoCentroDestino == null) {
			if (other.codigoCentroDestino != null)
				return false;
		} else if (!codigoCentroDestino.equals(other.codigoCentroDestino))
			return false;
		if (codigoEstado == null) {
			if (other.codigoEstado != null)
				return false;
		} else if (!codigoEstado.equals(other.codigoEstado))
			return false;
		if (codigoProveedor == null) {
			if (other.codigoProveedor != null)
				return false;
		} else if (!codigoProveedor.equals(other.codigoProveedor))
			return false;
		if (codigoTipoCarga == null) {
			if (other.codigoTipoCarga != null)
				return false;
		} else if (!codigoTipoCarga.equals(other.codigoTipoCarga))
			return false;
		if (codigoTipoSuministro == null) {
			if (other.codigoTipoSuministro != null)
				return false;
		} else if (!codigoTipoSuministro.equals(other.codigoTipoSuministro))
			return false;
		if (fechaCreacion == null) {
			if (other.fechaCreacion != null)
				return false;
		} else if (!fechaCreacion.equals(other.fechaCreacion))
			return false;
		if (fechaEntrega == null) {
			if (other.fechaEntrega != null)
				return false;
		} else if (!fechaEntrega.equals(other.fechaEntrega))
			return false;
		if (fechaModificacion == null) {
			if (other.fechaModificacion != null)
				return false;
		} else if (!fechaModificacion.equals(other.fechaModificacion))
			return false;
		if (fechaServicio == null) {
			if (other.fechaServicio != null)
				return false;
		} else if (!fechaServicio.equals(other.fechaServicio))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (mcaLPC == null) {
			if (other.mcaLPC != null)
				return false;
		} else if (!mcaLPC.equals(other.mcaLPC))
			return false;
		if (mcaPedidosSinValidar == null) {
			if (other.mcaPedidosSinValidar != null)
				return false;
		} else if (!mcaPedidosSinValidar.equals(other.mcaPedidosSinValidar))
			return false;
		if (numeroDivisiones == null) {
			if (other.numeroDivisiones != null)
				return false;
		} else if (!numeroDivisiones.equals(other.numeroDivisiones))
			return false;
		if (numeroHuecos == null) {
			if (other.numeroHuecos != null)
				return false;
		} else if (!numeroHuecos.equals(other.numeroHuecos))
			return false;
		if (numeroHuecosOrigen == null) {
			if (other.numeroHuecosOrigen != null)
				return false;
		} else if (!numeroHuecosOrigen.equals(other.numeroHuecosOrigen))
			return false;
		if (numeroHuecosRestantes == null) {
			if (other.numeroHuecosRestantes != null)
				return false;
		} else if (!numeroHuecosRestantes.equals(other.numeroHuecosRestantes))
			return false;
		if (numeroPesoRestante == null) {
			if (other.numeroPesoRestante != null)
				return false;
		} else if (!numeroPesoRestante.equals(other.numeroPesoRestante))
			return false;
		if (numeroPesoTotal == null) {
			if (other.numeroPesoTotal != null)
				return false;
		} else if (!numeroPesoTotal.equals(other.numeroPesoTotal))
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
