package es.mercadona.gesaduan.jpa.declaracionesdevalor;

import java.io.Serializable;


public class DeclaracionesDeValorPK implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer codDeclaracionValor;	
	private Integer anyo; 
	private Integer version;


	public Integer getCodDeclaracionValor() {
		return codDeclaracionValor;
	}
	public void setCodDeclaracionValor(Integer codDeclaracionValor) {
		this.codDeclaracionValor = codDeclaracionValor;
	}
	public Integer getAnyo() {
		return anyo;
	}
	public void setAnyo(Integer anyo) {
		this.anyo = anyo;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	
	public DeclaracionesDeValorPK(Integer codDeclaracionValor, Integer anyo, Integer version) {
		super();
		this.codDeclaracionValor = codDeclaracionValor;
		this.anyo = anyo;
		this.version = version;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((anyo == null) ? 0 : anyo.hashCode());
		result = prime * result + ((codDeclaracionValor == null) ? 0 : codDeclaracionValor.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
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
		DeclaracionesDeValorPK other = (DeclaracionesDeValorPK) obj;
		if (anyo == null) {
			if (other.anyo != null)
				return false;
		} else if (!anyo.equals(other.anyo))
			return false;
		if (codDeclaracionValor == null) {
			if (other.codDeclaracionValor != null)
				return false;
		} else if (!codDeclaracionValor.equals(other.codDeclaracionValor))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	}
	
	public DeclaracionesDeValorPK() {
		super();
		
	}
	
	
	
	
}
