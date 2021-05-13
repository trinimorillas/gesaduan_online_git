package es.mercadona.gesaduan.jpa.declaracionesdevalor.postdv.v1;

import java.io.Serializable;

public class LineaDeclaracionPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DeclaracionesDeValorPostPK codigoDv;	
	private Integer codMerca;
	private String nombreTipoBulto;

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

	/**
	 * @return the nombreTipoBulto
	 */
	public String getNombreTipoBulto() {
		return nombreTipoBulto;
	}

	/**
	 * @param nombreTipoBulto the nombreTipoBulto to set
	 */
	public void setNombreTipoBulto(String nombreTipoBulto) {
		this.nombreTipoBulto = nombreTipoBulto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codMerca == null) ? 0 : codMerca.hashCode());
		result = prime * result + ((codigoDv == null) ? 0 : codigoDv.hashCode());
		result = prime * result + ((nombreTipoBulto == null) ? 0 : nombreTipoBulto.hashCode());
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
		if (nombreTipoBulto == null) {
			if (other.nombreTipoBulto != null)
				return false;
		} else if (!nombreTipoBulto.equals(other.nombreTipoBulto))
			return false;
		return true;
	}

	public LineaDeclaracionPK() {
		super();
	}

	public LineaDeclaracionPK(DeclaracionesDeValorPostPK codigoDv, Integer codMerca, String nombreTipoBulto) {
		super();
		this.codigoDv = codigoDv;
		this.codMerca = codMerca;
		this.nombreTipoBulto = nombreTipoBulto;
	}

	@Override
	public String toString() {
		return "LineaDeclaracionPK [codigoDv=" + codigoDv + ", codMerca=" + codMerca + ", nombreTipoBulto=" + nombreTipoBulto +"]";
	}
	
}