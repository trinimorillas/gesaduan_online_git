package es.mercadona.gesaduan.dto.declaracionesdevalorapi.getvdsumary.v2.restfull;

import java.io.Serializable;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class DataVDossierIdsDTO extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String dossierNumber;	
	private String dossierYear;
	/**
	 * @return the dossierNumber
	 */
	public String getDossierNumber() {
		return dossierNumber;
	}
	/**
	 * @param dossierNumber the dossierNumber to set
	 */
	public void setDossierNumber(String dossierNumber) {
		this.dossierNumber = dossierNumber;
	}
	/**
	 * @return the dossierYear
	 */
	public String getDossierYear() {
		return dossierYear;
	}
	/**
	 * @param dossierYear the dossierYear to set
	 */
	public void setDossierYear(String dossierYear) {
		this.dossierYear = dossierYear;
	}
	
	
}
