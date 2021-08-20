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
@Table(name = "O_DECLARACION_VALOR_ENV")
@IdClass(ItemDeclaracionPK.class)
public class ItemDeclaracionJPA implements Serializable {

	private static final long serialVersionUID = 1L;


	@Id
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumns({ @JoinColumn(name = "COD_N_DECLARACION_VALOR", referencedColumnName = "COD_N_DECLARACION_VALOR"),
			@JoinColumn(name = "NUM_ANYO", referencedColumnName = "NUM_ANYO"),
			@JoinColumn(name = "COD_N_VERSION", referencedColumnName = "COD_N_VERSION") })
	private DeclaracionesDeValorPostJPA codigoDv;

	@Id
	@Column(name = "COD_V_ENVASE")
	private String codigoEnvase;
	
	@Column(name = "COD_N_TARIC")
	private Long codigoTaric;	
	
	@Column(name = "TXT_DENOMINACION")
	private String txtDenominacion;

	@Column(name = "TXT_NOMBRE_BULTO_DV")
	private String txtNombreBultoDV;
	
	@Column(name = "NUM_BULTOS")
	private Integer numBultos;

	@Column(name = "NUM_CANTIDAD")
	private Double numCantidad;

	@Column(name = "COD_V_UM_CANTIDAD")
	private String codUMCantidad;

	@Column(name = "NUM_PESO_NETO")
	private Double pesoNeto;
	
	@Column(name = "COD_V_UM_PESO_NETO")
	private String codUMPesoNeto;

	@Column(name = "NUM_PESO_BRUTO")
	private Double pesoBruto;
	
	@Column(name = "COD_V_UM_PESO_BRUTO")
	private String codUMPesoBruto;

	@Column(name = "NUM_PRECIO_UNIDAD")
	private Double precioUnidad;
	
	@Column(name = "COD_V_UM_PRECIO")
	private String codUMPrecio;

	@Column(name = "NUM_IMPORTE")
	private Double importe;
	
	@Column(name = "COD_V_UM_IMPORTE")
	private String codUMImporte;

	@Column(name = "MCA_ERROR")
	private String mcaError;

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

	
	
	/**
	 * @return the codigoDv
	 */
	public DeclaracionesDeValorPostJPA getCodigoDv() {
		return codigoDv;
	}

	/**
	 * @return the codigoEnvase
	 */
	public String getCodigoEnvase() {
		return codigoEnvase;
	}

	/**
	 * @return the codigoTaric
	 */
	public Long getCodigoTaric() {
		return codigoTaric;
	}

	/**
	 * @return the txtDenominacion
	 */
	public String getTxtDenominacion() {
		return txtDenominacion;
	}

	/**
	 * @return the txtNombreBultoDV
	 */
	public String getTxtNombreBultoDV() {
		return txtNombreBultoDV;
	}

	/**
	 * @return the numBultos
	 */
	public Integer getNumBultos() {
		return numBultos;
	}

	/**
	 * @return the numCantidad
	 */
	public Double getNumCantidad() {
		return numCantidad;
	}

	/**
	 * @return the codUMCantidad
	 */
	public String getCodUMCantidad() {
		return codUMCantidad;
	}

	/**
	 * @return the pesoNeto
	 */
	public Double getPesoNeto() {
		return pesoNeto;
	}

	/**
	 * @return the codUMPesoNeto
	 */
	public String getCodUMPesoNeto() {
		return codUMPesoNeto;
	}

	/**
	 * @return the pesoBruto
	 */
	public Double getPesoBruto() {
		return pesoBruto;
	}

	/**
	 * @return the codUMPesoBruto
	 */
	public String getCodUMPesoBruto() {
		return codUMPesoBruto;
	}

	/**
	 * @return the precioUnidad
	 */
	public Double getPrecioUnidad() {
		return precioUnidad;
	}

	/**
	 * @return the codUMPrecio
	 */
	public String getCodUMPrecio() {
		return codUMPrecio;
	}

	/**
	 * @return the importe
	 */
	public Double getImporte() {
		return importe;
	}

	/**
	 * @return the codUMImporte
	 */
	public String getCodUMImporte() {
		return codUMImporte;
	}

	/**
	 * @return the mcaError
	 */
	public String getMcaError() {
		return mcaError;
	}

	/**
	 * @return the fechaCreacion
	 */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	/**
	 * @return the fechaModificacion
	 */
	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	/**
	 * @return the codAplicacion
	 */
	public String getCodAplicacion() {
		return codAplicacion;
	}

	/**
	 * @return the usuarioCreacion
	 */
	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}

	/**
	 * @return the usuarioEdit
	 */
	public String getUsuarioEdit() {
		return usuarioEdit;
	}

	/**
	 * @param codigoDv the codigoDv to set
	 */
	public void setCodigoDv(DeclaracionesDeValorPostJPA codigoDv) {
		this.codigoDv = codigoDv;
	}

	/**
	 * @param codigoEnvase the codigoEnvase to set
	 */
	public void setCodigoEnvase(String codigoEnvase) {
		this.codigoEnvase = codigoEnvase;
	}

	/**
	 * @param codigoTaric the codigoTaric to set
	 */
	public void setCodigoTaric(Long codigoTaric) {
		this.codigoTaric = codigoTaric;
	}

	/**
	 * @param txtDenominacion the txtDenominacion to set
	 */
	public void setTxtDenominacion(String txtDenominacion) {
		this.txtDenominacion = txtDenominacion;
	}

	/**
	 * @param txtNombreBultoDV the txtNombreBultoDV to set
	 */
	public void setTxtNombreBultoDV(String txtNombreBultoDV) {
		this.txtNombreBultoDV = txtNombreBultoDV;
	}

	/**
	 * @param numBultos the numBultos to set
	 */
	public void setNumBultos(Integer numBultos) {
		this.numBultos = numBultos;
	}

	/**
	 * @param numCantidad the numCantidad to set
	 */
	public void setNumCantidad(Double numCantidad) {
		this.numCantidad = numCantidad;
	}

	/**
	 * @param codUMCantidad the codUMCantidad to set
	 */
	public void setCodUMCantidad(String codUMCantidad) {
		this.codUMCantidad = codUMCantidad;
	}

	/**
	 * @param pesoNeto the pesoNeto to set
	 */
	public void setPesoNeto(Double pesoNeto) {
		this.pesoNeto = pesoNeto;
	}

	/**
	 * @param codUMPesoNeto the codUMPesoNeto to set
	 */
	public void setCodUMPesoNeto(String codUMPesoNeto) {
		this.codUMPesoNeto = codUMPesoNeto;
	}

	/**
	 * @param pesoBruto the pesoBruto to set
	 */
	public void setPesoBruto(Double pesoBruto) {
		this.pesoBruto = pesoBruto;
	}

	/**
	 * @param codUMPesoBruto the codUMPesoBruto to set
	 */
	public void setCodUMPesoBruto(String codUMPesoBruto) {
		this.codUMPesoBruto = codUMPesoBruto;
	}

	/**
	 * @param precioUnidad the precioUnidad to set
	 */
	public void setPrecioUnidad(Double precioUnidad) {
		this.precioUnidad = precioUnidad;
	}

	/**
	 * @param codUMPrecio the codUMPrecio to set
	 */
	public void setCodUMPrecio(String codUMPrecio) {
		this.codUMPrecio = codUMPrecio;
	}

	/**
	 * @param importe the importe to set
	 */
	public void setImporte(Double importe) {
		this.importe = importe;
	}

	/**
	 * @param codUMImporte the codUMImporte to set
	 */
	public void setCodUMImporte(String codUMImporte) {
		this.codUMImporte = codUMImporte;
	}

	/**
	 * @param mcaError the mcaError to set
	 */
	public void setMcaError(String mcaError) {
		this.mcaError = mcaError;
	}

	/**
	 * @param fechaCreacion the fechaCreacion to set
	 */
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	/**
	 * @param fechaModificacion the fechaModificacion to set
	 */
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	/**
	 * @param codAplicacion the codAplicacion to set
	 */
	public void setCodAplicacion(String codAplicacion) {
		this.codAplicacion = codAplicacion;
	}

	/**
	 * @param usuarioCreacion the usuarioCreacion to set
	 */
	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	/**
	 * @param usuarioEdit the usuarioEdit to set
	 */
	public void setUsuarioEdit(String usuarioEdit) {
		this.usuarioEdit = usuarioEdit;
	}

	public ItemDeclaracionJPA( DeclaracionesDeValorPostJPA codigoDv, String codigoEnvase, Long codigoTaric,
			String txtDenominacion, String txtNombreBultoDV, Integer numBultos, Double numCantidad,
			String codUMCantidad, Double pesoNeto, String codUMPesoNeto, Double pesoBruto, String codUMPesoBruto,
			Double precioUnidad, String codUMPrecio, Double importe,  String mcaError,
			Date fechaCreacion, Date fechaModificacion, String codAplicacion, String usuarioCreacion,
			String usuarioEdit) {
		super();
	
		this.codigoDv = codigoDv;
		this.codigoEnvase = codigoEnvase;
		this.codigoTaric = codigoTaric;
		this.txtDenominacion = txtDenominacion;
		this.txtNombreBultoDV = txtNombreBultoDV;
		this.numBultos = numBultos;
		this.numCantidad = numCantidad;
		this.codUMCantidad = codUMCantidad;
		this.pesoNeto = pesoNeto;
		this.codUMPesoNeto = codUMPesoNeto;
		this.pesoBruto = pesoBruto;
		this.codUMPesoBruto = codUMPesoBruto;
		this.precioUnidad = precioUnidad;
		this.codUMPrecio = codUMPrecio;
		this.importe = importe;
		this.mcaError = mcaError;
		this.fechaCreacion = fechaCreacion;
		this.fechaModificacion = fechaModificacion;
		this.codAplicacion = codAplicacion;
		this.usuarioCreacion = usuarioCreacion;
		this.usuarioEdit = usuarioEdit;
	}

	public ItemDeclaracionJPA() {
		super();
		
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codAplicacion == null) ? 0 : codAplicacion.hashCode());
		result = prime * result + ((codUMCantidad == null) ? 0 : codUMCantidad.hashCode());
		result = prime * result + ((codUMImporte == null) ? 0 : codUMImporte.hashCode());
		result = prime * result + ((codUMPesoBruto == null) ? 0 : codUMPesoBruto.hashCode());
		result = prime * result + ((codUMPesoNeto == null) ? 0 : codUMPesoNeto.hashCode());
		result = prime * result + ((codUMPrecio == null) ? 0 : codUMPrecio.hashCode());
		result = prime * result + ((codigoDv == null) ? 0 : codigoDv.hashCode());
		result = prime * result + ((codigoEnvase == null) ? 0 : codigoEnvase.hashCode());
		result = prime * result + ((codigoTaric == null) ? 0 : codigoTaric.hashCode());
		result = prime * result + ((fechaCreacion == null) ? 0 : fechaCreacion.hashCode());
		result = prime * result + ((fechaModificacion == null) ? 0 : fechaModificacion.hashCode());
		result = prime * result + ((importe == null) ? 0 : importe.hashCode());
		result = prime * result + ((mcaError == null) ? 0 : mcaError.hashCode());
		result = prime * result + ((numBultos == null) ? 0 : numBultos.hashCode());
		result = prime * result + ((numCantidad == null) ? 0 : numCantidad.hashCode());
		result = prime * result + ((pesoBruto == null) ? 0 : pesoBruto.hashCode());
		result = prime * result + ((pesoNeto == null) ? 0 : pesoNeto.hashCode());
		result = prime * result + ((precioUnidad == null) ? 0 : precioUnidad.hashCode());
		result = prime * result + ((txtDenominacion == null) ? 0 : txtDenominacion.hashCode());
		result = prime * result + ((txtNombreBultoDV == null) ? 0 : txtNombreBultoDV.hashCode());
		result = prime * result + ((usuarioCreacion == null) ? 0 : usuarioCreacion.hashCode());
		result = prime * result + ((usuarioEdit == null) ? 0 : usuarioEdit.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemDeclaracionJPA other = (ItemDeclaracionJPA) obj;
		if (codAplicacion == null) {
			if (other.codAplicacion != null)
				return false;
		} else if (!codAplicacion.equals(other.codAplicacion))
			return false;
		if (codUMCantidad == null) {
			if (other.codUMCantidad != null)
				return false;
		} else if (!codUMCantidad.equals(other.codUMCantidad))
			return false;
		if (codUMImporte == null) {
			if (other.codUMImporte != null)
				return false;
		} else if (!codUMImporte.equals(other.codUMImporte))
			return false;
		if (codUMPesoBruto == null) {
			if (other.codUMPesoBruto != null)
				return false;
		} else if (!codUMPesoBruto.equals(other.codUMPesoBruto))
			return false;
		if (codUMPesoNeto == null) {
			if (other.codUMPesoNeto != null)
				return false;
		} else if (!codUMPesoNeto.equals(other.codUMPesoNeto))
			return false;
		if (codUMPrecio == null) {
			if (other.codUMPrecio != null)
				return false;
		} else if (!codUMPrecio.equals(other.codUMPrecio))
			return false;
		if (codigoDv == null) {
			if (other.codigoDv != null)
				return false;
		} else if (!codigoDv.equals(other.codigoDv))
			return false;
		if (codigoEnvase == null) {
			if (other.codigoEnvase != null)
				return false;
		} else if (!codigoEnvase.equals(other.codigoEnvase))
			return false;
		if (codigoTaric == null) {
			if (other.codigoTaric != null)
				return false;
		} else if (!codigoTaric.equals(other.codigoTaric))
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
		if (importe == null) {
			if (other.importe != null)
				return false;
		} else if (!importe.equals(other.importe))
			return false;
		if (mcaError == null) {
			if (other.mcaError != null)
				return false;
		} else if (!mcaError.equals(other.mcaError))
			return false;
		if (numBultos == null) {
			if (other.numBultos != null)
				return false;
		} else if (!numBultos.equals(other.numBultos))
			return false;
		if (numCantidad == null) {
			if (other.numCantidad != null)
				return false;
		} else if (!numCantidad.equals(other.numCantidad))
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
		if (precioUnidad == null) {
			if (other.precioUnidad != null)
				return false;
		} else if (!precioUnidad.equals(other.precioUnidad))
			return false;
		if (txtDenominacion == null) {
			if (other.txtDenominacion != null)
				return false;
		} else if (!txtDenominacion.equals(other.txtDenominacion))
			return false;
		if (txtNombreBultoDV == null) {
			if (other.txtNombreBultoDV != null)
				return false;
		} else if (!txtNombreBultoDV.equals(other.txtNombreBultoDV))
			return false;
		if (usuarioCreacion == null) {
			if (other.usuarioCreacion != null)
				return false;
		} else if (!usuarioCreacion.equals(other.usuarioCreacion))
			return false;
		if (usuarioEdit == null) {
			if (other.usuarioEdit != null)
				return false;
		} else if (!usuarioEdit.equals(other.usuarioEdit))
			return false;
		return true;
	}
	
	
}
