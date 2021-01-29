package es.mercadona.gesaduan.dto.declaracionesdevalor.getdvdetalle.v1.restfull;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class DatosDTO extends AbstractDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	DeclaracionesDeValorDTO declaracionDeValor;

	public DeclaracionesDeValorDTO getDeclaracionDeValor() {
		return declaracionDeValor;
	}

	public void setDeclaracionDeValor(DeclaracionesDeValorDTO declaracionDeValor) {
		this.declaracionDeValor = declaracionDeValor;
	}
	
	

}
