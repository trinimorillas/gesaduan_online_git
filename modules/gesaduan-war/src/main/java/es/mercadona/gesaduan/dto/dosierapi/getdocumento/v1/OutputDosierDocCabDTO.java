package es.mercadona.gesaduan.dto.dosierapi.getdocumento.v1;

import java.io.Serializable;
import java.util.List;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;
import es.mercadona.gesaduan.dto.declaracionesdevalorapi.getdvdocumento.v1.OutputDeclaracionesDeValorDocCabDTO;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class OutputDosierDocCabDTO extends AbstractDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String codigoDosier;
	private String anyoDosier;
	private String fechaDosier;
	private String tipoInforme;
	private byte[] ficheroPDF;
	private byte[] ficheroCSV;		
	private List<OutputDeclaracionesDeValorDocCabDTO> declaraciones;	
	private JRBeanCollectionDataSource declaracionesDataSource;
	
	/**
	 * @return the codigoDosier
	 */
	public String getCodigoDosier() {
		return codigoDosier;
	}
	/**
	 * @param codigoDosier the codigoDosier to set
	 */
	public void setCodigoDosier(String codigoDosier) {
		this.codigoDosier = codigoDosier;
	}
	/**
	 * @return the anyoDosier
	 */
	public String getAnyoDosier() {
		return anyoDosier;
	}
	/**
	 * @param anyoDosier the anyoDosier to set
	 */
	public void setAnyoDosier(String anyoDosier) {
		this.anyoDosier = anyoDosier;
	}
	/**
	 * @return the fechaDosier
	 */
	public String getFechaDosier() {
		return fechaDosier;
	}
	/**
	 * @param fechaDosier the fechaDosier to set
	 */
	public void setFechaDosier(String fechaDosier) {
		this.fechaDosier = fechaDosier;
	}
	/**
	 * @return the tipoInforme
	 */
	public String getTipoInforme() {
		return tipoInforme;
	}
	/**
	 * @param tipoInforme the tipoInforme to set
	 */
	public void setTipoInforme(String tipoInforme) {
		this.tipoInforme = tipoInforme;
	}
	/**
	 * @return the ficheroPDF
	 */
	public byte[] getFicheroPDF() {
		return ficheroPDF;
	}
	/**
	 * @param ficheroPDF the ficheroPDF to set
	 */
	public void setFicheroPDF(byte[] ficheroPDF) {
		this.ficheroPDF = ficheroPDF;
	}
	/**
	 * @return the ficheroCSV
	 */
	public byte[] getFicheroCSV() {
		return ficheroCSV;
	}
	/**
	 * @param ficheroCSV the ficheroCSV to set
	 */
	public void setFicheroCSV(byte[] ficheroCSV) {
		this.ficheroCSV = ficheroCSV;
	}
	/**
	 * @return the declaraciones
	 */
	public List<OutputDeclaracionesDeValorDocCabDTO> getDeclaraciones() {
		return declaraciones;
	}
	/**
	 * @param declaraciones the declaraciones to set
	 */
	public void setDeclaraciones(List<OutputDeclaracionesDeValorDocCabDTO> declaraciones) {
		this.declaraciones = declaraciones;
	}
	/**
	 * @return the declaracionesDataSource
	 */
	public JRBeanCollectionDataSource getDeclaracionesDataSource() {
		return declaracionesDataSource;
	}
	/**
	 * @param declaracionesDataSource the declaracionesDataSource to set
	 */
	public void setDeclaracionesDataSource(JRBeanCollectionDataSource declaracionesDataSource) {
		this.declaracionesDataSource = declaracionesDataSource;
	}
	
	
}
