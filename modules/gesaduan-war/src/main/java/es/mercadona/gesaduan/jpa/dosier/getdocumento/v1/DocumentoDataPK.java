package es.mercadona.gesaduan.jpa.dosier.getdocumento.v1;

import java.io.Serializable;

public class DocumentoDataPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
  
	private Integer numDosier;
	
	private Integer anyo;


	
	/**
	 * @return the numDosier
	 */
	public Integer getNumDosier() {
		return numDosier;
	}
	/**
	 * @param numDosier the numDosier to set
	 */
	public void setNumDosier(Integer numDosier) {
		this.numDosier = numDosier;
	}
	/**
	 * @return the anyo
	 */
	public Integer getAnyo() {
		return anyo;
	}
	/**
	 * @param anyo the anyo to set
	 */
	public void setAnyo(Integer anyo) {
		this.anyo = anyo;
	}
	
	public DocumentoDataPK(Integer numDosier, Integer anyo) {
		super();
		this.numDosier = numDosier;
		this.anyo = anyo;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numDosier == null) ? 0 : numDosier.hashCode());		
		result = prime * result + ((anyo == null) ? 0 : anyo.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DocumentoDataPK other = (DocumentoDataPK) obj;
		if (numDosier == null) {
			if (other.numDosier != null)
				return false;
		} else if (!numDosier.equals(other.numDosier))
			return false;
		if (anyo == null) {
			if (other.anyo != null)
				return false;
		} else if (!anyo.equals(other.anyo))
			return false;
		return true;
	}
	
	
	public DocumentoDataPK() {
		super();
		
	}
	
	
	
	
	
	

}
