package es.mercadona.gesaduan.dto.dosier.putdosier.v1.restfull;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class OutputDosierPutDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;		
	private DatosDosierPutDTO datos;
	
	/**
	 * @return the datos
	 */
	public DatosDosierPutDTO getDatos() {
		return datos;
	}
	/**
	 * @param datos the datos to set
	 */
	public void setDatos(DatosDosierPutDTO datos) {
		this.datos = datos;
	}	
		


}
