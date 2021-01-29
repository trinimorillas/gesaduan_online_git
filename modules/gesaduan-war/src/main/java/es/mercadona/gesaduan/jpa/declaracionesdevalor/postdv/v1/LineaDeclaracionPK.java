package es.mercadona.gesaduan.jpa.declaracionesdevalor.postdv.v1;

import java.io.Serializable;

public class LineaDeclaracionPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DeclaracionesDeValorPostPK codigoDv;	
	
	private Integer codMerca;

	public DeclaracionesDeValorPostPK getCodigoDv() {
		return codigoDv;
	}

	public void setCodigoDv(DeclaracionesDeValorPostPK codigoDv) {
		this.codigoDv = codigoDv;
	}

	public Integer getCodMerca() {
		return codMerca;
	}

	public void setCodMerca(Integer codMerca) {
		this.codMerca = codMerca;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codMerca == null) ? 0 : codMerca.hashCode());
		result = prime * result + ((codigoDv == null) ? 0 : codigoDv.hashCode());
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
		LineaDeclaracionPK other = (LineaDeclaracionPK) obj;
		if (codMerca == null) {
			if (other.codMerca != null)
				return false;
		} else if (!codMerca.equals(other.codMerca))
			return false;
		if (codigoDv == null) {
			if (other.codigoDv != null)
				return false;
		} else if (!codigoDv.equals(other.codigoDv))
			return false;
		return true;
	}

	public LineaDeclaracionPK() {
		super();
	}

	public LineaDeclaracionPK(DeclaracionesDeValorPostPK codigoDv, Integer codMerca) {
		super();
		this.codigoDv = codigoDv;
		this.codMerca = codMerca;
	}

	@Override
	public String toString() {
		return "LineaDeclaracionPK [codigoDv=" + codigoDv + ", codMerca=" + codMerca + "]";
	}


	
}



