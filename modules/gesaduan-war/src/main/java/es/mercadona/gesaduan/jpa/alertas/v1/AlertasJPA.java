package es.mercadona.gesaduan.jpa.alertas.v1;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "D_ALERTA")
public class AlertasJPA implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id  
	@Column(name="COD_N_ALERTA")  
	private Integer codigoAlerta;
	   
	@Column(name="COD_V_TIPO_ELEMENTO")  
	private String tipoElemento;
	
	@Column(name="TXT_DESCRIPCION")
	private String descripcion;
	
	@Column(name="TXT_PROPUESTA_RESOLUCION")
	private String propuestaResolucion;
	
	@Column(name="MCA_ENLACE_PANTALLA", length=1)
	private String enlacePantalla;
	
	@Column(name="MCA_ACTIVA", length=1)
	private String esActiva;
	
	@Column(name="FEC_D_CREACION")  
	private Date fechaCreacion;
	
	@Column(name="FEC_D_MODIFICACION")
	private Date fechaModificacion;
	
	@Column(name="COD_V_APLICACION")
	private String codAplicacion;
	
	@Column(name="COD_V_USUARIO_CREACION")
	private String codUsuarioCreacion;
	
	@Column(name="COD_V_USUARIO_MODIFICACION")
	private String codUsuarioModificacion;

	public Integer getCodigoAlerta() {
		return codigoAlerta;
	}

	public void setCodigoAlerta(Integer codigoAlerta) {
		this.codigoAlerta = codigoAlerta;
	}

	public String getTipoElemento() {
		return tipoElemento;
	}

	public void setTipoElemento(String tipoElemento) {
		this.tipoElemento = tipoElemento;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getPropuestaResolucion() {
		return propuestaResolucion;
	}

	public void setPropuestaResolucion(String propuestaResolucion) {
		this.propuestaResolucion = propuestaResolucion;
	}

	public String getEnlacePantalla() {
		return enlacePantalla;
	}

	public void setEnlacePantalla(String enlacePantalla) {
		this.enlacePantalla = enlacePantalla;
	}

	public String getEsActiva() {
		return esActiva;
	}

	public void setEsActiva(String esActiva) {
		this.esActiva = esActiva;
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

	public String getCodUsuarioCreacion() {
		return codUsuarioCreacion;
	}

	public void setCodUsuarioCreacion(String codUsuarioCreacion) {
		this.codUsuarioCreacion = codUsuarioCreacion;
	}

	public String getCodUsuarioModificacion() {
		return codUsuarioModificacion;
	}

	public void setCodUsuarioModificacion(String codUsuarioModificacion) {
		this.codUsuarioModificacion = codUsuarioModificacion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codAplicacion == null) ? 0 : codAplicacion.hashCode());
		result = prime * result + ((codUsuarioCreacion == null) ? 0 : codUsuarioCreacion.hashCode());
		result = prime * result + ((codUsuarioModificacion == null) ? 0 : codUsuarioModificacion.hashCode());
		result = prime * result + ((codigoAlerta == null) ? 0 : codigoAlerta.hashCode());
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((enlacePantalla == null) ? 0 : enlacePantalla.hashCode());
		result = prime * result + ((esActiva == null) ? 0 : esActiva.hashCode());
		result = prime * result + ((fechaCreacion == null) ? 0 : fechaCreacion.hashCode());
		result = prime * result + ((fechaModificacion == null) ? 0 : fechaModificacion.hashCode());
		result = prime * result + ((propuestaResolucion == null) ? 0 : propuestaResolucion.hashCode());
		result = prime * result + ((tipoElemento == null) ? 0 : tipoElemento.hashCode());
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
		AlertasJPA other = (AlertasJPA) obj;
		if (codAplicacion == null) {
			if (other.codAplicacion != null)
				return false;
		} else if (!codAplicacion.equals(other.codAplicacion))
			return false;
		if (codUsuarioCreacion == null) {
			if (other.codUsuarioCreacion != null)
				return false;
		} else if (!codUsuarioCreacion.equals(other.codUsuarioCreacion))
			return false;
		if (codUsuarioModificacion == null) {
			if (other.codUsuarioModificacion != null)
				return false;
		} else if (!codUsuarioModificacion.equals(other.codUsuarioModificacion))
			return false;
		if (codigoAlerta == null) {
			if (other.codigoAlerta != null)
				return false;
		} else if (!codigoAlerta.equals(other.codigoAlerta))
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (enlacePantalla == null) {
			if (other.enlacePantalla != null)
				return false;
		} else if (!enlacePantalla.equals(other.enlacePantalla))
			return false;
		if (esActiva == null) {
			if (other.esActiva != null)
				return false;
		} else if (!esActiva.equals(other.esActiva))
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
		if (propuestaResolucion == null) {
			if (other.propuestaResolucion != null)
				return false;
		} else if (!propuestaResolucion.equals(other.propuestaResolucion))
			return false;
		if (tipoElemento == null) {
			if (other.tipoElemento != null)
				return false;
		} else if (!tipoElemento.equals(other.tipoElemento))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AlertasJPA [codigoAlerta=" + codigoAlerta + ", tipoElemento=" + tipoElemento + ", descripcion="
				+ descripcion + ", propuestaResolucion=" + propuestaResolucion + ", enlacePantalla=" + enlacePantalla
				+ ", esActiva=" + esActiva + ", fechaCreacion=" + fechaCreacion + ", fechaModificacion="
				+ fechaModificacion + ", codAplicacion=" + codAplicacion + ", codUsuarioCreacion=" + codUsuarioCreacion
				+ ", codUsuarioModificacion=" + codUsuarioModificacion + "]";
	}
	
	

}
