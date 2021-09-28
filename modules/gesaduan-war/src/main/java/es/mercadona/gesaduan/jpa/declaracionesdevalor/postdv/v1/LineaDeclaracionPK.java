package es.mercadona.gesaduan.jpa.declaracionesdevalor.postdv.v1;

import java.io.Serializable;

public class LineaDeclaracionPK implements Serializable {

	private static final long serialVersionUID = 1L;
	private DeclaracionesDeValorPostPK codigoDv;
	private Long numLinea;

	public DeclaracionesDeValorPostPK getCodigoDv() {
		return codigoDv;
	}

	public void setCodigoDv(DeclaracionesDeValorPostPK codigoDv) {
		this.codigoDv = codigoDv;
	}

	/**
	 * @return the lineNumber
	 */
	public Long getNumLinea() {
		return numLinea;
	}

	/**
	 * @param lineNumber the lineNumber to set
	 */
	public void setNumLinea(Long numLinea) {
		this.numLinea = numLinea;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoDv == null) ? 0 : codigoDv.hashCode());
		result = prime * result + ((numLinea == null) ? 0 : numLinea.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		LineaDeclaracionPK other = (LineaDeclaracionPK) obj;
		if (codigoDv == null) {
			if (other.codigoDv != null) {
				return false;
			}
		} else if (!codigoDv.equals(other.codigoDv)) {
			return false;
		}
		if (numLinea == null) {
			if (other.numLinea != null) {
				return false;
			}
		} else if (!numLinea.equals(other.numLinea)) {
			return false;
		}
		return true;
	}

	public LineaDeclaracionPK() {
		super();
	}

	public LineaDeclaracionPK(DeclaracionesDeValorPostPK codigoDv, Long lineNumber) {
		super();
		this.codigoDv = codigoDv;
		this.numLinea = lineNumber;
	}

	@Override
	public String toString() {
		return "LineaDeclaracionPK [codigoDv=" + codigoDv + ", numLinea=" + numLinea + "]";
	}

}