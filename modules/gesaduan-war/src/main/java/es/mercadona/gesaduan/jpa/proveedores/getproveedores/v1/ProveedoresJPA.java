package es.mercadona.gesaduan.jpa.proveedores.getproveedores.v1;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "D_PROVEEDOR_R")
public class ProveedoresJPA implements Serializable{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	
	@Id  
	@Column(name="COD_N_PROVEEDOR")  
	private Long codProveedor;
	
	@Column(name="COD_N_LEGACY_PROVEEDOR")
	private Integer codPublico;
	
	@Column(name="MCA_AGENTE_ADUANA")
	private String mcaAgente;
	   
	@Column(name="TXT_RAZON_SOCIAL")  
	private String razonSocial;
	
	@Column(name="TXT_NOMBRE_CALLE")
	private String nombreCalle;
	   
	@Column(name="TXT_NUMERO_CALLE")  
	private String numeroCalle;
	
	@Column(name="TXT_PORTAL")
	private String portal;
	   
	@Column(name="TXT_PISO")  
	private String piso;
	
	@Column(name="TXT_URBANIZACION_POLIGONO")
	private String urbPoligono;
	   
	@Column(name="TXT_LOCALIDAD")  
	private String localidad;
	
	@Column(name="TXT_DISTRITO")
	private String distrito;
	   
	@Column(name="TXT_CODIGO_POSTAL")  
	private String codigoPostal;
	
	@Column(name="COD_V_PROVINCIA")  
	private String codigoProvincia;

	@Column(name="COD_N_PAIS")
	private Integer codigoPais;
	
	@Column(name="MCA_ACTIVO_CSM")
	private String mcaActivoCsm;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "FEC_D_ACTIVACION", nullable = false)
	private Date fechaActivacion;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "FEC_D_CREACION")
	private Date fechaCreacion;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "FEC_D_MODIFICACION", insertable=false)
	private Date fechaModificacion;
	
	@Column(name = "COD_V_APLICACION")
	private String codigoAplicacion;
	
	@Column(name = "COD_V_USUARIO_CREACION")
	private String codigoCreacion;
	
	@Column(name = "COD_V_USUARIO_MODIFICACION")
	private String codigoModificacion;
	
	@Column(name = "MCA_NAVIERA")
	private String mcaNaviera;
	
	@Column(name = "MCA_TRANSPORTISTA")
	private String mcaTransportista;

	public Long getCodProveedor() {
		return codProveedor;
	}

	public void setCodProveedor(Long codProveedor) {
		this.codProveedor = codProveedor;
	}

	public Integer getCodPublico() {
		return codPublico;
	}

	public void setCodPublico(Integer codPublico) {
		this.codPublico = codPublico;
	}

	public String getMcaAgente() {
		return mcaAgente;
	}

	public void setMcaAgente(String mcaAgente) {
		this.mcaAgente = mcaAgente;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getNombreCalle() {
		return nombreCalle;
	}

	public void setNombreCalle(String nombreCalle) {
		this.nombreCalle = nombreCalle;
	}

	public String getNumeroCalle() {
		return numeroCalle;
	}

	public void setNumeroCalle(String numeroCalle) {
		this.numeroCalle = numeroCalle;
	}

	public String getPortal() {
		return portal;
	}

	public void setPortal(String portal) {
		this.portal = portal;
	}

	public String getPiso() {
		return piso;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}

	public String getUrbPoligono() {
		return urbPoligono;
	}

	public void setUrbPoligono(String urbPoligono) {
		this.urbPoligono = urbPoligono;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getDistrito() {
		return distrito;
	}

	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getCodigoProvincia() {
		return codigoProvincia;
	}

	public void setCodigoProvincia(String codigoProvincia) {
		this.codigoProvincia = codigoProvincia;
	}

	public Integer getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(Integer codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getMcaActivoCsm() {
		return mcaActivoCsm;
	}

	public void setMcaActivoCsm(String mcaActivoCsm) {
		this.mcaActivoCsm = mcaActivoCsm;
	}

	public Date getFechaActivacion() {
		return fechaActivacion;
	}

	public void setFechaActivacion(Date fechaActivacion) {
		this.fechaActivacion = fechaActivacion;
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

	public String getCodigoCreacion() {
		return codigoCreacion;
	}

	public void setCodigoCreacion(String codigoCreacion) {
		this.codigoCreacion = codigoCreacion;
	}

	public String getCodigoModificacion() {
		return codigoModificacion;
	}

	public void setCodigoModificacion(String codigoModificacion) {
		this.codigoModificacion = codigoModificacion;
	}

	public String getMcaNaviera() {
		return mcaNaviera;
	}

	public void setMcaNaviera(String mcaNaviera) {
		this.mcaNaviera = mcaNaviera;
	}

	public String getMcaTransportista() {
		return mcaTransportista;
	}

	public void setMcaTransportista(String mcaTransportista) {
		this.mcaTransportista = mcaTransportista;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codProveedor == null) ? 0 : codProveedor.hashCode());
		result = prime * result + ((codPublico == null) ? 0 : codPublico.hashCode());
		result = prime * result + ((codigoAplicacion == null) ? 0 : codigoAplicacion.hashCode());
		result = prime * result + ((codigoCreacion == null) ? 0 : codigoCreacion.hashCode());
		result = prime * result + ((codigoModificacion == null) ? 0 : codigoModificacion.hashCode());
		result = prime * result + ((codigoPais == null) ? 0 : codigoPais.hashCode());
		result = prime * result + ((codigoPostal == null) ? 0 : codigoPostal.hashCode());
		result = prime * result + ((codigoProvincia == null) ? 0 : codigoProvincia.hashCode());
		result = prime * result + ((distrito == null) ? 0 : distrito.hashCode());
		result = prime * result + ((fechaActivacion == null) ? 0 : fechaActivacion.hashCode());
		result = prime * result + ((fechaCreacion == null) ? 0 : fechaCreacion.hashCode());
		result = prime * result + ((fechaModificacion == null) ? 0 : fechaModificacion.hashCode());
		result = prime * result + ((localidad == null) ? 0 : localidad.hashCode());
		result = prime * result + ((mcaActivoCsm == null) ? 0 : mcaActivoCsm.hashCode());
		result = prime * result + ((mcaAgente == null) ? 0 : mcaAgente.hashCode());
		result = prime * result + ((mcaNaviera == null) ? 0 : mcaNaviera.hashCode());
		result = prime * result + ((mcaTransportista == null) ? 0 : mcaTransportista.hashCode());
		result = prime * result + ((nombreCalle == null) ? 0 : nombreCalle.hashCode());
		result = prime * result + ((numeroCalle == null) ? 0 : numeroCalle.hashCode());
		result = prime * result + ((piso == null) ? 0 : piso.hashCode());
		result = prime * result + ((portal == null) ? 0 : portal.hashCode());
		result = prime * result + ((razonSocial == null) ? 0 : razonSocial.hashCode());
		result = prime * result + ((urbPoligono == null) ? 0 : urbPoligono.hashCode());
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
		ProveedoresJPA other = (ProveedoresJPA) obj;
		if (codProveedor == null) {
			if (other.codProveedor != null)
				return false;
		} else if (!codProveedor.equals(other.codProveedor))
			return false;
		if (codPublico == null) {
			if (other.codPublico != null)
				return false;
		} else if (!codPublico.equals(other.codPublico))
			return false;
		if (codigoAplicacion == null) {
			if (other.codigoAplicacion != null)
				return false;
		} else if (!codigoAplicacion.equals(other.codigoAplicacion))
			return false;
		if (codigoCreacion == null) {
			if (other.codigoCreacion != null)
				return false;
		} else if (!codigoCreacion.equals(other.codigoCreacion))
			return false;
		if (codigoModificacion == null) {
			if (other.codigoModificacion != null)
				return false;
		} else if (!codigoModificacion.equals(other.codigoModificacion))
			return false;
		if (codigoPais == null) {
			if (other.codigoPais != null)
				return false;
		} else if (!codigoPais.equals(other.codigoPais))
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
		if (distrito == null) {
			if (other.distrito != null)
				return false;
		} else if (!distrito.equals(other.distrito))
			return false;
		if (fechaActivacion == null) {
			if (other.fechaActivacion != null)
				return false;
		} else if (!fechaActivacion.equals(other.fechaActivacion))
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
		if (localidad == null) {
			if (other.localidad != null)
				return false;
		} else if (!localidad.equals(other.localidad))
			return false;
		if (mcaActivoCsm == null) {
			if (other.mcaActivoCsm != null)
				return false;
		} else if (!mcaActivoCsm.equals(other.mcaActivoCsm))
			return false;
		if (mcaAgente == null) {
			if (other.mcaAgente != null)
				return false;
		} else if (!mcaAgente.equals(other.mcaAgente))
			return false;
		if (mcaNaviera == null) {
			if (other.mcaNaviera != null)
				return false;
		} else if (!mcaNaviera.equals(other.mcaNaviera))
			return false;
		if (mcaTransportista == null) {
			if (other.mcaTransportista != null)
				return false;
		} else if (!mcaTransportista.equals(other.mcaTransportista))
			return false;
		if (nombreCalle == null) {
			if (other.nombreCalle != null)
				return false;
		} else if (!nombreCalle.equals(other.nombreCalle))
			return false;
		if (numeroCalle == null) {
			if (other.numeroCalle != null)
				return false;
		} else if (!numeroCalle.equals(other.numeroCalle))
			return false;
		if (piso == null) {
			if (other.piso != null)
				return false;
		} else if (!piso.equals(other.piso))
			return false;
		if (portal == null) {
			if (other.portal != null)
				return false;
		} else if (!portal.equals(other.portal))
			return false;
		if (razonSocial == null) {
			if (other.razonSocial != null)
				return false;
		} else if (!razonSocial.equals(other.razonSocial))
			return false;
		if (urbPoligono == null) {
			if (other.urbPoligono != null)
				return false;
		} else if (!urbPoligono.equals(other.urbPoligono))
			return false;
		return true;
	}
	
}
