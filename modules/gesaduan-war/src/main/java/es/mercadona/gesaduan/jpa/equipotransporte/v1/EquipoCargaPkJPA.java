package es.mercadona.gesaduan.jpa.equipotransporte.v1;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EquipoCargaPkJPA implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "COD_N_EQUIPO")
    private Long codigoEquipo;
	
	@Column(name = "COD_V_CARGA")
    private String codigoCarga;
	
	@Column(name = "COD_V_ALMACEN_ORIGEN")
	private String codigoCentroOrigen;

	public Long getCodigoEquipo() {
		return codigoEquipo; 
	}

	public void setCodigoEquipo(Long codigoEquipo) {
		this.codigoEquipo = codigoEquipo;
	}

	public String getCodigoCarga() {
		return codigoCarga;
	}
 
	public void setCodigoCarga(String codigoCarga) {
		this.codigoCarga = codigoCarga;
	}

	public String getCodigoCentroOrigen() {
		return codigoCentroOrigen;
	}

	public void setCodigoCentroOrigen(String codigoCentroOrigen) {
		this.codigoCentroOrigen = codigoCentroOrigen;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoCarga == null) ? 0 : codigoCarga.hashCode());
		result = prime * result + ((codigoCentroOrigen == null) ? 0 : codigoCentroOrigen.hashCode());
		result = prime * result + ((codigoEquipo == null) ? 0 : codigoEquipo.hashCode());
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
		EquipoCargaPkJPA other = (EquipoCargaPkJPA) obj;
		if (codigoCarga == null) {
			if (other.codigoCarga != null)
				return false;
		} else if (!codigoCarga.equals(other.codigoCarga))
			return false;
		if (codigoCentroOrigen == null) { 
			if (other.codigoCentroOrigen != null)
				return false;
		} else if (!codigoCentroOrigen.equals(other.codigoCentroOrigen))
			return false;
		if (codigoEquipo == null) {
			if (other.codigoEquipo != null)
				return false;
		} else if (!codigoEquipo.equals(other.codigoEquipo))
			return false;
		return true;
	}
	
}
