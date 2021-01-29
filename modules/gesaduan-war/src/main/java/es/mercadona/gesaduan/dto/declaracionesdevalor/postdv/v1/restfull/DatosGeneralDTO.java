package es.mercadona.gesaduan.dto.declaracionesdevalor.postdv.v1.restfull;


import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class DatosGeneralDTO extends AbstractDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DeclaracionDeValorDTO declaracionDeValor;
	
	public DeclaracionDeValorDTO getDeclaracionDeValor() {
		return declaracionDeValor;
	}
	public void setDeclaracionDeValor(DeclaracionDeValorDTO declaracionDeValor) {
		this.declaracionDeValor = declaracionDeValor;
	}

}
