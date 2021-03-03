package es.mercadona.gesaduan.dto.dosier.cambiarestado.v1.resfull;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class DatosCambiarEstadoDTO extends AbstractDTO {

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