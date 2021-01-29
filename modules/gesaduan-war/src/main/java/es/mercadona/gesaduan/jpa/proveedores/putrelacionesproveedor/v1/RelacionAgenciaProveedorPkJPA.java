package es.mercadona.gesaduan.jpa.proveedores.putrelacionesproveedor.v1;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class RelacionAgenciaProveedorPkJPA implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Column(name = "COD_N_AGENCIA_ADUANA")
    private Long codigoAgencia;
	
	@Column(name = "COD_N_PROVEEDOR")
    private Long codigoProveedor;

    @Temporal(TemporalType.DATE)
	@Column(name = "FEC_D_INICIO")
    private Date fechaInicio;

	public Long getCodigoAgencia() {
		return codigoAgencia;
	}

	public void setCodigoAgencia(Long codigoAgencia) {
		this.codigoAgencia = codigoAgencia;
	}

	public Long getCodigoProveedor() {
		return codigoProveedor;
	}

	public void setCodigoProveedor(Long codigoProveedor) {
		this.codigoProveedor = codigoProveedor;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoAgencia == null) ? 0 : codigoAgencia.hashCode());
		result = prime * result + ((codigoProveedor == null) ? 0 : codigoProveedor.hashCode());
		result = prime * result + ((fechaInicio == null) ? 0 : fechaInicio.hashCode());
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
		RelacionAgenciaProveedorPkJPA other = (RelacionAgenciaProveedorPkJPA) obj;
		if (codigoAgencia == null) {
			if (other.codigoAgencia != null)
				return false;
		} else if (!codigoAgencia.equals(other.codigoAgencia))
			return false;
		if (codigoProveedor == null) {
			if (other.codigoProveedor != null)
				return false;
		} else if (!codigoProveedor.equals(other.codigoProveedor))
			return false;
		if (fechaInicio == null) {
			if (other.fechaInicio != null)
				return false;
		} else if (!fechaInicio.equals(other.fechaInicio))
			return false;
		return true;
	}
    
    

	
}
