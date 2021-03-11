package es.mercadona.gesaduan.dto.dosier.putdosier.v1.restfull;

import java.util.List;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class DatosDosierPutDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
	
	private List<DosierPutDTO> dosier;

	/**
	 * @return the dosier
	 */
	public List<DosierPutDTO> getDosier() {
		return dosier;
	}

	/**
	 * @param dosier the dosier to set
	 */
	public void setDosier(List<DosierPutDTO> dosier) {
		this.dosier = dosier;
	}

	


}