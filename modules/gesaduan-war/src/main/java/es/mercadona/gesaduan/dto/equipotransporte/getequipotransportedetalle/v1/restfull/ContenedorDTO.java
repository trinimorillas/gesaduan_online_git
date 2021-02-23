package es.mercadona.gesaduan.dto.equipotransporte.getequipotransportedetalle.v1.restfull;

import es.mercadona.gesaduan.dto.common.AbstractDTO;

public class ContenedorDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;
	
	private Long numContenedor;
	private String codigoCarga;
	private String codigoCentroOrigen;
	private String mcaFacturado;
	private String fechaExpedicion;
	private String codigoAlmacen;
	private Integer numDosier;
	private Integer anyoDosier;

	public Long getNumContenedor() {
		return numContenedor;
	}

	public void setNumContenedor(Long numContenedor) {
		this.numContenedor = numContenedor;
	}

	public String getCodigoCarga() {
		return codigoCarga;
	}

	public void setCodigoCarga(String codigoCarga) {
		this.codigoCarga = codigoCarga;
	}

	public String getCodigoCentroOrigen() {
		return codigoCentroOrigen;
	}

	public void setCodigoCentroOrigen(String codigoCentroOrigen) {
		this.codigoCentroOrigen = codigoCentroOrigen;
	}

	public String getMcaFacturado() {
		return mcaFacturado;
	}

	public void setMcaFacturado(String mcaFacturado) {
		this.mcaFacturado = mcaFacturado;
	}

	public String getFechaExpedicion() {
		return fechaExpedicion;
	}

	public void setFechaExpedicion(String fechaExpedicion) {
		this.fechaExpedicion = fechaExpedicion;
	}

	public String getCodigoAlmacen() {
		return codigoAlmacen;
	}

	public void setCodigoAlmacen(String codigoAlmacen) {
		this.codigoAlmacen = codigoAlmacen;
	}

	public Integer getNumDosier() {
		return numDosier;
	}

	public void setNumDosier(Integer numDosier) {
		this.numDosier = numDosier;
	}

	public Integer getAnyoDosier() {
		return anyoDosier;
	}

	public void setAnyoDosier(Integer anyoDosier) {
		this.anyoDosier = anyoDosier;
	}	
	
}
