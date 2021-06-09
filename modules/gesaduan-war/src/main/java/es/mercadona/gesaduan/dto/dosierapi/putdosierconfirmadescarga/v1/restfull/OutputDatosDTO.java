package es.mercadona.gesaduan.dto.dosierapi.putdosierconfirmadescarga.v1.restfull;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class OutputDatosDTO extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long numDosier;
	private Integer anyoDosier;

	/**
	 * @return the numDosier
	 */
	public Long getNumDosier() {
		return numDosier;
	}

	/**
	 * @param numDosier the numDosier to set
	 */
	public void setNumDosier(Long numDosier) {
		this.numDosier = numDosier;
	}

	/**
	 * @return the anyoDosier
	 */
	public Integer getAnyoDosier() {
		return anyoDosier;
	}

	/**
	 * @param anyoDosier the anyoDosier to set
	 */
	public void setAnyoDosier(Integer anyoDosier) {
		this.anyoDosier = anyoDosier;
	}

}
