package es.mercadona.gesaduan.jpa.dosier;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

@Embeddable
public class DosierPkJPA implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "NUM_DOSIER")
	private Long numDosier;
	
	@Column(name="NUM_ANYO")
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numDosier == null) ? 0 : numDosier.hashCode());
		result = prime * result + ((anyoDosier == null) ? 0 : anyoDosier.hashCode());
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
		DosierPkJPA other = (DosierPkJPA) obj;
		if (numDosier == null) {
			if (other.numDosier != null)
				return false;
		} else if (!numDosier.equals(other.numDosier))
			return false;
		if (anyoDosier == null) {
			if (other.anyoDosier != null)
				return false;
		} else if (!anyoDosier.equals(other.anyoDosier))
			return false;
		return true;
	}
	
}
