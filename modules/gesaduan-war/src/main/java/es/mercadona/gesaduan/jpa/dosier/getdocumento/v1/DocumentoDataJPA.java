package es.mercadona.gesaduan.jpa.dosier.getdocumento.v1;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "D_DOSIER_DOCUMENTO")
@IdClass(DocumentoDataPK.class)
public class DocumentoDataJPA implements Serializable{
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@Column(name="NUM_DOSIER")  
	private Integer numDosier;
	
	@Id  
	@Column(name="NUM_ANYO")  
	private Integer anyo;
		

	@Lob
	@Column(name="SPC_DOCUMENTO_PDF")
	private byte[] ficheroPdf;


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


	/**
	 * @return the ficheroPdf
	 */
	public byte[] getFicheroPdf() {
		return ficheroPdf;
	}


	/**
	 * @param ficheroPdf the ficheroPdf to set
	 */
	public void setFicheroPdf(byte[] ficheroPdf) {
		this.ficheroPdf = ficheroPdf;
	}



	
	
	
}
