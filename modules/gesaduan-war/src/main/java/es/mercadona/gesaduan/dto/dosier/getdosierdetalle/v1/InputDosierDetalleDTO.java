package es.mercadona.gesaduan.dto.dosier.getdosierdetalle.v1;

import java.io.Serializable;
import java.util.List;

import es.mercadona.gesaduan.dto.common.AbstractDTO;
import es.mercadona.gesaduan.dto.planembarques.getplanembarquedetalle.v1.restfull.SuministroDTO;
import es.mercadona.gesaduan.dto.planembarques.getplanembarquedetalle.v1.restfull.TipoCargaDTO;

public class InputDosierDetalleDTO extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer anyoDosier;
	private Long numDosier;
	private String orden;
	
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
	 * @return the orden
	 */
	public String getOrden() {
		return orden;
	}
	/**
	 * @param orden the orden to set
	 */
	public void setOrden(String orden) {
		this.orden = orden;
	}
	
	
	
}
