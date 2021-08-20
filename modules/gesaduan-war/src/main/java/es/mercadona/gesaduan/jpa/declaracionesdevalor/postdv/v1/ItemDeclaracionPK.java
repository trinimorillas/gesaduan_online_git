package es.mercadona.gesaduan.jpa.declaracionesdevalor.postdv.v1;

import java.io.Serializable;

public class ItemDeclaracionPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private DeclaracionesDeValorPostPK codigoDv;	
	private String codigoEnvase;

	public DeclaracionesDeValorPostPK getCodigoDv() {
		return codigoDv;
	}

	public void setCodigoDv(DeclaracionesDeValorPostPK codigoDv) {
		this.codigoDv = codigoDv;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoEnvase == null) ? 0 : codigoEnvase.hashCode());
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
		ItemDeclaracionPK other = (ItemDeclaracionPK) obj;
		if (codigoEnvase == null) {
			if (other.codigoEnvase != null)
				return false;
		} else if (!codigoEnvase.equals(other.codigoEnvase))
			return false;
		if (codigoDv == null) {
			if (other.codigoDv != null)
				return false;
		} else if (!codigoDv.equals(other.codigoDv))
			return false;
		return true;
	}

	public ItemDeclaracionPK() {
		super();
	}

	public ItemDeclaracionPK(DeclaracionesDeValorPostPK codigoDv, String codigoEnvase, String nombreTipoBultoDV) {
		super();
		this.codigoDv = codigoDv;
		this.codigoEnvase = codigoEnvase;
	}
}