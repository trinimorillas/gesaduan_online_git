package es.mercadona.gesaduan.dto.declaracionesdevalor.putdvinddescarga.v1.restfull;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class EstaDescargadoDTO extends AbstractDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean estaDescargada;

	public boolean isEstaDescargada() {
		return estaDescargada;
	}

	public void setEstaDescargada(boolean estaDescargado) {
		this.estaDescargada = estaDescargado;
	}
	
	

}
