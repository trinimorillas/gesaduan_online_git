package es.mercadona.gesaduan.dto.dosier.putdosier.v1;

import java.util.List;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class InputDatosDosierDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
	
	private List<InputDosierDTO> dosier;
	

	/**
	 * @return the dosier
	 */
	public List<InputDosierDTO> getDosier() {
		return dosier;
	}

	/**
	 * @param dosier the dosier to set
	 */
	public void setDosier(List<InputDosierDTO> dosier) {
		this.dosier = dosier;
	}
	
	
}
