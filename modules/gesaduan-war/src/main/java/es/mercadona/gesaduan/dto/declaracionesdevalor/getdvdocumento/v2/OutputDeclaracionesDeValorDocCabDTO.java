package es.mercadona.gesaduan.dto.declaracionesdevalor.getdvdocumento.v2;

import java.io.Serializable;
import java.util.List;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class OutputDeclaracionesDeValorDocCabDTO extends AbstractDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String codigoDeclaracion;
	private String anyoDeclaracion;
	private String versionDeclaracion;
	private String fechaDeclaracion;
	private String numDosier;
	private String anyoDosier;
	private String fechaDosier;		
	private String nombreOrigen;	
	private String provinciaOrigen;
	private String tipoOrigen;
	private String condicionesEntrega;
	private String exportadorNombre;	
	private String exportadorDireccion;	
	private String exportadorCP;
	private String exportadorPoblacion;
	private String exportadorProvincia;
	private String exportadorNIF;
	private String importadorNombre;	
	private String importadorDireccion;	
	private String importadorCP;
	private String importadorPoblacion;
	private String importadorProvincia;
	private String importadorNIF;		
	private String txtInfoREA;	
	private String txtInfoLPC;
	private String txtInfoGeneral;
	private String tipoInforme;
	private byte[] ficheroPDF;
	private byte[] ficheroCSV;	
	private List<OutputDeclaracionesDeValorDocLinDTO> lineas;	
	private JRBeanCollectionDataSource lineasDataSource;
	
	/**
	 * @return the codigoDeclaracion
	 */
	public String getCodigoDeclaracion() {
		return codigoDeclaracion;
	}
	/**
	 * @param codigoDeclaracion the codigoDeclaracion to set
	 */
	public void setCodigoDeclaracion(String codigoDeclaracion) {
		this.codigoDeclaracion = codigoDeclaracion;
	}
	/**
	 * @return the anyoDeclaracion
	 */
	public String getAnyoDeclaracion() {
		return anyoDeclaracion;
	}
	/**
	 * @param anyoDeclaracion the anyoDeclaracion to set
	 */
	public void setAnyoDeclaracion(String anyoDeclaracion) {
		this.anyoDeclaracion = anyoDeclaracion;
	}
	/**
	 * @return the versionDeclaracion
	 */
	public String getVersionDeclaracion() {
		return versionDeclaracion;
	}
	/**
	 * @param versionDeclaracion the versionDeclaracion to set
	 */
	public void setVersionDeclaracion(String versionDeclaracion) {
		this.versionDeclaracion = versionDeclaracion;
	}
	/**
	 * @return the fechaDeclaracion
	 */
	public String getFechaDeclaracion() {
		return fechaDeclaracion;
	}
	/**
	 * @param fechaDeclaracion the fechaDeclaracion to set
	 */
	public void setFechaDeclaracion(String fechaDeclaracion) {
		this.fechaDeclaracion = fechaDeclaracion;
	}
	/**
	 * @return the numDosier
	 */
	public String getNumDosier() {
		return numDosier;
	}
	/**
	 * @param numDosier the numDosier to set
	 */
	public void setNumDosier(String numDosier) {
		this.numDosier = numDosier;
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
	 * @return the nombreOrigen
	 */
	public String getNombreOrigen() {
		return nombreOrigen;
	}
	/**
	 * @param nombreOrigen the nombreOrigen to set
	 */
	public void setNombreOrigen(String nombreOrigen) {
		this.nombreOrigen = nombreOrigen;
	}
	/**
	 * @return the provinciaOrigen
	 */
	public String getProvinciaOrigen() {
		return provinciaOrigen;
	}
	/**
	 * @param provinciaOrigen the provinciaOrigen to set
	 */
	public void setProvinciaOrigen(String provinciaOrigen) {
		this.provinciaOrigen = provinciaOrigen;
	}
	/**
	 * @return the tipoOrigen
	 */
	public String getTipoOrigen() {
		return tipoOrigen;
	}
	/**
	 * @param tipoOrigen the tipoOrigen to set
	 */
	public void setTipoOrigen(String tipoOrigen) {
		this.tipoOrigen = tipoOrigen;
	}
	/**
	 * @return the condicionesEntrega
	 */
	public String getCondicionesEntrega() {
		return condicionesEntrega;
	}
	/**
	 * @param condicionesEntrega the condicionesEntrega to set
	 */
	public void setCondicionesEntrega(String condicionesEntrega) {
		this.condicionesEntrega = condicionesEntrega;
	}
	/**
	 * @return the exportadorNombre
	 */
	public String getExportadorNombre() {
		return exportadorNombre;
	}
	/**
	 * @param exportadorNombre the exportadorNombre to set
	 */
	public void setExportadorNombre(String exportadorNombre) {
		this.exportadorNombre = exportadorNombre;
	}
	/**
	 * @return the exportadorDireccion
	 */
	public String getExportadorDireccion() {
		return exportadorDireccion;
	}
	/**
	 * @param exportadorDireccion the exportadorDireccion to set
	 */
	public void setExportadorDireccion(String exportadorDireccion) {
		this.exportadorDireccion = exportadorDireccion;
	}
	/**
	 * @return the exportadorCP
	 */
	public String getExportadorCP() {
		return exportadorCP;
	}
	/**
	 * @param exportadorCP the exportadorCP to set
	 */
	public void setExportadorCP(String exportadorCP) {
		this.exportadorCP = exportadorCP;
	}
	/**
	 * @return the exportadorPoblacion
	 */
	public String getExportadorPoblacion() {
		return exportadorPoblacion;
	}
	/**
	 * @param exportadorPoblacion the exportadorPoblacion to set
	 */
	public void setExportadorPoblacion(String exportadorPoblacion) {
		this.exportadorPoblacion = exportadorPoblacion;
	}
	/**
	 * @return the exportadorProvincia
	 */
	public String getExportadorProvincia() {
		return exportadorProvincia;
	}
	/**
	 * @param exportadorProvincia the exportadorProvincia to set
	 */
	public void setExportadorProvincia(String exportadorProvincia) {
		this.exportadorProvincia = exportadorProvincia;
	}
	/**
	 * @return the exportadorNIF
	 */
	public String getExportadorNIF() {
		return exportadorNIF;
	}
	/**
	 * @param exportadorNIF the exportadorNIF to set
	 */
	public void setExportadorNIF(String exportadorNIF) {
		this.exportadorNIF = exportadorNIF;
	}
	/**
	 * @return the importadorNombre
	 */
	public String getImportadorNombre() {
		return importadorNombre;
	}
	/**
	 * @param importadorNombre the importadorNombre to set
	 */
	public void setImportadorNombre(String importadorNombre) {
		this.importadorNombre = importadorNombre;
	}
	/**
	 * @return the importadorDireccion
	 */
	public String getImportadorDireccion() {
		return importadorDireccion;
	}
	/**
	 * @param importadorDireccion the importadorDireccion to set
	 */
	public void setImportadorDireccion(String importadorDireccion) {
		this.importadorDireccion = importadorDireccion;
	}
	/**
	 * @return the importadorCP
	 */
	public String getImportadorCP() {
		return importadorCP;
	}
	/**
	 * @param importadorCP the importadorCP to set
	 */
	public void setImportadorCP(String importadorCP) {
		this.importadorCP = importadorCP;
	}
	/**
	 * @return the importadorPoblacion
	 */
	public String getImportadorPoblacion() {
		return importadorPoblacion;
	}
	/**
	 * @param importadorPoblacion the importadorPoblacion to set
	 */
	public void setImportadorPoblacion(String importadorPoblacion) {
		this.importadorPoblacion = importadorPoblacion;
	}
	/**
	 * @return the importadorProvincia
	 */
	public String getImportadorProvincia() {
		return importadorProvincia;
	}
	/**
	 * @param importadorProvincia the importadorProvincia to set
	 */
	public void setImportadorProvincia(String importadorProvincia) {
		this.importadorProvincia = importadorProvincia;
	}
	/**
	 * @return the importadorNIF
	 */
	public String getImportadorNIF() {
		return importadorNIF;
	}
	/**
	 * @param importadorNIF the importadorNIF to set
	 */
	public void setImportadorNIF(String importadorNIF) {
		this.importadorNIF = importadorNIF;
	}
	/**
	 * @return the txtInfoREA
	 */
	public String getTxtInfoREA() {
		return txtInfoREA;
	}
	/**
	 * @param txtInfoREA the txtInfoREA to set
	 */
	public void setTxtInfoREA(String txtInfoREA) {
		this.txtInfoREA = txtInfoREA;
	}
	/**
	 * @return the txtInfoLPC
	 */
	public String getTxtInfoLPC() {
		return txtInfoLPC;
	}
	/**
	 * @param txtInfoLPC the txtInfoLPC to set
	 */
	public void setTxtInfoLPC(String txtInfoLPC) {
		this.txtInfoLPC = txtInfoLPC;
	}
	/**
	 * @return the txtInfoGeneral
	 */
	public String getTxtInfoGeneral() {
		return txtInfoGeneral;
	}
	/**
	 * @param txtInfoGeneral the txtInfoGeneral to set
	 */
	public void setTxtInfoGeneral(String txtInfoGeneral) {
		this.txtInfoGeneral = txtInfoGeneral;
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
	 * @return the lineas
	 */
	public List<OutputDeclaracionesDeValorDocLinDTO> getLineas() {
		return lineas;
	}
	/**
	 * @param lineas the lineas to set
	 */
	public void setLineas(List<OutputDeclaracionesDeValorDocLinDTO> lineas) {
		this.lineas = lineas;
	}
	/**
	 * @return the lineasDataSource
	 */
	public JRBeanCollectionDataSource getLineasDataSource() {
		return lineasDataSource;
	}
	/**
	 * @param lineasDataSource the lineasDataSource to set
	 */
	public void setLineasDataSource(JRBeanCollectionDataSource lineasDataSource) {
		this.lineasDataSource = lineasDataSource;
	}

	

}
