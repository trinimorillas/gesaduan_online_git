package es.mercadona.gesaduan.jpa.alertas.v1;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class NotificacionAlertaPkJPA implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "COD_N_ALERTA")
    private Integer codigoAlerta;
	
	@Column(name = "COD_V_ELEMENTO")
    private String codigoElemento;

	public Integer getCodigoAlerta() {
		return codigoAlerta;
	}

	public void setCodigoAlerta(Integer codigoAlerta) {
		this.codigoAlerta = codigoAlerta;
	}

	public String getCodigoElemento() {
		return codigoElemento;
	}

	public void setCodigoElemento(String codigoElemento) {
		this.codigoElemento = codigoElemento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoAlerta == null) ? 0 : codigoAlerta.hashCode());
		result = prime * result + ((codigoElemento == null) ? 0 : codigoElemento.hashCode());
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
		NotificacionAlertaPkJPA other = (NotificacionAlertaPkJPA) obj;
		if (codigoAlerta == null) {
			if (other.codigoAlerta != null)
				return false;
		} else if (!codigoAlerta.equals(other.codigoAlerta))
			return false;
		if (codigoElemento == null) {
			if (other.codigoElemento != null)
				return false;
		} else if (!codigoElemento.equals(other.codigoElemento))
			return false;
		return true;
	}
	
	

}
