package es.mercadona.gesaduan.dto.declaracionesdevalor.postdv.v1.restfull;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class LineaDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
	private Integer codigoPublico;
	private Long codigoTaric;
	private String codigoRea;
	private String codigoEan13;
	private String denominacionProducto;
	private String nombreAlternativo;
	private String formatoVentaEstandar;
	private String marca;
	private String descFormatoVentaAlternativo;
	private String codigoTipoBulto;
	private String nombreTipoBulto;
	private Integer numDeBultos;
	private Double pesoNetoLinea;
	private Double pesoBrutoLinea;
	private Double volumenUnidad;
	private Double cantidadFormato;
	private Double precioUnidad;
	private Double importeTotal;
	private Double gradoAlcohol;
	private Double gradoPlato;
	private String paisOrigen;
	private boolean esListoParaComer;
	private String marcaError;

	/**
	 * @return the codigoPublico
	 */
	public Integer getCodigoPublico() {
		return codigoPublico;
	}

	/**
	 * @param codigoPublico the codigoPublico to set
	 */
	public void setCodigoPublico(Integer codigoPublico) {
		this.codigoPublico = codigoPublico;
	}

	/**
	 * @return the codigoTaric
	 */
	public Long getCodigoTaric() {
		return codigoTaric;
	}

	/**
	 * @param codigoTaric the codigoTaric to set
	 */
	public void setCodigoTaric(Long codigoTaric) {
		this.codigoTaric = codigoTaric;
	}

	/**
	 * @return the codigoRea
	 */
	public String getCodigoRea() {
		return codigoRea;
	}

	/**
	 * @param codigoRea the codigoRea to set
	 */
	public void setCodigoRea(String codigoRea) {
		this.codigoRea = codigoRea;
	}

	/**
	 * @return the codigoEan13
	 */
	public String getCodigoEan13() {
		return codigoEan13;
	}

	/**
	 * @param codigoEan13 the codigoEan13 to set
	 */
	public void setCodigoEan13(String codigoEan13) {
		this.codigoEan13 = codigoEan13;
	}

	/**
	 * @return the nombreAlternativo
	 */
	public String getNombreAlternativo() {
		return nombreAlternativo;
	}

	/**
	 * @param nombreAlternativo the nombreAlternativo to set
	 */
	public void setNombreAlternativo(String nombreAlternativo) {
		this.nombreAlternativo = nombreAlternativo;
	}

	/**
	 * @return the marca
	 */
	public String getMarca() {
		return marca;
	}

	/**
	 * @param marca the marca to set
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}

	/**
	 * @return the formatoVentaEstandar
	 */
	public String getFormatoVentaEstandar() {
		return formatoVentaEstandar;
	}

	/**
	 * @param formatoVentaEstandar the formatoVentaEstandar to set
	 */
	public void setFormatoVentaEstandar(String formatoVentaEstandar) {
		this.formatoVentaEstandar = formatoVentaEstandar;
	}

	/**
	 * @return the denominacionProducto
	 */
	public String getDenominacionProducto() {
		return denominacionProducto;
	}

	/**
	 * @param denominacionProducto the denominacionProducto to set
	 */
	public void setDenominacionProducto(String denominacionProducto) {
		this.denominacionProducto = denominacionProducto;
	}

	/**
	 * @return the descFormatoVentaAlternativo
	 */
	public String getDescFormatoVentaAlternativo() {
		return descFormatoVentaAlternativo;
	}

	/**
	 * @param descFormatoVentaAlternativo the descFormatoVentaAlternativo to set
	 */
	public void setDescFormatoVentaAlternativo(String descFormatoVentaAlternativo) {
		this.descFormatoVentaAlternativo = descFormatoVentaAlternativo;
	}

	/**
	 * @return the codigoTipoBulto
	 */
	public String getCodigoTipoBulto() {
		return codigoTipoBulto;
	}

	/**
	 * @param codigoTipoBulto the codigoTipoBulto to set
	 */
	public void setCodigoTipoBulto(String codigoTipoBulto) {
		this.codigoTipoBulto = codigoTipoBulto;
	}

	/**
	 * @return the nombreTipoBulto
	 */
	public String getNombreTipoBulto() {
		return nombreTipoBulto;
	}

	/**
	 * @param nombreTipoBulto the nombreTipoBulto to set
	 */
	public void setNombreTipoBulto(String nombreTipoBulto) {
		this.nombreTipoBulto = nombreTipoBulto;
	}

	/**
	 * @return the numeroDeBultos
	 */
	public Integer getNumDeBultos() {
		return numDeBultos;
	}

	/**
	 * @param numeroDeBultos the numeroDeBultos to set
	 */
	public void setNumDeBultos(Integer numDeBultos) {
		this.numDeBultos = numDeBultos;
	}

	/**
	 * @return the pesoNetoLinea
	 */
	public Double getPesoNetoLinea() {
		return pesoNetoLinea;
	}

	/**
	 * @param pesoNetoLinea the pesoNetoLinea to set
	 */
	public void setPesoNetoLinea(Double pesoNetoLinea) {
		this.pesoNetoLinea = pesoNetoLinea;
	}

	/**
	 * @return the pesoBrutoLinea
	 */
	public Double getPesoBrutoLinea() {
		return pesoBrutoLinea;
	}

	/**
	 * @param pesoBrutoLinea the pesoBrutoLinea to set
	 */
	public void setPesoBrutoLinea(Double pesoBrutoLinea) {
		this.pesoBrutoLinea = pesoBrutoLinea;
	}

	/**
	 * @return the volumenUnidad
	 */
	public Double getVolumenUnidad() {
		return volumenUnidad;
	}

	/**
	 * @param volumenUnidad the volumenUnidad to set
	 */
	public void setVolumenUnidad(Double volumenUnidad) {
		this.volumenUnidad = volumenUnidad;
	}

	/**
	 * @return the cantidadFormato
	 */
	public Double getCantidadFormato() {
		return cantidadFormato;
	}

	/**
	 * @param cantidadFormato the cantidadFormato to set
	 */
	public void setCantidadFormato(Double cantidadFormato) {
		this.cantidadFormato = cantidadFormato;
	}

	/**
	 * @return the precioUnidad
	 */
	public Double getPrecioUnidad() {
		return precioUnidad;
	}

	/**
	 * @param precioUnidad the precioUnidad to set
	 */
	public void setPrecioUnidad(Double precioUnidad) {
		this.precioUnidad = precioUnidad;
	}

	/**
	 * @return the importeTotal
	 */
	public Double getImporteTotal() {
		return importeTotal;
	}

	/**
	 * @param importeTotal the importeTotal to set
	 */
	public void setImporteTotal(Double importeTotal) {
		this.importeTotal = importeTotal;
	}

	/**
	 * @return the gradoAlcohol
	 */
	public Double getGradoAlcohol() {
		return gradoAlcohol;
	}

	/**
	 * @param gradoAlcohol the gradoAlcohol to set
	 */
	public void setGradoAlcohol(Double gradoAlcohol) {
		this.gradoAlcohol = gradoAlcohol;
	}

	/**
	 * @return the gradoPlato
	 */
	public Double getGradoPlato() {
		return gradoPlato;
	}

	/**
	 * @param gradoPlato the gradoPlato to set
	 */
	public void setGradoPlato(Double gradoPlato) {
		this.gradoPlato = gradoPlato;
	}

	/**
	 * @return the paisOrigen
	 */
	public String getPaisOrigen() {
		return paisOrigen;
	}

	/**
	 * @param paisOrigen the paisOrigen to set
	 */
	public void setPaisOrigen(String paisOrigen) {
		this.paisOrigen = paisOrigen;
	}

	/**
	 * @return the esListoParaComer
	 */
	public boolean isEsListoParaComer() {
		return esListoParaComer;
	}

	/**
	 * @param esListoParaComer the esListoParaComer to set
	 */
	public void setEsListoParaComer(boolean esListoParaComer) {
		this.esListoParaComer = esListoParaComer;
	}

	/**
	 * @return the marcaError
	 */
	public String getMarcaError() {
		return marcaError;
	}

	/**
	 * @param marcaError the marcaError to set
	 */
	public void setMarcaError(String marcaError) {
		this.marcaError = marcaError;
	}

}