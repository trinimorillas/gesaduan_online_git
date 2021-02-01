package es.mercadona.gesaduan.dto.declaracionesdevalor.getdvdetalle.v1.restfull;

import es.mercadona.gesaduan.dto.declaracionesdevalor.AbstractDTO;

public class LineaDTO extends AbstractDTO{

	/**
	 * Pruebas GIT
	 */
	private static final long serialVersionUID = 1L;
	
    private Integer codigoPublico;

    private Long codigoTaric;
    
    private Long codigoTaricProducto;

    private String codigoRea;
    
    private String codigoEan13;
    
    private String denominacionProducto;

    private String nombreAlternativo;
    
    private String formatoVentaEstandar;
    
    private String marca;

    private String descFormatoVentaAlternativo;

    private String nombreTipoBulto;

    private Integer numeroDeBultos;

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
    
	public Long getCodigoTaricProducto() {
		return codigoTaricProducto;
	}

	public void setCodigoTaricProducto(Long codigoTaricProducto) {
		this.codigoTaricProducto = codigoTaricProducto;
	}

	public String getCodigoEan13() {
		return codigoEan13;
	}

	public void setCodigoEan13(String codigoEan13) {
		this.codigoEan13 = codigoEan13;
	}

	public String getDenominacionProducto() {
		return denominacionProducto;
	}

	public void setDenominacionProducto(String denominacionProducto) {
		this.denominacionProducto = denominacionProducto;
	}

	public String getFormatoVentaEstandar() {
		return formatoVentaEstandar;
	}

	public void setFormatoVentaEstandar(String formatoVentaEstandar) {
		this.formatoVentaEstandar = formatoVentaEstandar;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Integer getCodigoPublico() {
		return codigoPublico;
	}

	public void setCodigoPublico(Integer codigoPublico) {
		this.codigoPublico = codigoPublico;
	}


	public Long getCodigoTaric() {
		return codigoTaric;
	}

	public void setCodigoTaric(Long codigoTaric) {
		this.codigoTaric = codigoTaric;
	}

	public String getCodigoRea() {
		return codigoRea;
	}

	public void setCodigoRea(String codigoRea) {
		this.codigoRea = codigoRea;
	}

	public String getNombreAlternativo() {
		return nombreAlternativo;
	}

	public void setNombreAlternativo(String nombreAlternativo) {
		this.nombreAlternativo = nombreAlternativo;
	}

	public String getDescFormatoVentaAlternativo() {
		return descFormatoVentaAlternativo;
	}

	public void setDescFormatoVentaAlternativo(String descFormatoVentaAlternativo) {
		this.descFormatoVentaAlternativo = descFormatoVentaAlternativo;
	}

	public String getNombreTipoBulto() {
		return nombreTipoBulto;
	}

	public void setNombreTipoBulto(String nombreTipoBulto) {
		this.nombreTipoBulto = nombreTipoBulto;
	}

	public Integer getNumeroDeBultos() {
		return numeroDeBultos;
	}

	public void setNumeroDeBultos(Integer numeroDeBultos) {
		this.numeroDeBultos = numeroDeBultos;
	}

	public Double getPesoNetoLinea() {
		return pesoNetoLinea;
	}

	public void setPesoNetoLinea(Double pesoNetoLinea) {
		this.pesoNetoLinea = pesoNetoLinea;
	}

	public Double getPesoBrutoLinea() {
		return pesoBrutoLinea;
	}

	public void setPesoBrutoLinea(Double pesoBrutoLinea) {
		this.pesoBrutoLinea = pesoBrutoLinea;
	}

	public Double getVolumenUnidad() {
		return volumenUnidad;
	}

	public void setVolumenUnidad(Double volumenUnidad) {
		this.volumenUnidad = volumenUnidad;
	}

	public Double getCantidadFormato() {
		return cantidadFormato;
	}

	public void setCantidadFormato(Double cantidadFormato) {
		this.cantidadFormato = cantidadFormato;
	}

	public Double getPrecioUnidad() {
		return precioUnidad;
	}

	public void setPrecioUnidad(Double precioUnidad) {
		this.precioUnidad = precioUnidad;
	}

	public Double getImporteTotal() {
		return importeTotal;
	}

	public void setImporteTotal(Double importeTotal) {
		this.importeTotal = importeTotal;
	}

	public Double getGradoAlcohol() {
		return gradoAlcohol;
	}

	public void setGradoAlcohol(Double gradoAlcohol) {
		this.gradoAlcohol = gradoAlcohol;
	}

	public Double getGradoPlato() {
		return gradoPlato;
	}

	public void setGradoPlato(Double gradoPlato) {
		this.gradoPlato = gradoPlato;
	}

	public String getPaisOrigen() {
		return paisOrigen;
	}

	public void setPaisOrigen(String paisOrigen) {
		this.paisOrigen = paisOrigen;
	}

	public boolean isEsListoParaComer() {
		return esListoParaComer;
	}

	public void setEsListoParaComer(boolean esListoParaComer) {
		this.esListoParaComer = esListoParaComer;
	}
	
	


}
