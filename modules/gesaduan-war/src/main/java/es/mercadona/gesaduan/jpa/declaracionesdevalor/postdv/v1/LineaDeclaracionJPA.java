package es.mercadona.gesaduan.jpa.declaracionesdevalor.postdv.v1;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "O_DECLARACION_VALOR_LIN")
@IdClass(LineaDeclaracionPK.class)
public class LineaDeclaracionJPA implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "COD_N_MERCA")
	private Integer codMerca;

	@Id
	@Column(name = "TXT_NOMBRE_BULTO_DV")
	private String nombreTipoBulto;

	@Id
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumns({ @JoinColumn(name = "COD_N_DECLARACION_VALOR", referencedColumnName = "COD_N_DECLARACION_VALOR"),
			@JoinColumn(name = "NUM_ANYO", referencedColumnName = "NUM_ANYO"),
			@JoinColumn(name = "COD_N_VERSION", referencedColumnName = "COD_N_VERSION") })
	private DeclaracionesDeValorPostJPA codigoDv;

	@Column(name = "COD_N_TARIC")
	private Long codigoTaric;

	@Column(name = "COD_V_REA")
	private String codigoRea;
	
	@Column(name = "COD_V_EXPEDICION")
	private String expedicion;

	@Column(name = "TXT_NOMBRE_ALTERNATIVO")
	private String nombreAlternativo;

	@Column(name = "TXT_MARCA")
	private String marca;
	
	@Column(name = "MCA_ERROR")
	private String marcaError;

	@Column(name = "TXT_FORMATO_VENTA_ALTERNATIVO")
	private String descFormatoVentaAlternativo;

	@Column(name = "NUM_BULTOS")
	private Integer numeroDeBultos;

	@Column(name = "NUM_PESO_NETO")
	private Double pesoNetoLinea;

	@Column(name = "NUM_PESO_BRUTO")
	private Double pesoBrutoLinea;

	@Column(name = "NUM_VOLUMEN")
	private Double volumenUnidad;

	@Column(name = "NUM_CANTIDAD")
	private Double cantidadFormato;

	@Column(name = "NUM_PRECIO_UNIDAD")
	private Double precioUnidad;

	@Column(name = "NUM_IMPORTE_TOTAL")
	private Double importeTotal;

	@Column(name = "NUM_GRADO_ALCOHOL")
	private Double gradoAlcohol;

	@Column(name = "NUM_GRADO_PLATO")
	private Double gradoPlato;

	@Column(name = "COD_V_PAIS")
	private String paisOrigen;

	@Column(name = "MCA_PRODUCTO_LPC")
	private String esListoParaComer;

	@Column(name = "FEC_D_ALBARAN")
	@Temporal(TemporalType.DATE)
	private Date fechaAlbaran;

	@Column(name = "FEC_D_CREACION")
	@Temporal(TemporalType.DATE)
	private Date fechaCreacion;

	@Column(name = "FEC_D_MODIFICACION")
	@Temporal(TemporalType.DATE)
	private Date fechaModificacion;

	@Column(name = "COD_V_APLICACION")
	private String codAplicacion;

	@Column(name = "COD_V_USUARIO_CREACION")
	private String usuarioCreacion;

	@Column(name = "COD_V_USUARIO_MODIFICACION")
	private String usuarioEdit;

	public Integer getCodMerca() {
		return codMerca;
	}

	public void setCodMerca(Integer codMerca) {
		this.codMerca = codMerca;
	}

	public DeclaracionesDeValorPostJPA getCodigoDv() {
		return codigoDv;
	}

	public void setCodigoDv(DeclaracionesDeValorPostJPA codigoDv) {
		this.codigoDv = codigoDv;
	}

	public Long getCodigoTaric() {
		return codigoTaric;
	}

	public void setCodigoTaric(Long codigoTaric) {
		this.codigoTaric = codigoTaric;
	}

	public String getCodigoRea() {
		return codigoRea;
	}

	public void setCodigoRea(String codigoRea) {
		this.codigoRea = codigoRea;
	}

	public String getExpedicion() {
		return expedicion;
	}

	public void setExpedicion(String expedicion) {
		this.expedicion = expedicion;
	}

	public String getNombreAlternativo() {
		return nombreAlternativo;
	}

	public void setNombreAlternativo(String nombreAlternativo) {
		this.nombreAlternativo = nombreAlternativo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getDescFormatoVentaAlternativo() {
		return descFormatoVentaAlternativo;
	}

	public void setDescFormatoVentaAlternativo(String descFormatoVentaAlternativo) {
		this.descFormatoVentaAlternativo = descFormatoVentaAlternativo;
	}

	public String getNombreTipoBulto() {
		return nombreTipoBulto;
	}

	public void setNombreTipoBulto(String nombreTipoBulto) {
		this.nombreTipoBulto = nombreTipoBulto;
	}

	public Integer getNumeroDeBultos() {
		return numeroDeBultos;
	}

	public void setNumeroDeBultos(Integer numeroDeBultos) {
		this.numeroDeBultos = numeroDeBultos;
	}

	public Double getPesoNetoLinea() {
		return pesoNetoLinea;
	}

	public void setPesoNetoLinea(Double pesoNetoLinea) {
		this.pesoNetoLinea = pesoNetoLinea;
	}

	public Double getPesoBrutoLinea() {
		return pesoBrutoLinea;
	}

	public void setPesoBrutoLinea(Double pesoBrutoLinea) {
		this.pesoBrutoLinea = pesoBrutoLinea;
	}

	public Double getVolumenUnidad() {
		return volumenUnidad;
	}

	public void setVolumenUnidad(Double volumenUnidad) {
		this.volumenUnidad = volumenUnidad;
	}

	public Double getCantidadFormato() {
		return cantidadFormato;
	}

	public void setCantidadFormato(Double cantidadFormato) {
		this.cantidadFormato = cantidadFormato;
	}

	public Double getPrecioUnidad() {
		return precioUnidad;
	}

	public void setPrecioUnidad(Double precioUnidad) {
		this.precioUnidad = precioUnidad;
	}

	public Double getImporteTotal() {
		return importeTotal;
	}

	public void setImporteTotal(Double importeTotal) {
		this.importeTotal = importeTotal;
	}

	public Double getGradoAlcohol() {
		return gradoAlcohol;
	}

	public void setGradoAlcohol(Double gradoAlcohol) {
		this.gradoAlcohol = gradoAlcohol;
	}

	public Double getGradoPlato() {
		return gradoPlato;
	}

	public void setGradoPlato(Double gradoPlato) {
		this.gradoPlato = gradoPlato;
	}

	public String getPaisOrigen() {
		return paisOrigen;
	}

	public void setPaisOrigen(String paisOrigen) {
		this.paisOrigen = paisOrigen;
	}

	public String getEsListoParaComer() {
		return esListoParaComer;
	}

	public void setEsListoParaComer(String esListoParaComer) {
		this.esListoParaComer = esListoParaComer;
	}

	public Date getFechaAlbaran() {
		return fechaAlbaran;
	}

	public void setFechaAlbaran(Date fechaAlbaran) {
		this.fechaAlbaran = fechaAlbaran;
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

	public String getCodAplicacion() {
		return codAplicacion;
	}

	public void setCodAplicacion(String codAplicacion) {
		this.codAplicacion = codAplicacion;
	}

	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public String getUsuarioEdit() {
		return usuarioEdit;
	}

	public void setUsuarioEdit(String usuarioEdit) {
		this.usuarioEdit = usuarioEdit;
	}

	/**
	 * @return the marcaError
	 */
	public String getMarcaError() {
		return marcaError;
	}

	/**
	 * @param marcaError the marcaError to set
	 */
	public void setMarcaError(String marcaError) {
		this.marcaError = marcaError;
	}

	public LineaDeclaracionJPA(Integer codMerca, DeclaracionesDeValorPostJPA codigoDv, Long codigoTaric,
			String codigoRea, String expedicion, String nombreAlternativo, String marca,
			String descFormatoVentaAlternativo, String nombreTipoBulto, Integer numeroDeBultos, Double pesoNetoLinea,
			Double pesoBrutoLinea, Double volumenUnidad, Double cantidadFormato, Double precioUnidad,
			Double importeTotal, Double gradoAlcohol, Double gradoPlato, String paisOrigen, String esListoParaComer,
			Date fechaAlbaran, Date fechaCreacion, Date fechaModificacion, String codAplicacion, String usuarioCreacion,
			String usuarioEdit) {
		super();
		this.codMerca = codMerca;
		this.codigoDv = codigoDv;
		this.codigoTaric = codigoTaric;
		this.codigoRea = codigoRea;
		this.expedicion = expedicion;
		this.nombreAlternativo = nombreAlternativo;
		this.marca = marca;
		this.descFormatoVentaAlternativo = descFormatoVentaAlternativo;
		this.nombreTipoBulto = nombreTipoBulto;
		this.numeroDeBultos = numeroDeBultos;
		this.pesoNetoLinea = pesoNetoLinea;
		this.pesoBrutoLinea = pesoBrutoLinea;
		this.volumenUnidad = volumenUnidad;
		this.cantidadFormato = cantidadFormato;
		this.precioUnidad = precioUnidad;
		this.importeTotal = importeTotal;
		this.gradoAlcohol = gradoAlcohol;
		this.gradoPlato = gradoPlato;
		this.paisOrigen = paisOrigen;
		this.esListoParaComer = esListoParaComer;
		this.fechaAlbaran = fechaAlbaran;
		this.fechaCreacion = fechaCreacion;
		this.fechaModificacion = fechaModificacion;
		this.codAplicacion = codAplicacion;
		this.usuarioCreacion = usuarioCreacion;
		this.usuarioEdit = usuarioEdit;
	}

	public LineaDeclaracionJPA() {
		super();
		
	}

	@Override
	public String toString() {
		return "LineaDeclaracionJPA [codMerca=" + codMerca + ", codigoDv=" + codigoDv + ", codigoTaric=" + codigoTaric
				+ ", codigoRea=" + codigoRea + ", expedicion=" + expedicion + ", nombreAlternativo=" + nombreAlternativo
				+ ", marca=" + marca + ", descFormatoVentaAlternativo=" + descFormatoVentaAlternativo
				+ ", nombreTipoBulto=" + nombreTipoBulto + ", numeroDeBultos=" + numeroDeBultos + ", pesoNetoLinea="
				+ pesoNetoLinea + ", pesoBrutoLinea=" + pesoBrutoLinea + ", volumenUnidad=" + volumenUnidad
				+ ", cantidadFormato=" + cantidadFormato + ", precioUnidad=" + precioUnidad + ", importeTotal="
				+ importeTotal + ", gradoAlcohol=" + gradoAlcohol + ", gradoPlato=" + gradoPlato + ", paisOrigen="
				+ paisOrigen + ", esListoParaComer=" + esListoParaComer + ", fechaAlbaran=" + fechaAlbaran
				+ ", fechaCreacion=" + fechaCreacion + ", fechaModificacion=" + fechaModificacion + ", codAplicacion="
				+ codAplicacion + ", usuarioCreacion=" + usuarioCreacion + ", usuarioEdit=" + usuarioEdit + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cantidadFormato == null) ? 0 : cantidadFormato.hashCode());
		result = prime * result + ((codAplicacion == null) ? 0 : codAplicacion.hashCode());
		result = prime * result + ((codMerca == null) ? 0 : codMerca.hashCode());
		result = prime * result + ((codigoDv == null) ? 0 : codigoDv.hashCode());
		result = prime * result + ((codigoRea == null) ? 0 : codigoRea.hashCode());
		result = prime * result + ((codigoTaric == null) ? 0 : codigoTaric.hashCode());
		result = prime * result + ((descFormatoVentaAlternativo == null) ? 0 : descFormatoVentaAlternativo.hashCode());
		result = prime * result + ((esListoParaComer == null) ? 0 : esListoParaComer.hashCode());
		result = prime * result + ((expedicion == null) ? 0 : expedicion.hashCode());
		result = prime * result + ((fechaAlbaran == null) ? 0 : fechaAlbaran.hashCode());
		result = prime * result + ((fechaCreacion == null) ? 0 : fechaCreacion.hashCode());
		result = prime * result + ((fechaModificacion == null) ? 0 : fechaModificacion.hashCode());
		result = prime * result + ((gradoAlcohol == null) ? 0 : gradoAlcohol.hashCode());
		result = prime * result + ((gradoPlato == null) ? 0 : gradoPlato.hashCode());
		result = prime * result + ((importeTotal == null) ? 0 : importeTotal.hashCode());
		result = prime * result + ((marca == null) ? 0 : marca.hashCode());
		result = prime * result + ((nombreAlternativo == null) ? 0 : nombreAlternativo.hashCode());
		result = prime * result + ((nombreTipoBulto == null) ? 0 : nombreTipoBulto.hashCode());
		result = prime * result + ((numeroDeBultos == null) ? 0 : numeroDeBultos.hashCode());
		result = prime * result + ((paisOrigen == null) ? 0 : paisOrigen.hashCode());
		result = prime * result + ((pesoBrutoLinea == null) ? 0 : pesoBrutoLinea.hashCode());
		result = prime * result + ((pesoNetoLinea == null) ? 0 : pesoNetoLinea.hashCode());
		result = prime * result + ((precioUnidad == null) ? 0 : precioUnidad.hashCode());
		result = prime * result + ((usuarioCreacion == null) ? 0 : usuarioCreacion.hashCode());
		result = prime * result + ((usuarioEdit == null) ? 0 : usuarioEdit.hashCode());
		result = prime * result + ((volumenUnidad == null) ? 0 : volumenUnidad.hashCode());
		result = prime * result + ((marcaError == null) ? 0 : marcaError.hashCode());
		return result;
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		LineaDeclaracionJPA other = (LineaDeclaracionJPA) obj;
		if (cantidadFormato == null) {
			if (other.cantidadFormato != null) {
				return false;
			}
		} else if (!cantidadFormato.equals(other.cantidadFormato)) {
			return false;
		}
		if (codAplicacion == null) {
			if (other.codAplicacion != null) {
				return false;
			}
		} else if (!codAplicacion.equals(other.codAplicacion)) {
			return false;
		}
		if (codMerca == null) {
			if (other.codMerca != null) {
				return false;
			}
		} else if (!codMerca.equals(other.codMerca)) {
			return false;
		}
		if (codigoDv == null) {
			if (other.codigoDv != null) {
				return false;
			}
		} else if (!codigoDv.equals(other.codigoDv)) {
			return false;
		}
		if (codigoRea == null) {
			if (other.codigoRea != null) {
				return false;
			}
		} else if (!codigoRea.equals(other.codigoRea)) {
			return false;
		}
		if (codigoTaric == null) {
			if (other.codigoTaric != null) {
				return false;
			}
		} else if (!codigoTaric.equals(other.codigoTaric)) {
			return false;
		}
		if (descFormatoVentaAlternativo == null) {
			if (other.descFormatoVentaAlternativo != null) {
				return false;
			}
		} else if (!descFormatoVentaAlternativo.equals(other.descFormatoVentaAlternativo)) {
			return false;
		}
		if (esListoParaComer == null) {
			if (other.esListoParaComer != null) {
				return false;
			}
		} else if (!esListoParaComer.equals(other.esListoParaComer)) {
			return false;
		}
		if (expedicion == null) {
			if (other.expedicion != null) {
				return false;
			}
		} else if (!expedicion.equals(other.expedicion)) {
			return false;
		}
		if (fechaAlbaran == null) {
			if (other.fechaAlbaran != null) {
				return false;
			}
		} else if (!fechaAlbaran.equals(other.fechaAlbaran)) {
			return false;
		}
		if (fechaCreacion == null) {
			if (other.fechaCreacion != null) {
				return false;
			}
		} else if (!fechaCreacion.equals(other.fechaCreacion)) {
			return false;
		}
		if (fechaModificacion == null) {
			if (other.fechaModificacion != null) {
				return false;
			}
		} else if (!fechaModificacion.equals(other.fechaModificacion)) {
			return false;
		}
		if (gradoAlcohol == null) {
			if (other.gradoAlcohol != null) {
				return false;
			}
		} else if (!gradoAlcohol.equals(other.gradoAlcohol)) {
			return false;
		}
		if (gradoPlato == null) {
			if (other.gradoPlato != null) {
				return false;
			}
		} else if (!gradoPlato.equals(other.gradoPlato)) {
			return false;
		}
		if (importeTotal == null) {
			if (other.importeTotal != null) {
				return false;
			}
		} else if (!importeTotal.equals(other.importeTotal)) {
			return false;
		}
		if (marca == null) {
			if (other.marca != null) {
				return false;
			}
		} else if (!marca.equals(other.marca)) {
			return false;
		}
		if (nombreAlternativo == null) {
			if (other.nombreAlternativo != null) {
				return false;
			}
		} else if (!nombreAlternativo.equals(other.nombreAlternativo)) {
			return false;
		}
		if (nombreTipoBulto == null) {
			if (other.nombreTipoBulto != null) {
				return false;
			}
		} else if (!nombreTipoBulto.equals(other.nombreTipoBulto)) {
			return false;
		}
		if (numeroDeBultos == null) {
			if (other.numeroDeBultos != null) {
				return false;
			}
		} else if (!numeroDeBultos.equals(other.numeroDeBultos)) {
			return false;
		}
		if (paisOrigen == null) {
			if (other.paisOrigen != null) {
				return false;
			}
		} else if (!paisOrigen.equals(other.paisOrigen)) {
			return false;
		}
		if (pesoBrutoLinea == null) {
			if (other.pesoBrutoLinea != null) {
				return false;
			}
		} else if (!pesoBrutoLinea.equals(other.pesoBrutoLinea)) {
			return false;
		}
		if (pesoNetoLinea == null) {
			if (other.pesoNetoLinea != null) {
				return false;
			}
		} else if (!pesoNetoLinea.equals(other.pesoNetoLinea)) {
			return false;
		}
		if (precioUnidad == null) {
			if (other.precioUnidad != null) {
				return false;
			}
		} else if (!precioUnidad.equals(other.precioUnidad)) {
			return false;
		}
		if (usuarioCreacion == null) {
			if (other.usuarioCreacion != null) {
				return false;
			}
		} else if (!usuarioCreacion.equals(other.usuarioCreacion)) {
			return false;
		}
		if (usuarioEdit == null) {
			if (other.usuarioEdit != null) {
				return false;
			}
		} else if (!usuarioEdit.equals(other.usuarioEdit)) {
			return false;
		}
		if (volumenUnidad == null) {
			if (other.volumenUnidad != null) {
				return false;
			}
		} else if (!volumenUnidad.equals(other.volumenUnidad)) {
			return false;
		}
		if (marcaError == null) {
			if (other.marcaError != null) {
				return false;
			}
		} else if (!marcaError.equals(other.marcaError)) {
			return false;
		}
		return true;
	}	
	
}
