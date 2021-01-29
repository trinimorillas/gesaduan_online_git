package es.mercadona.gesaduan.jpa.expediciones.v1.postexpediciones;

import java.io.Serializable;
import java.util.Date;

public class ExpedicionesCabeceraPK implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	private String codVExpedicion;
	private Date fechaAlbaran;
	
	
	public String getCodVExpedicion() {
		return codVExpedicion;
	}
	public void setCodVExpedicion(String codVExpedicion) {
		this.codVExpedicion = codVExpedicion;
	}
	public Date getFechaAlbaran() {
		return fechaAlbaran;
	}
	public void setFechaAlbaran(Date fechaAlbaran) {
		this.fechaAlbaran = fechaAlbaran;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codVExpedicion == null) ? 0 : codVExpedicion.hashCode());
		result = prime * result + ((fechaAlbaran == null) ? 0 : fechaAlbaran.hashCode());
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
		ExpedicionesCabeceraPK other = (ExpedicionesCabeceraPK) obj;
		if (codVExpedicion == null) {
			if (other.codVExpedicion != null)
				return false;
		} else if (!codVExpedicion.equals(other.codVExpedicion))
			return false;
		if (fechaAlbaran == null) {
			if (other.fechaAlbaran != null)
				return false;
		} else if (!fechaAlbaran.equals(other.fechaAlbaran))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ExpedicionesCabeceraPK [codVExpedicion=" + codVExpedicion + ", fechaAlbaran=" + fechaAlbaran + "]";
	}
	public ExpedicionesCabeceraPK(String codVExpedicion, Date fechaAlbaran) {
		super();
		this.codVExpedicion = codVExpedicion;
		this.fechaAlbaran = fechaAlbaran;
	}
	public ExpedicionesCabeceraPK() {
		super();
		
	}
	
		

}
