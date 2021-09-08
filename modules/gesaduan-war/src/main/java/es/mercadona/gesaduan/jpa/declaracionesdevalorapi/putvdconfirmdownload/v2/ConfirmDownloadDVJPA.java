package es.mercadona.gesaduan.jpa.declaracionesdevalorapi.putvdconfirmdownload.v2;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "O_DECLARACION_VALOR_CAB")
@IdClass(ConfirmDownloadDVPK.class)
public class ConfirmDownloadDVJPA implements Serializable{
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@Column(name="COD_N_DECLARACION_VALOR")  
	private Integer codDeclaracionValor;
	
	@Id  
	@Column(name="NUM_ANYO")  
	private Integer anyo;
	
	@Id
	@Column(name="COD_N_VERSION")  
	private Integer version;
	
		  
	@Column(name="MCA_DESCARGA")  
	private String mcaDescarga;
	

	@Lob
	@Column(name="SPC_FICHERO_PDF")
	private byte[] ficheroPdf;
	
	@Lob
	@Column(name="SPC_FICHERO_CSV")
	private byte[] ficheroCsv;
	
	
	public Integer getCodDeclaracionValor() {
		return codDeclaracionValor;
	}

	public void setCodDeclaracionValor(Integer codDeclaracionValor) {
		this.codDeclaracionValor = codDeclaracionValor;
	}

	public Integer getAnyo() {
		return anyo;
	}

	public void setAnyo(Integer anyo) {
		this.anyo = anyo;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getMcaDescarga() {
		return mcaDescarga;
	}

	public void setMcaDescarga(String mcaDescarga) {
		this.mcaDescarga = mcaDescarga;
	}

	public byte[] getFicheroPdf() {
		return ficheroPdf;
	}

	public void setFicheroPdf(byte[] ficheroPdf) {
		this.ficheroPdf = ficheroPdf;
	}

	public byte[] getFicheroCsv() {
		return ficheroCsv;
	}

	public void setFicheroCsv(byte[] ficheroCsv) {
		this.ficheroCsv = ficheroCsv;
	}

	
	
	
	
}
