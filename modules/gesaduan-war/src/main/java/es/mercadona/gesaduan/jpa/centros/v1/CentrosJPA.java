package es.mercadona.gesaduan.jpa.centros.v1;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "D_CENTRO_R")
public class CentrosJPA implements Serializable {
	
	private static final long serialVersionUID = 1L;
		
	@Id
	@Column(name="COD_V_CENTRO")
	private Integer codigoCentro;
	
	@Column(name = "COD_V_EMPRESA")
	private String codigoEmpresa;
	
	@Column(name = "TXT_RAZON_SOCIAL")
	private String nombreCentro;
	
	@Column(name = "TXT_NOMBRE_LARGO")
	private String nombreLargo;
	
	@Column(name = "TXT_NOMBRE_CORTO")
	private String nombreCorto;
	
	@Column(name = "COD_V_TIPO_CENTRO")
	private String tipoCentro;
	
	@Column(name = "FEC_D_APERTURA")
	private Date fechaApertura;
	
	@Column(name = "FEC_D_CIERRE")
	private Date fechaCierre;
	
	@Column(name = "FEC_D_INI_BLOQUEO")
	private Date fechaInicioBloqueo;
	
	@Column(name = "FEC_D_FIN_BLOQUEO")
	private Date fechaFinBloqueo;
	
	@Column(name = "COD_N_GLN_PO")
	private String codigoGLN;
	
	@Column(name = "TXT_DIRECCION")
	private String direccion;
	
	@Column(name = "TXT_COD_POSTAL")
	private String codigoPostal;
	
	@Column(name = "TXT_LOCALIDAD")
	private String localidad;
	
	@Column(name = "TXT_MUNICIPIO")
	private String municipio;
	
	@Column(name = "COD_N_PROVINCIA")
	private String codigoProvincia;
	
	@Column(name = "TXT_PROVINCIA")
	private String nombreProvincia;
	
	@Column(name = "COD_V_ISO_PAIS")
	private String codigoIso;
	
	@Column(name = "TXT_COMUNIDAD_AUTONOMA")
	private String comunidadAutonoma;
	
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

	public Integer getCodigoCentro() {
		return codigoCentro;
	}

	public void setCodigoCentro(Integer codigoCentro) {
		this.codigoCentro = codigoCentro;
	}

	public String getCodigoEmpresa() {
		return codigoEmpresa;
	}

	public void setCodigoEmpresa(String codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
	}

	public String getNombreCentro() {
		return nombreCentro;
	}

	public void setNombreCentro(String nombreCentro) {
		this.nombreCentro = nombreCentro;
	}

	public String getNombreLargo() {
		return nombreLargo;
	}

	public void setNombreLargo(String nombreLargo) {
		this.nombreLargo = nombreLargo;
	}

	public String getNombreCorto() {
		return nombreCorto;
	}

	public void setNombreCorto(String nombreCorto) {
		this.nombreCorto = nombreCorto;
	}

	public String getTipoCentro() {
		return tipoCentro;
	}

	public void setTipoCentro(String tipoCentro) {
		this.tipoCentro = tipoCentro;
	}

	public Date getFechaApertura() {
		return fechaApertura;
	}

	public void setFechaApertura(Date fechaApertura) {
		this.fechaApertura = fechaApertura;
	}

	public Date getFechaCierre() {
		return fechaCierre;
	}

	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	public Date getFechaInicioBloqueo() {
		return fechaInicioBloqueo;
	}

	public void setFechaInicioBloqueo(Date fechaInicioBloqueo) {
		this.fechaInicioBloqueo = fechaInicioBloqueo;
	}

	public Date getFechaFinBloqueo() {
		return fechaFinBloqueo;
	}

	public void setFechaFinBloqueo(Date fechaFinBloqueo) {
		this.fechaFinBloqueo = fechaFinBloqueo;
	}

	public String getCodigoGLN() {
		return codigoGLN;
	}

	public void setCodigoGLN(String codigoGLN) {
		this.codigoGLN = codigoGLN;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getCodigoProvincia() {
		return codigoProvincia;
	}

	public void setCodigoProvincia(String codigoProvincia) {
		this.codigoProvincia = codigoProvincia;
	}

	public String getNombreProvincia() {
		return nombreProvincia;
	}

	public void setNombreProvincia(String nombreProvincia) {
		this.nombreProvincia = nombreProvincia;
	}

	public String getCodigoIso() {
		return codigoIso;
	}

	public void setCodigoIso(String codigoIso) {
		this.codigoIso = codigoIso;
	}

	public String getComunidadAutonoma() {
		return comunidadAutonoma;
	}

	public void setComunidadAutonoma(String comunidadAutonoma) {
		this.comunidadAutonoma = comunidadAutonoma;
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
		result = prime * result + ((codigoCentro == null) ? 0 : codigoCentro.hashCode());
		result = prime * result + ((codigoEmpresa == null) ? 0 : codigoEmpresa.hashCode());
		result = prime * result + ((codigoGLN == null) ? 0 : codigoGLN.hashCode());
		result = prime * result + ((codigoIso == null) ? 0 : codigoIso.hashCode());
		result = prime * result + ((codigoPostal == null) ? 0 : codigoPostal.hashCode());
		result = prime * result + ((codigoProvincia == null) ? 0 : codigoProvincia.hashCode());
		result = prime * result + ((comunidadAutonoma == null) ? 0 : comunidadAutonoma.hashCode());
		result = prime * result + ((direccion == null) ? 0 : direccion.hashCode());
		result = prime * result + ((fechaApertura == null) ? 0 : fechaApertura.hashCode());
		result = prime * result + ((fechaCierre == null) ? 0 : fechaCierre.hashCode());
		result = prime * result + ((fechaCreacion == null) ? 0 : fechaCreacion.hashCode());
		result = prime * result + ((fechaFinBloqueo == null) ? 0 : fechaFinBloqueo.hashCode());
		result = prime * result + ((fechaInicioBloqueo == null) ? 0 : fechaInicioBloqueo.hashCode());
		result = prime * result + ((fechaModificacion == null) ? 0 : fechaModificacion.hashCode());
		result = prime * result + ((localidad == null) ? 0 : localidad.hashCode());
		result = prime * result + ((municipio == null) ? 0 : municipio.hashCode());
		result = prime * result + ((nombreCentro == null) ? 0 : nombreCentro.hashCode());
		result = prime * result + ((nombreCorto == null) ? 0 : nombreCorto.hashCode());
		result = prime * result + ((nombreLargo == null) ? 0 : nombreLargo.hashCode());
		result = prime * result + ((nombreProvincia == null) ? 0 : nombreProvincia.hashCode());
		result = prime * result + ((tipoCentro == null) ? 0 : tipoCentro.hashCode());
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
		CentrosJPA other = (CentrosJPA) obj;
		if (codigoAplicacion == null) {
			if (other.codigoAplicacion != null)
				return false;
		} else if (!codigoAplicacion.equals(other.codigoAplicacion))
			return false;
		if (codigoCentro == null) {
			if (other.codigoCentro != null)
				return false;
		} else if (!codigoCentro.equals(other.codigoCentro))
			return false;
		if (codigoEmpresa == null) {
			if (other.codigoEmpresa != null)
				return false;
		} else if (!codigoEmpresa.equals(other.codigoEmpresa))
			return false;
		if (codigoGLN == null) {
			if (other.codigoGLN != null)
				return false;
		} else if (!codigoGLN.equals(other.codigoGLN))
			return false;
		if (codigoIso == null) {
			if (other.codigoIso != null)
				return false;
		} else if (!codigoIso.equals(other.codigoIso))
			return false;
		if (codigoPostal == null) {
			if (other.codigoPostal != null)
				return false;
		} else if (!codigoPostal.equals(other.codigoPostal))
			return false;
		if (codigoProvincia == null) {
			if (other.codigoProvincia != null)
				return false;
		} else if (!codigoProvincia.equals(other.codigoProvincia))
			return false;
		if (comunidadAutonoma == null) {
			if (other.comunidadAutonoma != null)
				return false;
		} else if (!comunidadAutonoma.equals(other.comunidadAutonoma))
			return false;
		if (direccion == null) {
			if (other.direccion != null)
				return false;
		} else if (!direccion.equals(other.direccion))
			return false;
		if (fechaApertura == null) {
			if (other.fechaApertura != null)
				return false;
		} else if (!fechaApertura.equals(other.fechaApertura))
			return false;
		if (fechaCierre == null) {
			if (other.fechaCierre != null)
				return false;
		} else if (!fechaCierre.equals(other.fechaCierre))
			return false;
		if (fechaCreacion == null) {
			if (other.fechaCreacion != null)
				return false;
		} else if (!fechaCreacion.equals(other.fechaCreacion))
			return false;
		if (fechaFinBloqueo == null) {
			if (other.fechaFinBloqueo != null)
				return false;
		} else if (!fechaFinBloqueo.equals(other.fechaFinBloqueo))
			return false;
		if (fechaInicioBloqueo == null) {
			if (other.fechaInicioBloqueo != null)
				return false;
		} else if (!fechaInicioBloqueo.equals(other.fechaInicioBloqueo))
			return false;
		if (fechaModificacion == null) {
			if (other.fechaModificacion != null)
				return false;
		} else if (!fechaModificacion.equals(other.fechaModificacion))
			return false;
		if (localidad == null) {
			if (other.localidad != null)
				return false;
		} else if (!localidad.equals(other.localidad))
			return false;
		if (municipio == null) {
			if (other.municipio != null)
				return false;
		} else if (!municipio.equals(other.municipio))
			return false;
		if (nombreCentro == null) {
			if (other.nombreCentro != null)
				return false;
		} else if (!nombreCentro.equals(other.nombreCentro))
			return false;
		if (nombreCorto == null) {
			if (other.nombreCorto != null)
				return false;
		} else if (!nombreCorto.equals(other.nombreCorto))
			return false;
		if (nombreLargo == null) {
			if (other.nombreLargo != null)
				return false;
		} else if (!nombreLargo.equals(other.nombreLargo))
			return false;
		if (nombreProvincia == null) {
			if (other.nombreProvincia != null)
				return false;
		} else if (!nombreProvincia.equals(other.nombreProvincia))
			return false;
		if (tipoCentro == null) {
			if (other.tipoCentro != null)
				return false;
		} else if (!tipoCentro.equals(other.tipoCentro))
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
